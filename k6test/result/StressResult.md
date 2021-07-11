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


running (2m45.0s), 000/300 VUs, 65949 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  2m45s

     ✓ Entered main screen successfully
     ✓ Logged in successfully
     ✓ Found shortest path successfully

     checks.........................: 100.00% ✓ 197847     ✗ 0
     data_received..................: 130 MB  785 kB/s
     data_sent......................: 23 MB   142 kB/s
     http_req_blocked...............: avg=708.94µs min=2.01µs  med=2.77µs   max=943.84ms p(90)=3.02µs   p(95)=3.52µs
     http_req_connecting............: avg=207.95µs min=0s      med=0s       max=382.55ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=146.57ms min=54.05µs med=145.19ms max=1.28s    p(90)=250.04ms p(95)=282.36ms
       { expected_response:true }...: avg=146.57ms min=54.05µs med=145.19ms max=1.28s    p(90)=250.04ms p(95)=282.36ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 197847
     http_req_receiving.............: avg=1.84ms   min=27.57µs med=49.86µs  max=592.4ms  p(90)=693.69µs p(95)=1.84ms
     http_req_sending...............: avg=1.18ms   min=0s      med=40.23µs  max=445.63ms p(90)=408.15µs p(95)=1.15ms
     http_req_tls_handshaking.......: avg=432.93µs min=0s      med=0s       max=852.38ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=143.54ms min=0s      med=143.01ms max=1.28s    p(90)=245.43ms p(95)=275.66ms
     http_reqs......................: 197847  1199.02829/s
     iteration_duration.............: avg=467.14ms min=9.63ms  med=491.95ms max=1.99s    p(90)=735.43ms p(95)=806.26ms
     iterations.....................: 65949   399.676097/s
     vus............................: 1       min=1        max=300
     vus_max........................: 300     min=300      max=300
```
###스트레스 테스트의 강도 ↑
```shell

running (2m45.0s), 0000/1200 VUs, 59636 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1200 VUs  2m45s

     ✗ Entered main screen successfully
      ↳  96% — ✓ 57687 / ✗ 1949
     ✗ Logged in successfully
      ↳  96% — ✓ 57687 / ✗ 1949
     ✓ Found shortest path successfully

     checks.........................: 97.79% ✓ 173061      ✗ 3898
     data_received..................: 117 MB 706 kB/s
     data_sent......................: 21 MB  127 kB/s
     http_req_blocked...............: avg=7.24ms   min=0s       med=2.8µs    max=3.21s p(90)=3.09µs   p(95)=4.2µs
     http_req_connecting............: avg=2.41ms   min=0s       med=0s       max=1.7s  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=587.89ms min=0s       med=627.11ms max=3.12s p(90)=903.13ms p(95)=957.62ms
       { expected_response:true }...: avg=601.13ms min=72.85µs  med=638.46ms max=3.12s p(90)=904.91ms p(95)=959.74ms
     http_req_failed................: 2.20%  ✓ 3898        ✗ 173061
     http_req_receiving.............: avg=4.26ms   min=0s       med=53.61µs  max=1.59s p(90)=488.55µs p(95)=1.69ms
     http_req_sending...............: avg=5.39ms   min=0s       med=46.85µs  max=1.27s p(90)=476.12µs p(95)=1.41ms
     http_req_tls_handshaking.......: avg=4.56ms   min=0s       med=0s       max=2.35s p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=578.24ms min=0s       med=618.93ms max=2.06s p(90)=895.4ms  p(95)=945.31ms
     http_reqs......................: 176959 1072.416934/s
     iteration_duration.............: avg=2.34s    min=323.62µs med=2.43s    max=9.24s p(90)=3.42s    p(95)=3.77s
     iterations.....................: 59636  361.409458/s
     vus............................: 1      min=1         max=1200
     vus_max........................: 1200   min=1200      max=1200
```