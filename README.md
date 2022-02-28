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

``` SQL
SELECT
  admin.사원번호
  , employee.이름
  , pay.연봉
  , position.직급명
  , visit_log.입출입시간
  , visit_log.지역
  , visit_log.입출입구분
FROM (
  SELECT dpt_sub.부서번호
  FROM tuning.부서 AS dpt_sub
  WHERE dpt_sub.비고 = "Active"
) AS dpt
JOIN tuning.부서관리자 AS admin
  ON admin.부서번호 = dpt.부서번호
  AND admin.종료일자 = "9999-01-01"
JOIN tuning.사원 AS employee
  ON employee.사원번호 = admin.사원번호
JOIN tuning.직급 AS position
  ON position.사원번호 = admin.사원번호
  AND position.종료일자 = "9999-01-01"
JOIN tuning.급여 AS pay
  ON pay.사원번호 = admin.사원번호
  AND pay.종료일자 = "9999-01-01"
JOIN tuning.사원출입기록 AS visit_log
  ON visit_log.사원번호 = admin.사원번호
  AND visit_log.입출입구분 = "O"
ORDER BY pay.연봉 DESC, visit_log.지역;
```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

   * Coding as a Hobby 와 같은 결과를 반환하세요.
      
      **[Query - befor indexing]**
      ```sql
      SELECT
        ROUND(COUNT(CASE WHEN hobby = "Yes" THEN 1 END) / COUNT(*) * 100, 1) AS Yes,
        ROUND(COUNT(CASE WHEN hobby = "No" THEN 1 END) / COUNT(*) * 100, 1) AS No
      FROM subway.programmer;

      Result(Duration / Fetch Time): 1.041 sec / 0.0000050 sec
      ```

      **[Query - after applying the index]**
      ```sql
      ALTER TABLE `subway`.`programmer` 
      ADD INDEX `IDX_HOBBY` (`hobby` ASC);

      Result(Duration / Fetch Time): 0.052 sec / 0.0000069 sec
      ```

   * 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

      **[Query - befor indexing]**
      ```sql
      SELECT
        programmer.member_id
          , covid.id
          , hospital.name
      FROM subway.programmer AS programmer
      JOIN subway.covid AS covid
        ON covid.member_id = programmer.member_id
      JOIN subway.hospital AS hospital
        ON hospital.id = covid.hospital_id
      ;

      # 50,000 rows
      Result: Error Code: 2013. Lost connection to MySQL server during query
      ```
      
      **[Query - after applying the index]**
      ```sql
      ALTER TABLE `subway`.`covid` 
      ADD INDEX `IDX_MEMBER_ID` (`member_id` ASC);
      ;

      ALTER TABLE `subway`.`hospital` 
      ADD UNIQUE INDEX `UQ_IDX_ID` (`id` ASC);
      ;

      # 50,000 rows
      Result(Duration / Fetch Time): 0.016 sec / 1.038 sec
      ```

   * 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
      
      **[Query - befor indexing]**
      ```sql
      SELECT
        covid.id
          , hospital.name
          , user.Hobby
          , user.DevType
          , user.YearsCoding
      FROM (
        SELECT
            filtered_programmer.member_id AS member_id
            , filtered_programmer.hobby AS Hobby
            , filtered_programmer.dev_type AS DevType
            , filtered_programmer.years_coding AS YearsCoding
          FROM subway.programmer AS filtered_programmer
          WHERE
            filtered_programmer.hobby = "Yes"
            AND filtered_programmer.student LIKE "Yes%"
            AND filtered_programmer.years_coding = "0-2 years"
      ) AS user
      JOIN subway.covid AS covid
        ON covid.member_id = user.member_id
      JOIN subway.hospital AS hospital
        ON hospital.id = covid.hospital_id
      ;

      # 3,610 rows
      Result(Duration / Fetch Time): 0.121 sec / 2.453 sec
      ```
      
      **[Query - after applying the index]**
      ```sql
      ALTER TABLE `subway`.`programmer` 
      ADD INDEX `IDX_STUDENT` (`student` ASC),
      ADD INDEX `IDX_YEARS_CODING` (`years_coding` ASC);
      ;

      # 3,610 rows
      Result(Duration / Fetch Time): 0.029 sec / 0.418 sec
      ```

   * 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

      **[Query - before indexing]**
      ```sql
      SELECT
        covid.stay AS Stay
          , COUNT(*) AS Count
      FROM (
        SELECT 
          covid.stay
              , covid.member_id
        FROM (
          SELECT *
          FROM subway.covid
          WHERE covid.hospital_id = (SELECT id FROM subway.hospital AS hospital WHERE hospital.name = "서울대병원")
        ) AS covid
        JOIN subway.member ON member.id = covid.member_id AND member.age >= 20 AND member.age < 30
      ) AS covid
      WHERE covid.member_id IN (SELECT member_id FROM programmer WHERE country = "India")
      GROUP BY covid.stay
      ;

      # 10 rows
      Result(Duration / Fetch Time): 2.192 sec / 0.0000079 sec
      ```

      **[Query - after applying the index]**

      ```sql
      ALTER TABLE `subway`.`member` 
      CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
      ADD PRIMARY KEY (`id`) ,
      ADD INDEX `IDX_AGE` (`age` ASC)
      ;

      ALTER TABLE `subway`.`hospital` 
      CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
      ADD PRIMARY KEY (`id`) ,
      ADD UNIQUE INDEX `UQ_IDX_ID` (`id` ASC)
      ;

      ALTER TABLE `subway`.`programmer` 
      ADD INDEX `IDX_COUNTRY` (`country` ASC) ,
      ADD INDEX `IDX_MEMBER_ID` (`member_id` ASC)
      ;

      ALTER TABLE `subway`.`covid` 
      ADD INDEX `IDX_STAY` (`stay` ASC) ,
      ADD INDEX `IDX_HOSPITAL_ID` (`hospital_id` ASC)
      ;

      # 10 rows
      Result(Duration / Fetch Time): 0.036 sec / 0.0000079 sec
      ```

   * 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

      **[Query - no additional indexes]**

      ```sql
      SELECT
        user.exercise AS Exercise
          , COUNT(*) AS Count
      FROM (
        SELECT 
          programmer.exercise
              , programmer.member_id
        FROM (
          SELECT covid.member_id
          FROM subway.covid
          WHERE covid.hospital_id = (
            SELECT id FROM subway.hospital AS hospital WHERE hospital.name = "서울대병원"
          )
        ) AS covid
        JOIN subway.member ON member.id = covid.member_id AND member.age >= 30 AND member.age < 40
        JOIN subway.programmer ON programmer.member_id = covid.member_id
      ) AS user
      GROUP BY user.exercise
      ;

      # 5 rows
      Result(Duration / Fetch Time): 0.034 sec / 0.0000081 sec
      ```
      
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
