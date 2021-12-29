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
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
   
#### A. 쿼리 최적화 
```
 1) 쿼리 작성만으로 1s 이하로 반환한다. 
 2) 인덱스 설정을 추가하여 50 ms 이하로 반환한다. 

 - 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. 
(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간) 
 - 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.
```
   

#### A-1) 쿼리 작성
- 실행 시간:  `250ms`

```sql
select E.*, D.입출입시간, D.지역, D.입출입구분
from 사원출입기록 D,
     (select C.사원번호, H.이름, C.연봉, G.직급명
      from 부서관리자 A, 부서 B, 급여 C, 사원 H, 직급 G
      where A.부서번호 = B.부서번호
        and B.비고 like 'active'
        and A.사원번호 = C.사원번호
        and H.사원번호 = A.사원번호
        and G.사원번호 = A.사원번호
        and C.시작일자 < now() and C.종료일자 > now()
        and A.시작일자 < now() and A.종료일자 > now()
        and G.시작일자 < now() and G.종료일자 > now()
      order by C.연봉 desc limit 5) E
where D.사원번호 = E.사원번호
  and 입출입구분 = 'O'
order by E.연봉 desc;
```
#### A-2) 인덱스 설정 추가
- 실행 시간: `0ms`
```sql
CREATE INDEX `idx_사원출입기록_사원번호`  ON `tuning`.`사원출입기록` (사원번호) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
```

#### B. 인덱스 설계


- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
  <br/>
   `*` 실행시간을 따로 명시하지 않은 것은 모두 `0ms`
  
<br/>
  - Coding as a Hobby 와 같은 결과를 반환하세요.

Yes | No
----------|-----------
80.80%	|19.20%
  
```sql
set @totalCnt = (select count(*) from programmer);
select concat(round(count(case when hobby = 'Yes' then 1 end) / @totalCnt * 100, 1), '%') AS Yes,
       concat(round(count(case when hobby = 'No' then 1 end) / @totalCnt * 100, 1), '%') AS No
from programmer;
```

<br/>
- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

```sql
select P.id, C.id, H.name
from programmer P
       inner join covid C
       inner join hospital H
                  on P.id = C.programmer_id and C.hospital_id = H.id;
```

<br/>
- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

```sql
select P.id, C.id, H.name, P.hobby, P.dev_type, P.years_coding
from programmer P
       inner join covid C
       inner join hospital H
                  on P.id = C.programmer_id
                    and C.hospital_id = H.id
where P.hobby = 'Yes' or P.years_coding like '0-2%';
```

<br/>
- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

- 실행 시간: `125ms`
```sql
SELECT C.stay as '머문 기간', COUNT(C.stay) as 집계 FROM covid as C 
  INNER JOIN (SELECT id, age FROM member WHERE age BETWEEN 20 and 29) as M ON M.id = C.member_id
  INNER JOIN (SELECT id FROM hospital WHERE name = '서울대병원') as H ON H.id = C.hospital_id
  INNER JOIN (SELECT id, country FROM programmer WHERE country = 'india') as P ON P.id = C.programmer_id
  GROUP BY C.stay;
```

- 인덱스 추가 후 실행 시간: `16ms`
```sql
   CREATE INDEX `idx_covid_hospital_id_member_id`  ON `subway`.`covid` (hospital_id, member_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT
```

<br/>
- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

- 실행 시간: `31ms`
```sql
SELECT P.exercise as '운동 횟수', COUNT(P.exercise) as 집계 FROM covid as C 
  INNER JOIN (SELECT id, age FROM member WHERE age BETWEEN 30 and 39) as M ON M.id = C.member_id
  INNER JOIN (SELECT id FROM hospital WHERE name = '서울대병원') as H ON H.id = C.hospital_id
  INNER JOIN (SELECT id, exercise FROM programmer WHERE country = 'india') as P ON P.id = C.programmer_id
  GROUP BY P.exercise;
```

<br/>

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
- ```/stations```
- 참고: `http/get-stations-with-page.http` 
