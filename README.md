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


running (10.5s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 지하철역이 정상적으로 조회됨

     checks.........................: 100.00% ✓ 20       ✗ 0
     data_received..................: 740 kB  71 kB/s
     data_sent......................: 2.5 kB  239 B/s
     http_req_blocked...............: avg=3.19ms   min=2.92µs  med=3.03µs  max=63.75ms  p(90)=3.1µs    p(95)=3.19ms
     http_req_connecting............: avg=102.51µs min=0s      med=0s      max=2.05ms   p(90)=0s       p(95)=102.51µ
   ✓ http_req_duration..............: avg=20.19ms  min=12.99ms med=18.95ms max=37.4ms   p(90)=25.79ms  p(95)=33.6ms
       { expected_response:true }...: avg=20.19ms  min=12.99ms med=18.95ms max=37.4ms   p(90)=25.79ms  p(95)=33.6ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 20
     http_req_receiving.............: avg=831.09µs min=72.74µs med=193.3µs max=5.91ms   p(90)=1.72ms   p(95)=3.25ms
     http_req_sending...............: avg=95.09µs  min=47.44µs med=89.17µs max=210.46µs p(90)=136.63µs p(95)=142.32µ
     http_req_tls_handshaking.......: avg=984.45µs min=0s      med=0s      max=19.68ms  p(90)=0s       p(95)=984.45µ
     http_req_waiting...............: avg=19.27ms  min=12.83ms med=17.61ms max=37.1ms   p(90)=25.35ms  p(95)=32.22ms
     http_reqs......................: 20      1.905912/s
     iteration_duration.............: avg=1.04s    min=1.03s   med=1.03s   max=1.13s    p(90)=1.05s    p(95)=1.09s
     iterations.....................: 10      0.952956/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
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


running (1m00.7s), 000/240 VUs, 7824 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  1m0s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 지하철역이 정상적으로 조회됨

     checks.........................: 100.00% ✓ 15648      ✗ 0
     data_received..................: 577 MB  9.5 MB/s
     data_sent......................: 1.4 MB  23 kB/s
     http_req_blocked...............: avg=198.72µs min=2.39µs  med=2.84µs   max=66.91ms  p(90)=3.01µs  p(95)=3.12µs
     http_req_connecting............: avg=67.59µs  min=0s      med=0s       max=32.06ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=24.31ms  min=9.48ms  med=19.87ms  max=1.55s    p(90)=33.75ms p(95)=41.01ms
       { expected_response:true }...: avg=24.31ms  min=9.48ms  med=19.87ms  max=1.55s    p(90)=33.75ms p(95)=41.01ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 15648
     http_req_receiving.............: avg=1.61ms   min=28.06µs med=215.94µs max=711.58ms p(90)=3.29ms  p(95)=5.66ms
     http_req_sending...............: avg=77.43µs  min=34.87µs med=54.86µs  max=24.94ms  p(90)=82.73µs p(95)=116.75µ
     http_req_tls_handshaking.......: avg=124.8µs  min=0s      med=0s       max=43ms     p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=22.62ms  min=9.24ms  med=18.81ms  max=1.54s    p(90)=31.62ms p(95)=37.62ms
     http_reqs......................: 15648   257.910438/s
     iteration_duration.............: avg=1.05s    min=1.02s   med=1.04s    max=2.57s    p(90)=1.06s   p(95)=1.08s
     iterations.....................: 7824    128.955219/s
     vus............................: 13      min=1        max=239
     vus_max........................: 240     min=240      max=240
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


running (0m56.0s), 000/300 VUs, 7741 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  55s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 지하철역이 정상적으로 조회됨

     checks.........................: 100.00% ✓ 15482      ✗ 0
     data_received..................: 571 MB  10 MB/s
     data_sent......................: 1.4 MB  25 kB/s
     http_req_blocked...............: avg=236.08µs min=2.46µs  med=2.85µs  max=57.46ms  p(90)=3.03µs  p(95)=3.15µs
     http_req_connecting............: avg=80.05µs  min=0s      med=0s      max=32.82ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=21.52ms  min=9.12ms  med=19.18ms max=404.27ms p(90)=30.18ms p(95)=36.58ms
       { expected_response:true }...: avg=21.52ms  min=9.12ms  med=19.18ms max=404.27ms p(90)=30.18ms p(95)=36.58ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 15482
     http_req_receiving.............: avg=1.13ms   min=25.02µs med=214µs   max=79.14ms  p(90)=3.08ms  p(95)=5.04ms
     http_req_sending...............: avg=76.13µs  min=32.97µs med=54.99µs max=21.09ms  p(90)=83.75µs p(95)=121.19µs
     http_req_tls_handshaking.......: avg=147.93µs min=0s      med=0s      max=39.79ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=20.31ms  min=9.02ms  med=18.15ms max=400.87ms p(90)=28.02ms p(95)=33.38ms
     http_reqs......................: 15482   276.431613/s
     iteration_duration.............: avg=1.04s    min=1.02s   med=1.04s   max=1.43s    p(90)=1.06s   p(95)=1.07s
     iterations.....................: 7741    138.215806/s
     vus............................: 1       min=1        max=299
     vus_max........................: 300     min=300      max=300
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


running (10.4s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 경로가 정상적으로 검색됨

     checks.........................: 100.00% ✓ 20       ✗ 0
     data_received..................: 48 kB   4.6 kB/s
     data_sent......................: 1.3 kB  121 B/s
     http_req_blocked...............: avg=1.13ms   min=2.76µs  med=3.02µs  max=22.67ms  p(90)=3.27µs   p(95)=1.13ms
     http_req_connecting............: avg=126.01µs min=0s      med=0s      max=2.52ms   p(90)=0s       p(95)=126.01µ
   ✓ http_req_duration..............: avg=20.28ms  min=14.71ms med=19.01ms max=39.22ms  p(90)=23.99ms  p(95)=24.94ms
       { expected_response:true }...: avg=20.28ms  min=14.71ms med=19.01ms max=39.22ms  p(90)=23.99ms  p(95)=24.94ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 20
     http_req_receiving.............: avg=82.54µs  min=57.93µs med=77.46µs max=140.38µs p(90)=98.01µs  p(95)=104.59µ
     http_req_sending...............: avg=89.56µs  min=58.6µs  med=84.39µs max=185.37µs p(90)=108.46µs p(95)=120.5µs
     http_req_tls_handshaking.......: avg=966.28µs min=0s      med=0s      max=19.32ms  p(90)=0s       p(95)=966.28µ
     http_req_waiting...............: avg=20.11ms  min=14.55ms med=18.83ms max=38.9ms   p(90)=23.84ms  p(95)=24.8ms
     http_reqs......................: 20      1.915227/s
     iteration_duration.............: avg=1.04s    min=1.03s   med=1.04s   max=1.08s    p(90)=1.04s    p(95)=1.06s
     iterations.....................: 10      0.957613/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
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


running (1m00.9s), 000/240 VUs, 7936 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  1m0s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 경로가 정상적으로 검색됨

     checks.........................: 100.00% ✓ 15872      ✗ 0
     data_received..................: 36 MB   591 kB/s
     data_sent......................: 701 kB  12 kB/s
     http_req_blocked...............: avg=180.48µs min=2.12µs  med=2.84µs  max=46.96ms  p(90)=2.99µs  p(95)=3.09µs
     http_req_connecting............: avg=57.26µs  min=0s      med=0s      max=21.09ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=18.15ms  min=9.66ms  med=16.49ms max=629.72ms p(90)=24.79ms p(95)=28.64ms
       { expected_response:true }...: avg=18.15ms  min=9.66ms  med=16.49ms max=629.72ms p(90)=24.79ms p(95)=28.64ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 15872
     http_req_receiving.............: avg=68.76µs  min=25.91µs med=50.91µs max=19.22ms  p(90)=74.46µs p(95)=97.52µs
     http_req_sending...............: avg=60.97µs  min=35.79µs med=54.84µs max=3.1ms    p(90)=72.99µs p(95)=85.1µs
     http_req_tls_handshaking.......: avg=116.59µs min=0s      med=0s      max=32.36ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=18.02ms  min=9.55ms  med=16.36ms max=629.62ms p(90)=24.67ms p(95)=28.52ms
     http_reqs......................: 15872   260.601696/s
     iteration_duration.............: avg=1.03s    min=1.02s   med=1.03s   max=1.68s    p(90)=1.04s   p(95)=1.05s
     iterations.....................: 7936    130.300848/s
     vus............................: 9       min=1        max=240
     vus_max........................: 240     min=240      max=240

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


running (0m56.0s), 000/300 VUs, 7801 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  55s

     ✓ 메인페이지가 정상적으로 응답함
     ✓ 경로가 정상적으로 검색됨

     checks.........................: 100.00% ✓ 15602      ✗ 0
     data_received..................: 36 MB   636 kB/s
     data_sent......................: 723 kB  13 kB/s
     http_req_blocked...............: avg=224.69µs min=2.51µs  med=2.85µs  max=53.54ms  p(90)=3µs     p(95)=3.12µs
     http_req_connecting............: avg=74.68µs  min=0s      med=0s      max=20.08ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=18.56ms  min=9.58ms  med=16.77ms max=179.2ms  p(90)=25.8ms  p(95)=30.38ms
       { expected_response:true }...: avg=18.56ms  min=9.58ms  med=16.77ms max=179.2ms  p(90)=25.8ms  p(95)=30.38ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 15602
     http_req_receiving.............: avg=73.24µs  min=26.77µs med=51.63µs max=19.46ms  p(90)=77.5µs  p(95)=102.23µs
     http_req_sending...............: avg=62.03µs  min=34.48µs med=54.98µs max=4.01ms   p(90)=76.68µs p(95)=92.66µs
     http_req_tls_handshaking.......: avg=142.31µs min=0s      med=0s      max=34.37ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=18.43ms  min=9.43ms  med=16.64ms max=179.09ms p(90)=25.65ms p(95)=30.18ms
     http_reqs......................: 15602   278.490157/s
     iteration_duration.............: avg=1.03s    min=1.02s   med=1.03s   max=1.23s    p(90)=1.05s   p(95)=1.05s
     iterations.....................: 7801    139.245078/s
     vus............................: 1       min=1        max=300
     vus_max........................: 300     min=300      max=300

```

</details>



### 2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- 개선전 Front Application 서버의 CPU자원이 남음  
  => 다수의 Front Application 서버를 구동함

- 불필요한 리소스사용 제거  
  => Front Application 서버 가용성 증가를 위해 개선전 K6 부하테스트 실행을 다른 서버에서 실행
  
- 캐쉬사용  
  => Redis를 사용하여 반복 호출되는 자료를 메모리에서 바로 호출하도록 변경

---

## 2단계 - 조회 성능 개선하기
1. 쿼리 최적화진행 과정 공유
<details><summary>조회 쿼리</summary>

```sql
select 연봉_top5_사원.사원번호, 연봉_top5_사원.이름, 연봉_top5_사원.연봉, 직급.직급명, 사원출입기록.입출입시간,사원출입기록.지역, 사원출입기록.입출입구분
from
(
	select 
	사원.사원번호, 사원.이름, 급여.연봉
	from 
		(
			select 사원번호, 이름
			from 사원
		) 사원,
		(
			select 부서번호
			from 부서
			where 비고 = 'active'
		) 부서,
		(
		select 사원번호, 부서번호
		from 부서관리자
		where 
		now() BETWEEN  시작일자 and 종료일자
		) 부서관리자,
		(
			select 사원번호, 연봉
			from 급여
			where now() BETWEEN 시작일자 and 종료일자
		) 급여,
		(
			select 사원번호, 부서번호
			from 부서사원_매핑
			where now() BETWEEN 시작일자 and 종료일자
		) 부서사원_매핑	
	WHERE 사원.사원번호 = 부서사원_매핑.사원번호
	and 사원.사원번호 = 부서관리자.사원번호
	and 사원.사원번호 = 급여.사원번호
	and 부서사원_매핑.부서번호 = 부서.부서번호
	order by 급여.연봉 desc limit 5
) 연봉_top5_사원,
(
	select 사원번호, 직급명
	from 직급
	where now() BETWEEN 시작일자 and 종료일자
) 직급,
(
	select 사원번호, 입출입시간, 지역, 입출입구분
	from 사원출입기록
	where 입출입구분 = 'O'
) 사원출입기록
WHERE 연봉_top5_사원.사원번호 = 직급.사원번호
	and 연봉_top5_사원.사원번호 = 사원출입기록.사원번호
order by 연봉 desc, 지역
```

</details>

<details><summary>튜닝 전 쿼리 조회</summary>

![queryResultBeforeTurning](https://raw.githubusercontent.com/LuneChaser/infra-subway-performance/step2/step2Docs/queryResultBeforeTurning.JPG)

</details>

<details><summary>튜닝 전 Plan</summary>

![planBeforeTurning](https://raw.githubusercontent.com/LuneChaser/infra-subway-performance/step2/step2Docs/planBeforeTurning.JPG)

</details>

<details><summary>Index 추가로 튜닝</summary>

![addIndex](https://raw.githubusercontent.com/LuneChaser/infra-subway-performance/step2/step2Docs/addIndex.JPG)

</details>

</details>

<details><summary>튜닝 후 쿼리 조회</summary>

![queryResultAfterTurning](https://raw.githubusercontent.com/LuneChaser/infra-subway-performance/step2/step2Docs/queryResultAfterTurning.JPG)

</details>

<details><summary>튜닝 후 Plan</summary>

![planAfterTurning](https://raw.githubusercontent.com/LuneChaser/infra-subway-performance/step2/step2Docs/planAfterTurning.JPG)

</details>

위 과정으로 튜닝전 `181ms => 3ms`으로 쿼리 조회 성능을 최적화함

2. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

3. 페이징 쿼리를 적용한 API endpoint를 알려주세요

4. MySQL Replication With JPA 진행 과정 공유