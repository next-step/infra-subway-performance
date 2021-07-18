```bash
$ k6 run smoke.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.4s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 20       ✗ 0
     data_received..................: 40 kB   3.8 kB/s
     data_sent......................: 5.2 kB  502 B/s
     http_req_blocked...............: avg=1.08ms  min=2.73µs  med=2.9µs   max=32.44ms  p(90)=3.15µs   p(95)=3.85µs
     http_req_connecting............: avg=24.06µs min=0s      med=0s      max=721.83µs p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=12.69ms min=6.46ms  med=11.06ms max=63.43ms  p(90)=15.17ms  p(95)=18.08ms
       { expected_response:true }...: avg=12.69ms min=6.46ms  med=11.06ms max=63.43ms  p(90)=15.17ms  p(95)=18.08ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 30
     http_req_receiving.............: avg=87.69µs min=61.35µs med=82.01µs max=137.47µs p(90)=103.19µs p(95)=118.39µs
     http_req_sending...............: avg=72.62µs min=34.01µs med=44.1µs  max=235.17µs p(90)=117.6µs  p(95)=166.79µs
     http_req_tls_handshaking.......: avg=1.02ms  min=0s      med=0s      max=30.77ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=12.53ms min=6.36ms  med=10.9ms  max=63.08ms  p(90)=14.92ms  p(95)=17.91ms
     http_reqs......................: 30      2.876593/s
     iteration_duration.............: avg=1.04s   min=1.02s   med=1.03s   max=1.11s    p(90)=1.05s    p(95)=1.08s
     iterations.....................: 10      0.958864/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
