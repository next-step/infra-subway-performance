A. smoke 테스트 : stress-test/smoke.js
```shell
              /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
       /          \   |  |\  \ |  (‾)  | 
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
         script: ./smoke.js
         output: -
    
      scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
               * default: 1 looping VUs for 10s (gracefulStop: 30s)
    
    
    running (10.3s), 0/1 VUs, 10 complete and 0 interrupted iterations
    default ✓ [======================================] 1 VUs  10s
    
         ✓ logged in successfully
         ✓ correct distance
    
         checks.........................: 100.00% ✓ 20       ✗ 0  
         data_received..................: 38 kB   3.7 kB/s
         data_sent......................: 1.5 kB  142 B/s
         http_req_blocked...............: avg=13.41ms  min=0s     med=1µs     max=134.1ms  p(90)=13.41ms  p(95)=73.75ms 
         http_req_connecting............: avg=419.5µs  min=0s     med=0s      max=4.19ms   p(90)=419.49µs p(95)=2.3ms   
       ✓ http_req_duration..............: avg=10.23ms  min=9.79ms med=10.17ms max=10.81ms  p(90)=10.67ms  p(95)=10.74ms 
           { expected_response:true }...: avg=10.23ms  min=9.79ms med=10.17ms max=10.81ms  p(90)=10.67ms  p(95)=10.74ms 
         http_req_failed................: 0.00%   ✓ 0        ✗ 10 
         http_req_receiving.............: avg=145.69µs min=96µs   med=128.5µs max=237µs    p(90)=222.6µs  p(95)=229.8µs 
         http_req_sending...............: avg=108.1µs  min=59µs   med=102.5µs max=199µs    p(90)=176.5µs  p(95)=187.74µs
         http_req_tls_handshaking.......: avg=12.86ms  min=0s     med=0s      max=128.63ms p(90)=12.86ms  p(95)=70.75ms 
         http_req_waiting...............: avg=9.97ms   min=9.6ms  med=9.94ms  max=10.56ms  p(90)=10.34ms  p(95)=10.45ms 
         http_reqs......................: 10      0.974245/s
         iteration_duration.............: avg=1.02s    min=1.01s  med=1.01s   max=1.14s    p(90)=1.02s    p(95)=1.08s   
         iterations.....................: 10      0.974245/s
         vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

B. load 테스트 : stress-test/load.js
```shell
              /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
       /          \   |  |\  \ |  (‾)  | 
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
         script: ./load.js
         output: -
    
      scenarios: (100.00%) 1 scenario, 35 max VUs, 3m40s max duration (incl. graceful stop):
               * default: Up to 35 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (3m10.5s), 00/35 VUs, 5385 complete and 0 interrupted iterations
    default ✓ [======================================] 00/35 VUs  3m10s
    
         ✓ logged in successfully
         ✓ correct distance
    
         checks.........................: 100.00% ✓ 10770     ✗ 0   
         data_received..................: 18 MB   95 kB/s
         data_sent......................: 484 kB  2.5 kB/s
         http_req_blocked...............: avg=127.86µs min=0s     med=1µs    max=137.95ms p(90)=1µs     p(95)=2µs    
         http_req_connecting............: avg=29.58µs  min=0s     med=0s     max=5.4ms    p(90)=0s      p(95)=0s     
       ✓ http_req_duration..............: avg=9.76ms   min=6.96ms med=9.68ms max=35.01ms  p(90)=10.85ms p(95)=11.31ms
           { expected_response:true }...: avg=9.76ms   min=6.96ms med=9.68ms max=35.01ms  p(90)=10.85ms p(95)=11.31ms
         http_req_failed................: 0.00%   ✓ 0         ✗ 5385
         http_req_receiving.............: avg=122.8µs  min=28µs   med=100µs  max=1.32ms   p(90)=212µs   p(95)=266µs  
         http_req_sending...............: avg=80.55µs  min=19µs   med=75µs   max=1.29ms   p(90)=121µs   p(95)=135.8µs
         http_req_tls_handshaking.......: avg=95.59µs  min=0s     med=0s     max=131.23ms p(90)=0s      p(95)=0s     
         http_req_waiting...............: avg=9.55ms   min=6.83ms med=9.47ms max=34.79ms  p(90)=10.63ms p(95)=11.09ms
         http_reqs......................: 5385    28.269486/s
         iteration_duration.............: avg=1.01s    min=1s     med=1.01s  max=1.15s    p(90)=1.01s   p(95)=1.01s  
         iterations.....................: 5385    28.269486/s
         vus............................: 1       min=1       max=35
         vus_max........................: 35      min=35      max=35
```

C. stress 테스트 : stress-test/stress.js
```shell
              /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
       /          \   |  |\  \ |  (‾)  | 
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
         script: ./stress.js
         output: -
    
      scenarios: (100.00%) 1 scenario, 140 max VUs, 12m40s max duration (incl. graceful stop):
               * default: Up to 140 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (12m10.4s), 000/140 VUs, 58851 complete and 0 interrupted iterations
    default ✓ [======================================] 000/140 VUs  12m10s
    
         ✓ logged in successfully
         ✓ correct distance
    
         checks.........................: 100.00% ✓ 117702    ✗ 0    
         data_received..................: 196 MB  268 kB/s
         data_sent......................: 5.1 MB  7.0 kB/s
         http_req_blocked...............: avg=40.92µs min=0s     med=1µs    max=177.64ms p(90)=1µs     p(95)=1µs    
         http_req_connecting............: avg=10.52µs min=0s     med=0s     max=6.32ms   p(90)=0s      p(95)=0s     
       ✓ http_req_duration..............: avg=9.68ms  min=6.12ms med=9.34ms max=25.67ms  p(90)=11.83ms p(95)=12.81ms
           { expected_response:true }...: avg=9.68ms  min=6.12ms med=9.34ms max=25.67ms  p(90)=11.83ms p(95)=12.81ms
         http_req_failed................: 0.00%   ✓ 0         ✗ 58851
         http_req_receiving.............: avg=86.24µs min=23µs   med=60µs   max=7.17ms   p(90)=158µs   p(95)=246µs  
         http_req_sending...............: avg=59.81µs min=15µs   med=53µs   max=2.18ms   p(90)=104µs   p(95)=120µs  
         http_req_tls_handshaking.......: avg=29.01µs min=0s     med=0s     max=160.94ms p(90)=0s      p(95)=0s     
         http_req_waiting...............: avg=9.54ms  min=6.05ms med=9.19ms max=25.52ms  p(90)=11.68ms p(95)=12.66ms
         http_reqs......................: 58851   80.575133/s
         iteration_duration.............: avg=1.01s   min=1s     med=1.01s  max=1.19s    p(90)=1.01s   p(95)=1.01s  
         iterations.....................: 58851   80.575133/s
         vus............................: 9       min=1       max=140
         vus_max........................: 140     min=140     max=140
```
