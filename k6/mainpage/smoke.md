
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
/          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: smoke.js
output: -

scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
* default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ page load success

     checks.........................: 100.00% ✓ 10      ✗ 0  
     data_received..................: 16 kB   1.6 kB/s
     data_sent......................: 1.0 kB  98 B/s
     http_req_blocked...............: avg=7.54ms   min=229ns    med=1.08µs   max=75.43ms  p(90)=7.54ms   p(95)=41.48ms 
     http_req_connecting............: avg=528.28µs min=0s       med=0s       max=5.28ms   p(90)=528.28µs p(95)=2.9ms   
✓ http_req_duration..............: avg=11.66ms  min=9.76ms   med=10.31ms  max=19.02ms  p(90)=13.83ms  p(95)=16.43ms
{ expected_response:true }...: avg=11.66ms  min=9.76ms   med=10.31ms  max=19.02ms  p(90)=13.83ms  p(95)=16.43ms
http_req_failed................: 0.00%   ✓ 0       ✗ 10
http_req_receiving.............: avg=201.8µs  min=127.56µs med=198.65µs max=316.64µs p(90)=272.22µs p(95)=294.43µs
http_req_sending...............: avg=156.92µs min=77.73µs  med=145.26µs max=306.3µs  p(90)=185.28µs p(95)=245.79µs
http_req_tls_handshaking.......: avg=5.55ms   min=0s       med=0s       max=55.56ms  p(90)=5.55ms   p(95)=30.56ms
http_req_waiting...............: avg=11.31ms  min=9.38ms   med=9.99ms   max=18.5ms   p(90)=13.51ms  p(95)=16ms    
http_reqs......................: 10      0.98003/s
iteration_duration.............: avg=1.02s    min=1.01s    med=1.01s    max=1.09s    p(90)=1.02s    p(95)=1.05s   
iterations.....................: 10      0.98003/s
vus............................: 1       min=1     max=1
vus_max........................: 1       min=1     max=1
