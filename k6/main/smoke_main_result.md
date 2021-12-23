```
* 개선전
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: ./smoke-main.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ main page

     checks.........................: 100.00% ✓ 10      ✗ 0  
     data_received..................: 12 kB   1.1 kB/s
     data_sent......................: 1.2 kB  119 B/s
     http_req_blocked...............: avg=2.03ms  min=5µs    med=7µs     max=20.29ms p(90)=2.03ms   p(95)=11.16ms 
     http_req_connecting............: avg=440.2µs min=0s     med=0s      max=4.4ms   p(90)=440.19µs p(95)=2.42ms  
   ✓ http_req_duration..............: avg=14.65ms min=9.02ms med=15.41ms max=19.12ms p(90)=16.08ms  p(95)=17.6ms  
       { expected_response:true }...: avg=14.65ms min=9.02ms med=15.41ms max=19.12ms p(90)=16.08ms  p(95)=17.6ms  
     http_req_failed................: 0.00%   ✓ 0       ✗ 10 
     http_req_receiving.............: avg=94.79µs min=78µs   med=87µs    max=163µs   p(90)=102.69µs p(95)=132.84µs
     http_req_sending...............: avg=35.6µs  min=14µs   med=34.5µs  max=74µs    p(90)=44.29µs  p(95)=59.14µs 
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s      max=0s      p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=14.52ms min=8.91ms med=15.29ms max=18.88ms p(90)=15.95ms  p(95)=17.42ms 
     http_reqs......................: 10      0.98241/s
     iteration_duration.............: avg=1.01s   min=1.01s  med=1.01s   max=1.04s   p(90)=1.01s    p(95)=1.02s   
     iterations.....................: 10      0.98241/s
     vus............................: 1       min=1     max=1
     vus_max........................: 1       min=1     max=1

```