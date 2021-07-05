### load - before
```shell script

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 150 max VUs, 2m30s max duration (incl. graceful stop):
           * peakTimeTest: 150 looping VUs for 2m0s (gracefulStop: 30s)


running (2m12.6s), 000/150 VUs, 1227 complete and 0 interrupted iterations
peakTimeTest ✓ [======================================] 150 VUs  2m0s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ information updated
     ✓ retrieved path
     ✓ retrieved lines
     ✓ retrieved stations

     DURATION TIME - information updated......: avg=2.02s    min=6.18ms   med=2.17s  max=6.8s     p(90)=3.03s  p(95)=3.17s
     DURATION TIME - logged in successfully...: avg=1.92s    min=22.03ms  med=2s     max=6.34s    p(90)=2.91s  p(95)=3.13s
     DURATION TIME - retrieved lines..........: avg=3.14s    min=276.65ms med=2.94s  max=6.75s    p(90)=5.19s  p(95)=5.72s
     DURATION TIME - retrieved member.........: avg=1.97s    min=7.08ms   med=2.17s  max=6.42s    p(90)=3.1s   p(95)=3.24s
     DURATION TIME - retrieved path...........: avg=2.73s    min=283.54ms med=2.66s  max=7.23s    p(90)=3.54s  p(95)=3.95s
     DURATION TIME - retrieved stations.......: avg=2.72s    min=35.28ms  med=2.55s  max=5.75s    p(90)=3.66s  p(95)=4.88s
     checks...................................: 100.00% ✓ 7362      ✗ 0
     data_received............................: 148 MB  1.1 MB/s
     data_sent................................: 1.7 MB  13 kB/s
     http_req_blocked.........................: avg=2.85ms   min=1.6µs    med=2.6µs  max=227.82ms p(90)=4.5µs  p(95)=5.4µs
     http_req_connecting......................: avg=278.38µs min=0s       med=0s     max=18.12ms  p(90)=0s     p(95)=0s
   ✗ http_req_duration........................: avg=2.42s    min=6.18ms   med=2.4s   max=7.23s    p(90)=3.46s  p(95)=3.86s
       { expected_response:true }.............: avg=2.42s    min=6.18ms   med=2.4s   max=7.23s    p(90)=3.46s  p(95)=3.86s
     http_req_failed..........................: 0.00%   ✓ 0         ✗ 7362
     http_req_receiving.......................: avg=935.44µs min=13.89µs  med=44.5µs max=40.25ms  p(90)=2.51ms p(95)=2.89ms
     http_req_sending.........................: avg=12.43µs  min=5.5µs    med=10.3µs max=355.9µs  p(90)=18.7µs p(95)=23.2µs
     http_req_tls_handshaking.................: avg=2.28ms   min=0s       med=0s     max=195.15ms p(90)=0s     p(95)=0s
     http_req_waiting.........................: avg=2.42s    min=6.14ms   med=2.4s   max=7.23s    p(90)=3.46s  p(95)=3.86s
     http_reqs................................: 7362    55.514399/s
     iteration_duration.......................: avg=15.54s   min=7.36s    med=15.88s max=19.56s   p(90)=16.83s p(95)=17.19s
     iterations...............................: 1227    9.2524/s
     vus......................................: 27      min=27      max=150
     vus_max..................................: 150     min=150     max=150

ERRO[0134] some thresholds have failed
```
