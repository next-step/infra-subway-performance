
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
/          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: load.js
output: -

scenarios: (100.00%) 1 scenario, 255 max VUs, 1m30s max duration (incl. graceful stop):
* default: Up to 255 looping VUs for 1m0s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m00.9s), 000/255 VUs, 5903 complete and 0 interrupted iterations
default ↓ [======================================] 011/255 VUs  1m0s

     ✓ 로그인페이지_응답 load success
     ✓ 로그인_요청_응답 load success
     ✓ 수정페이지_응답 load success
     ✓ 내정보_수정_응답 load success

     checks.........................: 100.00% ✓ 23612      ✗ 0    
     data_received..................: 17 MB   277 kB/s
     data_sent......................: 3.0 MB  49 kB/s
     http_req_blocked...............: avg=489.29µs min=149ns   med=1.04µs   max=888.33ms p(90)=1.17µs   p(95)=1.2µs   
     http_req_connecting............: avg=127.16µs min=0s      med=0s       max=235.83ms p(90)=0s       p(95)=0s      
✓ http_req_duration..............: avg=35.25ms  min=5.87ms  med=10.18ms  max=708.81ms p(90)=32.33ms  p(95)=273.61ms
{ expected_response:true }...: avg=35.25ms  min=5.87ms  med=10.18ms  max=708.81ms p(90)=32.33ms  p(95)=273.61ms
http_req_failed................: 0.00%   ✓ 0          ✗ 23612
http_req_receiving.............: avg=263.95µs min=9.52µs  med=134.69µs max=328.74ms p(90)=352.63µs p(95)=671.62µs
http_req_sending...............: avg=130.69µs min=16.72µs med=117.25µs max=18.43ms  p(90)=164.76µs p(95)=195.47µs
http_req_tls_handshaking.......: avg=357.1µs  min=0s      med=0s       max=651.61ms p(90)=0s       p(95)=0s      
http_req_waiting...............: avg=34.85ms  min=2.82ms  med=9.82ms   max=636.41ms p(90)=31.92ms  p(95)=273.38ms
http_reqs......................: 23612   387.996238/s
iteration_duration.............: avg=1.14s    min=1.02s   med=1.04s    max=3.41s    p(90)=1.13s    p(95)=2.15s   
iterations.....................: 5903    96.999059/s
vus............................: 11      min=5        max=254
vus_max........................: 255     min=255      max=255
