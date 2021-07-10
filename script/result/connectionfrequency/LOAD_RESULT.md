
# LOAD 부하 테스트

--- 

## 개선 전 계측

```shell
ubuntu@ip-192-168-181-7:~/script/connectionfrequency$ k6 run load.js

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


running (3m11.0s), 00/35 VUs, 5172 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  3m10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get stations
     ✓ get shortestPath
     ✓ get favorites

     checks.........................: 100.00% ✓ 25860      ✗ 0
     data_received..................: 25 MB   132 kB/s
     data_sent......................: 7.3 MB  38 kB/s
     http_req_blocked...............: avg=12.15µs min=3.21µs  med=4.49µs  max=34.54ms  p(90)=6.38µs  p(95)=7.91µs
     http_req_connecting............: avg=786ns   min=0s      med=0s      max=836.83µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=10.41ms min=4.78ms  med=8.59ms  max=100.71ms p(90)=16.07ms p(95)=23.16ms
       { expected_response:true }...: avg=10.41ms min=4.78ms  med=8.59ms  max=100.71ms p(90)=16.07ms p(95)=23.16ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 25860
     http_req_receiving.............: avg=54.06µs min=23.38µs med=53.37µs max=946.65µs p(90)=68.4µs  p(95)=75.1µs
     http_req_sending...............: avg=18.23µs min=9.42µs  med=14.13µs max=840.68µs p(90)=27.25µs p(95)=35.23µs
     http_req_tls_handshaking.......: avg=5.96µs  min=0s      med=0s      max=27.44ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=10.33ms min=4.7ms   med=8.51ms  max=100.63ms p(90)=16ms    p(95)=23.09ms
     http_reqs......................: 25860   135.387971/s
     iteration_duration.............: avg=1.05s   min=1.03s   med=1.04s   max=1.26s    p(90)=1.07s   p(95)=1.08s
     iterations.....................: 5172    27.077594/s
     vus............................: 1       min=1        max=35
     vus_max........................: 35      min=35       max=35
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


running (3m11.0s), 00/35 VUs, 5210 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  3m10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get stations
     ✓ get shortestPath
     ✓ get favorites

     checks.........................: 100.00% ✓ 26050      ✗ 0
     data_received..................: 23 MB   120 kB/s
     data_sent......................: 3.4 MB  18 kB/s
     http_req_blocked...............: avg=10.83µs min=1.73µs  med=2.73µs  max=51.08ms  p(90)=2.91µs  p(95)=3.02µs
     http_req_connecting............: avg=833ns   min=0s      med=0s      max=871.54µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=8.88ms  min=3.47ms  med=7.98ms  max=47.99ms  p(90)=13.74ms p(95)=16.35ms
       { expected_response:true }...: avg=8.88ms  min=3.47ms  med=7.98ms  max=47.99ms  p(90)=13.74ms p(95)=16.35ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 26050
     http_req_receiving.............: avg=61.41µs min=28.71µs med=58.37µs max=8.22ms   p(90)=74.41µs p(95)=82.29µs
     http_req_sending...............: avg=42.29µs min=17.05µs med=32.86µs max=791µs    p(90)=71.17µs p(95)=90.87µs
     http_req_tls_handshaking.......: avg=6.62µs  min=0s      med=0s      max=43.65ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=8.77ms  min=3.38ms  med=7.87ms  max=47.91ms  p(90)=13.64ms p(95)=16.26ms
     http_reqs......................: 26050   136.408403/s
     iteration_duration.............: avg=1.04s   min=1.02s   med=1.04s   max=1.13s    p(90)=1.05s   p(95)=1.06s
     iterations.....................: 5210    27.281681/s
     vus............................: 2       min=1        max=35
     vus_max........................: 35      min=35       max=35
     
```
