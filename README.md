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

#### 요구사항

- 저장소를 활용하여 아래 요구사항을 해결합니다.
- README 에 있는 질문에 답을 추가한 후 PR을 보내고 리뷰요청을 합니다.
- 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선
  - 개선 전 / 후를 직접 계측하여 확인

#### 힌트

1. Reverse Proxy 개선하기
2. WAS 성능 개선하기
3. Scale out - 초간단 Blue-Green 배포 구성하기
4. 정적 파일 경량화

### 1단계 - 화면 응답 개선하기
1. 시나리오의 요청 목표값
- 서비스 진단하기 미션 때, 경쟁사중 제일 빠른 카카오맵 api 응답속도를 통해 **120ms**를 목표값으로 설정헀습니다.

2. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- k6 폴더에 결과 첨부했습니다.

3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- 1차 튜닝에선 gzip 압축, cache, HTTP2 적용
- 2차 튜닝에선 Spring Data Cache를 적용했습니다.

---

### 2단계 - 스케일 아웃

#### 요구사항
- [x] springboot에 HTTP Cache, gzip 설정하기
- [x] Launch Template 작성하기
- [x] Auto Scaling Group 생성하기
- [x] Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-04acaa9c3bdc9c5e5

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
- k6/auto-scaling 폴더에 넣었습니다.

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- k6 하위에 추가했습니다

---

### 3단계 - 쿼리 최적화

#### 미션

- 실습환경 세팅

```
$ docker run -d -p 23306:3306 brainbackdoor/data-tuning:0.0.3
```  
workbench를 설치한 후 localhost:23306 (ID : user, PW : password) 로 접속합니다.

#### 요구사항

- 활동중인(Active) 부서의 현재 부서관리자(manager) 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실(O)했는지 조회해보세요.
(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
- 인덱스 설정을 추가하지 않고 200ms 이하로 반환합니다.
  - M1의 경우엔 시간 제약사항을 달성하기 어렵습니다. 2s를 기준으로 해보시고 어렵다면, 일단 리뷰요청 부탁드려요
- 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- SQL 쿼리
```
select 
  t.id as '사원번호', 
  t.name as '이름', 
  t.income as '연봉', 
  t.position_name as '직급명', 
  record.time as '입출입시간', 
  record.region as '지역', 
  record.record_symbol as '입출입구분' 
from 
(
  select employee.id as id, employee.last_name as name, salary.annual_income as income, position.position_name as position_name
  from department
  inner join manager on manager.department_id = department.id
  inner join salary on salary.id = manager.employee_id
  inner join employee on employee.id = manager.employee_id
  inner join position on position.id = employee.id
  where note = 'active' and manager.end_date = '9999-01-01' and salary.end_date = '9999-01-01' and position.end_date = '9999-01-01'
  order by salary.annual_income desc limit 5 
) t
inner join record on record.employee_id = t.id
where record.record_symbol = 'O';
```

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

#### 요구사항
- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
- M1의 경우엔 시간 제약사항을 달성하기 어렵습니다. 2배를 기준으로 해보시고 어렵다면, 일단 리뷰요청 부탁드려요

#### 진행 목록
- [x] Coding as a Hobby 와 같은 결과를 반환하세요.
- [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
- [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
- [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
- [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

1.1 **Coding as a Hobby 와 같은 결과를 반환하세요.**
- 우선 아래와 같은 쿼리를 작성하고 성능을 확인하니 0.5s가 나왔습니다.
```sql
select hobby, round((count(1) / (select count(id) from programmer) * 100), 2) as is_coding
from programmer
group by hobby;
```
- 인덱스를 확인해보니 programmer 테이블에는 인덱스가 없어서 id를 PK로 설정하고 hobby를 인덱스로 설정한 후에 원하는 성능을 얻을수 있었습니다.
```sql
ALTER TABLE programmer ADD PRIMARY KEY (id);
CREATE INDEX idx_programmer_hobby ON programmer(hobby);
```

1.2 **프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)**
- 아래와 같은 쿼리를 작성하고 성능을 확인하니 문제가 없어 추가 인덱스를 생성하진 않았습니다. (0.016s)
```sql
select c.id, h.name from hospital h 
inner join covid c on c.hospital_id = h.id
inner join programmer p on p.id= c.programmer_id;
```

1.3 **프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)**
- 아래와 같은 쿼리를 작성 후 실행하니 1.2s의 결과를 얻었습니다.
```sql
select c.id, h.name, p.hobby, p.dev_type, p.years_coding
from programmer p
inner join covid c on c.programmer_id = p.id
inner join hospital h on h.id = c.hospital_id
where (p.hobby = 'Yes' and p.student like 'Yes%') or p.years_coding = '0-2 years'
order by p.id;
```
- covid, hospital 테이블 역시 id가 PK로 지정되어 있지 않아 추가하고 확인해보니 0.9s 성능을 얻었습니다.
```sql
ALTER TABLE covid ADD PRIMARY KEY (id);
ALTER TABLE hospital ADD PRIMARY KEY (id);
```
- 조건절이 되는 student, years_coding는 카디널리티가 작아 인덱스에서 제외했고 살펴보니 join 절에 covid의 programmer_id, hospital_id
에 인덱스를 걸면 어떻게 될지 확인해봤습니다. 테이블마다 인덱스는 1개만 타기 때문에 복합 인덱스로 생성했고 그 후, 성능 요구사항을 만족시킬 수 있었습니다.
```sql
CREATE INDEX idx_covid_programmer_id_and_hospital_id ON covid(programmer_id, hospital_id);
```

1.4 **서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)**
- 아래와 같은 쿼리를 작성 후 실행하니 1.2s의 결과를 얻었습니다.
```sql
select c.stay, count(1)
from hospital h
inner join covid c on c.hospital_id = h.id
inner join programmer p on p.id= c.programmer_id
inner join member m on m.id = c.member_id
where h.name = '서울대병원' and p.country = 'India' and m.age between 20 and 29
group by c.stay;
```

- member 테이블에 id를 PK로 설정후 0.18s의 성능을 얻었습니다.
```sql
ALTER TABLE member ADD PRIMARY KEY (id);
```

- 100ms 이하의 성능을 만족시켜야 함으로 더 개선해야 합니다. 우선 조건절의 country, age는 카디널리티가 낮은것으로 확인했습니다.
```sql
select count(distinct country) from programmer; -- 184개
select count(distinct age) from member;         -- 43개
```

- 마찬가지로 covid의 hospital_id, programmer_id, member_id를 조인절에 사용하는 것을 확인할 수 있습니다. 따라서 아래와 같은 복합
인덱스를 생성해 성능을 개선할 수 있었습니다.
```sql
CREATE INDEX idx_covid_hospital_id_and_programmer_id_and_member_id ON covid(hospital_id, programmer_id, member_id);
```

1.5 **서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)**
- 아래 쿼리를 작성하였고 1.4와 동일한 인덱스를 타기 떄문에 성능 이슈는 없었습니다.

```sql
select p.exercise, count(1)
from hospital h
inner join covid c on c.hospital_id = h.id
inner join programmer p on p.id= c.programmer_id
inner join member m on m.id = c.member_id
where h.name = '서울대병원' and m.age between 30 and 39
group by p.exercise;
```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
