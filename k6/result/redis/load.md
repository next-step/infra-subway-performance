```javascript

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 150 max VUs, 1m30s max duration (incl. graceful stop):
           * default: Up to 150 looping VUs for 1m0s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m02.0s), 000/150 VUs, 2797 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m0s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 5594      ✗ 0
     data_received..................: 5.1 MB  82 kB/s
     data_sent......................: 328 kB  5.3 kB/s
     http_req_blocked...............: avg=543.2µs  min=0s     med=1µs    max=209.79ms p(90)=1µs     p(95)=1µs
     http_req_connecting............: avg=176.87µs min=0s     med=0s     max=195.68ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=9.46ms   min=4.22ms med=6.98ms max=400.06ms p(90)=10.96ms p(95)=14.08ms
       { expected_response:true }...: avg=9.46ms   min=4.22ms med=6.98ms max=400.06ms p(90)=10.96ms p(95)=14.08ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 5594
     http_req_receiving.............: avg=41.03µs  min=14µs   med=36µs   max=504µs    p(90)=64µs    p(95)=78µs
     http_req_sending...............: avg=66.55µs  min=20µs   med=59µs   max=1ms      p(90)=106µs   p(95)=124µs
     http_req_tls_handshaking.......: avg=361.35µs min=0s     med=0s     max=140.15ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=9.36ms   min=4.14ms med=6.86ms max=399.97ms p(90)=10.87ms p(95)=13.97ms
     http_reqs......................: 5594    90.229573/s
     iteration_duration.............: avg=2.02s    min=2s     med=2.01s  max=2.42s    p(90)=2.02s   p(95)=2.03s
     iterations.....................: 2797    45.114786/s
     vus............................: 27      min=5       max=150
     vus_max........................: 150     min=150     max=150

```
