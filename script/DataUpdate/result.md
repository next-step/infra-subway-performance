# 최적화 반영 전
```text
// load.js
running (1m40.9s), 000/300 VUs, 30000 complete and 0 interrupted iterations
default ✓ [======================================] 300 VUs  1m40s

     ✓ created in successfully

     checks.........................: 100.00% ✓ 30000      ✗ 0
     data_received..................: 3.0 MB  29 kB/s
     data_sent......................: 5.9 MB  59 kB/s
     http_req_blocked...............: avg=366.71µs min=3.19µs   med=4.87µs  max=120.5ms  p(90)=6.79µs  p(95)=11.49µs
     http_req_connecting............: avg=341.98µs min=0s       med=0s      max=79.19ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=4.48ms   min=726.02µs med=1.48ms  max=371.86ms p(90)=5.32ms  p(95)=8.5ms
       { expected_response:true }...: avg=4.48ms   min=726.02µs med=1.48ms  max=371.86ms p(90)=5.32ms  p(95)=8.5ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 30000
     http_req_receiving.............: avg=62.27µs  min=11.01µs  med=25.25µs max=15.04ms  p(90)=98.38µs p(95)=183.36µs
     http_req_sending...............: avg=143.71µs min=10.02µs  med=16.7µs  max=117.11ms p(90)=88.84µs p(95)=284.38µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=4.27ms   min=673.54µs med=1.38ms  max=353.01ms p(90)=5.05ms  p(95)=8.13ms
     http_reqs......................: 30000   297.390643/s
     iteration_duration.............: avg=1s       min=1s       med=1s      max=1.44s    p(90)=1s      p(95)=1.01s
     iterations.....................: 30000   297.390643/s
     vus............................: 300     min=300      max=300
     vus_max........................: 300     min=300      max=300
```

```text
// smoke.js
running (1m40.2s), 0/1 VUs, 100 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ created in successfully

     checks.........................: 100.00% ✓ 100      ✗ 0
     data_received..................: 9.9 kB  99 B/s
     data_sent......................: 20 kB   198 B/s
     http_req_blocked...............: avg=13µs    min=6.45µs med=7.36µs  max=512.72µs p(90)=8.17µs  p(95)=11.29µs
     http_req_connecting............: avg=4.58µs  min=0s     med=0s      max=458.07µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=1.37ms  min=1.2ms  med=1.28ms  max=4.97ms   p(90)=1.42ms  p(95)=1.58ms
       { expected_response:true }...: avg=1.37ms  min=1.2ms  med=1.28ms  max=4.97ms   p(90)=1.42ms  p(95)=1.58ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 100
     http_req_receiving.............: avg=47.34µs min=34.6µs med=46.34µs max=73.37µs  p(90)=52.12µs p(95)=56.09µs
     http_req_sending...............: avg=34.29µs min=25.1µs med=28.33µs max=364.18µs p(90)=37.35µs p(95)=42.43µs
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s      max=0s       p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=1.29ms  min=1.13ms med=1.21ms  max=4.56ms   p(90)=1.33ms  p(95)=1.51ms
     http_reqs......................: 100     0.997713/s
     iteration_duration.............: avg=1s      min=1s     med=1s      max=1s       p(90)=1s      p(95)=1s
     iterations.....................: 100     0.997713/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

```text
// stress.js
running (1m27.7s), 0000/1100 VUs, 50981 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1100 VUs  1m27s

     ✗ created in successfully
      ↳  98% — ✓ 50423 / ✗ 558

     checks.........................: 98.90% ✓ 50423      ✗ 558
     data_received..................: 5.0 MB 57 kB/s
     data_sent......................: 10 MB  114 kB/s
     http_req_blocked...............: avg=25.45µs min=0s       med=4.98µs  max=19.23ms p(90)=7.1µs   p(95)=20.68µs
     http_req_connecting............: avg=12.22µs min=0s       med=0s      max=15.78ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=2.04ms  min=0s       med=1.23ms  max=67.49ms p(90)=3.8ms   p(95)=5.98ms
       { expected_response:true }...: avg=2.07ms  min=694.56µs med=1.24ms  max=67.49ms p(90)=3.84ms  p(95)=6.02ms
     http_req_failed................: 1.09%  ✓ 558        ✗ 50423
     http_req_receiving.............: avg=63.2µs  min=0s       med=26.64µs max=27.96ms p(90)=70.94µs p(95)=180.39µs
     http_req_sending...............: avg=85.97µs min=0s       med=16.91µs max=29.65ms p(90)=70.71µs p(95)=225.3µs
     http_req_tls_handshaking.......: avg=0s      min=0s       med=0s      max=0s      p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=1.89ms  min=0s       med=1.15ms  max=67.44ms p(90)=3.51ms  p(95)=5.55ms
     http_reqs......................: 50981  581.000593/s
     iteration_duration.............: avg=1s      min=1s       med=1s      max=1.07s   p(90)=1s      p(95)=1s
     iterations.....................: 50981  581.000593/s
     vus............................: 58     min=50       max=1100
     vus_max........................: 1100   min=1100     max=1100
```

# 리버스-프록시를 제외한 최적화 반영 후

```text
// load.js
running (1m41.0s), 000/300 VUs, 29680 complete and 0 interrupted iterations
default ✓ [======================================] 300 VUs  1m40s

     ✓ created in successfully

     checks.........................: 100.00% ✓ 29680      ✗ 0
     data_received..................: 2.9 MB  29 kB/s
     data_sent......................: 5.9 MB  58 kB/s
     http_req_blocked...............: avg=410.67µs min=3.21µs   med=4.98µs  max=88.47ms  p(90)=6.98µs  p(95)=10.58µs
     http_req_connecting............: avg=392.06µs min=0s       med=0s      max=85.71ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=13.01ms  min=711.11µs med=1.37ms  max=1.05s    p(90)=5.75ms  p(95)=16.27ms
       { expected_response:true }...: avg=13.01ms  min=711.11µs med=1.37ms  max=1.05s    p(90)=5.75ms  p(95)=16.27ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 29680
     http_req_receiving.............: avg=72.83µs  min=10.61µs  med=26.87µs max=131.49ms p(90)=59.42µs p(95)=173.83µs
     http_req_sending...............: avg=117.48µs min=10.2µs   med=16.93µs max=126.79ms p(90)=48.46µs p(95)=170.69µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=12.82ms  min=672.44µs med=1.28ms  max=1.05s    p(90)=5.49ms  p(95)=15.82ms
     http_reqs......................: 29680   293.849546/s
     iteration_duration.............: avg=1.01s    min=1s       med=1s      max=2.22s    p(90)=1s      p(95)=1.02s
     iterations.....................: 29680   293.849546/s
     vus............................: 2       min=2        max=300
     vus_max........................: 300     min=300      max=300
```

```text
// smoke.js
default ✓ [======================================] 1 VUs  1m40s

     ✓ created in successfully

     checks.........................: 100.00% ✓ 100      ✗ 0
     data_received..................: 9.9 kB  99 B/s
     data_sent......................: 20 kB   198 B/s
     http_req_blocked...............: avg=13.56µs min=6.69µs  med=7.39µs  max=541.99µs p(90)=8.62µs  p(95)=13.1µs
     http_req_connecting............: avg=4.68µs  min=0s      med=0s      max=468.58µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=1.35ms  min=1.24ms  med=1.33ms  max=1.8ms    p(90)=1.43ms  p(95)=1.52ms
       { expected_response:true }...: avg=1.35ms  min=1.24ms  med=1.33ms  max=1.8ms    p(90)=1.43ms  p(95)=1.52ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 100
     http_req_receiving.............: avg=46.41µs min=27.82µs med=46.06µs max=74.61µs  p(90)=50.22µs p(95)=50.87µs
     http_req_sending...............: avg=32.17µs min=25.34µs med=28.82µs max=104.53µs p(90)=40.54µs p(95)=45.17µs
     http_req_tls_handshaking.......: avg=0s      min=0s      med=0s      max=0s       p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=1.27ms  min=1.16ms  med=1.25ms  max=1.72ms   p(90)=1.34ms  p(95)=1.43ms
     http_reqs......................: 100     0.997737/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1s       p(90)=1s      p(95)=1s
     iterations.....................: 100     0.997737/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

```text
// stress.js
running (1m27.8s), 0000/1100 VUs, 50995 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1100 VUs  1m27s

     ✗ created in successfully
      ↳  98% — ✓ 50435 / ✗ 560

     checks.........................: 98.90% ✓ 50435      ✗ 560
     data_received..................: 5.0 MB 57 kB/s
     data_sent......................: 10 MB  114 kB/s
     http_req_blocked...............: avg=26.26µs min=0s       med=4.91µs  max=33.39ms p(90)=7.01µs  p(95)=20.79µs
     http_req_connecting............: avg=13.61µs min=0s       med=0s      max=33.04ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=1.9ms   min=0s       med=1.2ms   max=76.42ms p(90)=3.36ms  p(95)=5.22ms
       { expected_response:true }...: avg=1.92ms  min=680.56µs med=1.21ms  max=76.42ms p(90)=3.39ms  p(95)=5.26ms
     http_req_failed................: 1.09%  ✓ 560        ✗ 50435
     http_req_receiving.............: avg=57µs    min=0s       med=26.52µs max=10.45ms p(90)=67.46µs p(95)=179.13µs
     http_req_sending...............: avg=76.78µs min=0s       med=16.75µs max=21.58ms p(90)=66.64µs p(95)=211.7µs
     http_req_tls_handshaking.......: avg=0s      min=0s       med=0s      max=0s      p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=1.77ms  min=0s       med=1.11ms  max=76.39ms p(90)=3.11ms  p(95)=4.86ms
     http_reqs......................: 50995  580.923632/s
     iteration_duration.............: avg=1s      min=1s       med=1s      max=1.07s   p(90)=1s      p(95)=1s
     iterations.....................: 50995  580.923632/s
     vus............................: 52     min=50       max=1100
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