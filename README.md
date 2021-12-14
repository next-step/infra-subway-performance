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
## 1단계 - 화면 응답 개선하기
### 1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

#### 지하철역 조회
- Smoke
<details><summary>성능 개선 전</summary>

```bash

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


running (11.1s), 0/1 VUs, 8 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 지하철역이 정상적으로 조회됨

     checks.........................: 100.00% ✓ 16       ✗ 0
     data_received..................: 593 kB  53 kB/s
     data_sent......................: 2.6 kB  231 B/s
     http_req_blocked...............: avg=4.27ms   min=3.11µs  med=3.25µs   max=68.36ms  p(90)=3.62µs   p(95)=17.09ms
     http_req_connecting............: avg=191.66µs min=0s      med=0s       max=3.06ms   p(90)=0s       p(95)=766.65µs
   ✗ http_req_duration..............: avg=189.56ms min=16.2ms  med=101.47ms max=994.87ms p(90)=359.25ms p(95)=553.03ms
       { expected_response:true }...: avg=189.56ms min=16.2ms  med=101.47ms max=994.87ms p(90)=359.25ms p(95)=553.03ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 16
     http_req_receiving.............: avg=5.08ms   min=71.04µs med=963.25µs max=22.9ms   p(90)=16.48ms  p(95)=18.92ms
     http_req_sending...............: avg=96.27µs  min=58.82µs med=87.35µs  max=195.85µs p(90)=126.52µs p(95)=145.34µs
     http_req_tls_handshaking.......: avg=1.62ms   min=0s      med=0s       max=25.98ms  p(90)=0s       p(95)=6.49ms
     http_req_waiting...............: avg=184.38ms min=15.99ms med=92.48ms  max=971.89ms p(90)=350.69ms p(95)=545.63ms
     http_reqs......................: 16      1.438287/s
     iteration_duration.............: avg=1.39s    min=1.17s   med=1.29s    max=2.11s    p(90)=1.63s    p(95)=1.87s
     iterations.....................: 8       0.719144/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

ERRO[0012] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

- Load
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 1m30s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 1m0s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m29.7s), 000/240 VUs, 349 complete and 77 interrupted iterations
default ↓ [======================================] 206/240 VUs  1m0s

     ✓ 메인페이지가 정상적으로 응답함
     ✗ 지하철역이 정상적으로 조회됨
      ↳  22% — ✓ 81 / ✗ 281

     checks.........................: 64.34% ✓ 507      ✗ 281
     data_received..................: 7.4 MB 83 kB/s
     data_sent......................: 178 kB 2.0 kB/s
     http_req_blocked...............: avg=2.77ms   min=2.63µs  med=3.09µs   max=67.59ms p(90)=9.48ms   p(95)=10.82ms
     http_req_connecting............: avg=709.43µs min=0s      med=0s       max=12.62ms p(90)=2.42ms   p(95)=3.12ms
   ✗ http_req_duration..............: avg=14.12s   min=13.13ms med=3.57s    max=49.68s  p(90)=31.66s   p(95)=32.55s
       { expected_response:true }...: avg=4.8s     min=13.13ms med=406.58ms max=49.68s  p(90)=19.5s    p(95)=37.7s
     http_req_failed................: 35.65% ✓ 281      ✗ 507
     http_req_receiving.............: avg=1.49ms   min=32.89µs med=89.74µs  max=70.05ms p(90)=3.39ms   p(95)=10.02ms
     http_req_sending...............: avg=117.93µs min=35.43µs med=80.44µs  max=6.17ms  p(90)=176.55µs p(95)=194.71µs
     http_req_tls_handshaking.......: avg=1.99ms   min=0s      med=0s       max=54.65ms p(90)=6.84ms   p(95)=7.79ms
     http_req_waiting...............: avg=14.12s   min=12.92ms med=3.57s    max=49.68s  p(90)=31.66s   p(95)=32.5s
     http_reqs......................: 788    8.787701/s
     iteration_duration.............: avg=30.92s   min=1.09s   med=31.98s   max=52.18s  p(90)=38.28s   p(95)=40.93s
     iterations.....................: 349    3.892015/s
     vus............................: 6      min=1      max=240
     vus_max........................: 240    min=240    max=240

ERRO[0091] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

- Stress
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m25s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 55s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m25.0s), 000/300 VUs, 307 complete and 125 interrupted iterations
default ✓ [======================================] 000/300 VUs  55s

     ✓ 메인페이지가 정상적으로 응답함
     ✗ 지하철역이 정상적으로 조회됨
      ↳  24% — ✓ 78 / ✗ 246

     checks.........................: 67.46% ✓ 510      ✗ 246
     data_received..................: 7.4 MB 87 kB/s
     data_sent......................: 209 kB 2.5 kB/s
     http_req_blocked...............: avg=3.47ms   min=2.53µs  med=3.18µs   max=31.81ms p(90)=9.73ms   p(95)=10.75ms
     http_req_connecting............: avg=918.17µs min=0s      med=0s       max=24.78ms p(90)=3.03ms   p(95)=3.14ms
   ✗ http_req_duration..............: avg=14.33s   min=12.79ms med=4.24s    max=46.72s  p(90)=33.19s   p(95)=37.52s
       { expected_response:true }...: avg=5.97s    min=12.79ms med=2.19s    max=46.72s  p(90)=20.47s   p(95)=38.1s
     http_req_failed................: 32.53% ✓ 246      ✗ 510
     http_req_receiving.............: avg=1.24ms   min=32.95µs med=94.75µs  max=29.04ms p(90)=4.21ms   p(95)=8.88ms
     http_req_sending...............: avg=148.41µs min=40.68µs med=102.34µs max=8.9ms   p(90)=188.07µs p(95)=212.73µs
     http_req_tls_handshaking.......: avg=2.45ms   min=0s      med=0s       max=23.05ms p(90)=6.82ms   p(95)=8.02ms
     http_req_waiting...............: avg=14.33s   min=12.55ms med=4.24s    max=46.72s  p(90)=33.17s   p(95)=37.52s
     http_reqs......................: 756    8.893897/s
     iteration_duration.............: avg=32.7s    min=1.09s   med=32.14s   max=50.3s   p(90)=41.75s   p(95)=43.61s
     iterations.....................: 307    3.611675/s
     vus............................: 1      min=1      max=300
     vus_max........................: 300    min=300    max=300

ERRO[0086] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

#### 경로 탐색 조회
- Smoke
<details><summary>성능 개선 전</summary>

```bash

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


running (10.7s), 0/1 VUs, 8 complete and 0 interrupted iterations
default ↓ [======================================] 1 VUs  10s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 경로가 정상적으로 검색됨

     checks.........................: 100.00% ✓ 16       ✗ 0
     data_received..................: 39 kB   3.6 kB/s
     data_sent......................: 1.1 kB  104 B/s
     http_req_blocked...............: avg=1.42ms   min=2.85µs  med=3.09µs  max=22.69ms  p(90)=3.26µs   p(95)=5.67ms
     http_req_connecting............: avg=68.78µs  min=0s      med=0s      max=1.1ms    p(90)=0s       p(95)=275.14µs
   ✗ http_req_duration..............: avg=165.83ms min=15.1ms  med=98.59ms max=615.53ms p(90)=426.89ms p(95)=491.49ms
       { expected_response:true }...: avg=165.83ms min=15.1ms  med=98.59ms max=615.53ms p(90)=426.89ms p(95)=491.49ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 16
     http_req_receiving.............: avg=111.12µs min=80.45µs med=90.2µs  max=344.49µs p(90)=125.35µs p(95)=182.77µs
     http_req_sending...............: avg=96.31µs  min=64.29µs med=90.39µs max=185.48µs p(90)=119.94µs p(95)=137.01µs
     http_req_tls_handshaking.......: avg=1.29ms   min=0s      med=0s      max=20.7ms   p(90)=0s       p(95)=5.17ms
     http_req_waiting...............: avg=165.63ms min=14.92ms med=98.35ms max=615.12ms p(90)=426.72ms p(95)=491.25ms
     http_reqs......................: 16      1.49683/s
     iteration_duration.............: avg=1.33s    min=1.18s   med=1.27s   max=1.66s    p(90)=1.53s    p(95)=1.59s
     iterations.....................: 8       0.748415/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

ERRO[0012] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

- Load
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 1m30s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 1m0s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)

ERRO[0088] TypeError: Cannot read property 'length' of undefined
running at 경로가 정상적으로 검색됨 (file:///app/infra-subway-performance/infra-subway-performance/k6-script/map/load.js:45:56(6))
default at go.k6.io/k6/js/common.Bind.func1 (native)
        at 경로탐색_결과_확인 (file:///app/infra-subway-performance/infra-subway-performance/k6-script/map/load.js:44:39(7))
        at file:///app/infra-subway-performance/infra-subway-performance/k6-script/map/load.js:24:29(15)  executor=ramping-vus scenario=default source=stacktrace

running (1m30.0s), 000/240 VUs, 343 complete and 57 interrupted iterations
default ✓ [======================================] 000/240 VUs  1m0s

     ✓ 메인페이지가 정상적으로 응답함
     ✗ 경로가 정상적으로 검색됨
      ↳  9% — ✓ 32 / ✗ 311

     checks.........................: 58.14% ✓ 432      ✗ 311
     data_received..................: 1.6 MB 18 kB/s
     data_sent......................: 159 kB 1.8 kB/s
     http_req_blocked...............: avg=2.77ms   min=2.28µs  med=3.15µs  max=34.27ms p(90)=9.17ms   p(95)=10.71ms
     http_req_connecting............: avg=725.46µs min=0s      med=0s      max=6.21ms  p(90)=2.51ms   p(95)=3.1ms
   ✗ http_req_duration..............: avg=15.24s   min=11.32ms med=4.83s   max=48.97s  p(90)=32.03s   p(95)=32.72s
       { expected_response:true }...: avg=3.67s    min=11.32ms med=65.86ms max=48.97s  p(90)=7.55s    p(95)=28.04s
     http_req_failed................: 41.85% ✓ 311      ✗ 432
     http_req_receiving.............: avg=602.66µs min=32.21µs med=91.79µs max=21.26ms p(90)=1.07ms   p(95)=3.15ms
     http_req_sending...............: avg=128.7µs  min=35.37µs med=78.98µs max=9.92ms  p(90)=180.23µs p(95)=202.49µs
     http_req_tls_handshaking.......: avg=1.96ms   min=0s      med=0s      max=23.7ms  p(90)=6.39ms   p(95)=7.96ms
     http_req_waiting...............: avg=15.24s   min=10.92ms med=4.82s   max=48.97s  p(90)=32.03s   p(95)=32.72s
     http_reqs......................: 743    8.255277/s
     iteration_duration.............: avg=32.62s   min=16.4s   med=31.34s  max=49.99s  p(90)=38.85s   p(95)=42.1s
     iterations.....................: 343    3.810982/s
     vus............................: 1      min=1      max=240
     vus_max........................: 240    min=240    max=240

ERRO[0091] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>

- Stress
<details><summary>성능 개선 전</summary>

```bash

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m25s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 55s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)

ERRO[0083] TypeError: Cannot read property 'length' of undefined
running at 경로가 정상적으로 검색됨 (file:///app/infra-subway-performance/infra-subway-performance/k6-script/map/stress.js:47:56(6))
default at go.k6.io/k6/js/common.Bind.func1 (native)
        at 경로탐색_결과_확인 (file:///app/infra-subway-performance/infra-subway-performance/k6-script/map/stress.js:46:39(7))
        at file:///app/infra-subway-performance/infra-subway-performance/k6-script/map/stress.js:26:29(15)  executor=ramping-vus scenario=default source=stacktrace

running (1m24.9s), 000/300 VUs, 313 complete and 126 interrupted iterations
default ✓ [======================================] 000/300 VUs  55s

     ✓ 메인페이지가 정상적으로 응답함
     ✗ 경로가 정상적으로 검색됨
      ↳  13% — ✓ 43 / ✗ 270

     checks.........................: 64.09% ✓ 482      ✗ 270
     data_received..................: 1.8 MB 22 kB/s
     data_sent......................: 197 kB 2.3 kB/s
     http_req_blocked...............: avg=3.32ms   min=2.5µs   med=3.17µs  max=28.43ms p(90)=9.52ms   p(95)=10.72ms
     http_req_connecting............: avg=907.2µs  min=0s      med=0s      max=15.15ms p(90)=3.06ms   p(95)=3.14ms
   ✗ http_req_duration..............: avg=14.66s   min=11.1ms  med=6.07s   max=52.29s  p(90)=34.99s   p(95)=36.72s
       { expected_response:true }...: avg=4.86s    min=11.1ms  med=1.41s   max=52.29s  p(90)=8.45s    p(95)=28.12s
     http_req_failed................: 35.90% ✓ 270      ✗ 482
     http_req_receiving.............: avg=282.23µs min=33.04µs med=89.51µs max=13.46ms p(90)=422.95µs p(95)=886.68µs
     http_req_sending...............: avg=173.58µs min=39.9µs  med=93.09µs max=12.51ms p(90)=180.94µs p(95)=209.42µs
     http_req_tls_handshaking.......: avg=2.33ms   min=0s      med=0s      max=24.31ms p(90)=6.66ms   p(95)=7.65ms
     http_req_waiting...............: avg=14.66s   min=10.88ms med=6.07s   max=52.29s  p(90)=34.99s   p(95)=36.72s
     http_reqs......................: 752    8.857125/s
     iteration_duration.............: avg=33.21s   min=1.24s   med=31.24s  max=53.33s  p(90)=40.91s   p(95)=43.55s
     iterations.....................: 313    3.686543/s
     vus............................: 8      min=1      max=300
     vus_max........................: 300    min=300    max=300

ERRO[0086] some thresholds have failed
```

</details>

<details><summary>성능 개선 후</summary>

```bash
```

</details>



### 2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

## 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

