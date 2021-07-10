
# Smoke 부하 테스트

--- 

## 개선 전 계측
```shell
ubuntu@ip-192-168-181-7:~/script/connectionfrequency$ k6 run smoke.js

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


running (10.5s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ↓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get stations
     ✓ get shortestPath
     ✓ get favorites

     checks.........................: 100.00% ✓ 50       ✗ 0
     data_received..................: 53 kB   5.1 kB/s
     data_sent......................: 15 kB   1.4 kB/s
     http_req_blocked...............: avg=770.18µs min=4.36µs  med=5.3µs   max=38.2ms   p(90)=8.29µs  p(95)=8.66µs
     http_req_connecting............: avg=15.36µs  min=0s      med=0s      max=768.15µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=8.36ms   min=6.1ms   med=7.89ms  max=16.24ms  p(90)=10.76ms p(95)=11.61ms
       { expected_response:true }...: avg=8.36ms   min=6.1ms   med=7.89ms  max=16.24ms  p(90)=10.76ms p(95)=11.61ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 50
     http_req_receiving.............: avg=78.08µs  min=47.65µs med=76.51µs max=101.11µs p(90)=89.14µs p(95)=95.32µs
     http_req_sending...............: avg=22.77µs  min=13.07µs med=17.82µs max=82.47µs  p(90)=36.18µs p(95)=42.72µs
     http_req_tls_handshaking.......: avg=554.75µs min=0s      med=0s      max=27.73ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=8.26ms   min=6.01ms  med=7.77ms  max=16.13ms  p(90)=10.65ms p(95)=11.52ms
     http_reqs......................: 50      4.772899/s
     iteration_duration.............: avg=1.04s    min=1.03s   med=1.04s   max=1.08s    p(90)=1.05s   p(95)=1.07s
     iterations.....................: 10      0.95458/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

---

## 개선 후 계측

```shell
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


running (11.0s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get stations
     ✓ get shortestPath
     ✓ get favorites

     checks.........................: 100.00% ✓ 50       ✗ 0
     data_received..................: 48 kB   4.4 kB/s
     data_sent......................: 7.1 kB  640 B/s
     http_req_blocked...............: avg=796.82µs min=2.71µs  med=2.88µs  max=39.69ms  p(90)=3.03µs   p(95)=3.09µs
     http_req_connecting............: avg=13.4µs   min=0s      med=0s      max=670.23µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=19.39ms  min=6.77ms  med=17.23ms max=68.15ms  p(90)=25.59ms  p(95)=42.92ms
       { expected_response:true }...: avg=19.39ms  min=6.77ms  med=17.23ms max=68.15ms  p(90)=25.59ms  p(95)=42.92ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 50
     http_req_receiving.............: avg=86.23µs  min=60.97µs med=81.59µs max=143.08µs p(90)=103.69µs p(95)=130.37µs
     http_req_sending...............: avg=58.3µs   min=27.2µs  med=39.14µs max=231.8µs  p(90)=118.26µs p(95)=121.1µs
     http_req_tls_handshaking.......: avg=566.28µs min=0s      med=0s      max=28.31ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=19.24ms  min=6.66ms  med=17.08ms max=67.99ms  p(90)=25.42ms  p(95)=42.66ms
     http_reqs......................: 50      4.53417/s
     iteration_duration.............: avg=1.1s     min=1.06s   med=1.08s   max=1.26s    p(90)=1.11s    p(95)=1.19s
     iterations.....................: 10      0.906834/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
