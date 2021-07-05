### smoke - before
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


running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     DURATION TIME - logged in successfully...: avg=9.14ms   min=6.8ms  med=7.07ms  max=26.95ms p(90)=9.92ms  p(95)=18.43ms
     DURATION TIME - retrieved member.........: avg=7.5ms    min=6.62ms med=6.99ms  max=11.57ms p(90)=7.95ms  p(95)=9.76ms
     checks...................................: 100.00% ✓ 20       ✗ 0
     data_received............................: 11 kB   1.1 kB/s
     data_sent................................: 5.8 kB  566 B/s
     http_req_blocked.........................: avg=2.99ms   min=2.29µs med=4.39µs  max=59.76ms p(90)=4.93µs  p(95)=2.99ms
     http_req_connecting......................: avg=123.47µs min=0s     med=0s      max=2.46ms  p(90)=0s      p(95)=123.47µs
   ✓ http_req_duration........................: avg=8.32ms   min=6.62ms med=7.07ms  max=26.95ms p(90)=8.38ms  p(95)=12.34ms
       { expected_response:true }.............: avg=8.32ms   min=6.62ms med=7.07ms  max=26.95ms p(90)=8.38ms  p(95)=12.34ms
     http_req_failed..........................: 0.00%   ✓ 0        ✗ 20
     http_req_receiving.......................: avg=54.97µs  min=27.4µs med=53.75µs max=86.9µs  p(90)=67.95µs p(95)=79.58µs
     http_req_sending.........................: avg=19.29µs  min=7.6µs  med=18.3µs  max=78.89µs p(90)=24.68µs p(95)=28.07µs
     http_req_tls_handshaking.................: avg=2ms      min=0s     med=0s      max=40.02ms p(90)=0s      p(95)=2ms
     http_req_waiting.........................: avg=8.25ms   min=6.58ms med=6.99ms  max=26.81ms p(90)=8.32ms  p(95)=12.27ms
     http_reqs................................: 20      1.953518/s
     iteration_duration.......................: avg=1.02s    min=1.01s  med=1.01s   max=1.09s   p(90)=1.02s   p(95)=1.06s
     iterations...............................: 10      0.976759/s
     vus......................................: 1       min=1      max=1
     vus_max..................................: 1       min=1      max=1

```
