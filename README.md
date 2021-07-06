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
- 개선전
```
- smoke

 scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (11.7s), 0/1 VUs, 6 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ lending page running
     ✓ logged in successfully
     ✓ retrieved member
     ✓ path info distance check success

     checks.........................: 100.00% ✓ 24       ✗ 0  
     data_received..................: 21 kB   1.8 kB/s
     data_sent......................: 6.1 kB  516 B/s
     http_req_blocked...............: avg=1.72ms   min=4.13µs  med=5.08µs  max=41.28ms  p(90)=8.3µs    p(95)=9.73µs  
     http_req_connecting............: avg=23.14µs  min=0s      med=0s      max=555.57µs p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=236.49ms min=3.73ms  med=15.15ms max=946.5ms  p(90)=903.37ms p(95)=921.84ms
       { expected_response:true }...: avg=236.49ms min=3.73ms  med=15.15ms max=946.5ms  p(90)=903.37ms p(95)=921.84ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 24 
     http_req_receiving.............: avg=84.64µs  min=62.58µs med=82.71µs max=135.06µs p(90)=104.77µs p(95)=117.26µs
     http_req_sending...............: avg=23.85µs  min=13.05µs med=19.49µs max=63.79µs  p(90)=33.29µs  p(95)=47.51µs 
     http_req_tls_handshaking.......: avg=1.14ms   min=0s      med=0s      max=27.37ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=236.38ms min=3.58ms  med=15.04ms max=946.39ms p(90)=903.23ms p(95)=921.73ms
     http_reqs......................: 24      2.046366/s
     iteration_duration.............: avg=1.95s    min=1.92s   med=1.93s   max=2.02s    p(90)=2s       p(95)=2.01s   
     iterations.....................: 6       0.511592/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

``` 

```
- load

 scenarios: (100.00%) 1 scenario, 90 max VUs, 52s max duration (incl. graceful stop):
           * default: Up to 90 looping VUs for 22s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (51.9s), 00/90 VUs, 52 complete and 59 interrupted iterations
default ✓ [======================================] 00/90 VUs  22s

     ✓ lending page running
     ✓ logged in successfully
     ✓ retrieved member
     ✓ path info distance check success

     checks.........................: 100.00% ✓ 363      ✗ 0   
     data_received..................: 663 kB  13 kB/s
     data_sent......................: 134 kB  2.6 kB/s
     http_req_blocked...............: avg=1.16ms   min=3.87µs  med=5.59µs  max=29.46ms  p(90)=4.32ms   p(95)=4.52ms  
     http_req_connecting............: avg=152.46µs min=0s      med=0s      max=1.07ms   p(90)=616.07µs p(95)=641.44µs
   ✗ http_req_duration..............: avg=7.48s    min=3.29ms  med=1.2s    max=36.63s   p(90)=28.12s   p(95)=29.23s  
       { expected_response:true }...: avg=7.48s    min=3.29ms  med=1.2s    max=36.63s   p(90)=28.12s   p(95)=29.23s  
     http_req_failed................: 0.00%   ✓ 0        ✗ 363 
     http_req_receiving.............: avg=86µs     min=34.59µs med=86.54µs max=182.07µs p(90)=101.56µs p(95)=109.46µs
     http_req_sending...............: avg=32.33µs  min=13.37µs med=23.04µs max=397.6µs  p(90)=55.34µs  p(95)=61.07µs 
     http_req_tls_handshaking.......: avg=980.22µs min=0s      med=0s      max=28.15ms  p(90)=3.58ms   p(95)=3.7ms   
     http_req_waiting...............: avg=7.48s    min=3.16ms  med=1.2s    max=36.63s   p(90)=28.12s   p(95)=29.23s  
     http_reqs......................: 363     6.995392/s
     iteration_duration.............: avg=21.29s   min=1.98s   med=21.6s   max=41.07s   p(90)=34.11s   p(95)=34.45s  
     iterations.....................: 52      1.002095/s
     vus............................: 9       min=1      max=90
     vus_max........................: 90      min=90     max=90
```

```
- stress

scenarios: (100.00%) 1 scenario, 120 max VUs, 48s max duration (incl. graceful stop):
           * default: Up to 120 looping VUs for 18s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (48.0s), 000/120 VUs, 65 complete and 66 interrupted iterations
default ✓ [======================================] 000/120 VUs  18s

     ✓ lending page running
     ✓ logged in successfully
     ✓ retrieved member
     ✗ path info distance check success
      ↳  84% — ✓ 61 / ✗ 11

     checks.........................: 97.38% ✓ 409      ✗ 11   
     data_received..................: 841 kB 18 kB/s
     data_sent......................: 154 kB 3.2 kB/s
     http_req_blocked...............: avg=1.28ms   min=3.73µs  med=5.44µs  max=28.82ms  p(90)=4.22ms   p(95)=4.32ms  
     http_req_connecting............: avg=172.28µs min=0s      med=0s      max=5.88ms   p(90)=570.88µs p(95)=608.92µs
   ✗ http_req_duration..............: avg=8.48s    min=3.18ms  med=1.32s   max=37.27s   p(90)=30s      p(95)=30.01s  
       { expected_response:true }...: avg=5.96s    min=3.18ms  med=1.14s   max=37.27s   p(90)=28.95s   p(95)=29.74s  
     http_req_failed................: 10.71% ✓ 45       ✗ 375  
     http_req_receiving.............: avg=83.09µs  min=35.54µs med=83.58µs max=147.19µs p(90)=102.44µs p(95)=110µs   
     http_req_sending...............: avg=31.34µs  min=12.07µs med=21.2µs  max=303.2µs  p(90)=57.28µs  p(95)=62.4µs  
     http_req_tls_handshaking.......: avg=1.07ms   min=0s      med=0s      max=27.69ms  p(90)=3.56ms   p(95)=3.61ms  
     http_req_waiting...............: avg=8.48s    min=3.06ms  med=1.32s   max=37.27s   p(90)=30s      p(95)=30.01s  
     http_reqs......................: 420    8.751062/s
     iteration_duration.............: avg=24.88s   min=1.99s   med=29.03s  max=41.99s   p(90)=35.94s   p(95)=36.16s  
     iterations.....................: 65     1.354331/s
     vus............................: 68     min=1      max=120
     vus_max........................: 120    min=120    max=120
```
==============================

- 개선후

```
- smoke

scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.1s), 0/1 VUs, 9 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ lending page running
     ✓ logged in successfully
     ✓ retrieved member
     ✓ path info distance check success

     checks.........................: 100.00% ✓ 36       ✗ 0  
     data_received..................: 26 kB   2.5 kB/s
     data_sent......................: 5.6 kB  553 B/s
     http_req_blocked...............: avg=1.16ms   min=2.7µs   med=2.91µs  max=41.74ms  p(90)=3.02µs  p(95)=3.06µs  
     http_req_connecting............: avg=15.82µs  min=0s      med=0s      max=569.76µs p(90)=0s      p(95)=0s      
   ✓ http_req_duration..............: avg=28ms     min=5.63ms  med=15.26ms max=312.88ms p(90)=24.11ms p(95)=92.36ms 
       { expected_response:true }...: avg=28ms     min=5.63ms  med=15.26ms max=312.88ms p(90)=24.11ms p(95)=92.36ms 
     http_req_failed................: 0.00%   ✓ 0        ✗ 36 
     http_req_receiving.............: avg=101.79µs min=85.06µs med=99.99µs max=139.5µs  p(90)=112.7µs p(95)=128.9µs 
     http_req_sending...............: avg=65.17µs  min=38.89µs med=61.23µs max=189.16µs p(90)=80.24µs p(95)=114.21µs
     http_req_tls_handshaking.......: avg=792.74µs min=0s      med=0s      max=28.53ms  p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=27.83ms  min=5.47ms  med=15.09ms max=312.67ms p(90)=23.96ms p(95)=92.17ms 
     http_reqs......................: 36      3.576299/s
     iteration_duration.............: avg=1.11s    min=1.04s   med=1.05s   max=1.63s    p(90)=1.17s   p(95)=1.4s    
     iterations.....................: 9       0.894075/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

```
- load

scenarios: (100.00%) 1 scenario, 90 max VUs, 52s max duration (incl. graceful stop):
           * default: Up to 90 looping VUs for 22s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (23.0s), 00/90 VUs, 930 complete and 0 interrupted iterations
default ✗ [======================================] 00/90 VUs  22s

     ✓ lending page running
     ✓ logged in successfully
     ✓ retrieved member
     ✓ path info distance check success

     checks.........................: 100.00% ✓ 3720       ✗ 0   
     data_received..................: 2.6 MB  112 kB/s
     data_sent......................: 567 kB  25 kB/s
     http_req_blocked...............: avg=118.24µs min=2.55µs  med=2.78µs  max=31.14ms  p(90)=3.05µs  p(95)=3.25µs  
     http_req_connecting............: avg=14.72µs  min=0s      med=0s      max=4.38ms   p(90)=0s      p(95)=0s      
   ✓ http_req_duration..............: avg=11.41ms  min=3.13ms  med=10.37ms max=59.98ms  p(90)=19.6ms  p(95)=24.04ms 
       { expected_response:true }...: avg=11.41ms  min=3.13ms  med=10.37ms max=59.98ms  p(90)=19.6ms  p(95)=24.04ms 
     http_req_failed................: 0.00%   ✓ 0          ✗ 3720
     http_req_receiving.............: avg=70.45µs  min=32.17µs med=65.55µs max=1.13ms   p(90)=90.84µs p(95)=100.36µs
     http_req_sending...............: avg=50.56µs  min=25.15µs med=41.05µs max=221.74µs p(90)=73.81µs p(95)=91.57µs 
     http_req_tls_handshaking.......: avg=96.3µs   min=0s      med=0s      max=29.06ms  p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=11.29ms  min=3.04ms  med=10.25ms max=59.88ms  p(90)=19.46ms p(95)=23.94ms 
     http_reqs......................: 3720    161.475376/s
     iteration_duration.............: avg=1.04s    min=1.03s   med=1.04s   max=1.1s     p(90)=1.06s   p(95)=1.07s   
     iterations.....................: 930     40.368844/s
     vus............................: 1       min=1        max=90
     vus_max........................: 90      min=90       max=90

```

```
- stress


 scenarios: (100.00%) 1 scenario, 120 max VUs, 48s max duration (incl. graceful stop):
           * default: Up to 120 looping VUs for 18s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (19.0s), 000/120 VUs, 1091 complete and 0 interrupted iterations
default ✓ [======================================] 000/120 VUs  18s

     ✓ lending page running
     ✓ logged in successfully
     ✓ retrieved member
     ✓ path info distance check success

     checks.........................: 100.00% ✓ 4364      ✗ 0    
     data_received..................: 3.1 MB  163 kB/s
     data_sent......................: 674 kB  36 kB/s
     http_req_blocked...............: avg=131.35µs min=2.54µs  med=2.76µs  max=30.82ms  p(90)=2.99µs  p(95)=3.21µs 
     http_req_connecting............: avg=15.7µs   min=0s      med=0s      max=2.64ms   p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=9.06ms   min=3.01ms  med=9.09ms  max=45.2ms   p(90)=15.04ms p(95)=16.9ms 
       { expected_response:true }...: avg=9.06ms   min=3.01ms  med=9.09ms  max=45.2ms   p(90)=15.04ms p(95)=16.9ms 
     http_req_failed................: 0.00%   ✓ 0         ✗ 4364 
     http_req_receiving.............: avg=67.49µs  min=32.18µs med=63.74µs max=237.41µs p(90)=84.74µs p(95)=95.18µs
     http_req_sending...............: avg=50.66µs  min=24.09µs med=40.52µs max=239.85µs p(90)=75.01µs p(95)=99.11µs
     http_req_tls_handshaking.......: avg=108.16µs min=0s      med=0s      max=29.49ms  p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=8.94ms   min=2.91ms  med=8.95ms  max=45.09ms  p(90)=14.93ms p(95)=16.77ms
     http_reqs......................: 4364    230.11803/s
     iteration_duration.............: avg=1.03s    min=1.03s   med=1.03s   max=1.07s    p(90)=1.04s   p(95)=1.05s  
     iterations.....................: 1091    57.529507/s
     vus............................: 64      min=1       max=120
     vus_max........................: 120     min=120     max=120


```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
```
1. Reverse Proxy를 개선해서 worker_connections 숫자를 늘려주었습니다. (nginx.conf 설정)
2. gizip 사용해서 정적 파일들을 압축하였습니다. (nginx.conf 설정)
3. proxy cache 설정 (nginx.conf 설정)
4. http2 설정 (nginx.conf 설정)
여기까지 진행했는데 findPath부분에서 느려지는건 어쩔 수 없는 듯 하여 다음 단계인 Redis 캐시 적용을
진행하였습니다.
5. private 서버에 Redis 설치하여 findPath 부분 조회시에 Redis Cache 적용

```
 
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

