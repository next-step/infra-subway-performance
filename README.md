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

#### Smoke
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';


export let options = {
    vus: 1, // 1 user looping for 1 minute
    duration: '10s',
};
const BASE_URL = 'https://kwj1270.ga';

export default () => {

    // main page
    let mainUrl = `${BASE_URL}`;
    let mainPageResponse = http.get(mainUrl);
    check(mainPageResponse, {
        'main page running': (response) => response.status === 200
    });

    // GangNam search line
    let GangNamSearchLineUrl = `${BASE_URL}/paths/?source=3&target=106`;
    let GangNamSearchLineResponse = http.get(GangNamSearchLineUrl);
    check(GangNamSearchLineResponse, {
        'GangNam line searching success': (response) => response.status === 200
    });

    // Gasan search line
    let GasanSearchLineUrl = `${BASE_URL}/paths/?source=3&target=24`;
    let GasanSearchLineResponse = http.get(GasanSearchLineUrl);
    check(GasanSearchLineResponse, {
        'Gasan line searching success': (response) => response.status === 200
    });

    // YangJae search line
    let YangJaeSearchLineUrl = `${BASE_URL}/paths/?source=3&target=150`;
    let YangJaeSearchLineResponse = http.get(YangJaeSearchLineUrl);
    check(YangJaeSearchLineResponse, {
        'YangJae line searching success': (response) => response.status === 200
    });

};
```
**개선전**
```shell

```
**개선후**
```shell
running (10.0s), 0/1 VUs, 2175 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ main page running
     ✓ GangNam line searching success
     ✓ Gasan line searching success
     ✓ YangJae line searching success

     checks.........................: 100.00% ✓ 8700 ✗ 0
     data_received..................: 11 MB   1.1 MB/s
     data_sent......................: 745 kB  74 kB/s
     http_req_blocked...............: avg=48.09µs  min=232ns    med=400ns    max=387.39ms p(90)=471ns    p(95)=504ns
     http_req_connecting............: avg=246ns    min=0s       med=0s       max=281.38µs p(90)=0s       p(95)=0s
     http_req_duration..............: avg=969.68µs min=620.71µs med=985.78µs max=15.3ms   p(90)=1.07ms   p(95)=1.14ms
       { expected_response:true }...: avg=969.68µs min=620.71µs med=985.78µs max=15.3ms   p(90)=1.07ms   p(95)=1.14ms
     http_req_failed................: 0.00%   ✓ 0    ✗ 8700
     http_req_receiving.............: avg=53.87µs  min=31.11µs  med=46.09µs  max=6.15ms   p(90)=69.49µs  p(95)=79.12µs
     http_req_sending...............: avg=24.57µs  min=16.58µs  med=21.21µs  max=1.5ms    p(90)=33.66µs  p(95)=47.87µs
     http_req_tls_handshaking.......: avg=7.87µs   min=0s       med=0s       max=45.2ms   p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=891.24µs min=547.61µs med=915.13µs max=15.23ms  p(90)=993.85µs p(95)=1.04ms
     http_reqs......................: 8700    869.804855/s
     iteration_duration.............: avg=4.58ms   min=3.84ms   med=4.2ms    max=395.04ms p(90)=4.62ms   p(95)=5.31ms
     iterations.....................: 2175    217.451214/s
     vus............................: 1       min=1  max=1
     vus_max........................: 1       min=1  max=1
```

#### Load
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';


export let options = {
    stages: [
        { duration: '1m', target: 100 }, // simulate ramp-up of traffic from 1 to 100 users over 1 minutes.
        { duration: '2m', target: 100 }, // stay at 100 users for 2 minutes
        { duration: '10s', target: 0 }, // ramp-down to 0 users
    ],
};

const BASE_URL = 'https://kwj1270.ga';

export default () => {

    // main page
    let mainUrl = `${BASE_URL}`;
    let mainPageResponse = http.get(mainUrl);
    check(mainPageResponse, {
        'main page running': (response) => response.status === 200
    });

    // GangNam search line
    let GangNamSearchLineUrl = `${BASE_URL}/paths/?source=3&target=106`;
    let GangNamSearchLineResponse = http.get(GangNamSearchLineUrl);
    check(GangNamSearchLineResponse, {
        'GangNam line searching success': (response) => response.status === 200
    });

    // Gasan search line
    let GasanSearchLineUrl = `${BASE_URL}/paths/?source=3&target=24`;
    let GasanSearchLineResponse = http.get(GasanSearchLineUrl);
    check(GasanSearchLineResponse, {
        'Gasan line searching success': (response) => response.status === 200
    });

    // YangJae search line
    let YangJaeSearchLineUrl = `${BASE_URL}/paths/?source=3&target=150`;
    let YangJaeSearchLineResponse = http.get(YangJaeSearchLineUrl);
    check(YangJaeSearchLineResponse, {
        'YangJae line searching success': (response) => response.status === 200
    });

};
```
**개선전**
```shell

```
**개선후**
```shell
running (3m10.0s), 000/100 VUs, 57678 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ main page running
     ✓ GangNam line searching success
     ✓ Gasan line searching success
     ✓ YangJae line searching success

     checks.........................: 100.00% ✓ 230712 ✗ 0
     data_received..................: 298 MB  1.6 MB/s
     data_sent......................: 20 MB   104 kB/s
     http_req_blocked...............: avg=150.49µs min=194ns    med=372ns    max=367.63ms p(90)=459ns    p(95)=527ns
     http_req_connecting............: avg=38.32µs  min=0s       med=0s       max=140.61ms p(90)=0s       p(95)=0s
     http_req_duration..............: avg=66.42ms  min=630.96µs med=55.33ms  max=402.79ms p(90)=129.76ms p(95)=175.11ms
       { expected_response:true }...: avg=66.42ms  min=630.96µs med=55.33ms  max=402.79ms p(90)=129.76ms p(95)=175.11ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 230712
     http_req_receiving.............: avg=741.9µs  min=20.33µs  med=57.95µs  max=225.55ms p(90)=717µs    p(95)=1.78ms
     http_req_sending...............: avg=164.11µs min=8.85µs   med=25.98µs  max=207.84ms p(90)=62.38µs  p(95)=227.43µs
     http_req_tls_handshaking.......: avg=106.51µs min=0s       med=0s       max=291.56ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=65.52ms  min=2.41µs   med=54.54ms  max=402.72ms p(90)=128.24ms p(95)=174.01ms
     http_reqs......................: 230712  1214.241103/s
     iteration_duration.............: avg=269.06ms min=4.02ms   med=239.56ms max=1.2s     p(90)=461.38ms p(95)=644.17ms
     iterations.....................: 57678   303.560276/s
     vus............................: 1       min=1    max=100
     vus_max........................: 100     min=100  max=100
```

#### Stress
```javascript
import http from 'k6/http';
import {check} from 'k6';

export let options = {
    stages: [
      { duration: '2s', target: 100 }, // below normal load
      { duration: '5s', target: 200 },
      { duration: '2s', target: 300 }, // normal load
      { duration: '5s', target: 400 },
      { duration: '1m', target: 1000 }, // sudden traffic
      { duration: '30s', target: 400 }, // around the breaking point
      { duration: '10s', target: 300 },
      { duration: '15s', target: 200 }, // beyond the breaking point
      { duration: '10s', target: 100 },
      { duration: '5s', target: 0 }, // scale down. Recovery stage.
    ],

};

const BASE_URL = 'https://kwj1270.ga';

export default () => {

    // main page
    let mainUrl = `${BASE_URL}`;
    let mainPageResponse = http.get(mainUrl);
    check(mainPageResponse, {
        'main page running': (response) => response.status === 200
    });

    // GangNam search line
    let GangNamSearchLineUrl = `${BASE_URL}/paths/?source=3&target=106`;
    let GangNamSearchLineResponse = http.get(GangNamSearchLineUrl);
    check(GangNamSearchLineResponse, {
        'GangNam line searching success': (response) => response.status === 200
    });

    // Gasan search line
    let GasanSearchLineUrl = `${BASE_URL}/paths/?source=3&target=24`;
    let GasanSearchLineResponse = http.get(GasanSearchLineUrl);
    check(GasanSearchLineResponse, {
        'Gasan line searching success': (response) => response.status === 200
    });

    // YangJae search line
    let YangJaeSearchLineUrl = `${BASE_URL}/paths/?source=3&target=150`;
    let YangJaeSearchLineResponse = http.get(YangJaeSearchLineUrl);
    check(YangJaeSearchLineResponse, {
        'YangJae line searching success': (response) => response.status === 200
    });

};
```
**개선전**
```shell

```
**개선후**
```shell
running (2m24.0s), 0000/1000 VUs, 33954 complete and 0 interrupted iterations
default ↓ [======================================] 0001/1000 VUs  2m24s

     ✓ main page running
     ✓ GangNam line searching success
     ✓ Gasan line searching success
     ✓ YangJae line searching success

     checks.........................: 100.00% ✓ 135816 ✗ 0
     data_received..................: 179 MB  1.2 MB/s
     data_sent......................: 12 MB   84 kB/s
     http_req_blocked...............: avg=9.73ms   min=200ns    med=388ns    max=4.44s  p(90)=491ns    p(95)=578ns
     http_req_connecting............: avg=3.16ms   min=0s       med=0s       max=1.82s  p(90)=0s       p(95)=0s
     http_req_duration..............: avg=518.35ms min=690.78µs med=460.3ms  max=23.44s p(90)=863.77ms p(95)=1.07s
       { expected_response:true }...: avg=518.35ms min=690.78µs med=460.3ms  max=23.44s p(90)=863.77ms p(95)=1.07s
     http_req_failed................: 0.00%   ✓ 0      ✗ 135816
     http_req_receiving.............: avg=4.62ms   min=21.06µs  med=64.78µs  max=1.6s   p(90)=996.51µs p(95)=2.78ms
     http_req_sending...............: avg=1.33ms   min=11.1µs   med=28.44µs  max=1.16s  p(90)=72.44µs  p(95)=373.89µs
     http_req_tls_handshaking.......: avg=6.43ms   min=0s       med=0s       max=4.28s  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=512.39ms min=0s       med=455.96ms max=23.44s p(90)=851.72ms p(95)=1.05s
     http_reqs......................: 135816  943.100584/s
     iteration_duration.............: avg=2.24s    min=5.09ms   med=2.11s    max=27.98s p(90)=4s       p(95)=4.56s
     iterations.....................: 33954   235.775146/s
     vus............................: 1       min=1    max=998
     vus_max........................: 1000    min=1000 max=1000
```


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

—

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요