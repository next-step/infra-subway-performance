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


running (11.1s), 0/1 VUs, 6 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 18       ✗ 0
     data_received..................: 27 kB   2.5 kB/s
     data_sent......................: 3.5 kB  318 B/s
     http_req_blocked...............: avg=178ms    min=0s       med=0s       max=3.2s     p(90)=0s       p(95)=480.61ms
     http_req_connecting............: avg=245.16µs min=0s       med=0s       max=4.41ms   p(90)=0s       p(95)=661.93µs
   ✗ http_req_duration..............: avg=80.22ms  min=13.19ms  med=22.39ms  max=245.63ms p(90)=198.16ms p(95)=213.49ms
       { expected_response:true }...: avg=198.22ms min=169.76ms med=190.15ms max=245.63ms p(90)=226.73ms p(95)=236.18ms
     http_req_failed................: 66.66%  ✓ 12       ✗ 6
     http_req_receiving.............: avg=312.12µs min=0s       med=0s       max=1.95ms   p(90)=975.73µs p(95)=1.12ms
     http_req_sending...............: avg=170.96µs min=0s       med=0s       max=1.95ms   p(90)=547.56µs p(95)=802.99µs
     http_req_tls_handshaking.......: avg=175.06ms min=0s       med=0s       max=3.15s    p(90)=0s       p(95)=472.68ms
     http_req_waiting...............: avg=79.74ms  min=12.67ms  med=21.42ms  max=244.66ms p(90)=198.16ms p(95)=213.34ms
     http_reqs......................: 18      1.628328/s
     iteration_duration.............: avg=1.82s    min=1.22s    med=1.27s    max=4.57s    p(90)=2.96s    p(95)=3.76s
     iterations.....................: 6       0.542776/s
     vus............................: 0       min=0      max=1
     vus_max........................: 1       min=1      max=1
```
