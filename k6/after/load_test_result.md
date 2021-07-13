[뒤로가기](../../markdown/step1.md)

```http

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_test.js
     output: -

  scenarios: (100.00%) 1 scenario, 240 max VUs, 1m5s max duration (incl. graceful stop):
           * default: Up to 240 looping VUs for 35s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m35.8s), 000/240 VUs, 5623 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get shortest line success

     checks.........................: 100.00% ✓ 16869      ✗ 0
     data_received..................: 9.4 MB  262 kB/s
     data_sent......................: 2.8 MB  79 kB/s
     http_req_blocked...............: avg=435.77µs min=0s     med=1µs     max=160.05ms p(90)=1µs      p(95)=1µs
     http_req_connecting............: avg=122.16µs min=0s     med=0s      max=31.66ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=65ms     min=9.8ms  med=46.3ms  max=464.32ms p(90)=141.29ms p(95)=178.65ms
       { expected_response:true }...: avg=65ms     min=9.8ms  med=46.3ms  max=464.32ms p(90)=141.29ms p(95)=178.65ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 16869
     http_req_receiving.............: avg=71.3µs   min=9µs    med=58µs    max=3.8ms    p(90)=118µs    p(95)=154µs
     http_req_sending...............: avg=64.19µs  min=6µs    med=44µs    max=4.16ms   p(90)=124µs    p(95)=175µs
     http_req_tls_handshaking.......: avg=309.73µs min=0s     med=0s      max=151.02ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=64.86ms  min=9.69ms med=46.19ms max=464.23ms p(90)=141.21ms p(95)=178.5ms
     http_reqs......................: 16869   470.566247/s
     iteration_duration.............: avg=1.19s    min=1.03s  med=1.15s   max=1.84s    p(90)=1.39s    p(95)=1.45s
     iterations.....................: 5623    156.855416/s
     vus............................: 29      min=24       max=240
     vus_max........................: 240     min=240      max=240

```