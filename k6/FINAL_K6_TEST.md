징## Before K6 Test Report

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


running (10.9s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 40       ✗ 0
     data_received..................: 33 kB   3.0 kB/s
     data_sent......................: 6.3 kB  584 B/s
     http_req_blocked...............: avg=1.56ms   min=355ns    med=564ns    max=62.41ms  p(90)=844ns    p(95)=1.02µs
     http_req_connecting............: avg=144.87µs min=0s       med=0s       max=5.79ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=19.28ms  min=10.98ms  med=18.75ms  max=33.06ms  p(90)=26.34ms  p(95)=27.74ms
       { expected_response:true }...: avg=19.28ms  min=10.98ms  med=18.75ms  max=33.06ms  p(90)=26.34ms  p(95)=27.74ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 40
     http_req_receiving.............: avg=235.34µs min=122.33µs med=219.61µs max=645.44µs p(90)=324.05µs p(95)=332.93µs
     http_req_sending...............: avg=184.22µs min=64.48µs  med=97.58µs  max=898.57µs p(90)=425.54µs p(95)=565.3µs
     http_req_tls_handshaking.......: avg=1.22ms   min=0s       med=0s       max=48.98ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=18.86ms  min=10.58ms  med=18.21ms  max=32.75ms  p(90)=25.88ms  p(95)=27.49ms
     http_reqs......................: 40      3.678949/s
     iteration_duration.............: avg=1.08s    min=1.07s    med=1.08s    max=1.13s    p(90)=1.09s    p(95)=1.11s
     iterations.....................: 10      0.919737/s
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


running (0m50.8s), 00/35 VUs, 1180 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  50s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 4720      ✗ 0
     data_received..................: 4.4 MB  86 kB/s
     data_sent......................: 703 kB  14 kB/s
     http_req_blocked...............: avg=176.06µs min=269ns   med=585ns    max=58.95ms p(90)=906ns    p(95)=1.12µs
     http_req_connecting............: avg=48.84µs  min=0s      med=0s       max=17.61ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=14.25ms  min=5.84ms  med=13.61ms  max=72.24ms p(90)=20.25ms  p(95)=23.36ms
       { expected_response:true }...: avg=14.25ms  min=5.84ms  med=13.61ms  max=72.24ms p(90)=20.25ms  p(95)=23.36ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 4720
     http_req_receiving.............: avg=315.95µs min=60.36µs med=226.32µs max=13.64ms p(90)=541.84µs p(95)=768.32µs
     http_req_sending...............: avg=269.03µs min=40.73µs med=172.1µs  max=10.88ms p(90)=531.47µs p(95)=738.95µs
     http_req_tls_handshaking.......: avg=118.72µs min=0s      med=0s       max=38.24ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=13.67ms  min=4.13ms  med=13.05ms  max=71.94ms p(90)=19.67ms  p(95)=22.75ms
     http_reqs......................: 4720    92.860797/s
     iteration_duration.............: avg=1.06s    min=1.04s   med=1.05s    max=1.12s   p(90)=1.07s    p(95)=1.08s
     iterations.....................: 1180    23.215199/s
     vus............................: 2       min=2       max=35
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


running (2m05.7s), 000/250 VUs, 18383 complete and 0 interrupted iterations
default ✓ [======================================] 000/250 VUs  2m5s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 55149      ✗ 0
     data_received..................: 55 MB   435 kB/s
     data_sent......................: 9.2 MB  73 kB/s
     http_req_blocked...............: avg=115.74µs min=232ns       med=483ns    max=226.32ms p(90)=762ns    p(95)=930ns
     http_req_connecting............: avg=39.35µs  min=0s          med=0s       max=209.22ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=71.56ms  min=238.2µs     med=36.04ms  max=1.76s    p(90)=171.48ms p(95)=279.77ms
       { expected_response:true }...: avg=71.56ms  min=238.2µs     med=36.04ms  max=1.76s    p(90)=171.48ms p(95)=279.77ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 55149
     http_req_receiving.............: avg=331.3µs  min=-20180223ns med=182.25µs max=80.56ms  p(90)=520.7µs  p(95)=829.27µs
     http_req_sending...............: avg=301.43µs min=31.71µs     med=161.34µs max=90.53ms  p(90)=524.04µs p(95)=787.1µs
     http_req_tls_handshaking.......: avg=72.47µs  min=0s          med=0s       max=127.23ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=70.93ms  min=0s          med=35.39ms  max=1.76s    p(90)=170.94ms p(95)=279.05ms
     http_reqs......................: 55149   438.697144/s
     iteration_duration.............: avg=1.21s    min=1.03s       med=1.13s    max=3.29s    p(90)=1.39s    p(95)=1.86s
     iterations.....................: 18383   146.232381/s
     vus............................: 11      min=4        max=250
     vus_max........................: 250     min=250      max=250
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


running (2m00.6s), 000/240 VUs, 19424 complete and 0 interrupted iterations
default ↓ [======================================] 017/240 VUs  2m0s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths

     checks.........................: 100.00% ✓ 58272    ✗ 0
     data_received..................: 27 MB   223 kB/s
     data_sent......................: 9.7 MB  81 kB/s
     http_req_blocked...............: avg=100.69µs min=227ns       med=480ns    max=166.13ms p(90)=762ns    p(95)=935ns
     http_req_connecting............: avg=26.86µs  min=0s          med=0s       max=26.83ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=30.87ms  min=-10561226ns med=17.67ms  max=602.62ms p(90)=62.22ms  p(95)=103.81ms
       { expected_response:true }...: avg=30.87ms  min=-10561226ns med=17.67ms  max=602.62ms p(90)=62.22ms  p(95)=103.81ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 58272
     http_req_receiving.............: avg=341.45µs min=-20171686ns med=176.39µs max=131.73ms p(90)=500.34µs p(95)=819.31µs
     http_req_sending...............: avg=350.25µs min=-20102060ns med=161.9µs  max=154ms    p(90)=542.38µs p(95)=874.36µs
     http_req_tls_handshaking.......: avg=70.15µs  min=0s          med=0s       max=158.85ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=30.18ms  min=0s          med=16.96ms  max=601.71ms p(90)=61.21ms  p(95)=102.96ms
     http_reqs......................: 58272   483.1443/s
     iteration_duration.............: avg=1.09s    min=1.02s       med=1.06s    max=1.98s    p(90)=1.16s    p(95)=1.31s
     iterations.....................: 19424   161.0481/s
     vus............................: 17      min=4      max=240
     vus_max........................: 240     min=240    max=240
```
