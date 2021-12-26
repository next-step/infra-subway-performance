# 개선 전
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load-path.js
     output: -

  scenarios: (100.00%) 1 scenario, 200 max VUs, 1m5s max duration (incl. graceful stop):
           * default: Up to 200 looping VUs for 35s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m35.8s), 000/200 VUs, 2674 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  35s

     ✓ paths

     checks.........................: 100.00% ✓ 2674      ✗ 0    
     data_received..................: 1.1 MB  30 kB/s
     data_sent......................: 388 kB  11 kB/s
     http_req_blocked...............: avg=667.52µs min=1µs     med=6µs      max=267.86ms p(90)=12µs     p(95)=5.34ms  
     http_req_connecting............: avg=652.93µs min=0s      med=0s       max=267.75ms p(90)=0s       p(95)=5.21ms  
   ✓ http_req_duration..............: avg=263.55ms min=34.35ms med=201.49ms max=1.5s     p(90)=690.58ms p(95)=849.62ms
       { expected_response:true }...: avg=263.55ms min=34.35ms med=201.49ms max=1.5s     p(90)=690.58ms p(95)=849.62ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 2674 
     http_req_receiving.............: avg=1.48ms   min=19µs    med=90µs     max=86.86ms  p(90)=3.27ms   p(95)=7.62ms  
     http_req_sending...............: avg=31.72µs  min=7µs     med=26µs     max=199µs    p(90)=50µs     p(95)=88µs    
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s       max=0s       p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=262.03ms min=34.22ms med=199.54ms max=1.5s     p(90)=686.73ms p(95)=845.03ms
     http_reqs......................: 2674    74.703542/s
     iteration_duration.............: avg=1.26s    min=1.03s   med=1.2s     max=2.5s     p(90)=1.69s    p(95)=1.84s   
     iterations.....................: 2674    74.703542/s
     vus............................: 27      min=5       max=199
     vus_max........................: 200     min=200     max=200

```

# 개선 후
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load-path.js
     output: -

  scenarios: (100.00%) 1 scenario, 200 max VUs, 1m5s max duration (incl. graceful stop):
           * default: Up to 200 looping VUs for 35s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m35.5s), 000/200 VUs, 3156 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  35s

     ✓ paths

     checks.........................: 100.00% ✓ 3156      ✗ 0    
     data_received..................: 1.3 MB  36 kB/s
     data_sent......................: 458 kB  13 kB/s
     http_req_blocked...............: avg=3.74ms   min=1µs   med=4µs     max=294.59ms p(90)=11.5µs  p(95)=37.36ms
     http_req_connecting............: avg=3.69ms   min=0s    med=0s      max=294.4ms  p(90)=0s      p(95)=37.2ms 
   ✓ http_req_duration..............: avg=57.87ms  min=75µs  med=51.84ms max=1.15s    p(90)=72.58ms p(95)=89.66ms
       { expected_response:true }...: avg=57.87ms  min=75µs  med=51.84ms max=1.15s    p(90)=72.58ms p(95)=89.66ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 3156 
     http_req_receiving.............: avg=263.47µs min=15µs  med=54µs    max=14.97ms  p(90)=118µs   p(95)=925µs  
     http_req_sending...............: avg=26.86µs  min=5µs   med=19µs    max=670µs    p(90)=45µs    p(95)=79µs   
     http_req_tls_handshaking.......: avg=0s       min=0s    med=0s      max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=57.57ms  min=0s    med=51.51ms max=1.14s    p(90)=72.2ms  p(95)=89.32ms
     http_reqs......................: 3156    88.973698/s
     iteration_duration.............: avg=1.06s    min=1.02s med=1.05s   max=2.3s     p(90)=1.08s   p(95)=1.1s   
     iterations.....................: 3156    88.973698/s
     vus............................: 24      min=5       max=199
     vus_max........................: 200     min=200     max=200

```