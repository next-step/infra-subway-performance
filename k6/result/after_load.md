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


running (0m36.0s), 000/240 VUs, 5738 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 11476      ✗ 0
     data_received..................: 21 MB   590 kB/s
     data_sent......................: 2.8 MB  78 kB/s
     http_req_blocked...............: avg=73.42µs min=2.22µs  med=2.74µs  max=60.01ms  p(90)=2.92µs   p(95)=3.09µs
     http_req_connecting............: avg=9.2µs   min=0s      med=0s      max=8.44ms   p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=57.38ms min=2.23ms  med=40.11ms max=433.55ms p(90)=130.04ms p(95)=166.11ms
       { expected_response:true }...: avg=57.38ms min=2.23ms  med=40.11ms max=433.55ms p(90)=130.04ms p(95)=166.11ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 17214
     http_req_receiving.............: avg=69.1µs  min=27.73µs med=59.95µs max=2.85ms   p(90)=85.79µs  p(95)=112.37µs
     http_req_sending...............: avg=50.27µs min=18.19µs med=36.51µs max=6.23ms   p(90)=74.37µs  p(95)=89.15µs
     http_req_tls_handshaking.......: avg=58.23µs min=0s      med=0s      max=45.21ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=57.26ms min=2.13ms  med=39.99ms max=433.46ms p(90)=129.95ms p(95)=166.03ms
     http_reqs......................: 17214   478.318018/s
     iteration_duration.............: avg=1.17s   min=1.01s   med=1.14s   max=1.84s    p(90)=1.34s    p(95)=1.4s
     iterations.....................: 5738    159.439339/s
     vus............................: 22      min=22       max=240
     vus_max........................: 240     min=240      max=240

```
