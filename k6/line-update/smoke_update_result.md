```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke-update.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.3s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ line update

     checks.........................: 100.00% ✓ 10       ✗ 0  
     data_received..................: 970 B   95 B/s
     data_sent......................: 2.2 kB  216 B/s
     http_req_blocked...............: avg=1.98ms  min=4µs     med=5.5µs   max=19.8ms  p(90)=1.98ms   p(95)=10.89ms 
     http_req_connecting............: avg=519.1µs min=0s      med=0s      max=5.19ms  p(90)=519.09µs p(95)=2.85ms  
   ✓ http_req_duration..............: avg=23.16ms min=11.67ms med=19.88ms max=42.95ms p(90)=42.38ms  p(95)=42.67ms 
       { expected_response:true }...: avg=23.16ms min=11.67ms med=19.88ms max=42.95ms p(90)=42.38ms  p(95)=42.67ms 
     http_req_failed................: 0.00%   ✓ 0        ✗ 10 
     http_req_receiving.............: avg=68.8µs  min=34µs    med=64µs    max=152µs   p(90)=88.99µs  p(95)=120.49µs
     http_req_sending...............: avg=57.8µs  min=23µs    med=34µs    max=268µs   p(90)=75.39µs  p(95)=171.69µs
     http_req_tls_handshaking.......: avg=0s      min=0s      med=0s      max=0s      p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=23.03ms min=11.57ms med=19.78ms max=42.53ms p(90)=42.22ms  p(95)=42.38ms 
     http_reqs......................: 10      0.974296/s
     iteration_duration.............: avg=1.02s   min=1.01s   med=1.02s   max=1.06s   p(90)=1.04s    p(95)=1.05s   
     iterations.....................: 10      0.974296/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```