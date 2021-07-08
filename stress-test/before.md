A. smoke 테스트 : stress-test/smoke.js
```shell
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
         script: ./smoke.js
         output: -
    
      scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
               * default: 1 looping VUs for 10s (gracefulStop: 30s)
    
    
    running (11.1s), 0/1 VUs, 8 complete and 0 interrupted iterations
    default ↓ [======================================] 1 VUs  10s
    
         ✓ logged in successfully
         ✓ correct distance
    
         checks.........................: 100.00% ✓ 16  ✗ 0
         data_received..................: 32 kB   2.8 kB/s
         data_sent......................: 1.8 kB  158 B/s
         http_req_blocked...............: avg=8.03ms   min=4.87µs   med=7.1µs    max=64.21ms  p(90)=19.26ms  p(95)=41.74ms
         http_req_connecting............: avg=170.16µs min=0s       med=0s       max=1.36ms   p(90)=408.39µs p(95)=884.84µs
       ✗ http_req_duration..............: avg=383.29ms min=197.68ms med=250.85ms max=848.2ms  p(90)=807.74ms p(95)=827.97ms
           { expected_response:true }...: avg=383.29ms min=197.68ms med=250.85ms max=848.2ms  p(90)=807.74ms p(95)=827.97ms
         http_req_failed................: 0.00%   ✓ 0   ✗ 8
         http_req_receiving.............: avg=69.06µs  min=45.23µs  med=65.14µs  max=125.28µs p(90)=88.71µs  p(95)=106.99µs
         http_req_sending...............: avg=30.36µs  min=17.44µs  med=24.44µs  max=69.99µs  p(90)=44.2µs   p(95)=57.1µs
         http_req_tls_handshaking.......: avg=7.76ms   min=0s       med=0s       max=62.1ms   p(90)=18.63ms  p(95)=40.36ms
         http_req_waiting...............: avg=383.19ms min=197.59ms med=250.78ms max=848.11ms p(90)=807.58ms p(95)=827.84ms
         http_reqs......................: 8       0.718211/s
         iteration_duration.............: avg=1.39s    min=1.19s    med=1.25s    max=1.85s    p(90)=1.85s    p(95)=1.85s
         iterations.....................: 8       0.718211/s
         vus............................: 1       min=1 max=1
         vus_max........................: 1       min=1 max=1
```

B. load 테스트 : stress-test/load.js
```shell
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
         script: ./load.js
         output: -
    
      scenarios: (100.00%) 1 scenario, 35 max VUs, 3m40s max duration (incl. graceful stop):
               * default: Up to 35 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (3m10.2s), 00/35 VUs, 3112 complete and 0 interrupted iterations
    default ↓ [======================================] 03/35 VUs  3m10s
    
         ✓ logged in successfully
         ✓ correct distance
    
         checks.........................: 100.00% ✓ 6224 ✗ 0
         data_received..................: 11 MB   56 kB/s
         data_sent......................: 552 kB  2.9 kB/s
         http_req_blocked...............: avg=102.36µs min=3.63µs  med=6.66µs   max=57.06ms  p(90)=17.93µs p(95)=36.34µs
         http_req_connecting............: avg=16.74µs  min=0s      med=0s       max=5.93ms   p(90)=0s      p(95)=0s
       ✗ http_req_duration..............: avg=751.68ms min=95.05ms med=417.01ms max=2.29s    p(90)=1.62s   p(95)=1.75s
           { expected_response:true }...: avg=751.68ms min=95.05ms med=417.01ms max=2.29s    p(90)=1.62s   p(95)=1.75s
         http_req_failed................: 0.00%   ✓ 0    ✗ 3112
         http_req_receiving.............: avg=64.25µs  min=26.49µs med=58.52µs  max=566.04µs p(90)=76.8µs  p(95)=87.84µs
         http_req_sending...............: avg=26.77µs  min=9.95µs  med=21.97µs  max=585.2µs  p(90)=43.78µs p(95)=61.1µs
         http_req_tls_handshaking.......: avg=75.3µs   min=0s      med=0s       max=55.13ms  p(90)=0s      p(95)=0s
         http_req_waiting...............: avg=751.59ms min=94.97ms med=416.91ms max=2.29s    p(90)=1.62s   p(95)=1.75s
         http_reqs......................: 3112    16.360151/s
         iteration_duration.............: avg=1.75s    min=1.09s   med=1.41s    max=3.29s    p(90)=2.62s   p(95)=2.75s
         iterations.....................: 3112    16.360151/s
         vus............................: 3       min=1  max=35
         vus_max........................: 35      min=35 max=35
```

C. stress 테스트 : stress-test/stress.js
```shell
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
         script: ./stress.js
         output: -
    
      scenarios: (100.00%) 1 scenario, 140 max VUs, 12m40s max duration (incl. graceful stop):
               * default: Up to 140 looping VUs for 12m10s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (12m40.0s), 000/140 VUs, 7697 complete and 117 interrupted iterations
    default ✗ [======================================] 000/140 VUs  12m10s
    
         ✗ logged in successfully
          ↳  90% — ✓ 6983 / ✗ 714
         ✗ correct distance
          ↳  90% — ✓ 6983 / ✗ 714
    
         checks.........................: 90.72% ✓ 13966 ✗ 1428
         data_received..................: 27 MB  35 kB/s
         data_sent......................: 1.6 MB 2.1 kB/s
         http_req_blocked...............: avg=485.33µs min=3.7µs   med=6.83µs  max=64.88ms  p(90)=45.69µs p(95)=6.29ms
         http_req_connecting............: avg=92.15µs  min=0s      med=0s      max=5.92ms   p(90)=0s      p(95)=1.19ms
       ✗ http_req_duration..............: avg=6.55s    min=1.26ms  med=1.2s    max=1m0s     p(90)=7.49s   p(95)=59.99s
           { expected_response:true }...: avg=2.39s    min=91.47ms med=1.14s   max=39.99s   p(90)=6.64s   p(95)=7.24s
         http_req_failed................: 9.27%  ✓ 714   ✗ 6983
         http_req_receiving.............: avg=58.38µs  min=0s      med=56.56µs max=899.01µs p(90)=77.77µs p(95)=88.5µs
         http_req_sending...............: avg=28.55µs  min=10.1µs  med=22.48µs max=1.38ms   p(90)=50.88µs p(95)=61.2µs
         http_req_tls_handshaking.......: avg=369.49µs min=0s      med=0s      max=30.13ms  p(90)=0s      p(95)=4.99ms
         http_req_waiting...............: avg=6.55s    min=1.19ms  med=1.2s    max=1m0s     p(90)=7.49s   p(95)=59.99s
         http_reqs......................: 7697   10.127584/s
         iteration_duration.............: avg=7.48s    min=1s      med=2.2s    max=1m1s     p(90)=8.49s   p(95)=1m0s
         iterations.....................: 7697   10.127584/s
         vus............................: 13     min=1   max=140
         vus_max........................: 140    min=140 max=140
```
