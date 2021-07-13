[뒤로가기](../../markdown/step1.md)

```http

     ✗ logged in successfully
      ↳  2% — ✓ 9334 / ✗ 360913
     ✓ retrieved member
     ✓ get shortest line success

     checks.........................: 7.20%  ✓ 28002       ✗ 360913
     data_received..................: 18 MB  178 kB/s
     data_sent......................: 8.4 MB 84 kB/s
     http_req_blocked...............: avg=19.88µs  min=0s      med=0s       max=178.31ms p(90)=0s       p(95)=1µs
     http_req_connecting............: avg=5.64µs   min=0s      med=0s       max=18.73ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=23.7ms   min=0s      med=0s       max=6.42s    p(90)=0s       p(95)=32.82ms
       { expected_response:true }...: avg=329.19ms min=9.33ms  med=193.79ms max=6.42s    p(90)=474.93ms p(95)=2.02s
     http_req_failed................: 92.79% ✓ 360913      ✗ 28002
     http_req_receiving.............: avg=2.53µs   min=0s      med=0s       max=630µs    p(90)=0s       p(95)=21µs
     http_req_sending...............: avg=935ns    min=0s      med=0s       max=457µs    p(90)=0s       p(95)=8µs
     http_req_tls_handshaking.......: avg=14.03µs  min=0s      med=0s       max=165.25ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=23.69ms  min=0s      med=0s       max=6.42s    p(90)=0s       p(95)=32.75ms
     http_reqs......................: 388915 3883.379143/s
     iteration_duration.............: avg=54.28ms  min=37.41µs med=5.02ms   max=10.73s   p(90)=7.6ms    p(95)=9.62ms
     iterations.....................: 370247 3696.976146/s
     vus............................: 13     min=12        max=360
     vus_max........................: 360    min=360       max=360

ERRO[0100] some thresholds have failed
```