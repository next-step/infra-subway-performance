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
- smoke 테스트 개선 결과 http_req_duration: avg= 68.54ms -> 14.19ms
```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: .\smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 2 max VUs, 40s max duration (incl. graceful stop):
           * default: 2 looping VUs for 10s (gracefulStop: 30s)


running (10.6s), 0/2 VUs, 20 complete and 0 interrupted iterations
default ✓ [======================================] 2 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path

     checks.........................: 100.00% ✓ 60       ✗ 0
     data_received..................: 84 kB   7.9 kB/s
     data_sent......................: 10 kB   979 B/s
     http_req_blocked...............: avg=1.52ms   min=0s     med=0s       max=46.31ms p(90)=0s       p(95)=0s
     http_req_connecting............: avg=101.06µs min=0s     med=0s       max=3.03ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=14.19ms  min=7.35ms med=14.78ms  max=27.4ms  p(90)=17.89ms  p(95)=19.92ms
       { expected_response:true }...: avg=14.19ms  min=7.35ms med=14.78ms  max=27.4ms  p(90)=17.89ms  p(95)=19.92ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 60
     http_req_receiving.............: avg=382.05µs min=0s     med=274.84µs max=1.11ms  p(90)=985.36µs p(95)=1ms
     http_req_sending...............: avg=305.55µs min=0s     med=0s       max=1.64ms  p(90)=849.43µs p(95)=1ms
     http_req_tls_handshaking.......: avg=1.27ms   min=0s     med=0s       max=38.3ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=13.51ms  min=7.07ms med=13.91ms  max=26.74ms p(90)=17.77ms  p(95)=18.67ms
     http_reqs......................: 60      5.678202/s
     iteration_duration.............: avg=1.05s    min=1.04s  med=1.05s    max=1.09s   p(90)=1.06s    p(95)=1.09s
     iterations.....................: 20      1.892734/s
     vus............................: 2       min=2      max=2
     vus_max........................: 2       min=2      max=2
```
- load 테스트 개선 결과 http_req_duration: avg= 3.99s -> 12.03ms
```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: .\load.js
     output: -

  scenarios: (100.00%) 1 scenario, 50 max VUs, 1m5s max duration (incl. graceful stop):
           * default: Up to 50 looping VUs for 35s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m35.8s), 00/50 VUs, 1346 complete and 0 interrupted iterations
default ✓ [======================================] 00/50 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path

     checks.........................: 100.00% ✓ 4038       ✗ 0
     data_received..................: 5.3 MB  147 kB/s
     data_sent......................: 650 kB  18 kB/s
     http_req_blocked...............: avg=232.88µs min=0s     med=0s       max=50.6ms  p(90)=0s       p(95)=0s
     http_req_connecting............: avg=49.64µs  min=0s     med=0s       max=5.99ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=12.03ms  min=3.88ms med=10.48ms  max=67.77ms p(90)=19.17ms  p(95)=25.21ms
       { expected_response:true }...: avg=12.03ms  min=3.88ms med=10.48ms  max=67.77ms p(90)=19.17ms  p(95)=25.21ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 4038
     http_req_receiving.............: avg=439.67µs min=0s     med=427.35µs max=24.51ms p(90)=947.04µs p(95)=1.03ms
     http_req_sending...............: avg=118.39µs min=0s     med=0s       max=2.27ms  p(90)=530.56µs p(95)=601.92µs
     http_req_tls_handshaking.......: avg=171.01µs min=0s     med=0s       max=44.62ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=11.47ms  min=3.32ms med=9.9ms    max=66.6ms  p(90)=18.63ms  p(95)=24.52ms
     http_reqs......................: 4038    112.739208/s
     iteration_duration.............: avg=1.04s    min=1.02s  med=1.03s    max=1.13s   p(90)=1.05s    p(95)=1.06s
     iterations.....................: 1346    37.579736/s
     vus............................: 7       min=5        max=50
     vus_max........................: 50      min=50       max=50
```
- stress 테스트 개선 결과 http_req_duration: avg= 5.57s -> 10.26ms
```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: .\stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 2m10s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 1m40s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m40.9s), 000/100 VUs, 4976 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path

     checks.........................: 100.00% ✓ 14928     ✗ 0
     data_received..................: 19 MB   189 kB/s
     data_sent......................: 2.4 MB  23 kB/s
     http_req_blocked...............: avg=103.85µs min=0s     med=0s       max=46.53ms p(90)=0s       p(95)=0s
     http_req_connecting............: avg=25.41µs  min=0s     med=0s       max=6.33ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=10.26ms  min=2.74ms med=8.71ms   max=90.84ms p(90)=16.38ms  p(95)=21.49ms
       { expected_response:true }...: avg=10.26ms  min=2.74ms med=8.71ms   max=90.84ms p(90)=16.38ms  p(95)=21.49ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 14928
     http_req_receiving.............: avg=338.54µs min=0s     med=252.75µs max=24.68ms p(90)=876.66µs p(95)=999.4µs
     http_req_sending...............: avg=96.64µs  min=0s     med=0s       max=23.68ms p(90)=518.83µs p(95)=568.46µs
     http_req_tls_handshaking.......: avg=73.38µs  min=0s     med=0s       max=40.94ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=9.82ms   min=0s     med=8.24ms   max=90.47ms p(90)=15.99ms  p(95)=21.19ms
     http_reqs......................: 14928   147.9829/s
     iteration_duration.............: avg=1.03s    min=1.01s  med=1.03s    max=1.11s   p(90)=1.04s    p(95)=1.05s
     iterations.....................: 4976    49.327633/s
     vus............................: 6       min=2       max=100
     vus_max........................: 100     min=100     max=100
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
 - Http 1.1 성능 개선 (nginx 설정)
 - gzip 사용 (nginx 설정)
 - http 캐싱 적용 (nginx 설정)
 - application 캐싱 적용 (Redis cache 사용)

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

