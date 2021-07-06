# 최적화 반영 전
```text
// load.js
running (1m41.0s), 000/300 VUs, 28444 complete and 0 interrupted iterations
default ✓ [======================================] 300 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 56888      ✗ 0
     data_received..................: 13 MB   127 kB/s
     data_sent......................: 13 MB   127 kB/s
     http_req_blocked...............: avg=331.89µs min=2.94µs   med=4.42µs  max=335.3ms  p(90)=6.24µs   p(95)=10.86µs
     http_req_connecting............: avg=310.83µs min=0s       med=0s      max=335.24ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=26.81ms  min=869.36µs med=2.17ms  max=1.86s    p(90)=43.72ms  p(95)=119.01ms
       { expected_response:true }...: avg=26.81ms  min=869.36µs med=2.17ms  max=1.86s    p(90)=43.72ms  p(95)=119.01ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 56888
     http_req_receiving.............: avg=735.28µs min=16.09µs  med=47.5µs  max=577.64ms p(90)=189.44µs p(95)=432.79µs
     http_req_sending...............: avg=306.62µs min=8.55µs   med=14.95µs max=494.37ms p(90)=40.36µs  p(95)=156.53µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=25.77ms  min=794.16µs med=2ms     max=1.86s    p(90)=40.57ms  p(95)=113.58ms
     http_reqs......................: 56888   563.273188/s
     iteration_duration.............: avg=1.05s    min=1s       med=1s      max=3.44s    p(90)=1.12s    p(95)=1.26s
     iterations.....................: 28444   281.636594/s
     vus............................: 0       min=0        max=300
     vus_max........................: 300     min=300      max=300
```

```text
// smoke.js
running (1m40.4s), 0/1 VUs, 100 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 200      ✗ 0
     data_received..................: 45 kB   450 B/s
     data_sent......................: 45 kB   450 B/s
     http_req_blocked...............: avg=11.46µs min=3.8µs    med=6.84µs  max=596.72µs p(90)=7.87µs  p(95)=8.16µs
     http_req_connecting............: avg=4.87µs  min=0s       med=0s      max=526.61µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=1.35ms  min=1.02ms   med=1.41ms  max=2.16ms   p(90)=1.59ms  p(95)=1.68ms
       { expected_response:true }...: avg=1.35ms  min=1.02ms   med=1.41ms  max=2.16ms   p(90)=1.59ms  p(95)=1.68ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 200
     http_req_receiving.............: avg=87.93µs min=39.09µs  med=79.79µs max=259.83µs p(90)=119.6µs p(95)=169.64µs
     http_req_sending...............: avg=22.8µs  min=12µs     med=26.31µs max=85.85µs  p(90)=30.03µs p(95)=37.78µs
     http_req_tls_handshaking.......: avg=0s      min=0s       med=0s      max=0s       p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=1.24ms  min=934.18µs med=1.29ms  max=2.06ms   p(90)=1.47ms  p(95)=1.55ms
     http_reqs......................: 200     1.992402/s
     iteration_duration.............: avg=1s      min=1s       med=1s      max=1s       p(90)=1s      p(95)=1s
     iterations.....................: 100     0.996201/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
     
```

```text
// stress.js
running (1m28.0s), 0000/1100 VUs, 56697 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1100 VUs  1m27s

     ✗ logged in successfully
      ↳  75% — ✓ 42975 / ✗ 13722
     ✓ retrieved member

     checks.........................: 86.23% ✓ 85949       ✗ 13722
     data_received..................: 19 MB  220 kB/s
     data_sent......................: 19 MB  221 kB/s
     http_req_blocked...............: avg=691.86µs min=0s       med=4.23µs  max=559.95ms p(90)=6.58µs   p(95)=19.68µs
     http_req_connecting............: avg=625.62µs min=0s       med=0s      max=559.9ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=62.61ms  min=0s       med=5.36ms  max=976.51ms p(90)=222.4ms  p(95)=295.14ms
       { expected_response:true }...: avg=72.6ms   min=808.45µs med=12.59ms max=976.51ms p(90)=239.79ms p(95)=309.38ms
     http_req_failed................: 13.76% ✓ 13723       ✗ 85949
     http_req_receiving.............: avg=824.51µs min=0s       med=32.27µs max=504.41ms p(90)=127.4µs  p(95)=353.63µs
     http_req_sending...............: avg=2.35ms   min=0s       med=13.94µs max=657.9ms  p(90)=82.07µs  p(95)=1.88ms
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=59.42ms  min=0s       med=4.92ms  max=831.41ms p(90)=214.13ms p(95)=282.59ms
     http_reqs......................: 99672  1132.896511/s
     iteration_duration.............: avg=899.66ms min=151.43µs med=1.01s   max=2.69s    p(90)=1.5s     p(95)=1.66s
     iterations.....................: 56697  644.432072/s
     vus............................: 68     min=50        max=1100
     vus_max........................: 1100   min=1100      max=1100
```

# 리버스-프록시를 제외한 최적화 반영 후

```text
// load.js

```

```text
// smoke.js

```

```text
// stress.js

```

# 리버스-프록시와 최적화 반영 후

```text
// load.js

```

```text
// smoke.js

```

```text
// stress.js

```