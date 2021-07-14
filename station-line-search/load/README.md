# Load Test 결과

## 스크립트
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
    {duration: '10s', target: 5},
	{duration: '40s', target: 15},
	{duration: '10s', target: 0}
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
     script: station-line-search-load.js
     output: -

  scenarios: (100.00%) 1 scenario, 15 max VUs, 1m30s max duration (incl. graceful stop):
           * default: Up to 15 looping VUs for 1m0s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m00.0s), 00/15 VUs, 6777 complete and 0 interrupted iterations
default ✓ [======================================] 00/15 VUs  1m0s

     ✓ find stations
     ✓ find path

     checks.........................: 100.00% ✓ 13554     ✗ 0
     data_received..................: 492 MB  8.2 MB/s
     data_sent......................: 1.8 MB  31 kB/s
     http_req_blocked...............: avg=17.91µs min=3.16µs  med=4.49µs   max=43.4ms   p(90)=5.2µs    p(95)=5.81µs
     http_req_connecting............: avg=2.02µs  min=0s      med=0s       max=1.53ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=35.68ms min=16.86ms med=30.69ms  max=173.32ms p(90)=58.49ms  p(95)=69.18ms
       { expected_response:true }...: avg=35.68ms min=16.86ms med=30.69ms  max=173.32ms p(90)=58.49ms  p(95)=69.18ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 13554
     http_req_receiving.............: avg=1.91ms  min=26.79µs med=851.94µs max=48.15ms  p(90)=3.8ms    p(95)=6.48ms
     http_req_sending...............: avg=17.58µs min=10.03µs med=14.06µs  max=1.63ms   p(90)=23.59µs  p(95)=25.63µs
     http_req_tls_handshaking.......: avg=10.07µs min=0s      med=0s       max=29.71ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=33.74ms min=14.62ms med=28.79ms  max=173.25ms p(90)=56.63ms  p(95)=67.87ms
     http_reqs......................: 13554   225.88152/s
     iteration_duration.............: avg=71.64ms min=38.18ms med=65.91ms  max=242.6ms  p(90)=106.87ms p(95)=121.81ms
     iterations.....................: 6777    112.94076/s
     vus............................: 1       min=1       max=15
     vus_max........................: 15      min=15      max=15
```
## 성능 개선 후
```javascript

/\      |‾‾| /‾‾/   /‾‾/
/\  /  \     |  |/  /   /  /
/  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: station-line-search-load.js
output: -

    scenarios: (100.00%) 1 scenario, 15 max VUs, 1m30s max duration (incl. graceful stop):
* default: Up to 15 looping VUs for 1m0s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m00.0s), 00/15 VUs, 27792 complete and 0 interrupted iterations
default ✓ [======================================] 00/15 VUs  1m0s

     ✓ find stations
     ✓ find path

checks.........................: 100.00% ✓ 55584      ✗ 0
data_received..................: 2.0 GB  34 MB/s
data_sent......................: 7.6 MB  126 kB/s
http_req_blocked...............: avg=16.06µs min=2.81µs  med=4.25µs   max=157.14ms p(90)=4.94µs  p(95)=5.86µs
http_req_connecting............: avg=1.69µs  min=0s      med=0s       max=8.13ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=10.84ms min=2.69ms  med=8.83ms   max=68.1ms   p(90)=20.24ms p(95)=24.74ms
{ expected_response:true }...: avg=10.84ms min=2.69ms  med=8.83ms   max=68.1ms   p(90)=20.24ms p(95)=24.74ms
http_req_failed................: 0.00%   ✓ 0          ✗ 55584
http_req_receiving.............: avg=1.9ms   min=20.14µs med=411.38µs max=43.33ms  p(90)=5.36ms  p(95)=8.59ms
http_req_sending...............: avg=20.53µs min=8.47µs  med=12.77µs  max=13.94ms  p(90)=22.37µs p(95)=27.64µs
http_req_tls_handshaking.......: avg=7.22µs  min=0s      med=0s       max=30.05ms  p(90)=0s      p(95)=0s
http_req_waiting...............: avg=8.91ms  min=2.63ms  med=7.44ms   max=61.63ms  p(90)=15.77ms p(95)=19.35ms
http_reqs......................: 55584   926.243657/s
iteration_duration.............: avg=21.94ms min=8.25ms  med=20.32ms  max=170.9ms  p(90)=33.61ms p(95)=38.54ms
iterations.....................: 27792   463.121829/s
vus............................: 1       min=1        max=15
vus_max........................: 15      min=15       max=15
```

