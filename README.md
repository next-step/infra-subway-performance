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

> smoke

```
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


running (10.1s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ main page 200

     checks.........................: 100.00% ✓ 10       ✗ 0
     data_received..................: 16 kB   1.6 kB/s
     data_sent......................: 993 B   98 B/s
     http_req_blocked...............: avg=4.52ms   min=2.91µs  med=3µs     max=45.25ms  p(90)=4.52ms   p(95)=24.89ms
     http_req_connecting............: avg=91.41µs  min=0s      med=0s      max=914.12µs p(90)=91.41µs  p(95)=502.76µs
   ✓ http_req_duration..............: avg=4.55ms   min=3.46ms  med=3.72ms  max=7.81ms   p(90)=7.1ms    p(95)=7.45ms
       { expected_response:true }...: avg=4.55ms   min=3.46ms  med=3.72ms  max=7.81ms   p(90)=7.1ms    p(95)=7.45ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=62.41µs  min=50.49µs med=59.76µs max=86.88µs  p(90)=73.69µs  p(95)=80.28µs
     http_req_sending...............: avg=301.29µs min=83.04µs med=87.47µs max=2.21ms   p(90)=307.38µs p(95)=1.26ms
     http_req_tls_handshaking.......: avg=2.72ms   min=0s      med=0s      max=27.29ms  p(90)=2.72ms   p(95)=15.01ms
     http_req_waiting...............: avg=4.18ms   min=3.31ms  med=3.57ms  max=6.86ms   p(90)=5.65ms   p(95)=6.26ms
     http_reqs......................: 10      0.990044/s
     iteration_duration.............: avg=1s       min=1s      med=1s      max=1.05s    p(90)=1.01s    p(95)=1.03s
     iterations.....................: 10      0.990044/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

> load

```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 7 max VUs, 7m30s max duration (incl. graceful stop):
           * default: Up to 7 looping VUs for 7m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (7m00.0s), 0/7 VUs, 311767 complete and 0 interrupted iterations
default ✗ [======================================] 0/7 VUs  7m0s

     ✓ main page 200

     checks.........................: 100.00% ✓ 311767     ✗ 0
     data_received..................: 366 MB  871 kB/s
     data_sent......................: 14 MB   32 kB/s
     http_req_blocked...............: avg=11.05µs min=2.29µs   med=2.82µs  max=27.37ms p(90)=3.05µs  p(95)=3.27µs
     http_req_connecting............: avg=737ns   min=0s       med=0s      max=7.38ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=4.78ms  min=789.82µs med=2.89ms  max=35.75ms p(90)=10.91ms p(95)=12.28ms
       { expected_response:true }...: avg=4.78ms  min=789.82µs med=2.89ms  max=35.75ms p(90)=10.91ms p(95)=12.28ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 311767
     http_req_receiving.............: avg=39.89µs min=14.73µs  med=36.74µs max=9.28ms  p(90)=45.89µs p(95)=52.84µs
     http_req_sending...............: avg=46.48µs min=23.12µs  med=43.86µs max=8.29ms  p(90)=57.1µs  p(95)=60.82µs
     http_req_tls_handshaking.......: avg=6.54µs  min=0s       med=0s      max=18.16ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=4.7ms   min=723.88µs med=2.8ms   max=35.48ms p(90)=10.82ms p(95)=12.19ms
     http_reqs......................: 311767  742.296023/s
     iteration_duration.............: avg=4.89ms  min=872.93µs med=2.99ms  max=38.85ms p(90)=11.03ms p(95)=12.4ms
     iterations.....................: 311767  742.296023/s
     vus............................: 1       min=1        max=7
     vus_max........................: 7       min=7        max=7
```

> stress

```
running (6m00.0s), 000/300 VUs, 696822 complete and 0 interrupted iterations
default ✗ [======================================] 000/300 VUs  6m0s

     ✗ main page 200
      ↳  99% — ✓ 696629 / ✗ 193

     checks.........................: 99.97% ✓ 696629      ✗ 193
     data_received..................: 838 MB 2.3 MB/s
     data_sent......................: 33 MB  92 kB/s
     http_req_blocked...............: avg=1.76ms   min=0s       med=2.76µs  max=4.53s   p(90)=2.94µs   p(95)=3.09µs
     http_req_connecting............: avg=2.5µs    min=0s       med=0s      max=33.05ms p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=77.26ms  min=0s       med=40.47ms max=7.52s   p(90)=150ms    p(95)=280.01ms
       { expected_response:true }...: avg=77.21ms  min=782.9µs  med=40.46ms max=7.52s   p(90)=149.78ms p(95)=279.96ms
     http_req_failed................: 0.02%  ✓ 193         ✗ 696629
     http_req_receiving.............: avg=477.58µs min=0s       med=44.42µs max=58.53ms p(90)=890.99µs p(95)=1.93ms
     http_req_sending...............: avg=1.8ms    min=0s       med=38.98µs max=7.46s   p(90)=58.7µs   p(95)=70.47µs
     http_req_tls_handshaking.......: avg=289.82µs min=0s       med=0s      max=4.18s   p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=74.97ms  min=0s       med=39.83ms max=4.31s   p(90)=142.94ms p(95)=273.48ms
     http_reqs......................: 696822 1935.588767/s
     iteration_duration.............: avg=77.66ms  min=864.27µs med=40.6ms  max=7.52s   p(90)=150.62ms p(95)=280.66ms
     iterations.....................: 696822 1935.588767/s
     vus............................: 1      min=1         max=300
     vus_max........................: 300    min=300       max=300
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

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
