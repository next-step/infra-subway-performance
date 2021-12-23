```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress-update.js
     output: -

  scenarios: (100.00%) 1 scenario, 1200 max VUs, 2m10s max duration (incl. graceful stop):
           * default: Up to 1200 looping VUs for 1m40s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m40.9s), 0000/1200 VUs, 52699 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1200 VUs  1m40s

     ✓ line update

     checks.........................: 100.00% ✓ 52699      ✗ 0     
     data_received..................: 5.1 MB  51 kB/s
     data_sent......................: 12 MB   116 kB/s
     http_req_blocked...............: avg=123.93µs min=1µs    med=3µs     max=14.39ms  p(90)=6µs     p(95)=7µs    
     http_req_connecting............: avg=118.5µs  min=0s     med=0s      max=12.57ms  p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=11.74ms  min=7.08ms med=11.5ms  max=148.09ms p(90)=14.08ms p(95)=15.42ms
       { expected_response:true }...: avg=11.74ms  min=7.08ms med=11.5ms  max=148.09ms p(90)=14.08ms p(95)=15.42ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 52699 
     http_req_receiving.............: avg=27.92µs  min=8µs    med=23µs    max=753µs    p(90)=48µs    p(95)=60µs   
     http_req_sending...............: avg=19.95µs  min=6µs    med=16µs    max=348µs    p(90)=35µs    p(95)=43µs   
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s      max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=11.69ms  min=7.05ms med=11.45ms max=148.06ms p(90)=14.02ms p(95)=15.36ms
     http_reqs......................: 52699   522.129176/s
     iteration_duration.............: avg=1.01s    min=1s     med=1.01s   max=1.14s    p(90)=1.01s   p(95)=1.01s  
     iterations.....................: 52699   522.129176/s
     vus............................: 121     min=5        max=1200
     vus_max........................: 1200    min=1200     max=1200

```