## 결과

```

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


running (01.0s), 1/1 VUs, 140 complete and 0 interrupted iterations
default   [  10% ] 1 VUs  01.0s/10s

running (02.0s), 1/1 VUs, 288 complete and 0 interrupted iterations
default   [  20% ] 1 VUs  02.0s/10s

running (03.0s), 1/1 VUs, 441 complete and 0 interrupted iterations
default   [  30% ] 1 VUs  03.0s/10s

running (04.0s), 1/1 VUs, 589 complete and 0 interrupted iterations
default   [  40% ] 1 VUs  04.0s/10s

running (05.0s), 1/1 VUs, 741 complete and 0 interrupted iterations
default   [  50% ] 1 VUs  05.0s/10s

running (06.0s), 1/1 VUs, 896 complete and 0 interrupted iterations
default   [  60% ] 1 VUs  06.0s/10s

running (07.0s), 1/1 VUs, 1052 complete and 0 interrupted iterations
default   [  70% ] 1 VUs  07.0s/10s

running (08.0s), 1/1 VUs, 1210 complete and 0 interrupted iterations
default   [  80% ] 1 VUs  08.0s/10s

running (09.0s), 1/1 VUs, 1362 complete and 0 interrupted iterations
default   [  90% ] 1 VUs  09.0s/10s

running (10.0s), 1/1 VUs, 1519 complete and 0 interrupted iterations
default   [ 100% ] 1 VUs  10.0s/10s

running (10.0s), 0/1 VUs, 1520 complete and 0 interrupted iterations
default ✓ [ 100% ] 1 VUs  10s

     ✓ logged in successfully

     checks.........................: 100.00% ✓ 1520       ✗ 0   
     data_received..................: 867 kB  87 kB/s
     data_sent......................: 413 kB  41 kB/s
     http_req_blocked...............: avg=5.41µs  min=217ns   med=311ns   max=15.04ms  p(90)=450ns   p(95)=494ns  
     http_req_connecting............: avg=278ns   min=0s      med=0s      max=846.29µs p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=3.15ms  min=1.24ms  med=3.39ms  max=17.46ms  p(90)=3.97ms  p(95)=4.71ms 
       { expected_response:true }...: avg=3.64ms  min=3.04ms  med=3.44ms  max=17.46ms  p(90)=3.94ms  p(95)=4.57ms 
     http_req_failed................: 50.00%  ✓ 1520       ✗ 1520
     http_req_receiving.............: avg=56.8µs  min=17.23µs med=37.77µs max=5.69ms   p(90)=64.93µs p(95)=96.51µs
     http_req_sending...............: avg=46.14µs min=24.27µs med=38.55µs max=5.81ms   p(90)=56.88µs p(95)=65.72µs
     http_req_tls_handshaking.......: avg=4.38µs  min=0s      med=0s      max=13.32ms  p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=3.05ms  min=1.11ms  med=3.3ms   max=17.37ms  p(90)=3.87ms  p(95)=4.55ms 
     http_reqs......................: 3040    303.881652/s
     iteration_duration.............: avg=6.56ms  min=4.52ms  med=6.86ms  max=24.84ms  p(90)=8.38ms  p(95)=10.06ms
     iterations.....................: 1520    151.940826/s
     vus............................: 1       min=1        max=1 
     vus_max........................: 1       min=1        max=1 

```
