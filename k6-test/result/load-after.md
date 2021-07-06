### load - after
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


running (2m01.2s), 000/150 VUs, 12330 complete and 0 interrupted iterations
peakTimeTest ✓ [======================================] 150 VUs  2m0s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ information updated
     ✓ retrieved path
     ✓ retrieved lines
     ✓ retrieved stations

     DURATION TIME - information updated......: avg=40.09ms  min=6.35ms med=25.22ms max=475.15ms p(90)=83.5ms   p(95)=120.28ms
     DURATION TIME - logged in successfully...: avg=42.09ms  min=4.9ms  med=25.27ms max=674.58ms p(90)=97.87ms  p(95)=134.56ms
     DURATION TIME - retrieved lines..........: avg=109.56ms min=6.51ms med=86.1ms  max=747.31ms p(90)=224.85ms p(95)=276.24ms
     DURATION TIME - retrieved member.........: avg=47.55ms  min=6.46ms med=30.22ms max=457.12ms p(90)=105.42ms p(95)=143.02ms
     DURATION TIME - retrieved path...........: avg=95.84ms  min=2.79ms med=73.09ms max=651.6ms  p(90)=207.82ms p(95)=254.92ms
     DURATION TIME - retrieved stations.......: avg=129.39ms min=6.04ms med=98.14ms max=975.11ms p(90)=266.08ms p(95)=334.54ms
     checks...................................: 100.00% ✓ 73980      ✗ 0
     data_received............................: 1.5 GB  12 MB/s
     data_sent................................: 16 MB   128 kB/s
     http_req_blocked.........................: avg=329.13µs min=1.2µs  med=1.4µs   max=280.86ms p(90)=1.6µs    p(95)=1.8µs
     http_req_connecting......................: avg=27.88µs  min=0s     med=0s      max=27.72ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration........................: avg=77.42ms  min=2.79ms med=46.27ms max=975.11ms p(90)=188.53ms p(95)=242.52ms
       { expected_response:true }.............: avg=77.42ms  min=2.79ms med=46.27ms max=975.11ms p(90)=188.53ms p(95)=242.52ms
     http_req_failed..........................: 0.00%   ✓ 0          ✗ 73980
     http_req_receiving.......................: avg=7.24ms   min=8.69µs med=46.3µs  max=460.29ms p(90)=18.72ms  p(95)=45.8ms
     http_req_sending.........................: avg=36.81µs  min=18µs   med=29.4µs  max=5.62ms   p(90)=56.09µs  p(95)=63.6µs
     http_req_tls_handshaking.................: avg=264.08µs min=0s     med=0s      max=234.22ms p(90)=0s       p(95)=0s
     http_req_waiting.........................: avg=70.13ms  min=2.71ms med=41.21ms max=916.01ms p(90)=172.07ms p(95)=222.9ms
     http_reqs................................: 73980   610.617823/s
     iteration_duration.......................: avg=1.46s    min=1.03s  med=1.39s   max=3.91s    p(90)=1.82s    p(95)=1.99s
     iterations...............................: 12330   101.769637/s
     vus......................................: 32      min=32       max=150
     vus_max..................................: 150     min=150      max=150

```
