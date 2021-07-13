# Stress Test 결과
## 스크립트
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        {duration: '10s', target: 100}, 
        {duration: '15s', target: 150}, 
        {duration: '15s', target: 250}, 
        {duration: '15s', target: 300}, 
        {duration: '5s', target: 0},
    ],
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
script: station-line-search-stress.js
output: -

    scenarios: (100.00%) 1 scenario, 300 max VUs, 1m30s max duration (incl. graceful stop):
* default: Up to 300 looping VUs for 1m0s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)

// 요청 실패 에러 로그....

running (1m00.0s), 000/300 VUs, 12938 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  1m0s

     ✗ find stations
      ↳  62% — ✓ 8124 / ✗ 4814
     ✗ find path
      ↳  61% — ✓ 7939 / ✗ 4999

     checks.........................: 62.07% ✓ 16063     ✗ 9813
     data_received..................: 633 MB 11 MB/s
     data_sent......................: 8.0 MB 133 kB/s
     http_req_blocked...............: avg=19.15ms  min=0s      med=4.78µs   max=152.37ms p(90)=70.3ms   p(95)=85.32ms
     http_req_connecting............: avg=1.23ms   min=0s      med=0s       max=33.02ms  p(90)=2.67ms   p(95)=5.66ms
   ✗ http_req_duration..............: avg=382.26ms min=0s      med=327.87ms max=2.3s     p(90)=864.51ms p(95)=892.43ms
       { expected_response:true }...: avg=608.42ms min=17.93ms med=613.73ms max=2.3s     p(90)=882.82ms p(95)=924.3ms
     http_req_failed................: 37.92% ✓ 9813      ✗ 16063
     http_req_receiving.............: avg=1.87ms   min=0s      med=52.15µs  max=83.34ms  p(90)=5.96ms   p(95)=9.37ms
     http_req_sending...............: avg=3.87ms   min=0s      med=15.72µs  max=157.14ms p(90)=798.54µs p(95)=31.86ms
     http_req_tls_handshaking.......: avg=14.6ms   min=0s      med=0s       max=151.04ms p(90)=60.7ms   p(95)=77.12ms
     http_req_waiting...............: avg=376.51ms min=0s      med=325.73ms max=2.25s    p(90)=857.05ms p(95)=880.19ms
     http_reqs......................: 25876  431.02882/s
     iteration_duration.............: avg=803.91ms min=6.09ms  med=887.63ms max=3.79s    p(90)=1.7s     p(95)=1.78s
     iterations.....................: 12938  215.51441/s
     vus............................: 2      min=2       max=300
     vus_max........................: 300    min=300     max=300
```
## 성능 개선 후
```javascript

/\      |‾‾| /‾‾/   /‾‾/
/\  /  \     |  |/  /   /  /
/  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: station-line-search-stress.js
output: -

    scenarios: (100.00%) 1 scenario, 300 max VUs, 1m30s max duration (incl. graceful stop):
* default: Up to 300 looping VUs for 1m0s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m01.3s), 000/300 VUs, 24856 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  1m0s

     ✓ find stations
     ✓ find path

checks.........................: 100.00% ✓ 49712      ✗ 0
data_received..................: 1.8 GB  30 MB/s
data_sent......................: 6.9 MB  112 kB/s
http_req_blocked...............: avg=454.68µs min=2.77µs  med=4.31µs   max=1.31s    p(90)=5.26µs   p(95)=8.63µs
http_req_connecting............: avg=127.03µs min=0s      med=0s       max=1.05s    p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=206.82ms min=2.76ms  med=90.26ms  max=15.66s   p(90)=564.7ms  p(95)=798.78ms
{ expected_response:true }...: avg=206.82ms min=2.76ms  med=90.26ms  max=15.66s   p(90)=564.7ms  p(95)=798.78ms
http_req_failed................: 0.00%   ✓ 0          ✗ 49712
http_req_receiving.............: avg=92.76ms  min=22.33µs med=337.37µs max=15.37s   p(90)=333.99ms p(95)=520.51ms
http_req_sending...............: avg=23.52µs  min=9.5µs   med=13.61µs  max=9.51ms   p(90)=25.73µs  p(95)=36.4µs
http_req_tls_handshaking.......: avg=321.95µs min=0s      med=0s       max=515.96ms p(90)=0s       p(95)=0s
http_req_waiting...............: avg=114.04ms min=2.69ms  med=80.74ms  max=4.18s    p(90)=260.12ms p(95)=342.32ms
http_reqs......................: 49712   811.286315/s
iteration_duration.............: avg=414.81ms min=8.82ms  med=247.34ms max=15.67s   p(90)=925.1ms  p(95)=1.18s
iterations.....................: 24856   405.643158/s
vus............................: 1       min=1        max=300
vus_max........................: 300     min=300      max=300

```
