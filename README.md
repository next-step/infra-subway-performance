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
```
explain
select 사원.사원번호    as 사원번호,
       사원.이름      as 이름,
       급여.연봉      as 연봉,
       직급.직급명     as 직급명,
       입출입구분,
       지역,
       max(입출입시간) as 입출입시간
from 사원출입기록,
     사원,
     직급,
     (select 사원번호, 연봉
      from 급여
      where 사원번호 in (
          select 부서관리자.사원번호
          from 부서관리자
          where 부서관리자.부서번호 in (
              select 부서.부서번호
              from 부서
              where 부서.비고 = 'Active'
          )
            and 부서관리자.종료일자 = '9999-01-01'
      )
        and 종료일자 = '9999-01-01'
      order by 연봉 desc
      limit 5) 급여
where 사원출입기록.사원번호 = 사원.사원번호
  and 사원출입기록.사원번호 = 급여.사원번호
  and 사원출입기록.사원번호 = 직급.사원번호
  and 직급.종료일자 = '9999-01-01'
  and 사원출입기록.입출입구분 = 'O'
group by 사원.사원번호, 사원.이름, 급여.연봉, 직급.직급명, 입출입구분, 지역
order by null
;
```

m1 기준 실행속도 : 1s 809ms

![step1](/images/step1.png)

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```
select c.id, h.name
from programmer p,
     covid c,
     hospital h
where p.id = c.programmer_id
  and h.id = c.hospital_id
```
![step2](https://user-images.githubusercontent.com/56009774/161279560-69898ce6-d230-4c1f-9ebc-39884b4b5b52.png)
![step2](https://user-images.githubusercontent.com/56009774/161279556-5a6d994f-bc8f-4a1f-9e5e-553f69e0b247.png)
![step2](https://user-images.githubusercontent.com/56009774/161279535-115210e8-4534-4be1-8fc5-9629937ddd79.png)

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```
select c.id, h.name, p.hobby, p.dev_type, p.years_coding
from programmer p,
     covid c,
     hospital h
where p.id = c.programmer_id
  and h.id = c.hospital_id
  and ((student like 'Yes%' and hobby = 'Yes') or (years_coding = '0-2 years'))
```
![step2](https://user-images.githubusercontent.com/56009774/161279982-f09d18da-6a7d-4ad0-8ced-bab3637c344a.png)
![step2](https://user-images.githubusercontent.com/56009774/161280004-6c01fc64-7bd8-4c42-bee0-f508228add43.png)
![step2](https://user-images.githubusercontent.com/56009774/161280008-5baa23e3-3bb8-4728-8c9d-3f606c25f375.png)

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
```
select c.stay, count(c.stay)
from programmer p,
     covid c,
     hospital h,
     member m
where p.id = c.programmer_id
  and h.id = c.hospital_id
  and m.id = p.member_id
  and p.country = 'India'
  and h.name = '서울대병원'
  and m.age in (20, 21, 22, 23, 24, 25, 26, 27, 28, 29)
group by c.stay
order by null  
```
![step2](https://user-images.githubusercontent.com/56009774/161280191-72292f7f-73ee-423e-826e-bcf1495a7410.png)
![step2](https://user-images.githubusercontent.com/56009774/161280198-40a963ae-7917-4fc3-97c2-ff2fc23bb126.png)
![step2](https://user-images.githubusercontent.com/56009774/161280201-78f35229-f8f0-4541-bf62-bbb0f49c0efd.png)

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```
select p.exercise, count(p.exercise)
from programmer p,
     covid c,
     hospital h,
     member m
where p.id = c.programmer_id
  and h.id = c.hospital_id
  and m.id = p.member_id
  and h.name = '서울대병원'
  and m.age in (30, 31, 32, 33, 34, 35, 36, 37, 38, 39)
group by p.exercise
order by null
```
![step2](https://user-images.githubusercontent.com/56009774/161280323-aee143c8-851a-478b-a0af-1ccf1aff9103.png)
![step2](https://user-images.githubusercontent.com/56009774/161280337-a2ea14f4-5128-4f85-8842-01044dad5d42.png)
![step2](https://user-images.githubusercontent.com/56009774/161280345-20c89ac3-4e46-47d8-9db0-117efb6ffaa6.png)
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
