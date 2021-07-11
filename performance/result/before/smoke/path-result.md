```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: path-smoke-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ path in successfully
     ✓ correct distance

     checks.........................: 100.00% ✓ 20       ✗ 0
     data_received..................: 22 kB   2.2 kB/s
     data_sent......................: 2.1 kB  208 B/s
     http_req_blocked...............: avg=3.24ms   min=3.68µs  med=4.36µs  max=32.43ms  p(90)=3.24ms   p(95)=17.84ms
     http_req_connecting............: avg=122.3µs  min=0s      med=0s      max=1.22ms   p(90)=122.3µs  p(95)=672.65µs
   ✓ http_req_duration..............: avg=17.05ms  min=12.82ms med=16.19ms max=23.72ms  p(90)=22.7ms   p(95)=23.21ms
       { expected_response:true }...: avg=17.05ms  min=12.82ms med=16.19ms max=23.72ms  p(90)=22.7ms   p(95)=23.21ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=102.57µs min=64.91µs med=80.19µs max=275.12µs p(90)=122.23µs p(95)=198.68µs
     http_req_sending...............: avg=30.45µs  min=25.99µs med=28.16µs max=53.97µs  p(90)=31.7µs   p(95)=42.83µs
     http_req_tls_handshaking.......: avg=2.32ms   min=0s      med=0s      max=23.25ms  p(90)=2.32ms   p(95)=12.78ms
     http_req_waiting...............: avg=16.92ms  min=12.72ms med=16.03ms max=23.61ms  p(90)=22.57ms  p(95)=23.09ms
     http_reqs......................: 10      0.979123/s
     iteration_duration.............: avg=1.02s    min=1.01s   med=1.01s   max=1.04s    p(90)=1.02s    p(95)=1.03s
     iterations.....................: 10      0.979123/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
