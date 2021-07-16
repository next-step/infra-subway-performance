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


running (0m48.3s), 000/200 VUs, 559 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 1677      ✗ 0
     data_received..................: 3.0 MB  62 kB/s
     data_sent......................: 369 kB  7.6 kB/s
     http_req_blocked...............: avg=2.75ms   min=0s       med=0s       max=234.74ms p(90)=13.74ms  p(95)=17.27ms
     http_req_connecting............: avg=616.48µs min=0s       med=0s       max=40.36ms  p(90)=3.03ms   p(95)=4.41ms
   ✗ http_req_duration..............: avg=2.54s    min=9.31ms   med=920.89ms max=14.32s   p(90)=7.67s    p(95)=9.32s
       { expected_response:true }...: avg=4.66s    min=150.27ms med=3.52s    max=14.32s   p(90)=10.72s   p(95)=12.41s
     http_req_failed................: 66.66%  ✓ 1118      ✗ 559
     http_req_receiving.............: avg=224.53µs min=0s       med=0s       max=119.55ms p(90)=586.12µs p(95)=975.7µs
     http_req_sending...............: avg=65.41µs  min=0s       med=0s       max=28.28ms  p(90)=0s       p(95)=317.55µs
     http_req_tls_handshaking.......: avg=2.05ms   min=0s       med=0s       max=230.62ms p(90)=9.98ms   p(95)=12.5ms
     http_req_waiting...............: avg=2.53s    min=8.74ms   med=920.89ms max=14.32s   p(90)=7.67s    p(95)=9.31s
     http_reqs......................: 1677    34.731073/s
     iteration_duration.............: avg=8.63s    min=1.28s    med=8.41s    max=24.72s   p(90)=14.91s   p(95)=15.3s
     iterations.....................: 559     11.577024/s
     vus............................: 3       min=3       max=200
     vus_max........................: 200     min=200     max=200

ERRO[0049] some thresholds have failed
```
