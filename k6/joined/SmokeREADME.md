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


running (01.0s), 1/1 VUs, 11 complete and 0 interrupted iterations
default   [  10% ] 1 VUs  01.0s/10s

running (02.0s), 1/1 VUs, 24 complete and 0 interrupted iterations
default   [  20% ] 1 VUs  02.0s/10s

running (03.0s), 1/1 VUs, 37 complete and 0 interrupted iterations
default   [  30% ] 1 VUs  03.0s/10s

running (04.0s), 1/1 VUs, 49 complete and 0 interrupted iterations
default   [  40% ] 1 VUs  04.0s/10s

running (05.0s), 1/1 VUs, 62 complete and 0 interrupted iterations
default   [  50% ] 1 VUs  05.0s/10s

running (06.0s), 1/1 VUs, 74 complete and 0 interrupted iterations
default   [  60% ] 1 VUs  06.0s/10s

running (07.0s), 1/1 VUs, 87 complete and 0 interrupted iterations
default   [  70% ] 1 VUs  07.0s/10s

running (08.0s), 1/1 VUs, 100 complete and 0 interrupted iterations
default   [  80% ] 1 VUs  08.0s/10s

running (09.0s), 1/1 VUs, 113 complete and 0 interrupted iterations
default   [  90% ] 1 VUs  09.0s/10s

running (10.0s), 1/1 VUs, 125 complete and 0 interrupted iterations
default   [ 100% ] 1 VUs  10.0s/10s

running (10.1s), 0/1 VUs, 127 complete and 0 interrupted iterations
default ✓ [ 100% ] 1 VUs  10s

     ✓ logged in successfully

     checks.........................: 100.00% ✓ 127       ✗ 0  
     data_received..................: 63 kB   6.3 kB/s
     data_sent......................: 24 kB   2.4 kB/s
     http_req_blocked...............: avg=81.82µs min=273ns   med=402ns   max=20.67ms  p(90)=527ns   p(95)=601ns  
     http_req_connecting............: avg=3.63µs  min=0s      med=0s      max=924.48µs p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=39.39ms min=3.76ms  med=37.97ms max=126.46ms p(90)=76.44ms p(95)=84.22ms
       { expected_response:true }...: avg=39.39ms min=3.76ms  med=37.97ms max=126.46ms p(90)=76.44ms p(95)=84.22ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 254
     http_req_receiving.............: avg=56.02µs min=33.41µs med=51.71µs max=517.22µs p(90)=68.94µs p(95)=74.57µs
     http_req_sending...............: avg=74.18µs min=29.91µs med=54.94µs max=3.69ms   p(90)=82.57µs p(95)=90.14µs
     http_req_tls_handshaking.......: avg=61.02µs min=0s      med=0s      max=15.49ms  p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=39.26ms min=3.66ms  med=36.89ms max=126.37ms p(90)=76.35ms p(95)=84.12ms
     http_reqs......................: 254     25.218047/s
     iteration_duration.............: avg=79.27ms min=70.89ms med=76ms    max=131.43ms p(90)=90.58ms p(95)=99.25ms
     iterations.....................: 127     12.609024/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1

```
