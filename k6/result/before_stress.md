```bash
$ k6 run stress.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 360 max VUs, 2m5s max duration (incl. graceful stop):
           * default: Up to 360 looping VUs for 1m35s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m52.2s), 000/360 VUs, 1974 complete and 0 interrupted iterations
default ✓ [======================================] 000/360 VUs  1m35s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 3948      ✗ 0
     data_received..................: 9.0 MB  80 kB/s
     data_sent......................: 1.8 MB  16 kB/s
     http_req_blocked...............: avg=303.69µs min=3.53µs  med=5.17µs  max=30.11ms  p(90)=8.94µs  p(95)=4.28ms
     http_req_connecting............: avg=46.21µs  min=0s      med=0s      max=15.1ms   p(90)=0s      p(95)=541.26µs
   ✗ http_req_duration..............: avg=3.78s    min=4.57ms  med=3.59s   max=20.44s   p(90)=6.98s   p(95)=7.78s
       { expected_response:true }...: avg=3.78s    min=4.57ms  med=3.59s   max=20.44s   p(90)=6.98s   p(95)=7.78s
     http_req_failed................: 0.00%   ✓ 0         ✗ 5922
     http_req_receiving.............: avg=73.19µs  min=28.63µs med=68.39µs max=345.39µs p(90)=99.11µs p(95)=106.79µs
     http_req_sending...............: avg=24.46µs  min=11.63µs med=19.05µs max=663.34µs p(90)=42.34µs p(95)=54.06µs
     http_req_tls_handshaking.......: avg=246.24µs min=0s      med=0s      max=28.89ms  p(90)=0s      p(95)=3.64ms
     http_req_waiting...............: avg=3.78s    min=4.42ms  med=3.59s   max=20.44s   p(90)=6.98s   p(95)=7.78s
     http_reqs......................: 5922    52.796742/s
     iteration_duration.............: avg=12.35s   min=1.09s   med=12.13s  max=34.71s   p(90)=20.23s  p(95)=23.13s
     iterations.....................: 1974    17.598914/s
     vus............................: 7       min=7       max=360
     vus_max........................: 360     min=360     max=360

```
