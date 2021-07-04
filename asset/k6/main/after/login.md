### login (stress)

```javascript

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress2.js
     output: -

  scenarios: (100.00%) 1 scenario, 270 max VUs, 5m15s max duration (incl. graceful stop):
           * default: Up to 270 looping VUs for 4m45.03s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (4m46.1s), 000/270 VUs, 39244 complete and 0 interrupted iterations
default ✓ [======================================] 000/270 VUs  4m45.03s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 78488 ✗ 0    
     data_received..................: 23 MB   82 kB/s
     data_sent......................: 15 MB   53 kB/s
     http_req_blocked...............: avg=71.85µs min=0s     med=0s      max=242.29ms p(90)=1µs     p(95)=1µs    
     http_req_connecting............: avg=20.74µs min=0s     med=0s      max=51.44ms  p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=15.92ms min=7.81ms med=12.02ms max=265.86ms p(90)=20.34ms p(95)=27.48ms
       { expected_response:true }...: avg=15.92ms min=7.81ms med=12.02ms max=265.86ms p(90)=20.34ms p(95)=27.48ms
     http_req_failed................: 0.00%   ✓ 0     ✗ 78488
     http_req_receiving.............: avg=63.71µs min=23µs   med=55µs    max=5.43ms   p(90)=98µs    p(95)=120µs  
     http_req_sending...............: avg=64.94µs min=17µs   med=53µs    max=3.41ms   p(90)=116µs   p(95)=144µs  
     http_req_tls_handshaking.......: avg=49.78µs min=0s     med=0s      max=217.84ms p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=15.79ms min=7.67ms med=11.89ms max=265.49ms p(90)=20.22ms p(95)=27.32ms
     http_reqs......................: 78488   274.382334/s
     iteration_duration.............: avg=1.03s   min=1.01s  med=1.02s   max=1.5s     p(90)=1.04s   p(95)=1.05s  
     iterations.....................: 39244   137.191167/s
     vus............................: 1       min=1   max=270
     vus_max........................: 270     min=270 max=270

```
