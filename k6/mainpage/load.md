
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
/          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: load.js
output: -

scenarios: (100.00%) 1 scenario, 510 max VUs, 1m30s max duration (incl. graceful stop):
* default: Up to 510 looping VUs for 1m0s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m00.9s), 000/510 VUs, 14190 complete and 0 interrupted iterations
default ✓ [======================================] 000/510 VUs  1m0s

     ✓ page load success

     checks.........................: 100.00% ✓ 14190      ✗ 0    
     data_received..................: 19 MB   307 kB/s
     data_sent......................: 902 kB  15 kB/s
     http_req_blocked...............: avg=1.14ms   min=151ns   med=1.05µs  max=103.39ms p(90)=1.14µs   p(95)=1.23µs  
     http_req_connecting............: avg=293.62µs min=0s      med=0s      max=32.09ms  p(90)=0s       p(95)=0s      
✓ http_req_duration..............: avg=12.69ms  min=6.4ms   med=9.72ms  max=102.4ms  p(90)=22.34ms  p(95)=26.81ms
{ expected_response:true }...: avg=12.69ms  min=6.4ms   med=9.72ms  max=102.4ms  p(90)=22.34ms  p(95)=26.81ms
http_req_failed................: 0.00%   ✓ 0          ✗ 14190
http_req_receiving.............: avg=303.59µs min=18.13µs med=233.3µs max=11.21ms  p(90)=392.69µs p(95)=858.86µs
http_req_sending...............: avg=125.97µs min=17.21µs med=110.8µs max=9.6ms    p(90)=155.1µs  p(95)=244.97µs
http_req_tls_handshaking.......: avg=840µs    min=0s      med=0s      max=76.87ms  p(90)=0s       p(95)=0s      
http_req_waiting...............: avg=12.26ms  min=4.88ms  med=9.3ms   max=100.83ms p(90)=21.89ms  p(95)=26.36ms
http_reqs......................: 14190   233.034293/s
iteration_duration.............: avg=1.01s    min=1s      med=1.01s   max=1.13s    p(90)=1.02s    p(95)=1.03s   
iterations.....................: 14190   233.034293/s
vus............................: 24      min=10       max=510
vus_max........................: 510     min=510      max=510
