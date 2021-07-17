```bash
$ k6 run stress.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 360 max VUs, 2m5s max duration (incl. graceful stop):
           * default: Up to 360 looping VUs for 1m35s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m35.8s), 000/360 VUs, 19746 complete and 0 interrupted iterations
default ✗ [======================================] 000/360 VUs  1m35s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 39492      ✗ 0
     data_received..................: 71 MB   740 kB/s
     data_sent......................: 9.4 MB  99 kB/s
     http_req_blocked...............: avg=33.93µs min=2.05µs  med=2.73µs  max=30.55ms  p(90)=2.9µs   p(95)=3.02µs
     http_req_connecting............: avg=4.7µs   min=0s      med=0s      max=17.12ms  p(90)=0s      p(95)=0s
   ✗ http_req_duration..............: avg=18.74ms min=1.82ms  med=11.6ms  max=249.59ms p(90)=42.21ms p(95)=57.93ms
       { expected_response:true }...: avg=18.74ms min=1.82ms  med=11.6ms  max=249.59ms p(90)=42.21ms p(95)=57.93ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 59238
     http_req_receiving.............: avg=68.35µs min=26.12µs med=56.84µs max=8.84ms   p(90)=81.66µs p(95)=117.35µs
     http_req_sending...............: avg=47.69µs min=16.9µs  med=36.19µs max=9.47ms   p(90)=72.51µs p(95)=84.47µs
     http_req_tls_handshaking.......: avg=25.3µs  min=0s      med=0s      max=29.21ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=18.62ms min=1.74ms  med=11.48ms max=249.51ms p(90)=42.1ms  p(95)=57.81ms
     http_reqs......................: 59238   618.089433/s
     iteration_duration.............: avg=1.05s   min=1.01s   med=1.04s   max=1.45s    p(90)=1.11s   p(95)=1.14s
     iterations.....................: 19746   206.029811/s
     vus............................: 40      min=12       max=360
     vus_max........................: 360     min=360      max=360


```
