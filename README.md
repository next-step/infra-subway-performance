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



2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

