### 개선 전

##설정 
    export let options = { vus: 1, duration: '10s',    
        thresholds: {
            http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
        },
    };
##작업
    //login 요청
    //정보확인
    //역 조회
    //노선 조회
    //경로 조회


execution: local
script: smoke.js
output: -

scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
* default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.9s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 20       ✗ 0  
     data_received..................: 20 kB   1.9 kB/s
     data_sent......................: 8.9 kB  817 B/s
     http_req_blocked...............: avg=744.32µs min=3.92µs  med=4.61µs  max=36.95ms  p(90)=8.02µs  p(95)=8.47µs 
     http_req_connecting............: avg=8.77µs   min=0s      med=0s      max=438.94µs p(90)=0s      p(95)=0s     
✓ http_req_duration..............: avg=17.06ms  min=3.38ms  med=4.82ms  max=464.15ms p(90)=10.3ms  p(95)=31.83ms
{ expected_response:true }...: avg=6.41ms   min=3.38ms  med=4.22ms  max=30.18ms  p(90)=7.51ms  p(95)=23.27ms
http_req_failed................: 60.00%  ✓ 30       ✗ 20
http_req_receiving.............: avg=57.99µs  min=39.51µs med=48.93µs max=133.91µs p(90)=78.24µs p(95)=91.01µs
http_req_sending...............: avg=20.76µs  min=12.05µs med=15.15µs max=126.32µs p(90)=30.9µs  p(95)=36.63µs
http_req_tls_handshaking.......: avg=556.23µs min=0s      med=0s      max=27.81ms  p(90)=0s      p(95)=0s     
http_req_waiting...............: avg=16.98ms  min=3.33ms  med=4.75ms  max=463.93ms p(90)=10.18ms p(95)=31.7ms
http_reqs......................: 50      4.584301/s
iteration_duration.............: avg=1.09s    min=1.02s   med=1.03s   max=1.63s    p(90)=1.09s   p(95)=1.36s  
iterations.....................: 10      0.91686/s
vus............................: 1       min=1      max=1
vus_max........................: 1       min=1      max=1


###개선 후

running (10.6s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 20       ✗ 0  
     data_received..................: 20 kB   1.9 kB/s
     data_sent......................: 8.9 kB  839 B/s
     http_req_blocked...............: avg=822.43µs min=4.12µs  med=4.68µs  max=40.81ms  p(90)=8.3µs   p(95)=11.76µs
     http_req_connecting............: avg=10.54µs  min=0s      med=0s      max=527.41µs p(90)=0s      p(95)=0s     
✓ http_req_duration..............: avg=11.17ms  min=3.5ms   med=4.87ms  max=208.14ms p(90)=10.65ms p(95)=24.62ms
{ expected_response:true }...: avg=5.94ms   min=3.5ms   med=3.89ms  max=28.11ms  p(90)=6.36ms  p(95)=20.73ms
http_req_failed................: 60.00%  ✓ 30       ✗ 20
http_req_receiving.............: avg=54.8µs   min=34.49µs med=48.36µs max=108.36µs p(90)=74.38µs p(95)=84.52µs
http_req_sending...............: avg=18.75µs  min=12.2µs  med=14.41µs max=76.01µs  p(90)=29.97µs p(95)=32.57µs
http_req_tls_handshaking.......: avg=590.01µs min=0s      med=0s      max=29.5ms   p(90)=0s      p(95)=0s     
http_req_waiting...............: avg=11.1ms   min=3.45ms  med=4.81ms  max=207.97ms p(90)=10.59ms p(95)=24.54ms
http_reqs......................: 50      4.709447/s
iteration_duration.............: avg=1.06s    min=1.02s   med=1.02s   max=1.34s    p(90)=1.06s   p(95)=1.2s   
iterations.....................: 10      0.941889/s
vus............................: 1       min=1      max=1
vus_max........................: 1       min=1      max=1
    