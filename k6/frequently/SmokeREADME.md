## 결과
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: ./smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (01.0s), 1/1 VUs, 22 complete and 0 interrupted iterations
default   [  10% ] 1 VUs  01.0s/10s

running (02.0s), 1/1 VUs, 62 complete and 0 interrupted iterations
default   [  20% ] 1 VUs  02.0s/10s

running (03.0s), 1/1 VUs, 115 complete and 0 interrupted iterations
default   [  30% ] 1 VUs  03.0s/10s

running (04.0s), 1/1 VUs, 170 complete and 0 interrupted iterations
default   [  40% ] 1 VUs  04.0s/10s

running (05.0s), 1/1 VUs, 227 complete and 0 interrupted iterations
default   [  50% ] 1 VUs  05.0s/10s

running (06.0s), 1/1 VUs, 286 complete and 0 interrupted iterations
default   [  60% ] 1 VUs  06.0s/10s

running (07.0s), 1/1 VUs, 342 complete and 0 interrupted iterations
default   [  70% ] 1 VUs  07.0s/10s

running (08.0s), 1/1 VUs, 402 complete and 0 interrupted iterations
default   [  80% ] 1 VUs  08.0s/10s

running (09.0s), 1/1 VUs, 465 complete and 0 interrupted iterations
default   [  90% ] 1 VUs  09.0s/10s

running (10.0s), 1/1 VUs, 525 complete and 0 interrupted iterations
default   [ 100% ] 1 VUs  10.0s/10s

running (10.0s), 0/1 VUs, 526 complete and 0 interrupted iterations
default ✓ [ 100% ] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 1052      ✗ 0   
     data_received..................: 300 kB  30 kB/s
     data_sent......................: 95 kB   9.5 kB/s
     http_req_blocked...............: avg=34.52µs min=239ns   med=376ns   max=30.47ms  p(90)=538ns   p(95)=637ns  
     http_req_connecting............: avg=1.73µs  min=0s      med=0s      max=945.63µs p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=9.27ms  min=5.49ms  med=8.34ms  max=191.39ms p(90)=11.3ms  p(95)=13.39ms
       { expected_response:true }...: avg=9.27ms  min=5.49ms  med=8.34ms  max=191.39ms p(90)=11.3ms  p(95)=13.39ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 1052
     http_req_receiving.............: avg=56.61µs min=26.14µs med=52.79µs max=882.62µs p(90)=69.83µs p(95)=81.21µs
     http_req_sending...............: avg=61.09µs min=27.03µs med=56µs    max=1.05ms   p(90)=75.14µs p(95)=89.77µs
     http_req_tls_handshaking.......: avg=18.29µs min=0s      med=0s      max=14.95ms  p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=9.15ms  min=5.41ms  med=8.23ms  max=190.26ms p(90)=11.15ms p(95)=13.27ms
     http_reqs......................: 1052    105.13548/s
     iteration_duration.............: avg=19ms    min=12.72ms med=16.97ms max=353.55ms p(90)=22.3ms  p(95)=26.87ms
     iterations.....................: 526     52.56774/s
     vus............................: 1       min=1       max=1 
     vus_max........................: 1       min=1       max=1 
```
