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

##### SmokeTest
```
running (10.0s), 0/1 VUs, 3813 complete and 0 interrupted iterations
default ✗ [======================================] 1 VUs  10s

     ✓ get stations successfully

     checks.........................: 100.00% ✓ 3813       ✗ 0   
     data_received..................: 956 kB  96 kB/s
     data_sent......................: 440 kB  44 kB/s
     http_req_blocked...............: avg=18.9µs  min=3.56µs  med=4.6µs   max=39.33ms  p(90)=5.17µs  p(95)=6.77µs 
     http_req_connecting............: avg=501ns   min=0s      med=0s      max=496.48µs p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=2.46ms  min=1.53ms  med=2.22ms  max=23.68ms  p(90)=3.4ms   p(95)=3.95ms 
       { expected_response:true }...: avg=2.46ms  min=1.53ms  med=2.22ms  max=23.68ms  p(90)=3.4ms   p(95)=3.95ms 
     http_req_failed................: 0.00%   ✓ 0          ✗ 3813
     http_req_receiving.............: avg=60.11µs min=35.07µs med=51.5µs  max=2.62ms   p(90)=88.64µs p(95)=99.03µs
     http_req_sending...............: avg=15.24µs min=11.59µs med=13.16µs max=1.34ms   p(90)=16.83µs p(95)=24.16µs
     http_req_tls_handshaking.......: avg=10.71µs min=0s      med=0s      max=29.23ms  p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=2.38ms  min=1.46ms  med=2.15ms  max=23.54ms  p(90)=3.33ms  p(95)=3.87ms 
     http_reqs......................: 3813    381.135827/s
     iteration_duration.............: avg=2.61ms  min=1.64ms  med=2.35ms  max=50.74ms  p(90)=3.55ms  p(95)=4.11ms 
     iterations.....................: 3813    381.135827/s
     vus............................: 1       min=1        max=1 
     vus_max........................: 1       min=1        max=1 
```

##### LoadTest
```
running (0m30.0s), 000/100 VUs, 31928 complete and 0 interrupted iterations
default ↓ [======================================] 021/100 VUs  30s

     ✓ get stations successfully

     checks.........................: 100.00% ✓ 31928       ✗ 0    
     data_received..................: 8.3 MB  278 kB/s
     data_sent......................: 3.7 MB  124 kB/s
     http_req_blocked...............: avg=398.55µs min=2.82µs  med=4.44µs  max=386.76ms p(90)=6.4µs    p(95)=14.6µs  
     http_req_connecting............: avg=97.19µs  min=0s      med=0s      max=150.94ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=78.11ms  min=1.66ms  med=73.5ms  max=440.57ms p(90)=129.05ms p(95)=151.85ms
       { expected_response:true }...: avg=78.11ms  min=1.66ms  med=73.5ms  max=440.57ms p(90)=129.05ms p(95)=151.85ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 31928
     http_req_receiving.............: avg=835.22µs min=20.68µs med=40.25µs max=280.28ms p(90)=346.56µs p(95)=1.25ms  
     http_req_sending...............: avg=311.78µs min=8.91µs  med=13.92µs max=142.77ms p(90)=31.37µs  p(95)=157.93µs
     http_req_tls_handshaking.......: avg=214.43µs min=0s      med=0s      max=265.41ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=76.96ms  min=1.58ms  med=72.78ms max=440.51ms p(90)=127.06ms p(95)=148.77ms
     http_reqs......................: 31928   1063.839168/s
     iteration_duration.............: avg=79.89ms  min=1.77ms  med=74.57ms max=456.82ms p(90)=131.97ms p(95)=157.68ms
     iterations.....................: 31928   1063.839168/s
     vus............................: 21      min=20        max=100
     vus_max........................: 100     min=100       max=100
```

##### StressTest
```
running (1m05.0s), 000/300 VUs, 77310 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  1m5s

     ✓ get stations successfully

     checks.........................: 100.00% ✓ 77310       ✗ 0    
     data_received..................: 21 MB   318 kB/s
     data_sent......................: 9.0 MB  139 kB/s
     http_req_blocked...............: avg=1.52ms   min=2.5µs   med=4.59µs   max=973.83ms p(90)=7.05µs   p(95)=32.88µs 
     http_req_connecting............: avg=469.77µs min=0s      med=0s       max=321.34ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=132.89ms min=1.47ms  med=117.6ms  max=1.39s    p(90)=221.89ms p(95)=260.32ms
       { expected_response:true }...: avg=132.89ms min=1.47ms  med=117.6ms  max=1.39s    p(90)=221.89ms p(95)=260.32ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 77310
     http_req_receiving.............: avg=1.43ms   min=21.48µs med=42.48µs  max=334.76ms p(90)=564.35µs p(95)=1.86ms  
     http_req_sending...............: avg=844.14µs min=8.81µs  med=14.6µs   max=1.08s    p(90)=50.17µs  p(95)=244.61µs
     http_req_tls_handshaking.......: avg=945.67µs min=0s      med=0s       max=696.61ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=130.62ms min=1.41ms  med=115.87ms max=1.38s    p(90)=217.29ms p(95)=254.78ms
     http_reqs......................: 77310   1189.062726/s
     iteration_duration.............: avg=137.64ms min=1.57ms  med=120.36ms max=1.4s     p(90)=229.38ms p(95)=272.65ms
     iterations.....................: 77310   1189.062726/s
     vus............................: 50      min=20        max=299
     vus_max........................: 300     min=300       max=300
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

