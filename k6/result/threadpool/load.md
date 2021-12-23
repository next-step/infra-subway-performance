```javascript

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 150 max VUs, 1m30s max duration (incl. graceful stop):
           * default: Up to 150 looping VUs for 1m0s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m01.4s), 000/150 VUs, 2780 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m0s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 5560      ✗ 0
     data_received..................: 4.2 MB  69 kB/s
     data_sent......................: 326 kB  5.3 kB/s
     http_req_blocked...............: avg=748.88µs min=0s     med=1µs    max=568.19ms p(90)=1µs     p(95)=1µs
     http_req_connecting............: avg=382.04µs min=0s     med=0s     max=553.39ms p(90)=0s      p(95)=0s
   _✓ http_req_duration..............: avg=10.98ms  min=4.08ms med=6.65ms max=554.1ms  p(90)=10.78ms p(95)=14.52ms_
       { expected_response:true }...: avg=10.98ms  min=4.08ms med=6.65ms max=554.1ms  p(90)=10.78ms p(95)=14.52ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 5560
     http_req_receiving.............: avg=33.9µs   min=11µs   med=30µs   max=461µs    p(90)=53µs    p(95)=63.05µs
     http_req_sending...............: avg=58.82µs  min=20µs   med=51µs   max=1.89ms   p(90)=90µs    p(95)=112µs
     http_req_tls_handshaking.......: avg=361.87µs min=0s     med=0s     max=137.34ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=10.89ms  min=4.03ms med=6.55ms max=553.98ms p(90)=10.71ms p(95)=14.45ms
     http_reqs......................: 5560    90.594668/s
     iteration_duration.............: avg=2.02s    min=2s     med=2.01s  max=2.6s     p(90)=2.03s   p(95)=2.03s
     iterations.....................: 2780    45.297334/s
     vus............................: 29      min=5       max=149
     vus_max........................: 150     min=150     max=150

```
