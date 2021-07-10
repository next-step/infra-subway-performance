# LOAD 부하 테스트

--- 

## 개선 전 계측

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 35 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 35 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.3s), 00/35 VUs, 5335 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  3m10s

     ✓ logged in successfully
     ✓ get shortestPath

     checks.........................: 100.00% ✓ 10670     ✗ 0
     data_received..................: 7.2 MB  38 kB/s
     data_sent......................: 2.9 MB  15 kB/s
     http_req_blocked...............: avg=23.36µs min=3.23µs  med=4.96µs max=27.98ms  p(90)=8.06µs  p(95)=8.6µs
     http_req_connecting............: avg=2.69µs  min=0s      med=0s     max=7.06ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=9.97ms  min=4.77ms  med=9.74ms max=140.57ms p(90)=15.1ms  p(95)=18.32ms
       { expected_response:true }...: avg=9.97ms  min=4.77ms  med=9.74ms max=140.57ms p(90)=15.1ms  p(95)=18.32ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 10670
     http_req_receiving.............: avg=58.5µs  min=24.17µs med=57.1µs max=724.33µs p(90)=77.7µs  p(95)=84.95µs
     http_req_sending...............: avg=22.84µs min=10.08µs med=17.4µs max=3.58ms   p(90)=37.91µs p(95)=41.8µs
     http_req_tls_handshaking.......: avg=14.52µs min=0s      med=0s     max=26.75ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=9.88ms  min=4.71ms  med=9.66ms max=140.5ms  p(90)=15.02ms p(95)=18.25ms
     http_reqs......................: 10670   56.072661/s
     iteration_duration.............: avg=1.02s   min=1.01s   med=1.01s  max=1.14s    p(90)=1.02s   p(95)=1.03s
     iterations.....................: 5335    28.03633/s
     vus............................: 3       min=1       max=35
     vus_max........................: 35      min=35      max=35
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
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 35 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 35 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.4s), 00/35 VUs, 5356 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  3m10s

     ✓ logged in successfully
     ✓ get shortestPath

     checks.........................: 100.00% ✓ 10712     ✗ 0
     data_received..................: 6.3 MB  33 kB/s
     data_sent......................: 2.1 MB  11 kB/s
     http_req_blocked...............: avg=20.8µs  min=2.24µs  med=2.77µs  max=29.58ms  p(90)=3.08µs   p(95)=3.23µs
     http_req_connecting............: avg=2.16µs  min=0s      med=0s      max=2.05ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=7.61ms  min=3.61ms  med=7.12ms  max=29.04ms  p(90)=11.2ms   p(95)=12.98ms
       { expected_response:true }...: avg=7.61ms  min=3.61ms  med=7.12ms  max=29.04ms  p(90)=11.2ms   p(95)=12.98ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 10712
     http_req_receiving.............: avg=63.88µs min=30.48µs med=61.41µs max=377.54µs p(90)=81.2µs   p(95)=88.65µs
     http_req_sending...............: avg=62.61µs min=25.76µs med=51.27µs max=646.98µs p(90)=109.36µs p(95)=120.12µs
     http_req_tls_handshaking.......: avg=15µs    min=0s      med=0s      max=28.13ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=7.48ms  min=3.51ms  med=6.99ms  max=28.55ms  p(90)=11.04ms  p(95)=12.84ms
     http_reqs......................: 10712   56.264558/s
     iteration_duration.............: avg=1.01s   min=1s      med=1.01s   max=1.06s    p(90)=1.02s    p(95)=1.02s
     iterations.....................: 5356    28.132279/s
     vus............................: 2       min=1       max=35
     vus_max........................: 35      min=35      max=35
```
