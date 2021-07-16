```
C:\Program Files\k6>k6 run performance_find_path_load_test.js

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


running (1m05.0s), 000/200 VUs, 91 complete and 156 interrupted iterations
default ✓ [======================================] 000/200 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 585      ✗ 0
     data_received..................: 1.3 MB  20 kB/s
     data_sent......................: 208 kB  3.2 kB/s
     http_req_blocked...............: avg=6.14ms   min=0s     med=0s     max=211.97ms p(90)=15.68ms  p(95)=17.92ms
     http_req_connecting............: avg=1.4ms    min=0s     med=0s     max=35.5ms   p(90)=4.11ms   p(95)=4.65ms
   ✗ http_req_duration..............: avg=7.16s    min=9.67ms med=2.92s  max=29s      p(90)=20.01s   p(95)=22.53s
       { expected_response:true }...: avg=15.08s   min=3.68s  med=13.11s max=29s      p(90)=25.65s   p(95)=26.45s
     http_req_failed................: 84.44%  ✓ 494      ✗ 91
     http_req_receiving.............: avg=222.21µs min=0s     med=0s     max=1.19ms   p(90)=771.48µs p(95)=975.85µs
     http_req_sending...............: avg=413.25µs min=0s     med=0s     max=75.68ms  p(90)=921.34µs p(95)=976.4µs
     http_req_tls_handshaking.......: avg=4.36ms   min=0s     med=0s     max=208.06ms p(90)=10.86ms  p(95)=12.65ms
     http_req_waiting...............: avg=7.16s    min=8.88ms med=2.92s  max=29s      p(90)=20.01s   p(95)=22.53s
     http_reqs......................: 585     8.999213/s
     iteration_duration.............: avg=25.39s   min=4.73s  med=23.06s max=48.92s   p(90)=43.19s   p(95)=47.17s
     iterations.....................: 91      1.399878/s
     vus............................: 1       min=1      max=200
     vus_max........................: 200     min=200    max=200

ERRO[0067] some thresholds have failed
```
