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

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

``` sql
SELECT A.사원번호, 이름, 연봉, 직급명, 입출입시간, 지역, 입출입구분
FROM
(
	SELECT S.사원번호, 이름, 연봉, MAX(입출입시간) AS 입출입시간, 지역, 입출입구분
	FROM 
	(
		SELECT DM.사원번호, 이름, 연봉
		FROM 
		(
			SELECT 사원.사원번호, 사원.이름, 부서.부서번호
			FROM 부서관리자 
			JOIN 부서 
			    ON 부서관리자.부서번호 = 부서.부서번호
			JOIN 사원 
			    ON 부서관리자.사원번호 = 사원.사원번호
			WHERE 부서.비고 = 'ACTIVE' AND 부서관리자.종료일자 >= CURDATE()
		) DM JOIN 급여 
                        ON DM.사원번호 = 급여.사원번호
		WHERE 종료일자 >= CURDATE() 
		ORDER BY 연봉 DESC 
		LIMIT 5
	) S JOIN 사원출입기록 
                ON S.사원번호 = 사원출입기록.사원번호
	WHERE 입출입구분 = 'O'
	GROUP BY 사원번호, 지역, 연봉
	ORDER BY 연봉 DESC
) A JOIN 직급 
        ON A.사원번호 = 직급.사원번호
WHERE 직급.종료일자 >= CURDATE();

```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

-  Coding as a Hobby 와 같은 결과를 반환하세요

``` sql
CREATE INDEX idx_user_hobby ON programmer (hobby); 
SELECT 
	ROUND((SELECT COUNT(*) FROM programmer WHERE hobby = 'yes')/(SELECT COUNT(*) FROM programmer) * 100 ,1) AS 'YES', 
	ROUND((SELECT COUNT(*) FROM programmer WHERE hobby = 'no')/(SELECT COUNT(*) FROM programmer) * 100, 1) AS 'NO';
```

- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
``` sql
ALTER TABLE covid ADD PRIMARY KEY (id);
ALTER TABLE hospital ADD PRIMARY KEY (id);
ALTER TABLE programmer ADD PRIMARY KEY (id);
CREATE INDEX idx_name ON hospital (name);

SELECT covid.id, hospital.name
FROM hospital
JOIN covid ON hospital.id = covid.hospital_id
JOIN programmer ON programmer.id = covid.programmer_id;
```

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
``` sql
ALTER TABLE covid ADD PRIMARY KEY (id);
ALTER TABLE hospital ADD PRIMARY KEY (id);
ALTER TABLE programmer ADD PRIMARY KEY (id);
CREATE INDEX idx_programmer_hospital_id  ON covid (programmer_id, hospital_id);

SELECT covid.id, hospital.name, programmer.hobby, programmer.dev_type, programmer.years_coding
FROM programmer
JOIN covid ON  programmer.id = covid.programmer_id
JOIN hospital ON hospital.id = covid.hospital_id
WHERE (hobby = 'yes' AND student LIKE 'yes%') OR years_coding = '0-2 years';
```

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
``` sql
ALTER TABLE covid ADD PRIMARY KEY (id);
ALTER TABLE programmer ADD PRIMARY KEY (id);
ALTER TABLE member ADD PRIMARY KEY (id);
CREATE INDEX idx_stay  ON covid (hospital_id,stay);
CREATE INDEX idx_age  ON member (age);
CREATE INDEX idx_country  ON programmer (country);
CREATE INDEX idx_name  ON hospital (name);


EXPLAIN
SELECT covid.stay, COUNT(*) as COUNT
FROM covid
JOIN member on covid.member_id = member.id
JOIN programmer on covid.programmer_id = programmer.id
JOIN hospital on covid.hospital_id = hospital.id
WHERE member.age BETWEEN 20 AND 29
AND programmer.country = 'india'
AND hospital.name = '서울대병원'
GROUP BY covid.stay;
```

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
``` sql
ALTER TABLE covid ADD PRIMARY KEY (id);
ALTER TABLE programmer ADD PRIMARY KEY (id);
ALTER TABLE member ADD PRIMARY KEY (id);
CREATE INDEX idx_stay  ON covid (programmer_id,member_id);
CREATE INDEX idx_age  ON member (age);
CREATE INDEX idx_exercise  ON programmer (exercise);
CREATE INDEX idx_name  ON hospital (name);


EXPLAIN
SELECT programmer.exercise, COUNT(*) as COUNT
FROM programmer
JOIN covid ON covid.programmer_id = programmer.id
JOIN member ON covid.member_id = member.id
JOIN hospital ON covid.hospital_id = hospital.id
WHERE member.age between 30 AND 39 AND hospital.name = '서울대병원'
GROUP BY programmer.exercise;
```
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
