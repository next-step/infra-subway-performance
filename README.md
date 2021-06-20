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
### Stress
#### 접속 빈도가 높은 페이지
**login-stress.js**
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
      { duration: '2s', target: 100 }, // below normal load
      { duration: '5s', target: 100 },
      { duration: '2s', target: 200 }, // normal load
      { duration: '5s', target: 200 },
      { duration: '1m', target: 2000 }, // sudden traffic
      { duration: '30s', target: 1000 }, // around the breaking point
      { duration: '10s', target: 300 },
      { duration: '15s', target: 400 }, // beyond the breaking point
      { duration: '10s', target: 400 },
      { duration: '5s', target: 0 }, // scale down. Recovery stage.
    ],

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://kwj1270.ga';
const USERNAME = 'kwj12705014@gmail.com';
const PASSWORD = 'test1234!';
const DATA = JSON.stringify({email: USERNAME, password: PASSWORD});
const PARAMS = {headers: {'Content-Type': 'application/json',},};

export default () => {
    let loginRes = http.post(`${BASE_URL}/login/token`, DATA, PARAMS);

    check(loginRes, {
        'logged in successfully': (resp) => resp.status === 200,
    });

    let authHeaders = {
        headers: {
            Authorization: `Bearer ${loginRes.json('accessToken')}`,
        },
    };
    let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
    check(myObjects, { 'retrieved member': (obj) => obj.id != 0 });
    sleep(1);
};
```
```shell
default ✓ [======================================] 0000/2000 VUs  2m24s

     ✗ logged in successfully
      ↳  35% — ✓ 17408 / ✗ 31017
     ✓ retrieved member

     checks.........................: 51.60% ✓ 33069  ✗ 31017
     data_received..................: 116 MB 798 kB/s
     data_sent......................: 22 MB  152 kB/s
     http_req_blocked...............: avg=564.07ms min=0s       med=4.34µs   max=3.31s  p(90)=1.91s    p(95)=2.26s
     http_req_connecting............: avg=219.7ms  min=0s       med=177.13ms max=1.95s  p(90)=541.12ms p(95)=610.16ms
   ✓ http_req_duration..............: avg=214.16ms min=0s       med=77.5ms   max=3.27s  p(90)=614.05ms p(95)=782.9ms
       { expected_response:true }...: avg=358.26ms min=5.08ms   med=299.27ms max=3.27s  p(90)=741.68ms p(95)=910.34ms
     http_req_failed................: 49.76% ✓ 32764  ✗ 33069
     http_req_receiving.............: avg=6.08ms   min=0s       med=19.73µs  max=1.06s  p(90)=451.1µs  p(95)=13.88ms
     http_req_sending...............: avg=25.78ms  min=0s       med=17.25µs  max=2.86s  p(90)=86.21ms  p(95)=146.08ms
     http_req_tls_handshaking.......: avg=447.46ms min=0s       med=0s       max=2.95s  p(90)=1.57s    p(95)=1.92s
     http_req_waiting...............: avg=182.28ms min=0s       med=21.98ms  max=2.42s  p(90)=543.6ms  p(95)=684.6ms
     http_reqs......................: 65833  454.568505/s
     iteration_duration.............: avg=2.36s    min=135.41µs med=1.92s    max=15.94s p(90)=4.61s    p(95)=5.59s
     iterations.....................: 48425  334.368476/s
     vus............................: 43     min=43   max=2000
     vus_max........................: 2000   min=2000 max=2000
```
#### 데이터를 조회하는 데 여러 데이터를 참조하는 페이지
**page-stress.js**
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';


export let options = {
    stages: [
      { duration: '2s', target: 100 }, // below normal load
      { duration: '5s', target: 100 },
      { duration: '2s', target: 200 }, // normal load
      { duration: '5s', target: 200 },
      { duration: '1m', target: 2000 }, // sudden traffic
      { duration: '30s', target: 1000 }, // around the breaking point
      { duration: '10s', target: 300 },
      { duration: '15s', target: 400 }, // beyond the breaking point
      { duration: '10s', target: 400 },
      { duration: '5s', target: 0 }, // scale down. Recovery stage.
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://kwj1270.ga';

export default function () {
    const res = http.get(`${BASE_URL}/stations`);
    check(res, {
        'page load successfully': (resp) => resp.status === 200,
    });


    sleep(1);
}
```
```shell
running (2m30.4s), 0000/2000 VUs, 55895 complete and 0 interrupted iterations
default ✓ [======================================] 0000/2000 VUs  2m24s

     ✗ page load successfully
      ↳  6% — ✓ 3896 / ✗ 51999

     checks.........................: 6.97%  ✓ 3896   ✗ 51999
     data_received..................: 333 MB 2.2 MB/s
     data_sent......................: 18 MB  116 kB/s
     http_req_blocked...............: avg=75.91ms  min=0s       med=0s      max=3.73s  p(90)=226.7ms  p(95)=599.1ms
     http_req_connecting............: avg=148.08ms min=0s       med=97.68ms max=2.89s  p(90)=344.79ms p(95)=513.36ms
   ✗ http_req_duration..............: avg=569.93ms min=0s       med=0s      max=22.34s p(90)=38.8ms   p(95)=7.08s
       { expected_response:true }...: avg=8.09s    min=321.91ms med=8.28s   max=22.34s p(90)=11.42s   p(95)=13.03s
     http_req_failed................: 93.02% ✓ 51999  ✗ 3896
     http_req_receiving.............: avg=5.64ms   min=0s       med=0s      max=1.7s   p(90)=0s       p(95)=3.63ms
     http_req_sending...............: avg=4.77ms   min=0s       med=0s      max=2.68s  p(90)=104µs    p(95)=15.73ms
     http_req_tls_handshaking.......: avg=54.98ms  min=0s       med=0s      max=3.39s  p(90)=158.34ms p(95)=428.44ms
     http_req_waiting...............: avg=559.5ms  min=0s       med=0s      max=21.72s p(90)=351.33µs p(95)=6.99s
     http_reqs......................: 55895  371.640872/s
     iteration_duration.............: avg=2.4s     min=1s       med=1.39s   max=23.43s p(90)=4.57s    p(95)=9.34s
     iterations.....................: 55895  371.640872/s
     vus............................: 19     min=19   max=2000
     vus_max........................: 2000   min=2000 max=2000

ERRO[0153] some thresholds have failed
```
#### 데이터를 갱신하는 페이지

**update-stress.js**
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
      { duration: '2s', target: 100 }, // below normal load
      { duration: '5s', target: 100 },
      { duration: '2s', target: 200 }, // normal load
      { duration: '5s', target: 200 },
      { duration: '1m', target: 2000 }, // sudden traffic
      { duration: '30s', target: 1000 }, // around the breaking point
      { duration: '10s', target: 300 },
      { duration: '15s', target: 400 }, // beyond the breaking point
      { duration: '10s', target: 400 },
      { duration: '5s', target: 0 }, // scale down. Recovery stage.
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://kwj1270.ga';
const USERNAME = 'kwj12705014@gmail.com';
const PASSWORD = 'test1234!';
const AGE = 20;
const LOGIN_DATA = JSON.stringify({email: USERNAME, password: PASSWORD,});
const UPDATE_DATA = JSON.stringify({email: USERNAME, password: PASSWORD, age:AGE});
const PARAMS = {headers: {'Content-Type': 'application/json',},};

export default () => {
    let updateRes = http.put(`${BASE_URL}/members/1`, UPDATE_DATA, PARAMS);

    check(updateRes, {
        'update successfully': (resp) => resp.status === 200,
    });

    let loginRes = http.post(`${BASE_URL}/login/token`, LOGIN_DATA, PARAMS);

    check(loginRes, {
        'logged in successfully': (resp) => resp.status === 200,
    });

    let authHeaders = {
        headers: {
            Authorization: `Bearer ${loginRes.json('accessToken')}`,
        },
    };
    let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
    check(myObjects, { 'retrieved member': (obj) => obj.id != 0 });
    sleep(1);
};
```
```shell
running (2m24.9s), 0000/2000 VUs, 50599 complete and 0 interrupted iterations
default ✓ [======================================] 0000/2000 VUs  2m24s

     ✗ update successfully
      ↳  30% — ✓ 15365 / ✗ 35234
     ✗ logged in successfully
      ↳  32% — ✓ 16606 / ✗ 33993
     ✓ retrieved member

     checks…………………….: 40.55% ✓ 47224  ✗ 69227
     data_received………………: 134 MB 923 kB/s
     data_sent………………….: 34 MB  233 kB/s
     http_req_blocked……………: avg=368.88ms min=0s       med=2.77µs   max=4.26s  p(90)=1.72s    p(95)=2.09s
     http_req_connecting…………: avg=224.33ms min=0s       med=198.61ms max=2.36s  p(90)=545.68ms p(95)=676.74ms
   ✓ http_req_duration…………..: avg=77.68ms  min=0s       med=2.69ms   max=3.08s  p(90)=250.77ms p(95)=481.97ms
       { expected_response:true }…: avg=143.7ms  min=4.46ms   med=42.13ms  max=3.08s  p(90)=446.36ms p(95)=608.64ms
     http_req_failed…………….: 59.91% ✓ 70580  ✗ 47224
     http_req_receiving………….: avg=2.65ms   min=0s       med=0s       max=1.23s  p(90)=87.81µs  p(95)=1.23ms
     http_req_sending……………: avg=11.33ms  min=0s       med=8.97µs   max=2.07s  p(90)=25.84ms  p(95)=67.37ms
     http_req_tls_handshaking…….: avg=293.49ms min=0s       med=0s       max=3.34s  p(90)=1.33s    p(95)=1.74s
     http_req_waiting……………: avg=63.69ms  min=0s       med=6.6µs    max=2.84s  p(90)=210.33ms p(95)=420.77ms
     http_reqs………………….: 117804 812.767735/s
     iteration_duration………….: avg=2.49s    min=229.22µs med=1.77s    max=12.56s p(90)=5.49s    p(95)=6.67s
     iterations…………………: 50599  349.098797/s
     vus……………………….: 32     min=32   max=1999
     vus_max……………………: 2000   min=2000 max=2000
```
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

—

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요