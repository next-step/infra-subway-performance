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


running (11.1s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 20       ✗ 0
     data_received..................: 42 kB   3.8 kB/s
     data_sent......................: 8.7 kB  782 B/s
     http_req_blocked...............: avg=1.58ms   min=4.22µs  med=4.85µs  max=47.38ms  p(90)=8.33µs   p(95)=17.05µs
     http_req_connecting............: avg=19.61µs  min=0s      med=0s      max=588.59µs p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=33.61ms  min=5.13ms  med=6.68ms  max=138.61ms p(90)=80.47ms  p(95)=86.09ms
       { expected_response:true }...: avg=33.61ms  min=5.13ms  med=6.68ms  max=138.61ms p(90)=80.47ms  p(95)=86.09ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 30
     http_req_receiving.............: avg=82.38µs  min=55.75µs med=80.17µs max=113.85µs p(90)=104.13µs p(95)=108.08µs
     http_req_sending...............: avg=24.37µs  min=12.67µs med=14.97µs max=83.83µs  p(90)=42.34µs  p(95)=43.4µs
     http_req_tls_handshaking.......: avg=934.66µs min=0s      med=0s      max=28.03ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=33.51ms  min=5.05ms  med=6.58ms  max=138.48ms p(90)=80.35ms  p(95)=85.98ms
     http_reqs......................: 30      2.709909/s
     iteration_duration.............: avg=1.1s     min=1.08s   med=1.09s   max=1.23s    p(90)=1.11s    p(95)=1.17s
     iterations.....................: 10      0.903303/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1


```
