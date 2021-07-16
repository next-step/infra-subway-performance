```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: performance_find_path_smoke_test.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (11.1s), 0/1 VUs, 9 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 27       ✗ 0
     data_received..................: 36 kB   3.3 kB/s
     data_sent......................: 3.7 kB  332 B/s
     http_req_blocked...............: avg=7.37ms   min=0s       med=0s       max=199.17ms p(90)=0s       p(95)=0s
     http_req_connecting............: avg=180.8µs  min=0s       med=0s       max=4.88ms   p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=68.63ms  min=10.86ms  med=15.4ms   max=208.04ms p(90)=201.06ms p(95)=207.35ms
       { expected_response:true }...: avg=176.64ms min=131.99ms med=183.19ms max=208.04ms p(90)=208.02ms p(95)=208.03ms
     http_req_failed................: 66.66%  ✓ 18       ✗ 9
     http_req_receiving.............: avg=469.59µs min=0s       med=0s       max=6.36ms   p(90)=770µs    p(95)=980.43µs
     http_req_sending...............: avg=518µs    min=0s       med=0s       max=10.73ms  p(90)=705.28µs p(95)=975.91µs
     http_req_tls_handshaking.......: avg=6.97ms   min=0s       med=0s       max=188.43ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=67.64ms  min=10.12ms  med=14.56ms  max=208.04ms p(90)=199.08ms p(95)=204.56ms
     http_reqs......................: 27      2.438129/s
     iteration_duration.............: avg=1.23s    min=1.15s    med=1.22s    max=1.43s    p(90)=1.27s    p(95)=1.35s
     iterations.....................: 9       0.81271/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

ERRO[0012] some thresholds have failed
```
