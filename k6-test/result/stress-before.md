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


running (4m05.9s), 000/280 VUs, 5363 complete and 0 interrupted iterations
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

     checks.........................: 100.00% ✓ 21452     ✗ 0
     data_received..................: 20 MB   80 kB/s
     data_sent......................: 6.1 MB  25 kB/s
     http_req_blocked...............: avg=1.16ms   min=1.6µs  med=2.4µs    max=166.48ms p(90)=4.5µs   p(95)=5.3µs
     http_req_connecting............: avg=98.23µs  min=0s     med=0s       max=10.65ms  p(90)=0s      p(95)=0s
   ✗ http_req_duration..............: avg=715.33ms min=4.71ms med=534.4ms  max=4.99s    p(90)=1.64s   p(95)=2.13s
       { expected_response:true }...: avg=715.33ms min=4.71ms med=534.4ms  max=4.99s    p(90)=1.64s   p(95)=2.13s
     http_req_failed................: 0.00%   ✓ 0         ✗ 21452
     http_req_receiving.............: avg=37.38µs  min=12.4µs med=31.9µs   max=1.02ms   p(90)=55.49µs p(95)=65.4µs
     http_req_sending...............: avg=11.75µs  min=4.89µs med=9.4µs    max=1.08ms   p(90)=19.5µs  p(95)=23.1µs
     http_req_tls_handshaking.......: avg=1.05ms   min=0s     med=0s       max=156.35ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=715.28ms min=4.67ms med=534.31ms max=4.99s    p(90)=1.64s   p(95)=2.13s
     http_reqs......................: 21452   87.243249/s
     iteration_duration.............: avg=3.86s    min=1.08s  med=3.77s    max=11.86s   p(90)=6.31s   p(95)=7.23s
     iterations.....................: 5363    21.810812/s
     vus............................: 28      min=10      max=258
     vus_max........................: 280     min=280     max=280

ERRO[0247] some thresholds have failed
```
