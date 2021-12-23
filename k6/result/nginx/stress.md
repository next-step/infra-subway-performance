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


running (2m42.1s), 000/240 VUs, 14303 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  2m40s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 28606      ✗ 0
     data_received..................: 47 MB   292 kB/s
     data_sent......................: 1.4 MB  8.4 kB/s
     http_req_blocked...............: avg=163.68µs min=0s     med=1µs     max=155.1ms  p(90)=1µs     p(95)=1µs
     http_req_connecting............: avg=48.11µs  min=0s     med=0s      max=90.65ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=49.57ms  min=3.94ms med=52.9ms  max=615.02ms p(90)=98.27ms p(95)=122.16ms
       { expected_response:true }...: avg=49.57ms  min=3.94ms med=52.9ms  max=615.02ms p(90)=98.27ms p(95)=122.16ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 28606
     http_req_receiving.............: avg=47.08µs  min=15µs   med=40µs    max=7.4ms    p(90)=68µs    p(95)=82µs
     http_req_sending...............: avg=62.1µs   min=22µs   med=56µs    max=909µs    p(90)=95µs    p(95)=116µs
     http_req_tls_handshaking.......: avg=113.81µs min=0s     med=0s      max=148.89ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=49.47ms  min=3.86ms med=52.79ms max=614.94ms p(90)=98.16ms p(95)=122.04ms
     http_reqs......................: 28606   176.469886/s
     iteration_duration.............: avg=2.1s     min=2.05s  med=2.08s   max=2.62s    p(90)=2.12s   p(95)=2.22s
     iterations.....................: 14303   88.234943/s
     vus............................: 17      min=10       max=240
     vus_max........................: 240     min=240      max=240

```
