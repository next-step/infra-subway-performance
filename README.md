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

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
#### 1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
##### A. 쿼리최적화
- 쿼리 작성만으로 1s 이하로 반환한다.
- 인덱스 설정을 추가하여 50 ms 이하로 반환한다.

###### [x] 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```sql
select
    상위연봉관리자.사원번호, 
    상위연봉관리자.이름, 
    상위연봉관리자.연봉, 
    상위연봉관리자.직급명, 
    사원출입기록.지역, 
    사원출입기록.입출입구분, 
    max(사원출입기록.입출입시간) 입출입시간
from (
    select
        사원.사원번호, 
        사원.이름, 
        현재급여.연봉, 
        현재직급.직급명
    from (select 부서번호 from 부서 where 비고 = 'active') 활동중인부서
    inner join (select 부서번호, 사원번호 from 부서관리자 where 종료일자 = date('9999-01-01')) 현재부서관리자
    on 활동중인부서.부서번호 = 현재부서관리자.부서번호
    inner join 사원
    on 현재부서관리자.사원번호 = 사원.사원번호
    inner join (select 사원번호, 직급명 from 직급 where 종료일자 = date('9999-01-01')) 현재직급
    on 사원.사원번호 = 현재직급.사원번호
    inner join (select 사원번호, 연봉 from 급여 where 종료일자 = date('9999-01-01')) 현재급여
    on 사원.사원번호 = 현재급여.사원번호
    order by 현재급여.연봉 desc
    limit 5
) 상위연봉관리자
inner join 사원출입기록
on 상위연봉관리자.사원번호 = 사원출입기록.사원번호
where 사원출입기록.입출입구분 = 'O'
group by 상위연봉관리자.사원번호, 상위연봉관리자.이름, 상위연봉관리자.연봉, 상위연봉관리자.직급명, 사원출입기록.지역, 사원출입기록.입출입구분

-- 인덱스 추가 전 360ms
```

```sql
CREATE INDEX I_비고 ON 부서 (비고);
CREATE INDEX I_종료일자 ON 부서관리자 (종료일자);
CREATE INDEX I_종료일자 ON 직급 (종료일자);
CREATE INDEX I_종료일자 ON 급여 (종료일자);
CREATE INDEX I_입출입구분 ON 사원출입기록 (입출입구분);
CREATE INDEX I_사원번호 ON 사원출입기록 (사원번호);

-- 인덱스 추가 후 0.9ms
```

##### B. 인덱스 설계
- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환

```sql
alter table programmer add primary key(id);
alter table hospital add primary key(id);
alter table covid add primary key(id);
CREATE INDEX I_hobby ON programmer (hobby);
CREATE INDEX I_programmer_id ON covid (programmer_id);
CREATE INDEX I_hospital_id ON covid (hospital_id);
```

###### [x] Coding as a Hobby 와 같은 결과를 반환하세요.

```sql
select
    hobby,
    round(count(*) * 100 / (select count(*) from programmer), 1)
from programmer
group by hobby

-- 62ms
```

###### [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

```sql
select
    programmer.id,
    covid.id,
    hospital.name
from hospital
inner join covid
on hospital.id = covid.hospital_id
inner join programmer
on covid.programmer_id = programmer.id

-- 15ms
```

###### [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

```sql
select
    covid.id, 
    hospital.name, 
    programmer.hobby, 
    programmer.dev_type, 
    programmer.years_coding
from programmer
inner join covid
on programmer.id = covid.programmer_id
inner join hospital
on covid.hospital_id = hospital.id
where hobby = 'yes' and (student like 'yes%' or years_coding = '0-2 years')
order by programmer.id

-- 16ms
```

###### [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

```sql
alter table hospital modify name varchar(255);
CREATE INDEX I_name ON hospital (name);
CREATE INDEX I_country ON programmer (country);
CREATE INDEX I_member_id ON covid (member_id);

select
    covid.stay, 
    count(*)
from (select id from programmer where country = 'india') indiaProgrammer
inner join covid
on indiaProgrammer.id = covid.programmer_id
inner join (select id from hospital where name = '서울대병원') seoulHospital
on covid.hospital_id = seoulHospital.id
inner join (select id from member where age between 20 and 29) member
on covid.member_id = member.id
group by covid.stay

-- 62ms
```

###### [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

```sql
select
    programmer.exercise, 
    count(*)
from programmer
inner join covid
on programmer.id = covid.programmer_id
inner join (select id from hospital where name = '서울대병원') seoulHospital
on covid.hospital_id = seoulHospital.id
inner join (select id from member where age between 30 and 39) member
on covid.member_id = member.id
group by programmer.exercise

-- 47ms
```

#### 2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

