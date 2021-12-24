
# 부하테스트 결과
1. smoke test
```
  ✓ response successfully
  ✓ get Paths in successfully

     checks.........................: 100.00% ✓ 1198      ✗ 0   
     data_received..................: 2.4 MB  243 kB/s
     data_sent......................: 112 kB  11 kB/s
     http_req_blocked...............: avg=82.13µs  min=1µs     med=3µs     max=14.76ms p(90)=6µs      p(95)=7µs    
     http_req_connecting............: avg=77.53µs  min=0s      med=0s      max=14.63ms p(90)=0s       p(95)=0s     
   ✓ http_req_duration..............: avg=8.09ms   min=5.09ms  med=7.01ms  max=42.46ms p(90)=11.11ms  p(95)=13.86ms
       { expected_response:true }...: avg=8.09ms   min=5.09ms  med=7.01ms  max=42.46ms p(90)=11.11ms  p(95)=13.86ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 1198
     http_req_receiving.............: avg=142.29µs min=20µs    med=83µs    max=5.44ms  p(90)=317.29µs p(95)=368µs  
     http_req_sending...............: avg=20.48µs  min=5µs     med=19µs    max=143µs   p(90)=31µs     p(95)=34µs   
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s      p(90)=0s       p(95)=0s     
     http_req_waiting...............: avg=7.93ms   min=5.05ms  med=6.85ms  max=42.14ms p(90)=10.86ms  p(95)=13.67ms
     http_reqs......................: 1198    119.55669/s
     iteration_duration.............: avg=16.71ms  min=11.52ms med=15.05ms max=49.21ms p(90)=22.72ms  p(95)=27.49ms
     iterations.....................: 599     59.778345/s
     vus............................: 1       min=1       max=1 
     vus_max........................: 1       min=1       max=1 
```

2. load test
```
   ✓ response successfully
     ✓ get Paths in successfully

     checks.........................: 100.00% ✓ 95114       ✗ 0    
     data_received..................: 193 MB  2.6 MB/s
     data_sent......................: 8.9 MB  118 kB/s
     http_req_blocked...............: avg=532.2µs  min=1µs     med=2µs     max=142.78ms p(90)=5µs      p(95)=5µs     
     http_req_connecting............: avg=528.65µs min=0s      med=0s      max=142.69ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=30.36ms  min=4.96ms  med=30.74ms max=853.58ms p(90)=46.93ms  p(95)=55.14ms 
       { expected_response:true }...: avg=30.36ms  min=4.96ms  med=30.74ms max=853.58ms p(90)=46.93ms  p(95)=55.14ms 
     http_req_failed................: 0.00%   ✓ 0           ✗ 95114
     http_req_receiving.............: avg=560.13µs min=12µs    med=43µs    max=459.76ms p(90)=490.69µs p(95)=1.4ms   
     http_req_sending...............: avg=14.95µs  min=4µs     med=12µs    max=1.52ms   p(90)=22µs     p(95)=29µs    
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s       p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=29.78ms  min=4.93ms  med=30.47ms max=788.46ms p(90)=46.23ms  p(95)=54.58ms 
     http_reqs......................: 95114   1266.361215/s
     iteration_duration.............: avg=62.06ms  min=11.61ms med=63.52ms max=1.1s     p(90)=93.8ms   p(95)=100.95ms
     iterations.....................: 47557   633.180608/s
     vus............................: 99      min=1         max=99 
     vus_max........................: 100     min=100       max=100
 
```

3. stress test
```
✓ response successfully
✓ get Paths in successfully

     checks.........................: 100.00% ✓ 19206       ✗ 0    
     data_received..................: 39 MB   3.8 MB/s
     data_sent......................: 1.8 MB  177 kB/s
     http_req_blocked...............: avg=1.33ms   min=1µs     med=2µs      max=1.09s p(90)=3µs      p(95)=3µs     
     http_req_connecting............: avg=1.33ms   min=0s      med=0s       max=1.09s p(90)=0s       p(95)=0s      
    ✗ http_req_duration..............: avg=77.08ms  min=6.8ms   med=59.09ms  max=4.77s p(90)=88.13ms  p(95)=125.02ms
    { expected_response:true }...: avg=77.08ms  min=6.8ms   med=59.09ms  max=4.77s p(90)=88.13ms  p(95)=125.02ms
    http_req_failed................: 0.00%   ✓ 0           ✗ 19206
    http_req_receiving.............: avg=4.09ms   min=11µs    med=33µs     max=1.31s p(90)=863.49µs p(95)=9.39ms  
    http_req_sending...............: avg=11.84µs  min=4µs     med=10µs     max=657µs p(90)=15µs     p(95)=19µs    
    http_req_tls_handshaking.......: avg=0s       min=0s      med=0s       max=0s    p(90)=0s       p(95)=0s      
    http_req_waiting...............: avg=72.98ms  min=6.49ms  med=58.39ms  max=4.73s p(90)=84.18ms  p(95)=104.63ms
    http_reqs......................: 19206   1892.437689/s
    iteration_duration.............: avg=157.07ms min=19.04ms med=121.25ms max=5.38s p(90)=172.15ms p(95)=337.58ms
    iterations.....................: 9603    946.218844/s
    vus............................: 150     min=150       max=150
    vus_max........................: 150     min=150       max=150
```
