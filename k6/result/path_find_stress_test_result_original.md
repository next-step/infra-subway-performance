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


running (0m44.1s), 000/230 VUs, 528 complete and 0 interrupted iterations
default ✓ [======================================] 000/230 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 1584      ✗ 0
     data_received..................: 3.0 MB  69 kB/s
     data_sent......................: 364 kB  8.3 kB/s
     http_req_blocked...............: avg=3.52ms   min=0s       med=0s    max=264.53ms p(90)=15.24ms  p(95)=18.37ms
     http_req_connecting............: avg=933.52µs min=0s       med=0s    max=66.47ms  p(90)=3.58ms   p(95)=4.45ms
   ✗ http_req_duration..............: avg=2.87s    min=8.42ms   med=1.42s max=15.86s   p(90)=8.28s    p(95)=11.62s
       { expected_response:true }...: avg=4.94s    min=122.33ms med=3.73s max=15.86s   p(90)=11.43s   p(95)=12.96s
     http_req_failed................: 66.66%  ✓ 1056      ✗ 528
     http_req_receiving.............: avg=296.07µs min=0s       med=0s    max=104.14ms p(90)=554.44µs p(95)=975.6µs
     http_req_sending...............: avg=37.19µs  min=0s       med=0s    max=1.57ms   p(90)=0s       p(95)=242.99µs
     http_req_tls_handshaking.......: avg=2.56ms   min=0s       med=0s    max=249.45ms p(90)=11.22ms  p(95)=13.19ms
     http_req_waiting...............: avg=2.87s    min=8.42ms   med=1.42s max=15.86s   p(90)=8.28s    p(95)=11.62s
     http_reqs......................: 1584    35.945345/s
     iteration_duration.............: avg=9.64s    min=1.16s    med=8.45s max=24.04s   p(90)=16.79s   p(95)=18.13s
     iterations.....................: 528     11.981782/s
     vus............................: 0       min=0       max=229
     vus_max........................: 230     min=230     max=230

ERRO[0047] some thresholds have failed
```
