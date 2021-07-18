### 개선 전
##설정
    export let options = {stages: [{ duration:'1m', target:100},
    {
        duration:
        '20s', target:300
    },{
        duration:
        '10s', target:400
    },{
        duration:
        '30s', target:300
    },{
        duration:
        '10s', target:100
    },{
        duration:
        '10s', target:500
    },{
        duration:
        '10s', target:0
    }, ],thresholds:
        {
        http_req_duration: [
        'p(90)<1500', 'p(95)<3000'], // 95% of requests must complete below 1.5s 'logged in successfully': ['p(95)<1500'], // 95% of requests must complete below 1.5s 
        }
    };
##작업
    //login 요청
    //정보확인
    //역 조회
    //노선 조회
    //경로 조회

running (2m30.9s), 000/500 VUs, 22476 complete and 0 interrupted iterations
default ✓ [======================================] 000/500 VUs  2m30s

     ✗ logged in successfully
      ↳  98% — ✓ 22069 / ✗ 407
     ✓ retrieved member

     checks.........................: 99.08% ✓ 44138      ✗ 407  
     data_received..................: 48 MB  316 kB/s
     data_sent......................: 20 MB  133 kB/s
     http_req_blocked...............: avg=6.77ms   min=0s     med=4.31µs  max=944.36ms p(90)=7.06µs   p(95)=46.05µs 
     http_req_connecting............: avg=1.94ms   min=0s     med=0s      max=456.87ms p(90)=0s       p(95)=0s      
✓ http_req_duration..............: avg=53.05ms  min=0s     med=28.35ms max=1.35s    p(90)=140.48ms p(95)=187.2ms
{ expected_response:true }...: avg=48.93ms  min=1.01ms med=26.43ms max=1.29s    p(90)=130.06ms p(95)=172.47ms
http_req_failed................: 60.14% ✓ 66614      ✗ 44138
http_req_receiving.............: avg=634.82µs min=0s     med=36.1µs  max=350.7ms  p(90)=135.69µs p(95)=508.25µs
http_req_sending...............: avg=1.54ms   min=0s     med=13.8µs  max=1.16s    p(90)=75.7µs   p(95)=1.17ms  
http_req_tls_handshaking.......: avg=4.58ms   min=0s     med=0s      max=757.42ms p(90)=0s       p(95)=0s      
http_req_waiting...............: avg=50.87ms  min=0s     med=27.63ms max=1.16s    p(90)=135.25ms p(95)=179.5ms
http_reqs......................: 110752 733.714216/s
iteration_duration.............: avg=1.29s    min=1.14ms med=1.18s   max=4.01s    p(90)=1.77s    p(95)=2.02s   
iterations.....................: 22476  148.899891/s
vus............................: 25     min=2        max=500
vus_max........................: 500    min=500      max=500

###개선 후 

default ✓ [======================================] 000/500 VUs  2m30s

     ✗ logged in successfully
      ↳  97% — ✓ 21737 / ✗ 531
     ✓ retrieved member

     checks.........................: 98.79% ✓ 43474      ✗ 531  
     data_received..................: 49 MB  326 kB/s
     data_sent......................: 20 MB  132 kB/s
     http_req_blocked...............: avg=8.6ms    min=0s       med=4.4µs   max=986.67ms p(90)=7.81µs   p(95)=77.95µs 
     http_req_connecting............: avg=2.44ms   min=0s       med=0s      max=379.04ms p(90)=0s       p(95)=0s      
✓ http_req_duration..............: avg=55.15ms  min=0s       med=25.47ms max=1.21s    p(90)=145.1ms  p(95)=194ms   
{ expected_response:true }...: avg=51.17ms  min=975.02µs med=22.91ms max=1.21s    p(90)=132.88ms p(95)=176.78ms
http_req_failed................: 60.20% ✓ 65742      ✗ 43461
http_req_receiving.............: avg=669.38µs min=0s       med=36.2µs  max=308.89ms p(90)=119.73µs p(95)=503.2µs
http_req_sending...............: avg=1.65ms   min=0s       med=13.88µs max=976.45ms p(90)=82.86µs  p(95)=1.37ms  
http_req_tls_handshaking.......: avg=5.86ms   min=0s       med=0s      max=767.88ms p(90)=0s       p(95)=0s      
http_req_waiting...............: avg=52.82ms  min=0s       med=24.72ms max=1.21s    p(90)=139.49ms p(95)=183.7ms
http_reqs......................: 109203 723.564645/s
iteration_duration.............: avg=1.3s     min=4.61ms   med=1.15s   max=3.96s    p(90)=1.85s    p(95)=2.18s   
iterations.....................: 22268  147.544825/s
vus............................: 28     min=2        max=500
vus_max........................: 500    min=500      max=500