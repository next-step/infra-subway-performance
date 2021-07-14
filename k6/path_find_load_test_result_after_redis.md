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


running (0m35.9s), 000/200 VUs, 3245 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 9735       ✗ 0
     data_received..................: 5.4 MB  152 kB/s
     data_sent......................: 1.2 MB  34 kB/s
     http_req_blocked...............: avg=323.95µs min=0s     med=0s     max=166.72ms p(90)=0s       p(95)=0s
     http_req_connecting............: avg=81.53µs  min=0s     med=0s     max=31.3ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=9.52ms   min=3.98ms med=7.96ms max=82.11ms  p(90)=14.56ms  p(95)=18.68ms
       { expected_response:true }...: avg=8.77ms   min=3.98ms med=7.25ms max=79.98ms  p(90)=13.69ms  p(95)=17.84ms
     http_req_failed................: 66.66%  ✓ 6490       ✗ 3245
     http_req_receiving.............: avg=252.27µs min=0s     med=0s     max=7.81ms   p(90)=797.96µs p(95)=972.5µs
     http_req_sending...............: avg=83.43µs  min=0s     med=0s     max=3.24ms   p(90)=503.6µs  p(95)=529.23µs
     http_req_tls_handshaking.......: avg=231.28µs min=0s     med=0s     max=149.55ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=9.18ms   min=3.16ms med=7.62ms max=81.1ms   p(90)=14.29ms  p(95)=18.45ms
     http_reqs......................: 9735    271.544308/s
     iteration_duration.............: avg=1.03s    min=1.01s  med=1.02s  max=1.35s    p(90)=1.04s    p(95)=1.05s
     iterations.....................: 3245    90.514769/s
     vus............................: 23      min=5        max=199
     vus_max........................: 200     min=200      max=200

```
