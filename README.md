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

- ### 접속 빈도가 높은 메인 페이지
    - #### 개선 전
        - [load 테스트 결과](./k6/main/load-result.txt)
        - [smoke 테스트 결과](./k6/main/smoke-result.txt)
        - [stress 테스트 결과](./k6/main/stress-result.txt)
    - #### 개선 후
        - [load 테스트 결과](./k6/main/load-result-after.txt)
        - [smoke 테스트 결과](./k6/main/smoke-result-after.txt)
        - [stress 테스트 결과](./k6/main/stress-result-after.txt)
- ### 데이터를 갱신하는 내정보 수정 페이지
    - #### 개선 전
        - [load 테스트 결과](./k6/myinfo-update/load-result.txt)
        - [smoke 테스트 결과](./k6/myinfo-update/smoke-result.txt)
        - [stress 테스트 결과](./k6/myinfo-update/stress-result.txt)
    - #### 개선 후
        - [load 테스트 결과](./k6/main/load-result-after.txt)
        - [smoke 테스트 결과](./k6/main/smoke-result-after.txt)
        - [stress 테스트 결과](./k6/main/stress-result-after.txt)
- ### 데이터를 조회하는데 여러 데이터를 참조하는 경로 탐색 페이지
    - #### 개선 전
        - [load 테스트 결과](./k6/path/load-result.txt)
        - [smoke 테스트 결과](./k6/path/smoke-result.txt)
        - [stress 테스트 결과](./k6/path/stress-result.txt)
    - #### 개선 후
        - [load 테스트 결과](./k6/main/load-result-after.txt)
        - [smoke 테스트 결과](./k6/main/smoke-result-after.txt)
        - [stress 테스트 결과](./k6/main/stress-result-after.txt)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    - Redis 캐시 적용
    - Thread Pool 설정 적용

---

### 2단계 - 조회 성능 개선하기

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

### A. 쿼리 최적화

활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
<br/> (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

- [x] 쿼리 작성만으로 1s 이하로 반환한다.

```mysql
# 0.80 -> 0.080
SELECT 사원.사원번호, 사원.이름, 직급.직급명, 사원출입기록.입출입구분, 사원출입기록.지역, 사원출입기록.입출입시간
FROM (SELECT 부서관리자.사원번호
      FROM (SELECT 부서번호 FROM 부서 WHERE 부서.비고 = 'ACTIVE') AS 부서
               INNER JOIN (SELECT 부서번호, 사원번호 FROM 부서관리자 WHERE NOW() BETWEEN 시작일자 AND 종료일자) AS 부서관리자
                          ON 부서관리자.부서번호 = 부서.부서번호
               INNER JOIN (SELECT 급여.사원번호, 급여.연봉 FROM 급여 WHERE NOW() BETWEEN 시작일자 AND 종료일자) 급여
                          ON 급여.사원번호 = 부서관리자.사원번호
      ORDER BY 급여.연봉 DESC
      LIMIT 5) AS 상위연봉관리자
         INNER JOIN (SELECT 사원번호, 직급명 FROM 직급 WHERE NOW() BETWEEN 시작일자 AND 종료일자) AS 직급
                    ON 상위연봉관리자.사원번호 = 직급.사원번호
         INNER JOIN (SELECT 사원번호, 이름 FROM 사원) AS 사원
                    ON 상위연봉관리자.사원번호 = 사원.사원번호
         INNER JOIN (SELECT * FROM 사원출입기록 WHERE 입출입구분 = 'O') AS 사원출입기록
                    ON 상위연봉관리자.사원번호 = 사원출입기록.사원번호
ORDER BY 사원.사원번호;
```

- [x] 인덱스 설정을 추가하여 50 ms 이하로 반환한다.

```mysql
CREATE INDEX I_사원번호 ON 사원출입기록 (사원번호);
```

### B. 인덱스 설계

- [ ] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
    - [x] [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 와 같은
      결과를 반환하세요.
      ```mysql
      SELECT round(((SUM(IF(hobby = 'Yes', 1, 0))) / count(id)) * 100, 1) as 'Yes',
      round(((SUM(IF(hobby = 'No', 1, 0))) / count(id)) * 100, 1)  as 'No'
      FROM programmer;


      ALTER TABLE `subway`.`programmer` ADD INDEX `I_hobby` (`hobby` ASC);
      ```

    - [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
      ```mysql
      SELECT covid.id, hospital.name
      FROM (SELECT id FROM programmer) as programmer
      INNER JOIN (select id, hospital_id, programmer_id from covid) covid ON covid.programmer_id = programmer.id
      INNER JOIN hospital ON hospital.id = covid.hospital_id
  
      ALTER TABLE hospital MODIFY name VARCHAR(255) NOT NULL;
      
      ALTER TABLE `subway`.`hospital`
      CHANGE COLUMN `id` `id` INT(11) NOT NULL,
      ADD PRIMARY KEY (`id`),
      ADD UNIQUE INDEX `id_unique` (`id` ASC);
  
      ALTER TABLE `subway`.`covid`
      CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL,
      ADD PRIMARY KEY (`id`),
      ADD UNIQUE INDEX `id_unique` (`id` ASC);
  
      ALTER TABLE `subway`.`programmer`
      CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
      ADD PRIMARY KEY (`id`),
      ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);
      ```

    - [ ] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
      (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
        ```mysql
          SELECT covid.id,
                 hospital.name,
                 programmer.hobby,
                 programmer.student,
                 programmer.dev_type,
                 programmer.years_coding
          FROM (SELECT id, hobby, dev_type, years_coding, student
                FROM programmer
                where (hobby = 'YES' AND student LIKE 'Yes%')
                   OR years_coding = '0-2 years') as programmer
                   INNER JOIN (select id, hospital_id, programmer_id from covid) covid
                              ON covid.programmer_id = programmer.id
                   INNER JOIN hospital ON hospital.id = covid.hospital_id;
       ```
    - [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
      ```mysql
      SELECT c.id, h.name, p.hobby, p.dev_type, p.years_coding
        FROM (SELECT id, name FROM hospital) h
          INNER JOIN (SELECT id, programmer_id, hospital_id FROM covid) c ON c.hospital_id = h.id
          INNER JOIN (SELECT id, hobby, dev_type, years_coding FROM programmer WHERE hobby = 'Yes' 
              AND (student LIKE 'Yes%' OR years_coding = '0-2 years')) p ON p.id = c.programmer_id
      ORDER BY p.id;
      ```

    - [x]  서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
       ```mysql
          SELECT p.exercise, count(p.id)
          FROM (SELECT id FROM hospital WHERE name = '서울대병원') h INNER JOIN (SELECT hospital_id, programmer_id FROM covid) c
          ON c.hospital_id = h.id INNER JOIN (SELECT id, member_id, exercise FROM programmer) p ON p.id = c.programmer_id
          INNER JOIN (SELECT id FROM member WHERE age >= 30 AND age <= 39) m ON p.member_id = m.id GROUP BY p.exercise;

          ALTER TABLE `subway`.`covid` ADD INDEX `I_hospital_id_programmer_id` (`hospital_id`, `programmer_id`);
       ```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요


