## 결과

```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: ./smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (01.0s), 1/1 VUs, 2 complete and 0 interrupted iterations
default   [  10% ] 1 VUs  01.0s/10s

running (02.0s), 1/1 VUs, 16 complete and 0 interrupted iterations
default   [  20% ] 1 VUs  02.0s/10s

running (03.0s), 1/1 VUs, 32 complete and 0 interrupted iterations
default   [  30% ] 1 VUs  03.0s/10s

running (04.0s), 1/1 VUs, 48 complete and 0 interrupted iterations
default   [  40% ] 1 VUs  04.0s/10s

running (05.0s), 1/1 VUs, 64 complete and 0 interrupted iterations
default   [  50% ] 1 VUs  05.0s/10s

running (06.0s), 1/1 VUs, 84 complete and 0 interrupted iterations
default   [  60% ] 1 VUs  06.0s/10s

running (07.0s), 1/1 VUs, 102 complete and 0 interrupted iterations
default   [  70% ] 1 VUs  07.0s/10s

running (08.0s), 1/1 VUs, 122 complete and 0 interrupted iterations
default   [  80% ] 1 VUs  08.0s/10s

running (09.0s), 1/1 VUs, 146 complete and 0 interrupted iterations
default   [  90% ] 1 VUs  09.0s/10s

running (10.0s), 1/1 VUs, 172 complete and 0 interrupted iterations
default   [ 100% ] 1 VUs  10.0s/10s

running (10.1s), 0/1 VUs, 173 complete and 0 interrupted iterations
default ✓ [ 100% ] 1 VUs  10s

     ✓ logged in successfully

     checks.........................: 100.00% ✓ 173       ✗ 0  
     data_received..................: 83 kB   8.2 kB/s
     data_sent......................: 31 kB   3.1 kB/s
     http_req_blocked...............: avg=42.28µs min=242ns   med=313ns   max=14.51ms  p(90)=389ns    p(95)=419ns   
     http_req_connecting............: avg=587ns   min=0s      med=0s      max=203.36µs p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=28.87ms min=1.22ms  med=2.32ms  max=787.05ms p(90)=105.2ms  p(95)=121.02ms
       { expected_response:true }...: avg=37.94ms min=2.09ms  med=2.4ms   max=787.05ms p(90)=111.64ms p(95)=126.22ms
     http_req_failed................: 24.85%  ✓ 86        ✗ 260
     http_req_receiving.............: avg=54.96µs min=19.49µs med=54.03µs max=506.15µs p(90)=71.76µs  p(95)=86.71µs 
     http_req_sending...............: avg=49.94µs min=21.54µs med=44.72µs max=152.99µs p(90)=81.68µs  p(95)=84.98µs 
     http_req_tls_handshaking.......: avg=34.1µs  min=0s      med=0s      max=11.8ms   p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=28.77ms min=1.18ms  med=2.2ms   max=786.94ms p(90)=105.09ms p(95)=120.92ms
     http_reqs......................: 346     34.384211/s
     iteration_duration.............: avg=58.14ms min=3.68ms  med=67.86ms max=805.18ms p(90)=123.74ms p(95)=142.38ms
     iterations.....................: 173     17.192105/s
     vus............................: 1       min=1       max=1
     vus_max........................: 1       min=1       max=1

```
