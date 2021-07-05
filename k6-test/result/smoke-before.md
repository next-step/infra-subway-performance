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


running (10.3s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 20       ✗ 0
     data_received..................: 11 kB   1.1 kB/s
     data_sent......................: 5.8 kB  565 B/s
     http_req_blocked...............: avg=3.21ms   min=2.1µs  med=4.59µs  max=64.3ms  p(90)=5.21µs  p(95)=3.22ms
     http_req_connecting............: avg=102.66µs min=0s     med=0s      max=2.05ms  p(90)=0s      p(95)=102.66µs
   ✓ http_req_duration..............: avg=8.86ms   min=5.96ms med=6.42ms  max=40.54ms p(90)=10.26ms p(95)=20.7ms
       { expected_response:true }...: avg=8.86ms   min=5.96ms med=6.42ms  max=40.54ms p(90)=10.26ms p(95)=20.7ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 20
     http_req_receiving.............: avg=62.79µs  min=39.9µs med=55.9µs  max=123.5µs p(90)=89.29µs p(95)=92.62µs
     http_req_sending...............: avg=21.29µs  min=7.4µs  med=17.39µs max=117.8µs p(90)=22.06µs p(95)=29.07µs
     http_req_tls_handshaking.......: avg=2.27ms   min=0s     med=0s      max=45.56ms p(90)=0s      p(95)=2.27ms
     http_req_waiting...............: avg=8.78ms   min=5.91ms med=6.34ms  max=40.3ms  p(90)=10.19ms p(95)=20.59ms
     http_reqs......................: 20      1.950329/s
     iteration_duration.............: avg=1.02s    min=1.01s  med=1.01s   max=1.11s   p(90)=1.03s   p(95)=1.07s
     iterations.....................: 10      0.975165/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
