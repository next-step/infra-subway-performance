# 디비 이중화 작업 후 stress test
### stress-test/stress.js
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
    
    
    running (12m10.9s), 000/140 VUs, 58730 complete and 0 interrupted iterations
    default ✓ [======================================] 000/140 VUs  12m10s
    
         ✓ logged in successfully
         ✓ correct distance
    
         checks.........................: 100.00% ✓ 117460    ✗ 0    
         data_received..................: 196 MB  268 kB/s
         data_sent......................: 5.1 MB  7.0 kB/s
         http_req_blocked...............: avg=43.26µs  min=0s     med=1µs     max=158.05ms p(90)=1µs     p(95)=2µs    
         http_req_connecting............: avg=10.97µs  min=0s     med=0s      max=17.06ms  p(90)=0s      p(95)=0s     
       ✓ http_req_duration..............: avg=11.19ms  min=6.58ms med=10.51ms max=450.92ms p(90)=14.42ms p(95)=16.2ms 
           { expected_response:true }...: avg=11.19ms  min=6.58ms med=10.51ms max=450.92ms p(90)=14.42ms p(95)=16.2ms 
         http_req_failed................: 0.00%   ✓ 0         ✗ 58730
         http_req_receiving.............: avg=116.22µs min=24µs   med=80µs    max=6.86ms   p(90)=226µs   p(95)=342µs  
         http_req_sending...............: avg=76.83µs  min=15µs   med=72µs    max=1.52ms   p(90)=120µs   p(95)=136µs  
         http_req_tls_handshaking.......: avg=30.55µs  min=0s     med=0s      max=146.24ms p(90)=0s      p(95)=0s     
         http_req_waiting...............: avg=10.99ms  min=6.5ms  med=10.31ms max=448.42ms p(90)=14.22ms p(95)=16.01ms
         http_reqs......................: 58730   80.348975/s
         iteration_duration.............: avg=1.01s    min=1s     med=1.01s   max=1.61s    p(90)=1.01s   p(95)=1.01s  
         iterations.....................: 58730   80.348975/s
         vus............................: 11      min=1       max=140
         vus_max........................: 140     min=140     max=140
```
