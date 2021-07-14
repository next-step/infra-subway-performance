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


running (0m46.9s), 000/200 VUs, 718 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 2154      ✗ 0
     data_received..................: 3.6 MB  77 kB/s
     data_sent......................: 452 kB  9.6 kB/s
     http_req_blocked...............: avg=2.35ms   min=0s      med=0s       max=296.07ms p(90)=0s       p(95)=16.14ms
     http_req_connecting............: avg=601.58µs min=0s      med=0s       max=108.42ms p(90)=0s       p(95)=3.9ms
   ✗ http_req_duration..............: avg=1.75s    min=4.63ms  med=531.48ms max=8.89s    p(90)=5.56s    p(95)=7.03s
       { expected_response:true }...: avg=2.95s    min=74.31ms med=2.26s    max=8.89s    p(90)=6.8s     p(95)=7.76s
     http_req_failed................: 66.66%  ✓ 1436      ✗ 718
     http_req_receiving.............: avg=144.32µs min=0s      med=0s       max=14.7ms   p(90)=589.94µs p(95)=975.63µs
     http_req_sending...............: avg=89.44µs  min=0s      med=0s       max=43.24ms  p(90)=0s       p(95)=110.28µs
     http_req_tls_handshaking.......: avg=1.69ms   min=0s      med=0s       max=278.6ms  p(90)=0s       p(95)=11.97ms
     http_req_waiting...............: avg=1.75s    min=4.63ms  med=531.48ms max=8.89s    p(90)=5.56s    p(95)=7.03s
     http_reqs......................: 2154    45.967126/s
     iteration_duration.............: avg=6.28s    min=1.09s   med=6.15s    max=17.42s   p(90)=11.39s   p(95)=11.86s
     iterations.....................: 718     15.322375/s
     vus............................: 0       min=0       max=200
     vus_max........................: 200     min=200     max=200

ERRO[0049] some thresholds have failed
```
