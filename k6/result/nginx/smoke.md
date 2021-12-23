```javascript

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.8s), 0/1 VUs, 5 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 10       ✗ 0
     data_received..................: 21 kB   1.9 kB/s
     data_sent......................: 1.0 kB  94 B/s
     http_req_blocked...............: avg=19.49ms min=0s    med=1µs     max=194.96ms p(90)=19.49ms  p(95)=107.23ms
     http_req_connecting............: avg=500.9µs min=0s    med=0s      max=5ms      p(90)=500.89µs p(95)=2.75ms
   ✓ http_req_duration..............: avg=54.76ms min=5.5ms med=54.72ms max=224.21ms p(90)=80.27ms  p(95)=152.24ms
       { expected_response:true }...: avg=54.76ms min=5.5ms med=54.72ms max=224.21ms p(90)=80.27ms  p(95)=152.24ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=51.4µs  min=36µs  med=53µs    max=61µs     p(90)=59.2µs   p(95)=60.09µs
     http_req_sending...............: avg=292.8µs min=64µs  med=125.5µs max=1.87ms   p(90)=405.69µs p(95)=1.13ms
     http_req_tls_handshaking.......: avg=17.5ms  min=0s    med=0s      max=175.05ms p(90)=17.5ms   p(95)=96.28ms
     http_req_waiting...............: avg=54.41ms min=5.4ms med=54.58ms max=224.02ms p(90)=79.99ms  p(95)=152ms
     http_reqs......................: 10      0.929029/s
     iteration_duration.............: avg=2.15s   min=2.06s med=2.07s   max=2.28s    p(90)=2.27s    p(95)=2.27s
     iterations.....................: 5       0.464514/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
