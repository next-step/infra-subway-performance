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
/monitoring에 결과 저장했습니다
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- reverse proxy 개선
  - gzip 압축을 통해 정적 컨텐츠 크기를 줄였습니다.
  - 정적컨텐츠 cache를 적용했습니다.
  - tls,http2를 적용했습니다.
- was 개선
  - redis를 사용해서 역,노선,경로 조회에 캐싱을 적욯했습니다.
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- [바로가기](https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-01791e69a8c40e4ec)

3. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
/monitoring/stress/autoscale
폴더에 정리했습니다
```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
/monitoring/load/autoscale
/monitoring/smoke/autoscale
/monitoring/stress/autoscale
폴더내부에 정리했습니다
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```roomsql
SELECT employee.id          AS "사원번호",
       employee.name        AS "이름",
       employee.income      AS "연봉",
       employee.position    AS "직급명",
       record.region        AS "지역",
       record.record_symbol AS "입출입구분",
       record.time          AS "입출입시간"
FROM   record
       INNER JOIN (SELECT manager.employee_id    AS id,
                          employee.last_name     AS name,
                          salary.annual_income   AS income,
                          position.position_name AS position
                   FROM   manager
                          INNER JOIN employee
                                  ON manager.employee_id = employee.id
                                     AND manager.end_date > Sysdate()
                          INNER JOIN department
                                  ON manager.department_id = department.id
                                     AND department.note = 'active'
                          INNER JOIN position
                                  ON manager.employee_id = position.id
                                     AND position_name = 'Manager'
                          INNER JOIN salary
                                  ON manager.employee_id = salary.id
                                     AND salary.end_date > Sysdate()
                   ORDER  BY salary.annual_income DESC
                   LIMIT  5) employee
               ON employee.id = record.employee_id
WHERE  record.record_symbol = 'O'; 

```
---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
##### 실행결과는 docs/step4 폴더에 순서대로 저장해뒀습니다

- Coding as a Hobby 와 같은 결과를 반환하세요.
```roomsql
ALTER TABLE programmer
  ADD CONSTRAINT programmer_pk PRIMARY KEY (id);

CREATE INDEX programmer_hobby_index ON programmer (hobby);

SELECT hobby,
       Count(*) / (SELECT Count(*)
                   FROM   programmer) * 100 AS rate
FROM   programmer
GROUP  BY hobby
ORDER  BY hobby DESC; 
```

- 프로그래머별로 해당하는 병원 이름을 반환하세요.
```roomsql
ALTER TABLE hospital
  ADD CONSTRAINT hospital_pk PRIMARY KEY (id);

ALTER TABLE covid
  ADD CONSTRAINT covid_pk PRIMARY KEY (id);

CREATE INDEX covid_programmer_id_index ON covid (programmer_id);

CREATE INDEX covid_hospital_id_index ON covid (hospital_id); 

SELECT covid.id,
       hospital.name
FROM   covid
       INNER JOIN programmer
               ON covid.programmer_id = programmer.id
       INNER JOIN hospital
               ON covid.hospital_id = hospital.id; 
```
- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```roomsql
SELECT covid.id,
       hospital.NAME,
       USER.hobby,
       USER.dev_type,
       USER.years_coding
FROM   covid
       INNER JOIN hospital
               ON covid.hospital_id = hospital.id
       INNER JOIN (SELECT id,
                          years_coding,
                          hobby,
                          dev_type
                   FROM   programmer
                   WHERE  hobby = 'Yes'
                          AND ( years_coding = '0-2 years'
                                 OR student IN ( 'Yes, full-time',
                                                 'Yes, part-time' ) )) USER
               ON covid.programmer_id = USER.id
ORDER  BY USER.id; 
```
- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
```roomsql
ALTER TABLE member
  ADD CONSTRAINT member_pk PRIMARY KEY (id); 

SELECT covid.stay,
       Count(*)
FROM   covid
       INNER JOIN hospital
               ON covid.hospital_id = hospital.id
                  AND hospital.NAME = '서울대병원'
       INNER JOIN member
               ON covid.member_id = member.id
                  AND member.age BETWEEN 20 AND 29
       INNER JOIN programmer
               ON covid.programmer_id = programmer.id
                  AND programmer.country = 'india'
GROUP  BY covid.stay; 
```

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```roomsql
CREATE INDEX covid_member_id_index ON covid (member_id);

SELECT exercise,
       Count(*)
FROM   programmer
       INNER JOIN covid
               ON programmer.id = covid.programmer_id
       INNER JOIN hospital
               ON covid.hospital_id = hospital.id
                  AND hospital.NAME = '서울대병원'
       INNER JOIN member
               ON covid.member_id = member.id
                  AND member.age BETWEEN 30 AND 39
GROUP  BY exercise;
```
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
