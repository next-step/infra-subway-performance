#개선전
```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 1m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 1m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m10.1s), 000/100 VUs, 1688 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  1m10s

     ✓ Entered main screen successfully
     ✓ Logged in successfully
     ✓ Found shortest path successfully

     checks.........................: 100.00% ✓ 5064      ✗ 0
     data_received..................: 4.1 MB  59 kB/s
     data_sent......................: 874 kB  13 kB/s
     http_req_blocked...............: avg=111.52µs min=3.53µs   med=4.86µs   max=29.71ms p(90)=6.24µs   p(95)=8.6µs
     http_req_connecting............: avg=12.1µs   min=0s       med=0s       max=3.12ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=716.86ms min=960.03µs med=534.32ms max=3.62s   p(90)=1.8s     p(95)=2.03s
       { expected_response:true }...: avg=716.86ms min=960.03µs med=534.32ms max=3.62s   p(90)=1.8s     p(95)=2.03s
     http_req_failed................: 0.00%   ✓ 0         ✗ 5064
     http_req_receiving.............: avg=76.48µs  min=26.96µs  med=65.07µs  max=5.87ms  p(90)=104.35µs p(95)=120.08µs
     http_req_sending...............: avg=23.58µs  min=10.81µs  med=17.94µs  max=7.65ms  p(90)=24.93µs  p(95)=36.34µs
     http_req_tls_handshaking.......: avg=91.53µs  min=0s       med=0s       max=28.43ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=716.76ms min=904.61µs med=534.22ms max=3.62s   p(90)=1.8s     p(95)=2.03s
     http_reqs......................: 5064    72.28343/s
     iteration_duration.............: avg=2.15s    min=95.31ms  med=2.14s    max=5.79s   p(90)=3.84s    p(95)=4s
     iterations.....................: 1688    24.094477/s
     vus............................: 1       min=1       max=99
     vus_max........................: 100     min=100     max=100
```

#Reverse Proxy 개선후
```shell
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 1m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 1m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m10.1s), 000/100 VUs, 1680 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  1m10s

     ✓ Entered main screen successfully
     ✓ Logged in successfully
     ✓ Found shortest path successfully

     checks.........................: 100.00% ✓ 5040      ✗ 0
     data_received..................: 3.7 MB  53 kB/s
     data_sent......................: 651 kB  9.3 kB/s
     http_req_blocked...............: avg=113.93µs min=2.32µs   med=2.9µs    max=41.07ms p(90)=3.18µs   p(95)=13.45µs
     http_req_connecting............: avg=12.69µs  min=0s       med=0s       max=3.52ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=721.77ms min=990.09µs med=495.1ms  max=3.81s   p(90)=1.89s    p(95)=2.21s
       { expected_response:true }...: avg=721.77ms min=990.09µs med=495.1ms  max=3.81s   p(90)=1.89s    p(95)=2.21s
     http_req_failed................: 0.00%   ✓ 0         ✗ 5040
     http_req_receiving.............: avg=88.14µs  min=38.52µs  med=73.96µs  max=4.67ms  p(90)=118.38µs p(95)=135.25µs
     http_req_sending...............: avg=90.19µs  min=21.39µs  med=52.01µs  max=4.65ms  p(90)=148.56µs p(95)=190.91µs
     http_req_tls_handshaking.......: avg=89.25µs  min=0s       med=0s       max=28.42ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=721.6ms  min=6.6µs    med=494.97ms max=3.81s   p(90)=1.89s    p(95)=2.21s
     http_reqs......................: 5040    71.943121/s
     iteration_duration.............: avg=2.16s    min=96.22ms  med=2.09s    max=6.17s   p(90)=3.87s    p(95)=4.04s
     iterations.....................: 1680    23.98104/s
     vus............................: 1       min=1       max=100
     vus_max........................: 100     min=100     max=100

ERRO[0071] some thresholds have failed
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