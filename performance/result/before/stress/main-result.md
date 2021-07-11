```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: main-stress-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 12m40s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (12m11.0s), 000/240 VUs, 101630 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ main in successfully

     checks.........................: 100.00% ✓ 101630     ✗ 0
     data_received..................: 129 MB  176 kB/s
     data_sent......................: 15 MB   21 kB/s
     http_req_blocked...............: avg=19.39µs min=1.29µs  med=3.02µs  max=37.3ms  p(90)=5.72µs  p(95)=6.25µs
     http_req_connecting............: avg=2.94µs  min=0s      med=0s      max=4.65ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=3.19ms  min=1.89ms  med=2.93ms  max=69.67ms p(90)=4.09ms  p(95)=4.82ms
       { expected_response:true }...: avg=3.19ms  min=1.89ms  med=2.93ms  max=69.67ms p(90)=4.09ms  p(95)=4.82ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 101630
     http_req_receiving.............: avg=52.62µs min=11.86µs med=42.03µs max=15.67ms p(90)=71.44µs p(95)=117.85µs
     http_req_sending...............: avg=22.18µs min=5.33µs  med=12.86µs max=8.12ms  p(90)=27.92µs p(95)=47.59µs
     http_req_tls_handshaking.......: avg=12.09µs min=0s      med=0s      max=25.15ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3.11ms  min=1.84ms  med=2.86ms  max=69.61ms p(90)=4.02ms  p(95)=4.73ms
     http_reqs......................: 101630  139.035312/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.07s   p(90)=1s      p(95)=1s
     iterations.....................: 101630  139.035312/s
     vus............................: 8       min=1        max=240
     vus_max........................: 240     min=240      max=240

```
