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


running (11.0s), 0/1 VUs, 9 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 36       ✗ 0
     data_received..................: 49 kB   4.5 kB/s
     data_sent......................: 5.8 kB  523 B/s
     http_req_blocked...............: avg=2.81ms   min=31.8µs  med=38.4µs   max=99.9ms   p(90)=66.6µs   p(95)=81.55µs
     http_req_connecting............: avg=356.26µs min=0s      med=0s       max=12.82ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=52.05ms  min=14.81ms med=26.65ms  max=148.57ms p(90)=126.83ms p(95)=143.97ms
       { expected_response:true }...: avg=52.05ms  min=14.81ms med=26.65ms  max=148.57ms p(90)=126.83ms p(95)=143.97ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 36
     http_req_receiving.............: avg=498.54µs min=174.2µs med=275.05µs max=5.5ms    p(90)=589.19µs p(95)=1.06ms
     http_req_sending...............: avg=275.84µs min=111µs   med=217.85µs max=810.2µs  p(90)=531.09µs p(95)=609.75µs
     http_req_tls_handshaking.......: avg=1.71ms   min=0s      med=0s       max=61.73ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=51.28ms  min=14.12ms med=26.24ms  max=148.19ms p(90)=126.34ms p(95)=143.55ms
     http_reqs......................: 36      3.266689/s
     iteration_duration.............: avg=1.22s    min=1.16s   med=1.21s    max=1.35s    p(90)=1.28s    p(95)=1.31s
     iterations.....................: 9       0.816672/s
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

  scenarios: (100.00%) 1 scenario, 35 max VUs, 2m0s max duration (incl. graceful stop):
           * default: Up to 35 looping VUs for 1m30s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m30.8s), 00/35 VUs, 1447 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  1m30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 5788      ✗ 0
     data_received..................: 7.4 MB  81 kB/s
     data_sent......................: 857 kB  9.4 kB/s
     http_req_blocked...............: avg=278.81µs min=30.3µs  med=41µs     max=62.73ms  p(90)=140.23µs p(95)=236.26µs
     http_req_connecting............: avg=52.18µs  min=0s      med=0s       max=23.5ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=116.75ms min=886.4µs med=24.53ms  max=1.68s    p(90)=447.79ms p(95)=559.89ms
       { expected_response:true }...: avg=116.75ms min=886.4µs med=24.53ms  max=1.68s    p(90)=447.79ms p(95)=559.89ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 5788
     http_req_receiving.............: avg=1.21ms   min=116.3µs med=406.9µs  max=159.33ms p(90)=1.63ms   p(95)=3.08ms
     http_req_sending...............: avg=1.19ms   min=79.8µs  med=316.14µs max=396.62ms p(90)=1.39ms   p(95)=2.5ms
     http_req_tls_handshaking.......: avg=118.2µs  min=0s      med=0s       max=42.98ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=114.34ms min=0s      med=22.77ms  max=1.67s    p(90)=445.61ms p(95)=556.54ms
     http_reqs......................: 5788    63.729184/s
     iteration_duration.............: avg=1.47s    min=1.1s    med=1.48s    max=2.8s     p(90)=1.75s    p(95)=1.8s
     iterations.....................: 1447    15.932296/s
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


running (2m05.4s), 000/250 VUs, 13764 complete and 0 interrupted iterations
default ✓ [======================================] 000/250 VUs  2m5s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 41292      ✗ 0
     data_received..................: 23 MB   186 kB/s
     data_sent......................: 6.9 MB  55 kB/s
     http_req_blocked...............: avg=1.54ms   min=29.9µs      med=41.3µs   max=1.96s    p(90)=170.4µs  p(95)=305.54µs
     http_req_connecting............: avg=175.04µs min=0s          med=0s       max=524.85ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=187.99ms min=-23779000ns med=92.55ms  max=3.85s    p(90)=470.46ms p(95)=788.25ms
       { expected_response:true }...: avg=187.99ms min=-23779000ns med=92.55ms  max=3.85s    p(90)=470.46ms p(95)=788.25ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 41292
     http_req_receiving.............: avg=7.42ms   min=-33968300ns med=310.09µs max=1.99s    p(90)=1.62ms   p(95)=6.72ms
     http_req_sending...............: avg=13.09ms  min=-33409599ns med=302.8µs  max=3.15s    p(90)=2.58ms   p(95)=21.65ms
     http_req_tls_handshaking.......: avg=320.54µs min=0s          med=0s       max=777.54ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=167.47ms min=0s          med=83.26ms  max=3.37s    p(90)=423.54ms p(95)=713.5ms
     http_reqs......................: 41292   329.201193/s
     iteration_duration.............: avg=1.62s    min=1.02s       med=1.35s    max=6.46s    p(90)=2.62s    p(95)=3.74s
     iterations.....................: 13764   109.733731/s
     vus............................: 15      min=4        max=250
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


running (2m01.4s), 000/240 VUs, 3616 complete and 0 interrupted iterations
default ✗ [======================================] 000/240 VUs  2m0s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths

     checks.........................: 100.00% ✓ 10848     ✗ 0
     data_received..................: 16 MB   129 kB/s
     data_sent......................: 1.9 MB  16 kB/s
     http_req_blocked...............: avg=8.55ms min=0s          med=41µs    max=2.91s  p(90)=171.3µs p(95)=365.56µs
     http_req_connecting............: avg=3.43ms min=0s          med=0s      max=2.13s  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=1.65s  min=7.79ms      med=1.72s   max=9.11s  p(90)=2.72s   p(95)=3.12s
       { expected_response:true }...: avg=1.65s  min=7.79ms      med=1.72s   max=9.11s  p(90)=2.72s   p(95)=3.12s
     http_req_failed................: 0.00%   ✓ 0         ✗ 10848
     http_req_receiving.............: avg=4.8ms  min=-29977500ns med=337.8µs max=2.04s  p(90)=1.38ms  p(95)=3.02ms
     http_req_sending...............: avg=5.92ms min=-28033300ns med=308.5µs max=1.95s  p(90)=1.45ms  p(95)=3.38ms
     http_req_tls_handshaking.......: avg=4.27ms min=-10127800ns med=0s      max=2.09s  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=1.64s  min=0s          med=1.71s   max=9.11s  p(90)=2.72s   p(95)=3.12s
     http_reqs......................: 10848   89.326568/s
     iteration_duration.............: avg=6.03s  min=1.08s       med=6.82s   max=14.85s p(90)=8.55s   p(95)=9.92s
     iterations.....................: 3616    29.775523/s
     vus............................: 33      min=4       max=240
     vus_max........................: 240     min=240     max=240
```

5. Stress-Favorite Max VUs Test

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_favorite.js
     output: -

  scenarios: (100.00%) 1 scenario, 4000 max VUs, 4m0s max duration (incl. graceful stop):
           * default: Up to 4000 looping VUs for 3m30s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)

WARN[0116] Could not get a VU from the buffer for 400ms  executor=ramping-vus scenario=default

running (4m42.8s), 0000/4000 VUs, 19517 complete and 3791 interrupted iterations
default ✓ [======================================] 3775/4000 VUs  3m30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 63944      ✗ 0
     data_received..................: 53 MB   188 kB/s
     data_sent......................: 13 MB   47 kB/s
     http_req_blocked...............: avg=341.55ms min=0s          med=42.8µs   max=57.97s p(90)=243.33µs p(95)=46.36ms
     http_req_connecting............: avg=102.52ms min=0s          med=0s       max=28.54s p(90)=0s       p(95)=16.98ms
   ✓ http_req_duration..............: avg=1.73s    min=0s          med=863.19ms max=1m55s  p(90)=3.88s    p(95)=4.87s
       { expected_response:true }...: avg=1.7s     min=478µs       med=875.18ms max=1m55s  p(90)=3.88s    p(95)=4.85s
     http_req_failed................: 0.74%   ✓ 501        ✗ 66857
     http_req_receiving.............: avg=93.93ms  min=-30985400ns med=317.85µs max=1m15s  p(90)=1.22ms   p(95)=6.85ms
     http_req_sending...............: avg=44.21ms  min=0s          med=300.89µs max=51.69s p(90)=1.38ms   p(95)=12.01ms
     http_req_tls_handshaking.......: avg=242.84ms min=0s          med=0s       max=1m35s  p(90)=0s       p(95)=27.91ms
     http_req_waiting...............: avg=1.59s    min=0s          med=834.03ms max=1m17s  p(90)=3.83s    p(95)=4.58s
     http_reqs......................: 67358   238.141083/s
     iteration_duration.............: avg=7.83s    min=1.02s       med=3.91s    max=1m27s  p(90)=23.09s   p(95)=25.82s
     iterations.....................: 20045   70.868167/s
     vus............................: 0       min=0        max=3819
     vus_max........................: 4000    min=4000     max=4000
```

6. Stress-Favorite Max VUs Test

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_paths.js
     output: -

  scenarios: (100.00%) 1 scenario, 1500 max VUs, 3m20s max duration (incl. graceful stop):
           * default: Up to 1500 looping VUs for 2m50s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m19.8s), 0000/1500 VUs, 6091 complete and 526 interrupted iterations
default ↓ [======================================] 1339/1500 VUs  2m50s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths

     checks.........................: 100.00% ✓ 18881     ✗ 0
     data_received..................: 32 MB   160 kB/s
     data_sent......................: 4.1 MB  21 kB/s
     http_req_blocked...............: avg=2.03ms   min=0s          med=40.4µs  max=383.42ms p(90)=266µs    p(95)=21.19ms
     http_req_connecting............: avg=552.51µs min=0s          med=0s      max=180.2ms  p(90)=0s       p(95)=5.65ms
   ✓ http_req_duration..............: avg=8.62s    min=839.6µs     med=9.15s   max=29.57s   p(90)=16.27s   p(95)=18.06s
       { expected_response:true }...: avg=8.62s    min=839.6µs     med=9.15s   max=29.57s   p(90)=16.27s   p(95)=18.06s
     http_req_failed................: 0.00%   ✓ 0         ✗ 19314
     http_req_receiving.............: avg=571.98µs min=-33327800ns med=306µs   max=167.06ms p(90)=844.6µs  p(95)=1.27ms
     http_req_sending...............: avg=528.75µs min=-28428500ns med=270.8µs max=131.17ms p(90)=956.81µs p(95)=1.41ms
     http_req_tls_handshaking.......: avg=1.34ms   min=0s          med=0s      max=342.49ms p(90)=0s       p(95)=14.3ms
     http_req_waiting...............: avg=8.62s    min=0s          med=9.15s   max=29.57s   p(90)=16.27s   p(95)=18.05s
     http_reqs......................: 19314   96.681905/s
     iteration_duration.............: avg=25.74s   min=1.08s       med=27.7s   max=1m1s     p(90)=45.71s   p(95)=48.26s
     iterations.....................: 6091    30.490291/s
     vus............................: 1       min=1       max=1500
     vus_max........................: 1500    min=1500    max=1500
```
