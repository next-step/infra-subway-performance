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
#### 가장 잦은 접속 페이지 - Smoke 개선 전
```running (06.1s), 0/1 VUs, 3 complete and 0 interrupted iterations
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
```
#### 가장 잦은 접속 페이지 - Smoke 개선 후
```running (06.1s), 0/1 VUs, 3 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  5s

     data_received..................: 12 kB  2.0 kB/s
     data_sent......................: 1.1 kB 177 B/s
     http_req_blocked...............: avg=4.79ms  min=7.15µs  med=7.45µs  max=28.74ms  p(90)=14.37ms  p(95)=21.56ms
     http_req_connecting............: avg=69.24µs min=0s      med=0s      max=415.49µs p(90)=207.74µs p(95)=311.62µs
✓ http_req_duration..............: avg=2.7ms   min=1.76ms  med=2.75ms  max=3.55ms   p(90)=3.55ms   p(95)=3.55ms
{ expected_response:true }...: avg=2.7ms   min=1.76ms  med=2.75ms  max=3.55ms   p(90)=3.55ms   p(95)=3.55ms
http_req_failed................: 0.00%  ✓ 0   ✗ 6
http_req_receiving.............: avg=80.79µs min=55.6µs  med=77.95µs max=109.75µs p(90)=100.69µs p(95)=105.22µs
http_req_sending...............: avg=39.61µs min=20.81µs med=33.49µs max=72.81µs  p(90)=63.52µs  p(95)=68.16µs
http_req_tls_handshaking.......: avg=4.55ms  min=0s      med=0s      max=27.34ms  p(90)=13.67ms  p(95)=20.5ms
http_req_waiting...............: avg=2.58ms  min=1.65ms  med=2.62ms  max=3.48ms   p(90)=3.43ms   p(95)=3.45ms
http_reqs......................: 6      0.991725/s
iteration_duration.............: avg=2.01s   min=2s      med=2s      max=2.03s    p(90)=2.02s    p(95)=2.03s
iterations.....................: 3      0.495862/s
vus............................: 1      min=1 max=1
vus_max........................: 1      min=1 max=1
```
#### 가장 잦은 접속 페이지 - Load 개선 전
```running (1m31.0s), 000/150 VUs, 4159 complete and 0 interrupted iterations
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
```
#### 가장 잦은 접속 페이지 - Load 개선 후
```running (1m31.0s), 000/150 VUs, 4159 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m30s

     data_received..................: 11 MB  122 kB/s
     data_sent......................: 1.0 MB 11 kB/s
     http_req_blocked...............: avg=86.87µs min=3.53µs  med=5.35µs  max=39.39ms  p(90)=7.72µs  p(95)=11.47µs
     http_req_connecting............: avg=8.43µs  min=0s      med=0s      max=1.96ms   p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=2.38ms  min=1.2ms   med=1.95ms  max=20.66ms  p(90)=3.42ms  p(95)=3.58ms
{ expected_response:true }...: avg=2.38ms  min=1.2ms   med=1.95ms  max=20.66ms  p(90)=3.42ms  p(95)=3.58ms
http_req_failed................: 0.00%  ✓ 0     ✗ 8320
http_req_receiving.............: avg=60µs    min=20.96µs med=52.19µs max=751.1µs  p(90)=86.13µs p(95)=106.37µs
http_req_sending...............: avg=21.75µs min=9.76µs  med=19.01µs max=443.94µs p(90)=31.36µs p(95)=40.59µs
http_req_tls_handshaking.......: avg=69.64µs min=0s      med=0s      max=29.2ms   p(90)=0s      p(95)=0s
http_req_waiting...............: avg=2.3ms   min=1.14ms  med=1.85ms  max=20.61ms  p(90)=3.34ms  p(95)=3.49ms
http_reqs......................: 8320   91.486446/s
iteration_duration.............: avg=2s      min=2s      med=2s      max=2.04s    p(90)=2s      p(95)=2s
iterations.....................: 4160   45.743223/s
vus............................: 6      min=6   max=149
vus_max........................: 150    min=150 max=150
```
#### 가장 잦은 접속 페이지 - Stress 개선 전
```running (2m26.3s), 0000/1200 VUs, 26799 complete and 0 interrupted iterations
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
```
#### 가장 잦은 접속 페이지 - Stress 개선 후
```running (2m26.8s), 0000/1200 VUs, 26759 complete and 0 interrupted iterations
default ↓ [======================================] 0007/1200 VUs  2m25s

     data_received..................: 213 MB 1.5 MB/s
     data_sent......................: 21 MB  141 kB/s
     http_req_blocked...............: avg=376.36ms min=0s     med=228.56ms max=3.08s    p(90)=976.01ms p(95)=1.13s
     http_req_connecting............: avg=66.11ms  min=0s     med=3.61ms   max=3.59s    p(90)=257.1ms  p(95)=337.81ms
✓ http_req_duration..............: avg=104.71ms min=0s     med=14.13ms  max=1.58s    p(90)=348.61ms p(95)=587.19ms
{ expected_response:true }...: avg=98.78ms  min=1.21ms med=14.52ms  max=1.58s    p(90)=309.4ms  p(95)=541.52ms
http_req_failed................: 18.04% ✓ 9658   ✗ 43860
http_req_receiving.............: avg=1.02ms   min=0s     med=32.34µs  max=448.39ms p(90)=87.16µs  p(95)=203.73µs
http_req_sending...............: avg=43.32ms  min=0s     med=190.74µs max=1.41s    p(90)=67.57ms  p(95)=163.55ms
http_req_tls_handshaking.......: avg=299.66ms min=0s     med=112.45ms max=2.16s    p(90)=854.45ms p(95)=1.05s
http_req_waiting...............: avg=60.35ms  min=0s     med=10.64ms  max=1.33s    p(90)=194.32ms p(95)=315.93ms
http_reqs......................: 53518  364.592072/s
iteration_duration.............: avg=2.99s    min=2s     med=2.95s    max=6.61s    p(90)=4.18s    p(95)=4.51s
iterations.....................: 26759  182.296036/s
vus............................: 3      min=3    max=1200
vus_max........................: 1200   min=1200 max=1200
```
#### 경로 찾기 - Smoke 개선 전
```running (05.1s), 0/1 VUs, 5 complete and 0 interrupted iterations
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
```
#### 경로 찾기 - Smoke 개선 후
```running (05.1s), 0/1 VUs, 5 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  5s

     data_received..................: 11 kB 2.1 kB/s
     data_sent......................: 971 B 192 B/s
     http_req_blocked...............: avg=7.49ms  min=6.96µs  med=7.87µs  max=37.41ms  p(90)=22.45ms  p(95)=29.93ms
     http_req_connecting............: avg=82.28µs min=0s      med=0s      max=411.42µs p(90)=246.85µs p(95)=329.14µs
✓ http_req_duration..............: avg=2.98ms  min=1.97ms  med=3.6ms   max=3.72ms   p(90)=3.68ms   p(95)=3.7ms
{ expected_response:true }...: avg=2.98ms  min=1.97ms  med=3.6ms   max=3.72ms   p(90)=3.68ms   p(95)=3.7ms
http_req_failed................: 0.00% ✓ 0   ✗ 5
http_req_receiving.............: avg=89.47µs min=78.78µs med=87.02µs max=109.82µs p(90)=100.75µs p(95)=105.29µs
http_req_sending...............: avg=31.32µs min=21.43µs med=22.55µs max=66.84µs  p(90)=49.6µs   p(95)=58.22µs
http_req_tls_handshaking.......: avg=5.66ms  min=0s      med=0s      max=28.31ms  p(90)=16.98ms  p(95)=22.65ms
http_req_waiting...............: avg=2.86ms  min=1.86ms  med=3.45ms  max=3.61ms   p(90)=3.56ms   p(95)=3.59ms
http_reqs......................: 5     0.988702/s
iteration_duration.............: avg=1.01s   min=1s      med=1s      max=1.04s    p(90)=1.02s    p(95)=1.03s
iterations.....................: 5     0.988702/s
vus............................: 1     min=1 max=1
vus_max........................: 1     min=1 max=1
```
#### 경로 찾기 - Load 개선 전
```scenarios: (100.00%) 1 scenario, 150 max VUs, 2m0s max duration (incl. graceful stop):
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
```
#### 경로 찾기 - Load 개선 후
```running (1m30.6s), 000/150 VUs, 8238 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m30s

     data_received..................: 11 MB  122 kB/s
     data_sent......................: 1.0 MB 11 kB/s
     http_req_blocked...............: avg=85.51µs min=3.62µs  med=5.33µs  max=29.89ms  p(90)=7.6µs   p(95)=11.57µs
     http_req_connecting............: avg=8.08µs  min=0s      med=0s      max=898.71µs p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=2.36ms  min=1.2ms   med=1.94ms  max=15.56ms  p(90)=3.36ms  p(95)=3.53ms
{ expected_response:true }...: avg=2.36ms  min=1.2ms   med=1.94ms  max=15.56ms  p(90)=3.36ms  p(95)=3.53ms
http_req_failed................: 0.00%  ✓ 0     ✗ 8238
http_req_receiving.............: avg=61.66µs min=19.89µs med=52.55µs max=1.23ms   p(90)=88.43µs p(95)=111.36µs
http_req_sending...............: avg=19.62µs min=9.69µs  med=14.44µs max=3.51ms   p(90)=27.61µs p(95)=39.01µs
http_req_tls_handshaking.......: avg=69.51µs min=0s      med=0s      max=28.39ms  p(90)=0s      p(95)=0s
http_req_waiting...............: avg=2.28ms  min=1.15ms  med=1.84ms  max=15.48ms  p(90)=3.28ms  p(95)=3.43ms
http_reqs......................: 8238   90.970454/s
iteration_duration.............: avg=1s      min=1s      med=1s      max=1.03s    p(90)=1s      p(95)=1s
iterations.....................: 8238   90.970454/s
vus............................: 3      min=3   max=149
vus_max........................: 150    min=150 max=150
```

#### 경로 찾기 - Stress 개선 전
```running (2m26.0s), 0000/1200 VUs, 52749 complete and 0 interrupted iterations
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
```
#### 경로 찾기 - Stress 개선 후
```running (2m25.9s), 0000/1200 VUs, 52331 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1200 VUs  2m25s

     data_received..................: 213 MB 1.5 MB/s
     data_sent......................: 20 MB  140 kB/s
     http_req_blocked...............: avg=415ms    min=0s     med=215.68ms max=3.1s     p(90)=1.14s    p(95)=1.53s
     http_req_connecting............: avg=37.1ms   min=0s     med=1.52ms   max=1.66s    p(90)=81.81ms  p(95)=198.76ms
✓ http_req_duration..............: avg=117.92ms min=0s     med=13.64ms  max=3.1s     p(90)=384.75ms p(95)=759.49ms
{ expected_response:true }...: avg=103.88ms min=1.15ms med=13.41ms  max=3.1s     p(90)=323.86ms p(95)=736.13ms
http_req_failed................: 13.89% ✓ 7271   ✗ 45060
http_req_receiving.............: avg=1.08ms   min=0s     med=32.84µs  max=202.12ms p(90)=85.13µs  p(95)=197.87µs
http_req_sending...............: avg=59ms     min=0s     med=148.9µs  max=2.84s    p(90)=61.86ms  p(95)=533.72ms
http_req_tls_handshaking.......: avg=343.71ms min=0s     med=80.23ms  max=2.18s    p(90)=1s       p(95)=1.47s
http_req_waiting...............: avg=57.83ms  min=0s     med=11.3ms   max=2.81s    p(90)=166.45ms p(95)=326.35ms
http_reqs......................: 52331  358.702913/s
iteration_duration.............: avg=1.51s    min=1s     med=1.34s    max=4.2s     p(90)=2.4s     p(95)=2.77s
iterations.....................: 52331  358.702913/s
vus............................: 3      min=3    max=1200
vus_max........................: 1200   min=1200 max=1200
```
------------------

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- 리버스 프록시 서버의 nginx 에서 gzip 기능 추가했습니다.
- 리버스 프록시 서버의 로드밸런싱 기능은 이전 미션에서 구현해두어서 큰 차이가 없었던 것 같습니다.
- Redis 캐싱을 추가했습니다. (가장 잦은 방문 페이지(stations), 가장 많이 사용되는 기능(path))  
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- 요구사항
    - 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
- Coding as a Hobby 와 같은 결과를 반환하세요.

- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)


2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

