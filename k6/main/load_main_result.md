```
* 개선전
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: ./load-main.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 1m0s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 30s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m30.6s), 000/400 VUs, 10114 complete and 0 interrupted iterations
default ✓ [======================================] 000/400 VUs  30s

     ✓ main page

     checks.........................: 100.00% ✓ 10114      ✗ 0    
     data_received..................: 12 MB   383 kB/s
     data_sent......................: 1.2 MB  40 kB/s
     http_req_blocked...............: avg=209.66µs min=1µs    med=4µs    max=14.13ms  p(90)=8µs    p(95)=17µs   
     http_req_connecting............: avg=200.88µs min=0s     med=0s     max=8.27ms   p(90)=0s     p(95)=0s     
   ✓ http_req_duration..............: avg=8.42ms   min=5.23ms med=7.62ms max=180.27ms p(90)=9.29ms p(95)=10.79ms
       { expected_response:true }...: avg=8.42ms   min=5.23ms med=7.62ms max=180.27ms p(90)=9.29ms p(95)=10.79ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 10114
     http_req_receiving.............: avg=49.38µs  min=15µs   med=42µs   max=251µs    p(90)=84µs   p(95)=93µs   
     http_req_sending...............: avg=20.85µs  min=5µs    med=17µs   max=255µs    p(90)=34µs   p(95)=43µs   
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s     max=0s       p(90)=0s     p(95)=0s     
     http_req_waiting...............: avg=8.35ms   min=5.18ms med=7.55ms max=180.2ms  p(90)=9.22ms p(95)=10.71ms
     http_reqs......................: 10114   330.033693/s
     iteration_duration.............: avg=1s       min=1s     med=1s     max=1.18s    p(90)=1.01s  p(95)=1.01s  
     iterations.....................: 10114   330.033693/s
     vus............................: 41      min=41       max=400
     vus_max........................: 400     min=400      max=400

```