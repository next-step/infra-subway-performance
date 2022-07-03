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

대상 사이트 : june2-nextstep.kro.kr

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

- 성능 개선 미적용
  - [smoke](https://github.com/june2/infra-subway-performance/blob/step1/docs/smoke/1.png)
  - [load](https://github.com/june2/infra-subway-performance/blob/step1/docs/load/1.png)
  - [stress](https://github.com/june2/infra-subway-performance/blob/step1/docs/stress/1.png)
- web proxy 개선
  - [smoke](https://github.com/june2/infra-subway-performance/blob/step1/docs/smoke/2.web.png)
  - [load](https://github.com/june2/infra-subway-performance/blob/step1/docs/load/2.web.png)
  - [stress](https://github.com/june2/infra-subway-performance/blob/step1/docs/stress/2.web.png)
- web + was 개선
  - [smoke](https://github.com/june2/infra-subway-performance/blob/step1/docs/smoke/3.web.was.png)
  - [load](https://github.com/june2/infra-subway-performance/blob/step1/docs/load/3.web.was.png)
  - [stress](https://github.com/june2/infra-subway-performance/blob/step1/docs/stress/3.web.was.png)


3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

   a. Reverse Proxy 개선
     - gzip 압축, cache, TLS, HTTP/2 설정

   b. WAS 성능 개선
     - Redis Spring Data Cache

 - 결과
   - web(nginx) 성능 개선으로 웹페이지 로딩 속도 감축
   - was(redis-cache) 데이터 응답 실패율 감소
  |         | FCP  |  TTI |  SI  |  TBT  |  LCP   |   CLS   |   Score  |
  |---------|------|------|------|-------|--------| ------- |  :-----: |
  | 미적용    |2.7s  | 2.8  | 2.7s | 70ms  |  2.8s  |  0.004  |    67    |
  | web개선  |1.2s  | 1.3  | 1.6s | 50ms  |  1.3s  |  0.004  |    92    |
  | was개선  |1.2s  | 1.2  | 1.7s | 50ms  |  1.2s  |  0.004  |    92    |
     

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-098c4a0f25c3dbf85

3. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
- [인스턴스 현황](https://github.com/june2/infra-subway-performance/blob/step2/docs/ASG/cloudwatch1.png)
- [cpu 사용률](https://github.com/june2/infra-subway-performance/blob/step2/docs/ASG/cloudwatch2.png)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- [smoke](https://github.com/june2/infra-subway-performance/blob/step2/docs/ASG/smoke.png)
- [load](https://github.com/june2/infra-subway-performance/blob/step2/docs/ASG/load.png)
- [stress](https://github.com/june2/infra-subway-performance/blob/step2/docs/ASG/stress.png)
- [stress 2배](https://github.com/june2/infra-subway-performance/blob/step2/docs/ASG/stress-2.png)
- [stress 4배](https://github.com/june2/infra-subway-performance/blob/step2/docs/ASG/stress-3.png)

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
select m.id as '사원번호', m.last_name as '이름', m.annual_income as '연봉', m.position_name as '직급명', r.time as '입출입시간', r.region as '지역', r.record_symbol as '입출입구분' 
from record r
inner join (
    select m.id, m.last_name, s.annual_income, m.position_name
    from salary s 
    inner join (
        select e.id, e.last_name, p.position_name
        from department d 
        inner join manager m on d.id = m.department_id and d.note = 'active'
        inner join employee e on e.id = m.employee_id and m.end_date = '9999-01-01'
        inner join position p on e.id = p.id and p.position_name = 'manager'
    ) m 
    on s.id = m.id and s.end_date = '9999-01-01'
    order by s.annual_income desc
    limit 5
) m
on r.employee_id = m.id and r.record_symbol = 'O';
```
---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- Coding as a Hobby 와 같은 결과를 반환하세요.
```sql
alter table programmer add primary key (id);
create index idx_programmer_hobby on programmer (hobby);

select hobby, round(count(*) / (select count(*) from programmer) * 100, 1) a
from programmer
group by hobby;
```

- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```sql
alter table programmer add primary key (id);
alter table hospital add primary key (id);
alter table covid add primary key (id);

create unique index idx_hospital_name on hospital (name)
create index idx_covid_programmer_id on covid (programmer_id);
create index idx_covid_hospital_id on covid (hospital_id);

select c.id, h.name from covid c
inner join programmer p on c.programmer_id = p.id
inner join hospital h on c.hospital_id = h.id;
```

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```sql
select p.id, p.hobby, p.dev_type, p.years_coding from programmer p
inner join covid c on c.programmer_id = p.id
inner join hospital h on c.hospital_id = h.id
where (p.hobby = 'yes' and p.student like 'Yes%') or (p.years_coding = '0-2 years')
order by p.id;
```

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
````sql
alter table member add primary key (id);
create index idx_covid_member_id on covid (member_id);

select c.stay, count(c.stay) from hospital h
inner join covid c on c.hospital_id = h.id and h.name = '서울대병원'
inner join member m on c.member_id = m.id and m.age between 20 and 29
inner join programmer p on c.programmer_id = p.id and p.country = 'India'
group by c.stay
order by c.stay;
````

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
````sql
select p.exercise, count(p.exercise) from hospital h
inner join covid c on c.hospital_id = h.id and h.name = '서울대병원'
inner join member m on c.member_id = m.id and m.age between 30 and 39
inner join programmer p on c.programmer_id = p.id 
group by p.exercise
order by exercise;
````
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
