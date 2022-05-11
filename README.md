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

**WebPageTest 결과**

- Mobile(Chrome, LTE)

| 항목 | 개선전 | 개선후 |
|--|--|--|
| FCP | 5753ms | 3639ms |
| TTI | 6229ms | 3671ms |

- Desktop(Chrome, Cable)

| 항목 | 개선전 | 개선후 |
|--|--|--|
| FCP | 4918ms | 1719ms |
| TTI | 5064ms | 1707ms |

**Smoke 테스트 결과**

```
// before
✗ http_req_duration..............: avg=245ms    min=67.35ms med=168.32ms max=875.99ms p(90)=552.76ms p(95)=718.02ms

// after
✓ http_req_duration..............: avg=31.97ms min=1.89ms  med=5.55ms  max=257.06ms p(90)=79.8ms   p(95)=250.18ms
```

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.1s), 0/1 VUs, 9 complete and 0 interrupted iterations
default ↓ [======================================] 1 VUs  10s

     ✓ retrieved stations
     ✓ retrieved paths

     checks.........................: 100.00% ✓ 18       ✗ 0
     data_received..................: 681 kB  67 kB/s
     data_sent......................: 4.2 kB  409 B/s
     http_req_blocked...............: avg=30.69ms min=297ns   med=463ns   max=552.52ms p(90)=617ns    p(95)=82.87ms
     http_req_connecting............: avg=37.9µs  min=0s      med=0s      max=682.37µs p(90)=0s       p(95)=102.35µs
   ✓ http_req_duration..............: avg=31.97ms min=1.89ms  med=5.55ms  max=257.06ms p(90)=79.8ms   p(95)=250.18ms
       { expected_response:true }...: avg=31.97ms min=1.89ms  med=5.55ms  max=257.06ms p(90)=79.8ms   p(95)=250.18ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 18
     http_req_receiving.............: avg=1.36ms  min=30.34µs med=1ms     max=3.88ms   p(90)=3ms      p(95)=3.46ms
     http_req_sending...............: avg=71.51µs min=32.22µs med=57.79µs max=280.76µs p(90)=103.95µs p(95)=162.29µs
     http_req_tls_handshaking.......: avg=1.79ms  min=0s      med=0s      max=32.22ms  p(90)=0s       p(95)=4.83ms
     http_req_waiting...............: avg=30.53ms min=1.81ms  med=3.35ms  max=256.92ms p(90)=76.66ms  p(95)=246.74ms
     http_reqs......................: 18      1.773708/s
     iteration_duration.............: avg=1.12s   min=1s      med=1.01s   max=2.06s    p(90)=1.22s    p(95)=1.64s
     iterations.....................: 9       0.886854/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

**Load 테스트 결과**

```
// before
✗ http_req_duration..............: avg=19.19s  min=30.07ms med=26.03s   max=40.4s   p(90)=36.35s  p(95)=37.01s

// after
✓ http_req_duration..............: avg=4.4ms   min=1.34ms  med=4.35ms   max=253.11ms p(90)=7.84ms  p(95)=9.38ms
```

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 46 max VUs, 3m30s max duration (incl. graceful stop):
           * default: Up to 46 looping VUs for 3m0s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m00.4s), 00/46 VUs, 5511 complete and 0 interrupted iterations
default ↓ [======================================] 01/46 VUs  3m0s

     ✓ retrieved stations
     ✓ retrieved paths

     checks.........................: 100.00% ✓ 11022     ✗ 0
     data_received..................: 414 MB  2.3 MB/s
     data_sent......................: 2.0 MB  11 kB/s
     http_req_blocked...............: avg=19.26µs min=160ns   med=405ns    max=15.94ms  p(90)=584ns   p(95)=667ns
     http_req_connecting............: avg=2.85µs  min=0s      med=0s       max=1.64ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=4.4ms   min=1.34ms  med=4.35ms   max=253.11ms p(90)=7.84ms  p(95)=9.38ms
       { expected_response:true }...: avg=4.4ms   min=1.34ms  med=4.35ms   max=253.11ms p(90)=7.84ms  p(95)=9.38ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 11022
     http_req_receiving.............: avg=1.26ms  min=14.91µs med=323.31µs max=28.57ms  p(90)=3.06ms  p(95)=4.04ms
     http_req_sending...............: avg=76.16µs min=19.06µs med=44.48µs  max=8.31ms   p(90)=78.14µs p(95)=98.86µs
     http_req_tls_handshaking.......: avg=15.22µs min=0s      med=0s       max=14.23ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3.06ms  min=1.28ms  med=2.84ms   max=252.98ms p(90)=4.53ms  p(95)=6.08ms
     http_reqs......................: 11022   61.093424/s
     iteration_duration.............: avg=1.01s   min=1s      med=1.01s    max=1.47s    p(90)=1.01s   p(95)=1.01s
     iterations.....................: 5511    30.546712/s
     vus............................: 1       min=1       max=46
     vus_max........................: 46      min=46      max=46
```

**Stress 테스트 결과**

```
// before
✗ http_req_duration..............: avg=1.34s   min=0s      med=0s      max=28.38s   p(90)=6.38s    p(95)=7.94s

// after
✗ http_req_duration..............: avg=110.82ms min=0s     med=32.1ms  max=1.46s    p(90)=327.67ms p(95)=501.93ms
```

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 3680 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 3680 looping VUs for 7m0s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m51.4s), 0000/3680 VUs, 21200 complete and 426 interrupted iterations
default ✗ [==============>-----------------------] 0297/3680 VUs  2m51.4s/7m00.0s

     ✓ retrieved stations
     ✓ retrieved paths

     checks.........................: 100.00% ✓ 42740      ✗ 0
     data_received..................: 1.6 GB  9.5 MB/s
     data_sent......................: 10 MB   61 kB/s
     http_req_blocked...............: avg=8.32ms   min=0s     med=381ns   max=699.2ms  p(90)=2.88µs   p(95)=65.93ms
     http_req_connecting............: avg=657.53µs min=0s     med=0s      max=134.29ms p(90)=0s       p(95)=732.85µs
   ✗ http_req_duration..............: avg=110.82ms min=0s     med=32.1ms  max=1.46s    p(90)=327.67ms p(95)=501.93ms
       { expected_response:true }...: avg=110.85ms min=1.31ms med=32.08ms max=1.46s    p(90)=327.71ms p(95)=502.51ms
     http_req_failed................: 0.16%   ✓ 72         ✗ 42740
     http_req_receiving.............: avg=10.15ms  min=0s     med=1.4ms   max=884.36ms p(90)=25.16ms  p(95)=50.86ms
     http_req_sending...............: avg=2.2ms    min=0s     med=38.49µs max=680.94ms p(90)=114.59µs p(95)=211.19µs
     http_req_tls_handshaking.......: avg=7.12ms   min=0s     med=0s      max=296.91ms p(90)=0s       p(95)=57.58ms
     http_req_waiting...............: avg=98.46ms  min=0s     med=24.9ms  max=1.38s    p(90)=303.97ms p(95)=443.69ms
     http_reqs......................: 42812   249.831875/s
     iteration_duration.............: avg=1.22s    min=3.17ms med=1.07s   max=2.83s    p(90)=1.7s     p(95)=1.99s
     iterations.....................: 21200   123.713813/s
     vus............................: 425     min=1        max=425
     vus_max........................: 3680    min=3680     max=3680
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

Reverse Proxy 개선
- gzip 압축
- cache
- HTTP/2

WAS 성능 개선
- Spring Data Cache

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

``` sql
select
  m.employee_id as "사원번호",
  e.last_name as "이름",
  m.annual_income as "연봉",
  m.position_name as "직급명",
  r.time as "입출입시간",
  r.region as "지역",
  r.record_symbol as "입출입구분"
from
  (
    select
      m.employee_id, s.annual_income, m.position_name
    from
      salary as s,
      (
        select
          m.employee_id, p.position_name
        from
          department as d,
          position as p,
          manager as m
        where
          d.id = m.department_id and d.note = "active" and
          p.id = m.employee_id and p.position_name = "manager" and p.end_date > current_date()
      ) as m
    where
      s.id = m.employee_id and
      s.end_date > current_date()
    order by s.annual_income desc
    limit 5
  ) as m,
  record r,
  employee e
where
  r.employee_id = m.employee_id and r.record_symbol = "O" and
  e.id = m.employee_id
```

**결과**

```
+----------+----------+--------+---------+---------------------+------+------------+
| 사원번호   | 이름      | 연봉    | 직급명    | 입출입시간             | 지역  | 입출입구분    |
+----------+----------+--------+---------+---------------------+------+------------+
|   110039 | Vishwani | 106491 | Manager | 2020-09-05 20:30:07 | a    | O          |
|   110039 | Vishwani | 106491 | Manager | 2020-08-05 21:01:50 | b    | O          |
|   110039 | Vishwani | 106491 | Manager | 2020-07-06 11:00:25 | d    | O          |
|   111133 | Hauke    | 101987 | Manager | 2020-01-24 02:59:37 | a    | O          |
|   111133 | Hauke    | 101987 | Manager | 2020-05-07 16:30:37 | b    | O          |
|   110114 | Isamu    |  83457 | Manager | 2020-05-29 19:38:12 | a    | O          |
|   110114 | Isamu    |  83457 | Manager | 2020-09-03 01:33:01 | b    | O          |
|   110114 | Isamu    |  83457 | Manager | 2020-11-12 02:29:00 | c    | O          |
|   110114 | Isamu    |  83457 | Manager | 2020-04-25 08:28:54 | d    | O          |
|   110567 | Leon     |  74510 | Manager | 2020-10-17 19:13:31 | a    | O          |
|   110567 | Leon     |  74510 | Manager | 2020-02-03 10:51:15 | b    | O          |
|   110228 | Karsten  |  65400 | Manager | 2020-01-11 22:29:04 | d    | O          |
|   110228 | Karsten  |  65400 | Manager | 2020-07-13 11:42:49 | a    | O          |
|   110228 | Karsten  |  65400 | Manager | 2020-09-23 06:07:01 | b    | O          |
+----------+----------+--------+---------+---------------------+------+------------+
14 rows in set (0.24 sec)
```

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
