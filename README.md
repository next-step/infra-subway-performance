<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 인프라공방 샘플 서비스 - 지하철 노선도

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew clean build
```
<br>

## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.

### 1단계 - 화면 응답 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

#### A 쿼리 최적화

활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.

```sql
SELECT 
  고연봉관리자.사원번호,
  사원.이름,
  고연봉관리자.연봉,
  직급.직급명,
  사원출입기록.지역,
  사원출입기록.입출입구분,
  사원출입기록.입출입시간
FROM
(
	SELECT 
		급여.사원번호,
        급여.연봉
	FROM 급여
	INNER JOIN 직급 
		ON 직급.사원번호 = 급여.사원번호
		AND now() BETWEEN 직급.시작일자 AND 직급.종료일자 
	INNER JOIN 부서관리자 
		ON 부서관리자.사원번호 = 급여.사원번호
		AND NOW() BETWEEN 부서관리자.시작일자 AND 부서관리자.종료일자 
	INNER JOIN 부서 
		ON 부서.부서번호 = 부서관리자.부서번호 
		AND UPPER(부서.비고) = 'ACTIVE'
	WHERE NOW() BETWEEN 급여.시작일자 AND 급여.종료일자 
	ORDER BY 급여.연봉 DESC LIMIT 5
) AS 고연봉관리자
INNER JOIN 사원출입기록 
	ON 고연봉관리자.사원번호 = 사원출입기록.사원번호
	AND 사원출입기록.입출입구분 = UPPER('O')
INNER JOIN 사원 
	ON 고연봉관리자.사원번호 = 사원.사원번호
INNER JOIN 직급 
	ON 고연봉관리자.사원번호 = 직급.사원번호
	AND now() BETWEEN 직급.시작일자 AND 직급.종료일자
ORDER BY 고연봉관리자.연봉 DESC
;
```

인덱스 적용 결과

- 인덱스 적용전
  - 14 row(s) returned , 0.281 sec / 0.000 sec
- 인덱스 1만 적용 후
  - 인덱스 1 : CREATE INDEX `idx_사원출입기록_사원번호` ON `사원출입기록` (사원번호);
  - 14 row(s) returned , 0.015 sec / 0.000 sec
- 인덱스 2만 적용 후
  - 인덱스 2 : CREATE INDEX `idx_부서관리자_시작일자_종료일자` ON `부서관리자` (시작일자,종료일자);
  - 14 row(s) returned , 0.266 sec / 0.000 sec
- 인덱스 1,2 동시 적용 후
  - 14 row(s) returned , 0.000 sec / 0.000 sec


#### B 인덱스 설계

1. Coding as a Hobby 와 같은 결과를 반환하세요.

- 인덱스 적용전 : 0.140 sec / 0.000 sec
- 인덱스 적용후 : 0.046 sec / 0.000 sec

```sql
SELECT
	ROUND(SUM(IF(hobby = 'YES', 1, 0)) / COUNT(hobby) * 100) AS 'YES',
	ROUND(SUM(IF(hobby = 'NO', 1, 0)) / COUNT(hobby) * 100) AS 'NO'
FROM programmer p
;

CREATE INDEX idx_programmer_member_id ON programmer (member_id);
CREATE INDEX idx_covid_hospital_id ON covid (hospital_id);
```

2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

- 인덱스 적용전 : 4.203 sec / 5.984 sec
- 인덱스 적용후 : 0.016 sec / 0.000 sec

```sql
SELECT
    c.id,
    h.name
FROM programmer p
INNER JOIN covid c 
    ON  c.member_id = p.member_id 
INNER JOIN hospital h 
    ON h.id = c.hospital_id
;

CREATE INDEX idx_programmer_member_id ON programmer (member_id);
CREATE INDEX idx_covid_hospital_id ON covid (hospital_id);
```


3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.(covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

- 인덱스 적용전 : 30.734 sec / 0.000 sec  , Lost Connection
- 인덱스 적용후 (순차적으로 누적 적용)
  - 1.391 sec / 0.000 sec
    - CREATE INDEX idx_covid_programmer_id ON covid (programmer_id);
  - 1.250 sec / 0.000 sec
    - CREATE INDEX idx_programmer_hobby ON programmer (hobby);
  - 1.266 sec / 0.000 sec
    - CREATE INDEX idx_covid_hospital_id ON covid (hospital_id);
  - 1.390 sec / 0.000 sec
    - CREATE INDEX idx_programmer_student ON programmer (student); 
    - CREATE INDEX idx_programmer_years_coding ON programmer (years_coding);

> 인덱스를 많이 생성한다고 좋은 것은 아닌 것 같다.

```sql
SELECT
  c.id,
  h.name,
  p.hobby,
  p.dev_type,
  p.years_coding
FROM programmer p
       INNER JOIN covid c
                  ON c.programmer_id = p.id
       INNER JOIN hospital h
                  ON h.id = c.hospital_id
WHERE (p.student LIKE 'Yes%' OR p.years_coding = '0-2 years')
  AND p.hobby = 'YES'
ORDER BY p.id ASC
;
```

4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

- 인덱스 적용전 : 1.735 sec / 0.000 sec
- 인덱스 적용후
  - CREATE INDEX idx_covid_programmer_id ON covid (programmer_id); -- 0.203 sec / 0.000 sec
  - CREATE INDEX idx_programmer_country ON programmer (country); -- 0.156 sec / 0.000 sec
  - CREATE INDEX idx_member_age ON member (age); -- 0.141 sec / 0.000 sec

```sql
-- EXPLAIN
SELECT
	c.stay,
    count(m.id)
FROM covid c
INNER JOIN member m
	ON  m.id = c.member_id
    AND m.age >= 20 and m.age <= 29
INNER JOIN programmer p
	ON p.member_id = c.programmer_id
    AND p.country = 'India'
INNER JOIN hospital h
	ON h.id = c.hospital_id
    AND h.name = '서울대병원'
GROUP BY c.stay
ORDER BY NULL
;
```


5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

- 인덱스 적용전 : Lost Connection (over 30 sec)
- 인덱스 적용후
  - CREATE INDEX idx_covid_programmer_id ON covid (programmer_id); -- 0.328 sec

```sql
-- EXPLAIN
SELECT
	p.exercise,
    count(p.exercise)
FROM programmer p
INNER JOIN member m
	ON  m.id = p.member_id
    AND m.age >= 30 and m.age <= 39
INNER JOIN covid c
	ON c.programmer_id = p.id
INNER JOIN hospital h
	ON h.id = c.hospital_id
    AND h.name = '서울대병원'
GROUP BY p.exercise
ORDER BY NULL
;
```


2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

