```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: path-stress-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 12m40s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (12m10.8s), 000/240 VUs, 33616 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ path in successfully
     ✓ correct distance

     checks.........................: 100.00% ✓ 67232    ✗ 0
     data_received..................: 58 MB   80 kB/s
     data_sent......................: 3.0 MB  4.1 kB/s
     http_req_blocked...............: avg=46.51µs min=214ns   med=383ns   max=48.71ms p(90)=657ns   p(95)=772ns
     http_req_connecting............: avg=8.64µs  min=0s      med=0s      max=6ms     p(90)=0s      p(95)=0s
   ✗ http_req_duration..............: avg=2.04s   min=19.97ms med=1.85s   max=7.9s    p(90)=4.03s   p(95)=4.06s
       { expected_response:true }...: avg=2.04s   min=19.97ms med=1.85s   max=7.9s    p(90)=4.03s   p(95)=4.06s
     http_req_failed................: 0.00%   ✓ 0        ✗ 33616
     http_req_receiving.............: avg=82.54µs min=31.96µs med=76.43µs max=3.41ms  p(90)=96.7µs  p(95)=119.91µs
     http_req_sending...............: avg=47.03µs min=15.26µs med=45.08µs max=4.79ms  p(90)=56.35µs p(95)=66.6µs
     http_req_tls_handshaking.......: avg=35.26µs min=0s      med=0s      max=25.35ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=2.04s   min=19.83ms med=1.85s   max=7.9s    p(90)=4.03s   p(95)=4.06s
     http_reqs......................: 33616   45.99832/s
     iteration_duration.............: avg=3.04s   min=1.02s   med=2.85s   max=8.9s    p(90)=5.03s   p(95)=5.06s
     iterations.....................: 33616   45.99832/s
     vus............................: 20      min=1      max=240
     vus_max........................: 240     min=240    max=240

ERRO[0732] some thresholds have failed
```
