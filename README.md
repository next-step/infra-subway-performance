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

load 테스트 (개선 전)

```shell
     checks.........................: 100.00% ✓ 20453 ✗ 0
     data_received..................: 16 MB  81 kB/s
     data_sent......................: 4.4 MB 23 kB/s
     http_req_blocked...............: avg=2.27ms  min=800ns  med=2.7µs  max=1.02s   p(90)=9.65ms  p(95)=13.83ms
     http_req_connecting............: avg=2.24ms  min=0s     med=0s     max=1.02s   p(90)=9.52ms  p(95)=13.74ms
   ✗ http_req_duration..............: avg=29.91ms min=2.24ms med=9.65ms max=1.25s   p(90)=80.52ms p(95)=123.82ms
       { expected_response:true }...: avg=29.92ms min=3.47ms med=9.65ms max=1.25s   p(90)=80.61ms p(95)=123.83ms
     http_req_failed................: 0.00%  ✓ 0     ✗ 20453
     http_req_receiving.............: avg=75.78µs min=0s     med=71.2µs max=6.63ms  p(90)=116µs   p(95)=132.83µs
     http_req_sending...............: avg=55.36µs min=5.7µs  med=13.6µs max=39.41ms p(90)=73.2µs  p(95)=98.43µs
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s     max=0s      p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=29.78ms min=2.22ms med=9.54ms max=1.25s   p(90)=80.17ms p(95)=123.68ms
     http_reqs......................: 20453  107.266915/s
     iteration_duration.............: avg=1.06s   min=3.12ms med=1.01s  max=3.19s   p(90)=1.17s   p(95)=1.22s
     iterations.....................: 10229  53.646569/s
     vus............................: 7      min=2   max=70
     vus_max........................: 70     min=70  max=70
```

load 테스트 (개선 후)

```shell
     checks.........................: 100.00% ✓ 21440 ✗ 0
     data_received..................: 17 MB   88 kB/s
     data_sent......................: 4.6 MB  24 kB/s
     http_req_blocked...............: avg=18.5µs  min=1µs    med=2.6µs  max=6.69ms  p(90)=4.8µs  p(95)=5.6µs
     http_req_connecting............: avg=15.08µs min=0s     med=0s     max=6.61ms  p(90)=0s     p(95)=0s
   ✓ http_req_duration..............: avg=7.83ms  min=3.44ms med=7.46ms max=48.24ms p(90)=11.3ms p(95)=12.92ms
       { expected_response:true }...: avg=7.83ms  min=3.44ms med=7.46ms max=48.24ms p(90)=11.3ms p(95)=12.92ms
     http_req_failed................: 0.00%   ✓ 0     ✗ 21440
     http_req_receiving.............: avg=73.5µs  min=11µs   med=69µs   max=988.7µs p(90)=109µs  p(95)=123.8µs
     http_req_sending...............: avg=20.31µs min=5.3µs  med=12.9µs max=8.38ms  p(90)=42.4µs p(95)=49.6µs
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s     max=0s      p(90)=0s     p(95)=0s
     http_req_waiting...............: avg=7.73ms  min=3.34ms med=7.38ms max=48.02ms p(90)=11.2ms p(95)=12.81ms
     http_reqs......................: 21440   112.718502/s
     iteration_duration.............: avg=1.01s   min=1.01s  med=1.01s  max=1.05s   p(90)=1.01s  p(95)=1.02s
     iterations.....................: 10720   56.359251/s
     vus............................: 2       min=2   max=70
     vus_max........................: 70      min=70  max=70
```

stress test (개선 전)

```shell
     checks.........................: 100.00% ✓ 188684 ✗ 0
     data_received..................: 44 MB  184 kB/s
     data_sent......................: 48 MB  171 kB/s
     http_req_blocked...............: avg=40.78ms  min=0s     med=54.5ms   max=158.76ms p(90)=82.53ms  p(95)=91.28ms
     http_req_connecting............: avg=30.07ms  min=0s     med=0s       max=158.72ms p(90)=78.78ms  p(95)=88.19ms
   ✓ http_req_duration..............: avg=585.48ms min=0s     med=413.81ms max=21.2s    p(90)=1.26s    p(95)=1.38s
       { expected_response:true }...: avg=822.29ms min=3.61ms med=884.01ms max=2.98s    p(90)=1.33s    p(95)=1.46s
     http_req_failed................: 0.00% ✓ 0  ✗ 188684
     http_req_receiving.............: avg=48.9µs   min=0s     med=38.3µs   max=26.86ms  p(90)=105.7µs  p(95)=127.7µs
     http_req_sending...............: avg=20.81ms  min=0s     med=47.4µs   max=285.9ms  p(90)=120.37ms p(95)=140.86ms
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s       max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=564.62ms min=0s     med=413.73ms max=21.2s    p(90)=1.2s     p(95)=1.36s
     http_reqs......................: 188684 661.625642/s
     iteration_duration.............: avg=1.46s    min=7.08ms med=1.38s    max=22.63s   p(90)=3.38s    p(95)=3.59s
     iterations.....................: 92753  349.862271/s
     vus............................: 1      min=1    max=700
     vus_max........................: 700    min=700  max=700
```

stress test (개선 전)

```shell
     checks.........................: 100.00% ✓ 190006 ✗ 0
     data_received..................: 45 MB   179 kB/s
     data_sent......................: 50 MB   199 kB/s
     http_req_blocked...............: avg=21.69µs  min=700ns  med=2.4µs    max=52.68ms p(90)=3.8µs    p(95)=4.5µs
     http_req_connecting............: avg=18.6µs   min=0s     med=0s       max=52.6ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=294.34ms min=5.68ms med=176.14ms max=2.64s   p(90)=789.54ms p(95)=1.08s
       { expected_response:true }...: avg=294.34ms min=5.68ms med=176.14ms max=2.64s   p(90)=789.54ms p(95)=1.08s
     http_req_failed................: 0.00%   ✓ 0      ✗ 190006
     http_req_receiving.............: avg=55.3µs   min=9.6µs  med=50.2µs   max=4.28ms  p(90)=93.2µs   p(95)=108.4µs
     http_req_sending...............: avg=17.4µs   min=5.6µs  med=13.1µs   max=9.38ms  p(90)=35.94µs  p(95)=45.5µs
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s       max=0s      p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=294.26ms min=5.61ms med=176.06ms max=2.64s   p(90)=789.45ms p(95)=1.08s
     http_reqs......................: 190006  756.930734/s
     iteration_duration.............: avg=1.58s    min=1.01s  med=1.4s     max=4.33s   p(90)=2.5s     p(95)=2.89s
     iterations.....................: 95003   378.465367/s
     vus............................: 1       min=1    max=700
     vus_max........................: 700     min=700  max=700
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
* nginx
  * gzip 압축 적용
  * worker 수 조정
  * http 2.0 적용
* was 성능 개선
  * 비동기 처리
  * redis 에 데이터 캐싱
  * was 인스턴스 추가하여 로드밸런싱
  * 정적 파일 경량화

처음에는 gzip 압축만으로 효과가 있었지만 부하테스트나 스트레스 테스트에서는 효과가 덜했습니다.
nginx worker 수를 조정해보고, 비동기로 처리하도록 수정하고 redis 를 적용하는 과정을 거쳤습니다.
마지막에는 was 인스턴스를 추가해서 처리량을 올렸습니다.

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* Coding as a Hobby 와 같은 결과를 반환하세요.
  * 개선: programmer.hobby 컬럼으로 인덱스 생성하여 성능 향상

```sql
-- 수정 전: 317ms
-- 수정 후: 55ms
SELECT hobby,
       ROUND(COUNT(1) / b.total * 100, 1)
  FROM programmer p
       CROSS JOIN
       (SELECT COUNT(1) AS total
          FROM programmer p2) b
 GROUP BY p.hobby, b.total;
-- 인덱스
CREATE INDEX idx_programmer_hobby ON programmer (hobby)
```

* 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
  * 개선: join 조건으로 사용하는 컬럼으로 인덱스를 생성하여 성능 향상

```sql
-- 수정 전: timeout
-- 수정 후: 11ms
SELECT p.id,
       h.name
  FROM programmer p 
       JOIN covid c
       ON p.id = c.programmer_id
          JOIN hospital h 
          ON c.hospital_id = h.id;
-- 인덱스
CREATE INDEX idx_programmer_id ON programmer (id);
CREATE INDEX idx_covid_p_id ON covid (programmer_id);
```

* 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
  * 개선: join 조건으로 사용하는 hospital.id 와 covid.hospital_id 컬럼으로 인덱스 생성하고 programmer 테이블에 필터 조건으로 인덱스 생성하여 성능 향상

```sql
-- 수정 전: 352ms
-- 수정 후: 11ms
SELECT a.id AS programmer_id
       , h.name AS hospital_name
  FROM 
       (SELECT p.*
          FROM programmer p 
         WHERE (student in ('Yes, full-time', 'Yes, part-time')
               OR years_coding = '0-2 years')
           AND hobby = 'Yes') a
       JOIN covid c
       ON a.id = c.programmer_id
          JOIN hospital h 
          ON c.hospital_id = h.id
 ORDER BY a.id;
-- 인덱스
CREATE INDEX idx_hopital_id ON hospital (id);
CREATE INDEX idx_programmer_hobby ON programmer (hobby);
CREATE INDEX idx_covid_h_i ON covid (hospital_id);
```

* 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  * 개선: hospital.name 컬럼 조회 시 성능이 저하됐는데, 데이터 타입이라 길이 20 만큼만 잘라서 인덱스로 생성한 뒤 성능 향상
 
```sql
-- 수정 전: 479ms
-- 수정 후: 69ms
SELECT c.stay,
       count(1) AS cnt
  FROM 
       (SELECT id
          FROM `member` m
         WHERE age BETWEEN 20 and 29) sa
       JOIN
            (SELECT id,
                    member_id
               FROM programmer p
              WHERE country = 'India') sb
       ON sa.id = sb.member_id
          JOIN covid c
          ON sb.id = c.programmer_id
             JOIN
                  (SELECT id,
                          name
                     FROM hospital h
                    WHERE name = '서울대병원') sc
             ON c.hospital_id = sc.id
 GROUP BY c.stay;
-- 인덱스
CREATE INDEX idx_hospital_name ON hospital (name(20));
```

* 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
  * 개선: 별도 개선 없이 성능 기준 만족

```sql
-- 수정 전: 49ms
-- 수정 후: 
SELECT sb.exercise,
       count(1) AS cnt
  FROM 
       (SELECT id
          FROM `member` m
         WHERE age BETWEEN 30 and 39) sa
       JOIN
            (SELECT id,
                    member_id,
                    exercise
               FROM programmer p) sb
       ON sa.id = sb.member_id
          JOIN covid c
          ON sb.id = c.programmer_id
             JOIN
                  (SELECT id,
                          name
                     FROM hospital h
                    WHERE name = '서울대병원') sc
             ON c.hospital_id = sc.id
 GROUP BY sb.exercise;
-- 인덱스
```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

