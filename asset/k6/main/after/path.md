### stress
```javascript
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
/          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: stress3.js
output: -

scenarios: (100.00%) 1 scenario, 240 max VUs, 5m15s max duration (incl. graceful stop):
* default: Up to 240 looping VUs for 4m45.03s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (4m45.8s), 000/240 VUs, 37519 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  4m45.03s

     ✓ path successfully 

     checks.........................: 100.00% ✓ 37519 ✗ 0    
     data_received..................: 20 MB   71 kB/s
     data_sent......................: 3.3 MB  12 kB/s
     http_req_blocked...............: avg=124.96µs min=0s     med=1µs    max=189.91ms p(90)=1µs     p(95)=1µs    
     http_req_connecting............: avg=36.53µs  min=0s     med=0s     max=32.72ms  p(90)=0s      p(95)=0s     
✓ http_req_duration..............: avg=13.28ms  min=6.57ms med=9.65ms max=311.97ms p(90)=14.74ms p(95)=22.05ms
{ expected_response:true }...: avg=13.28ms  min=6.57ms med=9.65ms max=311.97ms p(90)=14.74ms p(95)=22.05ms
http_req_failed................: 0.00%   ✓ 0     ✗ 37519
http_req_receiving.............: avg=66.13µs  min=22µs   med=58µs   max=4.88ms   p(90)=100µs   p(95)=120µs  
http_req_sending...............: avg=50.42µs  min=14µs   med=44µs   max=2.32ms   p(90)=81µs    p(95)=98µs   
http_req_tls_handshaking.......: avg=86.21µs  min=0s     med=0s     max=165.21ms p(90)=0s      p(95)=0s     
http_req_waiting...............: avg=13.16ms  min=6.48ms med=9.54ms max=311.85ms p(90)=14.62ms p(95)=21.93ms
http_reqs......................: 37519   131.264796/s
iteration_duration.............: avg=1.01s    min=1s     med=1.01s  max=1.31s    p(90)=1.01s   p(95)=1.02s  
iterations.....................: 37519   131.264796/s
vus............................: 7       min=4   max=240
vus_max........................: 240     min=240 max=240
```
