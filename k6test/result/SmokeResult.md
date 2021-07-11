#개선전

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


running (10.0s), 0/1 VUs, 108 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ Entered main screen successfully
     ✓ Logged in successfully
     ✓ Found shortest path successfully

     checks.........................: 100.00% ✓ 324       ✗ 0
     data_received..................: 240 kB  24 kB/s
     data_sent......................: 54 kB   5.4 kB/s
     http_req_blocked...............: avg=131.11µs min=4.14µs  med=4.87µs  max=40.77ms  p(90)=6.09µs   p(95)=6.33µs
     http_req_connecting............: avg=1.78µs   min=0s      med=0s      max=578.86µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=30.65ms  min=1.17ms  med=6.72ms  max=92.26ms  p(90)=84.1ms   p(95)=85.5ms
       { expected_response:true }...: avg=30.65ms  min=1.17ms  med=6.72ms  max=92.26ms  p(90)=84.1ms   p(95)=85.5ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 324
     http_req_receiving.............: avg=81.29µs  min=39.47µs med=78.6µs  max=164.1µs  p(90)=112.19µs p(95)=120.07µs
     http_req_sending...............: avg=19.57µs  min=11.68µs med=18.21µs max=74.8µs   p(90)=29.21µs  p(95)=31.67µs
     http_req_tls_handshaking.......: avg=87.92µs  min=0s      med=0s      max=28.48ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=30.55ms  min=1.07ms  med=6.62ms  max=92.11ms  p(90)=84ms     p(95)=85.36ms
     http_reqs......................: 324     32.289659/s
     iteration_duration.............: avg=92.88ms  min=89.33ms med=92.13ms max=139.16ms p(90)=95.01ms  p(95)=96.98ms
     iterations.....................: 108     10.76322/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1
```

#Reverse Proxy 개선후
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


running (10.1s), 0/1 VUs, 105 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ Entered main screen successfully
     ✓ Logged in successfully
     ✓ Found shortest path successfully

     checks.........................: 100.00% ✓ 315       ✗ 0
     data_received..................: 207 kB  21 kB/s
     data_sent......................: 38 kB   3.7 kB/s
     http_req_blocked...............: avg=131.61µs min=2.67µs  med=2.87µs  max=40.34ms  p(90)=3.07µs  p(95)=3.19µs
     http_req_connecting............: avg=1.68µs   min=0s      med=0s      max=531.62µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=31.56ms  min=1.18ms  med=7.03ms  max=142.96ms p(90)=85.62ms p(95)=87.91ms
       { expected_response:true }...: avg=31.56ms  min=1.18ms  med=7.03ms  max=142.96ms p(90)=85.62ms p(95)=87.91ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 315
     http_req_receiving.............: avg=82.91µs  min=49.39µs med=82.45µs max=178.05µs p(90)=107.3µs p(95)=114.37µs
     http_req_sending...............: avg=63.51µs  min=30.54µs med=40.62µs max=426.87µs p(90)=110µs   p(95)=136.42µs
     http_req_tls_handshaking.......: avg=88.59µs  min=0s      med=0s      max=27.9ms   p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=31.41ms  min=1.05ms  med=6.84ms  max=142.83ms p(90)=85.49ms p(95)=87.77ms
     http_reqs......................: 315     31.34251/s
     iteration_duration.............: avg=95.68ms  min=88.82ms med=93.67ms max=157.75ms p(90)=99.79ms p(95)=103.54ms
     iterations.....................: 105     10.447503/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1
```

#Redis를 이용하여 Cache 적용 후
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


running (10.0s), 0/1 VUs, 863 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ Entered main screen successfully
     ✓ Logged in successfully
     ✓ Found shortest path successfully

     checks.........................: 100.00% ✓ 2589       ✗ 0
     data_received..................: 1.7 MB  168 kB/s
     data_sent......................: 306 kB  31 kB/s
     http_req_blocked...............: avg=19.25µs min=2.63µs  med=2.82µs  max=29.52ms  p(90)=3.03µs   p(95)=3.21µs
     http_req_connecting............: avg=571ns   min=0s      med=0s      max=521.37µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=3.65ms  min=1.05ms  med=2.12ms  max=19.44ms  p(90)=7.45ms   p(95)=7.71ms
       { expected_response:true }...: avg=3.65ms  min=1.05ms  med=2.12ms  max=19.44ms  p(90)=7.45ms   p(95)=7.71ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 2589
     http_req_receiving.............: avg=69.74µs min=46.53µs med=64.36µs max=637.2µs  p(90)=89.19µs  p(95)=99.72µs
     http_req_sending...............: avg=63.56µs min=23.94µs med=39.68µs max=3.1ms    p(90)=117.67µs p(95)=138.53µs
     http_req_tls_handshaking.......: avg=13.89µs min=0s      med=0s      max=28.36ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=3.52ms  min=948.3µs med=2ms     max=19.26ms  p(90)=7.27ms   p(95)=7.52ms
     http_reqs......................: 2589    258.828946/s
     iteration_duration.............: avg=11.57ms min=9.71ms  med=11.23ms max=50.41ms  p(90)=12.46ms  p(95)=13.53ms
     iterations.....................: 863     86.276315/s
     vus............................: 0       min=0        max=1
     vus_max........................: 1       min=1        max=1
```
