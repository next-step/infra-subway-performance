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
* [smoke 전](/k6/smoke/before)
* [smoke 후](/k6/smoke/after)
* [load 전](/k6/load/before)
* [load 후](/k6/load/after)
* [stress 전](/k6/stress/before)
* [stress 후](/k6/stress/after)
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
*  gzip 압축, cache, TLS, HTTP/2 설정을 적용을 통해서 Reverse Proxy를 개선했습니다.
*  Redis, Spring Data Cache 사용을 통해서 WAS 성능 개선했습니다.
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
* [Launch Template](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0f6f887586835953a)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
* [Cloudwatch](/step2/cloudwatch)
```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* [smoke](/step2/smoke)
* [load](/step2/load)
* [stress](/step2/stress)
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```
select 
    t.id as '사원번호', 
    t.last_name as '이름', 
    t.annual_income as '연봉', 
    t.position_name as '직급',
    r.time as '입출입시간',
    r.region as '지역', 
    r.record_symbol as '입출입구분' 
from 
    record r
inner join (
    select 
        e.id, e.last_name, s.annual_income, p.position_name
	from 
	    employee e
    inner join position p on p.id = e.id and p.position_name = 'manager' and p.start_date <= now() and p.end_date >= now()
	inner join manager m on m.employee_id = e.id and m.start_date <= now() and m.end_date >= now()
	inner join department d on d.id = m.department_id and d.note = 'active'
	inner join salary s on s.id = e.id and s.start_date <= now() and s.end_date >= now()
	where 
	    e.join_date <= now()
	order by 
	    s.annual_income desc limit 5
) t 
on 
    t.id = r.employee_id and r.record_symbol = 'o';
```
* [결과](/step3)
---

### 4단계 - 인덱스 설계

#### 요구사항
* 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
  * M1의 경우엔 시간 제약사항을 달성하기 어렵워서 2배를 기준으로 해본 후 일단 리뷰요청

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 
와 같은 결과를 반환
  * 아래의 쿼리를 작성 후 313ms가 나온 것을 확인 후 hobby에 인덱스 생성 쿼리 적용한 뒤에 062ms 감소하였습니다.
  * [결과 이미지](/step4/1)
```
-- 쿼리
select hobby, round(count(*) / (select count(*) from programmer p) * 100, 1) AS percent
from programmer
group by hobby;

-- 인덱스 생성 쿼리
create index programmer_hobby_index on programmer (hobby);
```
* 프로그래머별로 해당하는 병원 이름을 반환 (covid.id, hospital.name)
  * 아래의 쿼리를 작성 후 360ms가 나온 것을 확인했고 조인 컬럼인 각 테이블의 id에 인덱스 생성 쿼리를 적용한 뒤에
  031ms로 감소했습니다.
  * [결과 이미지](/step4/2)
```
-- 쿼리
select c.id, h.name from hospital h 
inner join covid c on c.hospital_id = h.id
inner join programmer p on p.id= c.programmer_id;

-- 인덱스 생성 쿼리
create index covid_hospital_id_index
     on covid (hospital_id);
     
create index covid_programmer_id_index
     on covid (programmer_id);
```
* 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬
  (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
  * 아래의 쿼리를 작성 후 1188ms의 속도를 확인했으며 제가 작성한 쿼리를 토대로 id에서 조인이 가장 많이 일어나는 사실로
  현재 쿼리에서 사용되는 각 테이블의 id컬럼에 primary key를 지정해서 인덱스를 사용하도록 변경했습니다. 변경 후 속도는
  015ms로 측정되었습니다.
  * [결과 이미지](/step4/3)
```
-- 쿼리
select c.id, h.name, p.hobby, p.dev_type, p.Years_coding
from covid c
    inner join hospital h on c.hospital_id = h.id
    inner join
        (
            select p.id, p.hobby, p.dev_type, p.Years_coding
            from programmer p
            where p.hobby = 'Yes'
              and (p.student like 'Yes%' or p.years_coding = '0-2 years')
        ) p on c.programmer_id = p.id
order by p.id;

-- primary key 지정
alter table programmer add primary key (id);
alter table covid add primary key (id);
alter table hospital add primary key (id);

```
* 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계 (covid.Stay)
  * 아래의 쿼리를 작성 후 실행되는 쿼리의 속도는 1000ms가 측정되었습니다. 측정 후 위 요구사항들은 진행하면서
  추가되지 않은 member 테이블에 pk를 적용 후 측정했습니다. pk만 적용 후 인덱스를 통해서 078ms로 요구사항에 부합했지만
  현재 쿼리에서 where절에 사용되는 컬럼을 복합 인덱스 및 인덱스를 구성해 조금 더 줄였습니다. 인덱스를 적용 후에는
  063ms으로 줄었지만 미미한 차이로 데이터가 더 많아진다면 차이가 벌어진다고 생각합니다.
  * [결과 이미지](/step4/4)
```
-- 쿼리
select c.stay, count(*)
from covid c
inner join hospital h on c.hospital_id = h.id
inner join programmer p on c.programmer_id = p.id
inner join member m on c.member_id = m.id
where
    m.age between 20 and 29
    and h.name = '서울대병원'
    and p.country = 'India'
group by c.stay;

-- primary key 지정
ALTER TABLE member ADD PRIMARY KEY (id);

create index member_age_id_index
	on member (age, id);
	
-- 해당 인덱스는 위 요구사항 중 적용된 사항
create index covid_hospital_id_index
     on covid (hospital_id);
     
create index hospital_name_index
    on hospital (name);
```
* 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계 (user.Exercise)
  * 이번 쿼리는 바로 078ms로 이번 미션의 요구사항을 충족시켰는데 해당 이유는 4번째 사항에 추가된 pk값과 인덱스를 같이
  사용되서 무리 없이 요구사항이 충족되는 쿼리였습니다.
  * [결과 이미지](/step4/5)
```
-- 쿼리
select p.exercise, count(*)
from hospital h
inner join covid c on c.hospital_id = h.id
inner join programmer p on c.programmer_id = p.id
inner join member m on m.id = c.member_id
where
   m.age between 30 and 39
   and h.name = '서울대병원'
group by p.exercise;
```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
* https://anwjrrp33-webservice.kro.kr/lines/page?id=1&page=1&size=10
* [결과 이미지](/step5)
