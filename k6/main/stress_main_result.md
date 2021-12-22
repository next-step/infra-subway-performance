```
* 개선전
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: ./stress-main.js
     output: -

  scenarios: (100.00%) 1 scenario, 1200 max VUs, 2m10s max duration (incl. graceful stop):
           * default: Up to 1200 looping VUs for 1m40s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m40.9s), 0000/1200 VUs, 52888 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1200 VUs  1m40s

     ✓ main page

     checks.........................: 100.00% ✓ 52888      ✗ 0     
     data_received..................: 61 MB   609 kB/s
     data_sent......................: 6.4 MB  63 kB/s
     http_req_blocked...............: avg=126.4µs  min=1µs    med=3µs    max=16.75ms p(90)=6µs    p(95)=8µs    
     http_req_connecting............: avg=120.73µs min=0s     med=0s     max=16.67ms p(90)=0s     p(95)=0s     
   ✓ http_req_duration..............: avg=7.88ms   min=4.92ms med=7.58ms max=85.82ms p(90)=9.34ms p(95)=10.54ms
       { expected_response:true }...: avg=7.88ms   min=4.92ms med=7.58ms max=85.82ms p(90)=9.34ms p(95)=10.54ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 52888 
     http_req_receiving.............: avg=37.29µs  min=10µs   med=31µs   max=617µs   p(90)=63µs   p(95)=83µs   
     http_req_sending...............: avg=15.59µs  min=5µs    med=11µs   max=433µs   p(90)=27µs   p(95)=35µs   
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s     max=0s      p(90)=0s     p(95)=0s     
     http_req_waiting...............: avg=7.82ms   min=4.87ms med=7.53ms max=85.76ms p(90)=9.29ms p(95)=10.48ms
     http_reqs......................: 52888   524.087347/s
     iteration_duration.............: avg=1s       min=1s     med=1s     max=1.08s   p(90)=1.01s  p(95)=1.01s  
     iterations.....................: 52888   524.087347/s
     vus............................: 122     min=5        max=1200
     vus_max........................: 1200    min=1200     max=1200

```