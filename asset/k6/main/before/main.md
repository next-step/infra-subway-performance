### K6 main(stess)

```javascript

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 270 max VUs, 5m15s max duration (incl. graceful stop):
           * default: Up to 270 looping VUs for 4m45.03s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (4m45.9s), 000/270 VUs, 40002 complete and 0 interrupted iterations
default ✓ [======================================] 000/270 VUs  4m45.03s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 40002 ✗ 0    
     data_received..................: 47 MB   166 kB/s
     data_sent......................: 3.2 MB  11 kB/s
     http_req_blocked...............: avg=72.32µs min=1µs    med=4µs    max=161.73ms p(90)=7µs     p(95)=9µs    
     http_req_connecting............: avg=66.82µs min=0s     med=0s     max=161.66ms p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=12.78ms min=5.65ms med=8.85ms max=275.58ms p(90)=14.14ms p(95)=38.19ms
       { expected_response:true }...: avg=12.78ms min=5.65ms med=8.85ms max=275.58ms p(90)=14.14ms p(95)=38.19ms
     http_req_failed................: 0.00%   ✓ 0     ✗ 40002
     http_req_receiving.............: avg=51.19µs min=13µs   med=43µs   max=2.26ms   p(90)=85µs    p(95)=98µs   
     http_req_sending...............: avg=21.4µs  min=5µs    med=17µs   max=1ms      p(90)=33µs    p(95)=41µs   
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s     max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=12.71ms min=5.62ms med=8.78ms max=275.53ms p(90)=14.06ms p(95)=38.09ms
     http_reqs......................: 40002   139.924177/s
     iteration_duration.............: avg=1.01s   min=1s     med=1s     max=1.31s    p(90)=1.01s   p(95)=1.03s  
     iterations.....................: 40002   139.924177/s
     vus............................: 4       min=4   max=270
     vus_max........................: 270     min=270 max=270
```
