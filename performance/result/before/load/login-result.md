```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: login-load-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 60 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 60 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.7s), 00/60 VUs, 9250 complete and 0 interrupted iterations
default ✓ [======================================] 00/60 VUs  3m10s

     ✓ logged in successfully
     ✓ response ok

     checks.........................: 100.00% ✓ 18500     ✗ 0
     data_received..................: 16 MB   82 kB/s
     data_sent......................: 4.7 MB  25 kB/s
     http_req_blocked...............: avg=25.45µs min=1µs     med=2.52µs  max=24.9ms  p(90)=4.59µs  p(95)=5.77µs
     http_req_connecting............: avg=4.05µs  min=0s      med=0s      max=10.57ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=4.22ms  min=1.95ms  med=3.44ms  max=64.66ms p(90)=6.43ms  p(95)=8.21ms
       { expected_response:true }...: avg=4.22ms  min=1.95ms  med=3.44ms  max=64.66ms p(90)=6.43ms  p(95)=8.21ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 18500
     http_req_receiving.............: avg=51.16µs min=12.01µs med=43µs    max=4.22ms  p(90)=64.1µs  p(95)=80.5µs
     http_req_sending...............: avg=22.51µs min=5.6µs   med=16.18µs max=5.91ms  p(90)=28.68µs p(95)=35.66µs
     http_req_tls_handshaking.......: avg=17.34µs min=0s      med=0s      max=23.14ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=4.14ms  min=1.91ms  med=3.37ms  max=64.56ms p(90)=6.35ms  p(95)=8.11ms
     http_reqs......................: 18500   97.021007/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.08s   p(90)=1.01s   p(95)=1.01s
     iterations.....................: 9250    48.510504/s
     vus............................: 4       min=1       max=60
     vus_max........................: 60      min=60      max=60

```
