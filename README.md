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
select
    연봉_상위_5위.사원번호,
    사원.이름,
    연봉_상위_5위.연봉,
    직급.직급명,
    사원출입기록.입출입시간,
    사원출입기록.지역,
    사원출입기록.입출입구분
from
(
    select
        부서관리자.사원번호,
        급여.연봉
    from  tuning.부서 as 부서
    inner join tuning.부서관리자 as 부서관리자 on 부서.부서번호 = 부서관리자.부서번호
    inner join tuning.급여 as 급여 on 부서관리자.사원번호 = 급여.사원번호
    where 부서.비고 = 'active'
      and 부서관리자.종료일자 > now()
      and 급여.종료일자 > now()
    order by 급여.연봉 desc
    limit 5
)  as 연봉_상위_5위
inner join tuning.사원출입기록 as 사원출입기록 on 연봉_상위_5위.사원번호 = 사원출입기록.사원번호
inner join tuning.사원 as 사원 on 연봉_상위_5위.사원번호 = 사원.사원번호
inner join tuning.직급 as 직급 on 연봉_상위_5위.사원번호 = 직급.사원번호
where 사원출입기록.입출입구분 = 'o'
  and 직급.종료일자 > now();
```
---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* Coding as a Hobby 와 같은 결과를 반환하세요.
```sql
-- 수행 시간 0.958 sec
select
    round(result.yes / result.total * 100, 1) as yes,
    round(result.no / result.total * 100, 1) as no
from (
    select
        count(case when programer.hobby = 'yes' then 1 end) as yes,
        count(case when programer.hobby = 'no' then 1 end)  as no,
        count(*)                                            as total
    from subway.programmer as programer
) result;
```
programmer 테이블에 hobby 컬럼에 인덱스 추가
```sql 
alter table `subway`.`programmer`
    add index `idx_hobby` (`hobby` asc);
```
수행 시간 0.042 sec

* 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```sql
-- 쿼리
select
    covid.id,
    hospital.name
from subway.hospital as hospital
inner join subway.covid as covid on hospital.id = covid.hospital_id
inner join subway.programmer programer on covid.programmer_id = programer.id;
```
싱행 결과 : Lost connection to MySQL server during query 조회가 안됨
hospital, covid 테이블에 인덱스 추가
```sql
alter table `subway`.`hospital`
    add index `idx_id` (`id` asc);
alter table `subway`.`covid`
    add index `idx_programmer_id` (`programmer_id` asc);
```
수행 시간 0.036 sec

* 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```sql
-- 쿼리
select
    covid.id,
    hospital.name,
    programer.hobby,
    programer.dev_type,
    programer.years_coding
from subway.programmer as programer
         inner join subway.covid as covid on programer.member_id = covid.member_id
         inner join subway.hospital as hospital on covid.hospital_id = hospital.id
where programer.hobby = 'yes'
  and (programer.student like 'yes%' or programer.years_coding = '0-2 years');
```
Lost connection to MySQL server during query 조회가 안됨
```sql
-- 인덱스 추가
alter table `subway`.`covid`
    add index `idx_hospital_id` (`hospital_id` asc);
```
-- Lost connection to MySQL server during query 조회가 안됨
```sql
-- 다른 곳에도 인덱스를 추가해서 문제 해결시도
alter table `subway`.`covid`
    add index `idx_programmer_id` (`programmer_id` asc);
alter table `subway`.`programmer`
    add index `idx_student` (`student` asc);
alter table `subway`.`programmer`
    add index `idx_years_coding` (`years_coding` asc);
```
수행 결과 : Lost connection to MySQL server during query 조회가 안됨
-- covid 테이블에 member_id에 인덱스 추가
```sql
alter table `subway`.`covid`
    add index `idx_member_id` (`member_id` asc);
```
수행 시간 0.0032 sec

* 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
```sql
-- 쿼리
select
    stay.stay,
    count(*) as count
from subway.programmer as programmer
inner join (
    select
        member.id
    from subway.member as member
    where member.age >= 20
      and member.age < 30
) as member on programmer.member_id = member.id
inner join (
    select
        stay,
        member_id
    from subway.covid 
    where hospital_id = (select id from subway.hospital where name = '서울대병원')
) as stay on member.id = stay.member_id
where programmer.country = 'india'
group by stay.stay;
```
수행 시간 0.829 sec<br>조회에 조건으로 들어가는 member 테이블의 age, programmer 테이블의 country 컬럼에 인덱스 추가
```sql
alter table `subway`.`member`
    add index `idx_age` (`age` asc);
alter table `subway`.programmer
    add index `idx_country` (`country` asc);
```
수행 시간 0.798 sec<br>수횅 시간의 변화가 크지 않아 조회에 영향을 미칠 것 같은 부분에 인덱스 추가
```sql
alter table `subway`.`covid`
    add index `idx_stay` (`stay` asc);
alter table `subway`.`programmer`
    add index `idx_member_id` (`member_id` asc);
```
수행 시간 0.243 sec<br>더 이상 인덱스 추가로는 수행 시간이 줄어들지 않아서 해결 방법을 찾기 위해 쿼리 플랜을 조회
<img width="1360" alt="image" src="https://user-images.githubusercontent.com/16080479/156116607-6c5e319f-cc66-41d9-85a9-c336c36b4c64.png">
```sql
-- member 테이블에 기본키 추가
alter table subway.member
    add constraint pk_id
        primary key (id);

```
수행 시간 0.069 sec<br><br>
기본키 추가 후 쿼리의 플랜
<img width="1384" alt="image" src="https://user-images.githubusercontent.com/16080479/156117146-bde3d781-4478-40d7-87b7-377fbeef9c9e.png">
* 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```sql
-- 쿼리
select
    programmer.exercise,
    count(*) as count
from subway.programmer as programmer
inner join (
    select
        member.id
    from subway.member as member
    where member.age >= 30
      and member.age < 40
) as member on programmer.member_id = member.id
inner join (
    select
        stay,
        member_id
    from subway.covid
    where hospital_id = (select id from subway.hospital where name = '서울대병원')
) as stay on member.id = stay.member_id
group by programmer.exercise;
```
수행 시간 0.028 sec

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
