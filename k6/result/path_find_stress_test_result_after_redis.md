```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: performance_find_path_stress_test.js
     output: -

  scenarios: (100.00%) 1 scenario, 230 max VUs, 1m0s max duration (incl. graceful stop):
           * default: Up to 230 looping VUs for 30s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m30.9s), 000/230 VUs, 3085 complete and 0 interrupted iterations
default ✓ [======================================] 000/230 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 9255       ✗ 0
     data_received..................: 5.4 MB  173 kB/s
     data_sent......................: 1.2 MB  39 kB/s
     http_req_blocked...............: avg=1.14ms   min=0s     med=0s     max=325ms    p(90)=0s       p(95)=0s
     http_req_connecting............: avg=269.72µs min=0s     med=0s     max=92.86ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=14.36ms  min=3.43ms med=8.78ms max=203.19ms p(90)=29.9ms   p(95)=43.08ms
       { expected_response:true }...: avg=13.87ms  min=3.43ms med=7.8ms  max=128.07ms p(90)=30.45ms  p(95)=44.5ms
     http_req_failed................: 66.66%  ✓ 6170       ✗ 3085
     http_req_receiving.............: avg=353.92µs min=0s     med=0s     max=150.07ms p(90)=969.32µs p(95)=976.63µs
     http_req_sending...............: avg=228.75µs min=0s     med=0s     max=132.41ms p(90)=873.54µs p(95)=976.3µs
     http_req_tls_handshaking.......: avg=849.64µs min=0s     med=0s     max=263.29ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=13.78ms  min=0s     med=8.4ms  max=203.19ms p(90)=28.56ms  p(95)=41.96ms
     http_reqs......................: 9255    299.234124/s
     iteration_duration.............: avg=1.05s    min=1.01s  med=1.04s  max=1.38s    p(90)=1.11s    p(95)=1.14s
     iterations.....................: 3085    99.744708/s
     vus............................: 22      min=8        max=229
     vus_max........................: 230     min=230      max=230

ERRO[0032] some thresholds have failed

C:\Program Files\k6>k6 run performance_find_path_stress_test.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: performance_find_path_stress_test.js
     output: -

  scenarios: (100.00%) 1 scenario, 230 max VUs, 1m0s max duration (incl. graceful stop):
           * default: Up to 230 looping VUs for 30s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m31.0s), 000/230 VUs, 3153 complete and 0 interrupted iterations
default ✓ [======================================] 000/230 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 9459       ✗ 0
     data_received..................: 5.4 MB  175 kB/s
     data_sent......................: 1.2 MB  39 kB/s
     http_req_blocked...............: avg=508.66µs min=0s      med=0s     max=289.89ms p(90)=0s       p(95)=0s
     http_req_connecting............: avg=113.58µs min=0s      med=0s     max=45.1ms   p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=9.7ms    min=3.21ms  med=7.25ms max=136.68ms p(90)=15.22ms  p(95)=21.56ms
       { expected_response:true }...: avg=9.79ms   min=3.21ms  med=6.67ms max=103.23ms p(90)=16.59ms  p(95)=24.66ms
     http_req_failed................: 66.66%  ✓ 6306       ✗ 3153
     http_req_receiving.............: avg=212.48µs min=0s      med=0s     max=44.89ms  p(90)=927.66µs p(95)=976.4µs
     http_req_sending...............: avg=141.76µs min=0s      med=0s     max=36.9ms   p(90)=530.52µs p(95)=975.4µs
     http_req_tls_handshaking.......: avg=379.72µs min=0s      med=0s     max=240.16ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=9.34ms   min=854.9µs med=6.94ms max=128.87ms p(90)=14.7ms   p(95)=20.52ms
     http_reqs......................: 9459    304.644419/s
     iteration_duration.............: avg=1.03s    min=1.01s   med=1.02s  max=1.31s    p(90)=1.06s    p(95)=1.08s
     iterations.....................: 3153    101.54814/s
     vus............................: 0       min=0        max=229
     vus_max........................: 230     min=230      max=230

ERRO[0032] some thresholds have failed
```
