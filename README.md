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
- test_result 디렉토리 하위에 성능 개선 전후의 k6, grafana 결과 첨부하였습니다.

3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- reverse proxy : gzip, cache 적용, TLS, HTTP/2 설정
- was : redis cache 적용
- URL : https://cylee9409-subway.o-r.kr/

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- 배포 스크립트 : https://s3.console.aws.amazon.com/s3/object/nextstep-camp-pro?region=ap-northeast-2&prefix=cylee9409_deploy.sh
- Launch Template : https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-021bb0785927bc81b

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
- test_result_auto-scaling 디렉토리에 CloudWatch 캡쳐 추가했습니다.

시나리오에 따른 vUser
- 평소 vUser : 65
- 최대 vUSer : 75
- auto-scaling 적용 전 에러 없이 수행되는 vUser 수 (stress.js 기준) : 75
- auto-scaling 적용 후 에러 없이 수행되는 vUser 수 (stress.js 기준) : 200

기존 stress.js 부하테스트에서는 기존 웹 성능 예상에서 산출한 최대 vUser 99명을 적용한 경우, 
request block 이 여러 차례 발생하여 에러가 발생하지 않는 최대 치인 75 vUser 기준으로 테스트를 수행하였습니다.
하지만 auto-scaling 적용 후 instance 를 평소 2개 최대 4개로 설정한 기준으로 수행하였을 때 vUser 200 까지 에러없이 수행 가능해졌습니다.

- auto-scaling 적용 전
```sh


export let options = {

        stages: [

                { duration: '1m' , target: 75 },
                { duration: '5m' , target: 75 },
                { duration: '10m', target: 75 },
                { duration: '1m' , target: 0  }
        ],

        thresholds: {
                      http_req_duration: ['p(99)<500'],
                    },
};


```

- auto-scaling 적용 후
```sh


export let options = {

        stages: [

                { duration: '1m' , target: 99 },
                { duration: '5m' , target: 99 },
                { duration: '10m', target: 99 },
                { duration: '3m' , target: 150},
                { duration: '3m' , target: 200},
                { duration: '1m' , target: 0  }
        ],

        thresholds: {
                      http_req_duration: ['p(99)<500'],
                    },
};


```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- test_result_auto-scaling 디렉토리에 결과 추가했습니다.
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

select high_income_employee.employee_id 사원번호,  
high_income_employee.last_name 이름,  
high_income_employee.annual_income 연봉,  
high_income_employee.position_name 직급명,  
record.time 입출입시간,  
record.region 지역,  
record.record_symbol 입출입구분  
from (  
select employee_department.employee_id, employee.last_name, salary.annual_income, position.position_name  
from employee_department  
inner join department on employee_department.department_id = department.id  
inner join manager on employee_department.employee_id = manager.employee_id  
inner join position on manager.employee_id = position.id  
inner join salary on employee_department.employee_id = salary.id  
inner join employee on employee_department.employee_id = employee.id  
where employee_department.start_date < now()  
and employee_department.end_date > now()  
and upper(department.note) = 'ACTIVE'  
and position.position_name = 'Manager'  
and position.end_date > now()  
and position.end_date > now()  
and salary.start_date < now()  
and salary.end_date > now()  
order by salary.annual_income desc limit 5  
) high_income_employee  
inner join record on high_income_employee.employee_id = record.employee_id  
where record.record_symbol = 'O'  
;

- DB 결과는 test_result_query 에 넣어 놓았습니다.
---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
   - 1번 쿼리  


    select hobby, round(count(*) / (select count(*) from programmer) * 100, 1) as proportion
      from programmer  
    group by hobby  
    order by 2 desc  
    ;  


- 2번 쿼리  
mysql로 ERD 를 살펴보니 pk 및 fk 등록이 하나도 되어 있지 않아 테이블별 pk, fk 등록하여 관계 설정을 했습니다.  
pk와 fk를 등록하니 테이블별로 pk, fk 인덱스가 자동으로 생성되어 별도의 인덱스 생성 없이도 빠른 성능으로 쿼리 결과 조회가 가능했습니다.  
-- to do  
-- covid 에 pk 및 fk 생성  
-- hospital 에 pk 및 fk 생성  
-- programmer 에 pk 및 fk 생성  
alter table member add primary key(id);  
alter table covid add primary key(id);  
alter table covid add foreign key(hospital_id) references hospital(id);  
alter table covid add foreign key(member_id) references member(id);  
alter table covid add foreign key(programmer_id) references programmer(id);  
alter table hospital add primary key(id);  
alter table programmer add primary key(id);  
alter table programmer add foreign key( member_id ) references member(id);


    select covid.id, hospital.name
      from covid  
    inner join hospital on covid.hospital_id = hospital.id  
    inner join programmer on covid.programmer_id = programmer.id  
    order by covid.id  
    ;  

- 3번 쿼리  
이번 쿼리에서는 조회 조건에 따라 복합 인덱스와 단일 컬럼 인덱스를 추가했습니다.  
찾아보니 String 에 대한 like 조건이어도 '찾을문자열%' 처럼 % 가 뒤에 붙는 경우에는 컬럼 인덱스를 탄다는 점을 확인해서 스트링 like 조건이지만 인덱스 추가했습니다.  
-- to-do  
-- programmer 에 student 와 years_coding 조합으로 인덱스 추가  
ALTER TABLE programmer ADD INDEX `ix_student_and_years_coding` (student, years_coding);  
-- programmer 에 hobby 인덱스 추가  
ALTER TABLE programmer ADD INDEX `ix_hobby` (hobby);


     select covid.id, hospital.name, programmer.hobby, programmer.dev_type, programmer.years_coding
       from programmer  
     inner join covid on programmer.id = covid.id  
     inner join hospital on covid.hospital_id = hospital.id  
     where hobby = 'Yes'  
       and (student like 'Yes%' or years_coding = '0-2 years')  
     order by programmer.id  
    ; 

- 4번 쿼리  
-- to-do  
-- hospital name 에 인덱스 추가  
ALTER TABLE hospital ADD INDEX `ix_name` (name);  
-- member age 에 인덱스 추가  
ALTER TABLE member ADD INDEX `ix_age` (age);  


      select covid.stay, count(programmer.id)  
        from hospital  
      inner join covid on hospital.id = covid.hospital_id  
      inner join programmer on covid.id = programmer.id  
      inner join member on member.id = programmer.id  
      where hospital.name = '서울대병원'  
        and programmer.country = 'India'  
        and member.age between 20 and 29  
      group by stay  
      order by 1 desc  
      ;


- 5번 쿼리

    
    select programmer.exercise, count(programmer.id)  
      from hospital  
    inner join covid on hospital.id = covid.hospital_id  
    inner join programmer on covid.id = programmer.id  
    inner join member on member.id = programmer.id  
    where hospital.name = '서울대병원'  
      and member.age between 30 and 39  
    group by programmer.exercise  
    order by 2 desc  
    ;

각 쿼리에 대한 실행 결과 및 plan 에 대한 캡쳐는 test_result_query 디렉토리 하위에 옮겨두었습니다.

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
