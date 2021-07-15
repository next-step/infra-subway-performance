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
* test script: /performance/script/
* 개선전 테스트 결과: /performance/result/before/
* reverse proxy 개선 후 테스트 결과: /performance/result/proxy-improve/
* redis cache 적용 후 테스트 결과: /performance/result/cache-improve/
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    * reverse proxy 개선
        * gzip 적용
        * css, js, 이미지 파일에 대한 캐시 적용
        * http2 적용
    * 경로 조회 기능에 redis cache 적용

#### 요구사항
* 부하 테스트 각 시나리오의 요청 시간을 50이하로 개선
    * 개선 전/후를 직접 계측하여 확인

#### 작업 진행 순서
* [x] 성능 개선전 somke, load, stress 테스트 진행
* [x] reverse proxy 개선 후 성능 측정
* [x] redis 적용 후 성능 측정

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
    1. Coding as a Hobby 와 같은 결과를 반환하세요.
        - 변경 전 실행 시간: 515ms
        - 변경 후 실행 시간: 32ms
        - 수정 내용 
            - programmer 테이블의 id pk 설정
            - hobby field index 생성
                - CREATE INDEX idx_programmer_hobby ON programmer (hobby)
        - 조회 쿼리
            ```sql
              select hobby,
              		 (count(id) / (select count(id) from subway.programmer) * 100) as 'hobbyCount'
               from subway.programmer
               group by hobby;
            ```
    2. 프로그래머별로 해당하는 병원 이름을 반환하세요.
        - 변경 전 실행 시간: timeout
        - 변경 후 실행 시간: 11ms
        - 수정 내용
            - hispital 테이블의 id pk 설정
            - covid 테이블의 programmer_id field index 생성
                - CREATE INDEX idx_covid_p_id ON covid (programmer_id)
        - 조회 쿼리
            ```sql
              select p.id, h.name
              from subway.programmer p
              join subway.covid c on p.id = c.programmer_id
              join subway.hospital h on c.hospital_id = h.id;
            ```
    3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 programmer.id 기준으로 정렬하세요.
        - 변경 전 실행 시간: 22ms
        - 변경 후 실행 시간: 19ms
        - 수정 내용
            - CREATE INDEX idx_programmer_years_coding ON programmer (years_coding)
            - CREATE INDEX idx_programmer_student ON programmer (student)
        - 조회 쿼리
            ```sql
              SELECT P.id, C.name
              FROM (
              	SELECT id
              	FROM subway.programmer
                  WHERE Hobby = 'Yes' AND (student LIKE 'Yes%' OR years_coding = '0-2 years')) AS P
              JOIN (
              	SELECT covid.programmer_id, name FROM subway.covid
              	JOIN (SELECT hospital.id, name FROM subway.hospital) AS H ON H.id = covid.hospital_id   
              ) AS C ON C.programmer_id = P.id
            ```
    4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
        - 변경 전 실행 시간: timeout
        - 변경 후 실행 시간: 119ms
        - 수정 내용
            - CREATE INDEX idx_covid_stay ON covid (stay)
            - CREATE INDEX idx_covid_member_id ON covid (member_id)
            - CREATE INDEX idx_hospital_name ON hospital (name)
            - CREATE INDEX idx_member_age ON member (age)
        - 조회 쿼리
            ```sql
              SELECT 
              stay
              , COUNT(P.member_id)
              FROM (SELECT id FROM subway.member WHERE age BETWEEN 20 AND 29) AS M
              JOIN ( 
              	SELECT member_id FROM subway.programmer WHERE country = 'India') as P 
              ON M.id = P.member_id
              JOIN (
              	 SELECT covid.id, covid.member_id, hospital_id, stay 
                   FROM subway.covid
              	 JOIN (SELECT id FROM subway.hospital WHERE name = '서울대병원') as H ON covid.hospital_id = H.id) as C 
              ON M.id = C.member_id
              GROUP BY C.stay
            ```
    5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
        - 변경 전 실행 시간: 55ms
        - 수정 내용
            - 별도 수정 사항 없습니다.
        - 조회 쿼리
            ```sql
              SELECT 
              exercise
              , COUNT(P.id)
              FROM (SELECT id FROM subway.member WHERE age BETWEEN 30 AND 39) AS M
              INNER JOIN (SELECT member_id, hospital_id, programmer_id FROM subway.covid) AS C
              ON C.member_id = M.id
              INNER JOIN (SELECT id, exercise FROM subway.programmer) AS P
              ON C.programmer_id = P.id
              INNER JOIN (SELECT id FROM subway.hospital WHERE name = '서울대병원') as H
              ON C.hospital_id = H.id
              GROUP BY exercise
              ORDER BY null
            ```
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

#### 요구사항
* 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* 즐겨찾기 페이지에 페이징 쿼리를 적용
    * 로그인한 사용자는 최근에 추가한 즐겨찾기만 관심이 있기에 한번에 5개의 즐겨찾기만 보고싶다.
* 데이터베이스 이중화

#### 작업 진행 순서
* [x] 인덱스 적용해보기 실습
* [ ] 데이터베이스 이중화
* [ ] 즐겨찾기 페이지 페이징 쿼리 적용
