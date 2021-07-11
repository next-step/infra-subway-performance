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


running (12m10.5s), 000/240 VUs, 101568 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ sign in successfully

     checks.........................: 100.00% ✓ 101568     ✗ 0
     data_received..................: 23 MB   32 kB/s
     data_sent......................: 25 MB   34 kB/s
     http_req_blocked...............: avg=20.15µs min=1.27µs med=3.17µs  max=82.93ms  p(90)=5.73µs  p(95)=6.27µs
     http_req_connecting............: avg=2.92µs  min=0s     med=0s      max=5.53ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=3.72ms  min=2.09ms med=3.31ms  max=107.99ms p(90)=4.84ms  p(95)=5.83ms
       { expected_response:true }...: avg=3.72ms  min=2.09ms med=3.31ms  max=107.99ms p(90)=4.84ms  p(95)=5.83ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 101568
     http_req_receiving.............: avg=39.18µs min=9.81µs med=29.1µs  max=10.77ms  p(90)=48.22µs p(95)=86.86µs
     http_req_sending...............: avg=29.77µs min=6.36µs med=16.83µs max=16.34ms  p(90)=34.27µs p(95)=67.52µs
     http_req_tls_handshaking.......: avg=12.61µs min=0s     med=0s      max=51.89ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3.65ms  min=2.05ms med=3.25ms  max=107.96ms p(90)=4.77ms  p(95)=5.74ms
     http_reqs......................: 101568  139.040749/s
     iteration_duration.............: avg=1s      min=1s     med=1s      max=1.1s     p(90)=1s      p(95)=1s
     iterations.....................: 101568  139.040749/s
     vus............................: 13      min=1        max=240
     vus_max........................: 240     min=240      max=240

```
