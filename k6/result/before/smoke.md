```javascript

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.7s), 0/1 VUs, 5 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 10       ✗ 0
     data_received..................: 21 kB   2.0 kB/s
     data_sent......................: 1.7 kB  160 B/s
     http_req_blocked...............: avg=17.61ms min=6µs    med=7µs     max=176.13ms p(90)=17.61ms  p(95)=96.87ms
     http_req_connecting............: avg=407.5µs min=0s     med=0s      max=4.07ms   p(90)=407.49µs p(95)=2.24ms
   ✓ http_req_duration..............: avg=51.98ms min=5.75ms med=42.08ms max=164.4ms  p(90)=96.17ms  p(95)=130.28ms
       { expected_response:true }...: avg=51.98ms min=5.75ms med=42.08ms max=164.4ms  p(90)=96.17ms  p(95)=130.28ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 10
     http_req_receiving.............: avg=95.99µs min=67µs   med=98.5µs  max=121µs    p(90)=108.4µs  p(95)=114.69µs
     http_req_sending...............: avg=79.5µs  min=34µs   med=37µs    max=468µs    p(90)=80.99µs  p(95)=274.49µs
     http_req_tls_handshaking.......: avg=15.99ms min=0s     med=0s      max=159.94ms p(90)=15.99ms  p(95)=87.97ms
     http_req_waiting...............: avg=51.81ms min=5.62ms med=41.94ms max=164.24ms p(90)=96.03ms  p(95)=130.13ms
     http_reqs......................: 10      0.932526/s
     iteration_duration.............: avg=2.14s   min=2.08s  med=2.09s   max=2.35s    p(90)=2.24s    p(95)=2.3s
     iterations.....................: 5       0.466263/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
