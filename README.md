<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 인프라공방 샘플 서비스 - 지하철 노선도

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew clean build
```
<br>

## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.

### 1단계 - 화면 응답 개선하기
#### 1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
<details>
    <summary> 적용 전  테스트 결과 click </summary>
   
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke_line.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
           * default: 1 looping VUs for 10s (gracefulStop: 30s)


running (10.9s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 10  ✗ 0  
     data_received..................: 61 kB   5.6 kB/s
     data_sent......................: 1.5 kB  141 B/s
     http_req_blocked...............: avg=2.18ms  min=3.91µs  med=4.47µs  max=21.76ms  p(90)=2.18ms   p(95)=11.97ms 
     http_req_connecting............: avg=25.71µs min=0s      med=0s      max=257.16µs p(90)=25.71µs  p(95)=141.43µs
   ✓ http_req_duration..............: avg=90.63ms min=59.89ms med=96.41ms max=107.22ms p(90)=101.47ms p(95)=104.34ms
       { expected_response:true }...: avg=90.63ms min=59.89ms med=96.41ms max=107.22ms p(90)=101.47ms p(95)=104.34ms
     http_req_failed................: 0.00%   ✓ 0   ✗ 10 
     http_req_receiving.............: avg=89.48µs min=79.29µs med=87.54µs max=103.25µs p(90)=99.2µs   p(95)=101.22µs
     http_req_sending...............: avg=33.47µs min=24.84µs med=27.09µs max=88.39µs  p(90)=37.72µs  p(95)=63.05µs 
     http_req_tls_handshaking.......: avg=2.1ms   min=0s      med=0s      max=21.03ms  p(90)=2.1ms    p(95)=11.56ms 
     http_req_waiting...............: avg=90.51ms min=59.77ms med=96.3ms  max=107.11ms p(90)=101.34ms p(95)=104.23ms
     http_reqs......................: 10      0.914347/s
     iteration_duration.............: avg=1.09s   min=1.06s   med=1.09s   max=1.1s     p(90)=1.1s     p(95)=1.1s    
     iterations.....................: 10      0.914347/s
     vus............................: 1       min=1 max=1
     vus_max........................: 1       min=1 max=1

        
```
   
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_line.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m12.9s), 000/100 VUs, 2265 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 2265  ✗ 0    
     data_received..................: 13 MB   68 kB/s
     data_sent......................: 303 kB  1.6 kB/s
     http_req_blocked...............: avg=210.64µs min=1.79µs  med=4.57µs  max=36.26ms  p(90)=7.06µs   p(95)=21.92µs 
     http_req_connecting............: avg=13.23µs  min=0s      med=0s      max=1.06ms   p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=6.01s    min=52.82ms med=7.05s   max=13.7s    p(90)=7.69s    p(95)=7.85s   
       { expected_response:true }...: avg=6.01s    min=52.82ms med=7.05s   max=13.7s    p(90)=7.69s    p(95)=7.85s   
     http_req_failed................: 0.00%   ✓ 0     ✗ 2265 
     http_req_receiving.............: avg=87.03µs  min=31.34µs med=83.65µs max=995.99µs p(90)=106.97µs p(95)=115.79µs
     http_req_sending...............: avg=28.11µs  min=9.01µs  med=26.63µs max=385.23µs p(90)=34.65µs  p(95)=48.59µs 
     http_req_tls_handshaking.......: avg=182.58µs min=0s      med=0s      max=21.76ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=6.01s    min=52.69ms med=7.05s   max=13.7s    p(90)=7.69s    p(95)=7.85s   
     http_reqs......................: 2265    11.739341/s
     iteration_duration.............: avg=7.01s    min=1.05s   med=8.06s   max=14.7s    p(90)=8.69s    p(95)=8.86s   
     iterations.....................: 2265    11.739341/s
     vus............................: 17      min=2   max=100
     vus_max........................: 100     min=100 max=100

ERRO[0194] some thresholds have failed                  
```
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_line.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 1m8s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 38s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)

WARN[0021] Request Failed                                error="Get \"https://mysubway.kro.kr/lines/3\": EOF"
WARN[0021] Request Failed                                error="Get \"https://mysubway.kro.kr/lines/3\": read tcp 192.130.0.190:60514->3.37.14.163:443: read: connection reset by peer"
WARN[0022] Request Failed                                error="Get \"https://mysubway.kro.kr/lines/3\": read tcp 192.130.0.190:60786->3.37.14.163:443: read: connection reset by peer"

...

running (0m38.8s), 000/400 VUs, 3961 complete and 0 interrupted iterations
default ✓ [======================================] 000/400 VUs  38s

     ✗ load main successfully 
      ↳  84% — ✓ 3330 / ✗ 631

     checks.........................: 84.06% ✓ 3330  ✗ 631  
     data_received..................: 26 MB  666 kB/s
     data_sent......................: 1.3 MB 34 kB/s
     http_req_blocked...............: avg=4.81ms   min=0s      med=3.14ms   max=79.56ms  p(90)=13.47ms  p(95)=20.04ms 
     http_req_connecting............: avg=180.56µs min=0s      med=182.38µs max=22.88ms  p(90)=307.84µs p(95)=423.75µs
   ✗ http_req_duration..............: avg=1.29s    min=0s      med=938.03ms max=9.42s    p(90)=2.74s    p(95)=3.36s   
       { expected_response:true }...: avg=1.53s    min=13.31ms med=1.11s    max=9.42s    p(90)=3.03s    p(95)=3.48s   
     http_req_failed................: 15.93% ✓ 631   ✗ 3330 
     http_req_receiving.............: avg=52.6µs   min=0s      med=54.38µs  max=385.13µs p(90)=84.69µs  p(95)=96.08µs 
     http_req_sending...............: avg=35.74µs  min=0s      med=29.98µs  max=2.85ms   p(90)=58.52µs  p(95)=72.86µs 
     http_req_tls_handshaking.......: avg=4.62ms   min=0s      med=2.86ms   max=75.71ms  p(90)=13.05ms  p(95)=19.64ms 
     http_req_waiting...............: avg=1.28s    min=0s      med=937.98ms max=9.42s    p(90)=2.74s    p(95)=3.36s   
     http_reqs......................: 3961   102.010246/s
     iteration_duration.............: avg=2.29s    min=1s      med=1.93s    max=10.46s   p(90)=3.75s    p(95)=4.37s   
     iterations.....................: 3961   102.010246/s
     vus............................: 19     min=19  max=400
     vus_max........................: 400    min=400 max=400

ERRO[0040] some thresholds have failed                  
```

</details>

#### 2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
#### # 적용 전 load / stress 테스트 진행
- 결과 : load 테스트 시에도 1.5s 이상 걸리는 건도 있고 , stress 테스트는 엄청나게 많은 Request Fail 발생
   
<details>
    <summary> 적용 전 테스트 결과 click </summary>
   
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_line.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m12.9s), 000/100 VUs, 2265 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 2265  ✗ 0    
     data_received..................: 13 MB   68 kB/s
     data_sent......................: 303 kB  1.6 kB/s
     http_req_blocked...............: avg=210.64µs min=1.79µs  med=4.57µs  max=36.26ms  p(90)=7.06µs   p(95)=21.92µs 
     http_req_connecting............: avg=13.23µs  min=0s      med=0s      max=1.06ms   p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=6.01s    min=52.82ms med=7.05s   max=13.7s    p(90)=7.69s    p(95)=7.85s   
       { expected_response:true }...: avg=6.01s    min=52.82ms med=7.05s   max=13.7s    p(90)=7.69s    p(95)=7.85s   
     http_req_failed................: 0.00%   ✓ 0     ✗ 2265 
     http_req_receiving.............: avg=87.03µs  min=31.34µs med=83.65µs max=995.99µs p(90)=106.97µs p(95)=115.79µs
     http_req_sending...............: avg=28.11µs  min=9.01µs  med=26.63µs max=385.23µs p(90)=34.65µs  p(95)=48.59µs 
     http_req_tls_handshaking.......: avg=182.58µs min=0s      med=0s      max=21.76ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=6.01s    min=52.69ms med=7.05s   max=13.7s    p(90)=7.69s    p(95)=7.85s   
     http_reqs......................: 2265    11.739341/s
     iteration_duration.............: avg=7.01s    min=1.05s   med=8.06s   max=14.7s    p(90)=8.69s    p(95)=8.86s   
     iterations.....................: 2265    11.739341/s
     vus............................: 17      min=2   max=100
     vus_max........................: 100     min=100 max=100

ERRO[0194] some thresholds have failed                  
```


</details>
  
####  # 성능 개선 결과
#### 1) 노선 조회 하는 부분 redis caching 적용
    - load테스트는 실패 건 없이 성공, 그러나 stress 테스트는 여전히 많은 request fail 발생..
    - stress 테스트 시 request fail 발생 하는 부분 어느정도 해결 하고 속도를 측정하고자, 이 부분 위주로 테스트 진행.
   
<details>
       <summary> load 테스트 결과 click </summary>
   
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_line.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.6s), 000/100 VUs, 13757 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 13757 ✗ 0    
     data_received..................: 70 MB   365 kB/s
     data_sent......................: 1.6 MB  8.6 kB/s
     http_req_blocked...............: avg=35.44µs  min=1.44µs  med=3.82µs   max=21.93ms  p(90)=5.68µs   p(95)=6.41µs  
     http_req_connecting............: avg=2.21µs   min=0s      med=0s       max=2.51ms   p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=130.29ms min=12.79ms med=105.45ms max=790.24ms p(90)=256.87ms p(95)=318.07ms
       { expected_response:true }...: avg=130.29ms min=12.79ms med=105.45ms max=790.24ms p(90)=256.87ms p(95)=318.07ms
     http_req_failed................: 0.00%   ✓ 0     ✗ 13757
     http_req_receiving.............: avg=62.59µs  min=15.34µs med=56.76µs  max=4.35ms   p(90)=86.07µs  p(95)=99.69µs 
     http_req_sending...............: avg=20.61µs  min=5.95µs  med=16.81µs  max=3.6ms    p(90)=27.43µs  p(95)=32.46µs 
     http_req_tls_handshaking.......: avg=28.41µs  min=0s      med=0s       max=21.22ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=130.21ms min=12.71ms med=105.38ms max=790.13ms p(90)=256.78ms p(95)=318ms   
     http_reqs......................: 13757   72.191629/s
     iteration_duration.............: avg=1.13s    min=1.01s   med=1.1s     max=1.79s    p(90)=1.25s    p(95)=1.31s   
     iterations.....................: 13757   72.191629/s
     vus............................: 8       min=2   max=100
     vus_max........................: 100     min=100 max=100                
```
   
</details>
     
#### 2) nginx 설정 최적화 : 
##### i. worker_process, connection 수정
    - request failed에러 (아래 에러코드)가 기존에는 많이 떴는데, 이제 뜨지 않음. 하지만 여전히 1.5s 이상 걸리는 건들이 존재.
    
```
        WARN[0032] Request Failed                                error="Get \"https://mysubway.kro.kr/lines/3\": EOF"
           WARN[0032] Request Failed                                error="Get \"https://mysubway.kro.kr/lines/3\": read tcp 192.130.0.190:35622->3.37.14.163:443: read: connection reset by peer
```
   
<details>
        <summary> stress 결과 click </summary>
           
```
            
              /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
        /          \   |  |\  \ |  (‾)  | 
        / __________ \  |__| \__\ \_____/ .io
        
        execution: local
         script: stress_line.js
         output: -
        
        scenarios: (100.00%) 1 scenario, 400 max VUs, 1m8s max duration (incl. graceful stop):
               * default: Up to 400 looping VUs for 38s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)
        
        
        running (0m38.8s), 000/400 VUs, 3684 complete and 0 interrupted iterations
        default ✓ [======================================] 000/400 VUs  38s
        
         ✓ load main successfully 
        
         checks.........................: 100.00% ✓ 3684  ✗ 0    
         data_received..................: 20 MB   515 kB/s
         data_sent......................: 688 kB  18 kB/s
         http_req_blocked...............: avg=491.13µs min=209ns   med=362ns    max=40.32ms  p(90)=3.55ms   p(95)=3.9ms   
         http_req_connecting............: avg=36.15µs  min=0s      med=0s       max=12.38ms  p(90)=201.51µs p(95)=238.1µs 
        ✗ http_req_duration..............: avg=1.48s    min=13.28ms med=1.22s    max=7.97s    p(90)=2.86s    p(95)=3.04s   
           { expected_response:true }...: avg=1.48s    min=13.28ms med=1.22s    max=7.97s    p(90)=2.86s    p(95)=3.04s   
         http_req_failed................: 0.00%   ✓ 0     ✗ 3684 
         http_req_receiving.............: avg=5.46ms   min=32.81µs med=768.91µs max=234.42ms p(90)=16.74ms  p(95)=30.72ms 
         http_req_sending...............: avg=39.85µs  min=11.98µs med=29.33µs  max=799.91µs p(90)=85.5µs   p(95)=108.66µs
         http_req_tls_handshaking.......: avg=436.3µs  min=0s      med=0s       max=24.87ms  p(90)=3.19ms   p(95)=3.51ms  
         http_req_waiting...............: avg=1.47s    min=13.06ms med=1.22s    max=7.97s    p(90)=2.86s    p(95)=3.04s   
         http_reqs......................: 3684    94.846509/s
         iteration_duration.............: avg=2.48s    min=1.01s   med=2.23s    max=8.97s    p(90)=3.86s    p(95)=4.04s   
         iterations.....................: 3684    94.846509/s
         vus............................: 24      min=24  max=400
         vus_max........................: 400     min=400 max=400
        
        ERRO[0040] some thresholds have failed     
```
</details>
   
##### ii. http gzip 압축 활성화
      - 여전히 stress 테스트 시에는 1.5s 이상 소요 되는 건 존재.
      - load 테스트로 속도 차이 비교해보고자 함
    
<details>
<summary> stress & load click </summary>
   
   ## stress 
```
    
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_line.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 1m8s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 38s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m38.8s), 000/400 VUs, 3665 complete and 0 interrupted iterations
default ✓ [======================================] 000/400 VUs  38s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 3665  ✗ 0    
     data_received..................: 20 MB   514 kB/s
     data_sent......................: 686 kB  18 kB/s
     http_req_blocked...............: avg=498.07µs min=209ns   med=360ns    max=46.26ms  p(90)=3.26ms   p(95)=3.88ms  
     http_req_connecting............: avg=33.72µs  min=0s      med=0s       max=12.64ms  p(90)=199.74µs p(95)=233.99µs
   ✗ http_req_duration..............: avg=1.49s    min=13.47ms med=1.51s    max=8.16s    p(90)=2.8s     p(95)=2.87s   
       { expected_response:true }...: avg=1.49s    min=13.47ms med=1.51s    max=8.16s    p(90)=2.8s     p(95)=2.87s   
     http_req_failed................: 0.00%   ✓ 0     ✗ 3665 
     http_req_receiving.............: avg=5.97ms   min=34.31µs med=893.46µs max=135.02ms p(90)=18.8ms   p(95)=33.57ms 
     http_req_sending...............: avg=40.84µs  min=11.58µs med=29.77µs  max=1.39ms   p(90)=88.89µs  p(95)=109.76µs
     http_req_tls_handshaking.......: avg=447.26µs min=0s      med=0s       max=36.61ms  p(90)=2.85ms   p(95)=3.51ms  
     http_req_waiting...............: avg=1.48s    min=13.01ms med=1.5s     max=8.16s    p(90)=2.79s    p(95)=2.86s   
     http_reqs......................: 3665    94.340452/s
     iteration_duration.............: avg=2.49s    min=1.01s   med=2.51s    max=9.16s    p(90)=3.8s     p(95)=3.87s   
     iterations.....................: 3665    94.340452/s
     vus............................: 26      min=26  max=400
     vus_max........................: 400     min=400 max=400

ERRO[0040] some thresholds have failed 
```
    
    ## load 
```
    
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_line.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.9s), 000/100 VUs, 14310 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 14310 ✗ 0    
     data_received..................: 71 MB   373 kB/s
     data_sent......................: 1.9 MB  9.8 kB/s
     http_req_blocked...............: avg=27.15µs min=196ns   med=327ns    max=26.61ms  p(90)=535ns    p(95)=582ns   
     http_req_connecting............: avg=1.97µs  min=0s      med=0s       max=479.84µs p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=86.52ms min=11.47ms med=86.5ms   max=303.28ms p(90)=135.71ms p(95)=154.56ms
       { expected_response:true }...: avg=86.52ms min=11.47ms med=86.5ms   max=303.28ms p(90)=135.71ms p(95)=154.56ms
     http_req_failed................: 0.00%   ✓ 0     ✗ 14310
     http_req_receiving.............: avg=3.46ms  min=23.4µs  med=270.44µs max=119.01ms p(90)=9.43ms   p(95)=21.2ms  
     http_req_sending...............: avg=33.1µs  min=11.29µs med=27.8µs   max=3.6ms    p(90)=51.13µs  p(95)=54.85µs 
     http_req_tls_handshaking.......: avg=23.44µs min=0s      med=0s       max=21.45ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=83.03ms min=11ms    med=83.62ms  max=302.6ms  p(90)=128.77ms p(95)=147.85ms
     http_reqs......................: 14310   74.95628/s
     iteration_duration.............: avg=1.08s   min=1.01s   med=1.08s    max=1.3s     p(90)=1.13s    p(95)=1.15s   
     iterations.....................: 14310   74.95628/s
     vus............................: 5       min=2   max=100
     vus_max........................: 100     min=100 max=100


```
    
</details>    

#####  iii. proxy 캐시 설정
    - 웹 정적 자원(css/js/gif/png/jpg/jpeg) 캐싱 및 access log 찍지 않도록 수정
      - 2가지를 같이 적용 해 봄.
      - stress 테스트 시에는 여전히 1.5s 이상 걸리는 건이 있고, 속도도 살-짝 빨라졌지만 큰 차이는 없다.
      
##### iv. upstream 수정 : connection이 가장 적은 server로 request 분배
      - 성능에 가장 영향이 있지 않을까 해서 제일 마지막에 적용 해 봄.
      - stress테스트 결과, 1.5s 이상 걸리는 건이 있지만 대체적으로 속도가 이전 보다 빨라졋다! 
<details>
    <summary> stress & load 테스트 결과 click </summary>
       
```
    
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_line.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 1m8s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 38s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m38.6s), 000/400 VUs, 2395 complete and 0 interrupted iterations
default ✓ [======================================] 000/400 VUs  38s

     ✓ load main successfully 

     checks.........................: 100.00% ✓ 2395  ✗ 0    
     data_received..................: 14 MB   355 kB/s
     data_sent......................: 524 kB  14 kB/s
     http_req_blocked...............: avg=1ms      min=194ns   med=376ns   max=97.03ms  p(90)=3.95ms   p(95)=5.07ms  
     http_req_connecting............: avg=52.64µs  min=0s      med=0s      max=15.32ms  p(90)=239.91µs p(95)=266.67µs
   ✗ http_req_duration..............: avg=2.93s    min=39.03ms med=2.72s   max=16.63s   p(90)=5.82s    p(95)=6.34s   
       { expected_response:true }...: avg=2.93s    min=39.03ms med=2.72s   max=16.63s   p(90)=5.82s    p(95)=6.34s   
     http_req_failed................: 0.00%   ✓ 0     ✗ 2395 
     http_req_receiving.............: avg=29.03ms  min=37.02µs med=9.5ms   max=665.01ms p(90)=72.13ms  p(95)=114.52ms
     http_req_sending...............: avg=49.32µs  min=13.74µs med=34.71µs max=288.28µs p(90)=111.86µs p(95)=130.25µs
     http_req_tls_handshaking.......: avg=903.47µs min=0s      med=0s      max=55.66ms  p(90)=3.54ms   p(95)=4.64ms  
     http_req_waiting...............: avg=2.9s     min=38.53ms med=2.7s    max=16.63s   p(90)=5.77s    p(95)=6.31s   
     http_reqs......................: 2395    62.024334/s
     iteration_duration.............: avg=3.93s    min=1.03s   med=3.72s   max=17.63s   p(90)=6.84s    p(95)=7.34s   
     iterations.....................: 2395    62.024334/s
     vus............................: 29      min=29  max=400
     vus_max........................: 400     min=400 max=400

ERRO[0040] some thresholds have failed                  

```
    
```
    $ k6 run load_line.js 
    
              /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
       /          \   |  |\  \ |  (‾)  | 
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
         script: load_line.js
         output: -
    
      scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
               * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (3m10.9s), 000/100 VUs, 13523 complete and 0 interrupted iterations
    default ✓ [======================================] 000/100 VUs  3m10s
    
         ✓ load main successfully 
    
         checks.........................: 100.00% ✓ 13523 ✗ 0    
         data_received..................: 67 MB   353 kB/s
         data_sent......................: 1.8 MB  9.3 kB/s
         http_req_blocked...............: avg=32.18µs  min=199ns   med=327ns    max=23.37ms  p(90)=535ns    p(95)=605ns   
         http_req_connecting............: avg=2.12µs   min=0s      med=0s       max=2.49ms   p(90)=0s       p(95)=0s      
       ✓ http_req_duration..............: avg=149.54ms min=11.89ms med=127.61ms max=877.12ms p(90)=293.9ms  p(95)=373.69ms
           { expected_response:true }...: avg=149.54ms min=11.89ms med=127.61ms max=877.12ms p(90)=293.9ms  p(95)=373.69ms
         http_req_failed................: 0.00%   ✓ 0     ✗ 13523
         http_req_receiving.............: avg=7.13ms   min=31.38µs med=816.64µs max=186.77ms p(90)=20.9ms   p(95)=39.51ms 
         http_req_sending...............: avg=34.8µs   min=11.81µs med=30.01µs  max=2.66ms   p(90)=50.74µs  p(95)=54.72µs 
         http_req_tls_handshaking.......: avg=28.61µs  min=0s      med=0s       max=22.43ms  p(90)=0s       p(95)=0s      
         http_req_waiting...............: avg=142.37ms min=11.6ms  med=120.9ms  max=877.03ms p(90)=280.95ms p(95)=357.96ms
         http_reqs......................: 13523   70.854238/s
         iteration_duration.............: avg=1.15s    min=1.01s   med=1.12s    max=1.87s    p(90)=1.29s    p(95)=1.37s   
         iterations.....................: 13523   70.854238/s
         vus............................: 5       min=2   max=100
         vus_max........................: 100     min=100 max=100
    

```
    
</details>    

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

#### 1차 적용 내용
- favorite 테이블 db 인덱스 추가
- paging 쿼리 추가
- 적용 전 / 후 favorite 쿼리 조회 테스트 
  - 로그인 후, favorite 조회 하는 script
<details>
    <summary> 적용 전 후 /  테스트 결과</summary>
   
## 적용 전 
#### load      
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_favorite.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)

WARN[0203] Request Failed                                error="stream error: stream ID 27; INTERNAL_ERROR"
ERRO[0203] GoError: cannot parse json due to an error at line 1, character 227133 , error: unexpected end of JSON input
running at reflect.methodValueCall (native)
default at file:///home/ubuntu/k6/load_favorite.js:52:37(59)  executor=ramping-vus scenario=default source=stacktrace

running (3m39.8s), 000/100 VUs, 778 complete and 49 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ logged in successfully
     ✓ retrieved favorite

     checks.........................: 100.00% ✓ 1605  ✗ 0    
     data_received..................: 328 MB  1.5 MB/s
     data_sent......................: 1.4 MB  6.5 kB/s
     http_req_blocked...............: avg=363.78µs min=216ns   med=324ns   max=38.52ms  p(90)=724ns    p(95)=3.85ms  
     http_req_connecting............: avg=19.68µs  min=0s      med=0s      max=3.62ms   p(90)=0s       p(95)=249.89µs
   ✗ http_req_duration..............: avg=10.02s   min=55.16ms med=8.97s   max=51.4s    p(90)=13.71s   p(95)=26.8s   
       { expected_response:true }...: avg=9.53s    min=55.16ms med=8.87s   max=51.4s    p(90)=13.15s   p(95)=15.32s  
     http_req_failed................: 2.49%   ✓ 40    ✗ 1566 
     http_req_receiving.............: avg=107.83ms min=25.34µs med=98.99µs max=1.43s    p(90)=277.26ms p(95)=354.15ms
     http_req_sending...............: avg=72.64µs  min=18.26µs med=61.71µs max=885.44µs p(90)=134.89µs p(95)=170.76µs
     http_req_tls_handshaking.......: avg=334.15µs min=0s      med=0s      max=38.04ms  p(90)=0s       p(95)=3.41ms  
     http_req_waiting...............: avg=9.91s    min=54.98ms med=8.82s   max=51.36s   p(90)=13.57s   p(95)=26.73s  
     http_reqs......................: 1606    7.306587/s
     iteration_duration.............: avg=20.05s   min=5.99s   med=19.22s  max=58.61s   p(90)=24.03s   p(95)=38.71s  
     iterations.....................: 778     3.539555/s
     vus............................: 4       min=2   max=100
     vus_max........................: 100     min=100 max=100

ERRO[0221] some thresholds have failed  
```
#### stress      
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_favorite.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 1m8s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 38s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)

WARN[0059] Request Failed                                error="stream error: stream ID 3; INTERNAL_ERROR"
ERRO[0059] GoError: cannot parse json due to an error at line 1, character 297671 , error: unexpected end of JSON input
running at reflect.methodValueCall (native)
default at file:///home/ubuntu/k6/stress_favorite.js:59:37(59)  executor=ramping-vus scenario=default source=stacktrace

running (1m08.0s), 000/400 VUs, 113 complete and 317 interrupted iterations
default ✓ [======================================] 000/400 VUs  38s

     ✓ logged in successfully
     ✓ retrieved favorite

     checks.........................: 100.00% ✓ 560   ✗ 0    
     data_received..................: 25 MB   371 kB/s
     data_sent......................: 427 kB  6.3 kB/s
     http_req_blocked...............: avg=3.88ms   min=244ns    med=3.87ms   max=73.3ms  p(90)=7.13ms   p(95)=8.82ms  
     http_req_connecting............: avg=231.55µs min=0s       med=254.92µs max=14.1ms  p(90)=297.86µs p(95)=331.05µs
   ✗ http_req_duration..............: avg=26.39s   min=36.02ms  med=30.13s   max=49.24s  p(90)=31.22s   p(95)=35.05s  
       { expected_response:true }...: avg=22.87s   min=111.97ms med=27.12s   max=49.24s  p(90)=34.8s    p(95)=44.81s  
     http_req_failed................: 51.33%  ✓ 288   ✗ 273  
     http_req_receiving.............: avg=50.13ms  min=25.1µs   med=65.38µs  max=1.23s   p(90)=331.32µs p(95)=130.41ms
     http_req_sending...............: avg=149.68µs min=15.86µs  med=149.02µs max=4.34ms  p(90)=222.85µs p(95)=264.71µs
     http_req_tls_handshaking.......: avg=3.47ms   min=0s       med=3.46ms   max=47.07ms p(90)=6.29ms   p(95)=8.37ms  
     http_req_waiting...............: avg=26.34s   min=35.96ms  med=30.13s   max=49.15s  p(90)=31.22s   p(95)=34.61s  
     http_reqs......................: 561     8.249582/s
     iteration_duration.............: avg=44.19s   min=12.81s   med=50.72s   max=1m5s    p(90)=57.59s   p(95)=57.65s  
     iterations.....................: 113     1.661681/s
     vus............................: 0       min=0   max=400
     vus_max........................: 400     min=400 max=400

ERRO[0069] some thresholds have failed   
```
   
## 적용 후 
#### load          
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_favorite.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.5s), 000/100 VUs, 3558 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ logged in successfully
     ✓ retrieved favorite

     checks.........................: 100.00% ✓ 7116  ✗ 0    
     data_received..................: 6.3 MB  33 kB/s
     data_sent......................: 1.4 MB  7.4 kB/s
     http_req_blocked...............: avg=56.1µs  min=210ns   med=318ns   max=42.8ms   p(90)=582ns    p(95)=676ns   
     http_req_connecting............: avg=3.72µs  min=0s      med=0s      max=392.79µs p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=1.7s    min=30.53ms med=2.03s   max=3.62s    p(90)=2.11s    p(95)=2.15s   
       { expected_response:true }...: avg=1.7s    min=30.53ms med=2.03s   max=3.62s    p(90)=2.11s    p(95)=2.15s   
     http_req_failed................: 0.00%   ✓ 0     ✗ 7116 
     http_req_receiving.............: avg=72.77µs min=30.42µs med=71.26µs max=1.52ms   p(90)=88.77µs  p(95)=98.23µs 
     http_req_sending...............: avg=60.99µs min=17.95µs med=50.93µs max=712.63µs p(90)=100.93µs p(95)=115.05µs
     http_req_tls_handshaking.......: avg=47.12µs min=0s      med=0s      max=22.28ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=1.7s    min=30.37ms med=2.03s   max=3.62s    p(90)=2.11s    p(95)=2.15s   
     http_reqs......................: 7116    37.344994/s
     iteration_duration.............: avg=4.4s    min=1.06s   med=5.08s   max=6.76s    p(90)=5.2s     p(95)=5.26s   
     iterations.....................: 3558    18.672497/s
     vus............................: 6       min=2   max=100
     vus_max........................: 100     min=100 max=100


```

 #### stress          
 ```
 
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress_favorite.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 1m8s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 38s over 9 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m49.2s), 000/400 VUs, 932 complete and 0 interrupted iterations
default ✓ [======================================] 000/400 VUs  38s

     ✓ logged in successfully
     ✓ retrieved favorite

     checks.........................: 100.00% ✓ 1864  ✗ 0    
     data_received..................: 3.4 MB  69 kB/s
     data_sent......................: 581 kB  12 kB/s
     http_req_blocked...............: avg=835.71µs min=220ns    med=394ns   max=70.29ms p(90)=3.34ms   p(95)=3.82ms  
     http_req_connecting............: avg=76.78µs  min=0s       med=0s      max=14.46ms p(90)=249.47µs p(95)=272.89µs
   ✗ http_req_duration..............: avg=6.14s    min=108.3ms  med=6.71s   max=18.13s  p(90)=9.85s    p(95)=10.07s  
       { expected_response:true }...: avg=6.14s    min=108.3ms  med=6.71s   max=18.13s  p(90)=9.85s    p(95)=10.07s  
     http_req_failed................: 0.00%   ✓ 0     ✗ 1864 
     http_req_receiving.............: avg=78.77µs  min=29.26µs  med=72.56µs max=4.2ms   p(90)=95.59µs  p(95)=105.65µs
     http_req_sending...............: avg=80.62µs  min=18.49µs  med=54.71µs max=1.04ms  p(90)=169.18µs p(95)=199.16µs
     http_req_tls_handshaking.......: avg=720.13µs min=0s       med=0s      max=45.59ms p(90)=2.95ms   p(95)=3.4ms   
     http_req_waiting...............: avg=6.14s    min=108.02ms med=6.71s   max=18.13s  p(90)=9.85s    p(95)=10.07s  
     http_reqs......................: 1864    37.911436/s
     iteration_duration.............: avg=13.29s   min=1.8s     med=14.18s  max=27.04s  p(90)=19.39s   p(95)=20.03s  
     iterations.....................: 932     18.955718/s
     vus............................: 16      min=16  max=400
     vus_max........................: 400     min=400 max=400
ERRO[0051] some thresholds have failed                  
 ```   
</details>    

####  db 이중화 구성
##### 구성 진행 방법
  - internal 서버 접속
    - master 폴더 생성 및 cnf파일 생성
```
mkdir -p ~/mysql/master
vi /mysql/master/config_file.cnf
```
[config_file.cnf]
```
[mysqld]
log-bin=mysql-bin  
server-id=1
```
   - slave 폴더 생성 및 cnf파일 생성
   ```
   mkdir -p ~/mysql/slave
   vi /mysql/slave/config_file.cnf
   ```
   [config_file.cnf]
   ```
[mysqld]
server-id=2  
   ``` 

- master db container 생성 
  - 저는 subway 데이터를 이용하기 위해서 기존 사용하던 data-subway:0.0.2 docker 이미지를 활용했어요!
```
docker run --name mysql-master -p 13306:3306 -v ~/mysql/master:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=masterpw -d brainbackdoor/data-subway:0.0.2
```
- master 서버 설정 진행 (힌트 주신 내용 참고 했습니다!ㅎㅎ)
```
docker exec -it mysql-master /bin/bash
mysql -u root -p 
mysql> CREATE USER 'replication_user'@'%' IDENTIFIED WITH mysql_native_password by 'replication_pw';  
mysql> GRANT REPLICATION SLAVE ON *.* TO 'replication_user'@'%'; 

mysql> SHOW MASTER STATUS\G  
*************************** 1. row ***************************
             File: mysql-bin.000001
         Position: 619
     Binlog_Do_DB: 
 Binlog_Ignore_DB: 
Executed_Gtid_Set: 
```

- slave db container 생성 
  - 이 때 master랑 같은 data-subway 이미지를 사용하면 replication설정 시 UUID?가 같다고 오류가 나서, mysql 이미지로 생성 후 data-subway의 데이터를 dump 하는 방식으로 migration 후 replication 진행 했습니다!
  - 또, 기존 data-subway 를 이용하면 `MYSQL_ROOT_PASSWORD`를 slavepw로 환경 변수 지정해도 변경이 안돼더라구요.
```
docker run --name mysql-slave -p 13307:3306 -v ~/mysql/slave:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=slavepw -d mysql
```
- slave 서버 설정 진행 (이 때, MASTER_LOG_FILE 과 MASTER_LOG_POS 는 master db에서 `SHOW MASTER STATUS\G` 조회 된 값 입력  )
```
docker exec -it mysql-slave /bin/bash
 mysql -u root -p  
mysql> SET GLOBAL server_id = 2;
mysql> CHANGE MASTER TO MASTER_HOST='172.17.0.1', MASTER_PORT = 13306, MASTER_USER='replication_user', MASTER_PASSWORD='replication_pw', MASTER_LOG_FILE='binlog.000002', MASTER_LOG_POS=683;  

mysql> START SLAVE;  
mysql> SHOW SLAVE STATUS\G
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 172.17.0.1
                  Master_User: replication_user
                  Master_Port: 13306
                Connect_Retry: 60
              Master_Log_File: mysql-bin.000001
          Read_Master_Log_Pos: 619
               Relay_Log_File: fedf552cb3d0-relay-bin.000002
                Relay_Log_Pos: 322
        Relay_Master_Log_File: mysql-bin.000001
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB: 
          Replicate_Ignore_DB: 
           Replicate_Do_Table: 
       Replicate_Ignore_Table: 
      Replicate_Wild_Do_Table: 
  Replicate_Wild_Ignore_Table: 
                   Last_Errno: 0
                   Last_Error: 
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 619
              Relay_Log_Space: 538
              Until_Condition: None
               Until_Log_File: 
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File: 
           Master_SSL_CA_Path: 
              Master_SSL_Cert: 
            Master_SSL_Cipher: 
               Master_SSL_Key: 
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error: 
               Last_SQL_Errno: 0
               Last_SQL_Error: 
  Replicate_Ignore_Server_Ids: 
             Master_Server_Id: 1
                  Master_UUID: b83809e0-8eb2-11eb-a522-0242ac120002
             Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
           Master_Retry_Count: 86400
                  Master_Bind: 
      Last_IO_Error_Timestamp: 
     Last_SQL_Error_Timestamp: 
               Master_SSL_Crl: 
           Master_SSL_Crlpath: 
           Retrieved_Gtid_Set: 
            Executed_Gtid_Set: 
                Auto_Position: 0
         Replicate_Rewrite_DB: 
                 Channel_Name: 
           Master_TLS_Version: 
       Master_public_key_path: 
        Get_master_public_key: 0
            Network_Namespace: 
1 row in set, 1 warning (0.00 sec)
```
- 그리고 나서, 꼭 AWS internal 보안 그룹에 13306 / 13307 을 추가 ..^^.. !

   
- Application에 설정 추가
  - 저는 local 에서는 이중화 구성을 하지 않아서 prod 환경에서만 이중화 해서 사용한다고 가정하고 진행 했습니다!
  - application-prod.properties 설정 변경
  - DataBaseConfig 설정 시, test / local 환경에서 오류 나지 않도록 prod profile인 경우만 적용 되도록 설정
```java
@Profile("prod")
@Slf4j
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
class DataBaseConfig {
```

- db 이중화 구성 적용 전 후 속도 측정
  - insert / select 수행하는 테스트를 동시에 실행해야 master /slave로 나뉘어 속도 향상이 있는지 비교가 될 것 같아서 서버 두 대에서 진행 했습니다!
    - load 테스트만 진행 해 보았는데요..! 
    - load_favorite.js : 로그인 후, 인증 토큰으로 favorite 조회
    - load_favorite_insert.js : 로그인 후, 인증 토큰으로 favorite insert
 <details>
     <summary> 적용 전 /후 테스트 결과</summary>
        
## 적용전 favorite조회
        
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_favorite.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m12.9s), 000/100 VUs, 1786 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ logged in successfully
     ✓ retrieved favorite

     checks.........................: 100.00% ✓ 3572  ✗ 0    
     data_received..................: 3.4 MB  18 kB/s
     data_sent......................: 731 kB  3.8 kB/s
     http_req_blocked...............: avg=120.9µs  min=244ns   med=529ns   max=39.82ms  p(90)=756ns    p(95)=906ns   
     http_req_connecting............: avg=7.14µs   min=0s      med=0s      max=530.96µs p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=3.95s    min=35.96ms med=4.73s   max=9.05s    p(90)=4.83s    p(95)=4.9s    
       { expected_response:true }...: avg=3.95s    min=35.96ms med=4.73s   max=9.05s    p(90)=4.83s    p(95)=4.9s    
     http_req_failed................: 0.00%   ✓ 0     ✗ 3572 
     http_req_receiving.............: avg=82.83µs  min=39.17µs med=77.08µs max=899.17µs p(90)=106.63µs p(95)=119.79µs
     http_req_sending...............: avg=78.33µs  min=22.85µs med=64.26µs max=960.84µs p(90)=149.3µs  p(95)=173.18µs
     http_req_tls_handshaking.......: avg=104.39µs min=0s      med=0s      max=25.08ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=3.95s    min=35.75ms med=4.73s   max=9.05s    p(90)=4.83s    p(95)=4.9s    
     http_reqs......................: 3572    18.517779/s
     iteration_duration.............: avg=8.91s    min=1.08s   med=10.48s  max=14.74s   p(90)=10.66s   p(95)=10.71s  
     iterations.....................: 1786    9.25889/s
     vus............................: 20      min=2   max=100
     vus_max........................: 100     min=100 max=100

ERRO[0195] some thresholds have failed  
 ```
   
## 적용 전 favorite insert    
 ```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_favorite_insert.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m13.4s), 000/100 VUs, 1795 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ logged in successfully
     ✓ insert favorite

     checks.........................: 100.00% ✓ 3590  ✗ 0    
     data_received..................: 1.4 MB  7.2 kB/s
     data_sent......................: 783 kB  4.1 kB/s
     http_req_blocked...............: avg=110.79µs min=218ns   med=327ns   max=22.52ms  p(90)=623ns    p(95)=832ns   
     http_req_connecting............: avg=7.5µs    min=0s      med=0s      max=877.47µs p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=3.93s    min=37.68ms med=4.74s   max=9.06s    p(90)=4.83s    p(95)=4.89s   
       { expected_response:true }...: avg=3.93s    min=37.68ms med=4.74s   max=9.06s    p(90)=4.83s    p(95)=4.89s   
     http_req_failed................: 0.00%   ✓ 0     ✗ 3590 
     http_req_receiving.............: avg=60.03µs  min=15.77µs med=55.97µs max=975.3µs  p(90)=82.47µs  p(95)=91.52µs 
     http_req_sending...............: avg=83.27µs  min=32.49µs med=74.55µs max=588.61µs p(90)=105.27µs p(95)=131.56µs
     http_req_tls_handshaking.......: avg=98.71µs  min=0s      med=0s      max=21.64ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=3.93s    min=37.51ms med=4.74s   max=9.06s    p(90)=4.83s    p(95)=4.89s   
     http_reqs......................: 3590    18.565354/s
     iteration_duration.............: avg=8.87s    min=1.09s   med=10.49s  max=14.98s   p(90)=10.66s   p(95)=10.72s  
     iterations.....................: 1795    9.282677/s
     vus............................: 12      min=2   max=100
     vus_max........................: 100     min=100 max=100

ERRO[0195] some thresholds have failed
 ```
## 적용 후 favorite 조회
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_favorite.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m12.9s), 000/100 VUs, 1808 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ logged in successfully
     ✓ retrieved favorite

     checks.........................: 100.00% ✓ 3616  ✗ 0    
     data_received..................: 3.4 MB  18 kB/s
     data_sent......................: 740 kB  3.8 kB/s
     http_req_blocked...............: avg=139.48µs min=233ns   med=506ns   max=127.28ms p(90)=730ns    p(95)=901ns   
     http_req_connecting............: avg=6.88µs   min=0s      med=0s      max=480.15µs p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=3.89s    min=34.69ms med=4.66s   max=9.21s    p(90)=4.78s    p(95)=4.8s    
       { expected_response:true }...: avg=3.89s    min=34.69ms med=4.66s   max=9.21s    p(90)=4.78s    p(95)=4.8s    
     http_req_failed................: 0.00%   ✓ 0     ✗ 3616 
     http_req_receiving.............: avg=81.17µs  min=30.15µs med=76.3µs  max=566.52µs p(90)=103.93µs p(95)=116.69µs
     http_req_sending...............: avg=75.74µs  min=23.85µs med=63.08µs max=2.59ms   p(90)=139.14µs p(95)=167.9µs 
     http_req_tls_handshaking.......: avg=124.25µs min=0s      med=0s      max=114.69ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=3.89s    min=34.41ms med=4.66s   max=9.21s    p(90)=4.78s    p(95)=4.8s    
     http_reqs......................: 3616    18.747994/s
     iteration_duration.............: avg=8.78s    min=1.08s   med=10.37s  max=14.94s   p(90)=10.49s   p(95)=10.54s  
     iterations.....................: 1808    9.373997/s
     vus............................: 16      min=2   max=100
     vus_max........................: 100     min=100 max=100

ERRO[0194] some thresholds have failed  
```

## 적용 후 favorite insert
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load_favorite_insert.js
     output: -

  scenarios: (100.00%) 1 scenario, 100 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 100 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m13.4s), 000/100 VUs, 1824 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ logged in successfully
     ✓ insert favorite

     checks.........................: 100.00% ✓ 3648  ✗ 0    
     data_received..................: 1.4 MB  7.3 kB/s
     data_sent......................: 796 kB  4.1 kB/s
     http_req_blocked...............: avg=105.98µs min=223ns   med=317ns   max=40.39ms  p(90)=604ns    p(95)=740ns   
     http_req_connecting............: avg=7.32µs   min=0s      med=0s      max=490.14µs p(90)=0s       p(95)=0s      
   ✗ http_req_duration..............: avg=3.86s    min=34.98ms med=4.66s   max=9.19s    p(90)=4.78s    p(95)=4.81s   
       { expected_response:true }...: avg=3.86s    min=34.98ms med=4.66s   max=9.19s    p(90)=4.78s    p(95)=4.81s   
     http_req_failed................: 0.00%   ✓ 0     ✗ 3648 
     http_req_receiving.............: avg=60.23µs  min=16.36µs med=56.55µs max=1.86ms   p(90)=81.54µs  p(95)=90.63µs 
     http_req_sending...............: avg=85.27µs  min=33.47µs med=75.81µs max=4.04ms   p(90)=106.41µs p(95)=129.05µs
     http_req_tls_handshaking.......: avg=89.67µs  min=0s      med=0s      max=22.41ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=3.86s    min=34.64ms med=4.66s   max=9.19s    p(90)=4.78s    p(95)=4.81s   
     http_reqs......................: 3648    18.865314/s
     iteration_duration.............: avg=8.73s    min=1.08s   med=10.37s  max=14.93s   p(90)=10.49s   p(95)=10.55s  
     iterations.....................: 1824    9.432657/s
     vus............................: 10      min=2   max=100
     vus_max........................: 100     min=100 max=100

ERRO[0195] some thresholds have failed          
```     

 </details>     
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
- GET https://mysubway.kro.kr/favorites
- k6 로도 확인 가능 합니다.! 
<details>
<summary> k6 API 테스트 결과</summary>
  - --http-debug 기능과 console.log로 확인 하고자 하는 로그를 찍어 보았습니다 ㅎㅎ
  
```
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';


const BASE_URL = 'https://mysubway.kro.kr';
const USERNAME = 'koun@kakao.com';
const PASSWORD = '1234';
export default function ()  {

  var payload = JSON.stringify({
    email: USERNAME,
    password: PASSWORD
  });

  var params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };


  let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);

  check(loginRes, {
    'logged in successfully': (resp) => resp.json('accessToken') !== '',
  });


  let authHeaders = {
    headers: {
      Authorization: `Bearer ${loginRes.json('accessToken')}`,
      'Content-Type': 'application/json'
    },
  };
  let myObjects = http.get(`${BASE_URL}/favorites`, authHeaders);
  console.log(myObjects.body);
  check(myObjects, { 'get favorite': (resp) => resp.status === 200 });
  sleep(1);
};
```

```
$ k6 run --http-debug test.js 

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: test.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 10m30s max duration (incl. graceful stop):
           * default: 1 iterations for each of 1 VUs (maxDuration: 10m0s, gracefulStop: 30s)

INFO[0000] Request:
POST /login/token HTTP/1.1
Host: mysubway.kro.kr
User-Agent: k6/0.32.0 (https://k6.io/)
Content-Length: 44
Content-Type: application/json
Accept-Encoding: gzip

  group= iter=0 request_id=2cef4c42-9d61-44e1-5f89-8d808c0b03dd scenario=default source=http-debug vu=1
INFO[0001] Response:
HTTP/2.0 200 OK
Connection: close
Content-Type: application/json
Date: Thu, 24 Jun 2021 20:10:19 GMT
Server: nginx/1.21.0
Strict-Transport-Security: max-age=31536000
Vary: Accept-Encoding

  group= iter=0 request_id=2cef4c42-9d61-44e1-5f89-8d808c0b03dd scenario=default source=http-debug vu=1
INFO[0001] Request:
GET /favorites HTTP/1.1
Host: mysubway.kro.kr
User-Agent: k6/0.32.0 (https://k6.io/)
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrb3VuQGtha2FvLmNvbSIsImlhdCI6MTYyNDU2NTQxOCwiZXhwIjoxNjI0NTY5MDE4fQ.DegnPHgKXcLuVZMcAYEouEAFHZUSqQlYZzEsRwtYX9s
Content-Type: application/json
Accept-Encoding: gzip

  group= iter=0 request_id=a49a78a0-d9bf-4379-6f92-ffe0af93ae96 scenario=default source=http-debug vu=1
INFO[0002] Response:
HTTP/2.0 200 OK
Connection: close
Content-Type: application/json
Date: Thu, 24 Jun 2021 20:10:19 GMT
Server: nginx/1.21.0
Strict-Transport-Security: max-age=31536000
Vary: Accept-Encoding

  group= iter=0 request_id=a49a78a0-d9bf-4379-6f92-ffe0af93ae96 scenario=default source=http-debug vu=1
INFO[0002] [{"id":1804,"source":{"id":10,"name":"신이문","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"},"target":{"id":20,"name":"배방","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"}},{"id":1803,"source":{"id":10,"name":"신이문","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"},"target":{"id":20,"name":"배방","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"}},{"id":1802,"source":{"id":10,"name":"신이문","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"},"target":{"id":20,"name":"배방","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"}},{"id":1801,"source":{"id":10,"name":"신이문","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"},"target":{"id":20,"name":"배방","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"}},{"id":1800,"source":{"id":10,"name":"신이문","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"},"target":{"id":20,"name":"배방","createdDate":"2021-01-06 18:32:00","modifiedDate":"2021-01-06 18:32:00"}}]  source=console

running (00m02.2s), 0/1 VUs, 1 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  00m02.2s/10m0s  1/1 iters, 1 per VU

     ✓ logged in successfully
     ✓ get favorite

     checks.........................: 100.00% ✓ 2   ✗ 0  
     data_received..................: 6.2 kB  2.9 kB/s
     data_sent......................: 955 B   440 B/s
     http_req_blocked...............: avg=15.36ms  min=712ns    med=15.36ms  max=30.73ms  p(90)=27.66ms  p(95)=29.19ms 
     http_req_connecting............: avg=139.15µs min=0s       med=139.15µs max=278.31µs p(90)=250.48µs p(95)=264.39µs
     http_req_duration..............: avg=567.01ms min=175.75ms med=567.01ms max=958.26ms p(90)=880.01ms p(95)=919.14ms
       { expected_response:true }...: avg=567.01ms min=175.75ms med=567.01ms max=958.26ms p(90)=880.01ms p(95)=919.14ms
     http_req_failed................: 0.00%   ✓ 0   ✗ 2  
     http_req_receiving.............: avg=193.49µs min=159.58µs med=193.49µs max=227.41µs p(90)=220.62µs p(95)=224.01µs
     http_req_sending...............: avg=95.37µs  min=35.07µs  med=95.37µs  max=155.66µs p(90)=143.6µs  p(95)=149.63µs
     http_req_tls_handshaking.......: avg=11ms     min=0s       med=11ms     max=22ms     p(90)=19.8ms   p(95)=20.9ms  
     http_req_waiting...............: avg=566.72ms min=175.56ms med=566.72ms max=957.88ms p(90)=879.65ms p(95)=918.76ms
     http_reqs......................: 2       0.922982/s
     iteration_duration.............: avg=2.16s    min=2.16s    med=2.16s    max=2.16s    p(90)=2.16s    p(95)=2.16s   
     iterations.....................: 1       0.461491/s
     vus............................: 1       min=1 max=1
     vus_max........................: 1       min=1 max=1



```

</details>
