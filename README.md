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

- M1 환경입니다.

```
select top5OfSalary.사번, 사.이름, top5OfSalary.연봉, 직.직급명, 출입기록.입출입시간, 출입기록.지역, 출입기록.입출입구분
from (select Active관리자.사원번호 as 사번, max(급.연봉) as 연봉
      from (select 부서번호 from 부서 where 비고 = 'Active') activeDepart
               join (select 사원번호, 부서번호 from 부서관리자 where 종료일자 = '9999-01-01') Active관리자
                    on activeDepart.부서번호 = Active관리자.부서번호
               join 급여 급 on Active관리자.사원번호 = 급.사원번호
      group by Active관리자.사원번호
      order by 연봉 desc
      limit 5) top5OfSalary
         join 사원 사 on top5OfSalary.사번 = 사.사원번호
         join (select 사원번호, 직급명 from 직급 where 직급명 = 'Manager') 직 on top5OfSalary.사번 = 직.사원번호
         join (select * from 사원출입기록 where 입출입구분 = 'O') 출입기록 on top5OfSalary.사번 = 출입기록.사원번호;
```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

#### Q1 - Coding as a Hobby 와 같은 결과를 반환하세요.

programmer 테이블의 hobby 로만 쿼리가 구성되어 있어서 hobby필드로 인덱스를 추가하니 성능이 개선됐다.

```sql
select hobby, (count(hobby) / (select count(hobby) as count from programmer)) * 100 as result
from programmer
group by hobby;
```

#### Q2 - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

programmer, hospital, covid을 조인해야 하는 문제였다. 3개 테이블 중 크기가 가장 작은 hospital을 드라이빙 테이블로 지정했다. Covid 테이블이 310,000건으로 가장
컸고 covid 테이블 통해 programmer, hospital을 조인해야 했기 때문에 인덱스를 추가했다.

```sql
CREATE INDEX covid_hospital_id ON covid (hospital_id);
CREATE INDEX covid_programmer_id ON covid (programmer_id);
CREATE INDEX programmer_id on programmer (id);
```

covid의 hospital_id, programmer_id에 인덱스를 추가했고, programmer의 id에 인덱스를 추가했다. 조인 조건이 되는 id에 인덱스를 추가했기 때문에 실행계획에서 ref가
나왔다. 성능은 110ms 전후로 측정됐다. 아래는 최종쿼리이다.

```sql
select programmer_id, hospital.name
from hospital
         join covid c on hospital.id = c.hospital_id
         join programmer p on c.programmer_id = p.id;
``` 

#### Q3 - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

Programmer 테이블의 where 조건이 많이 들어가는 문제였다. hobby, years_coding, dev_type 이 세가지 컬럼에 인덱스가 필요해보였다. 하지만 hobby는 true /
false로 나눠지기 때문에 인덱스 설정이 적절하지 않다고 판단했다. 또한, dev_type은 type이 text라 인덱스를 못 만든다는 Mysql 에러가 발생했다.

```sql
[42000][1170] BLOB/TEXT column 'dev_type' used in key specification without a key length
```

결국, 아래와 같이 years_coding에 대해서만 인덱스를 추가했다.

```sql
create index years_coding on programmer (years_coding);
```

하지만, 인덱스 추가했다고 100ms 이하로 성능이 개선되진 않았고 1초대로 측정됐다.

```sql
select p.id, h.name
from hospital h
         join covid c on h.id = c.hospital_id
         join (select id
               from programmer
               where hobby = 'Yes' and years_coding = '0-2 years'
                  or dev_type = 'Student'
               order by id) p on c.programmer_id = p.id;
```

#### Q4 - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

hospital, covid, member, programmer 테이블 조인해야 하는 문제이다. `서울대병원에 다는 20대 India 환자`라는 조건을 join 전에 각자 테이블에서 줄여야 한다고
생각했다. member와 programmer 테이블에 적용했다.

```sql
select id
from member
where age between 20 and 29

select id
from programmer
where country = 'India'
```

member의 id와 age에 인덱스가 필요하다 판단해 인덱스를 추가했다.

```sql
CREATE INDEX member_id on member (id);
CREATE INDEX member_age on member (age);
```

아래 최종 쿼리이고, 성능은 200ms로 개선했다.

```sql
select c.stay, count(c.stay) as '서울대병원에 다닌 20대 India 환자들 병원에서 머문 기간'
from (select * from hospital where id = 9) as h
         join covid c on h.id = c.hospital_id
         join (select id from member where age between 20 and 29) m on m.id = c.member_id
         join (select id from programmer where country = 'India') p on c.programmer_id = p.id
group by c.stay;
```

#### Q5 - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

`서울대병원에 다닌 30대 환자`라는 조건을 적용해서 join 대상이 되는 테이블을 줄이는 게 필요하다고 판단했다. 인덱스는 이미 이전 문제를 해결하면서 추가했기 때문에 따로 추가하지는 않았다. 최종
쿼리는 아래와 같으면 성능은 300ms 전후가 측정된다.

```sql
select exercise, count(exercise) as '서울대병원에 다닌 30대 환자들 운동 횟수' from (select * from hospital where id=9) as h
join covid c on h.id = c.hospital_id
join (select id from member where age between 30 and 39) m on m.id = c.member_id
join programmer p on c.programmer_id = p.id
group by exercise;
```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
