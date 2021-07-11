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
- [x] 대상 페이지
    * 메인, 로그인, 경로 검색
- [x] 부하 테스트 시 저장될 데이터 건수 및 크기  
    * 준비 된 운영 DB 데이터  
- [x] 스크립트 작성
    * loadTest/longin 
        * smoke.js
        * load.js
        * stress.js
        
    * loadTest/main
        * smoke.js
        * load.js
        * stress.js

    * loadTest/path
        * smoke.js
        * load.js
        * stress.js

- [x] 개선 전 테스트 결과
    * loadTest/longin/beforeResult.txt
    * loadTest/main/beforeResult.txt
    * loadTest/path/beforeResult.txt

- [x] 개선 후 테스트 결과
    * loadTest/longin/afterResult.txt
    * loadTest/main/afterResult.txt
    * loadTest/path/afterResult.txt
    
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    - [x] Reverse Proxy 개선
        * gzip 압축 활성화
        * HTTP2.0 설정
    - [x] WAS 성능 개선
        * redis 적용
            * application-prod.properties 에 redis 설정값 추가 및 build.gradle 에 redis 추가
            * DB 서버에 Docker로 Redis Server 기동
            * 조회가 빈번한 station service, map service, member service 관련 메소드에 캐시 적용
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요 
   1) Coding as a Hobby 와 같은 결과를 반환하세요.
    ```roomsql  
        SELECT (Count(id) / (SELECT COUNT(id) FROM subway.programmer) * 100) as 'HobbyRate'
        FROM programmer
        GROUP BY hobby;
    ```
    
    2)  프로그래머별로 해당하는 병원 이름을 반환
        - [x] covid와 hosptial id 값을 pk 설정
        - [x] covid에 programmer_id가 존재해 programmer table join은 필요없어보여 하지 않았고 페이징쿼리 적용

    ```roomsql
        select c.id, h.name, c.programmer_id
        from subway.covid c
        join subway.hospital h on c.hospital_id = h.id
        LIMIT 0, 10;
    ```
   
    3) 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
        - [x] programmer 테이블의 id 값을 pk 설정하였고 hobby 컬럼에 인덱스 설정
    ```roomsql
        select c.id, h.name, p.hobby, p.dev_type, p.years_coding
        from subway.covid c
        join subway.hospital h on c.hospital_id = h.id
        join subway.programmer p on c.programmer_id = p.id
         where p.hobby = 'Yes' and (p.student like 'Yes%' or p.years_coding = '0-2 years')
        limit 0, 10;
    ```
   
    4) 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요
        - [x] hospital의 name 컬럼 text에서 varchar(225) 변경후 unique 및 인덱스 추가
        - [x] member의 age 컬럼 인덱스 추가 
        - [x] programmer의  country 컬럼 인덱스를 추가
    ```roomsql
        select c.stay, count(p.member_id)
        from subway.covid c
        join subway.member  m on c.member_id = m.id and m.age between 20 and 29
        join subway.programmer p on c.programmer_id = p.id and p.country = 'India'
        join subway.hospital h on h.id = c.hospital_id and h.name = '서울대병원'
        group by stay;
    ```
   5) 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
    ```roomsql
        select p.exercise, count(*)
        from covid c
        join subway.member  m on c.member_id = m.id and m.age between 30 and 39
        join subway.programmer p on c.programmer_id = p.id
        join subway.hospital h on h.id = c.hospital_id and h.name = '서울대병원'
        group by p.exercise
        order by null;
    ```
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요 : 
   - GET /favorites
   - https://sojeong-subway.n-e.kr/favorites
