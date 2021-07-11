# Stress 부하 테스트

--- 

## 개선 전 계측

```shell
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m6s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 36s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m36.5s), 000/300 VUs, 3150 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  36s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get stations
     ✓ get shortestPath
     ✓ get favorites

     checks.........................: 100.00% ✓ 15750      ✗ 0
     data_received..................: 17 MB   470 kB/s
     data_sent......................: 4.6 MB  126 kB/s
     http_req_blocked...............: avg=134.4µs  min=3.01µs  med=4.5µs   max=53.47ms  p(90)=6µs      p(95)=8.76µs
     http_req_connecting............: avg=15.67µs  min=0s      med=0s      max=15.84ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=113.42ms min=4.74ms  med=55.89ms max=845.14ms p(90)=276.03ms p(95)=323.72ms
       { expected_response:true }...: avg=113.42ms min=4.74ms  med=55.89ms max=845.14ms p(90)=276.03ms p(95)=323.72ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 15750
     http_req_receiving.............: avg=55.73µs  min=24.77µs med=54.78µs max=9.5ms    p(90)=66.68µs  p(95)=72.44µs
     http_req_sending...............: avg=20.93µs  min=9.92µs  med=14.14µs max=7.01ms   p(90)=25.26µs  p(95)=36.84µs
     http_req_tls_handshaking.......: avg=110.27µs min=0s      med=0s      max=29.98ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=113.34ms min=4.69ms  med=55.84ms max=845.06ms p(90)=275.94ms p(95)=323.67ms
     http_reqs......................: 15750   431.367076/s
     iteration_duration.............: avg=1.56s    min=1.03s   med=1.35s   max=3.42s    p(90)=2.31s    p(95)=2.41s
     iterations.....................: 3150    86.273415/s
     vus............................: 4       min=4        max=300
     vus_max........................: 300     min=300      max=300
```

---

## 개선 후 계측

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m6s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 36s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m06.0s), 000/300 VUs, 1344 complete and 297 interrupted iterations
default ✓ [======================================] 001/300 VUs  36s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get stations
     ✓ get shortestPath
     ✓ get favorites

     checks.........................: 100.00% ✓ 7041       ✗ 0
     data_received..................: 7.5 MB  114 kB/s
     data_sent......................: 1.1 MB  17 kB/s
     http_req_blocked...............: avg=211.31µs min=2.03µs  med=2.73µs  max=63.64ms p(90)=2.93µs   p(95)=7.81µs
     http_req_connecting............: avg=31.03µs  min=0s      med=0s      max=10.5ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=331.66ms min=3.56ms  med=11.95ms max=37.76s  p(90)=151.79ms p(95)=213.66ms
       { expected_response:true }...: avg=331.66ms min=3.56ms  med=11.95ms max=37.76s  p(90)=151.79ms p(95)=213.66ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 7041
     http_req_receiving.............: avg=65.92µs  min=29.68µs med=59.67µs max=2.51ms  p(90)=81.22µs  p(95)=94.57µs
     http_req_sending...............: avg=48.7µs   min=17.26µs med=33.58µs max=5.49ms  p(90)=77.89µs  p(95)=115.32µs
     http_req_tls_handshaking.......: avg=167.69µs min=0s      med=0s      max=31.16ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=331.55ms min=3.46ms  med=11.85ms max=37.76s  p(90)=151.69ms p(95)=213.57ms
     http_reqs......................: 7041    106.676693/s
     iteration_duration.............: avg=1.27s    min=1.03s   med=1.06s   max=36.84s  p(90)=1.65s    p(95)=1.81s
     iterations.....................: 1344    20.362658/s
     vus............................: 0       min=0        max=300
     vus_max........................: 300     min=300      max=300

```
