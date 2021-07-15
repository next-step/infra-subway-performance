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


running (0m35.7s), 000/200 VUs, 3237 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 9711       ✗ 0
     data_received..................: 5.4 MB  152 kB/s
     data_sent......................: 1.2 MB  34 kB/s
     http_req_blocked...............: avg=670.69µs min=0s     med=0s     max=700.87ms p(90)=0s      p(95)=0s
     http_req_connecting............: avg=128.37µs min=0s     med=0s     max=71.63ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=9.44ms   min=3.43ms med=7.33ms max=238.13ms p(90)=14.57ms p(95)=21.47ms
       { expected_response:true }...: avg=8.76ms   min=3.43ms med=6.53ms max=238.13ms p(90)=13.78ms p(95)=20.47ms
     http_req_failed................: 66.66%  ✓ 6474       ✗ 3237
     http_req_receiving.............: avg=216.17µs min=0s     med=0s     max=231.47ms p(90)=871.5µs p(95)=976.1µs
     http_req_sending...............: avg=129.76µs min=0s     med=0s     max=97.35ms  p(90)=517.7µs p(95)=972µs
     http_req_tls_handshaking.......: avg=530.52µs min=0s     med=0s     max=687.17ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=9.09ms   min=0s     med=7.02ms max=117.85ms p(90)=14.08ms p(95)=20.49ms
     http_reqs......................: 9711    272.297359/s
     iteration_duration.............: avg=1.03s    min=1.01s  med=1.02s  max=1.84s    p(90)=1.05s   p(95)=1.07s
     iterations.....................: 3237    90.765786/s
     vus............................: 22      min=5        max=200
     vus_max........................: 200     min=200      max=200

```
