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


running (0m39.2s), 000/240 VUs, 1173 complete and 0 interrupted iterations
default ✓ [======================================] 000/240 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ get shortest line success

     checks.........................: 100.00% ✓ 3519      ✗ 0
     data_received..................: 3.2 MB  82 kB/s
     data_sent......................: 1.1 MB  29 kB/s
     http_req_blocked...............: avg=2.05ms   min=0s      med=5µs   max=200.68ms p(90)=13µs  p(95)=23.23ms
     http_req_connecting............: avg=597.97µs min=0s      med=0s    max=141.22ms p(90)=0s    p(95)=6.54ms
   ✗ http_req_duration..............: avg=1.77s    min=13.39ms med=2.05s max=6.54s    p(90)=2.45s p(95)=2.58s
       { expected_response:true }...: avg=1.77s    min=13.39ms med=2.05s max=6.54s    p(90)=2.45s p(95)=2.58s
     http_req_failed................: 0.00%   ✓ 0         ✗ 3519
     http_req_receiving.............: avg=90.09µs  min=9µs     med=83µs  max=614µs    p(90)=143µs p(95)=160.09µs
     http_req_sending...............: avg=35.31µs  min=3µs     med=29µs  max=208µs    p(90)=60µs  p(95)=86µs
     http_req_tls_handshaking.......: avg=1.44ms   min=0s      med=0s    max=193.07ms p(90)=0s    p(95)=16.29ms
     http_req_waiting...............: avg=1.77s    min=13.27ms med=2.05s max=6.54s    p(90)=2.45s p(95)=2.58s
     http_reqs......................: 3519    89.853337/s
     iteration_duration.............: avg=6.32s    min=1.09s   med=7.34s max=11.96s   p(90)=7.86s p(95)=9.61s
     iterations.....................: 1173    29.951112/s
     vus............................: 13      min=13      max=240
     vus_max........................: 240     min=240     max=240

ERRO[0040] some thresholds have failed
```