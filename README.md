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
- smoke
    - script
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  vus: 1, // 1 user looping for 1 minute
  duration: '10s',

  thresholds: {
    http_req_duration: ['p(99)<50'], // 99% of requests must complete below 1.5s
  },
};

const BASE_URL = 'https://ascii92der.n-e.kr';
const USERNAME = 'ascii92der@gmail.com';
const PASSWORD = '1234';

export default function ()  {

  var payload = JSON.stringify({
    email: USERNAME,
    password: PASSWORD,
  });

  var params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };


  let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);

  check(loginRes, {
    'logged in successfully': (resp) => resp.json('accessToken') !== '',
  });


  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };
  let myObjects1 = http.get(`${BASE_URL}/members/me`, authHeaders).json();
  let myObjects2 = http.get(`${BASE_URL}/paths?source=1&target=3`, authHeaders).json();
  let myObjects3 = http.get(`${BASE_URL}/favorites`, authHeaders).json();
  check(myObjects1, { 'retrieved member': (obj) => obj.id != 0 });
  check(myObjects2, { 'find Paths': (obj) => obj.stations != null});
  check(myObjects3, { 'find Favorite Paths': (obj) => obj.size != 0});
  sleep(1);
};
```
- 결과(개선 이전)
```shell
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


running (10.2s), 0/1 VUs, 8 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 32       ✗ 0  
     data_received..................: 37 kB   3.7 kB/s
     data_sent......................: 9.4 kB  923 B/s
     http_req_blocked...............: avg=6.49ms   min=2µs    med=5µs     max=204.91ms p(90)=11.7µs  p(95)=1.29ms  
     http_req_connecting............: avg=221.28µs min=0s     med=0s      max=7.08ms   p(90)=0s      p(95)=0s      
   ✓ http_req_duration..............: avg=61.28ms  min=12.3ms med=20.01ms max=248.8ms  p(90)=181.2ms p(95)=203.39ms
     { expected_response:true }...: avg=61.28ms  min=12.3ms med=20.01ms max=248.8ms  p(90)=181.2ms p(95)=203.39ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 32
     http_req_receiving.............: avg=93.37µs  min=51µs   med=82µs    max=210µs    p(90)=144.8µs p(95)=191.8µs
     http_req_sending...............: avg=59.09µs  min=16µs   med=28.5µs  max=1.01ms   p(90)=38.9µs  p(95)=50.95µs
     http_req_tls_handshaking.......: avg=5.75ms   min=0s     med=0s      max=184.18ms p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=61.12ms  min=12.2ms med=19.89ms max=248.56ms p(90)=181.1ms p(95)=203.3ms
     http_reqs......................: 32      3.136275/s
     iteration_duration.............: avg=1.27s    min=1.18s  med=1.23s   max=1.56s    p(90)=1.37s   p(95)=1.47s   
     iterations.....................: 8       0.784069/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
- 결과(개선 이후)
```shell

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


running (11.1s), 0/1 VUs, 9 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 36       ✗ 0  
     data_received..................: 40 kB   3.6 kB/s
     data_sent......................: 5.7 kB  510 B/s
     http_req_blocked...............: avg=6.39ms   min=0s      med=1µs     max=230.29ms p(90)=1µs      p(95)=1.25µs  
     http_req_connecting............: avg=247.47µs min=0s      med=0s      max=8.9ms    p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=51.32ms  min=15.98ms med=33.78ms max=147.25ms p(90)=111.71ms p(95)=123.07ms
       { expected_response:true }...: avg=51.32ms  min=15.98ms med=33.78ms max=147.25ms p(90)=111.71ms p(95)=123.07ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 36 
     http_req_receiving.............: avg=118.36µs min=66µs    med=98µs    max=397µs    p(90)=169µs    p(95)=212µs   
     http_req_sending...............: avg=124.25µs min=27µs    med=65.5µs  max=1.6ms    p(90)=172.5µs  p(95)=274.75µs
     http_req_tls_handshaking.......: avg=5.76ms   min=0s      med=0s      max=207.55ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=51.08ms  min=15.67ms med=33.59ms max=147.13ms p(90)=111.58ms p(95)=122.87ms
     http_reqs......................: 36      3.241068/s
     iteration_duration.............: avg=1.23s    min=1.18s   med=1.2s    max=1.49s    p(90)=1.28s    p(95)=1.38s   
     iterations.....................: 9       0.810267/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

ERRO[0012] some thresholds have failed  
```
- load
    - script
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  stages: [
    { duration: '10s', target: 130 },
    { duration: '20s', target: 130 },
    { duration: '10s', target: 0 }, 
  ],
  thresholds: {
    http_req_duration: ['p(99)<50'], // 99% of requests must complete below 1.5s
    'logged in successfully': ['p(99)<50'], // 99% of requests must complete below 1.5s
  },
};

const BASE_URL = 'https://ascii92der.n-e.kr';
const USERNAME = 'ascii92der@gmail.com';
const PASSWORD = '1234';

export default function ()  {

  var payload = JSON.stringify({
    email: USERNAME,
    password: PASSWORD,
  });

  var params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };


  let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);

  check(loginRes, {
    'logged in successfully': (resp) => resp.json('accessToken') !== '',
  });


  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };
  let myObjects1 = http.get(`${BASE_URL}/members/me`, authHeaders).json();
  let myObjects2 = http.get(`${BASE_URL}/paths?source=1&target=3`, authHeaders).json();
  let myObjects3 = http.get(`${BASE_URL}/favorites`, authHeaders).json();
  check(myObjects1, { 'retrieved member': (obj) => obj.id != 0 });
  check(myObjects2, { 'find Paths': (obj) => obj.stations != null});
  check(myObjects3, { 'find Favorite Paths': (obj) => obj.size != 0});
  sleep(1);
};
```
- 결과(개선 이전)
```shell

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 130 max VUs, 1m10s max duration (incl. graceful stop):
           * default: Up to 130 looping VUs for 40s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m42.7s), 000/130 VUs, 529 complete and 0 interrupted iterations
default ✓ [======================================] 000/130 VUs  40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 2116      ✗ 0    
     data_received..................: 2.8 MB  65 kB/s
     data_sent......................: 647 kB  15 kB/s
     http_req_blocked...............: avg=4.86ms  min=1µs    med=3µs   max=411.4ms  p(90)=9µs    p(95)=19.73ms 
     http_req_connecting............: avg=1.64ms  min=0s     med=0s    max=174.41ms p(90)=0s     p(95)=5.26ms  
   ✓ http_req_duration..............: avg=1.82s   min=7.63ms med=1.82s max=5.65s    p(90)=3.07s  p(95)=3.45s   
       { expected_response:true }...: avg=1.82s   min=7.63ms med=1.82s max=5.65s    p(90)=3.07s  p(95)=3.45s   
     http_req_failed................: 0.00%   ✓ 0         ✗ 2116 
     http_req_receiving.............: avg=68.68µs min=19µs   med=56µs  max=1.62ms   p(90)=110µs  p(95)=142.25µs
     http_req_sending...............: avg=27.04µs min=7µs    med=19µs  max=1.99ms   p(90)=47µs   p(95)=66µs    
     http_req_tls_handshaking.......: avg=3.21ms  min=0s     med=0s    max=295.76ms p(90)=0s     p(95)=13.36ms 
     http_req_waiting...............: avg=1.82s   min=7.49ms med=1.82s max=5.65s    p(90)=3.07s  p(95)=3.45s   
     http_reqs......................: 2116    49.601284/s
     iteration_duration.............: avg=8.34s   min=1.4s   med=9.51s max=12.83s   p(90)=10.81s p(95)=11.36s  
     iterations.....................: 529     12.400321/s
     vus............................: 29      min=13      max=130
     vus_max........................: 130     min=130     max=130
```
- 결과(개선 이후)
```shell

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 130 max VUs, 1m10s max duration (incl. graceful stop)
           * default: Up to 130 looping VUs for 40s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m41.0s), 000/130 VUs, 649 complete and 0 interrupted iterations
default ✓ [======================================] 000/130 VUs  40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 2596      ✗ 0    
     data_received..................: 3.1 MB  77 kB/s
     data_sent......................: 443 kB  11 kB/s
     http_req_blocked...............: avg=3.07ms   min=0s      med=1µs   max=351.69ms p(90)=1µs   p(95)=7.74ms  
     http_req_connecting............: avg=890.71µs min=0s      med=0s    max=100.07ms p(90)=0s    p(95)=2.12ms  
   ✗ http_req_duration..............: avg=1.36s    min=14.05ms med=1.36s max=4.16s    p(90)=2.29s p(95)=2.47s   
       { expected_response:true }...: avg=1.36s    min=14.05ms med=1.36s max=4.16s    p(90)=2.29s p(95)=2.47s   
     http_req_failed................: 0.00%   ✓ 0         ✗ 2596 
     http_req_receiving.............: avg=105.47µs min=28µs    med=91µs  max=2.61ms   p(90)=159µs p(95)=190µs   
     http_req_sending...............: avg=87.14µs  min=21µs    med=53µs  max=3.13ms   p(90)=189µs p(95)=242.25µs
     http_req_tls_handshaking.......: avg=2.17ms   min=0s      med=0s    max=248.18ms p(90)=0s    p(95)=5.12ms  
     http_req_waiting...............: avg=1.36s    min=13.93ms med=1.36s max=4.15s    p(90)=2.29s p(95)=2.47s   
     http_reqs......................: 2596    63.358397/s
     iteration_duration.............: avg=6.48s    min=1.63s   med=7.26s max=11.86s   p(90)=8.24s p(95)=8.46s   
     iterations.....................: 649     15.839599/s
     vus............................: 37      min=13      max=130
     vus_max........................: 130     min=130     max=130

ERRO[0042] some thresholds have failed 
```
- stress
    - script
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
  stages: [
    { duration: '15s', target: 100 },
    { duration: '30s', target: 100 },
    { duration: '15s', target: 200 },
    { duration: '30s', target: 200 },
    { duration: '15s', target: 240 },
    { duration: '30s', target: 240 },
    { duration: '10s', target: 0 }, 
  ],
  thresholds: {
    http_req_duration: ['p(99)<50'],
  },
};

const BASE_URL = 'https://ascii92der.n-e.kr';
const USERNAME = 'ascii92der@gmail.com';
const PASSWORD = '1234';

export default function ()  {

  var payload = JSON.stringify({
    email: USERNAME,
    password: PASSWORD,
  });

  var params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };


  let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);

  check(loginRes, {
    'logged in successfully': (resp) => resp.json('accessToken') !== '',
  });


  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
    },
  };
  let myObjects1 = http.get(`${BASE_URL}/members/me`, authHeaders).json();
  let myObjects2 = http.get(`${BASE_URL}/paths?source=1&target=3`, authHeaders).json();
  let myObjects3 = http.get(`${BASE_URL}/favorites`, authHeaders).json();
  check(myObjects1, { 'retrieved member': (obj) => obj.id != 0 });
  check(myObjects2, { 'find Paths': (obj) => obj.stations != null});
  check(myObjects3, { 'find Favorite Paths': (obj) => obj.size != 0});
  sleep(1);
};
```
- 결과(개선 이전)
```shell

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 2m55s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 2m25s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)

WARN[0118] Request Failed                                error="Post \"https://ascii92der.n-e.kr/login/token\": EOF"
ERRO[0118] invalid type <nil>, expected string, []byte or ArrayBuffer
running at reflect.methodValueCall (native)
default at loggedInSuccessfully (file:///Users/ascii92der/WooTeCam_Pro2/stress.js:40:85(4))
	at go.k6.io/k6/js/common.Bind.func1 (native)
	at file:///Users/ascii92der/WooTeCam_Pro2/stress.js:39:27(34)  executor=ramping-vus scenario=default source=stacktrace

running (2m36.3s), 000/240 VUs, 1503 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  2m25s

     ✗ logged in successfully
      ↳  99% — ✓ 1502 / ✗ 1
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 99.98% ✓ 6008      ✗ 1    
     data_received..................: 7.3 MB 47 kB/s
     data_sent......................: 1.8 MB 12 kB/s
     http_req_blocked...............: avg=39.25ms  min=1µs      med=3µs   max=1.35s    p(90)=6µs    p(95)=17µs   
     http_req_connecting............: avg=12.86ms  min=0s       med=0s    max=472.79ms p(90)=0s     p(95)=0s     
   ✓ http_req_duration..............: avg=4.04s    min=30.79ms  med=3.32s max=14.53s   p(90)=8.65s  p(95)=10.05s 
       { expected_response:true }...: avg=4.04s    min=30.79ms  med=3.32s max=14.53s   p(90)=8.65s  p(95)=10.05s 
     http_req_failed................: 0.01%  ✓ 1         ✗ 6008 
     http_req_receiving.............: avg=53.33µs  min=0s       med=48µs  max=1.43ms   p(90)=84µs   p(95)=104µs  
     http_req_sending...............: avg=461.65µs min=6µs      med=16µs  max=1.33s    p(90)=32µs   p(95)=48.59µs
     http_req_tls_handshaking.......: avg=26.01ms  min=0s       med=0s    max=949.62ms p(90)=0s     p(95)=0s     
     http_req_waiting...............: avg=4.04s    min=30.65ms  med=3.31s max=14.53s   p(90)=8.65s  p(95)=10.05s 
     http_reqs......................: 6009   38.434583/s
     iteration_duration.............: avg=17.32s   min=337.94ms med=18.1s max=35.3s    p(90)=23.18s p(95)=24.15s 
     iterations.....................: 1503   9.613443/s
     vus............................: 8      min=7       max=240
     vus_max........................: 240    min=240     max=240


```
- 결과(개선 이후)
```shell

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 2m55s max duration (incl. graceful stop)
           * default: Up to 240 looping VUs for 2m25s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m30.4s), 000/240 VUs, 2647 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  2m25s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 10588     ✗ 0    
     data_received..................: 12 MB   77 kB/s
     data_sent......................: 1.6 MB  11 kB/s
     http_req_blocked...............: avg=1.35ms   min=0s      med=1µs    max=346.51ms p(90)=1µs    p(95)=1µs     
     http_req_connecting............: avg=447.22µs min=0s      med=0s     max=316.27ms p(90)=0s     p(95)=0s      
   ✗ http_req_duration..............: avg=2.11s    min=12.48ms med=1.8s   max=8.72s    p(90)=4.29s  p(95)=4.64s   
       { expected_response:true }...: avg=2.11s    min=12.48ms med=1.8s   max=8.72s    p(90)=4.29s  p(95)=4.64s   
     http_req_failed................: 0.00%   ✓ 0         ✗ 10588
     http_req_receiving.............: avg=92.63µs  min=28µs    med=82µs   max=4.63ms   p(90)=140µs  p(95)=168µs   
     http_req_sending...............: avg=71.07µs  min=16µs    med=48µs   max=1.74ms   p(90)=138µs  p(95)=189.64µs
     http_req_tls_handshaking.......: avg=897.33µs min=0s      med=0s     max=206.02ms p(90)=0s     p(95)=0s      
     http_req_waiting...............: avg=2.1s     min=12.17ms med=1.8s   max=8.72s    p(90)=4.29s  p(95)=4.64s   
     http_reqs......................: 10588   70.421811/s
     iteration_duration.............: avg=9.44s    min=1.18s   med=10.87s max=19.73s   p(90)=13.48s p(95)=13.83s  
     iterations.....................: 2647    17.605453/s
     vus............................: 36      min=7       max=240
     vus_max........................: 240     min=240     max=240

ERRO[0152] some thresholds have failed      
```
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
 - reverse-proxy를 개선해서 캐싱 설정을 하고, http2 설정을 추가함
 - was에서 cache 설정 추가, redis 사용, 자주 사용하는 메소드에 캐싱 설정
 - 일부 메소드에 async 설정을 두어 응답 속도를 개선함

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

