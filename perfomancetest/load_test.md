# load

### before


            /\      |‾‾| /‾‾/   /‾‾/   
       /\  /  \     |  |/  /   /  /    
      /  \/    \    |     (   /   ‾‾\  
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
    script: MultipleData_load.js
    output: -
    
    scenarios: (100.00%) 1 scenario, 175 max VUs, 3m40s max duration (incl. graceful stop):
    * default: Up to 175 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (3m13.8s), 000/175 VUs, 3584 complete and 0 interrupted iterations
    default ✓ [======================================] 000/175 VUs  3m10s

     ✓ find path in successfully
     ✓ distance

     checks.........................: 100.00% ✓ 7168  ✗ 0    
     data_received..................: 15 MB   77 kB/s
     data_sent......................: 412 kB  2.1 kB/s
     http_req_blocked...............: avg=256.13µs min=2.26µs   med=2.92µs   max=31.37ms p(90)=3.23µs  p(95)=25.58µs
     http_req_connecting............: avg=31.02µs  min=0s       med=0s       max=3.69ms  p(90)=0s      p(95)=0s     
    ✗ http_req_duration..............: avg=6.76s    min=120.12ms med=8.04s    max=22.89s  p(90)=8.28s   p(95)=8.34s  
    { expected_response:true }...: avg=6.76s    min=120.12ms med=8.04s    max=22.89s  p(90)=8.28s   p(95)=8.34s  
    http_req_failed................: 0.00%   ✓ 0     ✗ 3584
    http_req_receiving.............: avg=137.36µs min=56.49µs  med=110.17µs max=8.62ms  p(90)=159.7µs p(95)=190.41µ
    http_req_sending...............: avg=65.49µs  min=30.53µs  med=56.18µs  max=4.57ms  p(90)=73.7µs  p(95)=114.99µ
    http_req_tls_handshaking.......: avg=213.54µs min=0s       med=0s       max=29.96ms p(90)=0s      p(95)=0s     
    http_req_waiting...............: avg=6.76s    min=119.84ms med=8.04s    max=22.89s  p(90)=8.28s   p(95)=8.34s  
    http_reqs......................: 3584    18.488592/s
    iteration_duration.............: avg=7.76s    min=1.12s    med=9.04s    max=23.89s  p(90)=9.28s   p(95)=9.34s  
    iterations.....................: 3584    18.488592/s
    vus............................: 21      min=3   max=175
    vus_max........................: 175     min=175 max=175
    
    ERRO[0195] some thresholds have failed


--- 
### after

            /\      |‾‾| /‾‾/   /‾‾/   
       /\  /  \     |  |/  /   /  /    
      /  \/    \    |     (   /   ‾‾\  
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
    script: MultipleData_load.js
    output: -
    
    scenarios: (100.00%) 1 scenario, 175 max VUs, 3m40s max duration (incl. graceful stop):
    * default: Up to 175 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (3m11.0s), 000/175 VUs, 27084 complete and 0 interrupted iterations
    default ✓ [======================================] 000/175 VUs  3m10s

     ✓ find path in successfully
     ✓ distance

     checks.........................: 100.00% ✓ 54168 ✗ 0    
     data_received..................: 107 MB  562 kB/s
     data_sent......................: 2.4 MB  13 kB/s
     http_req_blocked...............: avg=37.96µs  min=2.07µs  med=2.76µs  max=60.36ms p(90)=3.07µs   p(95)=3.55µs  
     http_req_connecting............: avg=3.63µs   min=0s      med=0s      max=6.09ms  p(90)=0s       p(95)=0s      
    ✓ http_req_duration..............: avg=4.12ms   min=2.41ms  med=3.74ms  max=38.17ms p(90)=5.73ms   p(95)=6.58ms  
    { expected_response:true }...: avg=4.12ms   min=2.41ms  med=3.74ms  max=38.17ms p(90)=5.73ms   p(95)=6.58ms  
    http_req_failed................: 0.00%   ✓ 0     ✗ 27084
    http_req_receiving.............: avg=127.79µs min=31.7µs  med=72.93µs max=14.45ms p(90)=198.51µs p(95)=371.58µs
    http_req_sending...............: avg=76.14µs  min=19.51µs med=42.48µs max=29.63ms p(90)=83.39µs  p(95)=130.83µs
    http_req_tls_handshaking.......: avg=26.27µs  min=0s      med=0s      max=30.22ms p(90)=0s       p(95)=0s      
    http_req_waiting...............: avg=3.92ms   min=0s      med=3.54ms  max=32.12ms p(90)=5.5ms    p(95)=6.31ms  
    http_reqs......................: 27084   141.814147/s
    iteration_duration.............: avg=1s       min=1s      med=1s      max=1.06s   p(90)=1s       p(95)=1s      
    iterations.....................: 27084   141.814147/s
    vus............................: 10      min=3   max=175
    vus_max........................: 175     min=175 max=175

