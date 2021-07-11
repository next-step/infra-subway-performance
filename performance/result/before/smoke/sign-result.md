```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: sign-smoke-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.1s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ sign in successfully

     checks.........................: 100.00% ✓ 10     ✗ 0
     data_received..................: 6.7 kB  657 B/s
     data_sent......................: 2.8 kB  277 B/s
     http_req_blocked...............: avg=4.78ms   min=4.34µs  med=4.91µs  max=47.77ms p(90)=4.79ms   p(95)=26.28ms
     http_req_connecting............: avg=116.48µs min=0s      med=0s      max=1.16ms  p(90)=116.48µs p(95)=640.65µs
   ✓ http_req_duration..............: avg=8.55ms   min=7.72ms  med=8.47ms  max=9.42ms  p(90)=9.09ms   p(95)=9.26ms
       { expected_response:true }...: avg=8.55ms   min=7.72ms  med=8.47ms  max=9.42ms  p(90)=9.09ms   p(95)=9.26ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 10
     http_req_receiving.............: avg=61.03µs  min=49.5µs  med=57.81µs max=86.24µs p(90)=72.21µs  p(95)=79.22µs
     http_req_sending...............: avg=40.3µs   min=24.25µs med=32.96µs max=109.1µs p(90)=46.75µs  p(95)=77.93µs
     http_req_tls_handshaking.......: avg=4.6ms    min=0s      med=0s      max=46.03ms p(90)=4.6ms    p(95)=25.31ms
     http_req_waiting...............: avg=8.45ms   min=7.63ms  med=8.36ms  max=9.34ms  p(90)=9ms      p(95)=9.17ms
     http_reqs......................: 10      0.9858/s
     iteration_duration.............: avg=1.01s    min=1s      med=1s      max=1.05s   p(90)=1.01s    p(95)=1.03s
     iterations.....................: 10      0.9858/s
     vus............................: 1       min=1    max=1
     vus_max........................: 1       min=1    max=1

```
