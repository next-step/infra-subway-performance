```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: performance_find_path_stress_test.js
     output: -

  scenarios: (100.00%) 1 scenario, 230 max VUs, 1m0s max duration (incl. graceful stop):
           * default: Up to 230 looping VUs for 30s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m00.0s), 000/230 VUs, 116 complete and 134 interrupted iterations
default ✓ [======================================] 000/230 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✗ find path successfully
      ↳  51% — ✓ 60 / ✗ 56

     checks.........................: 90.90% ✓ 560       ✗ 56
     data_received..................: 1.4 MB 23 kB/s
     data_sent......................: 227 kB 3.8 kB/s
     http_req_blocked...............: avg=5.9ms    min=0s     med=0s      max=161.18ms p(90)=15.47ms p(95)=17.17ms
     http_req_connecting............: avg=1.59ms   min=0s     med=0s      max=37.07ms  p(90)=4.06ms  p(95)=4.68ms
   ✗ http_req_duration..............: avg=11.61s   min=9.66ms med=6.19s   max=33.98s   p(90)=30.02s  p(95)=30.04s
       { expected_response:true }...: avg=17.99s   min=6s     med=17.38s  max=33.98s   p(90)=32.96s  p(95)=33.16s
     http_req_failed................: 90.25% ✓ 556       ✗ 60
     http_req_receiving.............: avg=245.81µs min=0s     med=56.45µs max=1.95ms   p(90)=824.9µs p(95)=974.57µs
     http_req_sending...............: avg=184.93µs min=0s     med=0s      max=1.45ms   p(90)=671.7µs p(95)=881.62µs
     http_req_tls_handshaking.......: avg=4.15ms   min=0s     med=0s      max=152.72ms p(90)=11ms    p(95)=11.86ms
     http_req_waiting...............: avg=11.61s   min=9.05ms med=6.19s   max=33.97s   p(90)=30.02s  p(95)=30.04s
     http_reqs......................: 616    10.266106/s
     iteration_duration.............: avg=32.54s   min=11.24s med=33.16s  max=41.71s   p(90)=40.12s  p(95)=41.04s
     iterations.....................: 116    1.933228/s
     vus............................: 1      min=1       max=230
     vus_max........................: 230    min=230     max=230

ERRO[0061] some thresholds have failed
```
