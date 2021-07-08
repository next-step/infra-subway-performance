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
#### 가장 잦은 접속 페이지 - Smoke 전
execution: local
script: smoke-highest-connection.js
output: -

scenarios: (100.00%) 1 scenario, 1 max VUs, 35s max duration (incl. graceful stop):
* default: 1 looping VUs for 5s (gracefulStop: 30s)

running (06.1s), 0/1 VUs, 3 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  5s

     data_received..................: 12 kB  2.0 kB/s
     data_sent......................: 1.1 kB 177 B/s
     http_req_blocked...............: avg=5.9ms   min=6.49µs  med=7.69µs  max=35.42ms  p(90)=17.71ms  p(95)=26.56ms
     http_req_connecting............: avg=74.27µs min=0s      med=0s      max=445.65µs p(90)=222.82µs p(95)=334.24µs
✓ http_req_duration..............: avg=3.11ms  min=2.14ms  med=3.1ms   max=4.07ms   p(90)=4.06ms   p(95)=4.07ms
{ expected_response:true }...: avg=3.11ms  min=2.14ms  med=3.1ms   max=4.07ms   p(90)=4.06ms   p(95)=4.07ms
http_req_failed................: 0.00%  ✓ 0   ✗ 6
http_req_receiving.............: avg=91.02µs min=59.89µs med=71.57µs max=140.78µs p(90)=139.97µs p(95)=140.38µs
http_req_sending...............: avg=40.06µs min=22.44µs med=33.51µs max=77.95µs  p(90)=59.35µs  p(95)=68.65µs
http_req_tls_handshaking.......: avg=4.69ms  min=0s      med=0s      max=28.14ms  p(90)=14.07ms  p(95)=21.11ms
http_req_waiting...............: avg=2.98ms  min=1.99ms  med=3ms     max=3.96ms   p(90)=3.92ms   p(95)=3.94ms
http_reqs......................: 6      0.989918/s
iteration_duration.............: avg=2.01s   min=2s      med=2s      max=2.04s    p(90)=2.03s    p(95)=2.03s
iterations.....................: 3      0.494959/s
vus............................: 1      min=1 max=1
vus_max........................: 1      min=1 max=1
#### 가장 잦은 접속 페이지 - Smoke 후
running (06.1s), 0/1 VUs, 3 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  5s

     data_received..................: 12 kB  2.0 kB/s
     data_sent......................: 1.1 kB 176 B/s
     http_req_blocked...............: avg=5.75ms   min=7.28µs  med=7.96µs  max=34.51ms  p(90)=17.26ms  p(95)=25.88ms
     http_req_connecting............: avg=105.47µs min=0s      med=0s      max=632.84µs p(90)=316.42µs p(95)=474.63µs
✓ http_req_duration..............: avg=5.21ms   min=4.18ms  med=5.23ms  max=6.36ms   p(90)=6.16ms   p(95)=6.26ms
{ expected_response:true }...: avg=5.21ms   min=4.18ms  med=5.23ms  max=6.36ms   p(90)=6.16ms   p(95)=6.26ms
http_req_failed................: 0.00%  ✓ 0   ✗ 6
http_req_receiving.............: avg=91.57µs  min=68µs    med=84.47µs max=139.11µs p(90)=120.82µs p(95)=129.97µs
http_req_sending...............: avg=36.08µs  min=22.99µs med=33.96µs max=64.68µs  p(90)=50.14µs  p(95)=57.41µs
http_req_tls_handshaking.......: avg=4.51ms   min=0s      med=0s      max=27.09ms  p(90)=13.54ms  p(95)=20.31ms
http_req_waiting...............: avg=5.08ms   min=4.05ms  med=5.09ms  max=6.25ms   p(90)=6.06ms   p(95)=6.16ms
http_reqs......................: 6      0.987968/s
iteration_duration.............: avg=2.02s    min=2.01s   med=2.01s   max=2.04s    p(90)=2.04s    p(95)=2.04s
iterations.....................: 3      0.493984/s
vus............................: 1      min=1 max=1
vus_max........................: 1      min=1 max=1
#### 가장 잦은 접속 페이지 - Load 전
running (1m31.0s), 000/150 VUs, 4159 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m30s

     data_received..................: 11 MB  122 kB/s
     data_sent......................: 1.0 MB 11 kB/s
     http_req_blocked...............: avg=87.88µs min=3.33µs  med=5.5µs   max=39.42ms  p(90)=7.96µs  p(95)=15.74µs
     http_req_connecting............: avg=8.98µs  min=0s      med=0s      max=1.39ms   p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=2.84ms  min=1.34ms  med=2.58ms  max=15.71ms  p(90)=4.14ms  p(95)=4.56ms
{ expected_response:true }...: avg=2.84ms  min=1.34ms  med=2.58ms  max=15.71ms  p(90)=4.14ms  p(95)=4.56ms
http_req_failed................: 0.00%  ✓ 0     ✗ 8318
http_req_receiving.............: avg=64.04µs min=19.89µs med=53.53µs max=894.19µs p(90)=96.28µs p(95)=128.94µs
http_req_sending...............: avg=23.75µs min=10.17µs med=19.05µs max=1.66ms   p(90)=34.34µs p(95)=46.21µs
http_req_tls_handshaking.......: avg=69.52µs min=0s      med=0s      max=28.47ms  p(90)=0s      p(95)=0s
http_req_waiting...............: avg=2.75ms  min=1.29ms  med=2.48ms  max=15.59ms  p(90)=4.04ms  p(95)=4.46ms
http_reqs......................: 8318   91.420642/s
iteration_duration.............: avg=2s      min=2s      med=2s      max=2.05s    p(90)=2s      p(95)=2.01s
iterations.....................: 4159   45.710321/s
vus............................: 6      min=6   max=150
vus_max........................: 150    min=150 max=150
#### 가장 잦은 접속 페이지 - Load 후
running (1m31.0s), 000/150 VUs, 4158 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m30s

     data_received..................: 11 MB  122 kB/s
     data_sent......................: 1.0 MB 11 kB/s
     http_req_blocked...............: avg=88.56µs min=3.38µs  med=5.33µs  max=30.1ms   p(90)=7.76µs  p(95)=12.71µs
     http_req_connecting............: avg=8.91µs  min=0s      med=0s      max=2.6ms    p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=2.81ms  min=1.38ms  med=2.58ms  max=29.01ms  p(90)=4.04ms  p(95)=4.35ms
{ expected_response:true }...: avg=2.81ms  min=1.38ms  med=2.58ms  max=29.01ms  p(90)=4.04ms  p(95)=4.35ms
http_req_failed................: 0.00%  ✓ 0     ✗ 8316
http_req_receiving.............: avg=61.59µs min=19.85µs med=51.43µs max=934.57µs p(90)=89.61µs p(95)=121.52µs
http_req_sending...............: avg=22.83µs min=9.67µs  med=17.75µs max=996.8µs  p(90)=32.72µs p(95)=44.19µs
http_req_tls_handshaking.......: avg=71.63µs min=0s      med=0s      max=28.3ms   p(90)=0s      p(95)=0s
http_req_waiting...............: avg=2.72ms  min=1.32ms  med=2.49ms  max=28.94ms  p(90)=3.95ms  p(95)=4.26ms
http_reqs......................: 8316   91.382743/s
iteration_duration.............: avg=2s      min=2s      med=2s      max=2.04s    p(90)=2s      p(95)=2.01s
iterations.....................: 4158   45.691371/s
vus............................: 1      min=1   max=149
vus_max........................: 150    min=150 max=150
#### 가장 잦은 접속 페이지 - Stress 전
running (2m26.3s), 0000/1200 VUs, 26799 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1200 VUs  2m25s

     data_received..................: 216 MB 1.5 MB/s
     data_sent......................: 21 MB  143 kB/s
     http_req_blocked...............: avg=378.79ms min=0s     med=172.75ms max=2.99s    p(90)=1s       p(95)=1.38s
     http_req_connecting............: avg=51.61ms  min=0s     med=3.17ms   max=1.55s    p(90)=190.22ms p(95)=289.13ms
✓ http_req_duration..............: avg=112.93ms min=0s     med=16.34ms  max=3.16s    p(90)=348.49ms p(95)=686.03ms
{ expected_response:true }...: avg=99.55ms  min=1.26ms med=15.98ms  max=3.16s    p(90)=311.09ms p(95)=595.01ms
http_req_failed................: 16.29% ✓ 8736   ✗ 44862
http_req_receiving.............: avg=1.01ms   min=0s     med=32.69µs  max=245.76ms p(90)=81.44µs  p(95)=181.84µs
http_req_sending...............: avg=44.01ms  min=0s     med=208.24µs max=2.61s    p(90)=57.19ms  p(95)=140.05ms
http_req_tls_handshaking.......: avg=309.7ms  min=0s     med=79.83ms  max=2.24s    p(90)=916.09ms p(95)=1.33s
http_req_waiting...............: avg=67.91ms  min=0s     med=12.67ms  max=2.61s    p(90)=223.89ms p(95)=337.14ms
http_reqs......................: 53598  366.333571/s
iteration_duration.............: avg=2.98s    min=2s     med=2.95s    max=6.36s    p(90)=4.31s    p(95)=4.51s
iterations.....................: 26799  183.166785/s
vus............................: 3      min=3    max=1200
vus_max........................: 1200   min=1200 max=1200
#### 가장 잦은 접속 페이지 - Stress 후
running (2m26.2s), 0000/1200 VUs, 26558 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1200 VUs  2m25s

     data_received..................: 212 MB 1.4 MB/s
     data_sent......................: 20 MB  140 kB/s
     http_req_blocked...............: avg=388.61ms min=0s     med=187.02ms max=3.34s    p(90)=1.03s    p(95)=1.39s
     http_req_connecting............: avg=55.26ms  min=0s     med=2.7ms    max=3.09s    p(90)=203.01ms p(95)=304.28ms
✓ http_req_duration..............: avg=113.72ms min=0s     med=15.35ms  max=1.64s    p(90)=335.17ms p(95)=678.08ms
{ expected_response:true }...: avg=99.33ms  min=1.21ms med=14.98ms  max=1.64s    p(90)=289.48ms p(95)=578.83ms
http_req_failed................: 17.48% ✓ 9287   ✗ 43829
http_req_receiving.............: avg=1.03ms   min=0s     med=32.64µs  max=267.86ms p(90)=84.67µs  p(95)=210.58µs
http_req_sending...............: avg=44.88ms  min=0s     med=180.8µs  max=1.51s    p(90)=60.16ms  p(95)=159.59ms
http_req_tls_handshaking.......: avg=316.73ms min=0s     med=91.34ms  max=2.14s    p(90)=976.77ms p(95)=1.32s
http_req_waiting...............: avg=67.8ms   min=0s     med=11.7ms   max=1.18s    p(90)=225.77ms p(95)=336.1ms
http_reqs......................: 53116  363.286561/s
iteration_duration.............: avg=3.01s    min=2s     med=2.96s    max=6.43s    p(90)=4.37s    p(95)=4.58s
iterations.....................: 26558  181.64328/s
vus............................: 2      min=2    max=1200
vus_max........................: 1200   min=1200 max=1200

#### 경로 찾기 - Smoke 전
running (05.1s), 0/1 VUs, 5 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  5s

     data_received..................: 11 kB 2.1 kB/s
     data_sent......................: 971 B 192 B/s
     http_req_blocked...............: avg=6.96ms  min=7.48µs  med=7.89µs  max=34.81ms  p(90)=20.89ms  p(95)=27.85ms
     http_req_connecting............: avg=95.21µs min=0s      med=0s      max=476.09µs p(90)=285.65µs p(95)=380.87µs
✓ http_req_duration..............: avg=2.7ms   min=1.98ms  med=2.01ms  max=3.78ms   p(90)=3.76ms   p(95)=3.77ms
{ expected_response:true }...: avg=2.7ms   min=1.98ms  med=2.01ms  max=3.78ms   p(90)=3.76ms   p(95)=3.77ms
http_req_failed................: 0.00% ✓ 0   ✗ 5
http_req_receiving.............: avg=80.41µs min=72.98µs med=78.57µs max=87.71µs  p(90)=86.42µs  p(95)=87.07µs
http_req_sending...............: avg=33.42µs min=21.89µs med=23.66µs max=69µs     p(90)=53.52µs  p(95)=61.26µs
http_req_tls_handshaking.......: avg=5.34ms  min=0s      med=0s      max=26.74ms  p(90)=16.04ms  p(95)=21.39ms
http_req_waiting...............: avg=2.58ms  min=1.83ms  med=1.9ms   max=3.67ms   p(90)=3.65ms   p(95)=3.66ms
http_reqs......................: 5     0.98938/s
iteration_duration.............: avg=1.01s   min=1s      med=1s      max=1.03s    p(90)=1.02s    p(95)=1.03s
iterations.....................: 5     0.98938/s
vus............................: 1     min=1 max=1
vus_max........................: 1     min=1 max=1
#### 경로 찾기 - Smoke 후

#### 경로 찾기 - Load 전
scenarios: (100.00%) 1 scenario, 150 max VUs, 2m0s max duration (incl. graceful stop):
* default: Up to 150 looping VUs for 1m30s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)

running (1m30.6s), 000/150 VUs, 8237 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m30s

     data_received..................: 11 MB  122 kB/s
     data_sent......................: 1.0 MB 11 kB/s
     http_req_blocked...............: avg=88.06µs min=3.74µs  med=5.43µs  max=30.35ms  p(90)=7.61µs  p(95)=10.98µs
     http_req_connecting............: avg=9.14µs  min=0s      med=0s      max=1.74ms   p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=2.5ms   min=1.24ms  med=2.08ms  max=22.18ms  p(90)=3.54ms  p(95)=3.75ms
{ expected_response:true }...: avg=2.5ms   min=1.24ms  med=2.08ms  max=22.18ms  p(90)=3.54ms  p(95)=3.75ms
http_req_failed................: 0.00%  ✓ 0     ✗ 8237
http_req_receiving.............: avg=60.11µs min=20.36µs med=51.56µs max=6.33ms   p(90)=82.7µs  p(95)=101.65µs
http_req_sending...............: avg=19.65µs min=9.93µs  med=14.9µs  max=879.08µs p(90)=28.85µs p(95)=39.96µs
http_req_tls_handshaking.......: avg=70.69µs min=0s      med=0s      max=28.81ms  p(90)=0s      p(95)=0s
http_req_waiting...............: avg=2.43ms  min=1.2ms   med=1.98ms  max=22.1ms   p(90)=3.46ms  p(95)=3.66ms
http_reqs......................: 8237   90.935987/s
iteration_duration.............: avg=1s      min=1s      med=1s      max=1.03s    p(90)=1s      p(95)=1s
iterations.....................: 8237   90.935987/s
vus............................: 3      min=3   max=149
vus_max........................: 150    min=150 max=150
#### 경로 찾기 - Load 후

#### 경로 찾기 - Stress 전
running (2m26.0s), 0000/1200 VUs, 52749 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1200 VUs  2m25s

     data_received..................: 212 MB 1.5 MB/s
     data_sent......................: 21 MB  141 kB/s
     http_req_blocked...............: avg=386.55ms min=0s     med=188.91ms max=2.81s    p(90)=1.03s    p(95)=1.35s
     http_req_connecting............: avg=51.55ms  min=0s     med=2.68ms   max=1.59s    p(90)=177.96ms p(95)=287.66ms
✓ http_req_duration..............: avg=116.18ms min=0s     med=14.8ms   max=2.9s     p(90)=363.76ms p(95)=684.64ms
{ expected_response:true }...: avg=108.79ms min=1.19ms med=15.01ms  max=2.03s    p(90)=331.24ms p(95)=672.03ms
http_req_failed................: 15.86% ✓ 8368   ✗ 44381
http_req_receiving.............: avg=1.13ms   min=0s     med=33.45µs  max=443.64ms p(90)=87.96µs  p(95)=201.85µs
http_req_sending...............: avg=52.83ms  min=0s     med=191.3µs  max=2.7s     p(90)=63.25ms  p(95)=435.62ms
http_req_tls_handshaking.......: avg=312.43ms min=0s     med=67.46ms  max=2.09s    p(90)=967.52ms p(95)=1.28s
http_req_waiting...............: avg=62.21ms  min=0s     med=11.61ms  max=1.29s    p(90)=214.16ms p(95)=318.06ms
http_reqs......................: 52749  361.289787/s
iteration_duration.............: avg=1.5s     min=1s     med=1.34s    max=4.2s     p(90)=2.28s    p(95)=2.64s
iterations.....................: 52749  361.289787/s
vus............................: 0      min=0    max=1200
vus_max........................: 1200   min=1200 max=1200
#### 경로 찾기 - Stress 후

------------------

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

