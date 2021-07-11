```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: main-load-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 60 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 60 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.8s), 00/60 VUs, 9299 complete and 0 interrupted iterations
default ✓ [======================================] 00/60 VUs  3m10s

     ✓ main in successfully

     checks.........................: 100.00% ✓ 9299      ✗ 0
     data_received..................: 12 MB   63 kB/s
     data_sent......................: 1.4 MB  7.3 kB/s
     http_req_blocked...............: avg=47.61µs min=1.43µs  med=4.46µs  max=39.25ms p(90)=6.22µs  p(95)=6.68µs
     http_req_connecting............: avg=7.69µs  min=0s      med=0s      max=5.3ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=3.1ms   min=1.98ms  med=2.92ms  max=18.97ms p(90)=3.77ms  p(95)=4.33ms
       { expected_response:true }...: avg=3.1ms   min=1.98ms  med=2.92ms  max=18.97ms p(90)=3.77ms  p(95)=4.33ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 9299
     http_req_receiving.............: avg=58.36µs min=14.69µs med=55.4µs  max=2.21ms  p(90)=75.33µs p(95)=84.09µs
     http_req_sending...............: avg=24.81µs min=6.25µs  med=21.87µs max=3.02ms  p(90)=30.42µs p(95)=47.83µs
     http_req_tls_handshaking.......: avg=32.97µs min=0s      med=0s      max=23.62ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3.02ms  min=1.92ms  med=2.83ms  max=18.9ms  p(90)=3.7ms   p(95)=4.23ms
     http_reqs......................: 9299    48.738408/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.04s   p(90)=1s      p(95)=1s
     iterations.....................: 9299    48.738408/s
     vus............................: 2       min=1       max=60
     vus_max........................: 60      min=60      max=60

```
