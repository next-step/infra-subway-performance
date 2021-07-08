# No reverse Proxy + 최적화 반영 전(Redis 캐시 적용)
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

# No reverse Proxy + 최적화 반영 후(Redis 캐시 적용)

```text
// load.js
running (1m41.0s), 000/300 VUs, 28352 complete and 0 interrupted iterations
default ✓ [======================================] 300 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 56704      ✗ 0
     data_received..................: 13 MB   127 kB/s
     data_sent......................: 13 MB   127 kB/s
     http_req_blocked...............: avg=344.41µs min=2.84µs   med=4.53µs  max=102.28ms p(90)=6.93µs   p(95)=18.74µs
     http_req_connecting............: avg=312.41µs min=0s       med=0s      max=96.91ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=29.02ms  min=918.49µs med=3.8ms   max=1.67s    p(90)=52.56ms  p(95)=144.6ms
       { expected_response:true }...: avg=29.02ms  min=918.49µs med=3.8ms   max=1.67s    p(90)=52.56ms  p(95)=144.6ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 56704
     http_req_receiving.............: avg=498.17µs min=15.83µs  med=48.62µs max=348.12ms p(90)=251.62µs p(95)=1.06ms
     http_req_sending...............: avg=256.26µs min=8.26µs   med=15.84µs max=285.38ms p(90)=51.12µs  p(95)=277.04µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=28.27ms  min=830.41µs med=3.51ms  max=1.66s    p(90)=50.25ms  p(95)=142.39ms
     http_reqs......................: 56704   561.418494/s
     iteration_duration.............: avg=1.06s    min=1s       med=1.01s   max=3.25s    p(90)=1.11s    p(95)=1.32s
     iterations.....................: 28352   280.709247/s
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
     http_req_blocked...............: avg=11.39µs min=3.87µs   med=6.78µs  max=559.04µs p(90)=7.72µs   p(95)=8.43µs
     http_req_connecting............: avg=4.61µs  min=0s       med=0s      max=479.89µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=1.5ms   min=1.08ms   med=1.49ms  max=4.54ms   p(90)=1.72ms   p(95)=1.82ms
       { expected_response:true }...: avg=1.5ms   min=1.08ms   med=1.49ms  max=4.54ms   p(90)=1.72ms   p(95)=1.82ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 200
     http_req_receiving.............: avg=83.3µs  min=30.76µs  med=78.7µs  max=205.15µs p(90)=113.85µs p(95)=127.32µs
     http_req_sending...............: avg=23.77µs min=12.26µs  med=26.12µs max=97.34µs  p(90)=36.36µs  p(95)=39.6µs
     http_req_tls_handshaking.......: avg=0s      min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=1.39ms  min=997.62µs med=1.36ms  max=4.47ms   p(90)=1.61ms   p(95)=1.67ms
     http_reqs......................: 200     1.991849/s
     iteration_duration.............: avg=1s      min=1s       med=1s      max=1s       p(90)=1s       p(95)=1s
     iterations.....................: 100     0.995924/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

```text
// stress.js
running (1m27.7s), 0000/1100 VUs, 28798 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1100 VUs  1m27s

     ✗ logged in successfully
      ↳  92% — ✓ 26729 / ✗ 2069
     ✓ retrieved member

     checks.........................: 96.27% ✓ 53458      ✗ 2069
     data_received..................: 12 MB  138 kB/s
     data_sent......................: 12 MB  138 kB/s
     http_req_blocked...............: avg=266.03µs min=0s       med=4.53µs   max=376.79ms p(90)=10.15µs  p(95)=36.86µs
     http_req_connecting............: avg=235.65µs min=0s       med=0s       max=369.42ms p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=436.61ms min=0s       med=225.24ms max=3.97s    p(90)=1.18s    p(95)=1.55s
       { expected_response:true }...: avg=453.51ms min=896.42µs med=244.57ms max=3.97s    p(90)=1.2s     p(95)=1.57s
     http_req_failed................: 3.72%  ✓ 2069       ✗ 53458
     http_req_receiving.............: avg=457.76µs min=0s       med=47.17µs  max=453.06ms p(90)=305.54µs p(95)=794.4µs
     http_req_sending...............: avg=781.69µs min=0s       med=15.95µs  max=578.59ms p(90)=159.15µs p(95)=964.44µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s       max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=435.37ms min=0s       med=222.83ms max=3.97s    p(90)=1.18s    p(95)=1.55s
     http_reqs......................: 55527  633.338505/s
     iteration_duration.............: avg=1.79s    min=158.18µs med=1.54s    max=7.45s    p(90)=3.22s    p(95)=3.78s
     iterations.....................: 28798  328.468714/s
     vus............................: 78     min=50       max=1100
     vus_max........................: 1100   min=1100     max=1100
```

# Default reverse Proxy + 최적화 반영 후(Redis 캐시 적용)
```text
// load.js
running (1m41.0s), 000/300 VUs, 26713 complete and 0 interrupted iterations
default ✓ [======================================] 300 VUs  1m40s

     ✗ logged in successfully
      ↳  99% — ✓ 26699 / ✗ 14
     ✓ retrieved member

     checks.....................: 99.97%  ✓ 53398      ✗ 14
     data_received..............: 21 MB   211 kB/s
     data_sent..................: 11 MB   105 kB/s
     http_req_blocked...........: avg=5.71ms   min=3.08µs  med=4.5µs   max=1.59s    p(90)=6.45µs   p(95)=16.26µs
     http_req_connecting........: avg=445.72µs min=0s      med=0s      max=119.32ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..........: avg=51.68ms  min=1.04ms  med=19.71ms max=1.36s    p(90)=142.49ms p(95)=201.08ms
     http_req_failed............: 100.00% ✓ 53412      ✗ 0
     http_req_receiving.........: avg=492.51µs min=0s      med=34.6µs  max=211.44ms p(90)=129.74µs p(95)=619.9µs
     http_req_sending...........: avg=1.29ms   min=8.65µs  med=14.78µs max=424.11ms p(90)=143.29µs p(95)=1.41ms
     http_req_tls_handshaking...: avg=5.02ms   min=0s      med=0s      max=1.5s     p(90)=0s       p(95)=0s
     http_req_waiting...........: avg=49.89ms  min=14.96µs med=19.05ms max=1.33s    p(90)=138.94ms p(95)=193.98ms
     http_reqs..................: 53412   528.841772/s
     iteration_duration.........: avg=1.12s    min=3.27ms  med=1.06s   max=2.98s    p(90)=1.3s     p(95)=1.37s
     iterations.................: 26713   264.490194/s
     vus........................: 300     min=300      max=300
     vus_max....................: 300     min=300      max=300
```

```text
// smoke.js
default ✓ [======================================] 1 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member

     checks.....................: 100.00% ✓ 200      ✗ 0
     data_received..............: 78 kB   772 B/s
     data_sent..................: 40 kB   393 B/s
     http_req_blocked...........: avg=146.55µs min=3.98µs  med=6.82µs  max=27.99ms  p(90)=7.84µs   p(95)=8.63µs
     http_req_connecting........: avg=2.11µs   min=0s      med=0s      max=422.01µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..........: avg=2.57ms   min=1.19ms  med=2.89ms  max=9.6ms    p(90)=3.79ms   p(95)=3.85ms
     http_req_failed............: 100.00% ✓ 200      ✗ 0
     http_req_receiving.........: avg=73.91µs  min=40.7µs  med=71.36µs max=136.43µs p(90)=104.77µs p(95)=112.13µs
     http_req_sending...........: avg=23.75µs  min=11.81µs med=26.19µs max=79.88µs  p(90)=37.79µs  p(95)=38.99µs
     http_req_tls_handshaking...: avg=134.97µs min=0s      med=0s      max=26.99ms  p(90)=0s       p(95)=0s
     http_req_waiting...........: avg=2.48ms   min=1.11ms  med=2.78ms  max=9.5ms    p(90)=3.67ms   p(95)=3.71ms
     http_reqs..................: 200     1.986918/s
     iteration_duration.........: avg=1s       min=1s      med=1s      max=1.03s    p(90)=1s       p(95)=1s
     iterations.................: 100     0.993459/s
     vus........................: 1       min=1      max=1
     vus_max....................: 1       min=1      max=1
```

```text
// stress.js
running (1m27.9s), 0000/1100 VUs, 42870 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1100 VUs  1m27s

     ✗ logged in successfully
      ↳  32% — ✓ 13999 / ✗ 28871
     ✓ retrieved member

     checks.....................: 48.50%  ✓ 27197      ✗ 28871
     data_received..............: 90 MB   1.0 MB/s
     data_sent..................: 18 MB   208 kB/s
     http_req_blocked...........: avg=330.43ms min=0s       med=4.49µs   max=3.13s    p(90)=1.31s    p(95)=1.59s
     http_req_connecting........: avg=200.23ms min=0s       med=162.52ms max=1.55s    p(90)=477.83ms p(95)=572.14ms
   ✓ http_req_duration..........: avg=96.09ms  min=0s       med=2.9ms    max=2.26s    p(90)=341.26ms p(95)=478.82ms
     http_req_failed............: 100.00% ✓ 56869      ✗ 0
     http_req_receiving.........: avg=2.01ms   min=0s       med=0s       max=525.49ms p(90)=67.57µs  p(95)=212.19µs
     http_req_sending...........: avg=14.77ms  min=0s       med=12.63µs  max=2.02s    p(90)=44.02ms  p(95)=85.37ms
     http_req_tls_handshaking...: avg=264.33ms min=0s       med=0s       max=2.32s    p(90)=1.08s    p(95)=1.38s
     http_req_waiting...........: avg=79.3ms   min=0s       med=1.14ms   max=2.01s    p(90)=294.62ms p(95)=418.47ms
     http_reqs..................: 56869   646.726603/s
     iteration_duration.........: avg=1.17s    min=194.18µs med=1.01s    max=4.96s    p(90)=2.35s    p(95)=2.73s
     iterations.................: 42870   487.526939/s
     vus........................: 67      min=50       max=1100
     vus_max....................: 1100    min=1100     max=1100
```



# advanced reverse Proxy + 최적화 반영 후(Redis 캐시 적용)

```text
// load.js
running (1m41.0s), 000/300 VUs, 25865 complete and 0 interrupted iterations
default ✓ [======================================] 300 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 51730      ✗ 0
     data_received..................: 15 MB   148 kB/s
     data_sent......................: 9.9 MB  98 kB/s
     http_req_blocked...............: avg=5.67ms   min=2.46µs  med=2.7µs   max=2.5s     p(90)=2.88µs   p(95)=3.27µs
     http_req_connecting............: avg=301.72µs min=0s      med=0s      max=114.74ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=66.98ms  min=37.76µs med=12.69ms max=1.89s    p(90)=197.08ms p(95)=271.13ms
       { expected_response:true }...: avg=66.98ms  min=37.76µs med=12.69ms max=1.89s    p(90)=197.08ms p(95)=271.13ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 51730
     http_req_receiving.............: avg=1ms      min=27.32µs med=48.94µs max=572.5ms  p(90)=237.3µs  p(95)=936.41µs
     http_req_sending...............: avg=2.46ms   min=0s      med=56.06µs max=926.92ms p(90)=670.4µs  p(95)=3.28ms
     http_req_tls_handshaking.......: avg=5.07ms   min=0s      med=0s      max=2.36s    p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=63.51ms  min=0s      med=11.58ms max=1.89s    p(90)=185.45ms p(95)=258.77ms
     http_reqs......................: 51730   512.190122/s
     iteration_duration.............: avg=1.16s    min=1s      med=1.03s   max=4.17s    p(90)=1.42s    p(95)=1.52s
     iterations.....................: 25865   256.095061/s
     vus............................: 0       min=0        max=300
     vus_max........................: 300     min=300      max=300
```

```text
// smoke.js
running (1m40.6s), 0/1 VUs, 100 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 200     ✗ 0
     data_received..................: 57 kB   568 B/s
     data_sent......................: 38 kB   380 B/s
     http_req_blocked...............: avg=190.31µs min=2.63µs  med=2.82µs  max=37.43ms  p(90)=2.93µs   p(95)=3.04µs
     http_req_connecting............: avg=2.82µs   min=0s      med=0s      max=565.26µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=2.12ms   min=1.46ms  med=2.08ms  max=7.28ms   p(90)=2.63ms   p(95)=2.94ms
       { expected_response:true }...: avg=2.12ms   min=1.46ms  med=2.08ms  max=7.28ms   p(90)=2.63ms   p(95)=2.94ms
     http_req_failed................: 0.00%   ✓ 0       ✗ 200
     http_req_receiving.............: avg=73.63µs  min=49.88µs med=69.93µs max=126.84µs p(90)=93.34µs  p(95)=97.67µs
     http_req_sending...............: avg=77.64µs  min=30.61µs med=82.26µs max=230.98µs p(90)=120.84µs p(95)=124.8µs
     http_req_tls_handshaking.......: avg=135.62µs min=0s      med=0s      max=27.12ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=1.97ms   min=1.36ms  med=1.91ms  max=7.11ms   p(90)=2.44ms   p(95)=2.74ms
     http_reqs......................: 200     1.98856/s
     iteration_duration.............: avg=1s       min=1s      med=1s      max=1.04s    p(90)=1s       p(95)=1s
     iterations.....................: 100     0.99428/s
     vus............................: 1       min=1     max=1
     vus_max........................: 1       min=1     max=1
```

```text
// stress.js
running (1m27.9s), 0000/1100 VUs, 40873 complete and 0 interrupted iterations
default ✗ [======================================] 0000/1100 VUs  1m27s

     ✗ logged in successfully
      ↳  88% — ✓ 36189 / ✗ 4684
     ✓ retrieved member

     checks.........................: 93.92% ✓ 72378      ✗ 4684
     data_received..................: 24 MB  270 kB/s
     data_sent......................: 14 MB  162 kB/s
     http_req_blocked...............: avg=2.35ms   min=0s       med=2.68µs  max=1.24s    p(90)=2.89µs   p(95)=8.52µs
     http_req_connecting............: avg=930.1µs  min=0s       med=0s      max=813.67ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=150.96ms min=0s       med=79.25ms max=1.73s    p(90)=387.36ms p(95)=510.54ms
       { expected_response:true }...: avg=160.73ms min=37.21µs  med=94.99ms max=1.73s    p(90)=400.55ms p(95)=524.86ms
     http_req_failed................: 6.07%  ✓ 4684       ✗ 72378
     http_req_receiving.............: avg=1.78ms   min=0s       med=46.68µs max=853.15ms p(90)=213.19µs p(95)=959.44µs
     http_req_sending...............: avg=4.79ms   min=0s       med=49.98µs max=917.65ms p(90)=686.13µs p(95)=9.52ms
     http_req_tls_handshaking.......: avg=1.26ms   min=0s       med=0s      max=1.05s    p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=144.38ms min=0s       med=73.35ms max=1.52s    p(90)=372.74ms p(95)=490.57ms
     http_reqs......................: 77062  877.031136/s
     iteration_duration.............: avg=1.24s    min=156.43µs med=1.19s   max=3.34s    p(90)=1.94s    p(95)=2.25s
     iterations.....................: 40873  465.169521/s
     vus............................: 45     min=45       max=1100
     vus_max........................: 1100   min=1100     max=1100
```

