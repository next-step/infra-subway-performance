```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: performance_find_path_load_test.js
     output: -

  scenarios: (100.00%) 1 scenario, 200 max VUs, 1m5s max duration (incl. graceful stop):
           * default: Up to 200 looping VUs for 35s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m43.4s), 000/200 VUs, 746 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 2238      ✗ 0
     data_received..................: 3.5 MB  82 kB/s
     data_sent......................: 377 kB  8.7 kB/s
     http_req_blocked...............: avg=1.96ms   min=0s      med=0s       max=176.02ms p(90)=0s       p(95)=16.43ms
     http_req_connecting............: avg=490.17µs min=0s      med=0s       max=35.93ms  p(90)=0s       p(95)=3.99ms
   ✗ http_req_duration..............: avg=1.62s    min=4.85ms  med=511.49ms max=9.26s    p(90)=5.14s    p(95)=6.72s
       { expected_response:true }...: avg=2.66s    min=70.88ms med=2.04s    max=9.26s    p(90)=6.04s    p(95)=7.62s
     http_req_failed................: 66.66%  ✓ 1492      ✗ 746
     http_req_receiving.............: avg=291.62µs min=0s      med=0s       max=15.72ms  p(90)=995.76µs p(95)=1ms
     http_req_sending...............: avg=177.68µs min=0s      med=0s       max=12.98ms  p(90)=582.2µs  p(95)=997.31µs
     http_req_tls_handshaking.......: avg=1.42ms   min=0s      med=0s       max=165.43ms p(90)=0s       p(95)=11.14ms
     http_req_waiting...............: avg=1.62s    min=3.98ms  med=511.47ms max=9.26s    p(90)=5.14s    p(95)=6.72s
     http_reqs......................: 2238    51.520439/s
     iteration_duration.............: avg=5.89s    min=1.08s   med=5.83s    max=14.58s   p(90)=10.58s   p(95)=11.39s
     iterations.....................: 746     17.17348/s
     vus............................: 13      min=5       max=200
     vus_max........................: 200     min=200     max=200

ERRO[0045] some thresholds have failed
```
