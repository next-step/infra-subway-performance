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
running (10.0s), 0/1 VUs, 666 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ get stations successfully

     checks.........................: 100.00% ✓ 666       ✗ 0  
     data_received..................: 48 MB   4.8 MB/s
     data_sent......................: 77 kB   7.7 kB/s
     http_req_blocked...............: avg=65.86µs min=4.54µs   med=5.73µs  max=39.59ms  p(90)=6.43µs  p(95)=6.77µs 
     http_req_connecting............: avg=829ns   min=0s       med=0s      max=552.5µs  p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=14.74ms min=10.02ms  med=12.89ms max=45.13ms  p(90)=21.12ms p(95)=25.16ms
       { expected_response:true }...: avg=14.74ms min=10.02ms  med=12.89ms max=45.13ms  p(90)=21.12ms p(95)=25.16ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 666
     http_req_receiving.............: avg=3.27ms  min=223.39µs med=2.99ms  max=33.84ms  p(90)=4.87ms  p(95)=5.86ms 
     http_req_sending...............: avg=23.55µs min=13.84µs  med=19.8µs  max=569.26µs p(90)=28.05µs p(95)=31.73µs
     http_req_tls_handshaking.......: avg=42.64µs min=0s       med=0s      max=28.4ms   p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=11.44ms min=8.41ms   med=9.98ms  max=40.75ms  p(90)=16.12ms p(95)=19.55ms
     http_reqs......................: 666     66.513232/s
     iteration_duration.............: avg=15.01ms min=10.18ms  med=13.1ms  max=83.6ms   p(90)=21.39ms p(95)=25.4ms 
     iterations.....................: 666     66.513232/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1

```

##### LoadTest
```
running (0m30.0s), 000/100 VUs, 6031 complete and 0 interrupted iterations
default ↓ [======================================] 013/100 VUs  30s

     ✓ get stations successfully

     checks.........................: 100.00% ✓ 6031       ✗ 0    
     data_received..................: 435 MB  15 MB/s
     data_sent......................: 731 kB  24 kB/s
     http_req_blocked...............: avg=2.69ms   min=3.33µs   med=5.08µs   max=887.03ms p(90)=8.48µs   p(95)=18.51µs 
     http_req_connecting............: avg=901.72µs min=0s       med=0s       max=328.52ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=416.68ms min=12.7ms   med=418.04ms max=1.7s     p(90)=638.01ms p(95)=741.25ms
       { expected_response:true }...: avg=416.68ms min=12.7ms   med=418.04ms max=1.7s     p(90)=638.01ms p(95)=741.25ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 6031 
     http_req_receiving.............: avg=66.81ms  min=106.43µs med=21.29ms  max=1.07s    p(90)=197.46ms p(95)=264.55ms
     http_req_sending...............: avg=1.47ms   min=9.69µs   med=16.28µs  max=463.87ms p(90)=153.55µs p(95)=297.72µs
     http_req_tls_handshaking.......: avg=1.37ms   min=0s       med=0s       max=495.7ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=348.39ms min=8.59ms   med=352.85ms max=1.7s     p(90)=522.09ms p(95)=611.62ms
     http_reqs......................: 6031    200.845453/s
     iteration_duration.............: avg=424.35ms min=13.61ms  med=422.24ms max=1.7s     p(90)=650.06ms p(95)=774.04ms
     iterations.....................: 6031    200.845453/s
     vus............................: 22      min=21       max=100
     vus_max........................: 100     min=100      max=100
```

##### StressTest
```
running (1m05.4s), 000/300 VUs, 14168 complete and 0 interrupted iterations
default ↓ [======================================] 104/300 VUs  1m5s

     ✗ get stations successfully
      ↳  97% — ✓ 13829 / ✗ 339

     checks.........................: 97.60% ✓ 13829      ✗ 339  
     data_received..................: 1.0 GB 15 MB/s
     data_sent......................: 2.0 MB 30 kB/s
     http_req_blocked...............: avg=22.13ms  min=0s      med=5.14µs   max=2.37s    p(90)=11.89µs  p(95)=32.21ms 
     http_req_connecting............: avg=7.79ms   min=0s      med=0s       max=881.89ms p(90)=0s       p(95)=13.68ms 
   ✓ http_req_duration..............: avg=723.79ms min=0s      med=678.18ms max=3.43s    p(90)=1.18s    p(95)=1.38s   
       { expected_response:true }...: avg=740.27ms min=10.56ms med=688.21ms max=3.43s    p(90)=1.18s    p(95)=1.39s   
     http_req_failed................: 2.39%  ✓ 339        ✗ 13829
     http_req_receiving.............: avg=70.83ms  min=0s      med=6.39ms   max=1.49s    p(90)=222.06ms p(95)=321.87ms
     http_req_sending...............: avg=4.68ms   min=0s      med=17.11µs  max=1.84s    p(90)=193.01µs p(95)=2.8ms   
     http_req_tls_handshaking.......: avg=13.85ms  min=0s      med=0s       max=1.99s    p(90)=0s       p(95)=9.21ms  
     http_req_waiting...............: avg=648.26ms min=0s      med=601.84ms max=2.64s    p(90)=1.1s     p(95)=1.24s   
     http_reqs......................: 14168  216.664334/s
     iteration_duration.............: avg=762.96ms min=8.56ms  med=697.38ms max=3.8s     p(90)=1.23s    p(95)=1.52s   
     iterations.....................: 14168  216.664334/s
     vus............................: 104    min=20       max=300
     vus_max........................: 300    min=300      max=300
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

