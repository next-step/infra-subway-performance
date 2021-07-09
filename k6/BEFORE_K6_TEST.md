## Before K6 Test Report

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


running (11.2s), 0/1 VUs, 8 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 32       ✗ 0
     data_received..................: 46 kB   4.1 kB/s
     data_sent......................: 9.6 kB  855 B/s
     http_req_blocked...............: avg=7.99ms   min=24.6µs  med=32.8µs  max=253.99ms p(90)=90.01µs  p(95)=358.75µs
     http_req_connecting............: avg=545.33µs min=0s      med=0s      max=17.45ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=89.46ms  min=14.25ms med=24.3ms  max=716.78ms p(90)=235.19ms p(95)=250.06ms
       { expected_response:true }...: avg=89.46ms  min=14.25ms med=24.3ms  max=716.78ms p(90)=235.19ms p(95)=250.06ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 32
     http_req_receiving.............: avg=445.12µs min=128.2µs med=245.8µs max=2.69ms   p(90)=1.03ms   p(95)=1.67ms
     http_req_sending...............: avg=131.84µs min=36.9µs  med=83.65µs max=527µs    p(90)=291.54µs p(95)=384.9µs
     http_req_tls_handshaking.......: avg=5.92ms   min=0s      med=0s      max=189.51ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=88.89ms  min=13.96ms med=23.95ms max=716.39ms p(90)=234.82ms p(95)=249.63ms
     http_reqs......................: 32      2.862404/s
     iteration_duration.............: avg=1.39s    min=1.19s   med=1.26s   max=2.36s    p(90)=1.64s    p(95)=2s
     iterations.....................: 8       0.715601/s
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


running (1m30.2s), 00/35 VUs, 1251 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  1m30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 5004      ✗ 0
     data_received..................: 6.6 MB  73 kB/s
     data_sent......................: 1.4 MB  16 kB/s
     http_req_blocked...............: avg=225.02µs min=22.4µs  med=33µs     max=77.28ms p(90)=71.96µs  p(95)=136.12µs
     http_req_connecting............: avg=59.05µs  min=0s      med=0s       max=23.37ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=176.07ms min=8.1ms   med=70.45ms  max=1.14s   p(90)=548.35ms p(95)=644.59ms
       { expected_response:true }...: avg=176.07ms min=8.1ms   med=70.45ms  max=1.14s   p(90)=548.35ms p(95)=644.59ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 5004
     http_req_receiving.............: avg=261.57µs min=76.89µs med=190.35µs max=4.48ms  p(90)=446.78µs p(95)=616.96µs
     http_req_sending...............: avg=90.38µs  min=23µs    med=60.7µs   max=3.31ms  p(90)=167.4µs  p(95)=220.56µs
     http_req_tls_handshaking.......: avg=115.79µs min=0s      med=0s       max=53.29ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=175.72ms min=7.9ms   med=70.12ms  max=1.14s   p(90)=547.62ms p(95)=644.37ms
     http_reqs......................: 5004    55.475177/s
     iteration_duration.............: avg=1.7s     min=1.1s    med=1.72s    max=2.76s   p(90)=2.19s    p(95)=2.24s
     iterations.....................: 1251    13.868794/s
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

  scenarios: (100.00%) 1 scenario, 300 max VUs, 2m35s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 2m5s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m05.6s), 000/300 VUs, 21099 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  2m5s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Favorite Paths

     checks.........................: 100.00% ✓ 63297      ✗ 0
     data_received..................: 37 MB   295 kB/s
     data_sent......................: 18 MB   141 kB/s
     http_req_blocked...............: avg=781.54µs min=22.7µs      med=35.5µs  max=1.72s    p(90)=120.4µs  p(95)=200.6µs
     http_req_connecting............: avg=281.02µs min=0s          med=0s      max=728.64ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=69.93ms  min=-19732300ns med=23.52ms max=1.59s    p(90)=181.8ms  p(95)=282.1ms
       { expected_response:true }...: avg=69.93ms  min=-19732300ns med=23.52ms max=1.59s    p(90)=181.8ms  p(95)=282.1ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 63297
     http_req_receiving.............: avg=1.32ms   min=-31419800ns med=195.2µs max=646.5ms  p(90)=716.5µs  p(95)=1.57ms
     http_req_sending...............: avg=2.4ms    min=22.8µs      med=64µs    max=861.11ms p(90)=709.84µs p(95)=3.6ms
     http_req_tls_handshaking.......: avg=336.17µs min=0s          med=0s      max=992.04ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=66.2ms   min=0s          med=22.65ms max=1.08s    p(90)=173.96ms p(95)=263.9ms
     http_reqs......................: 63297   504.058554/s
     iteration_duration.............: avg=1.22s    min=1.02s       med=1.08s   max=3.43s    p(90)=1.6s     p(95)=1.91s
     iterations.....................: 21099   168.019518/s
     vus............................: 14      min=4        max=300
     vus_max........................: 300     min=300      max=300
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


running (2m04.2s), 000/240 VUs, 2917 complete and 0 interrupted iterations
default ↓ [======================================] 116/240 VUs  2m0s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find Paths

     checks.........................: 100.00% ✓ 8751      ✗ 0
     data_received..................: 14 MB   110 kB/s
     data_sent......................: 2.6 MB  21 kB/s
     http_req_blocked...............: avg=868.16µs min=0s          med=38µs   max=202.59ms p(90)=141.8µs p(95)=329.5µs
     http_req_connecting............: avg=278.76µs min=0s          med=0s     max=131.46ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=2.2s     min=6.98ms      med=2.51s  max=11.49s   p(90)=3.44s   p(95)=5.35s
       { expected_response:true }...: avg=2.2s     min=6.98ms      med=2.51s  max=11.49s   p(90)=3.44s   p(95)=5.35s
     http_req_failed................: 0.00%   ✓ 0         ✗ 8751
     http_req_receiving.............: avg=535.31µs min=76.2µs      med=247µs  max=201.75ms p(90)=766.1µs p(95)=1.28ms
     http_req_sending...............: avg=202.77µs min=25.1µs      med=76.2µs max=69.21ms  p(90)=277.6µs p(95)=441.25µs
     http_req_tls_handshaking.......: avg=486.1µs  min=-17540600ns med=0s     max=169.96ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=2.2s     min=6.71ms      med=2.51s  max=11.49s   p(90)=3.44s   p(95)=5.35s
     http_reqs......................: 8751    70.477268/s
     iteration_duration.............: avg=7.62s    min=1.08s       med=7.72s  max=23.73s   p(90)=12.24s  p(95)=13.35s
     iterations.....................: 2917    23.492423/s
     vus............................: 16      min=4       max=240
     vus_max........................: 240     min=240     max=240
```
