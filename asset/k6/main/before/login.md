### login(stress)
```javascript
``` javascript
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
/          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: stress2.js
output: -

scenarios: (100.00%) 1 scenario, 270 max VUs, 5m15s max duration (incl. graceful stop):
* default: Up to 270 looping VUs for 4m45.03s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (4m45.8s), 000/270 VUs, 39071 complete and 0 interrupted iterations
default ✓ [======================================] 000/270 VUs  4m45.03s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 78142 ✗ 0    
     data_received..................: 21 MB   74 kB/s
     data_sent......................: 17 MB   61 kB/s
     http_req_blocked...............: avg=35.92µs min=1µs   med=3µs     max=142.6ms  p(90)=7µs     p(95)=8µs    
     http_req_connecting............: avg=31.67µs min=0s    med=0s      max=142.49ms p(90)=0s      p(95)=0s     
✓ http_req_duration..............: avg=18.29ms min=47µs  med=12.52ms max=276.23ms p(90)=20.7ms  p(95)=37.51ms
{ expected_response:true }...: avg=18.29ms min=47µs  med=12.52ms max=276.23ms p(90)=20.7ms  p(95)=37.51ms
http_req_failed................: 0.00%   ✓ 0     ✗ 78142
http_req_receiving.............: avg=52.12µs min=14µs  med=45µs    max=2.36ms   p(90)=86µs    p(95)=97µs   
http_req_sending...............: avg=23.87µs min=5µs   med=20µs    max=1.52ms   p(90)=38µs    p(95)=45µs   
http_req_tls_handshaking.......: avg=0s      min=0s    med=0s      max=0s       p(90)=0s      p(95)=0s     
http_req_waiting...............: avg=18.21ms min=0s    med=12.44ms max=276.13ms p(90)=20.64ms p(95)=37.47ms
http_reqs......................: 78142   273.408266/s
iteration_duration.............: avg=1.03s   min=1.01s med=1.02s   max=1.44s    p(90)=1.04s   p(95)=1.08s  
iterations.....................: 39071   136.704133/s
vus............................: 2       min=2   max=270
vus_max........................: 270     min=270 max=270
```
```
