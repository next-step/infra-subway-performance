# stress

### before


            /\      |‾‾| /‾‾/   /‾‾/   
       /\  /  \     |  |/  /   /  /    
      /  \/    \    |     (   /   ‾‾\  
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
    script: MultipleData_stress.js
    output: -
    
    scenarios: (100.00%) 1 scenario, 900 max VUs, 12m40s max duration (incl. graceful stop):
    * default: Up to 900 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (12m40.0s), 000/900 VUs, 14362 complete and 322 interrupted iterations
    default ✗ [======================================] 000/900 VUs  12m10s

     ✓ find path in successfully
     ✓ distance

     checks.........................: 100.00% ✓ 28756 ✗ 0    
     data_received..................: 61 MB   80 kB/s
     data_sent......................: 1.8 MB  2.4 kB/s
     http_req_blocked...............: avg=323.43µs min=2.09µs  med=2.91µs  max=80.27ms p(90)=3.27µs   p(95)=4.35ms  
     http_req_connecting............: avg=39.94µs  min=0s      med=0s      max=19.22ms p(90)=0s       p(95)=447.41µs
    ✗ http_req_duration..............: avg=28.55s   min=92.54ms med=25.18s  max=56.46s  p(90)=46.12s   p(95)=46.29s  
    { expected_response:true }...: avg=28.55s   min=92.54ms med=25.18s  max=56.46s  p(90)=46.12s   p(95)=46.29s  
    http_req_failed................: 0.00%   ✓ 0     ✗ 14378
    http_req_receiving.............: avg=122.73µs min=50.38µs med=106µs   max=5.21ms  p(90)=154.02µs p(95)=176.56µs
    http_req_sending...............: avg=66.39µs  min=28.12µs med=55.99µs max=6.59ms  p(90)=76.29µs  p(95)=123.33µs
    http_req_tls_handshaking.......: avg=267.63µs min=0s      med=0s      max=62.81ms p(90)=0s       p(95)=3.69ms  
    http_req_waiting...............: avg=28.55s   min=92.35ms med=25.18s  max=56.46s  p(90)=46.12s   p(95)=46.29s  
    http_reqs......................: 14378   18.918814/s
    iteration_duration.............: avg=29.53s   min=1.09s   med=26.18s  max=57.46s  p(90)=47.12s   p(95)=47.29s  
    iterations.....................: 14362   18.897761/s
    vus............................: 34      min=5   max=900
    vus_max........................: 900     min=900 max=900
    
    ERRO[0762] some thresholds have failed


--- 
### after

    
            /\      |‾‾| /‾‾/   /‾‾/   
       /\  /  \     |  |/  /   /  /    
      /  \/    \    |     (   /   ‾‾\  
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
    script: MultipleData_stress.js
    output: -
    
    scenarios: (100.00%) 1 scenario, 900 max VUs, 12m40s max duration (incl. graceful stop):
    * default: Up to 900 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (12m11.0s), 000/900 VUs, 407727 complete and 0 interrupted iterations
    default ✓ [======================================] 000/900 VUs  12m10s

     ✓ find path in successfully
     ✓ distance

     checks.........................: 100.00% ✓ 815454 ✗ 0     
     data_received..................: 1.6 GB  2.2 MB/s
     data_sent......................: 36 MB   49 kB/s
     http_req_blocked...............: avg=41.25µs  min=1.61µs  med=2.72µs  max=300.74ms p(90)=2.98µs   p(95)=7.5µs  
     http_req_connecting............: avg=5.79µs   min=0s      med=0s      max=109.83ms p(90)=0s       p(95)=0s     
    ✓ http_req_duration..............: avg=21.03ms  min=2.4ms   med=6.05ms  max=1.26s    p(90)=49.47ms  p(95)=91.38ms
    { expected_response:true }...: avg=21.03ms  min=2.4ms   med=6.05ms  max=1.26s    p(90)=49.47ms  p(95)=91.38ms
    http_req_failed................: 0.00%   ✓ 0      ✗ 407727
    http_req_receiving.............: avg=422.47µs min=30.8µs  med=63.12µs max=278.95ms p(90)=339.82µs p(95)=921.76µ
    http_req_sending...............: avg=186.52µs min=18.21µs med=38.78µs max=320.6ms  p(90)=114.98µs p(95)=362.56µ
    http_req_tls_handshaking.......: avg=15.01µs  min=0s      med=0s      max=176.87ms p(90)=0s       p(95)=0s     
    http_req_waiting...............: avg=20.42ms  min=0s      med=5.72ms  max=1.26s    p(90)=48.27ms  p(95)=89.2ms
    http_reqs......................: 407727  557.802837/s
    iteration_duration.............: avg=1.02s    min=1s      med=1s      max=2.31s    p(90)=1.06s    p(95)=1.11s  
    iterations.....................: 407727  557.802837/s
    vus............................: 39      min=6    max=900
    vus_max........................: 900     min=900  max=900 

