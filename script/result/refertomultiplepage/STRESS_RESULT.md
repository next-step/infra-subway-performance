# Stress 부하 테스트

--- 

## 개선 전 계측

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 450 max VUs, 1m6s max duration (incl. graceful stop):
           * default: Up to 450 looping VUs for 36s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m36.2s), 000/450 VUs, 6702 complete and 0 interrupted iterations
default ✓ [======================================] 000/450 VUs  36s

     ✓ logged in successfully
     ✓ get shortestPath

     checks.........................: 100.00% ✓ 13404      ✗ 0
     data_received..................: 25 MB   689 kB/s
     data_sent......................: 5.0 MB  138 kB/s
     http_req_blocked...............: avg=3.85ms   min=3.24µs  med=4.99µs   max=117.84ms p(90)=9.85ms   p(95)=21.16ms
     http_req_connecting............: avg=319.24µs min=0s      med=0s       max=29.35ms  p(90)=649.77µs p(95)=1.46ms
   ✓ http_req_duration..............: avg=145.41ms min=4.81ms  med=133.67ms max=739.15ms p(90)=316.21ms p(95)=362.74ms
       { expected_response:true }...: avg=145.41ms min=4.81ms  med=133.67ms max=739.15ms p(90)=316.21ms p(95)=362.74ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 13404
     http_req_receiving.............: avg=61.2µs   min=22.46µs med=50.89µs  max=11.67ms  p(90)=67.92µs  p(95)=76.98µs
     http_req_sending...............: avg=82.95µs  min=9.82µs  med=17.64µs  max=20.19ms  p(90)=66.16µs  p(95)=181.49µs
     http_req_tls_handshaking.......: avg=3.5ms    min=0s      med=0s       max=114.84ms p(90)=8.76ms   p(95)=19.22ms
     http_req_waiting...............: avg=145.27ms min=4.73ms  med=133.61ms max=739.1ms  p(90)=316.08ms p(95)=362.63ms
     http_reqs......................: 13404   369.801142/s
     iteration_duration.............: avg=1.29s    min=1.01s   med=1.29s    max=2.25s    p(90)=1.6s     p(95)=1.67s
     iterations.....................: 6702    184.900571/s
     vus............................: 6       min=6        max=450
     vus_max........................: 450     min=450      max=450
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
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 450 max VUs, 1m6s max duration (incl. graceful stop):
           * default: Up to 450 looping VUs for 36s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m36.3s), 000/450 VUs, 8356 complete and 0 interrupted iterations
default ✓ [======================================] 000/450 VUs  36s

     ✓ logged in successfully
     ✓ get shortestPath

     checks.........................: 100.00% ✓ 16712      ✗ 0
     data_received..................: 12 MB   321 kB/s
     data_sent......................: 3.5 MB  97 kB/s
     http_req_blocked...............: avg=136.57µs min=2.07µs  med=2.73µs  max=66.72ms p(90)=2.94µs  p(95)=6.72µs
     http_req_connecting............: avg=16.91µs  min=0s      med=0s      max=16.04ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=18.73ms  min=3.51ms  med=7.43ms  max=1.04s   p(90)=36.63ms p(95)=72.03ms
       { expected_response:true }...: avg=18.73ms  min=3.51ms  med=7.43ms  max=1.04s   p(90)=36.63ms p(95)=72.03ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 16712
     http_req_receiving.............: avg=61.23µs  min=28.19µs med=56.45µs max=2.37ms  p(90)=75.78µs p(95)=85.43µs
     http_req_sending...............: avg=59.04µs  min=22.73µs med=48.69µs max=2.35ms  p(90)=88.98µs p(95)=123.29µs
     http_req_tls_handshaking.......: avg=109.99µs min=0s      med=0s      max=38.25ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=18.61ms  min=3.41ms  med=7.3ms   max=1.04s   p(90)=36.47ms p(95)=71.94ms
     http_reqs......................: 16712   460.351953/s
     iteration_duration.............: avg=1.03s    min=1s      med=1.01s   max=2.05s   p(90)=1.08s   p(95)=1.14s
     iterations.....................: 8356    230.175976/s
     vus............................: 5       min=5        max=450
     vus_max........................: 450     min=450      max=450

```
