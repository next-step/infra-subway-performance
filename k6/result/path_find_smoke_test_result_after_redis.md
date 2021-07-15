```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: performance_find_path_smoke_test.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.4s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 30       ✗ 0
     data_received..................: 19 kB   1.8 kB/s
     data_sent......................: 4.0 kB  387 B/s
     http_req_blocked...............: avg=4.74ms   min=0s     med=0s       max=142.25ms p(90)=0s       p(95)=0s
     http_req_connecting............: avg=81.92µs  min=0s     med=0s       max=2.45ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=7.21ms   min=5.3ms  med=6.63ms   max=16.2ms   p(90)=8.73ms   p(95)=9.78ms
       { expected_response:true }...: avg=6.31ms   min=5.78ms med=6.24ms   max=7.08ms   p(90)=6.96ms   p(95)=7.02ms
     http_req_failed................: 66.66%  ✓ 20       ✗ 10
     http_req_receiving.............: avg=302.5µs  min=0s     med=140.79µs max=1.06ms   p(90)=756.84µs p(95)=913.31µs
     http_req_sending...............: avg=240.46µs min=0s     med=0s       max=5.38ms   p(90)=220.26µs p(95)=849.6µs
     http_req_tls_handshaking.......: avg=4.44ms   min=0s     med=0s       max=133.26ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=6.67ms   min=5.17ms med=6.36ms   max=10.21ms  p(90)=8.58ms   p(95)=9.08ms
     http_reqs......................: 30      2.890893/s
     iteration_duration.............: avg=1.03s    min=1.01s  med=1.02s    max=1.17s    p(90)=1.03s    p(95)=1.1s
     iterations.....................: 10      0.963631/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
