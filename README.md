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
웹사이트 성능 비교
- 전:
![img.png](img.png)

- 후:
![img_1.png](img_1.png)

smoke 비교
![img_4.png](img_4.png)
load 비교 
![img_3.png](img_3.png)
stress 비교
![img_2.png](img_2.png)
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

    2-1. reverse proxy 를 개선했습니다.
   - 리소스를 압축(gzip)하여 제공
   - 리소스 브라우저 캐싱 적용

    2-2. was 내 조회 로직에 redis 캐시를 적용했습니다.
   - 노선, 노선 목록, 회원 정보

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
  
`https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0fc72886af9bd913d`

3. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 4
```
![img_5.png](img_5.png)

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- smoke
![img_6.png](img_6.png)
- load
![img_7.png](img_7.png)
- stress
![img_8.png](img_8.png)
---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```
select 사원번호, 이름, 연봉, 직급명, r.time as 입출입시간, region as 지역, r.record_symbol as 입출입구분
   from record r,
   (select employee.id as 사원번호, employee.last_name as 이름, salary.annual_income as 연봉, position.position_name as 직급명 
   from position, manager, employee, salary, employee_department
   where
   position.id = manager.employee_id
   and employee.id = manager.employee_id
   and salary.id = manager.employee_id
   and manager.employee_id = employee_department.employee_id
   and employee_department.department_id in ( select id from department where note = 'Active')
   and position.position_name = 'Manager'
   and position.start_date = manager.start_date
   and position.end_date = manager.end_date
   and manager.end_date = '9999-01-01'
   and salary.end_date = '9999-01-01'
   and employee_department.end_date = '9999-01-01'
   order by salary.annual_income desc limit 5) as a
   where r.employee_id = a.사원번호
   and r.record_symbol = 'O';
```
`0.229 sec / 0.000031 sec`

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요


각 테이블 내 인덱스, PK 추가함.
```
programmer
  PRIMARY KEY (`id`),
  KEY `idx_programmer_hobby` (`hobby`),
  KEY `idx_programmer_member_id` (`member_id`)
  
member
   PRIMARY KEY (`id`)

hospital
   PRIMARY KEY (`id`)
   
covid
   PRIMARY KEY (`id`),
   KEY `idx_covid_hospital_id` (`hospital_id`)        

```

1.1

`0.034 sec / 0.000010 sec`

```
select Truncate((count(hobby) * 100) / (select count(*) from programmer), 1) as total, hobby 
from programmer 
group by hobby;
```

1.2

`0.023 sec / 0.016 sec`

``` sql
select c.id, h.name 
from programmer p, hospital h, covid c 
where p.id = c.programmer_id 
and h.id = c.hospital_id;
```


1.3

`0.011 sec / 0.079 sec`

``` sql
select p.student, c.id, h.name, p.hobby, p.dev_type, p.years_coding 
from programmer p, hospital h, covid c 
where p.id = c.programmer_id 
and h.id = c.hospital_id and hobby='Yes'
and ((p.years_coding = '0-2 years') or (p.student like 'Yes%'));
```

1.4

`0.097 sec / 0.000011 sec`

``` sql
select c.stay, count(c.stay) 
from programmer p, covid c, member m 
where m.id = c.member_id
and p.member_id = m.id 
and c.member_id = p.member_id
and c.hospital_id = (select id from hospital where name = '서울대병원') 
and p.country = 'India'
and m.age between 20 and 29 
group by c.stay;
```

1.5

`0.095 sec / 0.0000081 sec`

``` sql
select p.exercise, count(p.exercise) 
from programmer p, covid c, member m 
where m.id = c.member_id 
and p.member_id = m.id 
and c.member_id = p.member_id
and c.hospital_id = (select id from hospital where name = '서울대병원') 
and m.age between 30 and 39 
group by p.exercise;
```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
