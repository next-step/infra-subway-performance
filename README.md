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

#### main

> smoke

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


running (10.1s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ main page 200

     checks.........................: 100.00% ✓ 10       ✗ 0
     data_received..................: 16 kB   1.6 kB/s
     data_sent......................: 993 B   98 B/s
     http_req_blocked...............: avg=4.52ms   min=2.91µs  med=3µs     max=45.25ms  p(90)=4.52ms   p(95)=24.89ms
     http_req_connecting............: avg=91.41µs  min=0s      med=0s      max=914.12µs p(90)=91.41µs  p(95)=502.76µs
   ✓ http_req_duration..............: avg=4.55ms   min=3.46ms  med=3.72ms  max=7.81ms   p(90)=7.1ms    p(95)=7.45ms
       { expected_response:true }...: avg=4.55ms   min=3.46ms  med=3.72ms  max=7.81ms   p(90)=7.1ms    p(95)=7.45ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=62.41µs  min=50.49µs med=59.76µs max=86.88µs  p(90)=73.69µs  p(95)=80.28µs
     http_req_sending...............: avg=301.29µs min=83.04µs med=87.47µs max=2.21ms   p(90)=307.38µs p(95)=1.26ms
     http_req_tls_handshaking.......: avg=2.72ms   min=0s      med=0s      max=27.29ms  p(90)=2.72ms   p(95)=15.01ms
     http_req_waiting...............: avg=4.18ms   min=3.31ms  med=3.57ms  max=6.86ms   p(90)=5.65ms   p(95)=6.26ms
     http_reqs......................: 10      0.990044/s
     iteration_duration.............: avg=1s       min=1s      med=1s      max=1.05s    p(90)=1.01s    p(95)=1.03s
     iterations.....................: 10      0.990044/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

> load

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 7 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 7 looping VUs for 7m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (7m00.0s), 0/7 VUs, 311767 complete and 0 interrupted iterations
default ✗ [======================================] 0/7 VUs  7m0s

     ✓ main page 200

     checks.........................: 100.00% ✓ 311767     ✗ 0
     data_received..................: 366 MB  871 kB/s
     data_sent......................: 14 MB   32 kB/s
     http_req_blocked...............: avg=11.05µs min=2.29µs   med=2.82µs  max=27.37ms p(90)=3.05µs  p(95)=3.27µs
     http_req_connecting............: avg=737ns   min=0s       med=0s      max=7.38ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=4.78ms  min=789.82µs med=2.89ms  max=35.75ms p(90)=10.91ms p(95)=12.28ms
       { expected_response:true }...: avg=4.78ms  min=789.82µs med=2.89ms  max=35.75ms p(90)=10.91ms p(95)=12.28ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 311767
     http_req_receiving.............: avg=39.89µs min=14.73µs  med=36.74µs max=9.28ms  p(90)=45.89µs p(95)=52.84µs
     http_req_sending...............: avg=46.48µs min=23.12µs  med=43.86µs max=8.29ms  p(90)=57.1µs  p(95)=60.82µs
     http_req_tls_handshaking.......: avg=6.54µs  min=0s       med=0s      max=18.16ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=4.7ms   min=723.88µs med=2.8ms   max=35.48ms p(90)=10.82ms p(95)=12.19ms
     http_reqs......................: 311767  742.296023/s
     iteration_duration.............: avg=4.89ms  min=872.93µs med=2.99ms  max=38.85ms p(90)=11.03ms p(95)=12.4ms
     iterations.....................: 311767  742.296023/s
     vus............................: 1       min=1        max=7
     vus_max........................: 7       min=7        max=7
```

> stress

```
running (6m00.0s), 000/300 VUs, 696822 complete and 0 interrupted iterations
default ✗ [======================================] 000/300 VUs  6m0s

     ✗ main page 200
      ↳  99% — ✓ 696629 / ✗ 193

     checks.........................: 99.97% ✓ 696629      ✗ 193
     data_received..................: 838 MB 2.3 MB/s
     data_sent......................: 33 MB  92 kB/s
     http_req_blocked...............: avg=1.76ms   min=0s       med=2.76µs  max=4.53s   p(90)=2.94µs   p(95)=3.09µs
     http_req_connecting............: avg=2.5µs    min=0s       med=0s      max=33.05ms p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=77.26ms  min=0s       med=40.47ms max=7.52s   p(90)=150ms    p(95)=280.01ms
       { expected_response:true }...: avg=77.21ms  min=782.9µs  med=40.46ms max=7.52s   p(90)=149.78ms p(95)=279.96ms
     http_req_failed................: 0.02%  ✓ 193         ✗ 696629
     http_req_receiving.............: avg=477.58µs min=0s       med=44.42µs max=58.53ms p(90)=890.99µs p(95)=1.93ms
     http_req_sending...............: avg=1.8ms    min=0s       med=38.98µs max=7.46s   p(90)=58.7µs   p(95)=70.47µs
     http_req_tls_handshaking.......: avg=289.82µs min=0s       med=0s      max=4.18s   p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=74.97ms  min=0s       med=39.83ms max=4.31s   p(90)=142.94ms p(95)=273.48ms
     http_reqs......................: 696822 1935.588767/s
     iteration_duration.............: avg=77.66ms  min=864.27µs med=40.6ms  max=7.52s   p(90)=150.62ms p(95)=280.66ms
     iterations.....................: 696822 1935.588767/s
     vus............................: 1      min=1         max=300
     vus_max........................: 300    min=300       max=300
```

#### login

> smoke

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


running (10.6s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ access token created

     checks.........................: 100.00% ✓ 10       ✗ 0
     data_received..................: 8.1 kB  768 B/s
     data_sent......................: 1.9 kB  182 B/s
     http_req_blocked...............: avg=4.19ms   min=2.95µs  med=3.03µs   max=41.96ms  p(90)=4.19ms   p(95)=23.08ms
     http_req_connecting............: avg=63.92µs  min=0s      med=0s       max=639.21µs p(90)=63.92µs  p(95)=351.56µs
   ✗ http_req_duration..............: avg=53.54ms  min=10.81ms med=12.35ms  max=416.45ms p(90)=61.34ms  p(95)=238.9ms
       { expected_response:true }...: avg=53.54ms  min=10.81ms med=12.35ms  max=416.45ms p(90)=61.34ms  p(95)=238.9ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=72.26µs  min=53.65µs med=66.9µs   max=110.85µs p(90)=93.88µs  p(95)=102.36µs
     http_req_sending...............: avg=121.27µs min=92.93µs med=109.57µs max=223.76µs p(90)=156.55µs p(95)=190.15µs
     http_req_tls_handshaking.......: avg=2.52ms   min=0s      med=0s       max=25.28ms  p(90)=2.52ms   p(95)=13.9ms
     http_req_waiting...............: avg=53.35ms  min=10.66ms med=12.18ms  max=416.11ms p(90)=61.1ms   p(95)=238.61ms
     http_reqs......................: 10      0.944286/s
     iteration_duration.............: avg=1.05s    min=1.01s   med=1.01s    max=1.45s    p(90)=1.06s    p(95)=1.26s
     iterations.....................: 10      0.944286/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

> load

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 7 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 7 looping VUs for 7m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (7m00.4s), 0/7 VUs, 1525 complete and 0 interrupted iterations
default ✓ [======================================] 0/7 VUs  7m0s

     ✓ access token created

     checks.........................: 100.00% ✓ 1525     ✗ 0
     data_received..................: 566 kB  1.3 kB/s
     data_sent......................: 209 kB  496 B/s
     http_req_blocked...............: avg=42.53µs  min=2.88µs  med=3.08µs   max=24.53ms  p(90)=3.28µs   p(95)=3.42µs
     http_req_connecting............: avg=2.85µs   min=0s      med=0s       max=899.56µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=4.62ms   min=3.8ms   med=4.43ms   max=18.29ms  p(90)=5.23ms   p(95)=5.85ms
       { expected_response:true }...: avg=4.62ms   min=3.8ms   med=4.43ms   max=18.29ms  p(90)=5.23ms   p(95)=5.85ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 1525
     http_req_receiving.............: avg=53.5µs   min=39.73µs med=52.36µs  max=131.74µs p(90)=60.6µs   p(95)=64.01µs
     http_req_sending...............: avg=106.11µs min=70.77µs med=103.52µs max=3.66ms   p(90)=115.04µs p(95)=120.5µs
     http_req_tls_handshaking.......: avg=30.41µs  min=0s      med=0s       max=16.05ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=4.46ms   min=3.64ms  med=4.27ms   max=18.11ms  p(90)=5.06ms   p(95)=5.68ms
     http_reqs......................: 1525    3.627851/s
     iteration_duration.............: avg=1s       min=1s      med=1s       max=1.03s    p(90)=1s       p(95)=1s
     iterations.....................: 1525    3.627851/s
     vus............................: 1       min=1      max=7
     vus_max........................: 7       min=7      max=7
```

> stress

```

     ✗ access token created
      ↳  99% — ✓ 103763 / ✗ 15

     checks.........................: 99.98% ✓ 103763     ✗ 15
     data_received..................: 231 MB 639 kB/s
     data_sent......................: 40 MB  110 kB/s
     http_req_blocked...............: avg=20.94ms  min=0s      med=2.99µs  max=2.04s    p(90)=55.67ms  p(95)=77.55ms
     http_req_connecting............: avg=6.46ms   min=0s      med=0s      max=2.03s    p(90)=7.52ms   p(95)=12.44ms
   ✓ http_req_duration..............: avg=22.97ms  min=0s      med=10.08ms max=1.11s    p(90)=60.2ms   p(95)=88.2ms
       { expected_response:true }...: avg=22.98ms  min=2.84ms  med=10.08ms max=1.11s    p(90)=60.2ms   p(95)=88.22ms
     http_req_failed................: 0.01%  ✓ 15         ✗ 103763
     http_req_receiving.............: avg=321.56µs min=0s      med=41.59µs max=36.04ms  p(90)=591.98µs p(95)=1.57ms
     http_req_sending...............: avg=468.29µs min=0s      med=73.84µs max=1.07s    p(90)=134.13µs p(95)=160.55µs
     http_req_tls_handshaking.......: avg=14.06ms  min=0s      med=0s      max=353.14ms p(90)=46.53ms  p(95)=66.61ms
     http_req_waiting...............: avg=22.18ms  min=0s      med=9.89ms  max=397.55ms p(90)=58.57ms  p(95)=85.84ms
     http_reqs......................: 103778 287.658867/s
     iteration_duration.............: avg=1.04s    min=60.59ms med=1.01s   max=30.82s   p(90)=1.11s    p(95)=1.15s
     iterations.....................: 103778 287.658867/s
     vus............................: 1      min=1        max=600
     vus_max........................: 600    min=600      max=600
```

#### post

> smoke

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


running (10.6s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ station created

     checks.........................: 100.00% ✓ 10      ✗ 0
     data_received..................: 8.0 kB  750 B/s
     data_sent......................: 1.9 kB  177 B/s
     http_req_blocked...............: avg=2.53ms   min=2.9µs   med=3.06µs   max=25.36ms  p(90)=2.53ms   p(95)=13.95ms
     http_req_connecting............: avg=61.52µs  min=0s      med=0s       max=615.27µs p(90)=61.52µs  p(95)=338.4µs
   ✗ http_req_duration..............: avg=60.5ms   min=9.93ms  med=13.11ms  max=493.34ms p(90)=62.17ms  p(95)=277.75ms
       { expected_response:true }...: avg=60.5ms   min=9.93ms  med=13.11ms  max=493.34ms p(90)=62.17ms  p(95)=277.75ms
     http_req_failed................: 0.00%   ✓ 0       ✗ 10
     http_req_receiving.............: avg=60µs     min=48.75µs med=57.89µs  max=71.76µs  p(90)=71.2µs   p(95)=71.48µs
     http_req_sending...............: avg=119.81µs min=87.58µs med=110.41µs max=220.84µs p(90)=139.63µs p(95)=180.24µs
     http_req_tls_handshaking.......: avg=1.62ms   min=0s      med=0s       max=16.22ms  p(90)=1.62ms   p(95)=8.92ms
     http_req_waiting...............: avg=60.32ms  min=9.76ms  med=12.93ms  max=493.04ms p(90)=61.97ms  p(95)=277.51ms
     http_reqs......................: 10      0.93974/s
     iteration_duration.............: avg=1.06s    min=1.01s   med=1.01s    max=1.51s    p(90)=1.06s    p(95)=1.29s
     iterations.....................: 10      0.93974/s
     vus............................: 1       min=1     max=1
     vus_max........................: 1       min=1     max=1
```

> load

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 7 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 7 looping VUs for 7m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (7m00.0s), 0/7 VUs, 228067 complete and 0 interrupted iterations
default ✗ [======================================] 0/7 VUs  7m0s

     ✓ station created

     checks.........................: 100.00% ✓ 228067     ✗ 0
     data_received..................: 79 MB   187 kB/s
     data_sent......................: 30 MB   71 kB/s
     http_req_blocked...............: avg=7.96µs  min=2.14µs  med=2.79µs  max=26.92ms p(90)=2.95µs  p(95)=3.02µs
     http_req_connecting............: avg=872ns   min=0s      med=0s      max=8.58ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=6.52ms  min=3.51ms  med=5.9ms   max=39.95ms p(90)=8.9ms   p(95)=11.25ms
       { expected_response:true }...: avg=6.52ms  min=3.51ms  med=5.9ms   max=39.95ms p(90)=8.9ms   p(95)=11.25ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 228067
     http_req_receiving.............: avg=41.02µs min=15.98µs med=37.38µs max=4.33ms  p(90)=45.38µs p(95)=50.48µs
     http_req_sending...............: avg=55.97µs min=34.09µs med=53.31µs max=11.24ms p(90)=64.9µs  p(95)=70.78µs
     http_req_tls_handshaking.......: avg=3.96µs  min=0s      med=0s      max=25.05ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=6.42ms  min=3.42ms  med=5.81ms  max=39.85ms p(90)=8.8ms   p(95)=11.15ms
     http_reqs......................: 228067  543.011042/s
     iteration_duration.............: avg=6.69ms  min=3.68ms  med=6.07ms  max=42.37ms p(90)=9.09ms  p(95)=11.45ms
     iterations.....................: 228067  543.011042/s
     vus............................: 1       min=1        max=7
     vus_max........................: 7       min=7        max=7
```

> stress

```
     ✗ station created
      ↳  99% — ✓ 342339 / ✗ 1059

     checks.........................: 99.69% ✓ 342339     ✗ 1059
     data_received..................: 137 MB 381 kB/s
     data_sent......................: 48 MB  133 kB/s
     http_req_blocked...............: avg=117.88µs min=0s     med=2.81µs   max=75.95ms  p(90)=2.98µs   p(95)=3.06µs
     http_req_connecting............: avg=13.43µs  min=0s     med=0s       max=20.58ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=131.1ms  min=0s     med=109.27ms max=5.07s    p(90)=233.01ms p(95)=352.34ms
       { expected_response:true }...: avg=131.44ms min=3.72ms med=109.7ms  max=5.07s    p(90)=233.18ms p(95)=353.09ms
     http_req_failed................: 0.30%  ✓ 1059       ✗ 342339
     http_req_receiving.............: avg=93.43µs  min=0s     med=40.06µs  max=20.89ms  p(90)=72.01µs  p(95)=323.37µs
     http_req_sending...............: avg=235.7µs  min=0s     med=54.02µs  max=185.87ms p(90)=71.3µs   p(95)=81.18µs
     http_req_tls_handshaking.......: avg=52.44µs  min=0s     med=0s       max=60.24ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=130.77ms min=0s     med=108.89ms max=5.07s    p(90)=232.85ms p(95)=351.39ms
     http_reqs......................: 343398 953.866957/s
     iteration_duration.............: avg=131.36ms min=1.46ms med=109.51ms max=5.09s    p(90)=233.2ms  p(95)=352.78ms
     iterations.....................: 343398 953.866957/s
     vus............................: 1      min=1        max=249
     vus_max........................: 250    min=250      max=250
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

#### 웹서버 개선

- 정적파일 gzip 압축
- 정적파일 http cache 설정
- http2.0 tpxld

#### WAS 개선

- Redis 캐싱 적용

#### 프론트엔드 개선

- 웹폰트 swap 적용

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0cc967d93b35352ff

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

![image](https://user-images.githubusercontent.com/37537207/167666040-a2e0aa29-52fd-4ea6-9d1f-a3d983cf2ac7.png)

![image](https://user-images.githubusercontent.com/37537207/167666116-deb3d943-7167-4ca6-b662-7a7db5e5e559.png)


3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

#### main

> smoke

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


running (10.1s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ main page 200

     checks.........................: 100.00% ✓ 10       ✗ 0
     data_received..................: 16 kB   1.6 kB/s
     data_sent......................: 1.0 kB  102 B/s
     http_req_blocked...............: avg=1.96ms  min=2.9µs   med=3.06µs  max=19.65ms  p(90)=1.96ms   p(95)=10.8ms
     http_req_connecting............: avg=69.12µs min=0s      med=0s      max=691.2µs  p(90)=69.12µs  p(95)=380.16µs
   ✓ http_req_duration..............: avg=3.33ms  min=2.77ms  med=3.21ms  max=4.45ms   p(90)=4.03ms   p(95)=4.24ms
       { expected_response:true }...: avg=3.33ms  min=2.77ms  med=3.21ms  max=4.45ms   p(90)=4.03ms   p(95)=4.24ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=65.13µs min=53µs    med=65.24µs max=76.03µs  p(90)=72.51µs  p(95)=74.27µs
     http_req_sending...............: avg=95.18µs min=83.17µs med=86.69µs max=167.05µs p(90)=102.23µs p(95)=134.64µs
     http_req_tls_handshaking.......: avg=1.63ms  min=0s      med=0s      max=16.31ms  p(90)=1.63ms   p(95)=8.97ms
     http_req_waiting...............: avg=3.17ms  min=2.63ms  med=3.05ms  max=4.2ms    p(90)=3.87ms   p(95)=4.04ms
     http_reqs......................: 10      0.993652/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.02s    p(90)=1s       p(95)=1.01s
     iterations.....................: 10      0.993652/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

> load

```
         /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 7 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 7 looping VUs for 7m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (7m00.0s), 0/7 VUs, 661251 complete and 0 interrupted iterations
default ✗ [======================================] 0/7 VUs  7m0s

     ✓ main page 200

     checks.........................: 100.00% ✓ 661251      ✗ 0
     data_received..................: 773 MB  1.8 MB/s
     data_sent......................: 29 MB   68 kB/s
     http_req_blocked...............: avg=3.75µs   min=1.51µs   med=2.8µs   max=30.33ms  p(90)=2.97µs   p(95)=3.07µs
     http_req_connecting............: avg=96ns     min=0s       med=0s      max=2.97ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=2.2ms    min=920.74µs med=2.32ms  max=718.44ms p(90)=2.91ms   p(95)=3.75ms
       { expected_response:true }...: avg=2.2ms    min=920.74µs med=2.32ms  max=718.44ms p(90)=2.91ms   p(95)=3.75ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 661251
     http_req_receiving.............: avg=104.79µs min=17.04µs  med=65.64µs max=16ms     p(90)=184.37µs p(95)=240.85µs
     http_req_sending...............: avg=42.82µs  min=22.84µs  med=39.47µs max=15.37ms  p(90)=54.37µs  p(95)=60.17µs
     http_req_tls_handshaking.......: avg=466ns    min=0s       med=0s      max=15.64ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=2.05ms   min=0s       med=2.2ms   max=718.34ms p(90)=2.75ms   p(95)=3.53ms
     http_reqs......................: 661251  1574.400572/s
     iteration_duration.............: avg=2.3ms    min=994.72µs med=2.41ms  max=718.54ms p(90)=3.01ms   p(95)=3.85ms
     iterations.....................: 661251  1574.400572/s
     vus............................: 1       min=1         max=7
     vus_max........................: 7       min=7         max=7
```

> stress

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 6m30s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 6m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (6m00.0s), 000/300 VUs, 2562578 complete and 0 interrupted iterations
default ✗ [======================================] 000/300 VUs  6m0s

     ✓ main page 200

     checks.........................: 100.00% ✓ 2562578     ✗ 0
     data_received..................: 3.0 GB  8.3 MB/s
     data_sent......................: 111 MB  307 kB/s
     http_req_blocked...............: avg=9.42µs  min=1.3µs    med=2.72µs  max=1.07s    p(90)=2.88µs  p(95)=2.97µs
     http_req_connecting............: avg=1.84µs  min=0s       med=0s      max=1.03s    p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=19.54ms min=604.93µs med=18.22ms max=811.06ms p(90)=32.58ms p(95)=38.61ms
       { expected_response:true }...: avg=19.54ms min=604.93µs med=18.22ms max=811.06ms p(90)=32.58ms p(95)=38.61ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 2562578
     http_req_receiving.............: avg=3.56ms  min=15.2µs   med=2.39ms  max=125.39ms p(90)=7.44ms  p(95)=10.55ms
     http_req_sending...............: avg=60.34µs min=20.67µs  med=30.57µs max=77.8ms   p(90)=47.28µs p(95)=57.04µs
     http_req_tls_handshaking.......: avg=4.07µs  min=0s       med=0s      max=126.41ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=15.91ms min=0s       med=15ms    max=807.07ms p(90)=27.08ms p(95)=31.83ms
     http_reqs......................: 2562578 7118.224481/s
     iteration_duration.............: avg=21.09ms min=667.27µs med=18.43ms max=1.1s     p(90)=33.41ms p(95)=40.88ms
     iterations.....................: 2562578 7118.224481/s
     vus............................: 1       min=1         max=299
     vus_max........................: 300     min=300       max=300
```

#### login

> smoke

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


running (10.1s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ access token created

     checks.........................: 100.00% ✓ 10       ✗ 0
     data_received..................: 7.7 kB  763 B/s
     data_sent......................: 2.0 kB  193 B/s
     http_req_blocked...............: avg=2.51ms   min=2.94µs  med=3.03µs   max=25.13ms  p(90)=2.51ms   p(95)=13.82ms
     http_req_connecting............: avg=35.02µs  min=0s      med=0s       max=350.25µs p(90)=35.02µs  p(95)=192.63µs
   ✓ http_req_duration..............: avg=10.9ms   min=9.83ms  med=10.84ms  max=12.76ms  p(90)=11.81ms  p(95)=12.29ms
       { expected_response:true }...: avg=10.9ms   min=9.83ms  med=10.84ms  max=12.76ms  p(90)=11.81ms  p(95)=12.29ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=316.27µs min=56.25µs med=323.37µs max=548.9µs  p(90)=548.6µs  p(95)=548.75µs
     http_req_sending...............: avg=115.67µs min=97.06µs med=105.29µs max=209.56µs p(90)=126.06µs p(95)=167.81µs
     http_req_tls_handshaking.......: avg=2.33ms   min=0s      med=0s       max=23.38ms  p(90)=2.33ms   p(95)=12.86ms
     http_req_waiting...............: avg=10.47ms  min=9.37ms  med=10.41ms  max=12.35ms  p(90)=11.51ms  p(95)=11.93ms
     http_reqs......................: 10      0.985874/s
     iteration_duration.............: avg=1.01s    min=1.01s   med=1.01s    max=1.03s    p(90)=1.01s    p(95)=1.02s
     iterations.....................: 10      0.985874/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

> load

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 7 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 7 looping VUs for 7m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (7m01.0s), 0/7 VUs, 1520 complete and 0 interrupted iterations
default ✓ [======================================] 0/7 VUs  7m0s

     ✓ access token created

     checks.........................: 100.00% ✓ 1520     ✗ 0
     data_received..................: 501 kB  1.2 kB/s
     data_sent......................: 208 kB  494 B/s
     http_req_blocked...............: avg=41.6µs   min=2.4µs   med=3.06µs   max=30.99ms  p(90)=3.32µs   p(95)=3.47µs
     http_req_connecting............: avg=3.06µs   min=0s      med=0s       max=1.03ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=8.59ms   min=5.28ms  med=7.75ms   max=518.6ms  p(90)=10.4ms   p(95)=11.74ms
       { expected_response:true }...: avg=8.59ms   min=5.28ms  med=7.75ms   max=518.6ms  p(90)=10.4ms   p(95)=11.74ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 1520
     http_req_receiving.............: avg=195.77µs min=27.14µs med=157.93µs max=5.09ms   p(90)=322.52µs p(95)=391.78µs
     http_req_sending...............: avg=100.37µs min=49.07µs med=102.19µs max=288.96µs p(90)=117.19µs p(95)=124.27µs
     http_req_tls_handshaking.......: avg=32.5µs   min=0s      med=0s       max=27.22ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=8.29ms   min=5.14ms  med=7.5ms    max=518.4ms  p(90)=10ms     p(95)=11.25ms
     http_reqs......................: 1520    3.610631/s
     iteration_duration.............: avg=1s       min=1s      med=1s       max=1.51s    p(90)=1.01s    p(95)=1.01s
     iterations.....................: 1520    3.610631/s
     vus............................: 1       min=1      max=7
     vus_max........................: 7       min=7      max=7
```

> stress

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -


running (6m00.5s), 000/600 VUs, 107637 complete and 0 interrupted iterations
default ✓ [======================================] 000/600 VUs  6m0s

     ✓ access token created

     checks.........................: 100.00% ✓ 107637     ✗ 0
     data_received..................: 37 MB   103 kB/s
     data_sent......................: 15 MB   42 kB/s
     http_req_blocked...............: avg=99.47µs  min=1.84µs  med=2.84µs  max=128.02ms p(90)=3.13µs   p(95)=3.45µs
     http_req_connecting............: avg=23.42µs  min=0s      med=0s      max=40.62ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=6.42ms   min=2.35ms  med=4.85ms  max=308.27ms p(90)=9.86ms   p(95)=12.79ms
       { expected_response:true }...: avg=6.42ms   min=2.35ms  med=4.85ms  max=308.27ms p(90)=9.86ms   p(95)=12.79ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 107637
     http_req_receiving.............: avg=226.45µs min=17.07µs med=82.61µs max=50.52ms  p(90)=428.11µs p(95)=679.68µs
     http_req_sending...............: avg=86.6µs   min=29.33µs med=62.83µs max=24.72ms  p(90)=107.67µs p(95)=185.06µs
     http_req_tls_handshaking.......: avg=69.09µs  min=0s      med=0s      max=96.54ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=6.1ms    min=2.25ms  med=4.62ms  max=307.98ms p(90)=9.35ms   p(95)=12.1ms
     http_reqs......................: 107637  298.545256/s
     iteration_duration.............: avg=1s       min=1s      med=1s      max=1.34s    p(90)=1.01s    p(95)=1.01s
     iterations.....................: 107637  298.545256/s
     vus............................: 2       min=2        max=599
     vus_max........................: 600     min=600      max=600
```

#### post

> smoke

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


running (10.3s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ station created

     checks.....................: 100.00% ✓ 10       ✗ 0
     data_received..............: 7.3 kB  709 B/s
     data_sent..................: 1.9 kB  187 B/s
     http_req_blocked...........: avg=2.78ms   min=2.96µs   med=3.08µs   max=27.77ms  p(90)=2.78ms   p(95)=15.27ms
     http_req_connecting........: avg=34.94µs  min=0s       med=0s       max=349.46µs p(90)=34.94µs  p(95)=192.2µs
   ✓ http_req_duration..........: avg=24.58ms  min=12.22ms  med=13.86ms  max=109.67ms p(90)=29.88ms  p(95)=69.78ms
     http_req_failed............: 100.00% ✓ 10       ✗ 0
     http_req_receiving.........: avg=81.74µs  min=61.01µs  med=78.33µs  max=133.89µs p(90)=100.44µs p(95)=117.17µs
     http_req_sending...........: avg=127.15µs min=111.76µs med=116.95µs max=208.74µs p(90)=140.94µs p(95)=174.84µs
     http_req_tls_handshaking...: avg=1.52ms   min=0s       med=0s       max=15.27ms  p(90)=1.52ms   p(95)=8.4ms
     http_req_waiting...........: avg=24.37ms  min=12.03ms  med=13.67ms  max=109.38ms p(90)=29.63ms  p(95)=69.51ms
     http_reqs..................: 10      0.972109/s
     iteration_duration.........: avg=1.02s    min=1.01s    med=1.01s    max=1.13s    p(90)=1.03s    p(95)=1.08s
     iterations.................: 10      0.972109/s
     vus........................: 1       min=1      max=1
     vus_max....................: 1       min=1      max=1
```

> load

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 7 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 7 looping VUs for 7m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (7m00.0s), 0/7 VUs, 183181 complete and 0 interrupted iterations
default ✗ [======================================] 0/7 VUs  7m0s

     ✓ station created

     checks.....................: 100.00% ✓ 183181     ✗ 0
     data_received..............: 48 MB   115 kB/s
     data_sent..................: 24 MB   57 kB/s
     http_req_blocked...........: avg=3.7µs    min=2.01µs  med=2.8µs   max=21.86ms  p(90)=2.97µs   p(95)=3.05µs
     http_req_connecting........: avg=107ns    min=0s      med=0s      max=2.56ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..........: avg=8.16ms   min=3.63ms  med=7.72ms  max=690.72ms p(90)=10.56ms  p(95)=12.78ms
     http_req_failed............: 100.00% ✓ 183181     ✗ 0
     http_req_receiving.........: avg=115.52µs min=19.29µs med=65.83µs max=43.5ms   p(90)=195.29µs p(95)=275.78µs
     http_req_sending...........: avg=60.29µs  min=36.81µs med=57.32µs max=1.89ms   p(90)=72.31µs  p(95)=79.35µs
     http_req_tls_handshaking...: avg=521ns    min=0s      med=0s      max=17.66ms  p(90)=0s       p(95)=0s
     http_req_waiting...........: avg=7.98ms   min=3.5ms   med=7.57ms  max=690.02ms p(90)=10.34ms  p(95)=12.48ms
     http_reqs..................: 183181  436.140992/s
     iteration_duration.........: avg=8.34ms   min=3.79ms  med=7.9ms   max=690.9ms  p(90)=10.75ms  p(95)=12.97ms
     iterations.................: 183181  436.140992/s
     vus........................: 1       min=1        max=7
     vus_max....................: 7       min=7        max=7
```

> stress

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 250 max VUs, 6m30s max duration (incl. graceful stop):
           * default: Up to 250 looping VUs for 6m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (6m00.0s), 000/250 VUs, 619390 complete and 0 interrupted iterations
default ✗ [======================================] 000/250 VUs  6m0s

     ✓ station created

     checks.....................: 100.00% ✓ 619390      ✗ 0
     data_received..............: 164 MB  456 kB/s
     data_sent..................: 81 MB   224 kB/s
     http_req_blocked...........: avg=5.6µs    min=1.85µs  med=2.79µs  max=46.33ms p(90)=2.96µs   p(95)=3.05µs
     http_req_connecting........: avg=482ns    min=0s      med=0s      max=29.49ms p(90)=0s       p(95)=0s
   ✗ http_req_duration..........: avg=72.63ms  min=3.7ms   med=26.63ms max=2.85s   p(90)=193.31ms p(95)=249.21ms
     http_req_failed............: 100.00% ✓ 619390      ✗ 0
     http_req_receiving.........: avg=299.35µs min=17.63µs med=77.05µs max=54.38ms p(90)=513.04µs p(95)=1.09ms
     http_req_sending...........: avg=66.2µs   min=28.81µs med=53.55µs max=34.37ms p(90)=74.5µs   p(95)=84.27µs
     http_req_tls_handshaking...: avg=1.8µs    min=0s      med=0s      max=17.03ms p(90)=0s       p(95)=0s
     http_req_waiting...........: avg=72.26ms  min=3.53ms  med=26.17ms max=2.85s   p(90)=192.91ms p(95)=248.78ms
     http_reqs..................: 619390  1720.494091/s
     iteration_duration.........: avg=72.81ms  min=3.84ms  med=26.82ms max=2.85s   p(90)=193.5ms  p(95)=249.4ms
     iterations.................: 619390  1720.494091/s
     vus........................: 1       min=1         max=250
     vus_max....................: 250     min=250       max=250
```

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```sql
SELECT STRAIGHT_JOIN
	tmp.employee_id
    , tmp.last_name
    , tmp.annual_income
	, tmp.position_name
    , tmp2.r_time
    , tmp2.region
    , tmp2.record_symbol
FROM
	(
	SELECT STRAIGHT_JOIN
		r.employee_id
        , r.region
        , MAX(r.time) r_time
        , r.record_symbol
    FROM
		(
		SELECT
			m.employee_id
		FROM
			tuning.manager m
			INNER JOIN tuning.department d ON m.department_id = d.id
			INNER JOIN tuning.salary s ON m.employee_id = s.id
		WHERE
			m.end_date = "9999-01-01"
			AND s.end_date = "9999-01-01"
			AND LOWER(d.note) = "active"
		ORDER BY
			s.annual_income DESC
		LIMIT 5
		) tmp3
		INNER JOIN tuning.record r ON r.employee_id = tmp3.employee_id
	WHERE
		r.record_symbol = "O"
	GROUP BY
		r.employee_id, r.region, r.record_symbol
    ) tmp2,
	(
	SELECT
		m.employee_id
        , e.last_name
        , s.annual_income
        , p.position_name
	FROM
		tuning.manager m
		INNER JOIN tuning.department d ON m.department_id = d.id
		INNER JOIN tuning.salary s ON m.employee_id = s.id
        INNER JOIN tuning.employee e ON m.employee_id = e.id
        INNER JOIN tuning.position p ON p.id = m.employee_id
	WHERE
		m.end_date = "9999-01-01"
		AND s.end_date = "9999-01-01"
		AND LOWER(d.note) = "active"
        AND p.end_date = "9999-01-01"
	ORDER BY
		s.annual_income DESC
	LIMIT 5
	) tmp
WHERE
	 tmp2.employee_id = tmp.employee_id
ORDER BY
	tmp.annual_income DESC
```

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- Coding as a Hobby 와 같은 결과를 반환하세요.

```sql
SELECT
	hobby, COUNT(1) / (SELECT COUNT(1) FROM subway.programmer) as percentage
FROM
	subway.programmer p
GROUP BY
	p.hobby
```

> hobby 에 인덱스 생성해서 인덱스 갯수만으로 count 하도록 함, 다만 분모가 되는 서브쿼리 때문에 시간이 아슬아슬하게 100ms 정도로 나옴

- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
