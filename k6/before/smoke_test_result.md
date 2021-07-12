```http

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke_test.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (11.0s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get shortest line success

     checks.........................: 100.00% ✓ 30       ✗ 0
     data_received..................: 23 kB   2.0 kB/s
     data_sent......................: 9.3 kB  845 B/s
     http_req_blocked...............: avg=7.18ms   min=2µs     med=7µs     max=215.35ms p(90)=15.1µs  p(95)=18.74µs
     http_req_connecting............: avg=268.93µs min=0s      med=0s      max=8.06ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=24.7ms   min=16.71ms med=22.83ms max=49.64ms  p(90)=32.93ms p(95)=43.89ms
       { expected_response:true }...: avg=24.7ms   min=16.71ms med=22.83ms max=49.64ms  p(90)=32.93ms p(95)=43.89ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 30
     http_req_receiving.............: avg=132.03µs min=29µs    med=113.5µs max=308µs    p(90)=213.1µs p(95)=260.64µs
     http_req_sending...............: avg=93.23µs  min=8µs     med=45µs    max=1.34ms   p(90)=85.3µs  p(95)=98.99µs
     http_req_tls_handshaking.......: avg=6.26ms   min=0s      med=0s      max=187.97ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=24.47ms  min=16.51ms med=22.68ms max=49.43ms  p(90)=32.75ms p(95)=43.73ms
     http_reqs......................: 30      2.727086/s
     iteration_duration.............: avg=1.09s    min=1.06s   med=1.07s   max=1.29s    p(90)=1.11s   p(95)=1.2s
     iterations.....................: 10      0.909029/s
     vus............................: 0       min=0      max=1
     vus_max........................: 1       min=1      max=1
```