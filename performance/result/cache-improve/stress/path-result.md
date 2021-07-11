```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: path-stress-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 12m40s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (12m10.8s), 000/240 VUs, 101551 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ path in successfully
     ✓ correct distance

     checks.........................: 100.00% ✓ 203102     ✗ 0
     data_received..................: 174 MB  238 kB/s
     data_sent......................: 8.9 MB  12 kB/s
     http_req_blocked...............: avg=16.29µs min=175ns   med=377ns   max=32.76ms p(90)=584ns    p(95)=644ns
     http_req_connecting............: avg=2.9µs   min=0s      med=0s      max=5.93ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=3.95ms  min=2.31ms  med=3.68ms  max=62.48ms p(90)=5.15ms   p(95)=5.93ms
       { expected_response:true }...: avg=3.95ms  min=2.31ms  med=3.68ms  max=62.48ms p(90)=5.15ms   p(95)=5.93ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 101551
     http_req_receiving.............: avg=68.95µs min=20.05µs med=52.97µs max=12.23ms p(90)=109.72µs p(95)=140.42µs
     http_req_sending...............: avg=32.49µs min=11.37µs med=25.23µs max=9.94ms  p(90)=53.2µs   p(95)=58.04µs
     http_req_tls_handshaking.......: avg=12.36µs min=0s      med=0s      max=26.28ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=3.85ms  min=4.5µs   med=3.58ms  max=62.42ms p(90)=5.05ms   p(95)=5.82ms
     http_reqs......................: 101551  138.961483/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.06s   p(90)=1s       p(95)=1s
     iterations.....................: 101551  138.961483/s
     vus............................: 7       min=1        max=240
     vus_max........................: 240     min=240      max=240

```
