```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: path-load-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 60 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 60 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.9s), 00/60 VUs, 9221 complete and 0 interrupted iterations
default ✓ [======================================] 00/60 VUs  3m10s

     ✓ path in successfully
     ✓ correct distance

     checks.........................: 100.00% ✓ 18442     ✗ 0
     data_received..................: 17 MB   87 kB/s
     data_sent......................: 1.6 MB  8.5 kB/s
     http_req_blocked...............: avg=49.53µs min=1.61µs  med=4.12µs  max=37.79ms p(90)=6.01µs  p(95)=6.51µs
     http_req_connecting............: avg=8.03µs  min=0s      med=0s      max=7.4ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=11.38ms min=4.49ms  med=8.82ms  max=87.45ms p(90)=19.88ms p(95)=26.14ms
       { expected_response:true }...: avg=11.38ms min=4.49ms  med=8.82ms  max=87.45ms p(90)=19.88ms p(95)=26.14ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 9221
     http_req_receiving.............: avg=67.12µs min=18.68µs med=61.99µs max=3.11ms  p(90)=84.64µs p(95)=99.67µs
     http_req_sending...............: avg=25.33µs min=6.43µs  med=21.7µs  max=3.39ms  p(90)=29.95µs p(95)=36.51µs
     http_req_tls_handshaking.......: avg=35.07µs min=0s      med=0s      max=25.27ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=11.29ms min=4.34ms  med=8.73ms  max=87.39ms p(90)=19.79ms p(95)=26.05ms
     http_reqs......................: 9221    48.307942/s
     iteration_duration.............: avg=1.01s   min=1s      med=1s      max=1.08s   p(90)=1.02s   p(95)=1.02s
     iterations.....................: 9221    48.307942/s
     vus............................: 4       min=1       max=60
     vus_max........................: 60      min=60      max=60

```
