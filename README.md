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

#### 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

> MacBook M1 기준 결과 표 입니다.

##### 쿼리 최적화

###### 미션 1

> 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

1. 쿼리 작성만으로 1s 이하로 반환한다.

```sql
SELECT TopPay.사원번호,
       TopPay.이름,
       any_value(TopPay.연봉),
       any_value(TopPay.직급명),
       EnterHistory.지역,
       EnterHistory.입출입구분
FROM (SELECT Worker.사원번호,
             Worker.이름,
             Pay.연봉,
             Position.직급명
      FROM (SELECT 부서번호 FROM 부서 WHERE 비고 = 'Active') AS Team
               INNER JOIN (SELECT 사원번호, 부서번호 FROM 부서관리자 WHERE now() BETWEEN 시작일자 AND 종료일자) AS TeamManager
                          ON Team.부서번호 = TeamManager.부서번호
               INNER JOIN (SELECT 사원번호, 연봉 FROM 급여 where now() BETWEEN 시작일자 AND 종료일자) AS Pay
                          ON TeamManager.사원번호 = Pay.사원번호
               INNER JOIN (SELECT 사원번호, 이름 FROM 사원) AS Worker
                          ON TeamManager.사원번호 = Worker.사원번호
               INNER JOIN (SELECT 사원번호, 직급명 FROM 직급 WHERE now() BETWEEN 시작일자 AND 종료일자) AS Position
                          ON TeamManager.사원번호 = Position.사원번호
      ORDER BY Pay.연봉 DESC
      LIMIT 5) AS TopPay
         INNER JOIN (SELECT 사원번호, 입출입구분, 지역, 입출입시간 FROM 사원출입기록 WHERE 입출입구분 = 'O') AS EnterHistory
                    ON TopPay.사원번호 = EnterHistory.사원번호
GROUP BY TopPay.사원번호, EnterHistory.지역
ORDER BY any_value(TopPay.연봉) DESC, EnterHistory.지역 asc;
```

```text
14 row(s) returned	1.964 sec / 0.000025 sec
14 row(s) returned	1.891 sec / 0.000079 sec
```

2. 인덱스 설정을 추가하여 50 ms 이하로 반환한다.

```sql
ALTER TABLE `tuning`.`사원출입기록`
    ADD INDEX `I_사원번호` USING BTREE (`사원번호`);
```

```text
14 row(s) returned	0.028 sec / 0.000013 sec
14 row(s) returned	0.019 sec / 0.000015 sec
```

##### 인덱스 설계

> 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환

###### 미션 1

> Coding as a Hobby 와 같은 결과를 반환하세요.

1. SQL

```sql
SELECT hobby,
       ROUND((Count(hobby) / Count(*) * 100), 1) AS rate
FROM programmer
GROUP BY hobby
ORDER BY rate DESC;
```

2. INDEX

```sql
ALTER TABLE `subway`.`programmer`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    ADD INDEX `I_hobby` USING BTREE (`hobby`);
```

3. Result

```text
2 row(s) returned	0.197 sec / 0.000020 sec
2 row(s) returned	0.182 sec / 0.0000091 sec
```

###### 미션 2

> 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

1. SQL

```sql
SELECT covid.id,
       hospital.name
FROM programmer
         JOIN covid
              ON covid.programmer_id = programmer.id
         JOIN hospital
              ON covid.hospital_id = hospital.id;
```

2. INDEX

```sql
ALTER TABLE `subway`.`programmer`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `subway`.`hospital`
    CHANGE COLUMN `id` `id` INT (11) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `subway`.`covid`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
```

3. Result

```text
1000 row(s) returned	0.036 sec / 0.012 sec
1000 row(s) returned	0.039 sec / 0.011 sec
```

###### 미션 3

> 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

1. SQL

```sql
SELECT covid.id,
       hospital.name,
       user.hobby,
       user.dev_type,
       user.years_coding
FROM covid
         JOIN(SELECT id, hobby, dev_type, years_coding
              FROM programmer
              WHERE hobby = 'yes'
                AND (student like 'yes%' OR years_coding = '0-2 years')) AS user
             ON covid.programmer_id = user.id
         JOIN hospital
              ON covid.hospital_id = hospital.id
ORDER BY user.id;
```

2. INDEX

```sql
ALTER TABLE `subway`.`programmer`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    ADD INDEX `I_hobby` USING BTREE (`hobby`);

ALTER TABLE `subway`.`covid`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    ADD INDEX `I_programmer_id` USING BTREE (`programmer_id`);

ALTER TABLE `subway`.`hospital`
    CHANGE COLUMN `id` `id` INT (11) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
```

3. Result

```text
1000 row(s) returned	0.036 sec / 0.085 sec
1000 row(s) returned	0.032 sec / 0.086 sec
```

###### 미션 4

> 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

1. SQL

```sql
SELECT covid.stay,
       Count(covid.stay)
FROM covid
         JOIN hospital
              ON covid.hospital_id = hospital.id
         JOIN programmer
              ON covid.programmer_id = programmer.id
         JOIN member
              ON covid.member_id = member.id
WHERE hospital.name = '서울대병원'
  AND programmer.country = 'india'
  AND member.age BETWEEN 20 AND 29
GROUP BY covid.stay;
```

2. INDEX

```sql
ALTER TABLE `subway`.`programmer`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `subway`.`member`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `subway`.`hospital`
    CHANGE COLUMN `id` `id` INT (11) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    CHANGE COLUMN `name` `name` VARCHAR (255) NULL DEFAULT NULL,
    ADD INDEX `I_name` USING BTREE (`name`);

ALTER TABLE `subway`.`covid`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    ADD INDEX `I_hospital_id` USING BTREE (`hospital_id`);
```

3. Result

```text
10 row(s) returned	0.239 sec / 0.000028 sec
10 row(s) returned	0.222 sec / 0.0000081 sec
```

###### 미션 5

> 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

1. SQL

```sql
SELECT programmer.exercise,
       Count(programmer.exercise)
FROM programmer
         JOIN covid
              ON covid.programmer_id = programmer.id
         JOIN hospital
              ON covid.hospital_id = hospital.id
         JOIN member
              ON covid.member_id = member.id
WHERE hospital.name = '서울대병원'
  AND member.age BETWEEN 30 AND 39
GROUP BY programmer.exercise;
```

2. INDEX

```sql
ALTER TABLE `subway`.`programmer`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `subway`.`member`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `subway`.`hospital`
    CHANGE COLUMN `id` `id` INT (11) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    CHANGE COLUMN `name` `name` VARCHAR (255) NULL DEFAULT NULL,
    ADD INDEX `I_name` USING BTREE (`name`);

ALTER TABLE `subway`.`covid`
    CHANGE COLUMN `id` `id` BIGINT (20) NOT NULL,
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    ADD INDEX `I_hospital_id` USING BTREE (`hospital_id`);
```

3. Result

```text
5 row(s) returned	0.261 sec / 0.0000091 sec
5 row(s) returned	0.247 sec / 0.000025 sec
```

#### 페이징 쿼리를 적용한 API endpoint를 알려주세요

