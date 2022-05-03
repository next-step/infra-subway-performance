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

**WebPageTest 결과**

- Mobile(Chrome, LTE)

| 항목 | 평균값 |
|--|--|
| FCP | 3639ms |
| TTI | 3671ms |

- Desktop(Chrome, Cable)

| 항목 | 평균값 |
|--|--|
| FCP | 1719ms |
| TTI | 1707ms |

```
$ k6 run smoke.js

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


running (10.3s), 0/1 VUs, 8 complete and 0 interrupted iterations
default ↓ [======================================] 1 VUs  10s

     ✓ retrieved stations
     ✓ retrieved paths

     checks.........................: 100.00% ✓ 16       ✗ 0
     data_received..................: 606 kB  59 kB/s
     data_sent......................: 4.0 kB  387 B/s
     http_req_blocked...............: avg=40.84ms  min=282ns   med=354ns   max=653.51ms p(90)=587ns    p(95)=163.37ms
     http_req_connecting............: avg=57.83µs  min=0s      med=0s      max=925.37µs p(90)=0s       p(95)=231.34µs
   ✓ http_req_duration..............: avg=101.31ms min=38.12ms med=92.02ms max=264.45ms p(90)=198.99ms p(95)=260.06ms
       { expected_response:true }...: avg=101.31ms min=38.12ms med=92.02ms max=264.45ms p(90)=198.99ms p(95)=260.06ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 16
     http_req_receiving.............: avg=2.61ms   min=49.91µs med=1.45ms  max=10.14ms  p(90)=6.75ms   p(95)=8.24ms
     http_req_sending...............: avg=60.11µs  min=37µs    med=50.61µs max=156.21µs p(90)=77.85µs  p(95)=102.75µs
     http_req_tls_handshaking.......: avg=859.85µs min=0s      med=0s      max=13.75ms  p(90)=0s       p(95)=3.43ms
     http_req_waiting...............: avg=98.64ms  min=34.53ms med=91.92ms max=258.98ms p(90)=198.88ms p(95)=258.61ms
     http_reqs......................: 16      1.554039/s
     iteration_duration.............: avg=1.28s    min=1.13s   med=1.14s   max=2.05s    p(90)=1.57s    p(95)=1.81s
     iterations.....................: 8       0.77702/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

```
$ k6 run load.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 46 max VUs, 6m30s max duration (incl. graceful stop):
           * default: Up to 46 looping VUs for 6m0s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (6m00.5s), 00/46 VUs, 4291 complete and 0 interrupted iterations
default ↓ [======================================] 01/46 VUs  6m0s

     ✓ retrieved stations
     ✓ retrieved paths

     checks.........................: 100.00% ✓ 8582      ✗ 0
     data_received..................: 323 MB  895 kB/s
     data_sent......................: 1.8 MB  4.9 kB/s
     http_req_blocked...............: avg=91.73µs  min=150ns   med=326ns    max=534.16ms p(90)=584ns   p(95)=641ns
     http_req_connecting............: avg=4.84µs   min=0s      med=0s       max=2.01ms   p(90)=0s      p(95)=0s
   ✗ http_req_duration..............: avg=796.84ms min=31.49ms med=896.39ms max=2.32s    p(90)=1.31s   p(95)=1.38s
       { expected_response:true }...: avg=796.84ms min=31.49ms med=896.39ms max=2.32s    p(90)=1.31s   p(95)=1.38s
     http_req_failed................: 0.00%   ✓ 0         ✗ 8582
     http_req_receiving.............: avg=2.58ms   min=23.72µs med=1.07ms   max=112.4ms  p(90)=6.19ms  p(95)=8.69ms
     http_req_sending...............: avg=77.79µs  min=19.26µs med=51.65µs  max=6.24ms   p(90)=80.41µs p(95)=100.27µs
     http_req_tls_handshaking.......: avg=25.15µs  min=0s      med=0s       max=14.57ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=794.17ms min=29.75ms med=892.72ms max=2.32s    p(90)=1.31s   p(95)=1.38s
     http_reqs......................: 8582    23.804515/s
     iteration_duration.............: avg=2.59s    min=1.1s    med=2.89s    max=4.29s    p(90)=3.43s   p(95)=3.54s
     iterations.....................: 4291    11.902257/s
     vus............................: 1       min=1       max=46
     vus_max........................: 46      min=46      max=46
```

```
$ k6 run stress.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 3680 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 3680 looping VUs for 7m0s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m15.2s), 0000/3680 VUs, 6556 complete and 288 interrupted iterations
default ✗ [===========>--------------------------] 0086/3680 VUs  2m15.2s/7m00.0s

     ✓ retrieved stations
     ✓ retrieved paths

     checks.........................: 100.00% ✓ 3463      ✗ 0
     data_received..................: 144 MB  1.1 MB/s
     data_sent......................: 3.4 MB  25 kB/s
     http_req_blocked...............: avg=241.82µs min=0s       med=0s       max=28.69ms  p(90)=548ns   p(95)=749ns
     http_req_connecting............: avg=2.99ms   min=0s       med=853.48µs max=49.12ms  p(90)=9.05ms  p(95)=14.55ms
   ✗ http_req_duration..............: avg=1.23s    min=0s       med=3.87ms   max=21.88s   p(90)=5.23s   p(95)=6.71s
       { expected_response:true }...: avg=2.96s    min=30.8ms   med=2.16s    max=21.88s   p(90)=6.89s   p(95)=7.54s
     http_req_failed................: 58.60%  ✓ 4903      ✗ 3463
     http_req_receiving.............: avg=1.04ms   min=0s       med=0s       max=84.82ms  p(90)=3.91ms  p(95)=5.16ms
     http_req_sending...............: avg=2.02ms   min=0s       med=35.58µs  max=489.24ms p(90)=99.17µs p(95)=143.56µs
     http_req_tls_handshaking.......: avg=1.69ms   min=0s       med=0s       max=73.41ms  p(90)=4.71ms  p(95)=12.14ms
     http_req_waiting...............: avg=1.22s    min=0s       med=3.6ms    max=21.84s   p(90)=5.23s   p(95)=6.71s
     http_reqs......................: 8366    61.888182/s
     iteration_duration.............: avg=1.72s    min=897.84µs med=13.12ms  max=21.9s    p(90)=8.11s   p(95)=11.75s
     iterations.....................: 6556    48.498556/s
     vus............................: 287     min=1       max=287
     vus_max........................: 3680    min=3680    max=3680
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

Reverse Proxy 개선
- gzip 압축
- cache
- HTTP/2

WAS 성능 개선
- Spring Data Cache

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
