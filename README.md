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
- [ ] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
- [ ] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
- [ ] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
- [ ] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

1.1 Coding as a Hobby 와 같은 결과를 반환하세요.
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
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
