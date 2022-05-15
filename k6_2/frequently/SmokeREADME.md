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


running (01.0s), 1/1 VUs, 86 complete and 0 interrupted iterations
default   [  10% ] 1 VUs  01.0s/10s

running (02.0s), 1/1 VUs, 179 complete and 0 interrupted iterations
default   [  20% ] 1 VUs  02.0s/10s

running (03.0s), 1/1 VUs, 271 complete and 0 interrupted iterations
default   [  30% ] 1 VUs  03.0s/10s

running (04.0s), 1/1 VUs, 365 complete and 0 interrupted iterations
default   [  40% ] 1 VUs  04.0s/10s

running (05.0s), 1/1 VUs, 457 complete and 0 interrupted iterations
default   [  50% ] 1 VUs  05.0s/10s

running (06.0s), 1/1 VUs, 549 complete and 0 interrupted iterations
default   [  60% ] 1 VUs  06.0s/10s

running (07.0s), 1/1 VUs, 643 complete and 0 interrupted iterations
default   [  70% ] 1 VUs  07.0s/10s

running (08.0s), 1/1 VUs, 736 complete and 0 interrupted iterations
default   [  80% ] 1 VUs  08.0s/10s

running (09.0s), 1/1 VUs, 831 complete and 0 interrupted iterations
default   [  90% ] 1 VUs  09.0s/10s

running (10.0s), 1/1 VUs, 924 complete and 0 interrupted iterations
default   [ 100% ] 1 VUs  10.0s/10s

running (10.0s), 0/1 VUs, 926 complete and 0 interrupted iterations
default ✓ [ 100% ] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 1852       ✗ 0  
     data_received..................: 475 kB  47 kB/s
     data_sent......................: 163 kB  16 kB/s
     http_req_blocked...............: avg=9.23µs  min=228ns   med=298ns   max=16.38ms  p(90)=417ns   p(95)=477ns   
     http_req_connecting............: avg=446ns   min=0s      med=0s      max=826.39µs p(90)=0s      p(95)=0s      
   ✓ http_req_duration..............: avg=5.23ms  min=1.25ms  med=5.47ms  max=49.25ms  p(90)=10.65ms p(95)=11.06ms 
       { expected_response:true }...: avg=6.75ms  min=1.99ms  med=9.01ms  max=49.25ms  p(90)=11.03ms p(95)=11.78ms 
     http_req_failed................: 50.00%  ✓ 926        ✗ 926
     http_req_receiving.............: avg=54.74µs min=18.34µs med=42.1µs  max=3.26ms   p(90)=72.66µs p(95)=108.19µs
     http_req_sending...............: avg=46.58µs min=19.7µs  med=41.61µs max=640.75µs p(90)=64.62µs p(95)=73.05µs 
     http_req_tls_handshaking.......: avg=7.04µs  min=0s      med=0s      max=13.03ms  p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=5.13ms  min=1.08ms  med=5.41ms  max=49.15ms  p(90)=10.55ms p(95)=10.94ms 
     http_reqs......................: 1852    184.917098/s
     iteration_duration.............: avg=10.79ms min=6.91ms  med=12.56ms max=52.06ms  p(90)=14.07ms p(95)=16.01ms 
     iterations.....................: 926     92.458549/s
     vus............................: 1       min=1        max=1
     vus_max........................: 1       min=1        max=1

```
