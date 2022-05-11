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


running (01.0s), 1/1 VUs, 107 complete and 0 interrupted iterations
default   [  10% ] 1 VUs  01.0s/10s

running (02.0s), 1/1 VUs, 234 complete and 0 interrupted iterations
default   [  20% ] 1 VUs  02.0s/10s

running (03.0s), 1/1 VUs, 356 complete and 0 interrupted iterations
default   [  30% ] 1 VUs  03.0s/10s

running (04.0s), 1/1 VUs, 482 complete and 0 interrupted iterations
default   [  40% ] 1 VUs  04.0s/10s

running (05.0s), 1/1 VUs, 604 complete and 0 interrupted iterations
default   [  50% ] 1 VUs  05.0s/10s

running (06.0s), 1/1 VUs, 727 complete and 0 interrupted iterations
default   [  60% ] 1 VUs  06.0s/10s

running (07.0s), 1/1 VUs, 853 complete and 0 interrupted iterations
default   [  70% ] 1 VUs  07.0s/10s

running (08.0s), 1/1 VUs, 971 complete and 0 interrupted iterations
default   [  80% ] 1 VUs  08.0s/10s

running (09.0s), 1/1 VUs, 1097 complete and 0 interrupted iterations
default   [  90% ] 1 VUs  09.0s/10s

running (10.0s), 1/1 VUs, 1223 complete and 0 interrupted iterations
default   [ 100% ] 1 VUs  10.0s/10s

running (10.0s), 0/1 VUs, 1225 complete and 0 interrupted iterations
default ✓ [ 100% ] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 2450       ✗ 0   
     data_received..................: 842 kB  84 kB/s
     data_sent......................: 335 kB  34 kB/s
     http_req_blocked...............: avg=14.99µs min=237ns   med=367ns   max=26.34ms  p(90)=479ns   p(95)=519ns  
     http_req_connecting............: avg=1.1µs   min=0s      med=0s      max=1.02ms   p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=3.91ms  min=3.2ms   med=3.72ms  max=17.25ms  p(90)=4.36ms  p(95)=4.89ms 
       { expected_response:true }...: avg=3.77ms  min=3.2ms   med=3.56ms  max=16.54ms  p(90)=4.08ms  p(95)=4.64ms 
     http_req_failed................: 50.00%  ✓ 1225       ✗ 1225
     http_req_receiving.............: avg=36.7µs  min=19.01µs med=32.62µs max=690.41µs p(90)=46.23µs p(95)=54.52µs
     http_req_sending...............: avg=52.86µs min=26.88µs med=45.87µs max=4.71ms   p(90)=59.75µs p(95)=73.86µs
     http_req_tls_handshaking.......: avg=9.15µs  min=0s      med=0s      max=14.91ms  p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=3.82ms  min=3.13ms  med=3.63ms  max=17.12ms  p(90)=4.26ms  p(95)=4.71ms 
     http_reqs......................: 2450    244.779337/s
     iteration_duration.............: avg=8.15ms  min=6.93ms  med=7.72ms  max=37.55ms  p(90)=9.12ms  p(95)=10.59ms
     iterations.....................: 1225    122.389668/s
     vus............................: 1       min=1        max=1 
     vus_max........................: 1       min=1        max=1 

```
