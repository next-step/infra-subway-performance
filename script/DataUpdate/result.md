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