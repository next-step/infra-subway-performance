
# 부하테스트 결과
1. smoke test
```
   ✓ response successfully
     ✓ get Paths in successfully

     checks.........................: 100.00% ✓ 490       ✗ 0  
     data_received..................: 1.0 MB  100 kB/s
     data_sent......................: 46 kB   4.6 kB/s
     http_req_blocked...............: avg=66.81µs min=1µs     med=2µs     max=8.57ms   p(90)=3µs     p(95)=4µs     
     http_req_connecting............: avg=63.96µs min=0s      med=0s      max=8.52ms   p(90)=0s      p(95)=0s      
   ✓ http_req_duration..............: avg=20.25ms min=3.85ms  med=29.93ms max=116.69ms p(90)=35.91ms p(95)=40.3ms  
       { expected_response:true }...: avg=20.25ms min=3.85ms  med=29.93ms max=116.69ms p(90)=35.91ms p(95)=40.3ms  
     http_req_failed................: 0.00%   ✓ 0         ✗ 490
     http_req_receiving.............: avg=407.6µs min=18µs    med=59µs    max=30.45ms  p(90)=422.1µs p(95)=472.19µs
     http_req_sending...............: avg=12.93µs min=5µs     med=11µs    max=406µs    p(90)=18.1µs  p(95)=28µs    
     http_req_tls_handshaking.......: avg=0s      min=0s      med=0s      max=0s       p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=19.83ms min=3.82ms  med=29.72ms max=109.36ms p(90)=34.98ms p(95)=39.11ms 
     http_reqs......................: 490     48.926131/s
     iteration_duration.............: avg=40.86ms min=34.35ms med=37.4ms  max=125.52ms p(90)=48.05ms p(95)=54.64ms 
     iterations.....................: 245     24.463066/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1

```

2. load test
```
   ✓ response successfully
     ✓ get Paths in successfully

     checks.........................: 100.00% ✓ 16666      ✗ 0    
     data_received..................: 34 MB   450 kB/s
     data_sent......................: 1.6 MB  21 kB/s
     http_req_blocked...............: avg=123.84µs min=1µs    med=3µs     max=104.81ms p(90)=5µs      p(95)=6µs     
     http_req_connecting............: avg=119.86µs min=0s     med=0s      max=104.7ms  p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=178.55ms min=3.5ms  med=33.73ms max=1.98s    p(90)=621.89ms p(95)=701.59ms
       { expected_response:true }...: avg=178.55ms min=3.5ms  med=33.73ms max=1.98s    p(90)=621.89ms p(95)=701.59ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 16666
     http_req_receiving.............: avg=1.2ms    min=14µs   med=84µs    max=299.9ms  p(90)=1.7ms    p(95)=5.89ms  
     http_req_sending...............: avg=17.08µs  min=4µs    med=14µs    max=337µs    p(90)=30µs     p(95)=32µs    
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s      max=0s       p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=177.33ms min=3.46ms med=33.41ms max=1.98s    p(90)=617.89ms p(95)=696.61ms
     http_reqs......................: 16666   220.168063/s
     iteration_duration.............: avg=357.68ms min=34.6ms med=331.5ms max=1.99s    p(90)=716.56ms p(95)=781.64ms
     iterations.....................: 8333    110.084032/s
     vus............................: 99      min=1        max=99 
     vus_max........................: 100     min=100      max=100

 
```

3. stress test
```
 ✗ response successfully
      ↳  96% — ✓ 1395 / ✗ 50
     ✗ get Paths in successfully
      ↳  96% — ✓ 1395 / ✗ 50

     checks.........................: 96.53% ✓ 2790       ✗ 100  
     data_received..................: 5.8 MB 533 kB/s
     data_sent......................: 270 kB 25 kB/s
     http_req_blocked...............: avg=6.39ms   min=1µs     med=3µs      max=135.5ms  p(90)=7µs    p(95)=77.12ms
     http_req_connecting............: avg=6.38ms   min=0s      med=0s       max=135.41ms p(90)=0s     p(95)=77.02ms
   ✗ http_req_duration..............: avg=536.38ms min=3.77ms  med=264.25ms max=2.2s     p(90)=1.13s  p(95)=1.17s  
       { expected_response:true }...: avg=542.04ms min=3.77ms  med=296.09ms max=2.2s     p(90)=1.13s  p(95)=1.17s  
     http_req_failed................: 1.73%  ✓ 50         ✗ 2840 
     http_req_receiving.............: avg=1.64ms   min=0s      med=89µs     max=461.52ms p(90)=3.11ms p(95)=8.57ms 
     http_req_sending...............: avg=23.73µs  min=5µs     med=17µs     max=347µs    p(90)=34µs   p(95)=71µs   
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s       max=0s       p(90)=0s     p(95)=0s     
     http_req_waiting...............: avg=534.7ms  min=3.72ms  med=263.89ms max=2.2s     p(90)=1.13s  p(95)=1.17s  
     http_reqs......................: 2890   263.521815/s
     iteration_duration.............: avg=1.08s    min=59.57ms med=1.11s    max=2.21s    p(90)=1.2s   p(95)=1.23s  
     iterations.....................: 1445   131.760907/s
     vus............................: 150    min=150      max=150
     vus_max........................: 150    min=150      max=150
```
