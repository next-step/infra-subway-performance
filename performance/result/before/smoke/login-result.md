```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: login-smoke-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ response ok

     checks.........................: 100.00% ✓ 20       ✗ 0
     data_received..................: 21 kB   2.1 kB/s
     data_sent......................: 5.5 kB  537 B/s
     http_req_blocked...............: avg=1.99ms  min=2.31µs  med=4.11µs  max=39.78ms  p(90)=5.17µs  p(95)=1.99ms
     http_req_connecting............: avg=57.03µs min=0s      med=0s      max=1.14ms   p(90)=0s      p(95)=57.03µs
   ✓ http_req_duration..............: avg=7.92ms  min=3.22ms  med=9.49ms  max=20.18ms  p(90)=11.67ms p(95)=13.47ms
       { expected_response:true }...: avg=7.92ms  min=3.22ms  med=9.49ms  max=20.18ms  p(90)=11.67ms p(95)=13.47ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 20
     http_req_receiving.............: avg=75.19µs min=49.91µs med=72.88µs max=116.94µs p(90)=94.26µs p(95)=108.66µs
     http_req_sending...............: avg=32.46µs min=17.74µs med=30.41µs max=95.67µs  p(90)=39.16µs p(95)=53.8µs
     http_req_tls_handshaking.......: avg=1.36ms  min=0s      med=0s      max=27.31ms  p(90)=0s      p(95)=1.36ms
     http_req_waiting...............: avg=7.81ms  min=3.15ms  med=9.38ms  max=19.97ms  p(90)=11.56ms p(95)=13.36ms
     http_reqs......................: 20      1.957867/s
     iteration_duration.............: avg=1.02s   min=1.01s   med=1.01s   max=1.07s    p(90)=1.02s   p(95)=1.04s
     iterations.....................: 10      0.978933/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
