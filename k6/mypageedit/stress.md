
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
/          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: stress.js
output: -

scenarios: (100.00%) 1 scenario, 2000 max VUs, 1m20s max duration (incl. graceful stop):
* default: Up to 2000 looping VUs for 50s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)

WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0055] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0055] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0055] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0055] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0055] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0055] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"

running (1m00.0s), 0000/2000 VUs, 9886 complete and 36 interrupted iterations
default ✗ [======================================] 0036/2000 VUs  50s

     ✗ 로그인페이지_응답 load success
      ↳  99% — ✓ 9882 / ✗ 13
     ✓ 로그인_요청_응답 load success
     ✓ 수정페이지_응답 load success
     ✓ 내정보_수정_응답 load success

     checks.........................: 99.96% ✓ 39555      ✗ 13    
     data_received..................: 36 MB  591 kB/s
     data_sent......................: 5.9 MB 99 kB/s
✗ errorRate......................: 49.97% ✓ 19774      ✗ 19794
http_req_blocked...............: avg=271.93ms min=0s     med=1.03µs   max=38.59s p(90)=1.2µs    p(95)=731.62µs
http_req_connecting............: avg=71.92ms  min=0s     med=0s       max=18.88s p(90)=0s       p(95)=0s      
✗ http_req_duration..............: avg=958.69ms min=0s     med=371.13ms max=34.08s p(90)=2.15s    p(95)=3.37s   
{ expected_response:true }...: avg=959.01ms min=5.46ms med=371.19ms max=34.08s p(90)=2.15s    p(95)=3.37s   
http_req_failed................: 0.03%  ✓ 13         ✗ 39555
http_req_receiving.............: avg=15.91ms  min=0s     med=106.38µs max=18.29s p(90)=970.05µs p(95)=1.81ms  
http_req_sending...............: avg=152.9µs  min=0s     med=119.66µs max=23.4ms p(90)=196.42µs p(95)=280.46µs
http_req_tls_handshaking.......: avg=199.98ms min=0s     med=0s       max=38.3s  p(90)=0s       p(95)=0s      
http_req_waiting...............: avg=942.62ms min=0s     med=369.17ms max=34.08s p(90)=2.11s    p(95)=3.34s   
http_reqs......................: 39568  659.196534/s
iteration_duration.............: avg=5.94s    min=1.03s  med=3.76s    max=39.83s p(90)=13.38s   p(95)=18.81s  
iterations.....................: 9886   164.699174/s
vus............................: 36     min=36       max=2000
vus_max........................: 2000   min=2000     max=2000

