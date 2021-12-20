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
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
   - Smoke 테스트(로그인, 나의 정보 찾기, 라인 찾기, 최소경로 찾기)
     - <details>
         <summary>성능개선 전</summary>
         <div markdown="1">
         
         ```text
         
                      /\      |‾‾| /‾‾/   /‾‾/   
                 /\  /  \     |  |/  /   /  /    
                /  \/    \    |     (   /   ‾‾\  
               /          \   |  |\  \ |  (‾)  |
              / __________ \  |__| \__\ \_____/ .io
         
         execution: local
         script: smoke.js
         output: -
         
         scenarios: (100.00%) 1 scenario, 2 max VUs, 1m0s max duration (incl. graceful stop):
         * default: 2 looping VUs for 30s (gracefulStop: 30s)
         
         
         running (0m31.1s), 0/2 VUs, 55 complete and 0 interrupted iterations
         default ✓ [======================================] 2 VUs  30s
         
              ✓ logged in successfully
              ✓ found member
              ✓ found line
              ✓ found path
         
              checks.........................: 100.00% ✓ 220      ✗ 0  
              data_received..................: 636 kB  21 kB/s
              data_sent......................: 59 kB   1.9 kB/s
              http_req_blocked...............: avg=306.46µs min=3.74µs  med=4.99µs  max=28.95ms  p(90)=7.59µs   p(95)=8.54µs  
              http_req_connecting............: avg=9.09µs   min=0s      med=0s      max=623.44µs p(90)=0s       p(95)=0s      
            ✓ http_req_duration..............: avg=26.85ms  min=6.46ms  med=12.72ms max=220.92ms p(90)=66.21ms  p(95)=89.39ms
                  { expected_response:true }...: avg=26.85ms  min=6.46ms  med=12.72ms max=220.92ms p(90)=66.21ms  p(95)=89.39ms
              http_req_failed................: 0.00%   ✓ 0        ✗ 220
              http_req_receiving.............: avg=68.45µs  min=28.14µs med=60.37µs max=243.34µs p(90)=105.76µs p(95)=127.28µs
              http_req_sending...............: avg=19.58µs  min=11.1µs  med=15.69µs max=193.58µs p(90)=27.47µs  p(95)=30.15µs
              http_req_tls_handshaking.......: avg=175.93µs min=0s      med=0s      max=15.81ms  p(90)=0s       p(95)=0s      
              http_req_waiting...............: avg=26.76ms  min=6.41ms  med=12.62ms max=220.82ms p(90)=66.11ms  p(95)=89.34ms
              http_reqs......................: 220     7.082426/s
              iteration_duration.............: avg=1.11s    min=1.06s   med=1.08s   max=1.48s    p(90)=1.14s    p(95)=1.16s   
              iterations.....................: 55      1.770606/s
              vus............................: 1       min=1      max=2
              vus_max........................: 2       min=2      max=2
         
         ```
         
         </div>
       </details>

     - <details>
         <summary>성능개선 후</summary>
         <div markdown="1">
       
         ```text
         
                      /\      |‾‾| /‾‾/   /‾‾/   
                 /\  /  \     |  |/  /   /  /    
                /  \/    \    |     (   /   ‾‾\  
               /          \   |  |\  \ |  (‾)  |
              / __________ \  |__| \__\ \_____/ .io
         
         execution: local
         script: smoke.js
         output: -
         
         scenarios: (100.00%) 1 scenario, 2 max VUs, 1m0s max duration (incl. graceful stop):
         * default: 2 looping VUs for 30s (gracefulStop: 30s)
         
         
         running (0m30.6s), 0/2 VUs, 60 complete and 0 interrupted iterations
         default ✓ [======================================] 2 VUs  30s
         
              ✓ logged in successfully
              ✓ found member
              ✓ found line
              ✓ found path
         
              checks.........................: 100.00% ✓ 240      ✗ 0  
              data_received..................: 666 kB  22 kB/s
              data_sent......................: 25 kB   826 B/s
              http_req_blocked...............: avg=146.08µs min=2.58µs  med=2.77µs  max=17.24ms  p(90)=2.96µs   p(95)=3.08µs  
              http_req_connecting............: avg=3.74µs   min=0s      med=0s      max=460.03µs p(90)=0s       p(95)=0s      
            ✓ http_req_duration..............: avg=4.09ms   min=2.07ms  med=4.4ms   max=9.2ms    p(90)=5.67ms   p(95)=5.86ms  
                  { expected_response:true }...: avg=4.09ms   min=2.07ms  med=4.4ms   max=9.2ms    p(90)=5.67ms   p(95)=5.86ms  
              http_req_failed................: 0.00%   ✓ 0        ✗ 240
              http_req_receiving.............: avg=103.77µs min=22.43µs med=55.63µs max=801.45µs p(90)=230.17µs p(95)=360.57µs
              http_req_sending...............: avg=71.34µs  min=36.95µs med=52.55µs max=888.04µs p(90)=99.02µs  p(95)=112.36µs
              http_req_tls_handshaking.......: avg=134.9µs  min=0s      med=0s      max=16.3ms   p(90)=0s       p(95)=0s      
              http_req_waiting...............: avg=3.91ms   min=1.97ms  med=4.25ms  max=9.09ms   p(90)=5.5ms    p(95)=5.77ms  
              http_reqs......................: 240     7.853187/s
              iteration_duration.............: avg=1.01s    min=1.01s   med=1.01s   max=1.03s    p(90)=1.01s    p(95)=1.02s   
              iterations.....................: 60      1.963297/s
              vus............................: 2       min=2      max=2
              vus_max........................: 2       min=2      max=2
         
         ```
       
         </div>
       </details>

   - Load 테스트(로그인, 나의 정보 찾기, 라인 찾기, 최소경로 찾기)
     - <details>
         <summary>성능개선 전</summary>
         <div markdown="1">
   
         ```text
               
                     /\      |‾‾| /‾‾/   /‾‾/   
                /\  /  \     |  |/  /   /  /    
               /  \/    \    |     (   /   ‾‾\  
              /          \   |  |\  \ |  (‾)  |
             / __________ \  |__| \__\ \_____/ .io
         
         execution: local
         script: load.js
         output: -
         
         scenarios: (100.00%) 1 scenario, 300 max VUs, 45s max duration (incl. graceful stop):
         * default: Up to 300 looping VUs for 15s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
         
         
         running (16.0s), 000/300 VUs, 1237 complete and 0 interrupted iterations
         default ✓ [======================================] 000/300 VUs  15s
         
              ✓ logged in successfully
              ✓ found member
              ✓ found line
              ✓ found path
         
            ✓ checks.........................: 100.00% ✓ 4948       ✗ 0    
              data_received..................: 16 MB   978 kB/s
              data_sent......................: 1.4 MB  91 kB/s
              http_req_blocked...............: avg=1.09ms   min=3.57µs  med=4.73µs   max=169.84ms p(90)=35.22µs  p(95)=4.63ms  
              http_req_connecting............: avg=295µs    min=0s      med=0s       max=69.66ms  p(90)=0s       p(95)=476.8µs
            ✓ http_req_duration..............: avg=333.72ms min=3.65ms  med=339.54ms max=1.78s    p(90)=602.31ms p(95)=711.13ms
                  { expected_response:true }...: avg=333.72ms min=3.65ms  med=339.54ms max=1.78s    p(90)=602.31ms p(95)=711.13ms
              http_req_failed................: 0.00%   ✓ 0          ✗ 4948
              http_req_receiving.............: avg=294.7µs  min=26.04µs med=48.79µs  max=63.9ms   p(90)=168.99µs p(95)=567.17µs
              http_req_sending...............: avg=369.13µs min=9.39µs  med=15.88µs  max=67.19ms  p(90)=97.59µs  p(95)=508.12µs
              http_req_tls_handshaking.......: avg=762.96µs min=0s      med=0s       max=105.49ms p(90)=0s       p(95)=3.91ms  
              http_req_waiting...............: avg=333.06ms min=3.58ms  med=339.43ms max=1.78s    p(90)=601.31ms p(95)=710.66ms
              http_reqs......................: 4948    309.370429/s
              iteration_duration.............: avg=2.34s    min=1.04s   med=2.41s    max=4.57s    p(90)=3.39s    p(95)=3.72s   
              iterations.....................: 1237    77.342607/s
              vus............................: 53      min=41       max=300
              vus_max........................: 300     min=300      max=300
         
         ```
   
         </div>
       </details>
   
     - <details>
         <summary>성능개선 후</summary>
         <div markdown="1">
   
         ```text
         
                      /\      |‾‾| /‾‾/   /‾‾/   
                 /\  /  \     |  |/  /   /  /    
                /  \/    \    |     (   /   ‾‾\  
               /          \   |  |\  \ |  (‾)  |
              / __________ \  |__| \__\ \_____/ .io
         
         execution: local
         script: load.js
         output: -
         
         scenarios: (100.00%) 1 scenario, 300 max VUs, 45s max duration (incl. graceful stop):
         * default: Up to 300 looping VUs for 15s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
         
         
         running (15.7s), 000/300 VUs, 2132 complete and 0 interrupted iterations
         default ✓ [======================================] 000/300 VUs  15s
         
              ✓ logged in successfully
              ✓ found member
              ✓ found line
              ✓ found path
         
             ✓ checks.........................: 100.00% ✓ 8528       ✗ 0    
             data_received..................: 25 MB   1.6 MB/s
             data_sent......................: 1.0 MB  66 kB/s
             http_req_blocked...............: avg=2.16ms  min=2.51µs  med=2.72µs  max=421.56ms p(90)=2.95µs   p(95)=45.35µs
             http_req_connecting............: avg=668µs   min=0s      med=0s      max=140.73ms p(90)=0s       p(95)=0s      
           ✓ http_req_duration..............: avg=60.82ms min=2.08ms  med=37.57ms max=436.58ms p(90)=147.43ms p(95)=182.62ms
                 { expected_response:true }...: avg=60.82ms min=2.08ms  med=37.57ms max=436.58ms p(90)=147.43ms p(95)=182.62ms
             http_req_failed................: 0.00%   ✓ 0          ✗ 8528
             http_req_receiving.............: avg=15.31ms min=19.27µs med=3.04ms  max=241.16ms p(90)=43.3ms   p(95)=64.62ms
             http_req_sending...............: avg=1.77ms  min=28.95µs med=50.39µs max=262.4ms  p(90)=756.8µs  p(95)=3.75ms  
             http_req_tls_handshaking.......: avg=1.43ms  min=0s      med=0s      max=373.58ms p(90)=0s       p(95)=0s      
             http_req_waiting...............: avg=43.74ms min=0s      med=29.73ms max=267.45ms p(90)=105.24ms p(95)=124.57ms
             http_reqs......................: 8528    543.014608/s
             iteration_duration.............: avg=1.27s   min=1.01s   med=1.22s   max=2.06s    p(90)=1.61s    p(95)=1.68s   
             iterations.....................: 2132    135.753652/s
             vus............................: 33      min=33       max=300
             vus_max........................: 300     min=300      max=300

         ```
   
         </div>
       </details>
   
   - Stress 테스트(로그인, 나의 정보 찾기, 라인 찾기, 최소경로 찾기)
     - <details>
         <summary>성능개선 전</summary>
         <div markdown="1">
   
         ```text
         
                      /\      |‾‾| /‾‾/   /‾‾/   
                 /\  /  \     |  |/  /   /  /    
                /  \/    \    |     (   /   ‾‾\  
               /          \   |  |\  \ |  (‾)  |
              / __________ \  |__| \__\ \_____/ .io
         
         execution: local
         script: stress.js
         output: -
         
         scenarios: (100.00%) 1 scenario, 400 max VUs, 2m50s max duration (incl. graceful stop):
         * default: Up to 400 looping VUs for 2m20s over 8 stages (gracefulRampDown: 30s, gracefulStop: 30s)
         
         
         running (2m20.9s), 000/400 VUs, 13012 complete and 0 interrupted iterations
         default ✓ [======================================] 000/400 VUs  2m20s
         
              ✗ logged in successfully
               ↳  93% — ✓ 12230 / ✗ 782
              ✓ found member
              ✓ found line
              ✓ found path
         
           ✓ checks.........................: 98.42% ✓ 48744      ✗ 782  
             data_received..................: 153 MB 1.1 MB/s
             data_sent......................: 14 MB  102 kB/s
             http_req_blocked...............: avg=20.46ms  min=0s     med=4.64µs   max=2.07s    p(90)=19.17µs  p(95)=48.09ms
             http_req_connecting............: avg=6.82ms   min=0s     med=0s       max=979.4ms  p(90)=0s       p(95)=16.59ms
           ✓ http_req_duration..............: avg=345.62ms min=0s     med=297.26ms max=2.95s    p(90)=717.23ms p(95)=913.09ms
                 { expected_response:true }...: avg=350.96ms min=3.54ms med=302.33ms max=2.95s    p(90)=721.57ms p(95)=916.28ms
             http_req_failed................: 1.76%  ✓ 876        ✗ 48737
             http_req_receiving.............: avg=844.97µs min=0s     med=45.57µs  max=524.81ms p(90)=170.08µs p(95)=623.44µs
             http_req_sending...............: avg=3.53ms   min=0s     med=15.12µs  max=2.01s    p(90)=184.37µs p(95)=4.8ms   
             http_req_tls_handshaking.......: avg=13.14ms  min=0s     med=0s       max=1.49s    p(90)=0s       p(95)=27.63ms
             http_req_waiting...............: avg=341.24ms min=0s     med=295.83ms max=2.95s    p(90)=702.16ms p(95)=890.38ms
             http_reqs......................: 49613  352.031499/s
             iteration_duration.............: avg=2.34s    min=1.7ms  med=2.2s     max=8.59s    p(90)=4.13s    p(95)=4.6s    
             iterations.....................: 13012  92.32729/s
             vus............................: 6      min=6        max=400
             vus_max........................: 400    min=400      max=400
         
         ```
   
         </div>
       </details>
   
     - <details>
         <summary>성능개선 후</summary>
         <div markdown="1">
   
         ```text
         
                      /\      |‾‾| /‾‾/   /‾‾/   
                 /\  /  \     |  |/  /   /  /    
                /  \/    \    |     (   /   ‾‾\  
               /          \   |  |\  \ |  (‾)  |
              / __________ \  |__| \__\ \_____/ .io
         
         execution: local
         script: stress.js
         output: -
         
         scenarios: (100.00%) 1 scenario, 400 max VUs, 2m50s max duration (incl. graceful stop):
         * default: Up to 400 looping VUs for 2m20s over 8 stages (gracefulRampDown: 30s, gracefulStop: 30s)
         
         
         running (2m21.0s), 000/400 VUs, 23126 complete and 0 interrupted iterations
         default ✓ [======================================] 000/400 VUs  2m20s
         
              ✓ logged in successfully
              ✓ found member
              ✓ found line
              ✓ found path
         
           ✓ checks.........................: 100.00% ✓ 92504      ✗ 0    
             data_received..................: 255 MB  1.8 MB/s
             data_sent......................: 9.5 MB  67 kB/s
             http_req_blocked...............: avg=401.72µs min=2.48µs  med=2.71µs  max=903.59ms p(90)=2.87µs   p(95)=3.41µs  
             http_req_connecting............: avg=114.81µs min=0s      med=0s      max=384.06ms p(90)=0s       p(95)=0s      
           ✓ http_req_duration..............: avg=71.85ms  min=1.91ms  med=27.02ms max=876.15ms p(90)=212.21ms p(95)=274.53ms
                 { expected_response:true }...: avg=71.85ms  min=1.91ms  med=27.02ms max=876.15ms p(90)=212.21ms p(95)=274.53ms
             http_req_failed................: 0.00%   ✓ 0          ✗ 92504
             http_req_receiving.............: avg=18.75ms  min=18.53µs med=2.03ms  max=582.45ms p(90)=57.96ms  p(95)=95.49ms
             http_req_sending...............: avg=1.94ms   min=27.08µs med=51.09µs max=446.22ms p(90)=657.5µs  p(95)=3.21ms  
             http_req_tls_handshaking.......: avg=233.15µs min=0s      med=0s      max=608.34ms p(90)=0s       p(95)=0s      
             http_req_waiting...............: avg=51.14ms  min=0s      med=21.64ms max=650.38ms p(90)=146.53ms p(95)=185.54ms
             http_reqs......................: 92504   656.116513/s
             iteration_duration.............: avg=1.3s     min=1.01s   med=1.15s   max=2.59s    p(90)=1.86s    p(95)=2.02s   
             iterations.....................: 23126   164.029128/s
             vus............................: 5       min=5        max=400
             vus_max........................: 400     min=400      max=400
         
         ```
   
         </div>
       </details>

  
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   - Reverse Proxy 개선하기   
     - nginx를 컨테이너 방식이 아닌 직접 설치(apt install nginx)
     - /etc/nginx.conf에 4주차 미션이었던 `그럴듯한 서비스`의 `2단계 - 서비스 배포하기`에서 사용했던 nginx.conf를 활용하여 설정(개선전 상태)
     - k6를 이용하여 Smoke, Load, Stress 테스트를 시행하고 기록을 작성 
     - 이번 미션에서 제공된 설정파일에 있는 것들을 순차적으로 적용하였음
       1. `worker_processes auto;` 추가 및 k6테스트 후 최초테스트의 지표와 비교 후 성능상 개선점 확인 불가
       2. `events { worker_connections 10240; }` 추가 및 k6테스트 후 최초테스트의 지표와 비교 후 성능상 개선점 확인 불가
       3. `gzip on`을 비롯한 압축 관련 설정 추가 및 k6테스트 후 최초테스트의 지표와 비교 후 성능상 개선점 확인 불가
       4. 단일 포트(8080)에서, 듀얼포트(8080, 8081)로 분배 설정 추가 및 k6테스트 후 최초테스트의 지표와 비교 후 성능상 개선점 확인 불가
       5. proxy관련 설정을 전부추가(이 부분은 지식 부족으로 관련되어 있을 것으로 추정하였음) 후에 k6테스트 후 최초테스트의 지표와 비교 후 성능상 개선점 확인 불가 
     - 결론
       1. 모든 설정을 전부 추가 하였음에도 성능상의 이점을 발견할 수 없었음
       2. 그대로 복사 붙여 넣기 했음에도 설정을 잘 했는지에 대해서는 현재의 학습자의 수준상 확인하기 어려운 점으로 성능상 개선을 못했을 수 있음
   - WAS 성능 개선하기(Spring Data Cache)
     - docker로 redis를 구동 및 build.gradle에 redis관련 라이브러리 가져오도록 설정  
     - CacheConfig 클래스 추가 및 테스트 서비스에 해당하는 메서드에 대해서 @Cacheable 어노테이션 설정
       - 멤버조회, 역조회, 경로조회 서비스에 해당 어노테이션 
       - DTO중 캐쉬처리를 위해 (역)직렬화 안되는 LocalDateTime 필드에 관련 어노테이션 추가
         - https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonserialize.htm
         - https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsondeserialize.htm
     - 결론
       - 가장 쉽고 명확하게 응답시간의 감소를 확인할 수 있었음
       - http_req_duration기준 전/후 결과 값을 다음과 같이 확인 할 수 있었음
         - 다음 결과는 `테스트 유형(전 -> 후)`의 형태로 작성하였음 
         - Smoke(26ms -> 4.09ms)
         - Load(333.72ms -> 60.82ms)
         - Stress(345.62ms -> 71.85ms)
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

