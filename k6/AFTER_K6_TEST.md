## After K6 Test Report

1. Smoke Test

```shell
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


running (10.5s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 40       ✗ 0
     data_received..................: 30 kB   2.8 kB/s
     data_sent......................: 6.3 kB  601 B/s
     http_req_blocked...............: avg=2.45ms   min=32µs    med=40.25µs  max=96.2ms  p(90)=85µs     p(95)=176.27µs
     http_req_connecting............: avg=163.31µs min=0s      med=0s       max=6.53ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=10.21ms  min=7.06ms  med=10.74ms  max=14.69ms p(90)=11.99ms  p(95)=12.4ms
       { expected_response:true }...: avg=10.21ms  min=7.06ms  med=10.74ms  max=14.69ms p(90)=11.99ms  p(95)=12.4ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 40
     http_req_receiving.............: avg=359.8µs  min=154.1µs med=303.59µs max=1.25ms  p(90)=608.2µs  p(95)=745.42µs
     http_req_sending...............: avg=303.33µs min=99.6µs  med=207.65µs max=1.12ms  p(90)=669.25µs p(95)=869.51µs
     http_req_tls_handshaking.......: avg=1.53ms   min=0s      med=0s       max=61.37ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=9.54ms   min=6.7ms   med=9.61ms   max=13.57ms p(90)=10.94ms  p(95)=11.77ms
     http_reqs......................: 40      3.792729/s
     iteration_duration.............: avg=1.05s    min=1.04s   med=1.04s    max=1.14s   p(90)=1.05s    p(95)=1.09s
     iterations.....................: 10      0.948182/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

2. Load Test

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 35 max VUs, 1m20s max duration (incl. graceful stop):
           * default: Up to 35 looping VUs for 50s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m51.0s), 00/35 VUs, 1203 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  50s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 4812      ✗ 0
     data_received..................: 3.2 MB  63 kB/s
     data_sent......................: 716 kB  14 kB/s
     http_req_blocked...............: avg=222.64µs min=30.1µs med=38.9µs   max=61.84ms p(90)=112.97µs p(95)=165.03µs
     http_req_connecting............: avg=45.92µs  min=0s     med=0s       max=10.02ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=9.43ms   min=5.39ms med=9.41ms   max=26.58ms p(90)=11.52ms  p(95)=12.49ms
       { expected_response:true }...: avg=9.43ms   min=5.39ms med=9.41ms   max=26.58ms p(90)=11.52ms  p(95)=12.49ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 4812
     http_req_receiving.............: avg=365.44µs min=91.3µs med=260.5µs  max=8.51ms  p(90)=643.01µs p(95)=922.09µs
     http_req_sending...............: avg=302.86µs min=69.9µs med=207.45µs max=7.75ms  p(90)=556.86µs p(95)=783.06µs
     http_req_tls_handshaking.......: avg=107.9µs  min=0s     med=0s       max=38.41ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=8.76ms   min=44.3µs med=8.78ms   max=21.8ms  p(90)=10.67ms  p(95)=11.56ms
     http_reqs......................: 4812    94.379761/s
     iteration_duration.............: avg=1.04s    min=1.03s  med=1.04s    max=1.11s   p(90)=1.04s    p(95)=1.05s
     iterations.....................: 1203    23.59494/s
     vus............................: 1       min=1       max=35
     vus_max........................: 35      min=35      max=35
```

3. Stress-Favorite Test

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_favorite.js
     output: -

  scenarios: (100.00%) 1 scenario, 250 max VUs, 2m35s max duration (incl. graceful stop):
           * default: Up to 250 looping VUs for 2m5s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m05.7s), 000/250 VUs, 19859 complete and 0 interrupted iterations
default ✓ [======================================] 000/250 VUs  2m5s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 59577     ✗ 0
     data_received..................: 38 MB   303 kB/s
     data_sent......................: 9.9 MB  79 kB/s
     http_req_blocked...............: avg=183.65µs min=0s          med=39.5µs  max=79.08ms  p(90)=136.6µs p(95)=209.9µs
     http_req_connecting............: avg=29.62µs  min=0s          med=0s      max=37.99ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=40.38ms  min=-15633700ns med=19.82ms max=627.39ms p(90)=93.84ms p(95)=152.83ms
       { expected_response:true }...: avg=40.38ms  min=-15633700ns med=19.82ms max=627.39ms p(90)=93.84ms p(95)=152.83ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 59577
     http_req_receiving.............: avg=648.14µs min=-31414400ns med=252.6µs max=156.24ms p(90)=796.5µs p(95)=1.43ms
     http_req_sending...............: avg=775.18µs min=-30313200ns med=242.3µs max=171.6ms  p(90)=852.6µs p(95)=1.72ms
     http_req_tls_handshaking.......: avg=73.37µs  min=0s          med=0s      max=60.13ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=38.96ms  min=0s          med=18.54ms max=626.53ms p(90)=91.19ms p(95)=149.56ms
     http_reqs......................: 59577   473.82141/s
     iteration_duration.............: avg=1.12s    min=1.02s       med=1.07s   max=2.19s    p(90)=1.28s   p(95)=1.45s
     iterations.....................: 19859   157.94047/s
     vus............................: 11      min=4       max=250
     vus_max........................: 250     min=250     max=250
```

4. Stress-Paths Test

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_paths.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 2m30s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 2m0s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m00.8s), 000/240 VUs, 19316 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  2m0s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths

     checks.........................: 100.00% ✓ 57948      ✗ 0
     data_received..................: 27 MB   222 kB/s
     data_sent......................: 9.7 MB  80 kB/s
     http_req_blocked...............: avg=199.22µs min=30.1µs      med=39.69µs  max=104.93ms p(90)=142.13µs p(95)=218.5µs
     http_req_connecting............: avg=32.64µs  min=-10811400ns med=0s       max=41.46ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=32.44ms  min=-21462300ns med=15.76ms  max=726.77ms p(90)=78.19ms  p(95)=111.2ms
       { expected_response:true }...: avg=32.44ms  min=-21462300ns med=15.76ms  max=726.77ms p(90)=78.19ms  p(95)=111.2ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 57948
     http_req_receiving.............: avg=611.9µs  min=-32667700ns med=254.4µs  max=136.26ms p(90)=776.43µs p(95)=1.35ms
     http_req_sending...............: avg=798.31µs min=-30087100ns med=245.65µs max=136.65ms p(90)=913.7µs  p(95)=2.02ms
     http_req_tls_handshaking.......: avg=83.51µs  min=0s          med=0s       max=63.99ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=31.03ms  min=0s          med=14.63ms  max=726.35ms p(90)=75.43ms  p(95)=108.12ms
     http_reqs......................: 57948   479.629804/s
     iteration_duration.............: avg=1.1s     min=1.02s       med=1.05s    max=2s       p(90)=1.23s    p(95)=1.32s
     iterations.....................: 19316   159.876601/s
     vus............................: 14      min=4        max=240
     vus_max........................: 240     min=240      max=240
```

---

1. Stress-Favorite Max VUs Test

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_favorite_max.js
     output: -

  scenarios: (100.00%) 1 scenario, 2500 max VUs, 2m25s max duration (incl. graceful stop):
           * default: Up to 2500 looping VUs for 1m55s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m57.4s), 0000/2500 VUs, 23576 complete and 0 interrupted iterations
default ✗ [======================================] 0000/2500 VUs  1m55s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 70728      ✗ 0
     data_received..................: 55 MB   471 kB/s
     data_sent......................: 13 MB   111 kB/s
     http_req_blocked...............: avg=15.47ms min=229ns       med=500ns    max=2.63s    p(90)=923ns    p(95)=2.19µs
     http_req_connecting............: avg=5.61ms  min=0s          med=0s       max=1.58s    p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=1.66s   min=7.4ms       med=1.79s    max=7.37s    p(90)=3.12s    p(95)=3.84s
       { expected_response:true }...: avg=1.66s   min=7.4ms       med=1.79s    max=7.37s    p(90)=3.12s    p(95)=3.84s
     http_req_failed................: 0.00%   ✓ 0          ✗ 70728
     http_req_receiving.............: avg=1.69ms  min=36.1µs      med=182.35µs max=943.72ms p(90)=643.37µs p(95)=1.31ms
     http_req_sending...............: avg=1.76ms  min=-19487782ns med=171.09µs max=945.92ms p(90)=693.39µs p(95)=1.45ms
     http_req_tls_handshaking.......: avg=9.74ms  min=0s          med=0s       max=1.75s    p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=1.66s   min=0s          med=1.78s    max=7.37s    p(90)=3.11s    p(95)=3.84s
     http_reqs......................: 70728   602.371081/s
     iteration_duration.............: avg=6.05s   min=1.02s       med=6.98s    max=15.08s   p(90)=10.18s   p(95)=11.56s
     iterations.....................: 23576   200.79036/s
     vus............................: 369     min=4        max=2500
     vus_max........................: 2500    min=2500     max=2500
```

2. Stress-Paths Max VUs Test

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_paths_max.js
     output: -

  scenarios: (100.00%) 1 scenario, 1500 max VUs, 2m25s max duration (incl. graceful stop):
           * default: Up to 1500 looping VUs for 1m55s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m55.9s), 0000/1500 VUs, 28816 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1500 VUs  1m55s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths

     checks.........................: 100.00% ✓ 86448      ✗ 0
     data_received..................: 45 MB   390 kB/s
     data_sent......................: 15 MB   130 kB/s
     http_req_blocked...............: avg=3.41ms   min=196ns   med=471ns    max=2.15s    p(90)=770ns    p(95)=1.02µs
     http_req_connecting............: avg=1.35ms   min=0s      med=0s       max=1.01s    p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=704.69ms min=5.31ms  med=656.07ms max=5.47s    p(90)=1.49s    p(95)=1.84s
       { expected_response:true }...: avg=704.69ms min=5.31ms  med=656.07ms max=5.47s    p(90)=1.49s    p(95)=1.84s
     http_req_failed................: 0.00%   ✓ 0          ✗ 86448
     http_req_receiving.............: avg=1.25ms   min=34.42µs med=165.4µs  max=925.49ms p(90)=556.7µs  p(95)=1.09ms
     http_req_sending...............: avg=1.5ms    min=20.68µs med=155.78µs max=1.38s    p(90)=601.26µs p(95)=1.24ms
     http_req_tls_handshaking.......: avg=2.01ms   min=0s      med=0s       max=1.69s    p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=701.93ms min=0s      med=653.11ms max=5.46s    p(90)=1.48s    p(95)=1.83s
     http_reqs......................: 86448   745.764799/s
     iteration_duration.............: avg=3.13s    min=1.02s   med=3.39s    max=9.6s     p(90)=4.92s    p(95)=5.4s
     iterations.....................: 28816   248.588266/s
     vus............................: 0       min=0        max=1500
     vus_max........................: 1500    min=1500     max=1500
```
