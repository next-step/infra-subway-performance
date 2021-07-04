

### stress
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


running (4m45.6s), 000/270 VUs, 40059 complete and 0 interrupted iterations
default ✓ [======================================] 000/270 VUs  4m45.03s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 40059 ✗ 0    
     data_received..................: 49 MB   170 kB/s
     data_sent......................: 3.6 MB  12 kB/s
     http_req_blocked...............: avg=137.57µs min=0s     med=1µs    max=214.64ms p(90)=1µs     p(95)=1µs    
     http_req_connecting............: avg=39.94µs  min=0s     med=0s     max=46.94ms  p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=11.36ms  min=5.8ms  med=9.05ms max=228.96ms p(90)=16.6ms  p(95)=20.65ms
       { expected_response:true }...: avg=11.36ms  min=5.8ms  med=9.05ms max=228.96ms p(90)=16.6ms  p(95)=20.65ms
     http_req_failed................: 0.00%   ✓ 0     ✗ 40059
     http_req_receiving.............: avg=70.36µs  min=24µs   med=63µs   max=2.76ms   p(90)=106µs   p(95)=124µs  
     http_req_sending...............: avg=51.7µs   min=14µs   med=46µs   max=1.22ms   p(90)=82µs    p(95)=98µs   
     http_req_tls_handshaking.......: avg=95.42µs  min=0s     med=0s     max=195.73ms p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=11.24ms  min=5.68ms med=8.92ms max=228.86ms p(90)=16.48ms p(95)=20.51ms
     http_reqs......................: 40059   140.279605/s
     iteration_duration.............: avg=1.01s    min=1s     med=1s     max=1.23s    p(90)=1.01s   p(95)=1.02s  
     iterations.....................: 40059   140.279605/s
     vus............................: 6       min=4   max=270
     vus_max........................: 270     min=270 max=270

```
