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

* smoke test (before)
```javascript
execution: local
script: smoke.js
output: -

    scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
* default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.3s), 0/1 VUs, 6 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

 ✓ logged in successfully
 ✓ retrieved member

checks.........................: 100.00% ✓ 12       ✗ 0
data_received..................: 20 kB   2.0 kB/s
data_sent......................: 4.2 kB  407 B/s
http_req_blocked...............: avg=111.76ms min=3µs     med=5µs      max=2.01s    p(90)=10.7µs   p(95)=301.77ms
http_req_connecting............: avg=111.75ms min=0s      med=0s       max=2.01s    p(90)=0s       p(95)=301.73ms
✓ http_req_duration..............: avg=128.95ms min=17.28ms med=71.25ms  max=585.04ms p(90)=382.35ms p(95)=434.76ms
    { expected_response:true }...: avg=128.95ms min=17.28ms med=71.25ms  max=585.04ms p(90)=382.35ms p(95)=434.76ms
http_req_failed................: 0.00%   ✓ 0        ✗ 18
http_req_receiving.............: avg=1.09ms   min=63µs    med=404.49µs max=4.51ms   p(90)=2.8ms    p(95)=4.16ms
http_req_sending...............: avg=141.44µs min=17µs    med=31.5µs   max=2.02ms   p(90)=49.4µs   p(95)=356.89µs
http_req_tls_handshaking.......: avg=0s       min=0s      med=0s       max=0s       p(90)=0s       p(95)=0s
http_req_waiting...............: avg=127.71ms min=16.1ms  med=71.15ms  max=582.74ms p(90)=379.32ms p(95)=434.02ms
http_reqs......................: 18      1.739444/s
iteration_duration.............: avg=1.72s    min=1.13s   med=1.38s    max=3.49s    p(90)=2.64s    p(95)=3.07s
iterations.....................: 6       0.579815/s
vus............................: 1       min=1      max=1
vus_max........................: 1       min=1      max=1
```
* smoke test (after)
```javascript
execution: local
script: smoke.js
output: -

scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.7s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

 ✓ logged in successfully
 ✓ retrieved member

 checks.........................: 100.00% ✓ 20       ✗ 0
 data_received..................: 40 kB   3.7 kB/s
 data_sent......................: 5.2 kB  485 B/s
 http_req_blocked...............: avg=2.12ms   min=200ns   med=500ns   max=63.65ms p(90)=1.72µs   p(95)=2µs
 http_req_connecting............: avg=202.65µs min=0s      med=0s      max=6.07ms  p(90)=0s       p(95)=0s
✓ http_req_duration..............: avg=20.19ms  min=12.94ms med=20.4ms  max=28.75ms p(90)=26.43ms  p(95)=27.92ms
   { expected_response:true }...: avg=20.19ms  min=12.94ms med=20.4ms  max=28.75ms p(90)=26.43ms  p(95)=27.92ms
 http_req_failed................: 0.00%   ✓ 0        ✗ 30
 http_req_receiving.............: avg=155.67µs min=66.69µs med=119µs   max=396.2µs p(90)=259.47µs p(95)=370.83µs
 http_req_sending...............: avg=84.75µs  min=19µs    med=62.2µs  max=458.9µs p(90)=120.97µs p(95)=185.22µs
 http_req_tls_handshaking.......: avg=935.45µs min=0s      med=0s      max=28.06ms p(90)=0s       p(95)=0s
 http_req_waiting...............: avg=19.95ms  min=12.85ms med=19.96ms max=28.56ms p(90)=26.2ms   p(95)=27.6ms
 http_reqs......................: 30      2.806285/s
 iteration_duration.............: avg=1.06s    min=1.05s   med=1.06s   max=1.12s   p(90)=1.07s    p(95)=1.1s
 iterations.....................: 10      0.935428/s
 vus............................: 1       min=1      max=1
 vus_max........................: 1       min=1      max=1
```

* load test (before)
```javascript
execution: local
script: load.js
output: -

    scenarios: (100.00%) 1 scenario, 220 max VUs, 2m10s max duration (incl. graceful stop):
* default: Up to 220 looping VUs for 1m40s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m40.7s), 000/220 VUs, 8697 complete and 0 interrupted iterations
default ✓ [======================================] 000/220 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member

checks.........................: 100.00% ✓ 17394      ✗ 0
data_received..................: 29 MB   292 kB/s
data_sent......................: 6.1 MB  61 kB/s
http_req_blocked...............: avg=221.5µs  min=1µs    med=5µs      max=452.58ms p(90)=8µs      p(95)=12µs
http_req_connecting............: avg=214.37µs min=0s     med=0s       max=452.4ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=173.17ms min=9.22ms med=157.59ms max=1.25s    p(90)=343.38ms p(95)=387.23ms
{ expected_response:true }...: avg=173.17ms min=9.22ms med=157.59ms max=1.25s    p(90)=343.38ms p(95)=387.23ms
http_req_failed................: 0.00%   ✓ 0          ✗ 26091
http_req_receiving.............: avg=556.26µs min=18µs   med=94µs     max=75.21ms  p(90)=1.42ms   p(95)=2.38ms
http_req_sending...............: avg=35.35µs  min=7µs    med=30µs     max=1.09ms   p(90)=56µs     p(95)=75µs
http_req_tls_handshaking.......: avg=0s       min=0s     med=0s       max=0s       p(90)=0s       p(95)=0s
http_req_waiting...............: avg=172.57ms min=8.78ms med=156.97ms max=1.25s    p(90)=342.56ms p(95)=386.4ms
http_reqs......................: 26091   259.010937/s
iteration_duration.............: avg=1.52s    min=1.07s  med=1.53s    max=3s       p(90)=1.99s    p(95)=2.07s
iterations.....................: 8697    86.336979/s
vus............................: 12      min=4        max=219
vus_max........................: 220     min=220      max=220
```

* load test (after)
```javascript
execution: local
script: load.js
output: -

scenarios: (100.00%) 1 scenario, 220 max VUs, 2m10s max duration (incl. graceful stop):
   * default: Up to 220 looping VUs for 1m40s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m40.9s), 000/220 VUs, 12521 complete and 0 interrupted iterations
default ✓ [======================================] 000/220 VUs  1m40s

 ✓ logged in successfully
 ✓ retrieved member

 checks.........................: 100.00% ✓ 25042      ✗ 0
 data_received..................: 45 MB   445 kB/s
 data_sent......................: 5.9 MB  59 kB/s
 http_req_blocked...............: avg=201.47µs min=200ns  med=500ns   max=1.05s    p(90)=1.1µs    p(95)=1.6µs
 http_req_connecting............: avg=97.22µs  min=0s     med=0s      max=1.03s    p(90)=0s       p(95)=0s
✓ http_req_duration..............: avg=17.15ms  min=8.67ms med=16.09ms max=236.39ms p(90)=23.47ms  p(95)=26.06ms
   { expected_response:true }...: avg=17.15ms  min=8.67ms med=16.09ms max=236.39ms p(90)=23.47ms  p(95)=26.06ms
 http_req_failed................: 0.00%   ✓ 0          ✗ 37563
 http_req_receiving.............: avg=213.86µs min=22.2µs med=90.8µs  max=17.15ms  p(90)=276.98µs p(95)=541.55µs
 http_req_sending...............: avg=75.54µs  min=14.4µs med=46.5µs  max=7ms      p(90)=145.19µs p(95)=212µs
 http_req_tls_handshaking.......: avg=101.59µs min=0s     med=0s      max=31.99ms  p(90)=0s       p(95)=0s
 http_req_waiting...............: avg=16.86ms  min=8.56ms med=15.81ms max=236.13ms p(90)=23.29ms  p(95)=25.87ms
 http_reqs......................: 37563   372.401331/s
 iteration_duration.............: avg=1.05s    min=1.04s  med=1.05s   max=2.1s     p(90)=1.06s    p(95)=1.07s
 iterations.....................: 12521   124.133777/s
 vus............................: 13      min=4        max=219
 vus_max........................: 220     min=220      max=220
```

* stress test (before)
```javascript
execution: local
script: stress.js
output: -

    scenarios: (100.00%) 1 scenario, 240 max VUs, 2m50s max duration (incl. graceful stop):
* default: Up to 240 looping VUs for 2m20s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m21.0s), 000/240 VUs, 9856 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  2m20s

     ✓ logged in successfully
     ✓ retrieved member

checks.........................: 100.00% ✓ 19712      ✗ 0
data_received..................: 35 MB   246 kB/s
data_sent......................: 7.0 MB  50 kB/s
http_req_blocked...............: avg=113.53µs min=1µs     med=4µs     max=244.59ms p(90)=8µs      p(95)=11µs
http_req_connecting............: avg=107.36µs min=0s      med=0s      max=244.53ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=148.16ms min=10.45ms med=89.28ms max=1.34s    p(90)=360.68ms p(95)=418.18ms
{ expected_response:true }...: avg=148.16ms min=10.45ms med=89.28ms max=1.34s    p(90)=360.68ms p(95)=418.18ms
http_req_failed................: 0.00%   ✓ 0          ✗ 29568
http_req_receiving.............: avg=134.07µs min=16µs    med=72µs    max=124.22ms p(90)=177µs    p(95)=283µs
http_req_sending...............: avg=33.42µs  min=6µs     med=25µs    max=5.32ms   p(90)=54µs     p(95)=72µs
http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s       p(90)=0s       p(95)=0s
http_req_waiting...............: avg=147.99ms min=10.37ms med=89.06ms max=1.34s    p(90)=360.52ms p(95)=418.04ms
http_reqs......................: 29568   209.687976/s
iteration_duration.............: avg=1.44s    min=1.08s   med=1.25s   max=3.41s    p(90)=2.04s    p(95)=2.23s
iterations.....................: 9856    69.895992/s
vus............................: 1       min=1        max=239
vus_max........................: 240     min=240      max=240
```

* stress test (after)
```javascript
execution: local
script: stress.js
output: -

scenarios: (100.00%) 1 scenario, 300 max VUs, 3m10s max duration (incl. graceful stop):
       * default: Up to 300 looping VUs for 2m40s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m40.6s), 000/300 VUs, 18905 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  2m40s

 ✓ logged in successfully
 ✓ retrieved member

 checks.........................: 100.00% ✓ 37810      ✗ 0
 data_received..................: 68 MB   421 kB/s
 data_sent......................: 8.9 MB  56 kB/s
 http_req_blocked...............: avg=178.89µs min=100ns  med=500ns   max=1.09s   p(90)=900ns   p(95)=1.3µs
 http_req_connecting............: avg=91.4µs   min=0s     med=0s      max=1.07s   p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=40.13ms  min=8.74ms med=19.33ms max=1.25s   p(90)=81.72ms p(95)=152.42ms
   { expected_response:true }...: avg=40.13ms  min=8.74ms med=19.33ms max=1.25s   p(90)=81.72ms p(95)=152.42ms
 http_req_failed................: 0.00%   ✓ 0          ✗ 56715
 http_req_receiving.............: avg=180.83µs min=22.8µs med=78.39µs max=13.18ms p(90)=231µs   p(95)=448.75µs
 http_req_sending...............: avg=67.37µs  min=14.2µs med=42µs    max=6.27ms  p(90)=126.6µs p(95)=177.62µs
 http_req_tls_handshaking.......: avg=85.23µs  min=0s     med=0s      max=30.32ms p(90)=0s      p(95)=0s
 http_req_waiting...............: avg=39.88ms  min=8.41ms med=19.1ms  max=1.25s   p(90)=81.59ms p(95)=152.27ms
 http_reqs......................: 56715   353.225353/s
 iteration_duration.............: avg=1.12s    min=1.03s  med=1.06s   max=2.47s   p(90)=1.27s   p(95)=1.42s
 iterations.....................: 18905   117.741784/s
 vus............................: 3       min=2        max=300
 vus_max........................: 300     min=300      max=300
```
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

    * nginx
        - gzip compress 적용
        - css, js, gif, png, jpg, jpeg에 대해 nginx 캐시 적용
        - http 2.0 적용
    * redis
        - private 서버에 redis 설치
        - 운영 프로필에서 redis 연동하여 캐싱 처리
        - 자주 사용되는 조회 기능에 캐시 적용

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

