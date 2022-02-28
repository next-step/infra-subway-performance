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

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역,
  입출입구분, 입출입시간)

<img width="1629" alt="CleanShot 2022-02-26 at 17 03 19@2x" src="https://user-images.githubusercontent.com/37217320/155835455-62e43e00-8f17-48ce-a8de-a5c1e08c0a4d.png">

<img width="1738" alt="CleanShot 2022-02-26 at 17 04 29@2x" src="https://user-images.githubusercontent.com/37217320/155835493-c132884b-b8a7-499e-a163-3a63868232b7.png">

```mysql
select u.사원번호, u.이름, u.연봉, u.직급명, h.입출입시간, h.지역, h.입출입구분
from 사원출입기록 h
         right join (select a.사원번호, u.이름, r.직급명, a.부서번호, p.연봉
                     from 부서관리자 a
                              join (select p1.사원번호, p1.연봉
                                    from 급여 p1
                                    where p1.종료일자 > now()) p
                                   on a.사원번호 = p.사원번호
                              join 사원 u
                                   on a.사원번호 = u.사원번호
                              join 부서 d
                                   on a.부서번호 = d.부서번호
                              join (select r1.사원번호, r1.직급명
                                    from 직급 r1
                                    where r1.종료일자 > now()) as r
                                   on r.사원번호 = u.사원번호
                     where d.비고 = 'active'
                       and a.종료일자 > now()
                     order by p.연봉 desc
                     limit 5) u
                    on h.사원번호 = u.사원번호
where h.입출입구분 = 'O'
order by u.연봉 desc, h.지역;


```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

#### [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 와 같은 결과를 반환하세요.

(629ms)

```mysql
select years_coding,
       ROUND((count(*) / (select count(*)
                          from programmer
                          where years_coding <> 'NA')) * 100, 1) as percentage
from programmer
where years_coding <> 'NA'
group by years_coding
order by years_coding * 1;
```

인덱스 적용

```
 create index programmer_years_coding_index
    on programmer (years_coding);
```

performance: 4.637ms -> 629ms

<img width="346" alt="CleanShot 2022-03-01 at 00 40 50@2x" src="https://user-images.githubusercontent.com/37217320/156012293-f2ce6b7c-36d1-455d-a279-740b4492d87c.png">

#### 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

(3.17s)

```mysql
select c.id, h.name
from covid c
         join programmer p
              on c.programmer_id = p.id
         join hospital h
              on c.hospital_id = h.id
group by c.id, h.name;
```

인덱스 적용 performance: 120s after (time-out) -> 3.17s

```mysql
alter table programmer
    add constraint programmer_pk
        primary key (id);

alter table hospital
    add constraint hospital_pk
        primary key (id);

alter table covid
    add constraint covid_pk
        primary key (id);


create index covid_programmer_id_hospital_id_index
    on covid (programmer_id, hospital_id);
```

개선 포인트:

- programmer_id, hospital_id 두가지를 한 Index로 지정: 3.17s
- programmer_id, hospital_id를 다르게 두 Index 지정: 3.352s (미적용)

<img width="571" alt="CleanShot 2022-03-01 at 00 41 54@2x" src="https://user-images.githubusercontent.com/37217320/156012451-e7d9439a-b210-4f6b-b1d6-0c7ccbd861bd.png">

#### 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

(4.16s)

```mysql
select c.id, h.name, p.hobby, p.dev_type, p.years_coding
from programmer p
         join covid c on p.id = c.programmer_id
         join hospital h on c.hospital_id = h.id
where hobby = 'Yes'
  and (student <> 'No' || years_coding = '0-2 years')
order by p.id;

```

인덱스 적용: 10.9s -> 4.16s

<img width="608" alt="CleanShot 2022-03-01 at 00 04 29@2x" src="https://user-images.githubusercontent.com/37217320/156006150-a9bb2048-e0e6-4a9b-8dbc-031cebd2006a.png">

```mysql
 create index programmer_hobby_index
    on programmer (hobby);
```

시도 1: Or조건은 Index를 안타는 것으로 알고 있어서, union all로 개선을 해보면 어떨까?

- 결과: 6.379초
- union 과정에서, `Full Table Scan` 이 결국 일어나는 것으로 보인다.
  <img width="1563" alt="CleanShot 2022-02-28 at 23 58 19@2x" src="https://user-images.githubusercontent.com/37217320/156005186-f8c7cc8d-4652-421b-8478-48eabc828e7e.png">

```mysql

create index programmer_student_hobby_index
    on programmer (hobby, student);

select c.id, h.name, p.hobby, p.dev_type, p.years_coding
from (select c.id, h.name, p.hobby, p.dev_type, p.years_coding
      from programmer p
               join covid c on p.id = c.programmer_id
               join hospital h on c.hospital_id = h.id
      where hobby = 'Yes'
        and student like 'Yes%'
      union all
      select c2.id, h2.name, p2.hobby, p2.dev_type, p2.years_coding
      from programmer p2
               join covid c2 on p2.id = c2.programmer_id
               join hospital h2 on c2.hospital_id = h2.id
      where p2.hobby = 'Yes'
        and years_coding = '0-2 years') p
         join covid c on p.id = c.programmer_id
         join hospital h on c.hospital_id = h.id
where hobby = 'Yes'
order by p.id;
```

#### 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

(1.354s)

```mysql
select c.stay, count(*)
from member m
         join programmer p on m.id = p.member_id
         join covid c on p.id = c.programmer_id
         join hospital h on h.id = c.hospital_id
where m.age in (20, 21, 22, 23, 24, 25, 26, 27, 28, 29)
  and p.country = 'India'
  and h.id = 9
group by stay;

```

```mysql
alter table member
    add constraint member_pk
        primary key (id);

create index member_age_index
    on member (age);

create index programmer_country_index
    on programmer (country);

```

성능 개선: 120s after -> 1.354s

<img width="754" alt="CleanShot 2022-03-01 at 00 32 56@2x" src="https://user-images.githubusercontent.com/37217320/156011007-390e18da-ab38-4573-ab0b-503f994dda14.png">

#### 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

(0.289s)

```mysql
select p.exercise, count(*)
from member m
         join programmer p on m.id = p.member_id
         join covid c on p.id = c.programmer_id
         join hospital h on h.id = c.hospital_id
where m.age in (30, 31, 32, 33, 34, 35, 36, 37, 38, 39)
  and h.id = 9
group by p.exercise;
```

성능 개선: - -> 0.289s (위과정 설정 인덱스 사용)

<img width="671" alt="CleanShot 2022-03-01 at 00 38 04@2x" src="https://user-images.githubusercontent.com/37217320/156011829-b7b34597-6890-4803-b6f5-6c88f60c4d8e.png">


---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
