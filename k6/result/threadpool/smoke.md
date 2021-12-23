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
     data_received..................: 11 kB   1.1 kB/s
     data_sent......................: 1.0 kB  98 B/s
     http_req_blocked...............: avg=14.44ms min=0s     med=1µs     max=144.41ms p(90)=14.44ms  p(95)=79.43ms
     http_req_connecting............: avg=500.2µs min=0s     med=0s      max=5ms      p(90)=500.19µs p(95)=2.75ms
   ✓ http_req_duration..............: avg=11.36ms min=7.42ms med=11.55ms max=15.53ms  p(90)=15.06ms  p(95)=15.3ms
       { expected_response:true }...: avg=11.36ms min=7.42ms med=11.55ms max=15.53ms  p(90)=15.06ms  p(95)=15.3ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=57.99µs min=36µs   med=55µs    max=82µs     p(90)=81.1µs   p(95)=81.55µs
     http_req_sending...............: avg=91.59µs min=50µs   med=88µs    max=133µs    p(90)=124µs    p(95)=128.5µs
     http_req_tls_handshaking.......: avg=13.84ms min=0s     med=0s      max=138.43ms p(90)=13.84ms  p(95)=76.13ms
     http_req_waiting...............: avg=11.21ms min=7.25ms med=11.41ms max=15.35ms  p(90)=14.86ms  p(95)=15.11ms
     http_reqs......................: 10      0.972027/s
     iteration_duration.............: avg=2.05s   min=2.02s  med=2.02s   max=2.17s    p(90)=2.11s    p(95)=2.14s
     iterations.....................: 5       0.486014/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
