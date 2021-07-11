# Stress 부하 테스트

--- 

## 개선 전 계측

```shell
running (0m36.4s), 000/600 VUs, 9800 complete and 0 interrupted iterations
default ✓ [======================================] 000/600 VUs  36s

     ✗ logged in successfully
      ↳  99% — ✓ 9749 / ✗ 51
     ✓ updated in  successfully

     checks.........................: 99.73% ✓ 19498      ✗ 51
     data_received..................: 34 MB  923 kB/s
     data_sent......................: 6.9 MB 190 kB/s
     http_req_blocked...............: avg=42.32ms  min=3.28µs   med=5.07µs  max=284.5ms  p(90)=208.74ms p(95)=218.1ms
     http_req_connecting............: avg=806.69µs min=0s       med=0s      max=66.27ms  p(90)=1.45ms   p(95)=2.35ms
   ✓ http_req_duration..............: avg=15.77ms  min=114.91µs med=14.88ms max=257.59ms p(90)=26.67ms  p(95)=41.31ms
       { expected_response:true }...: avg=15.79ms  min=4.57ms   med=14.89ms max=257.59ms p(90)=26.72ms  p(95)=41.34ms
     http_req_failed................: 0.26%  ✓ 51         ✗ 19498
     http_req_receiving.............: avg=53.45µs  min=0s       med=36.7µs  max=28.94ms  p(90)=61.05µs  p(95)=70.96µs
     http_req_sending...............: avg=271.82µs min=10.77µs  med=19.41µs max=34.57ms  p(90)=207.56µs p(95)=457.19µs
     http_req_tls_handshaking.......: avg=41.46ms  min=0s       med=0s      max=283.91ms p(90)=205.63ms p(95)=216.35ms
     http_req_waiting...............: avg=15.45ms  min=100.12µs med=14.77ms max=257.48ms p(90)=25.83ms  p(95)=39.01ms
     http_reqs......................: 19549  537.402134/s
     iteration_duration.............: avg=1.11s    min=620.15µs med=1.08s   max=1.37s    p(90)=1.25s    p(95)=1.27s
     iterations.....................: 9800   269.402062/s
     vus............................: 7      min=7        max=600
     vus_max........................: 600    min=600      max=600
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

  scenarios: (100.00%) 1 scenario, 600 max VUs, 1m6s max duration (incl. graceful stop):
           * default: Up to 600 looping VUs for 36s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m36.8s), 000/600 VUs, 10120 complete and 0 interrupted iterations
default ✓ [======================================] 000/600 VUs  36s

     ✓ logged in successfully
     ✓ updated in  successfully

     checks.........................: 100.00% ✓ 20240      ✗ 0
     data_received..................: 8.0 MB  218 kB/s
     data_sent......................: 3.6 MB  97 kB/s
     http_req_blocked...............: avg=143.96µs min=2.13µs  med=2.74µs  max=36.35ms  p(90)=2.95µs  p(95)=7.05µs
     http_req_connecting............: avg=18.8µs   min=0s      med=0s      max=16.6ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=37.79ms  min=4.71ms  med=18.57ms max=569.93ms p(90)=90.15ms p(95)=114.19ms
       { expected_response:true }...: avg=37.79ms  min=4.71ms  med=18.57ms max=569.93ms p(90)=90.15ms p(95)=114.19ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 20240
     http_req_receiving.............: avg=47.83µs  min=13.58µs med=42.1µs  max=3.41ms   p(90)=69.62µs p(95)=80.26µs
     http_req_sending...............: avg=70.09µs  min=33.27µs med=59.32µs max=10.88ms  p(90)=90.37µs p(95)=128.88µs
     http_req_tls_handshaking.......: avg=117.4µs  min=0s      med=0s      max=30.1ms   p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=37.67ms  min=4.62ms  med=18.46ms max=569.84ms p(90)=90.03ms p(95)=114.08ms
     http_reqs......................: 20240   550.458185/s
     iteration_duration.............: avg=1.07s    min=1.01s   med=1.03s   max=1.79s    p(90)=1.17s   p(95)=1.22s
     iterations.....................: 10120   275.229092/s
     vus............................: 6       min=6        max=600
     vus_max........................: 600     min=600      max=600

```
