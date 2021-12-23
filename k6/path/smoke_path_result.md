```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke-path.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.7s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ paths

     checks.........................: 100.00% ✓ 10      ✗ 0  
     data_received..................: 4.1 kB  380 B/s
     data_sent......................: 1.5 kB  135 B/s
     http_req_blocked...............: avg=1.78ms  min=4µs     med=7µs     max=17.81ms  p(90)=1.78ms   p(95)=9.8ms   
     http_req_connecting............: avg=424.2µs min=0s      med=0s      max=4.24ms   p(90)=424.19µs p(95)=2.33ms  
   ✓ http_req_duration..............: avg=68.68ms min=36.64ms med=60.64ms max=180.92ms p(90)=85.62ms  p(95)=133.27ms
       { expected_response:true }...: avg=68.68ms min=36.64ms med=60.64ms max=180.92ms p(90)=85.62ms  p(95)=133.27ms
     http_req_failed................: 0.00%   ✓ 0       ✗ 10 
     http_req_receiving.............: avg=96.4µs  min=43µs    med=79.5µs  max=206µs    p(90)=152.89µs p(95)=179.44µs
     http_req_sending...............: avg=33.29µs min=15µs    med=32µs    max=82µs     p(90)=41.49µs  p(95)=61.74µs 
     http_req_tls_handshaking.......: avg=0s      min=0s      med=0s      max=0s       p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=68.55ms min=36.56ms med=60.46ms max=180.85ms p(90)=85.39ms  p(95)=133.12ms
     http_reqs......................: 10      0.93309/s
     iteration_duration.............: avg=1.07s   min=1.03s   med=1.06s   max=1.18s    p(90)=1.08s    p(95)=1.13s   
     iterations.....................: 10      0.93309/s
     vus............................: 1       min=1     max=1
     vus_max........................: 1       min=1     max=1

```