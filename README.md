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
- images/step1 디렉토리를 참고해주세요
  - before : 개선 작업 전 최초 데이터
  - after1 : nginx proxy 적용 후
  - after2 : 압축, js, css 최적화, redis 적용 후

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   1) nginx Reverse Proxy 개선 - gzip 압축, cache, TLS, HTTP/2 설정
   2) spring boot - response 압축 적용
   3) index.html - js, css 최적화 적용
   4) redis 캐시 - 노선 조회, 최단 경로 조회

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
  + [링크](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0dd7ae9f6c5d7501c)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
```sh
$ stress -c 2
```
  + images/step2 디렉토리를 참고해주세요

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
  + images/step2 디렉토리를 참고해주세요
  + Load test -> http_req_duration avg 7.64 -> 3.49로 3.15ms 감소
  + Stress test -> http_req_duration avg 306.66 -> 43.27로 263.39ms 감소 및 실패율 0%

### 미션 요구사항
- [x] 미션1: 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
- [x] 미션2: 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
- [x] 미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?
  - `no-store`만으로 캐시가 무효화 될 수 있지만 HTTP 스펙상 다양한 이슈에 대응하기 위해 `cache-control: no-cache, no-store, must-revalidate` 사용을 더 권장한다고 합니다. 
  - 그러므로 결론은 가능하다. 입니다
  - [참고](https://stackoverflow.com/questions/49547/how-do-we-control-web-page-caching-across-all-browsers)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
select s.id                 as '사원번호',
       s.last_name          as '이름',
       s.annual_income      as '연봉',
       s.position_name      as '직급명',
       record.region        as '지역',
       record.record_symbol as '입출입구분',
       record.time          as '입출입시간'
from record
inner join (
    select hr.id, hr.last_name, salary.annual_income, hr.position_name
    from salary
    inner join (
        select e.id, e.last_name, p.position_name
        from department d
        inner join manager m on d.id = m.department_id and LOWER(d.note) = 'active' and m.end_date = '9999-01-01'
        inner join employee e on m.employee_id = e.id
        inner join position p on e.id = p.id and p.position_name = 'Manager') hr
    on salary.id = hr.id and salary.end_date = '9999-01-01'
    order by salary.annual_income desc limit 5) s
on record.employee_id = s.id and record.record_symbol = 'O'; 
```

---

### 4단계 - 인덱스 설계
0. 인덱스 설계 결과
```sql
-- 1. covid
PRIMARY KEY - id,
UNIQUE KEY - id,
INDEX - hospital_id, member_id,
INDEX2 - programmer_id

-- 2. hopital
PRIMARY KEY - id,
UNIQUE KEY - id, name
INDEX - name

-- 3. member
PRIMARY KEY - id,
UNIQUE KEY - id

-- 4. programmer
PRIMARY KEY - id,
UNIQUE KEY - id,
INDEX - hobby
```

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
#### Q1. Coding as a Hobby 와 같은 결과를 반환하세요.
- 개선 결과 : 0.3s
```sql
select hobby, concat(round(count(hobby) * 100.0 / (select count(hobby) from programmer), 1), '%') as percentage
from programmer
group by hobby
order by hobby desc;
```

#### Q2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
- 개선 결과 : 0.03s
```sql
select covid.id, hospital.name 
from covid
inner join hospital on covid.hospital_id = hospital.id
inner join programmer on covid.programmer_id = programmer.id;
```

#### Q3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
- 개선 결과 : 0.03s
```sql
select covid.id, hospital.name, user.hobby, user.dev_type, user.years_coding
from covid
inner join hospital on covid.hospital_id = hospital.id
inner join (
    select id, hobby, dev_type, years_coding
    from programmer
    where (hobby = 'Yes' and student like 'Yes%') or years_coding = '0-2 years'
) as user
on covid.programmer_id = user.id
order by user.id;
```

#### Q4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
- 개선 결과 : 0.3s
```sql
select stay, count(member.id) as count
from (select id from member where age between 20 and 29) as member
inner join covid on covid.id = member.id 
inner join (select id from programmer where country = 'India') as programmer on member.id = programmer.id
inner join (select id from hospital where name = '서울대병원') as hospital on covid.hospital_id = hospital.id
group by stay;
```

#### Q5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
- 개선 결과 : 0.2s
```sql
select exercise, count(member.id)as count
from (select id from member where age between 30 and 39) as member
inner join covid on covid.id = member.id
inner join programmer on covid.id = programmer.id
inner join (select id from hospital where name = '서울대병원') as hospital on covid.hospital_id = hospital.id
group by exercise;
```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
