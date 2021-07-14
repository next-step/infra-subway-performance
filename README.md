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
        - load
        - stress

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

