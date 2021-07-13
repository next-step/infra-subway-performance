[뒤로가기](../../markdown/step1.md)

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


running (10.8s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get shortest line success

     checks.........................: 100.00% ✓ 30       ✗ 0
     data_received..................: 19 kB   1.8 kB/s
     data_sent......................: 5.3 kB  493 B/s
     http_req_blocked...............: avg=7.57ms   min=0s      med=1µs     max=227.23ms p(90)=2µs     p(95)=2µs
     http_req_connecting............: avg=223.96µs min=0s      med=0s      max=6.71ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=19.07ms  min=12.81ms med=17.55ms max=36.45ms  p(90)=26.06ms p(95)=26.98ms
       { expected_response:true }...: avg=19.07ms  min=12.81ms med=17.55ms max=36.45ms  p(90)=26.06ms p(95)=26.98ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 30
     http_req_receiving.............: avg=120.5µs  min=35µs    med=113.5µs max=236µs    p(90)=165.2µs p(95)=176.89µs
     http_req_sending...............: avg=139.6µs  min=19µs    med=66µs    max=1.11ms   p(90)=244.1µs p(95)=300.54µs
     http_req_tls_handshaking.......: avg=5.63ms   min=0s      med=0s      max=169.11ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=18.81ms  min=12.66ms med=17.3ms  max=36.23ms  p(90)=25.86ms p(95)=26.2ms
     http_reqs......................: 30      2.769033/s
     iteration_duration.............: avg=1.08s    min=1.04s   med=1.05s   max=1.29s    p(90)=1.1s    p(95)=1.19s
     iterations.....................: 10      0.923011/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```