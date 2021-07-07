### load - before
```shell script

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 8 scenarios, 280 max VUs, 4m30s max duration (incl. graceful stop):
           * testWithVUs10: 10 looping VUs for 30s (gracefulStop: 30s)
           * testWithVUs30: 30 looping VUs for 30s (startTime: 30s, gracefulStop: 30s)
           * testWithVUs50: 50 looping VUs for 30s (startTime: 1m0s, gracefulStop: 30s)
           * testWithVUs70: 70 looping VUs for 30s (startTime: 1m30s, gracefulStop: 30s)
           * testWithVUs90: 90 looping VUs for 30s (startTime: 2m0s, gracefulStop: 30s)
           * testWithVUs110: 110 looping VUs for 30s (startTime: 2m30s, gracefulStop: 30s)
           * testWithVUs130: 130 looping VUs for 30s (startTime: 3m0s, gracefulStop: 30s)
           * testWithVUs150: 150 looping VUs for 30s (startTime: 3m30s, gracefulStop: 30s)


running (4m12.3s), 000/280 VUs, 2271 complete and 0 interrupted iterations
testWithVUs10  ✓ [======================================] 10 VUs   30s
testWithVUs30  ✓ [======================================] 30 VUs   30s
testWithVUs50  ✓ [======================================] 50 VUs   30s
testWithVUs70  ✓ [======================================] 70 VUs   30s
testWithVUs90  ✓ [======================================] 90 VUs   30s
testWithVUs110 ✓ [======================================] 110 VUs  30s
testWithVUs130 ✓ [======================================] 130 VUs  30s
testWithVUs150 ✓ [======================================] 150 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ information updated
     ✓ retrieved path
     ✓ retrieved lines
     ✓ retrieved stations

     DURATION TIME - information updated......: avg=965.07ms min=5.65ms  med=651.25ms max=7.62s    p(90)=2.35s   p(95)=2.99s
     DURATION TIME - logged in successfully...: avg=1.18s    min=4.49ms  med=836.08ms max=7.03s    p(90)=2.93s   p(95)=3.39s
     DURATION TIME - retrieved lines..........: avg=2.43s    min=67.78ms med=1.87s    max=7.48s    p(90)=5.55s   p(95)=6.17s
     DURATION TIME - retrieved member.........: avg=898.99ms min=5.85ms  med=544.12ms max=5.14s    p(90)=2.15s   p(95)=2.56s
     DURATION TIME - retrieved path...........: avg=1.86s    min=65.25ms med=1.42s    max=8.7s     p(90)=4.1s    p(95)=5.15s
     DURATION TIME - retrieved stations.......: avg=1.96s    min=31.92ms med=1.45s    max=6.26s    p(90)=4.44s   p(95)=5.33s
     checks...................................: 100.00% ✓ 13626     ✗ 0
     data_received............................: 274 MB  1.1 MB/s
     data_sent................................: 3.3 MB  13 kB/s
     http_req_blocked.........................: avg=1.59ms   min=1.6µs   med=2.6µs    max=146.41ms p(90)=4.4µs   p(95)=5.6µs
     http_req_connecting......................: avg=160.32µs min=0s      med=0s       max=11.07ms  p(90)=0s      p(95)=0s
   ✗ http_req_duration........................: avg=1.55s    min=4.49ms  med=1.13s    max=8.7s     p(90)=3.83s   p(95)=4.83s
       { expected_response:true }.............: avg=1.55s    min=4.49ms  med=1.13s    max=8.7s     p(90)=3.83s   p(95)=4.83s
     http_req_failed..........................: 0.00%   ✓ 0         ✗ 13626
     http_req_receiving.......................: avg=1ms      min=13.6µs  med=44.25µs  max=38.11ms  p(90)=2.63ms  p(95)=3.34ms
     http_req_sending.........................: avg=11.83µs  min=5.1µs   med=10µs     max=1.02ms   p(90)=17.95µs p(95)=22.8µs
     http_req_tls_handshaking.................: avg=1.39ms   min=0s      med=0s       max=135.72ms p(90)=0s      p(95)=0s
     http_req_waiting.........................: avg=1.55s    min=4.46ms  med=1.13s    max=8.7s     p(90)=3.83s   p(95)=4.83s
     http_reqs................................: 13626   54.010648/s
     iteration_duration.......................: avg=10.32s   min=1.18s   med=9.21s    max=26.13s   p(90)=21.98s  p(95)=23.88s
     iterations...............................: 2271    9.001775/s
     vus......................................: 18      min=10      max=277
     vus_max..................................: 280     min=280     max=280

ERRO[0253] some thresholds have failed
```
