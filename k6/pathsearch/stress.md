
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
/          \   |  |\  \ |  (‾)  |
/ __________ \  |__| \__\ \_____/ .io

execution: local
script: stress.js
output: -

scenarios: (100.00%) 1 scenario, 2000 max VUs, 1m30s max duration (incl. graceful stop):
* default: Up to 2000 looping VUs for 1m0s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)

WARN[0051] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"
WARN[0054] Request Failed                                error="Get \"https://wooobo.r-e.kr/login\": dial: i/o timeout"

running (1m00.1s), 0000/2000 VUs, 8893 complete and 1038 interrupted iterations
default ✗ [======================================] 0932/2000 VUs  1m0s

     ✗ 로그인페이지_응답 load success
      ↳  99% — ✓ 9744 / ✗ 3
     ✓ 로그인_요청_응답 load success
     ✓ 경로검색_페이지_응답 load success
     ✓ 경로_검색_응답 load success
     ✓ 경로_좋아요_응답 load success

     checks.........................: 99.99% ✓ 47642      ✗ 3     
     data_received..................: 39 MB  641 kB/s
     data_sent......................: 7.5 MB 125 kB/s
✗ errorRate......................: 59.47% ✓ 28339      ✗ 19306
http_req_blocked...............: avg=192.9ms  min=0s     med=885ns    max=39.37s  p(90)=1.17µs   p(95)=1.26µs  
http_req_connecting............: avg=43.26ms  min=0s     med=0s       max=16.11s  p(90)=0s       p(95)=0s      
✗ http_req_duration..............: avg=1.08s    min=0s     med=403.29ms max=37.23s  p(90)=2.57s    p(95)=3.94s   
{ expected_response:true }...: avg=1.12s    min=5.96ms med=406.66ms max=37.23s  p(90)=2.64s    p(95)=4.13s   
http_req_failed................: 39.23% ✓ 18692      ✗ 28953
http_req_receiving.............: avg=21.08ms  min=0s     med=139.74µs max=20.36s  p(90)=1.12ms   p(95)=2.13ms  
http_req_sending...............: avg=133µs    min=0s     med=102.14µs max=27.63ms p(90)=163.17µs p(95)=236.49µs
http_req_tls_handshaking.......: avg=149.62ms min=0s     med=0s       max=38.99s  p(90)=0s       p(95)=0s      
http_req_waiting...............: avg=1.06s    min=0s     med=400.64ms max=37.23s  p(90)=2.53s    p(95)=3.88s   
http_reqs......................: 47645  792.855659/s
iteration_duration.............: avg=6.8s     min=1.05s  med=4.93s    max=44.28s  p(90)=14.04s   p(95)=19.69s  
iterations.....................: 8893   147.98752/s
vus............................: 1045   min=50       max=2000
vus_max........................: 2000   min=2000     max=2000
