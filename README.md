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

A. 쿼리 최적화

    ```
    SELECT t7.사원번호, t7.이름, t7.연봉, t7.직급명, t8.지역, t8.입출입구분, t8.입출입시간 FROM (
        SELECT t3.사원번호, t3.이름, t5.연봉, t6.직급명 FROM (SELECT 부서번호, 비고 FROM 부서 WHERE 부서.비고 = 'ACTIVE') as t1
        INNER JOIN 부서관리자 as t2 ON t1.부서번호 = t2.부서번호
        INNER JOIN 사원 as t3 ON t2.사원번호 = t3.사원번호
        INNER JOIN 부서사원_매핑 as t4 ON t3.사원번호 = t4.사원번호
        INNER JOIN 급여 as t5 ON t4.사원번호 = t5.사원번호
        INNER JOIN 직급 as t6 ON t5.사원번호 = t6.사원번호
        WHERE t5.종료일자 >= now() and t2.종료일자 >= now()
        GROUP BY t3.이름
        ORDER BY t5.연봉 DESC
        LIMIT 5
    ) as t7
    INNER JOIN 사원출입기록 as t8 ON t7.사원번호 = t8.사원번호
    WHERE t8.입출입구분 = 'O'
    ORDER BY t8.지역;
    ```

실행 시간 : 0.204

사원출입기록 사원번호 인덱스 설정 후

실행 시간 : 0.000

---
   

B. 인덱스 설계 

- [x] codding as a hobby 와 같은 결과 반환
  ```
  set @rowCount = (select count(hobby) from programmer);
  select hobby,  round(COUNT( * ) / @rowCount * 100, 1) AS percentage from programmer
  group by hobby DESC;
  ```
  hobby index로 지정 - full index scan으로 전환  
  실행 결과 0.032s
  
- [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
  ```
  SELECT c.id as covid_id, name as hospital_name FROM hospital as h
  INNER JOIN covid as c ON h.id = c.hospital_id
  INNER JOIN programmer as p ON p.id = c.programmer_id;
  ```
  programmer, hospital id 키 unique 및 pk 지정  
  covid programmer_id, hospital_id 복합 인덱스 키 지정  
  실행 결과 0.016s  
  
- [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)  
  ```
  SELECT c.id as covid_id, name as hospital_name, hobby as user_hobby, dev_type as user_devType, years_coding as user_YearsCoding FROM hospital as h
  INNER JOIN covid as c ON h.id = c.hospital_id
  INNER JOIN (
    SELECT * FROM programmer WHERE (hobby = 'Yes' and student = 'Yes') or years_coding = '0-2 years'
  ) as p ON p.id = c.programmer_id;
  ```
  실행 결과 : 0.016s  

- [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  ```
  SELECT c.stay, COUNT(c.id) FROM covid as c 
  INNER JOIN (SELECT id FROM hospital WHERE name = '서울대병원') as h ON h.id = c.hospital_id
  INNER JOIN (SELECT id, age FROM member WHERE age BETWEEN 20 and 29) as m ON m.id = c.member_id
  INNER JOIN (SELECT id, country FROM programmer WHERE country = 'india') as p ON p.id = c.programmer_id
  GROUP BY c.stay;
  ```
  hospital text -> varchar(255) 로 변경 후 index search 로 변경
  실행 결과 : 0.078s
  
- [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
  ```
  SELECT p.exercise, COUNT(c.id) FROM covid as c
  INNER JOIN (SELECT id FROM hospital WHERE name = '서울대병원') as h ON h.id = c.hospital_id
  INNER JOIN (SELECT id, age FROM member WHERE age BETWEEN 30 and 39) as m ON m.id = c.member_id
  INNER JOIN (SELECT id, exercise FROM programmer) as p ON p.id = c.programmer_id
  GROUP BY p.exercise;
  ```
  앞 단계 최적화 때문인지 바로 해결  
  실행 결과 : 0.062s
  
---
C. 페이징 쿼리

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
   http://localhost:8080/stations?page=0&size=2
   
---

D. MySQL Replication with JPA

적용 완료 /favorites 와 /stations를 통해 테스트 해 봄.
- 데이터가 너무 적어서 그런지 체감이 느껴지지는 않았음
- 그러나 자주 쓰는 쪽을 slave로 옮기거나 조회는 slave 데이터 삽입은 master 이런 식으로 나누는 방식에 대해 생각 해 볼 수 있었음
- subway 데이터가 처음에 들어와 있지 않아 mysql workbench 로 데이터 마이그레이션 진행.



---

### 삽질 record
- A. 처음 시도
```
SELECT 사원.사원번호, 사원.이름, 급여.연봉, 직급.직급명, 사원출입기록.입출입구분, 사원출입기록.입출입시간, 급여.종료일자 FROM 부서
INNER JOIN 부서관리자 ON 부서.부서번호 = 부서관리자.부서번호
INNER JOIN 사원 ON 부서관리자.사원번호 = 사원.사원번호
INNER JOIN 급여 ON 사원.사원번호 = 급여.사원번호
INNER JOIN 직급 ON 사원.사원번호 = 직급.사원번호
INNER JOIN 사원출입기록 ON 사원.사원번호 = 사원출입기록.사원번호
WHERE 비고 = 'active' and 급여.종료일자 >= now()
ORDER BY 급여.연봉 DESC
LIMIT 5;
```
- 원하는 결과를 뽑아낼 수 없음
    
- A2. 드디어 첫 결과 뽑아내기 성공
  ```
  select * from (select * from 부서 where 부서.비고 = 'ACTIVE') as t1
  INNER JOIN 부서관리자 as t2 ON t1.부서번호 = t2.부서번호
  INNER JOIN 사원 as t3 ON t2.사원번호 = t3.사원번호
  INNER JOIN 부서사원_매핑 as t4 ON t3.사원번호 = t4.사원번호
  INNER JOIN 급여 as t5 ON t4.사원번호 = t5.사원번호
  WHERE t5.종료일자 >= now() and t2.종료일자 >= now()
  ORDER BY t5.연봉 DESC
  LIMIT 5;
  ```
  
- A3. 합체
  ```
  select t7.사원번호, t7.이름, t7.연봉, t7.직급명, t8.지역, t8.입출입구분, t8.입출입시간 from (
  select t3.사원번호, t3.이름, t5.연봉, t6.직급명 from (select * from 부서 where 부서.비고 = 'ACTIVE') as t1
  INNER JOIN 부서관리자 as t2 ON t1.부서번호 = t2.부서번호
  INNER JOIN 사원 as t3 ON t2.사원번호 = t3.사원번호
  INNER JOIN 부서사원_매핑 as t4 ON t3.사원번호 = t4.사원번호
  INNER JOIN 급여 as t5 ON t4.사원번호 = t5.사원번호
  INNER JOIN 직급 as t6 ON t5.사원번호 = t6.사원번호
  WHERE t5.종료일자 >= now() and t2.종료일자 >= now()
  GROUP BY t3.이름
  ORDER BY t5.연봉 DESC
  LIMIT 5
  ) as t7
  INNER JOIN 사원출입기록 as t8 ON t7.사원번호 = t8.사원번호
  WHERE t8.입출입구분 = 'O'
  ORDER BY t8.지역;
  ```
  성공! -> 서브쿼리 테스트 해 본다고 필요없는 부서.비교 넣은 부분 최적화 가능할 듯


