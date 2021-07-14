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