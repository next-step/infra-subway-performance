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


running (1m01.5s), 000/150 VUs, 2697 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m0s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 5394      ✗ 0
     data_received..................: 4.9 MB  80 kB/s
     data_sent......................: 319 kB  5.2 kB/s
     http_req_blocked...............: avg=572.38µs min=0s     med=1µs     max=242.61ms p(90)=1µs     p(95)=1µs
     http_req_connecting............: avg=194.51µs min=0s     med=0s      max=231.55ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=44.03ms  min=4.01ms med=51.72ms max=585.92ms p(90)=86.79ms p(95)=100.95ms
       { expected_response:true }...: avg=44.03ms  min=4.01ms med=51.72ms max=585.92ms p(90)=86.79ms p(95)=100.95ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 5394
     http_req_receiving.............: avg=41.89µs  min=17µs   med=37µs    max=1.45ms   p(90)=62µs    p(95)=77µs
     http_req_sending...............: avg=62.1µs   min=22µs   med=55µs    max=1.78ms   p(90)=97µs    p(95)=121µs
     http_req_tls_handshaking.......: avg=372.99µs min=0s     med=0s      max=137.26ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=43.93ms  min=3.96ms med=51.64ms max=585.81ms p(90)=86.69ms p(95)=100.85ms
     http_reqs......................: 5394    87.641613/s
     iteration_duration.............: avg=2.09s    min=2.05s  med=2.07s   max=2.59s    p(90)=2.1s    p(95)=2.21s
     iterations.....................: 2697    43.820806/s
     vus............................: 27      min=5       max=149
     vus_max........................: 150     min=150     max=150

```
