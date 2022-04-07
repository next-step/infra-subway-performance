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

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
  (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```sql
SELECT
  고연봉자.사원번호,
  사원.이름,
  고연봉자.연봉,
  직급.직급명,
  사원출입기록.지역,
  사원출입기록.입출입구분,
  사원출입기록.입출입시간
FROM
  (
    SELECT
      급여.사원번호,
      급여.연봉
    FROM 부서
           INNER JOIN 부서관리자
                      ON 부서.부서번호 = 부서관리자.부서번호
                        AND NOW() BETWEEN 부서관리자.시작일자 AND 부서관리자.종료일자
           INNER JOIN 급여
                      ON 부서관리자.사원번호 = 급여.사원번호
                        AND NOW() BETWEEN 급여.시작일자 AND 급여.종료일자
    WHERE UPPER(부서.비고) = 'ACTIVE'
    ORDER BY 급여.연봉 DESC LIMIT 5
  ) AS 고연봉자
    INNER JOIN 사원출입기록
               ON 고연봉자.사원번호 = 사원출입기록.사원번호
                 AND 사원출입기록.입출입구분 = UPPER('O')
    INNER JOIN 직급
               ON 고연봉자.사원번호 = 직급.사원번호
                 AND now() BETWEEN 직급.시작일자 AND 직급.종료일자
    INNER JOIN 사원
               ON 고연봉자.사원번호 = 사원.사원번호
ORDER BY 고연봉자.연봉 DESC
;
```

개선을 시도하다 보니 아래 2개와 같은 쿼리를 더 작성하게 됐습니다.

```sql
SELECT
    활동중인부서관리자급여직급.사원번호,
    활동중인부서관리자급여직급.이름,
    활동중인부서관리자급여직급.연봉,
    활동중인부서관리자급여직급.직급명,
    사원출입기록.지역,
    사원출입기록.입출입구분,
    사원출입기록.입출입시간
FROM
    (
        SELECT
            활동중인부서관리자급여.사원번호,
            직급.직급명,
            활동중인부서관리자급여.이름,
            활동중인부서관리자급여.연봉
        FROM
            (
                SELECT
                    활동중인부서관리자.사원번호,
                    활동중인부서관리자.이름,
                    급여.연봉
                FROM
                    (
                        SELECT
                            활동중인부서의부서관리자.사원번호,
                            사원.이름
                        FROM
                            (
                                SELECT
                                    부서관리자.사원번호
                                FROM 부서
                                         INNER JOIN 부서관리자 ON 부서.부서번호 = 부서관리자.부서번호
                                     AND NOW() BETWEEN 부서관리자.시작일자 AND 부서관리자.종료일자
                                 WHERE UPPER(부서.비고)  = 'ACTIVE'
                            ) 활동중인부서의부서관리자
                                INNER JOIN 사원 ON 활동중인부서의부서관리자.사원번호 = 사원.사원번호
                    ) 활동중인부서관리자
                        INNER JOIN 급여 ON 활동중인부서관리자.사원번호 = 급여.사원번호
                 WHERE NOW() BETWEEN 급여.시작일자 AND 급여.종료일자
                 ORDER BY 급여.연봉 DESC
                 LIMIT 5
            ) 활동중인부서관리자급여
                INNER JOIN 직급
                           ON 활동중인부서관리자급여.사원번호 = 직급.사원번호
         WHERE NOW() BETWEEN 직급.시작일자 AND 직급.종료일자
    ) 활동중인부서관리자급여직급
        INNER JOIN 사원출입기록
                   ON 활동중인부서관리자급여직급.사원번호 = 사원출입기록.사원번호
                   AND UPPER(사원출입기록.입출입구분) = 'O'
ORDER BY 활동중인부서관리자급여직급.연봉 DESC
```

```sql
SELECT
    사원출입기록.사원번호,
    활동중인부서관리자사원이름급여직급.이름,
    활동중인부서관리자사원이름급여직급.연봉,
    활동중인부서관리자사원이름급여직급.직급명,
    MAX(사원출입기록.지역) AS 지역,
    MAX(사원출입기록.입출입구분) AS 입출입구분,
    MAX(사원출입기록.입출입시간) AS 입출입시간
FROM 사원출입기록
INNER JOIN
    (
        SELECT
            직급.사원번호,
            MAX(직급.직급명) AS 직급명,
            활동중인부서관리자사원이름급여.이름,
            MAX(활동중인부서관리자사원이름급여.연봉) AS 연봉
        FROM 직급
                 INNER JOIN (
            SELECT
                급여.사원번호,
                활동중인부서관리자사원이름.이름,
                급여.연봉
            FROM 급여
                     INNER JOIN
                 (
                     SELECT
                         사원.사원번호,
                         사원.이름
                     FROM 사원
                              INNER JOIN
                          (
                              SELECT
                                  부서관리자.사원번호
                              FROM 부서관리자
                                       INNER JOIN (
                                            SELECT 부서번호 FROM 부서 WHERE UPPER(부서.비고) = 'ACTIVE'
                                        ) 활동중인부서
                                        ON 활동중인부서.부서번호 = 부서관리자.부서번호
                              WHERE NOW() BETWEEN 부서관리자.시작일자 AND 부서관리자.종료일자
                          ) 활동중인부서관리자
                          ON 사원.사원번호 = 활동중인부서관리자.사원번호
                 ) 활동중인부서관리자사원이름
                 ON 급여.사원번호 = 활동중인부서관리자사원이름.사원번호
                     AND NOW() BETWEEN 급여.시작일자 AND 급여.종료일자
        ) 활동중인부서관리자사원이름급여
                            ON 직급.사원번호 = 활동중인부서관리자사원이름급여.사원번호
                            AND NOW() BETWEEN 직급.시작일자 AND 직급.종료일자
        GROUP BY 직급.사원번호
        ORDER BY 연봉 DESC
        LIMIT 5
    ) 활동중인부서관리자사원이름급여직급
ON 사원출입기록.사원번호 = 활동중인부서관리자사원이름급여직급.사원번호
AND UPPER(사원출입기록.입출입구분) = 'O'
GROUP BY 사원출입기록.사원번호, 사원출입기록.지역
;
```

## 위 3개 쿼리의 실행계획 결과

![image](https://user-images.githubusercontent.com/10750614/161395268-2df00bb1-95c6-4ef3-969f-44ffac6e642e.png)
![image](https://user-images.githubusercontent.com/10750614/161395205-1427ab35-8f37-496b-9f81-edac6635d114.png)
![image](https://user-images.githubusercontent.com/10750614/161395188-23427e10-bdd4-4ff3-aebd-ff8826e0f442.png)

오히려 첫번째 만들었던 쿼리의 결과가 다른 쿼리들에 비해 ROWS 가 낮아 성능이 좋아보이네요...
WHERE 절이 없으면 Using index 로 나오다가 WHERE 을 붙이면 filtered 는 낮아지지만 Using Index가 사라지더라고요.
결국 원하는 값을 출력하기 위해 where 절을 사용해 filtered 는 더 낮추진 못했습니다 ㅠㅠ

드라이빙 테이블과 드리븐 테이블의 선후를 결정하는데 있어서는 join 절의 select 결과의 수가 가장 적은 것이 깊은 depth 에 속하게 만들었습니다.
그렇게 만들다 보니 사원출입기록 테이블이 가장 바깥의 from 절에 위치하게 됐습니다.

여기서 어떻게 해야 성능이 더 나올지는 더 고민해도 답이 안나오더라고요 ㅠㅠ 피드백 부탁드립니다!

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환

## Coding as a Hobby 와 같은 결과를 반환하세요.

인덱스 적용전 : 140~160ms  
인덱스 적용후 : 50~70ms

```sql
SELECT
    ROUND(SUM(IF(hobby = 'YES', 1, 0)) / COUNT(hobby) * 100) AS 'YES',
    ROUND(SUM(IF(hobby = 'NO', 1, 0)) / COUNT(hobby) * 100) AS 'NO'
FROM programmer p;

CREATE INDEX idx_programmer_hobby ON programmer (hobby);
```


## 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

인덱스 적용전 : 280~ 299ms
인덱스 적용후 : 22 ~ 49ms

```sql
SELECT
  c.id,h.name
FROM covid c
       INNER JOIN
     (
       SELECT id FROM programmer
     ) p
     ON c.programmer_id = p.id
       INNER JOIN
     (
       SELECT id, name   FROM hospital
     ) h
     ON c.hospital_id = h.id
;

CREATE INDEX idx_covid_id ON covid (id);
CREATE INDEX idx_covid_programmer_id ON covid (programmer_id);
CREATE INDEX idx_covid_hospital_id ON covid (hospital_id);
CREATE INDEX idx_programmer_member_id ON programmer (id);
CREATE INDEX idx_hospital_id ON hospital (id,name);
```

## 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)


인덱스 적용전 : 2분동안 안끝남

programmer 인덱스 적용후 : 706 ms
hospital 인덱스 적용후 : 2분동안 안끝남
covid 인덱스 적용후 : 1,256ms

> where 절에 조건이 있는 programmer 테이블의 인덱스를 적용해야 시간이 단축되는 것 같다.

programmer,hospital 인덱스 적용후 : 640 ms
covid, hospital 인덱스 적용후 : 165ms
programmer, covid 인덱스 적용후 : 2,635ms

> 그런데 where 절 컬럼의 인덱스가 사용됐지만 programmer,covid 인덱스 적용한 결과는 시간이 느려졌다. 왤까...?

programmer,hospital,covid 인덱스 적용후 : 23 ms


```sql
select count(*) from programmer where student like 'Yes%'; # 24502
select count(*) from programmer where years_coding = '0-2 years'; # 10682
select count(*) from programmer where hobby = 'YES'; # 79897

SELECT
  c.id,
  h.name,
  p.hobby,
  p.dev_type,
  p.years_coding
FROM covid c
       INNER JOIN
     (
       SELECT id,hobby,student,years_coding,dev_type FROM programmer
       WHERE hobby = 'YES' AND (student LIKE 'Yes%' OR years_coding = '0-2 years')
     ) p
     ON c.programmer_id = p.id
       INNER JOIN
     (
       SELECT id,name FROM hospital
     ) h
     ON c.hospital_id = h.id
ORDER BY p.id ASC
;

CREATE INDEX idx_covid_id ON covid (id);
CREATE INDEX idx_covid_programmer_id ON covid (programmer_id);
CREATE INDEX idx_covid_hospital_id ON covid (hospital_id);
CREATE INDEX idx_programmer_where_condition ON programmer (hobby, student, years_coding);
CREATE INDEX idx_programmer_id ON programmer (id);
CREATE INDEX idx_hospital_id ON hospital (id,name);
```

## 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)


인덱스 적용전: 2분동안 안끝남
covid 테이블의 인덱스만 적용 : 1000ms  
covid,programmer 테이블의 인덱스 적용 : 80~100ms  
covid,programmer,hospital 테이블 인덱스 적용 : 90~110ms    
모든 인덱스 적용 후 : 70 ~ 90ms

> 인덱스를 설정하면 자동으로 범위 조건이 있는 테이블을 제일 마지막에 실행하게 한다.


```sql
SELECT count(*) FROM member WHERE age >= 20 AND age <= 29; # 22745
SELECT count(*) FROM programmer WHERE country = 'India'; # 13721
SELECT count(*) FROM hospital WHERE name = '서울대병원'; # 1

SELECT
  c.stay
FROM covid c
       INNER JOIN
     (
       SELECT id FROM hospital WHERE name = '서울대병원'
     ) h
     ON c.hospital_id = h.id
       INNER JOIN
     (
       SELECT id FROM programmer WHERE country = 'India'
     ) p
     ON c.programmer_id = p.id
       INNER JOIN
     (
       SELECT id FROM member WHERE age >= 20 AND age <= 29
     ) m
     ON c.member_id = m.id
GROUP BY c.stay
ORDER BY NULL
;

CREATE INDEX idx_covid_id ON covid (id);
CREATE INDEX idx_covid_hospital_id ON covid (hospital_id);
CREATE INDEX idx_covid_programmer_id ON covid (programmer_id);
CREATE INDEX idx_covid_member_id ON covid (member_id);
CREATE INDEX idx_covid_stay ON covid (stay);
CREATE INDEX idx_programmer_id ON programmer (id);
CREATE INDEX idx_programmer_country ON programmer (country);
CREATE INDEX idx_hospital_id ON hospital (id);
CREATE INDEX idx_hospital_name ON hospital (name);
CREATE INDEX idx_member_id ON member (id);
CREATE INDEX idx_member_age ON member (age);
```

```sql
CREATE INDEX idx_hospital_id ON hospital (id);
CREATE INDEX idx_hospital_name ON hospital (name);
CREATE INDEX idx_member_id ON member (id);
CREATE INDEX idx_member_age ON member (age);

아래로 변경

CREATE INDEX idx_hospital_two ON hospital (id, name);
CREATE INDEX idx_member_two ON member (id, age);
```

아래 인덱스로 변경시 : 40~60ms


## 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

인덱스 적용전: 2분동안 안끝남

모든 테이블의 인덱스 적용후: 40~50ms
- programmer, member 의 인덱스를 컬럼 하나씩 인덱스 걸지 않고  
  두개를 한번에 거니까 시간이 많이 줄어들었음.programmer (id,exercise),member (id,age)

> member와 programmer 테이블 조인문 위치를 바꿔도 결과는 같다.
  인덱스를 설정하면 자동으로 범위 조건이 있는 테이블을 제일 마지막에 실행하게 한다. 

```sql
SELECT
    count(p.exercise)
FROM covid c
INNER JOIN
    (
        SELECT id FROM hospital where name = '서울대병원'
    ) h
ON c.hospital_id = h.id
INNER JOIN
    (
        SELECT id FROM member WHERE age >= 30 AND age <= 39
     ) m
ON c.member_id = m.id
INNER JOIN
     (
         SELECT id,exercise FROM programmer
     ) p
     ON c.programmer_id = p.id
GROUP BY p.exercise
ORDER BY NULL
;

CREATE INDEX idx_covid_hospital_id ON covid (hospital_id);
CREATE INDEX idx_covid_member_id ON covid (member_id);
CREATE INDEX idx_covid_programmer_id ON covid (programmer_id);
# CREATE INDEX idx_programmer_id ON programmer (id);
# CREATE INDEX idx_programmer_exercise ON programmer (exercise);
CREATE INDEX idx_programmer_two ON programmer (id,exercise);
CREATE INDEX idx_hospital_id ON hospital (id);
CREATE INDEX idx_hospital_name ON hospital (name);
#CREATE INDEX idx_member_id ON member (id);
#CREATE INDEX idx_member_age ON member (age);
#CREATE INDEX idx_member_two ON member (age,id);
CREATE INDEX idx_member_two ON member (id,age);


DROP INDEX idx_covid_hospital_id ON covid;
DROP INDEX idx_covid_member_id ON covid;
DROP INDEX idx_covid_programmer_id ON covid;
#DROP INDEX idx_programmer_id ON programmer;
#DROP INDEX idx_programmer_exercise ON programmer;
DROP INDEX idx_programmer_two ON programmer;
DROP INDEX idx_hospital_id ON hospital;
DROP INDEX idx_hospital_name ON hospital;
#DROP INDEX idx_member_id ON member;
#DROP INDEX idx_member_age ON member;
DROP INDEX idx_member_two ON member;
```






---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
