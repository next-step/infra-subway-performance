
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
/          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: stress.js
output: -

scenarios: (100.00%) 1 scenario, 2000 max VUs, 4m30s max duration (incl. graceful stop):
* default: Up to 2000 looping VUs for 4m0s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)

WARN[0156] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0160] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0160] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0161] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0176] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0190] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0198] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0199] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0199] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0203] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0206] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"
WARN[0222] Request Failed                                error="Get \"https://wooobo.r-e.kr\": request timeout"

running (4m00.9s), 0000/2000 VUs, 156063 complete and 2 interrupted iterations
default ✓ [======================================] 0000/2000 VUs  4m0s

     ✗ page load success
      ↳  99% — ✓ 156051 / ✗ 12

     checks.........................: 99.99% ✓ 156051     ✗ 12    
     data_received..................: 189 MB 784 kB/s
     data_sent......................: 7.9 MB 33 kB/s
✓ errorRate......................: 0.00%  ✓ 12         ✗ 156051
http_req_blocked...............: avg=25.13ms  min=0s     med=873ns    max=56.6s   p(90)=1.09µs   p(95)=1.13µs  
http_req_connecting............: avg=6.14ms   min=0s     med=0s       max=16.39s  p(90)=0s       p(95)=0s      
✗ http_req_duration..............: avg=910.42ms min=0s     med=492.32ms max=1m0s    p(90)=2.19s    p(95)=3.22s   
{ expected_response:true }...: avg=906.26ms min=6.31ms med=492.31ms max=57.3s   p(90)=2.19s    p(95)=3.22s   
http_req_failed................: 0.00%  ✓ 12         ✗ 156051
http_req_receiving.............: avg=22.2ms   min=0s     med=255.89µs max=47.77s  p(90)=1.71ms   p(95)=3.25ms  
http_req_sending...............: avg=115.49µs min=0s     med=93.11µs  max=47.13ms p(90)=136.33µs p(95)=213.55µs
http_req_tls_handshaking.......: avg=18.99ms  min=0s     med=0s       max=55.58s  p(90)=0s       p(95)=0s      
http_req_waiting...............: avg=888.1ms  min=0s     med=490.79ms max=1m0s    p(90)=2.13s    p(95)=3.13s   
http_reqs......................: 156063 647.765638/s
iteration_duration.............: avg=1.93s    min=1s     med=1.49s    max=1m1s    p(90)=3.22s    p(95)=4.33s   
iterations.....................: 156063 647.765638/s
vus............................: 15     min=15       max=2000
vus_max........................: 2000   min=2000     max=2000

