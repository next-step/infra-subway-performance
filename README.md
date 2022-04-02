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
    ```sql
    SET @end_date = '9999-01-01';
    
    SELECT emp.사원번호,
           emp.이름,
           salary_2.연봉,
           po.직급명,
           ar.지역,
           ar.입출입구분,
           ar.입출입시간
    FROM   사원출입기록 AS ar
           JOIN (SELECT salary_1.사원번호,
                        Max(salary_1.연봉) AS 연봉
                 FROM   급여 AS salary_1
                        INNER JOIN 부서관리자 AS dm
                                ON dm.사원번호 = salary_1.사원번호
                                   AND dm.종료일자 = @end_date
                        INNER JOIN 부서 AS dep
                                ON dep.부서번호 = dm.부서번호
                 WHERE  dep.비고 = 'Active'
                        AND salary_1.종료일자 = @end_date
                 GROUP  BY 사원번호
                 ORDER  BY 2 DESC
                 LIMIT  5) AS salary_2
             ON salary_2.사원번호 = ar.사원번호
           INNER JOIN 사원 AS emp
                   ON emp.사원번호 = ar.사원번호
           INNER JOIN 직급 AS po
                   ON po.사원번호 = ar.사원번호
                      AND po.종료일자 = @end_date
    WHERE  po.종료일자 = @end_date
           AND ar.입출입구분 = 'O'; 
    ```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- [x] Coding as a Hobby 와 같은 결과를 반환하세요.
```sql
-- 실행속도 1.062s
SELECT
    CONCAT(ROUND(COUNT(
                CASE WHEN hobby = 'YES' THEN
                    1
                END) / COUNT(*) * 100, 2), '%') AS 'yes',
    CONCAT(ROUND(COUNT(
                CASE WHEN hobby = 'NO' THEN
                    1
                END) / COUNT(*) * 100, 2), '%') AS 'no'
FROM
    programmer;

-- index 추가 후 0.035 sec / 0.0000069 sec
ALTER TABLE programmer
    ADD INDEX ix_hobby (hobby);
```
- [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```sql
-- 너무 오래걸려서 중간에 kill함.
SELECT sql_no_cache covid.id,
        hospital. `name`
FROM   covid AS covid
           INNER JOIN programmer AS programmer
                      ON covid.programmer_id = programmer.id
           INNER JOIN hospital AS hospital
                      ON hospital.id = covid.hospital_id;

-- 1. 조인에 필요한 컬럼에 인덱스 추가해봄. 549ms
ALTER TABLE covid
    ADD INDEX ix_programmer_id (programmer_id ASC);

ALTER TABLE covid
    ADD INDEX ix_hospital_id (hospital_id ASC);

ALTER TABLE hospital
    ADD PRIMARY KEY (id);

-- 2. 위처럼 진행 했을때 100ms가 나올 수 없을 것 같고, 한개의 테이블에선 한개의 인덱스만 타는 걸로 알고있어,
-- ix_programmer_id, ix_hospital_id를 삭제 후, 아래인덱스를 추가함. 0.0052 sec / 0.0014 sec
-- 진행 과정에서 PRIMARY KEY, 복합 키, 외래키 이것저것 사용을 다해 보았지만,  위보다 더 빠르게 나오게 하기 힘들다고 판단함.
ALTER TABLE covid
    ADD INDEX ix_programer_id_hospital_id (programmer_id, hospital_id);

ALTER TABLE programmer
    ADD PRIMARY KEY (id);




```
- [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```sql
-- 실행속도 1.762
SELECT
    sql_no_cache covid.id,
        hospital. `name`,
    programmer.hobby,
    programmer.dev_type,
    programmer.years_coding
FROM
    programmer AS programmer
        INNER JOIN covid AS covid ON covid.programmer_id = programmer.id
        INNER JOIN hospital AS hospital ON hospital.id = covid.hospital_id
WHERE (programmer.hobby = 'YES'
    AND Substring_index(programmer.student, ',', 1) = 'Yes')
   OR years_coding_prof = '0-2 years');

-- student 여부를 찾을때 인덱스를 타지 않으므로 FULLTEXT 인덱스 추가 후 실행속도 3.577
ALTER TABLE programmer 
    ADD FULLTEXT(student);

SELECT
    sql_no_cache covid.id,
        hospital. `name`,
    programmer.hobby,
    programmer.dev_type,
    programmer.years_coding
FROM
    programmer AS programmer
        INNER JOIN covid AS covid ON covid.programmer_id = programmer.id
        INNER JOIN hospital AS hospital ON hospital.id = covid.hospital_id
WHERE (programmer.hobby = 'YES'
    AND MATCH(programmer.student) AGAINST ('Yes' IN NATURAL LANGUAGE MODE)
   OR years_coding_prof = '0-2 years';


-- OR 절이 INDEX를 타지 않는것으로 보여, 쿼리를 수정하기로 함.
-- 실행속도 1.366
SELECT
    covid.id,
    hospital.`name`,
    programmer.id,
    programmer.dev_type,
    programmer.years_coding
FROM
    (
        SELECT
            id,
            hobby,
            dev_type,
            years_coding
        FROM
            programmer
        WHERE
                        hobby = 'YES'
                    AND MATCH(programmer.student) AGAINST ('Yes' IN NATURAL LANGUAGE MODE)
        UNION
            ALL
        SELECT
            id,
            hobby,
            dev_type,
            years_coding
        FROM
            programmer
        WHERE
            years_coding_prof = '0-2 years'
    ) AS programmer
        INNER JOIN covid AS covid ON covid.programmer_id = programmer.id
        INNER JOIN hospital AS hospital ON hospital.id = covid.hospital_id;

-- 인덱스 추가 2.250
ALTER TABLE programmer
    ADD INDEX ix_hobby_student (hobby DESC, student);

ALTER TABLE programmer
    ADD INDEX ix_years_coding_prof (years_coding_prof ASC);

-- UNION 절로 인해 속도개선이 힘들다고 판단함.
-- 0.0030 sec / 0.000014 sec
SELECT
    SQL_NO_CACHE covid.id,
        hospital. `name`,
    programmer.hobby,
    programmer.dev_type,
    programmer.years_coding
FROM
    programmer AS programmer
        INNER JOIN covid AS covid ON covid.programmer_id = programmer.id
        INNER JOIN hospital AS hospital ON hospital.id = covid.hospital_id
WHERE (programmer.hobby = 'YES' AND programmer.student IN ('Yes, full-time', 'Yes, part-time'))
   OR programmer.years_coding_prof = '0-2 years';
```
- [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
```sql
-- 수행시간 169.881s
SELECT
    covid.stay,
    COUNT(*)
FROM
    covid AS covid
        JOIN hospital AS hospital ON hospital.id = covid.hospital_id
        AND hospital. `name` = '서울대병원'
        JOIN `member` AS `member` ON `member`.id = covid.member_id
        AND `member`.age >= 20
        AND `member`.age < 30
        JOIN programmer AS programmer ON programmer.member_id = `member`.id
        AND programmer.member_id > 0
        AND programmer.country = 'India'
GROUP BY
    covid.stay;
    
-- 아래 인덱스 추가 후 실행속도 0.042 sec / 0.0000079 sec
ALTER TABLE covid
    ADD INDEX ix_member_id (member_id ASC);

ALTER TABLE `member`
    ADD PRIMARY KEY (id);

ALTER TABLE programmer
    ADD INDEX ix_country_member_id (country, member_id);

ALTER TABLE hospital
    ADD INDEX ix_id_name (id, `name`);

ALTER TABLE `member`
    ADD INDEX ix_age_id (age ASC, id);
```
- [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```sql
-- 수행시간 0.891 sec / 0.0000069 sec
SELECT
    SQL_NO_CACHE
    programmer.exercise,
        COUNT(*)
FROM
    covid AS covid
        JOIN hospital AS hospital ON hospital.id = covid.hospital_id
        AND hospital. `name` = '서울대병원'
        JOIN `member` AS `member` ON `member`.id = covid.member_id
        AND `member`.age >= 30
        AND `member`.age < 40
        JOIN programmer AS programmer ON programmer.member_id = `member`.id
        AND programmer.member_id > 0
GROUP BY
    programmer.exercise;

-- 인덱스 추가 시 수행속도 0.028 sec / 0.0000081 sec
ALTER TABLE programmer
    ADD INDEX ix_member_id (member_id);

ALTER TABLE covid
    ADD INDEX ix_hospital_id_member_id (hospital_id, member_id);
```
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
