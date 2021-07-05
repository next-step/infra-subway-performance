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
#### 개선전
##### smoke
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 2 max VUs, 40s max duration (incl. graceful stop):
           * default: Up to 2 looping VUs for 10s over 1 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (10.1s), 0/2 VUs, 9 complete and 0 interrupted iterations
default ✓ [======================================] 0/2 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 36       ✗ 0  
     data_received..................: 104 kB  10 kB/s
     data_sent......................: 6.0 kB  590 B/s
     http_req_blocked...............: avg=1.9ms    min=2.66µs  med=2.77µs  max=68.36ms  p(90)=2.95µs   p(95)=4.32µs  
     http_req_connecting............: avg=37.67µs  min=0s      med=0s      max=1.35ms   p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=27.94ms  min=12.93ms med=15.08ms max=74.32ms  p(90)=68.84ms  p(95)=73.91ms 
       { expected_response:true }...: avg=27.94ms  min=12.93ms med=15.08ms max=74.32ms  p(90)=68.84ms  p(95)=73.91ms 
     http_req_failed................: 0.00%   ✓ 0        ✗ 36 
     http_req_receiving.............: avg=208.62µs min=49.7µs  med=83.22µs max=883.39µs p(90)=627.07µs p(95)=685.47µs
     http_req_sending...............: avg=59.01µs  min=28.41µs med=36.5µs  max=251.43µs p(90)=109.62µs p(95)=147.96µs
     http_req_tls_handshaking.......: avg=821.65µs min=0s      med=0s      max=29.57ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=27.67ms  min=12.75ms med=14.67ms max=74.17ms  p(90)=68.73ms  p(95)=73.78ms 
     http_reqs......................: 36      3.567831/s
     iteration_duration.............: avg=1.12s    min=1.1s    med=1.11s   max=1.18s    p(90)=1.13s    p(95)=1.16s   
     iterations.....................: 9       0.891958/s
     vus............................: 1       min=1      max=1
     vus_max........................: 2       min=2      max=2
```
##### load
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m0s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 30s over 1 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m33.3s), 000/300 VUs, 2159 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 8636       ✗ 0    
     data_received..................: 25 MB   755 kB/s
     data_sent......................: 1.5 MB  44 kB/s
     http_req_blocked...............: avg=242.35µs min=2.53µs  med=2.7µs    max=48.35ms p(90)=2.87µs   p(95)=3.11µs  
     http_req_connecting............: avg=47.65µs  min=0s      med=0s       max=14.14ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=331.63ms min=7.48ms  med=293.87ms max=2.83s   p(90)=627.82ms p(95)=828.58ms
       { expected_response:true }...: avg=331.63ms min=7.48ms  med=293.87ms max=2.83s   p(90)=627.82ms p(95)=828.58ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 8636 
     http_req_receiving.............: avg=205.12µs min=30.92µs med=61.51µs  max=35.03ms p(90)=268.05µs p(95)=447.35µs
     http_req_sending...............: avg=47.32µs  min=20.81µs med=34.02µs  max=4.06ms  p(90)=75.95µs  p(95)=106.16µs
     http_req_tls_handshaking.......: avg=186.52µs min=0s      med=0s       max=45.84ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=331.38ms min=7.3ms   med=293.63ms max=2.83s   p(90)=627.73ms p(95)=828.35ms
     http_reqs......................: 8636    259.457088/s
     iteration_duration.............: avg=2.32s    min=1.1s    med=2.27s    max=6s      p(90)=3.45s    p(95)=3.98s   
     iterations.....................: 2159    64.864272/s
     vus............................: 39      min=10       max=299
     vus_max........................: 300     min=300      max=300
```
##### stress
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 2m50s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 2m20s over 8 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m20.9s), 000/400 VUs, 13141 complete and 0 interrupted iterations
default ✓ [======================================] 000/400 VUs  2m20s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 52564      ✗ 0    
     data_received..................: 146 MB  1.0 MB/s
     data_sent......................: 8.1 MB  58 kB/s
     http_req_blocked...............: avg=53.34µs  min=2.42µs  med=2.68µs   max=61.51ms p(90)=2.8µs    p(95)=2.87µs  
     http_req_connecting............: avg=9.74µs   min=0s      med=0s       max=15.87ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=328.94ms min=6.85ms  med=269.53ms max=4.74s   p(90)=658.26ms p(95)=987.23ms
       { expected_response:true }...: avg=328.94ms min=6.85ms  med=269.53ms max=4.74s   p(90)=658.26ms p(95)=987.23ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 52564
     http_req_receiving.............: avg=110.49µs min=27.78µs med=55.95µs  max=27.53ms p(90)=197.6µs  p(95)=265.84µs
     http_req_sending...............: avg=40.93µs  min=18.18µs med=32.67µs  max=1.08ms  p(90)=62.92µs  p(95)=71.84µs 
     http_req_tls_handshaking.......: avg=39.1µs   min=0s      med=0s       max=29.83ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=328.79ms min=6.73ms  med=269.4ms  max=4.74s   p(90)=658.15ms p(95)=987.12ms
     http_reqs......................: 52564   373.070023/s
     iteration_duration.............: avg=2.31s    min=1.07s   med=2.22s    max=7.8s    p(90)=3.63s    p(95)=4.24s   
     iterations.....................: 13141   93.267506/s
     vus............................: 5       min=5        max=400
     vus_max........................: 400     min=400      max=400

```
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

