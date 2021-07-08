# 최적화 반영 전

distance 는 설정한 거리가 정확하지 않아서 틀렸다고 나오지만, 로그를 통해서 정상적으로 잘 실행되었됨을 확인함


```text
// load.js
running (1m41.0s), 000/300 VUs, 29711 complete and 0 interrupted iterations
default ✓ [======================================] 300 VUs  1m40s

     ✓ find path in successfully
     ✗ distance
      ↳  0% — ✓ 0 / ✗ 29711

     checks.........................: 50.00% ✓ 29711      ✗ 29711
     data_received..................: 15 MB  147 kB/s
     data_sent......................: 4.2 MB 42 kB/s
     http_req_blocked...............: avg=960.82µs min=3.32µs  med=5.19µs  max=188.76ms p(90)=6.81µs   p(95)=10.18µs
     http_req_connecting............: avg=929.31µs min=0s      med=0s      max=155.92ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=11.32ms  min=1.35ms  med=2.7ms   max=933.71ms p(90)=14.29ms  p(95)=28.57ms
       { expected_response:true }...: avg=11.32ms  min=1.35ms  med=2.7ms   max=933.71ms p(90)=14.29ms  p(95)=28.57ms
     http_req_failed................: 0.00%  ✓ 0          ✗ 29711
     http_req_receiving.............: avg=480.06µs min=16.81µs med=46.12µs max=155.88ms p(90)=190.46µs p(95)=642.54µs
     http_req_sending...............: avg=374.87µs min=9.03µs  med=14.32µs max=129.78ms p(90)=31.31µs  p(95)=79.59µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=10.47ms  min=1.28ms  med=2.55ms  max=877.71ms p(90)=13.42ms  p(95)=26.05ms
     http_reqs......................: 29711  294.148244/s
     iteration_duration.............: avg=1.01s    min=1s      med=1s      max=2.05s    p(90)=1.01s    p(95)=1.03s
     iterations.....................: 29711  294.148244/s
     vus............................: 3      min=3        max=300
     vus_max........................: 300    min=300      max=300
```

```text
// smoke.js
running (1m40.3s), 0/1 VUs, 100 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ find path in successfully
     ✗ distance
      ↳  0% — ✓ 0 / ✗ 100

     checks.........................: 50.00% ✓ 100      ✗ 100
     data_received..................: 50 kB  499 B/s
     data_sent......................: 14 kB  141 B/s
     http_req_blocked...............: avg=12.01µs  min=6.62µs  med=7.28µs  max=460.71µs p(90)=7.97µs   p(95)=9.72µs
     http_req_connecting............: avg=4.05µs   min=0s      med=0s      max=405.53µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=2.42ms   min=2.05ms  med=2.23ms  max=8.53ms   p(90)=2.69ms   p(95)=3.77ms
       { expected_response:true }...: avg=2.42ms   min=2.05ms  med=2.23ms  max=8.53ms   p(90)=2.69ms   p(95)=3.77ms
     http_req_failed................: 0.00%  ✓ 0        ✗ 100
     http_req_receiving.............: avg=117.66µs min=52.66µs med=87.03µs max=1.45ms   p(90)=127.29µs p(95)=157.37µs
     http_req_sending...............: avg=25.52µs  min=20.14µs med=22.82µs max=83.79µs  p(90)=31.93µs  p(95)=38.73µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=2.27ms   min=1.95ms  med=2.1ms   max=8.39ms   p(90)=2.53ms   p(95)=3.26ms
     http_reqs......................: 100    0.996744/s
     iteration_duration.............: avg=1s       min=1s      med=1s      max=1s       p(90)=1s       p(95)=1s
     iterations.....................: 100    0.996744/s
     vus............................: 1      min=1      max=1
     vus_max........................: 1      min=1      max=1
```

```text
// stress.js
running (1m27.9s), 0000/1100 VUs, 55384 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1100 VUs  1m27s

     ✗ find path in successfully
      ↳  85% — ✓ 47588 / ✗ 7796
     ✗ distance
      ↳  0% — ✓ 0 / ✗ 55384

     checks.........................: 42.96% ✓ 47588      ✗ 63180
     data_received..................: 24 MB  271 kB/s
     data_sent......................: 6.7 MB 76 kB/s
     http_req_blocked...............: avg=153.54µs min=0s       med=4.97µs  max=350.25ms p(90)=6.9µs    p(95)=19.44µs
     http_req_connecting............: avg=130.14µs min=0s       med=0s      max=350.19ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=49.81ms  min=0s       med=2.93ms  max=2.05s    p(90)=166.74ms p(95)=300.45ms
       { expected_response:true }...: avg=57.97ms  min=1.35ms   med=4ms     max=2.05s    p(90)=203.7ms  p(95)=340.21ms
     http_req_failed................: 14.07% ✓ 7796       ✗ 47588
     http_req_receiving.............: avg=871.76µs min=0s       med=39.33µs max=512.02ms p(90)=199.99µs p(95)=781.37µs
     http_req_sending...............: avg=700.61µs min=0s       med=13.88µs max=516.77ms p(90)=59.03µs  p(95)=221.75µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=48.24ms  min=0s       med=2.71ms  max=2.05s    p(90)=161.93ms p(95)=288.1ms
     http_reqs......................: 55384  630.080228/s
     iteration_duration.............: avg=918.56ms min=153.88µs med=1s      max=3.09s    p(90)=1.18s    p(95)=1.33s
     iterations.....................: 55384  630.080228/s
     vus............................: 42     min=42       max=1100
     vus_max........................: 1100   min=1100     max=1100
```

# 리버스-프록시를 제외한 최적화 반영 후

```text
// load.js
running (1m41.0s), 000/300 VUs, 29820 complete and 0 interrupted iterations
default ✓ [======================================] 300 VUs  1m40s

     ✓ find path in successfully
     ✗ distance
      ↳  0% — ✓ 0 / ✗ 29820

     checks.........................: 50.00% ✓ 29820      ✗ 29820
     data_received..................: 15 MB  148 kB/s
     data_sent......................: 4.2 MB 41 kB/s
     http_req_blocked...............: avg=754.06µs min=3.28µs  med=5.15µs  max=238.36ms p(90)=6.71µs   p(95)=9.56µs
     http_req_connecting............: avg=700.55µs min=0s      med=0s      max=213.71ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=8.05ms   min=1.37ms  med=2.13ms  max=918.66ms p(90)=6.24ms   p(95)=11.16ms
       { expected_response:true }...: avg=8.05ms   min=1.37ms  med=2.13ms  max=918.66ms p(90)=6.24ms   p(95)=11.16ms
     http_req_failed................: 0.00%  ✓ 0          ✗ 29820
     http_req_receiving.............: avg=197.87µs min=17.27µs med=51.4µs  max=60.32ms  p(90)=168.77µs p(95)=245.34µs
     http_req_sending...............: avg=123.55µs min=8.79µs  med=14.45µs max=229.16ms p(90)=29.99µs  p(95)=50.89µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=7.73ms   min=1.3ms   med=2ms     max=885.57ms p(90)=5.94ms   p(95)=10.52ms
     http_reqs......................: 29820  295.205037/s
     iteration_duration.............: avg=1.01s    min=1s      med=1s      max=1.98s    p(90)=1s       p(95)=1.01s
     iterations.....................: 29820  295.205037/s
     vus............................: 4      min=4        max=300
     vus_max........................: 300
```

```text
// smoke.js
running (1m40.3s), 0/1 VUs, 100 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ find path in successfully
     ✗ distance
      ↳  0% — ✓ 0 / ✗ 100

     checks.........................: 50.00% ✓ 100      ✗ 100
     data_received..................: 50 kB  499 B/s
     data_sent......................: 14 kB  140 B/s
     http_req_blocked...............: avg=13.07µs min=6.91µs  med=7.44µs  max=514.15µs p(90)=8.3µs    p(95)=11.2µs
     http_req_connecting............: avg=4.56µs  min=0s      med=0s      max=456.81µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=2.18ms  min=1.98ms  med=2.12ms  max=5.63ms   p(90)=2.24ms   p(95)=2.33ms
       { expected_response:true }...: avg=2.18ms  min=1.98ms  med=2.12ms  max=5.63ms   p(90)=2.24ms   p(95)=2.33ms
     http_req_failed................: 0.00%  ✓ 0        ✗ 100
     http_req_receiving.............: avg=83.95µs min=49.62µs med=72.52µs max=352.81µs p(90)=109.12µs p(95)=123.31µs
     http_req_sending...............: avg=33.31µs min=20.37µs med=23.38µs max=657.46µs p(90)=38.91µs  p(95)=43.53µs
     http_req_tls_handshaking.......: avg=0s      min=0s      med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=2.07ms  min=1.89ms  med=2.01ms  max=5.51ms   p(90)=2.1ms    p(95)=2.17ms
     http_reqs......................: 100    0.996938/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1s       p(90)=1s       p(95)=1s
     iterations.....................: 100    0.996938/s
     vus............................: 1      min=1      max=1
     vus_max........................: 1      min=1      max=1
```

```text
// stress.js
running (1m27.8s), 0000/1100 VUs, 52729 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1100 VUs  1m27s

     ✗ find path in successfully
      ↳  91% — ✓ 48252 / ✗ 4477
     ✗ distance
      ↳  0% — ✓ 0 / ✗ 52729

     checks.........................: 45.75% ✓ 48252      ✗ 57206
     data_received..................: 24 MB  275 kB/s
     data_sent......................: 6.8 MB 77 kB/s
     http_req_blocked...............: avg=45.62µs  min=0s       med=4.93µs  max=164.92ms p(90)=7.12µs   p(95)=21.32µs
     http_req_connecting............: avg=26.25µs  min=0s       med=0s      max=133.09ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=40.49ms  min=0s       med=3.57ms  max=1.28s    p(90)=123.37ms p(95)=252.56ms
       { expected_response:true }...: avg=44.24ms  min=1.36ms   med=4.37ms  max=1.28s    p(90)=138.38ms p(95)=266.97ms
     http_req_failed................: 8.49%  ✓ 4477       ✗ 48252
     http_req_receiving.............: avg=1.33ms   min=0s       med=40.58µs max=232.83ms p(90)=212.07µs p(95)=958.58µs
     http_req_sending...............: avg=624.98µs min=0s       med=14.16µs max=305.99ms p(90)=76.88µs  p(95)=246.1µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=38.53ms  min=0s       med=3.32ms  max=1.28s    p(90)=118.32ms p(95)=238.35ms
     http_reqs......................: 52729  600.433574/s
     iteration_duration.............: avg=964.89ms min=152.29µs med=1s      max=2.29s    p(90)=1.13s    p(95)=1.26s
     iterations.....................: 52729  600.433574/s
     vus............................: 53     min=50       max=1100
     vus_max........................: 1100   min=1100     max=1100
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