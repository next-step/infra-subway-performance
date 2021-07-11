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


running (12m11.0s), 000/240 VUs, 101012 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ logged in successfully
     ✓ response ok

     checks.........................: 100.00% ✓ 202024     ✗ 0
     data_received..................: 155 MB  211 kB/s
     data_sent......................: 39 MB   53 kB/s
     http_req_blocked...............: avg=11.68µs min=188ns   med=358ns   max=60.35ms  p(90)=470ns   p(95)=562ns
     http_req_connecting............: avg=1.98µs  min=0s      med=0s      max=3.88ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=4.61ms  min=1.88ms  med=3.84ms  max=200.41ms p(90)=7.78ms  p(95)=9.5ms
       { expected_response:true }...: avg=4.61ms  min=1.88ms  med=3.84ms  max=200.41ms p(90)=7.78ms  p(95)=9.5ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 202024
     http_req_receiving.............: avg=47.56µs min=15.41µs med=39.7µs  max=16.08ms  p(90)=60.36µs p(95)=77.74µs
     http_req_sending...............: avg=43.24µs min=13.12µs med=37.21µs max=15.02ms  p(90)=68.23µs p(95)=88.96µs
     http_req_tls_handshaking.......: avg=8.87µs  min=0s      med=0s      max=39.73ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=4.52ms  min=1.19ms  med=3.74ms  max=199.99ms p(90)=7.67ms  p(95)=9.39ms
     http_reqs......................: 202024  276.379141/s
     iteration_duration.............: avg=1.01s   min=1s      med=1s      max=1.22s    p(90)=1.01s   p(95)=1.01s
     iterations.....................: 101012  138.189571/s
     vus............................: 15      min=1        max=240
     vus_max........................: 240     min=240      max=240

```
