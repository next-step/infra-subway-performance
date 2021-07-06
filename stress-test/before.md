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
    
    
    running (11.3s), 0/1 VUs, 6 complete and 0 interrupted iterations
    default ✓ [======================================] 1 VUs  10s
    
         ✓ logged in successfully
         ✓ retrieved member
         ✓ updated info
         ✓ path stations check
    
         checks.........................: 100.00% ✓ 24       ✗ 0  
         data_received..................: 17 kB   1.5 kB/s
         data_sent......................: 5.5 kB  489 B/s
         http_req_blocked...............: avg=7.41ms   min=2µs      med=4.5µs    max=177.84ms p(90)=9µs      p(95)=9.84µs  
         http_req_connecting............: avg=188.95µs min=0s       med=0s       max=4.53ms   p(90)=0s       p(95)=0s      
       ✓ http_req_duration..............: avg=213.63ms min=11.61ms  med=16.25ms  max=1.23s    p(90)=757.37ms p(95)=838.98ms
           { expected_response:true }...: avg=750.71ms min=416.33ms med=715.36ms max=1.23s    p(90)=1.03s    p(95)=1.13s   
         http_req_failed................: 75.00%  ✓ 18       ✗ 6  
         http_req_receiving.............: avg=93.45µs  min=47µs     med=100.5µs  max=162µs    p(90)=127.4µs  p(95)=133.1µs 
         http_req_sending...............: avg=49.45µs  min=15µs     med=28.99µs  max=415µs    p(90)=53µs     p(95)=61.49µs 
         http_req_tls_handshaking.......: avg=6.97ms   min=0s       med=0s       max=167.4ms  p(90)=0s       p(95)=0s      
         http_req_waiting...............: avg=213.49ms min=11.52ms  med=16.12ms  max=1.23s    p(90)=757.23ms p(95)=838.83ms
         http_reqs......................: 24      2.119095/s
         iteration_duration.............: avg=1.88s    min=1.46s    med=1.76s    max=2.8s     p(90)=2.34s    p(95)=2.57s   
         iterations.....................: 6       0.529774/s
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
    
      scenarios: (100.00%) 1 scenario, 92 max VUs, 7m40s max duration (incl. graceful stop):
               * default: Up to 92 looping VUs for 7m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (7m39.9s), 00/92 VUs, 670 complete and 48 interrupted iterations
    default ✓ [======================================] 00/92 VUs  7m10s
    
         ✓ logged in successfully
         ✓ retrieved member
         ✓ updated info
         ✓ path stations check
    
         checks.........................: 100.00% ✓ 2809     ✗ 0   
         data_received..................: 3.2 MB  7.0 kB/s
         data_sent......................: 651 kB  1.4 kB/s
         http_req_blocked...............: avg=596.52µs min=1µs      med=5µs    max=174.8ms  p(90)=9µs    p(95)=11µs  
         http_req_connecting............: avg=150.99µs min=0s       med=0s     max=5.58ms   p(90)=0s     p(95)=0s    
       ✗ http_req_duration..............: avg=9.33s    min=8.14ms   med=9.47s  max=30.42s   p(90)=17.94s p(95)=20.26s
           { expected_response:true }...: avg=14.29s   min=826.15ms med=14.74s max=30.42s   p(90)=20.93s p(95)=22.16s
         http_req_failed................: 76.14%  ✓ 2139     ✗ 670 
         http_req_receiving.............: avg=94.42µs  min=22µs     med=88µs   max=1.54ms   p(90)=143µs  p(95)=168µs 
         http_req_sending...............: avg=36.69µs  min=9µs      med=33µs   max=1.96ms   p(90)=53µs   p(95)=66µs  
         http_req_tls_handshaking.......: avg=428.98µs min=0s       med=0s     max=151.73ms p(90)=0s     p(95)=0s    
         http_req_waiting...............: avg=9.33s    min=8.07ms   med=9.47s  max=30.42s   p(90)=17.94s p(95)=20.26s
         http_reqs......................: 2809    6.107996/s
         iteration_duration.............: avg=37.66s   min=1.88s    med=38.24s max=1m24s    p(90)=1m2s   p(95)=1m3s  
         iterations.....................: 670     1.456873/s
         vus............................: 3       min=1      max=92
         vus_max........................: 92      min=92     max=92
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
    
      scenarios: (100.00%) 1 scenario, 230 max VUs, 15m30s max duration (incl. graceful stop):
               * default: Up to 230 looping VUs for 15m0s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (15m19.4s), 000/230 VUs, 1467 complete and 149 interrupted iterations
    default ✓ [======================================] 000/230 VUs  15m0s
    
         ✓ logged in successfully
         ✓ retrieved member
         ✓ updated info
         ✗ path stations check
          ↳  88% — ✓ 1302 / ✗ 169
    
         checks.........................: 97.27% ✓ 6037     ✗ 169  
         data_received..................: 6.8 MB 7.4 kB/s
         data_sent......................: 1.5 MB 1.6 kB/s
         http_req_blocked...............: avg=616.4µs  min=1µs      med=4µs    max=156.35ms p(90)=8µs    p(95)=11µs   
         http_req_connecting............: avg=168.21µs min=0s       med=0s     max=5.56ms   p(90)=0s     p(95)=0s     
       ✗ http_req_duration..............: avg=21.27s   min=7.59ms   med=23.17s max=42.93s   p(90)=33.37s p(95)=35.27s 
           { expected_response:true }...: avg=26.28s   min=972.73ms med=28.09s max=42.93s   p(90)=37.84s p(95)=39.41s 
         http_req_failed................: 79.02% ✓ 4904     ✗ 1302 
         http_req_receiving.............: avg=84.87µs  min=20µs     med=76µs   max=890µs    p(90)=135µs  p(95)=160µs  
         http_req_sending...............: avg=31.98µs  min=7µs      med=27µs   max=445µs    p(90)=51µs   p(95)=64.75µs
         http_req_tls_handshaking.......: avg=438.46µs min=0s       med=0s     max=145.99ms p(90)=0s     p(95)=0s     
         http_req_waiting...............: avg=21.27s   min=7.47ms   med=23.17s max=42.93s   p(90)=33.37s p(95)=35.27s 
         http_reqs......................: 6206   6.750119/s
         iteration_duration.............: avg=1m25s    min=2.19s    med=1m27s  max=2m23s    p(90)=2m3s   p(95)=2m11s  
         iterations.....................: 1467   1.595621/s
         vus............................: 3      min=2      max=230
         vus_max........................: 230    min=230    max=230
```
