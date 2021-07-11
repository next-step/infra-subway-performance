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


running (12m10.9s), 000/240 VUs, 101640 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ main in successfully

     checks.........................: 100.00% ✓ 101640     ✗ 0
     data_received..................: 120 MB  164 kB/s
     data_sent......................: 8.9 MB  12 kB/s
     http_req_blocked...............: avg=16.5µs  min=185ns   med=382ns   max=66.82ms  p(90)=599ns   p(95)=662ns
     http_req_connecting............: avg=2.92µs  min=0s      med=0s      max=6.78ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=3.09ms  min=1.9ms   med=2.84ms  max=217.14ms p(90)=3.93ms  p(95)=4.53ms
       { expected_response:true }...: avg=3.09ms  min=1.9ms   med=2.84ms  max=217.14ms p(90)=3.93ms  p(95)=4.53ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 101640
     http_req_receiving.............: avg=54.33µs min=16.74µs med=41.6µs  max=7.22ms   p(90)=78.71µs p(95)=123.55µs
     http_req_sending...............: avg=31.84µs min=11.14µs med=24.82µs max=4.11ms   p(90)=53.13µs p(95)=57.95µs
     http_req_tls_handshaking.......: avg=12.27µs min=0s      med=0s      max=45.93ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3ms     min=1.76ms  med=2.75ms  max=217.09ms p(90)=3.84ms  p(95)=4.45ms
     http_reqs......................: 101640  139.052001/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.21s    p(90)=1s      p(95)=1s
     iterations.....................: 101640  139.052001/s
     vus............................: 15      min=1        max=240
     vus_max........................: 240     min=240      max=240

```
