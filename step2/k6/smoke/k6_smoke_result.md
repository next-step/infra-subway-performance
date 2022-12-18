```text

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: InfluxDBv1 (http://localhost:8086)

  scenarios: (100.00%) 1 scenario, 1 max VUs, 1m30s max duration (incl. graceful stop):
           * default: 1 looping VUs for 1m0s (gracefulStop: 30s)


running (1m00.0s), 0/1 VUs, 3184 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m0s

     ✓ [Result] Main Page
     ✓ [Result] Login Page
     ✓ [Result] Login
     ✓ [Result] me
     ✓ [Result] Path Page
     ✓ [Result] Search Path

     checks.........................: 100.00% ✓ 19104      ✗ 0
     data_received..................: 16 MB   271 kB/s
     data_sent......................: 1.1 MB  19 kB/s
     http_req_blocked...............: avg=2.58µs  min=197ns    med=365ns   max=37.32ms  p(90)=580ns   p(95)=654ns
     http_req_connecting............: avg=48ns    min=0s       med=0s      max=654.19µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=3.01ms  min=763.6µs  med=1.96ms  max=69.06ms  p(90)=6.74ms  p(95)=7.19ms
       { expected_response:true }...: avg=3.01ms  min=763.6µs  med=1.96ms  max=69.06ms  p(90)=6.74ms  p(95)=7.19ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 19104
     http_req_receiving.............: avg=54.49µs min=15.64µs  med=36.1µs  max=10.23ms  p(90)=67.75µs p(95)=98.16µs
     http_req_sending...............: avg=33.33µs min=13.35µs  med=27.69µs max=7.53ms   p(90)=44.46µs p(95)=52.29µs
     http_req_tls_handshaking.......: avg=295ns   min=0s       med=0s      max=2.99ms   p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=2.93ms  min=577.51µs med=1.89ms  max=68.97ms  p(90)=6.64ms  p(95)=7.09ms
     http_reqs......................: 19104   318.332461/s
     iteration_duration.............: avg=18.83ms min=14.27ms  med=17.94ms max=180.51ms p(90)=22.46ms p(95)=24.77ms
     iterations.....................: 3184    53.05541/s
     vus............................: 1       min=1        max=1
     vus_max........................: 1       min=1        max=1

```
