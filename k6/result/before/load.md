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


running (1m02.0s), 000/150 VUs, 2702 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  1m0s

     ✓ pathPage success
     ✓ getPath success

   ✓ checks.........................: 100.00% ✓ 5404      ✗ 0
     data_received..................: 5.4 MB  87 kB/s
     data_sent......................: 768 kB  12 kB/s
     http_req_blocked...............: avg=664.16µs min=1µs    med=4µs     max=337.26ms p(90)=7µs     p(95)=10µs
     http_req_connecting............: avg=288.64µs min=0s     med=0s      max=324.01ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=47.18ms  min=3.96ms med=52.35ms max=566.04ms p(90)=95.9ms  p(95)=138.16ms
       { expected_response:true }...: avg=47.18ms  min=3.96ms med=52.35ms max=566.04ms p(90)=95.9ms  p(95)=138.16ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 5404
     http_req_receiving.............: avg=50.52µs  min=18µs   med=44µs    max=2.84ms   p(90)=77µs    p(95)=93µs
     http_req_sending...............: avg=19.89µs  min=6µs    med=17µs    max=877µs    p(90)=33µs    p(95)=38µs
     http_req_tls_handshaking.......: avg=368.76µs min=0s     med=0s      max=151.78ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=47.11ms  min=3.91ms med=52.26ms max=565.99ms p(90)=95.85ms p(95)=138.08ms
     http_reqs......................: 5404    87.149001/s
     iteration_duration.............: avg=2.09s    min=2.05s  med=2.07s   max=2.57s    p(90)=2.14s   p(95)=2.21s
     iterations.....................: 2702    43.5745/s
     vus............................: 2       min=2       max=149
     vus_max........................: 150     min=150     max=150

```
