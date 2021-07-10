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

  scenarios: (100.00%) 1 scenario, 1 max VUs, 32s max duration (incl. graceful stop):
           * default: 1 looping VUs for 2s (gracefulStop: 30s)


running (02.1s), 0/1 VUs, 2 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  2s

     ✓ logged in successfully
     ✓ updated in  successfully

     checks.........................: 100.00% ✓ 4        ✗ 0
     data_received..................: 5.8 kB  2.8 kB/s
     data_sent......................: 1.3 kB  623 B/s
     http_req_blocked...............: avg=10.23ms  min=4.57µs  med=7.27µs  max=40.9ms   p(90)=28.63ms  p(95)=34.77ms
     http_req_connecting............: avg=146.94µs min=0s      med=0s      max=587.78µs p(90)=411.45µs p(95)=499.61µs
   ✓ http_req_duration..............: avg=6.2ms    min=5.04ms  med=6.34ms  max=7.06ms   p(90)=6.98ms   p(95)=7.02ms
       { expected_response:true }...: avg=6.2ms    min=5.04ms  med=6.34ms  max=7.06ms   p(90)=6.98ms   p(95)=7.02ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 4
     http_req_receiving.............: avg=64.13µs  min=41.57µs med=63.31µs max=88.34µs  p(90)=86.66µs  p(95)=87.5µs
     http_req_sending...............: avg=37.76µs  min=17.56µs med=24.59µs max=84.3µs   p(90)=68.38µs  p(95)=76.34µs
     http_req_tls_handshaking.......: avg=7.06ms   min=0s      med=0s      max=28.27ms  p(90)=19.79ms  p(95)=24.03ms
     http_req_waiting...............: avg=6.09ms   min=4.98ms  med=6.25ms  max=6.89ms   p(90)=6.82ms   p(95)=6.86ms
     http_reqs......................: 4       1.932914/s
     iteration_duration.............: avg=1.03s    min=1.01s   med=1.03s   max=1.05s    p(90)=1.05s    p(95)=1.05s
     iterations.....................: 2       0.966457/s
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

  scenarios: (100.00%) 1 scenario, 1 max VUs, 32s max duration (incl. graceful stop):
           * default: 1 looping VUs for 2s (gracefulStop: 30s)


running (02.2s), 0/1 VUs, 1 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  2s

     ✓ logged in successfully
     ✓ updated in  successfully

     checks.........................: 100.00% ✓ 2        ✗ 0
     data_received..................: 5.1 kB  2.3 kB/s
     data_sent......................: 902 B   404 B/s
     http_req_blocked...............: avg=21.14ms  min=2.88µs  med=21.14ms  max=42.28ms  p(90)=38.05ms  p(95)=40.17ms
     http_req_connecting............: avg=328.75µs min=0s      med=328.75µs max=657.51µs p(90)=591.76µs p(95)=624.63µs
   ✓ http_req_duration..............: avg=593.95ms min=93.67ms med=593.95ms max=1.09s    p(90)=994.18ms p(95)=1.04s
       { expected_response:true }...: avg=593.95ms min=93.67ms med=593.95ms max=1.09s    p(90)=994.18ms p(95)=1.04s
     http_req_failed................: 0.00%   ✓ 0        ✗ 2
     http_req_receiving.............: avg=87.74µs  min=64.74µs med=87.74µs  max=110.73µs p(90)=106.13µs p(95)=108.43µs
     http_req_sending...............: avg=213.2µs  min=74.06µs med=213.2µs  max=352.35µs p(90)=324.52µs p(95)=338.43µs
     http_req_tls_handshaking.......: avg=14.22ms  min=0s      med=14.22ms  max=28.45ms  p(90)=25.61ms  p(95)=27.03ms
     http_req_waiting...............: avg=593.65ms min=93.53ms med=593.65ms max=1.09s    p(90)=993.75ms p(95)=1.04s
     http_reqs......................: 2       0.895598/s
     iteration_duration.............: avg=2.23s    min=2.23s   med=2.23s    max=2.23s    p(90)=2.23s    p(95)=2.23s
     iterations.....................: 1       0.447799/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```
