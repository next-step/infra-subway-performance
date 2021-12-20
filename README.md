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

#### [성능 개선 전] Smoke, Load, Stress 테스트 결과
#### 1. 메인 페이지
<details><summary>smoke test 스크립트 실행 결과 보기</summary>

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


running (10.0s), 0/1 VUs, 3248 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ 메인페이지 응답 결과 ===>

     checks.........................: 100.00% ✓ 3248       ✗ 0
     data_received..................: 4.1 MB  410 kB/s
     data_sent......................: 395 kB  40 kB/s
     http_req_blocked...............: avg=18.05µs min=3.51µs  med=4.28µs  max=30.05ms  p(90)=4.77µs  p(95)=5.3µs
     http_req_connecting............: avg=1µs     min=0s      med=0s      max=1.27ms   p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=2.94ms  min=1.96ms  med=2.9ms   max=12.86ms  p(90)=3.61ms  p(95)=3.98ms
{ expected_response:true }...: avg=2.94ms  min=1.96ms  med=2.9ms   max=12.86ms  p(90)=3.61ms  p(95)=3.98ms
http_req_failed................: 0.00%   ✓ 0          ✗ 3248
http_req_receiving.............: avg=54.96µs min=31.73µs med=49.75µs max=1.62ms   p(90)=72.36µs p(95)=91.19µs
http_req_sending...............: avg=13.43µs min=9.27µs  med=11.98µs max=378.23µs p(90)=14.93µs p(95)=21.24µs
http_req_tls_handshaking.......: avg=8.71µs  min=0s      med=0s      max=16.98ms  p(90)=0s      p(95)=0s
http_req_waiting...............: avg=2.87ms  min=1.89ms  med=2.83ms  max=12.79ms  p(90)=3.55ms  p(95)=3.91ms
http_reqs......................: 3248    324.754115/s
iteration_duration.............: avg=3.06ms  min=2.07ms  med=3ms     max=37.38ms  p(90)=3.72ms  p(95)=4.09ms
iterations.....................: 3248    324.754115/s
vus............................: 1       min=1        max=1
vus_max........................: 1       min=1        max=1

```
</details>

<details><summary>load test 스크립트 실행 결과 보기</summary>

```bash
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 50 max VUs, 1m25s max duration (incl. graceful stop):
           * default: Up to 50 looping VUs for 55s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m55.0s), 00/50 VUs, 62124 complete and 0 interrupted iterations
default ✓ [======================================] 00/50 VUs  55s

     ✓ 메인페이지 응답 결과 ===>

     checks.........................: 100.00% ✓ 62124       ✗ 0
     data_received..................: 79 MB   1.4 MB/s
     data_sent......................: 7.6 MB  137 kB/s
     http_req_blocked...............: avg=52.3µs   min=2.62µs  med=4.08µs  max=96.95ms  p(90)=5.37µs   p(95)=9.77µs
     http_req_connecting............: avg=4.12µs   min=0s      med=0s      max=19.94ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=15.78ms  min=1.6ms   med=7.38ms  max=106.69ms p(90)=45.39ms  p(95)=55.61ms
       { expected_response:true }...: avg=15.78ms  min=1.6ms   med=7.38ms  max=106.69ms p(90)=45.39ms  p(95)=55.61ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 62124
     http_req_receiving.............: avg=117.56µs min=19.77µs med=38.73µs max=54.52ms  p(90)=101.88µs p(95)=253.12µs
     http_req_sending...............: avg=46.28µs  min=7.43µs  med=12.17µs max=24.4ms   p(90)=29.1µs   p(95)=54.99µs
     http_req_tls_handshaking.......: avg=33.15µs  min=0s      med=0s      max=82.55ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=15.61ms  min=1.55ms  med=7.2ms   max=106.64ms p(90)=45.24ms  p(95)=55.46ms
     http_reqs......................: 62124   1129.423893/s
     iteration_duration.............: avg=16.08ms  min=1.7ms   med=7.71ms  max=180.67ms p(90)=45.71ms  p(95)=55.92ms
     iterations.....................: 62124   1129.423893/s
     vus............................: 1       min=1         max=49
     vus_max........................: 50      min=50        max=50

```
</details>

<details><summary>stress test 스크립트 실행 결과 보기</summary>

```bash
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 50 max VUs, 2m35s max duration (incl. graceful stop):
           * default: Up to 50 looping VUs for 2m5s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m05.0s), 00/50 VUs, 170643 complete and 0 interrupted iterations
default ✓ [======================================] 00/50 VUs  2m5s

     ✓ 메인페이지 응답 결과 ===>

     checks.........................: 100.00% ✓ 170643      ✗ 0
     data_received..................: 215 MB  1.7 MB/s
     data_sent......................: 21 MB   166 kB/s
     http_req_blocked...............: avg=50.91µs  min=2.42µs  med=4µs     max=141.96ms p(90)=5.46µs   p(95)=14.63µs
     http_req_connecting............: avg=4.69µs   min=0s      med=0s      max=67.22ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=20.61ms  min=1.5ms   med=14.71ms max=151.22ms p(90)=49.49ms  p(95)=60.14ms
       { expected_response:true }...: avg=20.61ms  min=1.5ms   med=14.71ms max=151.22ms p(90)=49.49ms  p(95)=60.14ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 170643
     http_req_receiving.............: avg=129.66µs min=18.74µs med=36.5µs  max=69.94ms  p(90)=117.22µs p(95)=274.15µs
     http_req_sending...............: avg=50.25µs  min=7.11µs  med=12.01µs max=58.05ms  p(90)=34.86µs  p(95)=66.19µs
     http_req_tls_handshaking.......: avg=31.93µs  min=0s      med=0s      max=140.44ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=20.43ms  min=1.41ms  med=14.52ms max=142.91ms p(90)=49.24ms  p(95)=59.93ms
     http_reqs......................: 170643  1365.079046/s
     iteration_duration.............: avg=20.92ms  min=1.6ms   med=15.05ms max=224.7ms  p(90)=49.9ms   p(95)=60.55ms
     iterations.....................: 170643  1365.079046/s
     vus............................: 1       min=1         max=50
     vus_max........................: 50      min=50        max=50

```
</details>

#### 2. 회원 정보 수정 - 나이 수정
<details><summary>smoke test 스크립트 실행 결과 보기</summary>

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


running (10.0s), 0/1 VUs, 651 complete and 0 interrupted iterations
default ↓ [======================================] 1 VUs  10s

     ✓ 메인페이지 응답 결과 ===>
     ✓ 로그인 요청 응답 결과 ===>
     ✓ 로그인 성공
     ✓ 로그인한 유저 나이 변경 요청 응답 결과 ===>

     checks.........................: 100.00% ✓ 2604      ✗ 0
     data_received..................: 1.2 MB  118 kB/s
     data_sent......................: 510 kB  51 kB/s
     http_req_blocked...............: avg=20.25µs min=4.02µs  med=4.54µs  max=25.53ms  p(90)=5.25µs   p(95)=6.18µs
     http_req_connecting............: avg=450ns   min=0s      med=0s      max=445.81µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=4.94ms  min=1.59ms  med=5.22ms  max=155.98ms p(90)=7.4ms    p(95)=8.3ms
       { expected_response:true }...: avg=4.94ms  min=1.59ms  med=5.22ms  max=155.98ms p(90)=7.4ms    p(95)=8.3ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 1953
     http_req_receiving.............: avg=61.4µs  min=28.87µs med=49.97µs max=233.69µs p(90)=101.13µs p(95)=114.22µs
     http_req_sending...............: avg=18.92µs min=11.52µs med=16µs    max=2.53ms   p(90)=21.33µs  p(95)=28.66µs
     http_req_tls_handshaking.......: avg=9.91µs  min=0s      med=0s      max=15.63ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=4.85ms  min=1.51ms  med=5.14ms  max=155.84ms p(90)=7.33ms   p(95)=8.23ms
     http_reqs......................: 1953    195.16203/s
     iteration_duration.............: avg=15.35ms min=12.1ms  med=14.44ms max=230.47ms p(90)=17.62ms  p(95)=18.71ms
     iterations.....................: 651     65.05401/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1

```
</details>
<details><summary>load test 스크립트 실행 결과 보기</summary>

```bash
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 50 max VUs, 1m25s max duration (incl. graceful stop):
           * default: Up to 50 looping VUs for 55s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m55.0s), 00/50 VUs, 18313 complete and 0 interrupted iterations
default ✓ [======================================] 00/50 VUs  55s

     ✓ 메인페이지 응답 결과 ===>
     ✓ 로그인 요청 응답 결과 ===>
     ✓ 로그인 성공
     ✓ 로그인한 유저 나이 변경 요청 응답 결과 ===>

     checks.........................: 100.00% ✓ 73252      ✗ 0
     data_received..................: 33 MB   604 kB/s
     data_sent......................: 14 MB   261 kB/s
     http_req_blocked...............: avg=39.79µs  min=3.15µs  med=4.36µs  max=84.56ms  p(90)=5.45µs   p(95)=8.66µs
     http_req_connecting............: avg=5.65µs   min=0s      med=0s      max=19.91ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=17.81ms  min=1.51ms  med=12.48ms max=510.95ms p(90)=39.21ms  p(95)=49.73ms
       { expected_response:true }...: avg=17.81ms  min=1.51ms  med=12.48ms max=510.95ms p(90)=39.21ms  p(95)=49.73ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 54939
     http_req_receiving.............: avg=121.84µs min=15.73µs med=37.33µs max=45.88ms  p(90)=92.38µs  p(95)=285.63µs
     http_req_sending...............: avg=69.67µs  min=8.23µs  med=15.41µs max=40.82ms  p(90)=28.06µs  p(95)=78.23µs
     http_req_tls_handshaking.......: avg=17.7µs   min=0s      med=0s      max=64.76ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=17.62ms  min=1.44ms  med=12.3ms  max=510.86ms p(90)=38.88ms  p(95)=49.37ms
     http_reqs......................: 54939   998.800174/s
     iteration_duration.............: avg=54.66ms  min=10.93ms med=43.47ms max=615.79ms p(90)=107.12ms p(95)=127.8ms
     iterations.....................: 18313   332.933391/s
     vus............................: 1       min=1        max=50
     vus_max........................: 50      min=50       max=50

```
</details>

<details><summary>stress test 스크립트 실행 결과 보기</summary>

```bash
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 50 max VUs, 2m35s max duration (incl. graceful stop):
           * default: Up to 50 looping VUs for 2m5s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m05.0s), 00/50 VUs, 51745 complete and 0 interrupted iterations
default ✓ [======================================] 00/50 VUs  2m5s

     ✓ 메인페이지 응답 결과 ===>
     ✓ 로그인 요청 응답 결과 ===>
     ✓ 로그인 성공
     ✓ 로그인한 유저 나이 변경 요청 응답 결과 ===>

     checks.........................: 100.00% ✓ 206980      ✗ 0
     data_received..................: 94 MB   749 kB/s
     data_sent......................: 41 MB   324 kB/s
     http_req_blocked...............: avg=44.52µs  min=2.66µs  med=4.29µs  max=69.12ms  p(90)=5.72µs   p(95)=17.84µs
     http_req_connecting............: avg=3.99µs   min=0s      med=0s      max=21.63ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=22.55ms  min=1.55ms  med=17.04ms max=156.02ms p(90)=48.28ms  p(95)=58.43ms
       { expected_response:true }...: avg=22.55ms  min=1.55ms  med=17.04ms max=156.02ms p(90)=48.28ms  p(95)=58.43ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 155235
     http_req_receiving.............: avg=136.66µs min=14.96µs med=35.81µs max=48.79ms  p(90)=164.46µs p(95)=435.56µs
     http_req_sending...............: avg=73.44µs  min=8.23µs  med=15.69µs max=43.28ms  p(90)=44.89µs  p(95)=147.19µs
     http_req_tls_handshaking.......: avg=22.16µs  min=0s      med=0s      max=62.87ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=22.34ms  min=1.5ms   med=16.81ms max=155.97ms p(90)=48ms     p(95)=58.17ms
     http_reqs......................: 155235  1241.692094/s
     iteration_duration.............: avg=69.09ms  min=11.91ms med=55.76ms max=286.63ms p(90)=138.94ms p(95)=162.8ms
     iterations.....................: 51745   413.897365/s
     vus............................: 1       min=1         max=50
     vus_max........................: 50      min=50        max=50

```
</details>

#### 3. 경로(Map) 조회
<details><summary>smoke test 스크립트 실행 결과 보기</summary>

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


running (10.0s), 0/1 VUs, 80 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ 메인페이지 응답 결과 ===>
     ✓ 최단경로 조회 응답 결과 ===>

     checks.........................: 100.00% ✓ 160       ✗ 0
     data_received..................: 145 kB  15 kB/s
     data_sent......................: 22 kB   2.2 kB/s
     http_req_blocked...............: avg=179.92µs min=4.09µs  med=5.49µs   max=27.9ms   p(90)=6.15µs   p(95)=7.23µs
     http_req_connecting............: avg=8.44µs   min=0s      med=0s       max=1.35ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=62.21ms  min=2.58ms  med=52.8ms   max=427.31ms p(90)=127.57ms p(95)=145.39ms
       { expected_response:true }...: avg=62.21ms  min=2.58ms  med=52.8ms   max=427.31ms p(90)=127.57ms p(95)=145.39ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 160
     http_req_receiving.............: avg=81.89µs  min=48.41µs med=85.91µs  max=208.43µs p(90)=101.7µs  p(95)=105.62µs
     http_req_sending...............: avg=18.34µs  min=11.36µs med=17.08µs  max=57.71µs  p(90)=26.98µs  p(95)=29.04µs
     http_req_tls_handshaking.......: avg=111.22µs min=0s      med=0s       max=17.79ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=62.11ms  min=2.5ms   med=52.7ms   max=427.2ms  p(90)=127.45ms p(95)=145.27ms
     http_reqs......................: 160     15.979655/s
     iteration_duration.............: avg=125.12ms min=105.3ms med=114.86ms max=458.64ms p(90)=148.77ms p(95)=160.4ms
     iterations.....................: 80      7.989827/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1

```
</details>
<details><summary>load test 스크립트 실행 결과 보기</summary>

```bash
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 50 max VUs, 1m25s max duration (incl. graceful stop):
           * default: Up to 50 looping VUs for 55s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m55.0s), 00/50 VUs, 1539 complete and 0 interrupted iterations
default ✓ [======================================] 00/50 VUs  55s

     ✓ 메인페이지 응답 결과 ===>
     ✓ 최단경로 조회 응답 결과 ===>

     checks.........................: 100.00% ✓ 3078      ✗ 0
     data_received..................: 2.9 MB  53 kB/s
     data_sent......................: 433 kB  7.9 kB/s
     http_req_blocked...............: avg=96.88µs  min=3.47µs  med=4.65µs   max=19.5ms  p(90)=6.01µs   p(95)=7.65µs
     http_req_connecting............: avg=14.72µs  min=0s      med=0s       max=1.68ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=331.65ms min=1.53ms  med=44.69ms  max=1.71s   p(90)=1.11s    p(95)=1.27s
       { expected_response:true }...: avg=331.65ms min=1.53ms  med=44.69ms  max=1.71s   p(90)=1.11s    p(95)=1.27s
     http_req_failed................: 0.00%   ✓ 0         ✗ 3078
     http_req_receiving.............: avg=72.93µs  min=25.75µs med=61.92µs  max=3.95ms  p(90)=104.56µs p(95)=125.71µs
     http_req_sending...............: avg=18.88µs  min=9.31µs  med=14.26µs  max=4.1ms   p(90)=20.06µs  p(95)=29.12µs
     http_req_tls_handshaking.......: avg=74.93µs  min=0s      med=0s       max=17.36ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=331.56ms min=1.48ms  med=44.59ms  max=1.71s   p(90)=1.11s    p(95)=1.27s
     http_reqs......................: 3078    55.920192/s
     iteration_duration.............: avg=663.85ms min=75.09ms med=636.03ms max=1.71s   p(90)=1.27s    p(95)=1.38s
     iterations.....................: 1539    27.960096/s
     vus............................: 1       min=1       max=50
     vus_max........................: 50      min=50      max=50

```
</details>

<details><summary>stress test 스크립트 실행 결과 보기</summary>

```bash
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 150 max VUs, 1m50s max duration (incl. graceful stop):
           * default: Up to 150 looping VUs for 1m20s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m20.5s), 000/150 VUs, 2649 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m20s

     ✓ 메인페이지 응답 결과 ===>
     ✓ 최단경로 조회 응답 결과 ===>

     checks.........................: 100.00% ✓ 5298      ✗ 0
     data_received..................: 5.3 MB  66 kB/s
     data_sent......................: 771 kB  9.6 kB/s
     http_req_blocked...............: avg=175.78µs min=3.22µs  med=4.56µs  max=37.93ms p(90)=6.04µs  p(95)=9.77µs
     http_req_connecting............: avg=27.71µs  min=0s      med=0s      max=2.86ms  p(90)=0s      p(95)=0s
   ✗ http_req_duration..............: avg=1.34s    min=1.56ms  med=55.72ms max=6.3s    p(90)=3.82s   p(95)=4.32s
       { expected_response:true }...: avg=1.34s    min=1.56ms  med=55.72ms max=6.3s    p(90)=3.82s   p(95)=4.32s
     http_req_failed................: 0.00%   ✓ 0         ✗ 5298
     http_req_receiving.............: avg=73.21µs  min=27.84µs med=60.79µs max=6.05ms  p(90)=105µs   p(95)=124.38µs
     http_req_sending...............: avg=18.46µs  min=9.66µs  med=13.89µs max=1.28ms  p(90)=22.55µs p(95)=34.64µs
     http_req_tls_handshaking.......: avg=139.76µs min=0s      med=0s      max=36.65ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=1.34s    min=1.49ms  med=55.63ms max=6.3s    p(90)=3.82s   p(95)=4.32s
     http_reqs......................: 5298    65.775374/s
     iteration_duration.............: avg=2.68s    min=74.58ms med=3.1s    max=6.3s    p(90)=4.32s   p(95)=4.61s
     iterations.....................: 2649    32.887687/s
     vus............................: 15      min=1       max=150
     vus_max........................: 150     min=150     max=150

```
</details>

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- WAS 성능 개선하기
  - [X] Redis Cache 적용
    - 성능 이슈가 발생 가능한 Station, Line, Favorite, Map Service 에 캐싱 적용
    - @CachePut 은 사용하지 않았는데, 관련 데이터가 수정되면 캐시 데이터를 수정하기보다 깔끔하게 관련 캐시를 삭제하고 새로운 데이터로 캐싱하는 것이 더 안전하다고 판단함 
  - [ ] Thread pool 설정 적용
  
- Nginx 성능 개선 관련 설정 추가
  - [ ] gzip 설정 추가 : text/plain, text/css, text/js, text/xml 등 적용
  - [ ] Cache-Control 적용 : max-age=31536000 설정
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

