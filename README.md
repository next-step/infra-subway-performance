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
   ![webpagetest](https://user-images.githubusercontent.com/64854054/123132515-c96e7700-d489-11eb-886d-c2369c000907.png)
   ![image](https://user-images.githubusercontent.com/64854054/123132360-a17f1380-d489-11eb-84c5-dd61e7615aff.png)

- Smoke
  - <details>
    <summary>로그인 (접기/펼치기)</summary>
    <div markdown="1">
    
    ```
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: smoke_login.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
      * default: 1 looping VUs for 10s (gracefulStop: 30s)
    
    
    running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
    default ✓ [======================================] 1 VUs  10s
    
         ✓ HTTP status OK
         ✓ logged in successfully
         ✓ retrieved member
    
         checks.........................: 100.00% ✓ 30  ✗ 0
         data_received..................: 11 kB   1.1 kB/s
         data_sent......................: 5.5 kB  536 B/s
         http_req_blocked...............: avg=1.44ms  min=5.03µs  med=6.29µs  max=28.71ms  p(90)=11.31µs  p(95)=1.46ms
         http_req_connecting............: avg=21.31µs min=0s      med=0s      max=426.32µs p(90)=0s       p(95)=21.31µs
       ✓ http_req_duration..............: avg=7.35ms  min=6.6ms   med=7.22ms  max=10.56ms  p(90)=8.08ms   p(95)=9.15ms
           { expected_response:true }...: avg=7.35ms  min=6.6ms   med=7.22ms  max=10.56ms  p(90)=8.08ms   p(95)=9.15ms
         http_req_failed................: 0.00%   ✓ 0   ✗ 20
         http_req_receiving.............: avg=82.03µs min=51.91µs med=75.14µs max=116.1µs  p(90)=111.75µs p(95)=115.05µs
         http_req_sending...............: avg=28.52µs min=16.41µs med=26.02µs max=90.72µs  p(90)=38.75µs  p(95)=41.68µs
         http_req_tls_handshaking.......: avg=1.37ms  min=0s      med=0s      max=27.53ms  p(90)=0s       p(95)=1.37ms
         http_req_waiting...............: avg=7.24ms  min=6.45ms  med=7.12ms  max=10.49ms  p(90)=7.94ms   p(95)=9.02ms
         http_reqs......................: 20      1.963114/s
         iteration_duration.............: avg=1.01s   min=1.01s   med=1.01s   max=1.04s    p(90)=1.02s    p(95)=1.03s
         iterations.....................: 10      0.981557/s
         vus............................: 1       min=1 max=1
         vus_max........................: 1       min=1 max=1
    ```    
    </div>
    </details>
  
  - <details>
    <summary>회원 정보 수정 (접기/펼치기)</summary>
    <div markdown="1">
    
    ```
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: smoke_mypage_edit.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
      * default: 1 looping VUs for 10s (gracefulStop: 30s)
    
    
    running (10.2s), 0/1 VUs, 10 complete and 0 interrupted iterations
    default ✓ [======================================] 1 VUs  10s
    
         ✓ HTTP status OK
         ✓ logged in successfully
         ✓ update successfully
    
         checks.........................: 100.00% ✓ 30  ✗ 0
         data_received..................: 10 kB   1.0 kB/s
         data_sent......................: 6.5 kB  635 B/s
         http_req_blocked...............: avg=1.94ms  min=4.76µs  med=6.92µs  max=38.74ms  p(90)=8.81µs   p(95)=1.95ms
         http_req_connecting............: avg=22.18µs min=0s      med=0s      max=443.68µs p(90)=0s       p(95)=22.18µs
       ✓ http_req_duration..............: avg=9.14ms  min=6.37ms  med=9.32ms  max=14.48ms  p(90)=11.28ms  p(95)=11.54ms
           { expected_response:true }...: avg=9.14ms  min=6.37ms  med=9.32ms  max=14.48ms  p(90)=11.28ms  p(95)=11.54ms
         http_req_failed................: 0.00%   ✓ 0   ✗ 20
         http_req_receiving.............: avg=79.52µs min=46.14µs med=75.85µs max=129.17µs p(90)=119.35µs p(95)=122.21µs
         http_req_sending...............: avg=34.33µs min=22.18µs med=31.14µs max=89.25µs  p(90)=41.41µs  p(95)=49.73µs
         http_req_tls_handshaking.......: avg=1.36ms  min=0s      med=0s      max=27.37ms  p(90)=0s       p(95)=1.36ms
         http_req_waiting...............: avg=9.03ms  min=6.22ms  med=9.19ms  max=14.38ms  p(90)=11.18ms  p(95)=11.42ms
         http_reqs......................: 20      1.954274/s
         iteration_duration.............: avg=1.02s   min=1.01s   med=1.01s   max=1.05s    p(90)=1.02s    p(95)=1.04s
         iterations.....................: 10      0.977137/s
         vus............................: 1       min=1 max=1
         vus_max........................: 1       min=1 max=1
    ```
    </div>
    </details>

  - <details>
    <summary>경로 조회 (접기/펼치기)</summary>
    <div markdown="1">

    ```
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: smoke_paths_new.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 1 max VUs, 40s max duration (incl. graceful stop):
      * default: 1 looping VUs for 10s (gracefulStop: 30s)
    
    
    running (10.3s), 0/1 VUs, 9 complete and 0 interrupted iterations
    default ✓ [======================================] 1 VUs  10s
    
         ✓ found path successfully
    
         checks.........................: 100.00% ✓ 9   ✗ 0
         data_received..................: 25 kB   2.4 kB/s
         data_sent......................: 3.1 kB  304 B/s
         http_req_blocked...............: avg=2.02ms  min=5µs      med=7.44µs  max=34.77ms  p(90)=499.42µs p(95)=6.57ms
         http_req_connecting............: avg=49.31µs min=0s       med=0s      max=490.95µs p(90)=119.02µs p(95)=410.88µs
       ✗ http_req_duration..............: avg=69.75ms min=605.27µs med=50.1ms  max=185.34ms p(90)=153.04ms p(95)=161.83ms
           { expected_response:true }...: avg=69.75ms min=605.27µs med=50.1ms  max=185.34ms p(90)=153.04ms p(95)=161.83ms
         http_req_failed................: 0.00%   ✓ 0   ✗ 18
         http_req_receiving.............: avg=94.46µs min=70.37µs  med=82.87µs max=223.01µs p(90)=109.46µs p(95)=131.39µs
         http_req_sending...............: avg=26.03µs min=14.85µs  med=22.07µs max=90.01µs  p(90)=36.88µs  p(95)=62.88µs
         http_req_tls_handshaking.......: avg=1.9ms   min=0s       med=0s      max=34.29ms  p(90)=0s       p(95)=5.14ms
         http_req_waiting...............: avg=69.63ms min=513.11µs med=49.96ms max=185.22ms p(90)=152.8ms  p(95)=161.71ms
         http_reqs......................: 18      1.747014/s
         iteration_duration.............: avg=1.14s   min=1.1s     med=1.14s   max=1.19s    p(90)=1.18s    p(95)=1.18s
         iterations.....................: 9       0.873507/s
         vus............................: 1       min=1 max=1
         vus_max........................: 1       min=1 max=1
    ```
    </div>
    </details>
    
- Load
  - <details>
    <summary>로그인 (접기/펼치기)</summary>
    <div markdown="1">
      
    ```
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: load_login.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 60 max VUs, 20m30s max duration (incl. graceful stop):
      * default: Up to 60 looping VUs for 20m0s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (20m00.8s), 00/60 VUs, 53400 complete and 0 interrupted iterations
    default ✓ [======================================] 00/60 VUs  20m0s
    
         ✓ HTTP status OK
         ✓ logged in successfully
         ✓ retrieved member
    
         checks.........................: 100.00% ✓ 160200 ✗ 0
         data_received..................: 29 MB   24 kB/s
         data_sent......................: 20 MB   17 kB/s
         http_req_blocked...............: avg=19.11µs  min=2.55µs  med=2.81µs  max=40.12ms  p(90)=3.08µs   p(95)=3.49µs
         http_req_connecting............: avg=1.1µs    min=0s      med=0s      max=7.7ms    p(90)=0s       p(95)=0s
       ✓ http_req_duration..............: avg=6.48ms   min=38.86µs med=5.86ms  max=556.99ms p(90)=9.03ms   p(95)=10.77ms
           { expected_response:true }...: avg=6.48ms   min=38.86µs med=5.86ms  max=556.99ms p(90)=9.03ms   p(95)=10.77ms
         http_req_failed................: 0.00%   ✓ 0      ✗ 106800
         http_req_receiving.............: avg=145.21µs min=32.21µs med=63.43µs max=21.91ms  p(90)=206.46µs p(95)=490.48µs
         http_req_sending...............: avg=186.09µs min=0s      med=73.34µs max=32.17ms  p(90)=277.81µs p(95)=674.23µs
         http_req_tls_handshaking.......: avg=7.48µs   min=0s      med=0s      max=29.26ms  p(90)=0s       p(95)=0s
         http_req_waiting...............: avg=6.15ms   min=0s      med=5.58ms  max=556.5ms  p(90)=8.56ms   p(95)=10.21ms
         http_reqs......................: 106800  88.941824/s
         iteration_duration.............: avg=1.01s    min=1s      med=1.01s   max=1.7s     p(90)=1.01s    p(95)=1.02s
         iterations.....................: 53400   44.470912/s
         vus............................: 1       min=1    max=60
         vus_max........................: 60      min=60   max=60
    ```
    </div>
    </details>

  - <details>
    <summary>회원 정보 수정 (접기/펼치기)</summary>
    <div markdown="1">

    ```
        
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: load_mypage_edit.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 60 max VUs, 20m30s max duration (incl. graceful stop):
      * default: Up to 60 looping VUs for 20m0s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (20m01.0s), 00/60 VUs, 53190 complete and 0 interrupted iterations
    default ✓ [======================================] 00/60 VUs  20m0s
    
         ✓ HTTP status OK
         ✓ logged in successfully
         ✓ update successfully
    
         checks.........................: 100.00% ✓ 159570 ✗ 0
         data_received..................: 27 MB   22 kB/s
         data_sent......................: 23 MB   19 kB/s
         http_req_blocked...............: avg=18.43µs  min=2.54µs  med=2.82µs  max=31.37ms  p(90)=3.08µs   p(95)=3.39µs
         http_req_connecting............: avg=1.03µs   min=0s      med=0s      max=11.09ms  p(90)=0s       p(95)=0s
       ✓ http_req_duration..............: avg=8.45ms   min=50.87µs med=7.93ms  max=349.57ms p(90)=12.68ms  p(95)=14.95ms
           { expected_response:true }...: avg=8.45ms   min=50.87µs med=7.93ms  max=349.57ms p(90)=12.68ms  p(95)=14.95ms
         http_req_failed................: 0.00%   ✓ 0      ✗ 106380
         http_req_receiving.............: avg=125.57µs min=14.29µs med=46.53µs max=28.04ms  p(90)=140.83µs p(95)=404.35µs
         http_req_sending...............: avg=234.55µs min=0s      med=87.8µs  max=36.72ms  p(90)=408.9µs  p(95)=845.89µs
         http_req_tls_handshaking.......: avg=7.07µs   min=0s      med=0s      max=29.49ms  p(90)=0s       p(95)=0s
         http_req_waiting...............: avg=8.09ms   min=0s      med=7.66ms  max=349.45ms p(90)=12.2ms   p(95)=14.4ms
         http_reqs......................: 106380  88.575175/s
         iteration_duration.............: avg=1.01s    min=1s      med=1.01s   max=1.38s    p(90)=1.02s    p(95)=1.02s
         iterations.....................: 53190   44.287588/s
         vus............................: 1       min=1    max=60
         vus_max........................: 60      min=60   max=60
    ```
    </div>
    </details>

  - <details>
    <summary>경로 조회 (접기/펼치기)</summary>
    <div markdown="1">

    ```
        
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: load_paths.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 60 max VUs, 20m30s max duration (incl. graceful stop):
      * default: Up to 60 looping VUs for 20m0s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (20m00.2s), 00/60 VUs, 19272 complete and 0 interrupted iterations
    default ✓ [======================================] 00/60 VUs  20m0s
    
         ✓ found path successfully
    
         checks.........................: 100.00% ✓ 19272 ✗ 0
         data_received..................: 35 MB   29 kB/s
         data_sent......................: 1.7 MB  1.4 kB/s
         http_req_blocked...............: avg=19.99µs min=2.59µs  med=2.94µs   max=48.25ms  p(90)=3.12µs   p(95)=3.23µs
         http_req_connecting............: avg=1.6µs   min=0s      med=0s       max=898.38µs p(90)=0s       p(95)=0s
       ✗ http_req_duration..............: avg=1.81s   min=72.75ms med=2.27s    max=6.3s     p(90)=2.51s    p(95)=2.58s
           { expected_response:true }...: avg=1.81s   min=72.75ms med=2.27s    max=6.3s     p(90)=2.51s    p(95)=2.58s
         http_req_failed................: 0.00%   ✓ 0     ✗ 19272
         http_req_receiving.............: avg=115.8µs min=49.15µs med=103.84µs max=6.82ms   p(90)=138.72µs p(95)=157.26µs
         http_req_sending...............: avg=67.66µs min=31.21µs med=63.96µs  max=2.03ms   p(90)=78.93µs  p(95)=87.55µs
         http_req_tls_handshaking.......: avg=14.52µs min=0s      med=0s       max=46.48ms  p(90)=0s       p(95)=0s
         http_req_waiting...............: avg=1.81s   min=72.56ms med=2.27s    max=6.3s     p(90)=2.51s    p(95)=2.58s
         http_reqs......................: 19272   16.057248/s
         iteration_duration.............: avg=2.81s   min=1.07s   med=3.27s    max=7.3s     p(90)=3.51s    p(95)=3.58s
         iterations.....................: 19272   16.057248/s
         vus............................: 1       min=1   max=60
         vus_max........................: 60      min=60  max=60
    ```
    </div>
    </details>
    
- Stress
  - <details>
    <summary>로그인 (접기/펼치기)</summary>
    <div markdown="1">

    ```
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: stress_login.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 800 max VUs, 15m30s max duration (incl. graceful stop):
      * default: Up to 800 looping VUs for 15m0s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (15m00.5s), 000/800 VUs, 230219 complete and 0 interrupted iterations
    default ✓ [======================================] 000/800 VUs  15m0s
    
         ✓ HTTP status OK
         ✓ logged in successfully
         ✓ retrieved member
    
         checks.........................: 100.00% ✓ 690657 ✗ 0
         data_received..................: 125 MB  139 kB/s
         data_sent......................: 87 MB   97 kB/s
         http_req_blocked...............: avg=447.22µs min=2.54µs  med=2.76µs  max=1.65s    p(90)=3.03µs   p(95)=4.7µs
         http_req_connecting............: avg=159.86µs min=0s      med=0s      max=929.25ms p(90)=0s       p(95)=0s
       ✗ http_req_duration..............: avg=162.66ms min=46.71µs med=74.72ms max=2.6s     p(90)=439.62ms p(95)=560.02ms
           { expected_response:true }...: avg=162.66ms min=46.71µs med=74.72ms max=2.6s     p(90)=439.62ms p(95)=560.02ms
         http_req_failed................: 0.00%   ✓ 0      ✗ 460438
         http_req_receiving.............: avg=2.22ms   min=27.63µs med=56.27µs max=832.45ms p(90)=584.28µs p(95)=1.72ms
         http_req_sending...............: avg=4.71ms   min=0s      med=64µs    max=1.19s    p(90)=1.2ms    p(95)=8.04ms
         http_req_tls_handshaking.......: avg=179.68µs min=0s      med=0s      max=1.13s    p(90)=0s       p(95)=0s
         http_req_waiting...............: avg=155.72ms min=0s      med=69.93ms max=2.5s     p(90)=422.72ms p(95)=540.53ms
         http_reqs......................: 460438  511.29621/s
         iteration_duration.............: avg=1.4s     min=1s      med=1.22s   max=4.37s    p(90)=2.05s    p(95)=2.28s
         iterations.....................: 230219  255.648105/s
         vus............................: 1       min=1    max=800
         vus_max........................: 800     min=800  max=800
    ```
    </div>
    </details>

  - <details>
    <summary>회원 정보 수정 (접기/펼치기)</summary>
    <div markdown="1">

    ```
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: stress_mypage_edit.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 800 max VUs, 15m30s max duration (incl. graceful stop):
      * default: Up to 800 looping VUs for 15m0s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (15m00.4s), 000/800 VUs, 211335 complete and 0 interrupted iterations
    default ✓ [======================================] 000/800 VUs  15m0s
    
         ✓ HTTP status OK
         ✓ logged in successfully
         ✓ update successfully
    
         checks.........................: 100.00% ✓ 634005 ✗ 0
         data_received..................: 108 MB  120 kB/s
         data_sent......................: 91 MB   101 kB/s
         http_req_blocked...............: avg=440.91µs min=2.54µs  med=2.77µs   max=1.81s   p(90)=3.01µs   p(95)=3.49µs
         http_req_connecting............: avg=142.82µs min=0s      med=0s       max=1.21s   p(90)=0s       p(95)=0s
       ✗ http_req_duration..............: avg=229.45ms min=16.29µs med=138.72ms max=3.46s   p(90)=569.79ms p(95)=756.67ms
           { expected_response:true }...: avg=229.45ms min=16.29µs med=138.72ms max=3.46s   p(90)=569.79ms p(95)=756.67ms
         http_req_failed................: 0.00%   ✓ 0      ✗ 422670
         http_req_receiving.............: avg=1.69ms   min=12.08µs med=44.84µs  max=814.1ms p(90)=377.32µs p(95)=1.27ms
         http_req_sending...............: avg=5.54ms   min=0s      med=76.71µs  max=1.05s   p(90)=1.88ms   p(95)=14.88ms
         http_req_tls_handshaking.......: avg=183.89µs min=0s      med=0s       max=1.56s   p(90)=0s       p(95)=0s
         http_req_waiting...............: avg=222.2ms  min=0s      med=132.01ms max=3.45s   p(90)=554.17ms p(95)=737.16ms
         http_reqs......................: 422670  469.405289/s
         iteration_duration.............: avg=1.52s    min=1s      med=1.39s    max=5.2s    p(90)=2.28s    p(95)=2.59s
         iterations.....................: 211335  234.702644/s
         vus............................: 3       min=1    max=800
         vus_max........................: 800     min=800  max=800
    ```
    </div>
    </details>

  - <details>
    <summary>경로 조회 (접기/펼치기)</summary>
    <div markdown="1">

    ```
        
              /\      |‾‾| /‾‾/   /‾‾/
         /\  /  \     |  |/  /   /  /
        /  \/    \    |     (   /   ‾‾\
       /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: stress_paths.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 800 max VUs, 15m30s max duration (incl. graceful stop):
      * default: Up to 800 looping VUs for 15m0s over 6 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    WARN[0637] Request Failed                                error="Get \"https://seondongpyo.kro.kr/paths?source=1&target=10\": context deadline exceeded"
    
    running (15m00.2s), 000/800 VUs, 15398 complete and 88 interrupted iterations
    default ✓ [======================================] 000/800 VUs  15m0s
    
         ✗ found path successfully
          ↳  99% — ✓ 15395 / ✗ 10
    
         checks.........................: 99.93% ✓ 15395 ✗ 10
         data_received..................: 31 MB  35 kB/s
         data_sent......................: 1.8 MB 2.0 kB/s
         http_req_blocked...............: avg=269.27µs min=2.6µs   med=2.96µs   max=62.31ms p(90)=3.22µs   p(95)=4.21ms
         http_req_connecting............: avg=32.41µs  min=0s      med=0s       max=17.28ms p(90)=0s       p(95)=415.23µs
       ✗ http_req_duration..............: avg=20.49s   min=74.08ms med=18.98s   max=1m0s    p(90)=41.06s   p(95)=43.45s
           { expected_response:true }...: avg=20.47s   min=74.08ms med=18.98s   max=55.67s  p(90)=41.05s   p(95)=43.44s
         http_req_failed................: 0.06%  ✓ 10    ✗ 15395
         http_req_receiving.............: avg=120.98µs min=0s      med=104.77µs max=18.69ms p(90)=140.91µs p(95)=162.58µs
         http_req_sending...............: avg=76.99µs  min=31.35µs med=64.92µs  max=5.68ms  p(90)=86.7µs   p(95)=131.41µs
         http_req_tls_handshaking.......: avg=220.87µs min=0s      med=0s       max=50.8ms  p(90)=0s       p(95)=3.59ms
         http_req_waiting...............: avg=20.49s   min=73.89ms med=18.98s   max=1m0s    p(90)=41.06s   p(95)=43.45s
         http_reqs......................: 15405  17.111975/s
         iteration_duration.............: avg=21.48s   min=1.07s   med=19.98s   max=1m1s    p(90)=42.05s   p(95)=44.44s
         iterations.....................: 15398  17.104199/s
         vus............................: 1      min=1   max=800
         vus_max........................: 800    min=800 max=800
    ```
    </div>
    </details>
    
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    1. **리버스 프록시**
        - nginx.conf 수정 후, 해당 설정으로 docker 리버스 프록시 서버 실행
            - ```$ docker build -t nextstep/reverse-proxy:0.0.2 . ```
            - ```$ docker run -d -p 80:80 -p 443:443 -v /var/log/nginx:/var/log/nginx --name proxy nextstep/reverse-proxy:0.0.2```
        
    2. **정적 리소스 캐싱**
        - '/resource/static/' 이하의 정적 리소스에 대한 캐시 설정
    
    3. **Redis**
        - 부하 테스트를 하는 시나리오는 '경로 탐색'이므로, ```@Cacheable```만 적용
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

