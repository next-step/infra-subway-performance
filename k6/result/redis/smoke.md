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


running (10.3s), 0/1 VUs, 5 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 10       ✗ 0
     data_received..................: 21 kB   2.0 kB/s
     data_sent......................: 1.0 kB  98 B/s
     http_req_blocked...............: avg=14.81ms min=0s     med=1µs      max=148.17ms p(90)=14.81ms p(95)=81.49ms
     http_req_connecting............: avg=1.09ms  min=0s     med=0s       max=10.97ms  p(90)=1.09ms  p(95)=6.03ms
   ✓ http_req_duration..............: avg=13.06ms min=8.03ms med=13.82ms  max=17.28ms  p(90)=16.84ms p(95)=17.06ms
       { expected_response:true }...: avg=13.06ms min=8.03ms med=13.82ms  max=17.28ms  p(90)=16.84ms p(95)=17.06ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=65.7µs  min=41µs   med=64µs     max=97µs     p(90)=92.5µs  p(95)=94.75µs
     http_req_sending...............: avg=107.2µs min=63µs   med=114.99µs max=165µs    p(90)=139.8µs p(95)=152.39µs
     http_req_tls_handshaking.......: avg=13.61ms min=0s     med=0s       max=136.19ms p(90)=13.61ms p(95)=74.9ms
     http_req_waiting...............: avg=12.88ms min=7.89ms med=13.66ms  max=17.16ms  p(90)=16.73ms p(95)=16.95ms
     http_reqs......................: 10      0.970697/s
     iteration_duration.............: avg=2.06s   min=2.02s  med=2.03s    max=2.17s    p(90)=2.11s   p(95)=2.14s
     iterations.....................: 5       0.485349/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```
