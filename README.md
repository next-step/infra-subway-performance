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
    - k6-test/result/smoke-after.md
    - k6-test/result/load-after.md
    - k6-test/result/stress-after.md

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    - Reverse Proxy 개선
        - 정적 컨텐츠 전송 gzip 압축 활성화
        - cache control 적용(max-age 30일)
        - HTTP/2 설정
    - WAS 성능 개선
        - 노선, 역, 경로 조회 기능 redis cache 적용

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
   - [x] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
      - [x] Coding as a Hobby 와 같은 결과를 반환하세요.
         ```sql
         SELECT hobby,
         round(count(hobby) / (SELECT count(hobby) FROM programmer) * 100, 1) AS "Coding as a Hobby"
         FROM programmer
         GROUP BY hobby
        
         # 결과: 0.297s -> 0.047s
         ```
         - `programmer.hobby` 컬럼에 인덱스를 줘서 정렬시켰습니다.
         - 조회 시 `programmer.hobby` 컬럼을 명시하여 커버링 인덱스를 적용했습니다. 
         - count 함수에 `id`, `*`로도 각각 지정해 봤는데 `*`로 지정 시 옵티마이저가 자동으로 커버링 인덱스를 적용했습니다.

      - [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
         ```sql
         SELECT c.id, h.name
         FROM covid c, hospital h
         WHERE c.hospital_id = h.id
           AND c.id >= 1000
         LIMIT 0, 1000
         
         # 결과: 0.735s -> 0.000s
         ```
         - `hospital.id` 컬럼을 PK로 지정했습니다.
         - `covid.id` 컬럼을 PK로 지정하고, 페이징 쿼리와 `covid.id >= 1000`를 적용해서 `Index Range Scan`이 동작하도록 수정했습니다.

      - [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
         ```sql
         SELECT p.id, h.name
         FROM programmer p, covid c, hospital h
         WHERE p.id = c.id
           AND c.hospital_id = h.id
           AND (p.hobby = 'Yes' OR p.years_coding = '0-2 years')
           AND p.id >= 1000
         LIMIT 0, 1000
         
         # 결과: 0.360 -> 0.000s
         ```
         - `programmer.id` 컬럼을 PK로 지정했습니다.
         - `programmer.id` 컬럼을 PK로 지정하고, 페이징 쿼리와 `programmer.id >= 1000`를 적용해서 `Index Range Scan`이 동작하도록 수정했습니다.

      - [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
         ```sql
         SELECT m.id, c.stay, h.name
         FROM member m, programmer p, covid c, hospital h
         WHERE m.id = p.id
           AND p.id = c.id
           AND c.hospital_id = h.id
           AND m.age BETWEEN 20 AND 29
           AND h.name = '서울대병원'
           AND p.country = 'India'
         ORDER BY c.stay
         
            # 결과: 0.172s -> 0.032s
         ```
         - `member.age`, `programmer.country`, `hospital.name` 컬럼에 인덱스를 적용했습니다.
        
      - [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
         ```sql
         SELECT p.exercise, count(p.id)
         FROM hospital h, covid c, member m, programmer p
         WHERE h.id = c.hospital_id
           AND c.id = m.id
           AND c.id = p.id
           AND h.name = '서울대병원'
           AND m.age BETWEEN 30 AND 39
         GROUP BY p.exercise
         ORDER BY null
         
         # 결과: 0.203s -> 0.016s
         ```
         - `covid.hospital_id` 컬럼에 인덱스를 적용하여 성능이 향상됐습니다. 아래 순서로 스캔이 진행됐습니다.
            1. `hospital.name` 인덱스 스캔(Non-Unique Key Lookup)
            2. `hospital.id = covid.hospital_id` 인덱스 스캔(Non-Unique Key Lookup)
            3. `covid.id = member.id`, `covid.id = programmer.id` PK 스캔(Unique Key Lookup)


2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
   - Endpoint: https://우찬.웹.한국/favorites?page=0&size=5
   - Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3b290ZWNhbUBwcm8uMm5kIiwiaWF0IjoxNjI1OTIwNjg3LCJleHAiOjE2MjU5MjQyODd9.N4HcEJZ-GVYHcK7GgLzjQEiLFHajgb7Zih3wBUu0oVQ
