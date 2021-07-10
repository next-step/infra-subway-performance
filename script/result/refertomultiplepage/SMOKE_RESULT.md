# Smoke 부하 테스트

--- 

## 개선 전 계측

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


running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ get shortestPath

     checks.........................: 100.00% ✓ 20       ✗ 0
     data_received..................: 18 kB   1.7 kB/s
     data_sent......................: 5.7 kB  557 B/s
     http_req_blocked...............: avg=1.84ms  min=4.25µs  med=6.62µs  max=36.85ms  p(90)=10.07µs p(95)=1.86ms
     http_req_connecting............: avg=31.62µs min=0s      med=0s      max=632.42µs p(90)=0s      p(95)=31.62µs
   ✓ http_req_duration..............: avg=9.01ms  min=6.8ms   med=9.66ms  max=12.27ms  p(90)=10.67ms p(95)=10.96ms
       { expected_response:true }...: avg=9.01ms  min=6.8ms   med=9.66ms  max=12.27ms  p(90)=10.67ms p(95)=10.96ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 20
     http_req_receiving.............: avg=80.42µs min=60.43µs med=76.46µs max=123.72µs p(90)=88.5µs  p(95)=98.23µs
     http_req_sending...............: avg=28.45µs min=13.77µs med=28.41µs max=85.58µs  p(90)=39.89µs p(95)=43.08µs
     http_req_tls_handshaking.......: avg=1.41ms  min=0s      med=0s      max=28.28ms  p(90)=0s      p(95)=1.41ms
     http_req_waiting...............: avg=8.9ms   min=6.68ms  med=9.55ms  max=12.17ms  p(90)=10.57ms p(95)=10.86ms
     http_reqs......................: 20      1.955259/s
     iteration_duration.............: avg=1.02s   min=1.01s   med=1.01s   max=1.05s    p(90)=1.02s   p(95)=1.04s
     iterations.....................: 10      0.97763/s
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


running (10.1s), 0/1 VUs, 8 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ get shortestPath

     checks.........................: 100.00% ✓ 16       ✗ 0
     data_received..................: 14 kB   1.4 kB/s
     data_sent......................: 3.7 kB  364 B/s
     http_req_blocked...............: avg=2.29ms   min=2.74µs  med=2.92µs  max=36.59ms  p(90)=3.29µs   p(95)=9.15ms
     http_req_connecting............: avg=37.23µs  min=0s      med=0s      max=595.73µs p(90)=0s       p(95)=148.93µs
   ✓ http_req_duration..............: avg=129.39ms min=7.86ms  med=17.63ms max=1.02s    p(90)=418.45ms p(95)=864.97ms
       { expected_response:true }...: avg=129.39ms min=7.86ms  med=17.63ms max=1.02s    p(90)=418.45ms p(95)=864.97ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 16
     http_req_receiving.............: avg=97.37µs  min=75.34µs med=95.32µs max=129.03µs p(90)=115.43µs p(95)=128.86µs
     http_req_sending...............: avg=95.3µs   min=34.9µs  med=82.05µs max=251.81µs p(90)=176.9µs  p(95)=233.09µs
     http_req_tls_handshaking.......: avg=1.77ms   min=0s      med=0s      max=28.42ms  p(90)=0s       p(95)=7.1ms
     http_req_waiting...............: avg=129.2ms  min=7.74ms  med=17.45ms max=1.02s    p(90)=418.26ms p(95)=864.75ms
     http_reqs......................: 16      1.58137/s
     iteration_duration.............: avg=1.26s    min=1.02s   med=1.03s   max=2.87s    p(90)=1.59s    p(95)=2.23s
     iterations.....................: 8       0.790685/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
