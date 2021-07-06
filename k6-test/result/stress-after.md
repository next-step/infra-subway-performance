### stress - after
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


running (4m01.0s), 000/280 VUs, 17475 complete and 0 interrupted iterations
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

     DURATION TIME - information updated......: avg=16.54ms  min=5.59ms med=10.4ms  max=1.05s    p(90)=28.24ms p(95)=46.6ms
     DURATION TIME - logged in successfully...: avg=13.75ms  min=4.45ms med=8.24ms  max=430.79ms p(90)=24.54ms p(95)=42.88ms
     DURATION TIME - retrieved lines..........: avg=24.1ms   min=6.21ms med=11.83ms max=285.81ms p(90)=56.84ms p(95)=85.43ms
     DURATION TIME - retrieved member.........: avg=19.94ms  min=5.71ms med=10.76ms max=1.06s    p(90)=30.84ms p(95)=55.31ms
     DURATION TIME - retrieved path...........: avg=16.18ms  min=2.53ms med=6.33ms  max=256.45ms p(90)=45.14ms p(95)=67.05ms
     DURATION TIME - retrieved stations.......: avg=25.33ms  min=5.6ms  med=11.54ms max=1.04s    p(90)=63.38ms p(95)=93.54ms
     checks...................................: 100.00% ✓ 104850     ✗ 0
     data_received............................: 2.1 GB  8.7 MB/s
     data_sent................................: 21 MB   88 kB/s
     http_req_blocked.........................: avg=302.21µs min=1.2µs  med=1.4µs   max=250.96ms p(90)=1.6µs   p(95)=1.8µs
     http_req_connecting......................: avg=21.85µs  min=0s     med=0s      max=13.48ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration........................: avg=19.31ms  min=2.53ms med=10.07ms max=1.06s    p(90)=41.81ms p(95)=68.38ms
       { expected_response:true }.............: avg=19.31ms  min=2.53ms med=10.07ms max=1.06s    p(90)=41.81ms p(95)=68.38ms
     http_req_failed..........................: 0.00%   ✓ 0          ✗ 104850
     http_req_receiving.......................: avg=2.15ms   min=8.69µs med=49.7µs  max=239.31ms p(90)=4.44ms  p(95)=6.48ms
     http_req_sending.........................: avg=38.88µs  min=17.8µs med=30.7µs  max=3.8ms    p(90)=59.7µs  p(95)=68.4µs
     http_req_tls_handshaking.................: avg=268.02µs min=0s     med=0s      max=240.84ms p(90)=0s      p(95)=0s
     http_req_waiting.........................: avg=17.11ms  min=2.46ms med=8.85ms  max=1.06s    p(90)=37.31ms p(95)=62.11ms
     http_reqs................................: 104850  434.980019/s
     iteration_duration.......................: avg=1.11s    min=1.03s  med=1.06s   max=2.24s    p(90)=1.25s   p(95)=1.37s
     iterations...............................: 17475   72.49667/s
     vus......................................: 6       min=6        max=224
     vus_max..................................: 280     min=280      max=280

```
