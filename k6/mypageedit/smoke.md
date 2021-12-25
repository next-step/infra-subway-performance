
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


running (10.5s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ 로그인페이지_응답 load success
     ✓ 로그인_요청_응답 load success
     ✓ 수정페이지_응답 load success
     ✓ 내정보_수정_응답 load success

     checks.........................: 100.00% ✓ 40       ✗ 0  
     data_received..................: 31 kB   3.0 kB/s
     data_sent......................: 5.4 kB  511 B/s
     http_req_blocked...............: avg=896.94µs min=282ns  med=1.11µs   max=35.83ms  p(90)=1.21µs   p(95)=1.24µs  
     http_req_connecting............: avg=132.99µs min=0s     med=0s       max=5.31ms   p(90)=0s       p(95)=0s      
✓ http_req_duration..............: avg=11.62ms  min=8.41ms med=11.29ms  max=28.68ms  p(90)=13.7ms   p(95)=16.79ms
{ expected_response:true }...: avg=11.62ms  min=8.41ms med=11.29ms  max=28.68ms  p(90)=13.7ms   p(95)=16.79ms
http_req_failed................: 0.00%   ✓ 0        ✗ 40
http_req_receiving.............: avg=187.48µs min=63.6µs med=169.74µs max=609.3µs  p(90)=302.12µs p(95)=338.8µs
http_req_sending...............: avg=132.75µs min=36.2µs med=142.46µs max=285.05µs p(90)=173.08µs p(95)=188.03µs
http_req_tls_handshaking.......: avg=604.69µs min=0s     med=0s       max=24.18ms  p(90)=0s       p(95)=0s      
http_req_waiting...............: avg=11.3ms   min=8.16ms med=10.96ms  max=28.52ms  p(90)=13.45ms  p(95)=16.53ms
http_reqs......................: 40      3.801298/s
iteration_duration.............: avg=1.05s    min=1.04s  med=1.04s    max=1.1s     p(90)=1.05s    p(95)=1.07s   
iterations.....................: 10      0.950324/s
vus............................: 1       min=1      max=1
vus_max........................: 1       min=1      max=1
