```text

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: InfluxDBv1 (http://localhost:8086)

  scenarios: (100.00%) 1 scenario, 14 max VUs, 29m40s max duration (incl. graceful stop):
           * default: Up to 14 looping VUs for 29m10s over 12 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (29m10.0s), 00/14 VUs, 622455 complete and 0 interrupted iterations
default ✓ [======================================] 00/14 VUs  29m10s

     ✓ [Result] Main Page
     ✓ [Result] Login Page
     ✓ [Result] Login
     ✓ [Result] me
     ✓ [Result] Path Page
     ✓ [Result] Search Path

     checks.....................: 100.00% ✓ 3734730     ✗ 0
     data_received..............: 3.2 GB  1.9 MB/s
     data_sent..................: 223 MB  128 kB/s
     http_req_blocked...........: avg=939ns    min=121ns    med=307ns   max=65.52ms  p(90)=381ns   p(95)=415ns
     http_req_connecting........: avg=111ns    min=0s       med=0s      max=42.17ms  p(90)=0s      p(95)=0s
   ✓ http_req_duration..........: avg=3.81ms   min=620.25µs med=2.14ms  max=877.01ms p(90)=9.27ms  p(95)=11.05ms
     http_req_failed............: 0.00%   ✓ 0           ✗ 3734730
     http_req_receiving.........: avg=171.56µs min=9.5µs    med=35.94µs max=135.66ms p(90)=311.6µs p(95)=591.61µs
     http_req_sending...........: avg=29.87µs  min=8.09µs   med=20.17µs max=65.41ms  p(90)=37.45µs p(95)=45.44µs
     http_req_tls_handshaking...: avg=401ns    min=0s       med=0s      max=42.35ms  p(90)=0s      p(95)=0s
     http_req_waiting...........: avg=3.61ms   min=0s       med=2.01ms  max=876.2ms  p(90)=9.01ms  p(95)=10.64ms
     http_reqs..................: 3734730 2134.107853/s
     iteration_duration.........: avg=23.43ms  min=8.49ms   med=20.74ms max=2.12s    p(90)=34.83ms p(95)=42.32ms
     iterations.................: 622455  355.684642/s
     vus........................: 1       min=1         max=14
     vus_max....................: 14      min=14        max=14

```
