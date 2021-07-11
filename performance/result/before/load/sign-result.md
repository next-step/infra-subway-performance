```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: sign-load-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 60 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 60 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m11.0s), 00/60 VUs, 9293 complete and 0 interrupted iterations
default ✓ [======================================] 00/60 VUs  3m10s

     ✓ sign in successfully

     checks.........................: 100.00% ✓ 9293      ✗ 0
     data_received..................: 2.3 MB  12 kB/s
     data_sent......................: 2.3 MB  12 kB/s
     http_req_blocked...............: avg=45.91µs min=1.5µs   med=4.34µs  max=26.49ms  p(90)=6.12µs  p(95)=6.63µs
     http_req_connecting............: avg=7.5µs   min=0s      med=0s      max=2.39ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=3.83ms  min=2.22ms  med=3.59ms  max=35.67ms  p(90)=4.79ms  p(95)=5.59ms
       { expected_response:true }...: avg=3.83ms  min=2.22ms  med=3.59ms  max=35.67ms  p(90)=4.79ms  p(95)=5.59ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 9293
     http_req_receiving.............: avg=40.35µs min=10.66µs med=35.8µs  max=2.52ms   p(90)=52.56µs p(95)=60.32µs
     http_req_sending...............: avg=30.38µs min=7.75µs  med=26.81µs max=914.25µs p(90)=38.16µs p(95)=70.64µs
     http_req_tls_handshaking.......: avg=33.16µs min=0s      med=0s      max=24.88ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3.76ms  min=2.17ms  med=3.51ms  max=35.64ms  p(90)=4.71ms  p(95)=5.5ms
     http_reqs......................: 9293    48.666128/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.03s    p(90)=1s      p(95)=1s
     iterations.....................: 9293    48.666128/s
     vus............................: 1       min=1       max=60
     vus_max........................: 60      min=60      max=60

```
