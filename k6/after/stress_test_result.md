[뒤로가기](../../markdown/step1.md)

```http

     ✗ logged in successfully
      ↳  4% — ✓ 16525 / ✗ 385315
     ✓ retrieved member
     ✓ get shortest line success

     checks.........................: 11.39% ✓ 49575       ✗ 385315
     data_received..................: 26 MB  266 kB/s
     data_sent......................: 8.0 MB 84 kB/s
     http_req_blocked...............: avg=15.48µs min=0s      med=0s      max=205.53ms p(90)=0s      p(95)=1µs
     http_req_connecting............: avg=4.38µs  min=0s      med=0s      max=186.81ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=2.93ms  min=0s      med=0s      max=2.15s    p(90)=10.07ms p(95)=14.51ms
       { expected_response:true }...: avg=25.72ms min=7.29ms  med=13.49ms max=2.15s    p(90)=36.47ms p(95)=54.37ms
     http_req_failed................: 88.60% ✓ 385315      ✗ 49575
     http_req_receiving.............: avg=5.91µs  min=0s      med=0s      max=5.14ms   p(90)=16µs    p(95)=49µs
     http_req_sending...............: avg=5.56µs  min=0s      med=0s      max=3.04ms   p(90)=11µs    p(95)=38µs
     http_req_tls_handshaking.......: avg=10.86µs min=0s      med=0s      max=156.17ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=2.92ms  min=0s      med=0s      max=2.15s    p(90)=9.95ms  p(95)=14.41ms
     http_reqs......................: 434890 4536.553444/s
     iteration_duration.............: avg=48.16ms min=36.95µs med=4.93ms  max=3.2s     p(90)=7.88ms  p(95)=11.71ms
     iterations.....................: 401840 4191.79249/s
     vus............................: 39     min=12        max=360
     vus_max........................: 360    min=360       max=360

```