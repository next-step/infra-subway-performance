### load - before
```shell script

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 2m30s max duration (incl. graceful stop):
           * peakTimeTest: 100 looping VUs for 2m0s (gracefulStop: 30s)


running (2m03.8s), 000/100 VUs, 2969 complete and 0 interrupted iterations
peakTimeTest ✓ [======================================] 100 VUs  2m0s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ information updated
     ✓ retrieved path

     checks.........................: 100.00% ✓ 11876     ✗ 0
     data_received..................: 11 MB   85 kB/s
     data_sent......................: 3.3 MB  27 kB/s
     http_req_blocked...............: avg=910.47µs min=1.6µs  med=2.4µs    max=167.05ms p(90)=4.4µs  p(95)=5.1µs
     http_req_connecting............: avg=82.27µs  min=0s     med=0s       max=12.25ms  p(90)=0s     p(95)=0s
   ✗ http_req_duration..............: avg=775.95ms min=5.26ms med=751.28ms max=2.65s    p(90)=1.16s  p(95)=1.3s
       { expected_response:true }...: avg=775.95ms min=5.26ms med=751.28ms max=2.65s    p(90)=1.16s  p(95)=1.3s
     http_req_failed................: 0.00%   ✓ 0         ✗ 11876
     http_req_receiving.............: avg=36.65µs  min=13µs   med=31.4µs   max=548µs    p(90)=54.4µs p(95)=62.7µs
     http_req_sending...............: avg=11.99µs  min=4.89µs med=9.6µs    max=715.8µs  p(90)=19.2µs p(95)=22.9µs
     http_req_tls_handshaking.......: avg=727.99µs min=0s     med=0s       max=142.91ms p(90)=0s     p(95)=0s
     http_req_waiting...............: avg=775.9ms  min=5.21ms med=751.24ms max=2.65s    p(90)=1.16s  p(95)=1.3s
     http_reqs......................: 11876   95.956718/s
     iteration_duration.............: avg=4.1s     min=1.72s  med=4.11s    max=6.13s    p(90)=4.31s  p(95)=4.71s
     iterations.....................: 2969    23.98918/s
     vus............................: 25      min=25      max=100
     vus_max........................: 100     min=100     max=100

ERRO[0125] some thresholds have failed
```
