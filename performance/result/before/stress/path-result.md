```

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: path-stress-test.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 12m40s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (12m10.9s), 000/240 VUs, 32890 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  12m10s

     ✓ path in successfully
     ✓ correct distance

     checks.........................: 100.00% ✓ 65780     ✗ 0
     data_received..................: 59 MB   81 kB/s
     data_sent......................: 5.8 MB  8.0 kB/s
     http_req_blocked...............: avg=52.09µs min=1.82µs  med=4.52µs  max=61.71ms p(90)=6.19µs  p(95)=6.74µs
     http_req_connecting............: avg=8.4µs   min=0s      med=0s      max=2.76ms  p(90)=0s      p(95)=0s
   ✗ http_req_duration..............: avg=2.11s   min=4.42ms  med=1.81s   max=8.68s   p(90)=4.17s   p(95)=4.22s
       { expected_response:true }...: avg=2.11s   min=4.42ms  med=1.81s   max=8.68s   p(90)=4.17s   p(95)=4.22s
     http_req_failed................: 0.00%   ✓ 0         ✗ 32890
     http_req_receiving.............: avg=79.13µs min=20.42µs med=74.39µs max=12.08ms p(90)=92.48µs p(95)=106.1µs
     http_req_sending...............: avg=27.74µs min=7.22µs  med=26.39µs max=5ms     p(90)=31.02µs p(95)=37.29µs
     http_req_tls_handshaking.......: avg=37.56µs min=0s      med=0s      max=51.02ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=2.11s   min=4.36ms  med=1.81s   max=8.68s   p(90)=4.17s   p(95)=4.22s
     http_reqs......................: 32890   44.996405/s
     iteration_duration.............: avg=3.11s   min=1s      med=2.82s   max=9.68s   p(90)=5.18s   p(95)=5.22s
     iterations.....................: 32890   44.996405/s
     vus............................: 33      min=1       max=240
     vus_max........................: 240     min=240     max=240

ERRO[0732] some thresholds have failed
```
