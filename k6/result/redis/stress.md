```javascript

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 3m10s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 2m40s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m41.9s), 000/240 VUs, 14863 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  2m40s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 29726      ✗ 0
     data_received..................: 49 MB   303 kB/s
     data_sent......................: 1.4 MB  8.8 kB/s
     http_req_blocked...............: avg=163.49µs min=0s     med=0s     max=162.62ms p(90)=1µs     p(95)=1µs
     http_req_connecting............: avg=55.66µs  min=0s     med=0s     max=147.65ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=10.08ms  min=3.93ms med=7.75ms max=438.66ms p(90)=12.22ms p(95)=14.8ms
       { expected_response:true }...: avg=10.08ms  min=3.93ms med=7.75ms max=438.66ms p(90)=12.22ms p(95)=14.8ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 29726
     http_req_receiving.............: avg=42.89µs  min=12µs   med=32µs   max=3.87ms   p(90)=57µs    p(95)=70µs
     http_req_sending...............: avg=54.57µs  min=19µs   med=47µs   max=447µs    p(90)=86µs    p(95)=101µs
     http_req_tls_handshaking.......: avg=106.11µs min=0s     med=0s     max=136.38ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=9.98ms   min=3.8ms  med=7.66ms max=438.57ms p(90)=12.12ms p(95)=14.69ms
     http_reqs......................: 29726   183.659769/s
     iteration_duration.............: avg=2.02s    min=2s     med=2.01s  max=2.44s    p(90)=2.02s   p(95)=2.03s
     iterations.....................: 14863   91.829885/s
     vus............................: 52      min=10       max=240
     vus_max........................: 240     min=240      max=240

```
