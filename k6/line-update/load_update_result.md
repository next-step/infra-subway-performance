```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load-update.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 1m5s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 35s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m35.8s), 000/100 VUs, 2766 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  35s

     ✓ line update

     checks.........................: 100.00% ✓ 2766      ✗ 0    
     data_received..................: 268 kB  7.5 kB/s
     data_sent......................: 614 kB  17 kB/s
     http_req_blocked...............: avg=210.8µs  min=1µs    med=6µs     max=13.57ms  p(90)=8µs     p(95)=17.75µs
     http_req_connecting............: avg=198.23µs min=0s     med=0s      max=6.95ms   p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=12.88ms  min=8.16ms med=12.39ms max=215.63ms p(90)=14.61ms p(95)=15.56ms
       { expected_response:true }...: avg=12.88ms  min=8.16ms med=12.39ms max=215.63ms p(90)=14.61ms p(95)=15.56ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 2766 
     http_req_receiving.............: avg=52.18µs  min=12µs   med=49µs    max=668µs    p(90)=72µs    p(95)=79µs   
     http_req_sending...............: avg=35.64µs  min=7µs    med=34µs    max=223µs    p(90)=48µs    p(95)=66µs   
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s      max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=12.79ms  min=8.11ms med=12.3ms  max=215.6ms  p(90)=14.5ms  p(95)=15.49ms
     http_reqs......................: 2766    77.323279/s
     iteration_duration.............: avg=1.01s    min=1s     med=1.01s   max=1.21s    p(90)=1.01s   p(95)=1.01s  
     iterations.....................: 2766    77.323279/s
     vus............................: 10      min=10      max=100
     vus_max........................: 100     min=100     max=100

```