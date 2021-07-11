```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: sign-stress-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 12m40s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (12m10.6s), 000/240 VUs, 101406 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ sign in successfully

     checks.........................: 100.00% ✓ 101406     ✗ 0
     data_received..................: 19 MB   26 kB/s
     data_sent......................: 15 MB   21 kB/s
     http_req_blocked...............: avg=16.53µs min=195ns   med=384ns   max=72.47ms  p(90)=568ns   p(95)=643ns
     http_req_connecting............: avg=3.07µs  min=0s      med=0s      max=10.76ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=5.35ms  min=2.85ms  med=4.47ms  max=197.09ms p(90)=7.85ms  p(95)=9.84ms
       { expected_response:true }...: avg=5.35ms  min=2.85ms  med=4.47ms  max=197.09ms p(90)=7.85ms  p(95)=9.84ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 101406
     http_req_receiving.............: avg=31.96µs min=7.81µs  med=25.15µs max=4.4ms    p(90)=39.19µs p(95)=68.83µs
     http_req_sending...............: avg=62.13µs min=21.47µs med=51.56µs max=7.39ms   p(90)=93µs    p(95)=106.86µs
     http_req_tls_handshaking.......: avg=12.23µs min=0s      med=0s      max=41.28ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=5.25ms  min=1.77ms  med=4.38ms  max=197.01ms p(90)=7.75ms  p(95)=9.74ms
     http_reqs......................: 101406  138.806765/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.19s    p(90)=1s      p(95)=1.01s
     iterations.....................: 101406  138.806765/s
     vus............................: 12      min=1        max=240
     vus_max........................: 240     min=240      max=240

```
