# Smoke Test 결과
## 스크립트
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    vus: 1, // 1 user looping for 1 minute
    duration: '10s',

    thresholds: {
        http_req_duration: ['p(99)<100'],
    },
};

const BASE_URL = 'https://nextstep.5minho.p-e.kr/';

function 경로_검색(source, target) {
    let pathRes = http.get(`${BASE_URL}/paths?source=${source}&target=${target}`);
    check(pathRes, {'find path': (res) => res.status === 200});
}

function 지하철_조회() {
    let stationsRes = http.get(`${BASE_URL}/stations`);
    check(stationsRes, {'find stations': (res) => res.status === 200});
}

export default function ()  {
    지하철_조회();
    경로_검색(103, 102);
}

```
## 성능 개선 전
```javascript
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: station-line-search-smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.0s), 0/1 VUs, 220 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ find stations
     ✓ find path

     checks.........................: 100.00% ✓ 440       ✗ 0
     data_received..................: 16 MB   1.6 MB/s
     data_sent......................: 60 kB   6.0 kB/s
     http_req_blocked...............: avg=77.21µs min=4.79µs  med=5.7µs    max=31.36ms p(90)=6.27µs  p(95)=7.35µs
     http_req_connecting............: avg=2.99µs  min=0s      med=0s       max=1.31ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=22.5ms  min=18.62ms med=23.38ms  max=52.49ms p(90)=24.95ms p(95)=25.76ms
       { expected_response:true }...: avg=22.5ms  min=18.62ms med=23.38ms  max=52.49ms p(90)=24.95ms p(95)=25.76ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 440
     http_req_receiving.............: avg=1.25ms  min=73.98µs med=744.81µs max=15.99ms p(90)=2.46ms  p(95)=2.61ms
     http_req_sending...............: avg=21.13µs min=15.17µs med=18.71µs  max=429.3µs p(90)=27.66µs p(95)=29.47µs
     http_req_tls_handshaking.......: avg=66.88µs min=0s      med=0s       max=29.42ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=21.22ms min=16.36ms med=23.07ms  max=52.37ms p(90)=24.83ms p(95)=25.52ms
     http_reqs......................: 440     43.918364/s
     iteration_duration.............: avg=45.51ms min=42.29ms med=44.3ms   max=95.88ms p(90)=47.53ms p(95)=50.57ms
     iterations.....................: 220     21.959182/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1
```

## 성능 개선 후

``` javascript
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: station-line-search-smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.0s), 0/1 VUs, 1051 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ find stations
     ✓ find path

     checks.........................: 100.00% ✓ 2102       ✗ 0
     data_received..................: 76 MB   7.6 MB/s
     data_sent......................: 286 kB  29 kB/s
     http_req_blocked...............: avg=30.52µs  min=3.33µs  med=4.59µs   max=40.84ms  p(90)=5.14µs  p(95)=5.77µs
     http_req_connecting............: avg=2.09µs   min=0s      med=0s       max=1.93ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=4.6ms    min=2.75ms  med=5.41ms   max=13.81ms  p(90)=6.27ms  p(95)=6.86ms
       { expected_response:true }...: avg=4.6ms    min=2.75ms  med=5.41ms   max=13.81ms  p(90)=6.27ms  p(95)=6.86ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 2102
     http_req_receiving.............: avg=765.42µs min=31.42µs med=289.91µs max=8.17ms   p(90)=1.53ms  p(95)=1.8ms
     http_req_sending...............: avg=15.98µs  min=10.14µs med=13.46µs  max=530.19µs p(90)=22.63µs p(95)=24.59µs
     http_req_tls_handshaking.......: avg=18.27µs  min=0s      med=0s       max=28.5ms   p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3.82ms   min=2.69ms  med=4.2ms    max=10.56ms  p(90)=4.67ms  p(95)=5.05ms
     http_reqs......................: 2102    210.083313/s
     iteration_duration.............: avg=9.5ms    min=8.31ms  med=9.13ms   max=57.69ms  p(90)=10.45ms p(95)=11.53ms
     iterations.....................: 1051    105.041656/s
     vus............................: 1       min=1        max=1
     vus_max........................: 1       min=1        max=1
```