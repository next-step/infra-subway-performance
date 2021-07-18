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
        - smoke
            - [script](./k6-script/path-page/smoke.js)
            - result
            ```renderscript
           checks.........................: 100.00% ✓ 2811       ✗ 0
           data_received..................: 2.1 MB  211 kB/s
           data_sent......................: 608 kB  61 kB/s
           http_req_blocked...............: avg=21.93µs min=3.61µs  med=4.68µs  max=39.11ms  p(90)=5.09µs  p(95)=5.45µs
           http_req_connecting............: avg=539ns   min=0s      med=0s      max=602.98µs p(90)=0s      p(95)=0s
           ✓ http_req_duration..............: avg=3.37ms  min=1.54ms  med=2.94ms  max=19.76ms  p(90)=5.19ms  p(95)=6.46ms
           { expected_response:true }...: avg=3.37ms  min=1.54ms  med=2.94ms  max=19.76ms  p(90)=5.19ms  p(95)=6.46ms
           http_req_failed................: 0.00%   ✓ 0          ✗ 2811
           http_req_receiving.............: avg=56.66µs min=31.36µs med=50.61µs max=2.59ms   p(90)=70.43µs p(95)=78µs
           http_req_sending...............: avg=16.73µs min=11.83µs med=14.49µs max=546.43µs p(90)=18.93µs p(95)=21.42µs
           http_req_tls_handshaking.......: avg=16.1µs  min=0s      med=0s      max=37.9ms   p(90)=0s      p(95)=0s
           http_req_waiting...............: avg=3.3ms   min=1.49ms  med=2.86ms  max=19.67ms  p(90)=5.12ms  p(95)=6.39ms
           http_reqs......................: 2811    280.953778/s
           iteration_duration.............: avg=10.66ms min=7.75ms  med=9.63ms  max=62.3ms   p(90)=14.07ms p(95)=15.6ms
           iterations.....................: 937     93.651259/s
           vus............................: 1       min=1        max=1
           vus_max........................: 1       min=1        max=1
            ```
        - load
            - [script](./k6-script/path-page/load.js)
            - result
            ```renderscript
           checks.........................: 100.00% ✓ 67581      ✗ 0
           data_received..................: 52 MB   736 kB/s
           data_sent......................: 15 MB   210 kB/s
           http_req_blocked...............: avg=650.29µs min=3.14µs  med=4.45µs   max=793.78ms p(90)=6.27µs   p(95)=9.74µs
           http_req_connecting............: avg=187.88µs min=0s      med=0s       max=240.46ms p(90)=0s       p(95)=0s
           ✓ http_req_duration..............: avg=72.53ms  min=1.42ms  med=55.36ms  max=1.33s    p(90)=161.76ms p(95)=202.79ms
           { expected_response:true }...: avg=72.53ms  min=1.42ms  med=55.36ms  max=1.33s    p(90)=161.76ms p(95)=202.79ms
           http_req_failed................: 0.00%   ✓ 0          ✗ 67581
           http_req_receiving.............: avg=709.84µs min=22.89µs med=43µs     max=263.28ms p(90)=112.41µs p(95)=922.9µs
           http_req_sending...............: avg=371.15µs min=8.66µs  med=15.41µs  max=237.42ms p(90)=31.39µs  p(95)=73.82µs
           http_req_tls_handshaking.......: avg=399.72µs min=0s      med=0s       max=634.32ms p(90)=0s       p(95)=0s
           http_req_waiting...............: avg=71.45ms  min=1.34ms  med=54.64ms  max=1.33s    p(90)=159.48ms p(95)=199.59ms
           http_reqs......................: 67581   965.383787/s
           iteration_duration.............: avg=225.38ms min=6.98ms  med=189.83ms max=1.78s    p(90)=475.71ms p(95)=560.41ms
           iterations.....................: 22527   321.794596/s
           vus............................: 0       min=0        max=200
           vus_max........................: 200     min=200      max=200
            ```
        - stress
            - [script](./k6-script/path-page/stress.js)
            - result
            ```renderscript
           checks.........................: 100.00% ✓ 68580       ✗ 0
           data_received..................: 52 MB   947 kB/s
           data_sent......................: 15 MB   270 kB/s
           http_req_blocked...............: avg=631.4µs  min=3.11µs  med=4.32µs   max=771.3ms  p(90)=6.33µs   p(95)=10.4µs
           http_req_connecting............: avg=185.36µs min=0s      med=0s       max=318.46ms p(90)=0s       p(95)=0s
           ✓ http_req_duration..............: avg=100.2ms  min=1.53ms  med=89.1ms   max=1.42s    p(90)=184.6ms  p(95)=222.39ms
           { expected_response:true }...: avg=100.2ms  min=1.53ms  med=89.1ms   max=1.42s    p(90)=184.6ms  p(95)=222.39ms
           http_req_failed................: 0.00%   ✓ 0           ✗ 68580
           http_req_receiving.............: avg=917.52µs min=22.85µs med=40.69µs  max=226.87ms p(90)=207.79µs p(95)=1.26ms
           http_req_sending...............: avg=396.23µs min=9.62µs  med=15.37µs  max=245.01ms p(90)=35.32µs  p(95)=152.55µs
           http_req_tls_handshaking.......: avg=388.33µs min=0s      med=0s       max=503.69ms p(90)=0s       p(95)=0s
           http_req_waiting...............: avg=98.89ms  min=1.47ms  med=88.02ms  max=1.42s    p(90)=181.88ms p(95)=218.13ms
           http_reqs......................: 68580   1245.051096/s
           iteration_duration.............: avg=307.26ms min=7.39ms  med=305.51ms max=1.77s    p(90)=493.27ms p(95)=553.4ms
           iterations.....................: 22860   415.017032/s
           vus............................: 49      min=10        max=200
           vus_max........................: 200     min=200       max=200
            ```
    - 개선후
        - smoke
          - result
            ```renderscript
               checks.........................: 100.00% ✓ 3669       ✗ 0
               data_received..................: 2.3 MB  226 kB/s
               data_sent......................: 420 kB  42 kB/s
               http_req_blocked...............: avg=15.25µs min=2.67µs   med=2.78µs  max=29.73ms  p(90)=2.91µs   p(95)=2.97µs
               http_req_connecting............: avg=578ns   min=0s       med=0s      max=578.1µs  p(90)=0s       p(95)=0s
               ✓ http_req_duration..............: avg=2.52ms  min=1.49ms   med=2.52ms  max=12.38ms  p(90)=3.16ms   p(95)=3.45ms
               { expected_response:true }...: avg=2.25ms  min=1.49ms   med=2.26ms  max=10.56ms  p(90)=2.77ms   p(95)=2.98ms
               http_req_failed................: 33.33%  ✓ 1223       ✗ 2446
               http_req_receiving.............: avg=70.16µs min=46.25µs  med=66.23µs max=446.8µs  p(90)=79.61µs  p(95)=96.2µs
               http_req_sending...............: avg=64.15µs min=24.9µs   med=60.67µs max=763.56µs p(90)=105.62µs p(95)=117.27µs
               http_req_tls_handshaking.......: avg=11.04µs min=0s       med=0s      max=28.34ms  p(90)=0s       p(95)=0s
               http_req_waiting...............: avg=2.38ms  min=923.83µs med=2.39ms  max=12.21ms  p(90)=2.99ms   p(95)=3.28ms
               http_reqs......................: 3669    366.578671/s
               iteration_duration.............: avg=8.16ms  min=6.57ms   med=7.75ms  max=40.45ms  p(90)=9.07ms   p(95)=11.02ms
               iterations.....................: 1223    122.19289/s
               vus............................: 1       min=1        max=1
               vus_max........................: 1       min=1        max=1
            ```
        - load
          - result
          ```renderscript
               checks.........................: 100.00% ✓ 81294      ✗ 0
               data_received..................: 51 MB   724 kB/s
               data_sent......................: 9.4 MB  134 kB/s
               http_req_blocked...............: avg=512.42µs min=2.2µs   med=2.73µs   max=552.32ms p(90)=2.93µs   p(95)=3.17µs
               http_req_connecting............: avg=152.18µs min=0s      med=0s       max=197.46ms p(90)=0s       p(95)=0s
               ✓ http_req_duration..............: avg=60.84ms  min=1.49ms  med=48.61ms  max=1.35s    p(90)=126.11ms p(95)=153.71ms
               { expected_response:true }...: avg=62.35ms  min=1.49ms  med=50.57ms  max=1.35s    p(90)=129.04ms p(95)=157.19ms
               http_req_failed................: 33.33%  ✓ 27098      ✗ 54196
               http_req_receiving.............: avg=859.34µs min=31.43µs med=54.09µs  max=225.37ms p(90)=208.64µs p(95)=1.35ms
               http_req_sending...............: avg=523.94µs min=16.19µs med=43.49µs  max=190.32ms p(90)=122.18µs p(95)=474.13µs
               http_req_tls_handshaking.......: avg=325.15µs min=0s      med=0s       max=436.84ms p(90)=0s       p(95)=0s
               http_req_waiting...............: avg=59.46ms  min=0s      med=47.45ms  max=1.21s    p(90)=123.71ms p(95)=150.45ms
               http_reqs......................: 81294   1161.26645/s
               iteration_duration.............: avg=187.26ms min=6.38ms  med=159.97ms max=1.67s    p(90)=376.33ms p(95)=437.1ms
               iterations.....................: 27098   387.088817/s
               vus............................: 1       min=1        max=200
               vus_max........................: 200     min=200      max=200
            ```
        - stress
          - result
          ```renderscript
               checks.........................: 100.00% ✓ 103302      ✗ 0
               data_received..................: 64 MB   1.2 MB/s
               data_sent......................: 12 MB   216 kB/s
               http_req_blocked...............: avg=296.07µs min=2.13µs  med=2.73µs  max=388.3ms  p(90)=2.96µs   p(95)=3.66µs
               http_req_connecting............: avg=88.61µs  min=0s      med=0s      max=184.55ms p(90)=0s       p(95)=0s
               ✓ http_req_duration..............: avg=66.71ms  min=1.4ms   med=62.94ms max=1.31s    p(90)=113.65ms p(95)=131.73ms
               { expected_response:true }...: avg=68.85ms  min=1.4ms   med=65.67ms max=1.31s    p(90)=116.55ms p(95)=134.19ms
               http_req_failed................: 33.33%  ✓ 34434       ✗ 68868
               http_req_receiving.............: avg=630.69µs min=29.99µs med=49.11µs max=202.88ms p(90)=145.42µs p(95)=473.07µs
               http_req_sending...............: avg=302.15µs min=15.61µs med=39.94µs max=161.35ms p(90)=97.68µs  p(95)=197.81µs
               http_req_tls_handshaking.......: avg=182.87µs min=0s      med=0s      max=311.32ms p(90)=0s       p(95)=0s
               http_req_waiting...............: avg=65.78ms  min=0s      med=62.3ms  max=1.31s    p(90)=112.17ms p(95)=129.31ms
               http_reqs......................: 103302  1875.426782/s
               iteration_duration.............: avg=203.89ms min=5.8ms   med=208.7ms max=1.46s    p(90)=319.11ms p(95)=353.68ms
               iterations.....................: 34434   625.142261/s
               vus............................: 51      min=10        max=200
               vus_max........................: 200     min=200       max=200
            ```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Reverse Proxy 개선
    - gzip 압축 활용
    - http2.0 설정
    - access_log off
- WAS 개선
    - redis 추가
    - Line , Path 캐싱 추가
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
    - [result](./markdown/step2.md)
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
    - https://fdevjc-subway.kro.kr/favorites


