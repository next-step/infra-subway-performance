#개선전
```shell
     ✗ Entered main screen successfully
      ↳  24% — ✓ 4138 / ✗ 13080
     ✗ Logged in successfully
      ↳  25% — ✓ 4376 / ✗ 12842
     ✓ Found shortest path successfully

     checks.........................: 31.87% ✓ 12126      ✗ 25922
     data_received..................: 52 MB  307 kB/s
     data_sent......................: 12 MB  70 kB/s
     http_req_blocked...............: avg=27.72ms  min=0s       med=0s       max=485.45ms p(90)=125.69ms p(95)=181.97ms
     http_req_connecting............: avg=21.91ms  min=0s       med=11.78ms  max=290.07ms p(90)=60.55ms  p(95)=79.71ms
   ✗ http_req_duration..............: avg=774.06ms min=0s       med=0s       max=24.21s   p(90)=3.93s    p(95)=4.83s
       { expected_response:true }...: avg=2.47s    min=906.56µs med=1.72s    max=24.21s   p(90)=5.25s    p(95)=6.03s
     http_req_failed................: 68.83% ✓ 26718      ✗ 12094
     http_req_receiving.............: avg=824.07µs min=0s       med=0s       max=284.07ms p(90)=92.41µs  p(95)=363.17µs
     http_req_sending...............: avg=1.92ms   min=0s       med=0s       max=479.25ms p(90)=1.43ms   p(95)=9.73ms
     http_req_tls_handshaking.......: avg=21.37ms  min=0s       med=0s       max=431.91ms p(90)=95.84ms  p(95)=139.2ms
     http_req_waiting...............: avg=771.31ms min=0s       med=0s       max=24.21s   p(90)=3.93s    p(95)=4.82s
     http_reqs......................: 38812  229.928389/s
     iteration_duration.............: avg=1.88s    min=2.22ms   med=199.91ms max=29.6s    p(90)=8.15s    p(95)=9.69s
     iterations.....................: 17218  102.002139/s
     vus............................: 25     min=4        max=300
     vus_max........................: 300    min=300      max=300

ERRO[0170] some thresholds have failed
```

#Reverse Proxy 개선후
```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 3m15s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 2m45s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m49.9s), 000/300 VUs, 4098 complete and 0 interrupted iterations
default ↓ [======================================] 124/300 VUs  2m45s

     ✓ Entered main screen successfully
     ✓ Logged in successfully
     ✓ Found shortest path successfully

     checks.........................: 100.00% ✓ 12294     ✗ 0
     data_received..................: 9.3 MB  55 kB/s
     data_sent......................: 1.6 MB  9.5 kB/s
     http_req_blocked...............: avg=137.53µs min=2.15µs   med=2.89µs  max=40.02ms p(90)=3.19µs   p(95)=20.83µs
     http_req_connecting............: avg=17.43µs  min=0s       med=0s      max=18.08ms p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=2.68s    min=974.47µs med=2.11s   max=21.22s  p(90)=5.39s    p(95)=5.81s
       { expected_response:true }...: avg=2.68s    min=974.47µs med=2.11s   max=21.22s  p(90)=5.39s    p(95)=5.81s
     http_req_failed................: 0.00%   ✓ 0         ✗ 12294
     http_req_receiving.............: avg=97.88µs  min=37.36µs  med=76.61µs max=6.52ms  p(90)=125.17µs p(95)=150.04µs
     http_req_sending...............: avg=95.15µs  min=19.24µs  med=53.73µs max=12.21ms p(90)=145.9µs  p(95)=211.21µs
     http_req_tls_handshaking.......: avg=107.37µs min=0s       med=0s      max=29.83ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=2.68s    min=135.39µs med=2.11s   max=21.22s  p(90)=5.39s    p(95)=5.81s
     http_reqs......................: 12294   72.377375/s
     iteration_duration.............: avg=8.04s    min=93.97ms  med=8.81s   max=27.01s  p(90)=12.38s   p(95)=13.5s
     iterations.....................: 4098    24.125792/s
     vus............................: 27      min=4       max=300
     vus_max........................: 300     min=300     max=300

ERRO[0171] some thresholds have failed
```

#Redis를 이용하여 Cache 적용 후
```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 3m15s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 2m45s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m45.0s), 000/300 VUs, 53815 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  2m45s

     ✓ Entered main screen successfully
     ✓ Logged in successfully
     ✓ Found shortest path successfully

     checks.........................: 100.00% ✓ 161445     ✗ 0
     data_received..................: 105 MB  638 kB/s
     data_sent......................: 19 MB   116 kB/s
     http_req_blocked...............: avg=779.77µs min=2.13µs  med=2.82µs   max=1.27s    p(90)=3.07µs   p(95)=3.34µs
     http_req_connecting............: avg=207.22µs min=0s      med=0s       max=424.77ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=181.37ms min=52.93µs med=166.45ms max=1.66s    p(90)=341.73ms p(95)=404.34ms
       { expected_response:true }...: avg=181.37ms min=52.93µs med=166.45ms max=1.66s    p(90)=341.73ms p(95)=404.34ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 161445
     http_req_receiving.............: avg=1.83ms   min=28.33µs med=50.18µs  max=587.56ms p(90)=588.16µs p(95)=1.86ms
     http_req_sending...............: avg=1.22ms   min=0s      med=39.41µs  max=478.25ms p(90)=300.61µs p(95)=1.09ms
     http_req_tls_handshaking.......: avg=508.31µs min=0s      med=0s       max=953.22ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=178.31ms min=0s      med=163.92ms max=1.65s    p(90)=335.87ms p(95)=399.28ms
     http_reqs......................: 161445  978.433932/s
     iteration_duration.............: avg=572.62ms min=10.15ms med=579.24ms max=2.79s    p(90)=988.46ms p(95)=1.14s
     iterations.....................: 53815   326.144644/s
     vus............................: 1       min=1        max=300
     vus_max........................: 300     min=300      max=300
```
