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

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
select 사원출입기록.사원번호, 사원.이름, 탑급여관리자.연봉, 탑급여관리자.직급명, max(사원출입기록.입출입시간) as 입출입시간, 사원출입기록.지역, 사원출입기록.입출입구분
from (
         select 급여.사원번호, 직급.직급명, 급여.연봉
         from 부서
                  inner join 부서관리자 on 부서.부서번호 = 부서관리자.부서번호 and 부서관리자.종료일자 > now()
                  inner join 급여 on 부서관리자.사원번호 = 급여.사원번호 and 급여.종료일자 > now()
                  inner join 직급 on 부서관리자.사원번호 = 직급.사원번호 and 직급.종료일자 > now()
         where 비고 = 'Active'
         order by 급여.연봉 desc
         limit 5
 ) 탑급여관리자
inner join 사원 on 탑급여관리자.사원번호 = 사원.사원번호
inner join 사원출입기록 on 사원.사원번호 = 사원출입기록.사원번호  and 사원출입기록.입출입구분 = 'O'
group by 사원출입기록.사원번호, 사원출입기록.지역, 사원출입기록.입출입시간, 사원출입기록.입출입구분, 사원.이름, 탑급여관리자.연봉, 탑급여관리자.직급명;
```
---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요


### 각 쿼리 마다 기존 인덱스를 다 지우고 실행하였습니다.

####Coding as a Hobby 와 같은 결과를 반환하세요.

```sql
select hobby,  concat(round(count(hobby) * 100  / (select count(hobby) from programmer),1 ) , '%')as cnt
from programmer
group by hobby
order by cnt desc;

-- 튜닝
ALTER TABLE programmer
    ADD INDEX ix_hobby (hobby);


```
* 튜닝전 : 2.029 sec / 0.000007 sec 
* 튜닝후 : 0.08 sec / 0.000007 sec

---
#### 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)


```sql

select covid.id, hospital.name
from hospital
         inner join covid on  hospital.id = covid.hospital_id
         inner join programmer  on covid.programmer_id = programmer.id

--- 튜닝
create index idx_hosptial_id
    on subway.covid (hospital_id);

create index idx_programmer_id
    on subway.covid (programmer_id);

ALTER TABLE programmer
    ADD PRIMARY KEY (id)
```

1000개 조회 기준
* 튜닝전 : 0.7 sec 이상 / 0.4 sec 이상 
* 튜닝후 : 0.006 sec 미만 / 0.003 미만

---
#### 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```sql

select  covid.id, hospital.name, programmer.hobby, programmer.dev_type, programmer.years_coding
from programmer
inner join covid on programmer.id = covid.programmer_id
inner join hospital on covid.hospital_id = hospital.id
where (programmer.hobby = 'yes' and programmer.student like 'yes%') or programmer.years_coding = '0-2 years' ;

--- 튜닝
ALTER TABLE `subway`.`covid`
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
ADD PRIMARY KEY (`id`),
ADD INDEX `idx_programmer_id_hospital_id` (`programmer_id` ASC, `hospital_id` ASC);


```
1000개 조회 기준
* 튜닝전 : 무한
* 튜닝후 : 0.014sec / 0.059sec


---
#### 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

```sql

select  covid.stay , COUNT(*)
from programmer
inner join covid on programmer.id = covid.programmer_id
inner join hospital on covid.hospital_id = hospital.id and hospital.name = '서울대병원'
inner join member on programmer.member_id = member.id and member.age  between 20 and 29
where programmer.country = 'India'
GROUP BY covid.stay

--- 튜닝
ALTER TABLE `subway`.`programmer`
    CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
    ADD PRIMARY KEY (`id`),
    ADD INDEX `idx_country_member_id` (country ASC, member_id ASC);

ALTER TABLE `subway`.`member`
    CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
    ADD PRIMARY KEY (`id`),
    ADD INDEX `idx_age` (`age` ASC);


ALTER TABLE `subway`.`covid`
    ADD INDEX `idx_programmer_id_hospital_id_stay` (`programmer_id` ASC, `hospital_id` ASC, stay ASC);

```
* 튜닝전: 무제한 속도 걸림
* 튜닝후: 0.032sec / 0.0000007 sec

---
#### 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```sql
select exercise, count(*)
from programmer
inner join member on programmer.member_id = member.id and member.age  between 30 and 39
inner join covid  on member.id = covid.member_id
inner join hospital on covid.hospital_id = hospital.id and hospital.name = '서울대병원'
group by exercise;

-- 튜닝
ALTER TABLE `subway`.`member`
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
ADD PRIMARY KEY (`id`),
ADD INDEX `idx_age` (`age` ASC);

ALTER TABLE `subway`.`covid`
ADD INDEX `idx_hospital_id_member_id` (`hospital_id` ASC, `member_id` ASC);

ALTER TABLE `subway`.`programmer`
ADD INDEX `idx_member_id_exercise` USING BTREE (`member_id`, `exercise`);

```
* 튜닝전: 무제한 속도 걸림
* 튜닝후: 0.02sec / 0.0000007 sec 



### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
