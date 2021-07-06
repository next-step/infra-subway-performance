# smoke

### before


            /\      |‾‾| /‾‾/   /‾‾/   
       /\  /  \     |  |/  /   /  /    
      /  \/    \    |     (   /   ‾‾\  
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
    script: MultipleData_smoke.js
    output: -
    
    scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
    * default: 1 looping VUs for 10s (gracefulStop: 30s)
    
    
    running (10.7s), 0/1 VUs, 8 complete and 0 interrupted iterations
    default ✓ [======================================] 1 VUs  10s

     ✓ find path in successfully
     ✓ distance

     checks.........................: 100.00% ✓ 16  ✗ 0  
     data_received..................: 35 kB   3.3 kB/s
     data_sent......................: 1.3 kB  120 B/s
     http_req_blocked...............: avg=6.57ms   min=2.76µs   med=2.83µs   max=52.59ms  p(90)=15.78ms  p(95)=34.19ms 
     http_req_connecting............: avg=65.08µs  min=0s       med=0s       max=520.71µs p(90)=156.21µs p(95)=338.46µs
    ✓ http_req_duration..............: avg=324.13ms min=141.02ms med=192.52ms max=1.22s    p(90)=586.35ms p(95)=906.15ms
    { expected_response:true }...: avg=324.13ms min=141.02ms med=192.52ms max=1.22s    p(90)=586.35ms p(95)=906.15ms
    http_req_failed................: 0.00%   ✓ 0   ✗ 8  
    http_req_receiving.............: avg=129.29µs min=94.26µs  med=117.62µs max=166.89µs p(90)=159.56µs p(95)=163.22µs
    http_req_sending...............: avg=92.37µs  min=73.82µs  med=82.3µs   max=164.46µs p(90)=111.66µs p(95)=138.06µs
    http_req_tls_handshaking.......: avg=4.16ms   min=0s       med=0s       max=33.34ms  p(90)=10ms     p(95)=21.67ms
    http_req_waiting...............: avg=323.9ms  min=140.82ms med=192.31ms max=1.22s    p(90)=586.08ms p(95)=905.85ms
    http_reqs......................: 8       0.750738/s
    iteration_duration.............: avg=1.33s    min=1.14s    med=1.19s    max=2.28s    p(90)=1.6s     p(95)=1.94s   
    iterations.....................: 8       0.750738/s
    vus............................: 1       min=1 max=1
    vus_max........................: 1       min=1 max=1



--- 
### after

            /\      |‾‾| /‾‾/   /‾‾/   
       /\  /  \     |  |/  /   /  /    
      /  \/    \    |     (   /   ‾‾\  
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
    script: MultipleData_smoke.js
    output: -
    
    scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
    * default: 1 looping VUs for 10s (gracefulStop: 30s)
    
    
    running (10.3s), 0/1 VUs, 10 complete and 0 interrupted iterations
    default ✓ [======================================] 1 VUs  10s

     ✓ find path in successfully
     ✓ distance

     checks.........................: 100.00% ✓ 20  ✗ 0  
     data_received..................: 42 kB   4.1 kB/s
     data_sent......................: 1.5 kB  140 B/s
     http_req_blocked...............: avg=3.32ms   min=2.81µs  med=2.91µs  max=33.25ms  p(90)=3.32ms   p(95)=18.29ms
     http_req_connecting............: avg=62.72µs  min=0s      med=0s      max=627.2µs  p(90)=62.72µs  p(95)=344.96µ
    ✓ http_req_duration..............: avg=24.77ms  min=3.71ms  med=3.97ms  max=209.35ms p(90)=26.41ms  p(95)=117.88m
    { expected_response:true }...: avg=24.77ms  min=3.71ms  med=3.97ms  max=209.35ms p(90)=26.41ms  p(95)=117.88m
    http_req_failed................: 0.00%   ✓ 0   ✗ 10
    http_req_receiving.............: avg=102.73µs min=76.85µs med=94.65µs max=144.66µs p(90)=139.66µs p(95)=142.16µ
    http_req_sending...............: avg=94.43µs  min=73.19µs med=77.56µs max=226.22µs p(90)=106.06µs p(95)=166.14µ
    http_req_tls_handshaking.......: avg=2.72ms   min=0s      med=0s      max=27.29ms  p(90)=2.72ms   p(95)=15.01ms
    http_req_waiting...............: avg=24.58ms  min=3.52ms  med=3.8ms   max=209.02ms p(90)=26.23ms  p(95)=117.63m
    http_reqs......................: 10      0.971537/s
    iteration_duration.............: avg=1.02s    min=1s      med=1s      max=1.24s    p(90)=1.03s    p(95)=1.13s  
    iterations.....................: 10      0.971537/s
    vus............................: 1       min=1 max=1
    vus_max........................: 1       min=1 max=1
    

