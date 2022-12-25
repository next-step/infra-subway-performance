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
  - /src/main/k6 before에 개선 전, After에 개선 후 테스트결과 첨부하였습니다.
  
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
  - Reverse Proxy nginx서버 개선 진행하였습니다. (gzip 압축, cache 설정, TLS, HTTP/2 설정)
  - WAS 성능 개선 진행하였습니다. (Spring data cache 적용 LineService, MapService, StationService)

---

### 2단계 - 스케일 아웃
 - [x] springboot에 HTTP Cache, gzip 설정하기
   - 미션1 : 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
   - 미션2 : 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
   - 미션3 : 모든 정적 자원에 대해 no-cache, no-store설정이 가능한가?
     - no-cache값은 대부분의 브라우저에서 max-age=0과 동일한 뜻입니다. 즉, 캐시는 저장하지만 사용하려고 할 때마다 서버에 재검증 요청을 보내야 합니다.
     - no-store값은 캐시를 절대로 해서는 안 되는 리소스일 때 사용합니다. 캐시를 만들어서 저장조차 하지말라는 가장 강력한 Cache-Control값입니다. no-store를 사용하면 브라우저는 어떤 경우에도 캐시 저장소에 해당 리소스를 저장하지 않습니다.
     - 두 캐시 컨트롤 정의를 보면 항상 캐시가 무효화 되어야 맞지만, 오래된 브라우저와의 호환 및 버그, 수많은 프록시 캐시 업체들과 그 구현 서버 등등 여러 이슈로 no-store만 사용하지 않고 메이저 사이트에서는 cache-control로 no-cache, no-store, must-revalidate를 함께 설정하고 있습니다.
     - https://www.inflearn.com/questions/112647/no-store-%EB%A1%9C%EB%8F%84-%EC%B6%A9%EB%B6%84%ED%95%A0-%EA%B2%83-%EA%B0%99%EC%9D%80%EB%8D%B0-no-cache-must-revalidate-%EB%8A%94-%EC%99%9C-%EA%B0%99%EC%9D%B4-%EC%B6%94%EA%B0%80%ED%95%98%EB%8A%94-%EA%B2%83%EC%9D%B8%EA%B0%80%EC%9A%94
     - https://stackoverflow.com/questions/49547/how-do-we-control-web-page-caching-across-all-browsers
    
 - [x] Launch Template 작성하기
 - [x] Auto Scailing Group 생성하기
 - [x] Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.
https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0800573c3706ba409

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

/main/k6/step2 위치에 업로드하였습니다!

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

/main/k6/step2 위치에 업로드하였습니다!

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
-쿼리
   SELECT
   active_dept_top5_manager.employee_id AS 사원번호,
   employee.last_name AS 이름,
   active_dept_top5_manager.annual_income AS 연봉,
   position.position_name AS 직급명,
   record.region AS 지역,
   record.record_symbol AS 입출입구분,
   record.time AS 입출입시간
   from
   (SELECT
   employee_department.employee_id AS employee_id,
   salary.annual_income
   FROM employee_department
   INNER JOIN department ON employee_department.department_id = department.id
   INNER JOIN salary ON employee_department.employee_id = salary.id
   INNER JOIN manager ON employee_department.employee_id = manager.employee_id
   WHERE 1=1
   AND UPPER(department.note) = 'ACTIVE'
   AND salary.start_date < now() AND now() < salary.end_date
   AND manager.start_date < now() AND now() < manager.end_date
   order by salary.annual_income desc limit 5) active_dept_top5_manager
   INNER JOIN employee ON active_dept_top5_manager.employee_id = employee.id
   INNER JOIN position ON active_dept_top5_manager.employee_id = position.id
   INNER JOIN record ON active_dept_top5_manager.employee_id = record.employee_id
   WHERE 1=1
   AND UPPER(position.position_name) = 'Manager'
   AND record.record_symbol = 'O';

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

- 프로젝트 루트디렉토리에 workbench.png 수행결과 넣어두었습니다!

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

-쿼리
-- PK 추가
ALTER TABLE covid ADD CONSTRAINT covid_pk PRIMARY KEY (id);
ALTER TABLE hospital ADD CONSTRAINT hospital_pk PRIMARY KEY (id);
ALTER TABLE programmer ADD CONSTRAINT programmer_pk PRIMARY KEY (id);
ALTER TABLE member ADD CONSTRAINT member_pk PRIMARY KEY (id);


-- Coding as a Hobby 와 같은 결과를 반환하세요.
SELECT
programmer.hobby,
ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM programmer), 1) AS rate
FROM programmer
GROUP BY hobby
ORDER BY 2 DESC;

-- 추가된 인덱스
ALTER TABLE programmer ADD INDEX idx_id(id);
ALTER TABLE programmer ADD INDEX idx_hobby(hobby);

-- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
SELECT
covid.id,
hospital.name
FROM covid
INNER JOIN hospital ON covid.hospital_id = hospital.id
INNER JOIN programmer ON covid.programmer_id = programmer.id;

-- 추가된 인덱스
ALTER TABLE covid ADD INDEX idx_id(id);
ALTER TABLE covid ADD INDEX idx_hospital_id(hospital_id);
ALTER TABLE covid ADD INDEX idx_programmer_id(programmer_id);
ALTER TABLE hospital ADD INDEX idx_id(id);

-- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
SELECT
covid.id,
hospital.name,
programmer.hobby,
programmer.dev_type,
programmer.years_coding
FROM covid
INNER JOIN programmer ON covid.programmer_id = programmer.id
INNER JOIN hospital ON covid.hospital_id = hospital.id
WHERE programmer.hobby = 'Yes'
ANd (student LIKE 'Yes%' OR years_coding = '0-2 years');


-- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
SELECT
covid.stay,
count(covid.id)
FROM covid
INNER JOIN hospital ON covid.hospital_id = hospital.id
INNER JOIN programmer ON covid.programmer_id = programmer.id
INNER JOIN member ON covid.member_id = member.id
WHERE 1=1
AND programmer.country = 'India'
AND hospital.name = '서울대병원'
AND member.age BETWEEN 20 AND 29
GROUP BY stay;

ALTER TABLE member ADD INDEX idx_id(id);
ALTER TABLE member ADD INDEX idx_age(age);
ALTER TABLE covid ADD INDEX idx_member_id(member_id);
ALTER TABLE programmer ADD INDEX idx_country(country);
ALTER TABLE hospital ADD INDEX idx_name(name);
ALTER TABLE covid ADD INDEX idx_hospital_id_programmer_id(hospital_id, programmer_id);

-- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
SELECT
programmer.exercise,
count(covid.id)
FROM covid
INNER JOIN hospital ON covid.hospital_id = hospital.id
INNER JOIN programmer ON covid.programmer_id = programmer.id
INNER JOIN member ON covid.member_id = member.id
WHERE 1=1
AND hospital.name = '서울대병원'
AND member.age BETWEEN 30 AND 39
GROUP BY programmer.exercise;

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
