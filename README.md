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

- 개선전
    - [smoke] : ./docs/step1/before/before_smoke.PNG
    - [load] : ./docs/step1/before/before_load.PNG
    - [stress] : ./docs/step1/before/before_stress.PNG

- 개선후
    - [smoke] : ./docs/step1/after/after_smoke.PNG
    - [load] : ./docs/step1/after/after_load.PNG
    - [stress] : ./docs/step1/after/after_stress.PNG

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- nginx 리버시 프록시 개선
    - gzip 압축
    - cache 사용
    - http2 적용

- application redis cache 적용

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

- https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-063fd4ece17528ff6

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

- ./docs/step2/auto-scaling/autoScaling-instance.PNG
- ./docs/step2/auto-scaling/autoScaling-monitoring.PNG


3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

- ./docs/step2/stress-v-user-360
- ./docs/step2/stress-v-user-730
- ./docs/step2/stress-v-user-920
- ./docs/step2/stress-v-user-1100

### 미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?

https://stackoverflow.com/questions/49547/how-do-we-control-web-page-caching-across-all-browsers

```
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.
```

검색해보았을때 위와 같은 설정을 통해 가능하다고 생각됩니다!
`response.setHeader("Pragma", "no-cache"); // HTTP 1.0.` 옵션을 주는 이유는  
HTTP1.0 에서의 캐시설정이고 HTTP1.1 이후 부터는 Cache-Control 을 이용해서 캐시 설정을 할 수 있는것으로 확인됩니다.

`Cache-Control: no-cache, no-store, must-revalidate` 에 no-store 만으로 사용하지 않고 저 셋을 다 사용하는 이유에 대해서는  
브라우저의 호환성, 여러 프록시 서버, 기타 버그들이 엮여있어 no-store 만으로 사용하지 않고  
여러 옵션을 같이 사용한다고 확인했습니다.

브라우저의 호환성, HTTP 버전문제 등으로 인하여 위 옵션을 모두 같이 사용하면  
모든 정적자원에 대해 no-cache, no-store 설정이 가능하다고 판단했습니다!

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역,
  입출입구분, 입출입시간)

```mysql
SELECT manager_top5_salary.employee_id   as '사원번호'
     , manager_top5_salary.name          as '이름'
     , manager_top5_salary.annual_income as '연봉'
     , manager_top5_salary.position_name as '직급명'
     , r.`time`                          as '입출입시간'
     , r.region                          as '지역'
     , r.record_symbol                   as '입출입구분'
FROM (
         select m.employee_id as 'employee_id'
              , e.last_name   as 'name'
              , s.annual_income  'annual_income'
              , p.position_name  'position_name'
         from manager m
                  inner join employee e on m.employee_id = e.id
             and m.end_date > now()
                  inner join department d on m.department_id = d.id
             and upper(d.note) = 'ACTIVE'
                  inner join `position` p on m.employee_id = p.id
             and p.end_date > now()
                  inner join salary s on m.employee_id = s.id
             and s.end_date > now()
         order by s.annual_income desc
         limit 5
     ) as manager_top5_salary
         inner join record r
                    on manager_top5_salary.employee_id = r.employee_id
                        and r.record_symbol = 'O';
```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- [X] __Coding as a Hobby 와 같은 결과를 반환하세요.__

```mysql
select hobby,
       round(count(id) / (select count(id) from programmer p) * 100, 1) as 'rate'
from programmer
group by hobby;

```
`0.047sec`

#### 추가한 인덱스
- `alter table programmer add primary key(id);`
- `alter table programmer add index idx_programmer_02(hobby);`

#### 쿼리 실행시간 / 실행계획
- ./docs/step4/1-1-time.PNG
- ./docs/step4/1-1-explain.png

<hr>

- [X] __프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)__

```mysql
SELECT c.id, h.name
FROM covid c
         inner join hospital h
                    on c.hospital_id = h.id
         inner join programmer p
                    on c.programmer_id = p.id;
```

`0.015sec`

#### 추가한 인덱스
- `alter table covid add primary key(id);`
- `alter table covid add index idx_covid_01(hospital_id);`
- `alter table covid add index idx_covid_03(programmer_id);`
- `alter table hospital add primary key(id);`

#### 쿼리 실행시간 / 실행계획
- ./docs/step4/1-2-time.PNG
- ./docs/step4/1-2-explain.png

<hr>

- [X] __로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name,
  user.Hobby, user.DevType, user.YearsCoding)__

```mysql
SELECT c.id
     , h.name
     , p.hobby
     , p.dev_type
     , p.years_coding
FROM programmer p
         inner join covid c
                    on p.id = c.programmer_id
         inner join hospital h
                    on c.hospital_id = h.id
         inner join member m
                    on p.member_id = m.id
WHERE 1 = 1
  and p.hobby = 'YES'
  and ((p.years_coding = '0-2 years') or (p.student like 'Yes%'))
;
```
`0.015sec`

#### 추가한 인덱스
- `alter table member add primary key(id);`
- `alter table programmer add index idx_programmer_01(member_id);`

#### 쿼리 실행시간 / 실행계획
- ./docs/step4/1-3-time.PNG
- ./docs/step4/1-3-explain.png

<hr>

- [X] __서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)__

```mysql
SELECT c.stay, count(1)
from programmer p
         inner join covid c
                    on c.programmer_id = p.id
         inner join member m
                    on c.member_id = m.id
         inner join hospital h
                    on c.hospital_id = h.id
WHERE 1 = 1
  and h.name = '서울대병원'
  and p.country = 'India'
  and m.age BETWEEN 20 and 29
group by c.stay;
```
`0.032sec`

#### 추가한 인덱스
- `alter table covid add index idx_covid_02(member_id);`

#### 쿼리 실행시간 / 실행계획
- ./docs/step4/1-4-time.PNG
- ./docs/step4/1-4-explain.png

<hr>

- [X] __서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)__

```mysql
SELECT p.exercise, count(p.id)
FROM programmer p
         inner join covid c
                    on c.programmer_id = p.id
         inner join member m
                    on c.member_id = m.id
         inner join hospital h
                    on c.hospital_id = h.id
where 1 = 1
  and h.name = '서울대병원'
  and m.age BETWEEN 30 and 39
group by exercise;
```

`0.031sec`

이전 단계에서 필요한 인덱스가 이미 추가되었음

#### 쿼리 실행시간 / 실행계획
- ./docs/step4/1-5-time.PNG
- ./docs/step4/1-5-explain.png

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
