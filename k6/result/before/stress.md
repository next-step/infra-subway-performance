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


running (2m42.0s), 000/240 VUs, 14295 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  2m40s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 28590      ✗ 0
     data_received..................: 49 MB   305 kB/s
     data_sent......................: 3.9 MB  24 kB/s
     http_req_blocked...............: avg=245.41µs min=1µs    med=5µs     max=592.71ms p(90)=7µs     p(95)=8µs
     http_req_connecting............: avg=125.85µs min=0s     med=0s      max=579.89ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=48.62ms  min=3.78ms med=52.7ms  max=710.4ms  p(90)=97.07ms p(95)=114.43ms
       { expected_response:true }...: avg=48.62ms  min=3.78ms med=52.7ms  max=710.4ms  p(90)=97.07ms p(95)=114.43ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 28590
     http_req_receiving.............: avg=64.71µs  min=16µs   med=63µs    max=368µs    p(90)=99µs    p(95)=109µs
     http_req_sending...............: avg=23.61µs  min=5µs    med=22µs    max=488µs    p(90)=37µs    p(95)=38µs
     http_req_tls_handshaking.......: avg=113.68µs min=0s     med=0s      max=152.94ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=48.53ms  min=3.74ms med=52.62ms max=710.34ms p(90)=96.95ms p(95)=114.34ms
     http_reqs......................: 28590   176.501032/s
     iteration_duration.............: avg=2.09s    min=2.05s  med=2.08s   max=2.76s    p(90)=2.12s   p(95)=2.18s
     iterations.....................: 14295   88.250516/s
     vus............................: 35      min=10       max=240
     vus_max........................: 240     min=240      max=240

```
