### 개선 전
##설정
       export let options = {stages: [{
            duration:'1m', target:50
        },{
            duration:'2m', target:100
        },{
            duration:'10s', target:150
        },{
            duration:'30s', target:50
        }, ],
        thresholds:
        {
            http_req_duration: [
            'p(95)<1500'], // 95% of requests must complete below 1.5s 'logged in successfully': ['p(95)<1500'], // 95% of requests must complete below 1.5s }, };
        }
##작업
    //login 요청
    //정보확인
    //역 조회
    //노선 조회
    //경로 조회


execution: local
script: load.js
output: -

scenarios: (100.00%) 1 scenario, 150 max VUs, 4m10s max duration (incl. graceful stop):
* default: Up to 150 looping VUs for 3m40s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m41.0s), 000/150 VUs, 14427 complete and 0 interrupted iterations
default ✓ [======================================] 000/150 VUs  3m40s

     ✓ logged in successfully
     ✓ retrieved member

     checks.........................: 100.00% ✓ 28854      ✗ 0    
     data_received..................: 24 MB   106 kB/s
     data_sent......................: 12 MB   56 kB/s
     http_req_blocked...............: avg=31.19µs  min=2.84µs   med=4.44µs  max=73.06ms  p(90)=6.29µs  p(95)=10.43µs 
     http_req_connecting............: avg=2.79µs   min=0s       med=0s      max=36.39ms  p(90)=0s      p(95)=0s      
✓ http_req_duration..............: avg=4.24ms   min=995.66µs med=2.33ms  max=194.45ms p(90)=9.34ms  p(95)=14.1ms  
{ expected_response:true }...: avg=3.57ms   min=995.66µs med=1.71ms  max=143.51ms p(90)=8ms     p(95)=12.25ms
http_req_failed................: 60.00%  ✓ 43281      ✗ 28854
http_req_receiving.............: avg=111.33µs min=19.53µs  med=42.65µs max=58.7ms   p(90)=77.66µs p(95)=195.15µs
http_req_sending...............: avg=60.69µs  min=8.32µs   med=13.62µs max=42.51ms  p(90)=36.24µs p(95)=65.02µs
http_req_tls_handshaking.......: avg=12.51µs  min=0s       med=0s      max=36.58ms  p(90)=0s      p(95)=0s      
http_req_waiting...............: avg=4.07ms   min=940.36µs med=2.24ms  max=194.4ms  p(90)=8.98ms  p(95)=13.55ms
http_reqs......................: 72135   326.420777/s
iteration_duration.............: avg=1.02s    min=1s       med=1.01s   max=1.28s    p(90)=1.05s   p(95)=1.06s   
iterations.....................: 14427   65.284155/s
vus............................: 53      min=1        max=150
vus_max........................: 150     min=150      max=150