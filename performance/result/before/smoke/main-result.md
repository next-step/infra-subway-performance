```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: main-smoke-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.1s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ main in successfully

     checks.........................: 100.00% ✓ 10      ✗ 0
     data_received..................: 17 kB   1.7 kB/s
     data_sent......................: 1.9 kB  184 B/s
     http_req_blocked...............: avg=5.98ms   min=4.04µs  med=4.9µs   max=59.79ms  p(90)=5.98ms   p(95)=32.88ms
     http_req_connecting............: avg=121.66µs min=0s      med=0s      max=1.21ms   p(90)=121.66µs p(95)=669.18µs
   ✓ http_req_duration..............: avg=5.16ms   min=4.29ms  med=4.57ms  max=10.51ms  p(90)=5.56ms   p(95)=8.04ms
       { expected_response:true }...: avg=5.16ms   min=4.29ms  med=4.57ms  max=10.51ms  p(90)=5.56ms   p(95)=8.04ms
     http_req_failed................: 0.00%   ✓ 0       ✗ 10
     http_req_receiving.............: avg=90.51µs  min=56.17µs med=78.91µs max=193.21µs p(90)=109.24µs p(95)=151.22µs
     http_req_sending...............: avg=33.22µs  min=26.52µs med=28.34µs max=66.9µs   p(90)=42.12µs  p(95)=54.51µs
     http_req_tls_handshaking.......: avg=4.31ms   min=0s      med=0s      max=43.14ms  p(90)=4.31ms   p(95)=23.72ms
     http_req_waiting...............: avg=5.03ms   min=4.15ms  med=4.46ms  max=10.4ms   p(90)=5.45ms   p(95)=7.92ms
     http_reqs......................: 10      0.98809/s
     iteration_duration.............: avg=1.01s    min=1s      med=1s      max=1.06s    p(90)=1.01s    p(95)=1.04s
     iterations.....................: 10      0.98809/s
     vus............................: 1       min=1     max=1
     vus_max........................: 1       min=1     max=1

```
