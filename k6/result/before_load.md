```bash
$ k6 run load.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 1m5s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 35s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m44.9s), 000/240 VUs, 786 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 1572      ✗ 0
     data_received..................: 4.0 MB  90 kB/s
     data_sent......................: 742 kB  17 kB/s
     http_req_blocked...............: avg=468.17µs min=3.68µs  med=5.16µs  max=31.59ms  p(90)=4.03ms   p(95)=4.32ms
     http_req_connecting............: avg=59.94µs  min=0s      med=0s      max=3.23ms   p(90)=455.44µs p(95)=542.52µs
   ✗ http_req_duration..............: avg=3.09s    min=5.12ms  med=3.5s    max=15.59s   p(90)=4.76s    p(95)=6.86s
       { expected_response:true }...: avg=3.09s    min=5.12ms  med=3.5s    max=15.59s   p(90)=4.76s    p(95)=6.86s
     http_req_failed................: 0.00%   ✓ 0         ✗ 2358
     http_req_receiving.............: avg=75.17µs  min=28.31µs med=69.11µs max=941.15µs p(90)=101.98µs p(95)=112.01µs
     http_req_sending...............: avg=25.73µs  min=11.51µs med=19.06µs max=378.08µs p(90)=49.7µs   p(95)=59.43µs
     http_req_tls_handshaking.......: avg=393.43µs min=0s      med=0s      max=29.72ms  p(90)=3.46ms   p(95)=3.66ms
     http_req_waiting...............: avg=3.09s    min=4.99ms  med=3.5s    max=15.59s   p(90)=4.76s    p(95)=6.86s
     http_reqs......................: 2358    52.458388/s
     iteration_duration.............: avg=10.28s   min=1.24s   med=10.55s  max=24.57s   p(90)=15.55s   p(95)=17.15s
     iterations.....................: 786     17.486129/s
     vus............................: 22      min=22      max=240
     vus_max........................: 240     min=240     max=240

```
