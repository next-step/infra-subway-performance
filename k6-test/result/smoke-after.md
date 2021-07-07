### smoke - after
```shell script

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


running (10.3s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     DURATION TIME - logged in successfully...: avg=9.12ms   min=8.72ms med=9.02ms  max=10.28ms  p(90)=9.4ms    p(95)=9.84ms
     DURATION TIME - retrieved member.........: avg=10.92ms  min=8.96ms med=10.15ms max=18.6ms   p(90)=12.66ms  p(95)=15.63ms
     checks...................................: 100.00% ✓ 20       ✗ 0
     data_received............................: 9.9 kB  962 B/s
     data_sent................................: 4.3 kB  423 B/s
     http_req_blocked.........................: avg=2.87ms   min=1.3µs  med=1.4µs   max=57.37ms  p(90)=1.86µs   p(95)=2.87ms
     http_req_connecting......................: avg=113.16µs min=0s     med=0s      max=2.26ms   p(90)=0s       p(95)=113.16µs
   ✓ http_req_duration........................: avg=10.02ms  min=8.72ms med=9.28ms  max=18.6ms   p(90)=10.79ms  p(95)=12.33ms
       { expected_response:true }.............: avg=10.02ms  min=8.72ms med=9.28ms  max=18.6ms   p(90)=10.79ms  p(95)=12.33ms
     http_req_failed..........................: 0.00%   ✓ 0        ✗ 20
     http_req_receiving.......................: avg=89.34µs  min=53.7µs med=79.25µs max=304.09µs p(90)=101.54µs p(95)=125.69µs
     http_req_sending.........................: avg=87.5µs   min=31.3µs med=90.65µs max=257.1µs  p(90)=135.23µs p(95)=167.23µs
     http_req_tls_handshaking.................: avg=2.15ms   min=0s     med=0s      max=43.09ms  p(90)=0s       p(95)=2.15ms
     http_req_waiting.........................: avg=9.85ms   min=8.53ms med=9.03ms  max=18.43ms  p(90)=10.66ms  p(95)=12.23ms
     http_reqs................................: 20      1.946967/s
     iteration_duration.......................: avg=1.02s    min=1.01s  med=1.02s   max=1.08s    p(90)=1.03s    p(95)=1.05s
     iterations...............................: 10      0.973484/s
     vus......................................: 1       min=1      max=1
     vus_max..................................: 1       min=1      max=1

```
