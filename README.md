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

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

- load
         /\      |‾‾| /‾‾/   /‾‾/   
   /\  /  \     |  |/  /   /  /    
   /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  |
   / __________ \  |__| \__\ \_____/ .io

execution: local
script: load.js
output: -

scenarios: (100.00%) 1 scenario, 360 max VUs, 4m30s max duration (incl. graceful stop):
* default: Up to 360 looping VUs for 4m0s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (4m00.6s), 000/360 VUs, 33191 complete and 0 interrupted iterations
default ↓ [======================================] 001/360 VUs  4m0s

     ✓ logged in successfully
     ✓ retrieved member

     checks.....................: 100.00% ✓ 66382      ✗ 0    
     data_received..............: 38 MB   158 kB/s
     data_sent..................: 19 MB   79 kB/s
     http_req_blocked...........: avg=25.49µs min=3.12µs  med=4.05µs  max=67.11ms  p(90)=6.21µs  p(95)=23.91µs 
     http_req_connecting........: avg=2.58µs  min=0s      med=0s      max=12.1ms   p(90)=0s      p(95)=0s      
✓ http_req_duration..........: avg=11.85ms min=2.96ms  med=6.96ms  max=211.72ms p(90)=25.35ms p(95)=35.89ms
http_req_failed............: 100.00% ✓ 99573      ✗ 0    
http_req_receiving.........: avg=59.54µs min=19.56µs med=31.36µs max=33.98ms  p(90)=61.54µs p(95)=178.12µs
http_req_sending...........: avg=55.61µs min=9.07µs  med=13.54µs max=36.54ms  p(90)=35.36µs p(95)=148.47µs
http_req_tls_handshaking...: avg=16.81µs min=0s      med=0s      max=43.77ms  p(90)=0s      p(95)=0s      
http_req_waiting...........: avg=11.74ms min=2.92ms  med=6.87ms  max=211.68ms p(90)=25.17ms p(95)=35.62ms
http_reqs..................: 99573   413.801931/s
iteration_duration.........: avg=1.03s   min=1.01s   med=1.02s   max=1.48s    p(90)=1.07s   p(95)=1.09s   
iterations.................: 33191   137.933977/s
vus........................: 1       min=1        max=360
vus_max....................: 360     min=360      max=360


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


running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ↓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.....................: 100.00% ✓ 20       ✗ 0  
     data_received..............: 15 kB   1.5 kB/s
     data_sent..................: 6.1 kB  593 B/s
     http_req_blocked...........: avg=2.59ms  min=3.71µs  med=4.96µs  max=77.66ms  p(90)=7.95µs  p(95)=8.51µs 
     http_req_connecting........: avg=18.55µs min=0s      med=0s      max=556.75µs p(90)=0s      p(95)=0s     
✓ http_req_duration..........: avg=5.24ms  min=3.41ms  med=5.03ms  max=13.02ms  p(90)=6.39ms  p(95)=7.25ms
http_req_failed............: 100.00% ✓ 30       ✗ 0  
http_req_receiving.........: avg=43.71µs min=30.31µs med=41.87µs max=69.58µs  p(90)=64.4µs  p(95)=68.62µs
http_req_sending...........: avg=19.64µs min=11.22µs med=12.82µs max=87.57µs  p(90)=29.28µs p(95)=29.44µs
http_req_tls_handshaking...: avg=1.67ms  min=0s      med=0s      max=50.33ms  p(90)=0s      p(95)=0s     
http_req_waiting...........: avg=5.17ms  min=3.36ms  med=4.96ms  max=12.86ms  p(90)=6.34ms  p(95)=7.19ms
http_reqs..................: 30      2.927607/s
iteration_duration.........: avg=1.02s   min=1.01s   med=1.01s   max=1.1s     p(90)=1.02s   p(95)=1.06s  
iterations.................: 10      0.975869/s
vus........................: 1       min=1      max=1
vus_max....................: 1       min=1      max=1



          /\      |‾‾| /‾‾/   /‾‾/   
   /\  /  \     |  |/  /   /  /    
   /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  |
   / __________ \  |__| \__\ \_____/ .io

execution: local
script: stress.js
output: -

scenarios: (100.00%) 1 scenario, 270 max VUs, 4m30s max duration (incl. graceful stop):
* default: Up to 270 looping VUs for 4m0s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)

running (4m00.7s), 000/270 VUs, 19893 complete and 0 interrupted iterations
default ↓ [======================================] 001/270 VUs  4m0s
   checks.....................: 99.94%  ✓ 39744      ✗ 21   
   data_received..............: 24 MB   98 kB/s
   data_sent..................: 12 MB   48 kB/s
   http_req_blocked...........: avg=117.05µs min=3.07µs   med=4.03µs  max=201.39ms p(90)=7µs      p(95)=17.28µs
   http_req_connecting........: avg=22.01µs  min=0s       med=0s      max=49.08ms  p(90)=0s       p(95)=0s      
   ✓ http_req_duration..........: avg=106.94ms min=320.25µs med=34.71ms max=1.25s    p(90)=338.63ms p(95)=483.97ms
   http_req_failed............: 100.00% ✓ 59637      ✗ 0    
   http_req_receiving.........: avg=63.85µs  min=0s       med=32.41µs max=65.22ms  p(90)=58.23µs  p(95)=80.76µs
   http_req_sending...........: avg=88.18µs  min=9.44µs   med=13.27µs max=67.96ms  p(90)=35.59µs  p(95)=157.94µs
   http_req_tls_handshaking...: avg=87.28µs  min=0s       med=0s      max=154.23ms p(90)=0s       p(95)=0s      
   http_req_waiting...........: avg=106.79ms min=11.75µs  med=34.58ms max=1.25s    p(90)=338.53ms p(95)=483.92ms
   http_reqs..................: 59637   247.807681/s
   iteration_duration.........: avg=1.32s    min=798.44µs med=1.18s   max=3.15s    p(90)=1.82s    p(95)=1.98s   
   iterations.................: 19893   82.660734/s
   vus........................: 1       min=1        max=270
   vus_max....................: 270     min=270      max=270