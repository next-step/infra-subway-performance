```javascript

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 3m10s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 2m40s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m41.9s), 000/240 VUs, 14861 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  2m40s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 29722      ✗ 0
     data_received..................: 20 MB   124 kB/s
     data_sent......................: 1.4 MB  8.8 kB/s
     http_req_blocked...............: avg=264.77µs min=0s     med=0s     max=698.83ms p(90)=1µs     p(95)=1µs
     http_req_connecting............: avg=155.12µs min=0s     med=0s     max=684.79ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=9.85ms   min=3.94ms med=7.15ms max=665.22ms p(90)=11.27ms p(95)=13.78ms
       { expected_response:true }...: avg=9.85ms   min=3.94ms med=7.15ms max=665.22ms p(90)=11.27ms p(95)=13.78ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 29722
     http_req_receiving.............: avg=30.52µs  min=10µs   med=26µs   max=2.74ms   p(90)=46µs    p(95)=56µs
     http_req_sending...............: avg=53.86µs  min=19µs   med=47µs   max=983µs    p(90)=85.9µs  p(95)=98µs
     http_req_tls_handshaking.......: avg=107.8µs  min=0s     med=0s     max=142.66ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=9.77ms   min=3.88ms med=7.07ms max=665.09ms p(90)=11.17ms p(95)=13.7ms
     http_reqs......................: 29722   183.560883/s
     iteration_duration.............: avg=2.02s    min=2s     med=2.01s  max=2.71s    p(90)=2.02s   p(95)=2.03s
     iterations.....................: 14861   91.780442/s
     vus............................: 46      min=10       max=240
     vus_max........................: 240     min=240      max=240
```
