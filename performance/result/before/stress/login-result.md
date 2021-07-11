```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: login-stress-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 12m40s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (12m10.7s), 000/240 VUs, 101202 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ logged in successfully
     ✓ response ok

     checks.........................: 100.00% ✓ 202404     ✗ 0
     data_received..................: 170 MB  233 kB/s
     data_sent......................: 52 MB   71 kB/s
     http_req_blocked...............: avg=14.04µs min=839ns   med=2.44µs  max=42.99ms  p(90)=4.28µs  p(95)=5.7µs
     http_req_connecting............: avg=1.97µs  min=0s      med=0s      max=6.96ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=3.64ms  min=1.85ms  med=3.08ms  max=333.97ms p(90)=4.9ms   p(95)=5.97ms
       { expected_response:true }...: avg=3.64ms  min=1.85ms  med=3.08ms  max=333.97ms p(90)=4.9ms   p(95)=5.97ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 202404
     http_req_receiving.............: avg=50.92µs min=11.25µs med=41.27µs max=57.78ms  p(90)=60.6µs  p(95)=79.16µs
     http_req_sending...............: avg=26.64µs min=5.16µs  med=15.32µs max=40.72ms  p(90)=28.06µs p(95)=36.38µs
     http_req_tls_handshaking.......: avg=8.55µs  min=0s      med=0s      max=25.21ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3.56ms  min=1.81ms  med=3.02ms  max=333.92ms p(90)=4.82ms  p(95)=5.87ms
     http_reqs......................: 202404  276.983139/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.36s    p(90)=1.01s   p(95)=1.01s
     iterations.....................: 101202  138.491569/s
     vus............................: 11      min=1        max=240
     vus_max........................: 240     min=240      max=240

```
