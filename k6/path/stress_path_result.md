# 개선 전
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress-path.js
     output: -

  scenarios: (100.00%) 1 scenario, 200 max VUs, 1m35s max duration (incl. graceful stop):
           * default: Up to 200 looping VUs for 1m5s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m05.6s), 000/200 VUs, 5585 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  1m5s

     ✓ paths

     checks.........................: 100.00% ✓ 5585      ✗ 0    
     data_received..................: 13 MB   200 kB/s
     data_sent......................: 821 kB  13 kB/s
     http_req_blocked...............: avg=204.47µs min=2µs     med=6µs      max=16.98ms p(90)=8µs    p(95)=21µs  
     http_req_connecting............: avg=193µs    min=0s      med=0s       max=9.5ms   p(90)=0s     p(95)=0s    
   ✓ http_req_duration..............: avg=371.02ms min=40.36ms med=158.54ms max=2.6s    p(90)=1.23s  p(95)=1.3s  
       { expected_response:true }...: avg=371.02ms min=40.36ms med=158.54ms max=2.6s    p(90)=1.23s  p(95)=1.3s  
     http_req_failed................: 0.00%   ✓ 0         ✗ 5585 
     http_req_receiving.............: avg=1.55ms   min=21µs    med=89µs     max=183.1ms p(90)=3.44ms p(95)=7.66ms
     http_req_sending...............: avg=28.86µs  min=7µs     med=26µs     max=488µs   p(90)=39µs   p(95)=53µs  
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s       max=0s      p(90)=0s     p(95)=0s    
     http_req_waiting...............: avg=369.44ms min=40.26ms med=157.39ms max=2.6s    p(90)=1.23s  p(95)=1.3s  
     http_reqs......................: 5585    85.086226/s
     iteration_duration.............: avg=1.37s    min=1.04s   med=1.15s    max=3.6s    p(90)=2.23s  p(95)=2.3s  
     iterations.....................: 5585    85.086226/s
     vus............................: 34      min=10      max=200
     vus_max........................: 200     min=200     max=200

```

# 개선 후
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress-path.js
     output: -

  scenarios: (100.00%) 1 scenario, 200 max VUs, 1m35s max duration (incl. graceful stop):
           * default: Up to 200 looping VUs for 1m5s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m06.0s), 000/200 VUs, 7151 complete and 0 interrupted iterations
default ✓ [======================================] 000/200 VUs  1m5s

     ✓ paths

     checks.........................: 100.00% ✓ 7151       ✗ 0    
     data_received..................: 17 MB   255 kB/s
     data_sent......................: 1.1 MB  16 kB/s
     http_req_blocked...............: avg=1.43ms   min=1µs     med=6µs     max=146.4ms  p(90)=8µs     p(95)=14.49µs
     http_req_connecting............: avg=1.4ms    min=0s      med=0s      max=146.18ms p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=60.76ms  min=27.53ms med=55.48ms max=964.83ms p(90)=81.56ms p(95)=95.9ms 
       { expected_response:true }...: avg=60.76ms  min=27.53ms med=55.48ms max=964.83ms p(90)=81.56ms p(95)=95.9ms 
     http_req_failed................: 0.00%   ✓ 0          ✗ 7151 
     http_req_receiving.............: avg=675.89µs min=17µs    med=74µs    max=86.31ms  p(90)=1.46ms  p(95)=4.64ms 
     http_req_sending...............: avg=28.89µs  min=6µs     med=24µs    max=467µs    p(90)=39µs    p(95)=64µs   
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=60.06ms  min=27.46ms med=54.78ms max=964.66ms p(90)=80.48ms p(95)=95.36ms
     http_reqs......................: 7151    108.423496/s
     iteration_duration.............: avg=1.06s    min=1.02s   med=1.05s   max=2.08s    p(90)=1.08s   p(95)=1.1s   
     iterations.....................: 7151    108.423496/s
     vus............................: 18      min=10       max=200
     vus_max........................: 200     min=200      max=200

```