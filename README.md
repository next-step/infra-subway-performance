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
- Smoke Test
  - 경로 찾기
    ```javascript
    
              /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
        /          \   |  |\  \ |  (‾)  |
        / __________ \  |__| \__\ \_____/ .io
        
        execution: local
        script: smoke/find_path.js
        output: -
        
        scenarios: (100.00%) 1 scenario, 1 max VUs, 31s max duration (incl. graceful stop):
        * default: 1 looping VUs for 1s (gracefulStop: 30s)
        
        INFO[0000] source:382, target:290                        source=console
        
        running (01.4s), 0/1 VUs, 1 complete and 0 interrupted iterations
        default ✓ [======================================] 1 VUs  1s
        
        ✓ id
        ✓ 경로 찾기
        
        checks.........................: 100.00% ✓ 2   ✗ 0  
        data_received..................: 78 kB   55 kB/s
        data_sent......................: 1.1 kB  778 B/s
        http_req_blocked...............: avg=110.34ms min=1µs    med=110.34ms max=220.68ms p(90)=198.62ms p(95)=209.65ms
        http_req_connecting............: avg=3.5ms    min=0s     med=3.5ms    max=7.01ms   p(90)=6.31ms   p(95)=6.66ms  
        ✓ http_req_duration..............: avg=93.38ms  min=9.31ms med=93.38ms  max=177.44ms p(90)=160.63ms p(95)=169.04ms
        { expected_response:true }...: avg=93.38ms  min=9.31ms med=93.38ms  max=177.44ms p(90)=160.63ms p(95)=169.04ms
        http_req_failed................: 0.00%   ✓ 0   ✗ 2  
        http_req_receiving.............: avg=9.89ms   min=100µs  med=9.89ms   max=19.68ms  p(90)=17.72ms  p(95)=18.7ms  
        http_req_sending...............: avg=260.5µs  min=56µs   med=260.5µs  max=465µs    p(90)=424.1µs  p(95)=444.55µs
        http_req_tls_handshaking.......: avg=96.64ms  min=0s     med=96.64ms  max=193.28ms p(90)=173.95ms p(95)=183.61ms
        http_req_waiting...............: avg=83.23ms  min=9.16ms med=83.23ms  max=157.3ms  p(90)=142.48ms p(95)=149.89ms
        http_reqs......................: 2       1.414692/s
        iteration_duration.............: avg=1.41s    min=1.41s  med=1.41s    max=1.41s    p(90)=1.41s    p(95)=1.41s   
        iterations.....................: 1       0.707346/s
        vus............................: 1       min=1 max=1
        vus_max........................: 1       min=1 max=1
    ```
  - 내 정보 가져오기
    ```js
                /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
      /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: smoke/sign_in.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 1 max VUs, 10m30s max duration (incl. graceful stop):
      * default: 1 iterations for each of 1 VUs (maxDuration: 10m0s, gracefulStop: 30s)
    
    INFO[0000] [object Object]                               source=console
    
    running (00m01.3s), 0/1 VUs, 1 complete and 0 interrupted iterations
    default ✓ [======================================] 1 VUs  00m01.3s/10m0s  1/1 iters, 1 per VU

     ✓ 로그인 성공
     ✓ retrieved member

     checks.....................: 100.00% ✓ 2   ✗ 0  
     data_received..............: 5.2 kB  3.9 kB/s
     data_sent..................: 847 B   641 B/s
     http_req_blocked...........: avg=94.85ms min=0s      med=94.85ms max=189.71ms p(90)=170.74ms p(95)=180.22ms
     http_req_connecting........: avg=8.73ms  min=0s      med=8.73ms  max=17.46ms  p(90)=15.71ms  p(95)=16.59ms 
     http_req_duration..........: avg=64.48ms min=60.22ms med=64.48ms max=68.74ms  p(90)=67.89ms  p(95)=68.31ms 
     http_req_failed............: 100.00% ✓ 2   ✗ 0  
     http_req_receiving.........: avg=586.5µs min=88µs    med=586.5µs max=1.08ms   p(90)=985.3µs  p(95)=1.03ms  
     http_req_sending...........: avg=335.5µs min=22µs    med=335.5µs max=649µs    p(90)=586.3µs  p(95)=617.64µs
     http_req_tls_handshaking...: avg=85.24ms min=0s      med=85.24ms max=170.48ms p(90)=153.44ms p(95)=161.96ms
     http_req_waiting...........: avg=63.56ms min=59.48ms med=63.56ms max=67.63ms  p(90)=66.82ms  p(95)=67.22ms 
     http_reqs..................: 2       1.513964/s
     iteration_duration.........: avg=1.31s   min=1.31s   med=1.31s   max=1.31s    p(90)=1.31s    p(95)=1.31s   
     iterations.................: 1       0.756982/s
     vus........................: 1       min=1 max=1
     vus_max....................: 1       min=1 max=1
    ```
  - 노선 정보 가져오기
    ```javascript
        
              /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
      /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: smoke/get_lines.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 1 max VUs, 31s max duration (incl. graceful stop):
      * default: 1 looping VUs for 1s (gracefulStop: 30s)
    
    
      running (01.2s), 0/1 VUs, 1 complete and 0 interrupted iterations
      default ✓ [======================================] 1 VUs  1s

     ✓ 노선 불러오기

    checks.........................: 100.00% ✓ 1   ✗ 0  
    data_received..................: 12 kB   10 kB/s
    data_sent......................: 698 B   573 B/s
    http_req_blocked...............: avg=187.57ms min=187.57ms med=187.57ms max=187.57ms p(90)=187.57ms p(95)=187.57ms
    http_req_connecting............: avg=17.16ms  min=17.16ms  med=17.16ms  max=17.16ms  p(90)=17.16ms  p(95)=17.16ms 
    ✓ http_req_duration..............: avg=25.15ms  min=25.15ms  med=25.15ms  max=25.15ms  p(90)=25.15ms  p(95)=25.15ms
    { expected_response:true }...: avg=25.15ms  min=25.15ms  med=25.15ms  max=25.15ms  p(90)=25.15ms  p(95)=25.15ms
    http_req_failed................: 0.00%   ✓ 0   ✗ 1  
    http_req_receiving.............: avg=101µs    min=101µs    med=101µs    max=101µs    p(90)=101µs    p(95)=101µs   
    http_req_sending...............: avg=570µs    min=570µs    med=570µs    max=570µs    p(90)=570µs    p(95)=570µs   
    http_req_tls_handshaking.......: avg=168.05ms min=168.05ms med=168.05ms max=168.05ms p(90)=168.05ms p(95)=168.05ms
    http_req_waiting...............: avg=24.48ms  min=24.48ms  med=24.48ms  max=24.48ms  p(90)=24.48ms  p(95)=24.48ms
    http_reqs......................: 1       0.821832/s
    iteration_duration.............: avg=1.21s    min=1.21s    med=1.21s    max=1.21s    p(90)=1.21s    p(95)=1.21s   
    iterations.....................: 1       0.821832/s
    vus............................: 1       min=1 max=1
    vus_max........................: 1       min=1 max=1
    ```

- Load Test
  - 경로 찾기
    ```javascript
                  /\      |‾‾| /‾‾/   /‾‾/   
           /\  /  \     |  |/  /   /  /    
          /  \/    \    |     (   /   ‾‾\  
        /          \   |  |\  \ |  (‾)  |
        / __________ \  |__| \__\ \_____/ .io
      
        execution: local
        script: load/find_path.js
        output: -
      
        scenarios: (100.00%) 1 scenario, 800 max VUs, 10m30s max duration (incl. graceful stop):
        * default: Up to 800 looping VUs for 10m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)
      
      
      running (10m01.0s), 000/800 VUs, 237497 complete and 0 interrupted iterations
      default ✓ [======================================] 000/800 VUs  10m0s

     █ 경로 찾기

       ✓ Status OK

     checks.........................: 100.00% ✓ 237497 ✗ 0     
     data_received..................: 298 MB  497 kB/s
     data_sent......................: 21 MB   35 kB/s
     group_duration.................: avg=1.01s   min=1s     med=1s     max=2.18s    p(90)=1.01s   p(95)=1.01s  
     http_req_blocked...............: avg=126.6µs min=0s     med=1µs    max=1.17s    p(90)=1µs     p(95)=1µs    
     http_req_connecting............: avg=50.45µs min=0s     med=0s     max=1.13s    p(90)=0s      p(95)=0s     
    ✓ http_req_duration..............: avg=10.04ms min=3.55ms med=8.8ms  max=234.9ms  p(90)=13.18ms p(95)=16.65ms
    { expected_response:true }...: avg=10.04ms min=3.55ms med=8.8ms  max=234.9ms  p(90)=13.18ms p(95)=16.65ms
    http_req_failed................: 0.00%   ✓ 0      ✗ 237497
    http_req_receiving.............: avg=99.93µs min=8µs    med=57µs   max=101.41ms p(90)=133µs   p(95)=197µs  
    http_req_sending...............: avg=76.17µs min=6µs    med=44µs   max=87.16ms  p(90)=110µs   p(95)=153µs  
    http_req_tls_handshaking.......: avg=74.25µs min=0s     med=0s     max=177.48ms p(90)=0s      p(95)=0s     
    http_req_waiting...............: avg=9.86ms  min=2µs    med=8.65ms max=234.71ms p(90)=12.95ms p(95)=16.35ms
    http_reqs......................: 237497  395.15536/s
    iteration_duration.............: avg=1.01s   min=1s     med=1s     max=2.18s    p(90)=1.01s   p(95)=1.01s  
    iterations.....................: 237497  395.15536/s
    vus............................: 5       min=2    max=800
    vus_max........................: 800     min=800  max=800
    ```
  - 내 정보 가져오기
    ```javascript
                /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
      /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: load/sign_in.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 800 max VUs, 10m30s max duration (incl. graceful stop):
      * default: Up to 800 looping VUs for 10m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (10m02.3s), 000/800 VUs, 235974 complete and 0 interrupted iterations
    default ✓ [======================================] 000/800 VUs  10m0s

     █ setup

       ✓ logged in successfully

     █ 내 정보 가져오기

       ✓ retrieved member

     checks.....................: 100.00% ✓ 235975 ✗ 0    
     data_received..............: 68 MB   113 kB/s
     data_sent..................: 21 MB   34 kB/s
     group_duration.............: avg=1.01s   min=1s     med=1.01s   max=1.24s    p(90)=1.02s   p(95)=1.04s  
     http_req_blocked...........: avg=94.87µs min=0s     med=1µs     max=186.5ms  p(90)=1µs     p(95)=1µs    
     http_req_connecting........: avg=30.21µs min=0s     med=0s      max=47.04ms  p(90)=0s      p(95)=0s     
    ✓ http_req_duration..........: avg=17.05ms min=5.53ms med=13.11ms max=241.55ms p(90)=27.85ms p(95)=38.98ms
    http_req_failed............: 100.00% ✓ 235975 ✗ 0    
    http_req_receiving.........: avg=62.3µs  min=7µs    med=52µs    max=11.16ms  p(90)=99µs    p(95)=125µs  
    http_req_sending...........: avg=50.48µs min=6µs    med=44µs    max=5.46ms   p(90)=82µs    p(95)=106µs  
    http_req_tls_handshaking...: avg=63.09µs min=0s     med=0s      max=167.51ms p(90)=0s      p(95)=0s     
    http_req_waiting...........: avg=16.94ms min=3.71ms med=12.99ms max=241.35ms p(90)=27.75ms p(95)=38.89ms
    http_reqs..................: 235975  391.821414/s
    iteration_duration.........: avg=1.01s   min=1s     med=1.01s   max=1.24s    p(90)=1.02s   p(95)=1.04s  
    iterations.................: 235974  391.819754/s
    vus........................: 182     min=0    max=799
    vus_max....................: 800     min=800  max=800

    ```
  
  - 노선 정보 가져오기
    ```javascript
        
              /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
      /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: load/get_lines.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 800 max VUs, 10m30s max duration (incl. graceful stop):
      * default: Up to 800 looping VUs for 10m0s over 2 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (10m01.2s), 000/800 VUs, 236948 complete and 0 interrupted iterations
    default ✓ [======================================] 000/800 VUs  10m0s

     █ 노선 정보 불러오기

       ✓ Status OK

      checks.........................: 100.00% ✓ 236948 ✗ 0     
      data_received..................: 1.8 GB  2.9 MB/s
      data_sent......................: 31 MB   51 kB/s
      group_duration.................: avg=1.01s    min=1s     med=1.01s  max=1.82s    p(90)=1.01s   p(95)=1.02s  
      http_req_blocked...............: avg=129.39µs min=0s     med=1µs    max=244.08ms p(90)=1µs     p(95)=1µs    
      http_req_connecting............: avg=37.66µs  min=0s     med=0s     max=45.92ms  p(90)=0s      p(95)=0s     
      ✓ http_req_duration..............: avg=13.28ms  min=4.36ms med=9.75ms max=822.2ms  p(90)=15.69ms p(95)=23.42ms
      { expected_response:true }...: avg=13.28ms  min=4.36ms med=9.75ms max=822.2ms  p(90)=15.69ms p(95)=23.42ms
      http_req_failed................: 0.00%   ✓ 0      ✗ 236948
      http_req_receiving.............: avg=262.4µs  min=14µs   med=100µs  max=170.98ms p(90)=242µs   p(95)=449µs  
      http_req_sending...............: avg=87µs     min=6µs    med=46µs   max=239.74ms p(90)=106µs   p(95)=151µs  
      http_req_tls_handshaking.......: avg=89.59µs  min=0s     med=0s     max=206.98ms p(90)=0s      p(95)=0s     
      http_req_waiting...............: avg=12.93ms  min=2µs    med=9.52ms max=733.09ms p(90)=15.28ms p(95)=22.92ms
      http_reqs......................: 236948  394.137642/s
      iteration_duration.............: avg=1.01s    min=1s     med=1.01s  max=1.82s    p(90)=1.01s   p(95)=1.02s  
      iterations.....................: 236948  394.137642/s
      vus............................: 70      min=2    max=800
      vus_max........................: 800     min=800  max=800
    ```

- Stress Test
  - 경로 찾기
    ```
            /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
        /          \   |  |\  \ |  (‾)  | 
        / __________ \  |__| \__\ \_____/ .io

        execution: local
         script: load/find_path.js
         output: -
        
        scenarios: (100.00%) 1 scenario, 1000 max VUs, 2m0s max duration (incl. graceful stop):
               * default: Up to 1000 looping VUs for 1m30s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
        running (1m31.1s), 0000/1000 VUs, 50807 complete and 0 interrupted iterations
        default ✓ [======================================] 0000/1000 VUs  1m30s
    
         █ 경로 찾기
    
           ✓ Status OK
    
         checks.........................: 100.00% ✓ 50807  ✗ 0     
         data_received..................: 68 MB   743 kB/s
         data_sent......................: 5.0 MB  54 kB/s
         group_duration.................: avg=1.01s    min=1s     med=1s     max=1.36s    p(90)=1.01s   p(95)=1.03s  
         http_req_blocked...............: avg=751.53µs min=0s     med=1µs    max=348.19ms p(90)=1µs     p(95)=1µs    
         http_req_connecting............: avg=240.14µs min=0s     med=0s     max=195.87ms p(90)=0s      p(95)=0s     
       ✓ http_req_duration..............: avg=11.45ms  min=3.63ms med=8.68ms max=210.82ms p(90)=15.23ms p(95)=22.27ms
           { expected_response:true }...: avg=11.45ms  min=3.63ms med=8.68ms max=210.82ms p(90)=15.23ms p(95)=22.27ms
         http_req_failed................: 0.00%   ✓ 0      ✗ 50807 
         http_req_receiving.............: avg=103.15µs min=8µs    med=59µs   max=51.93ms  p(90)=131µs   p(95)=203µs  
         http_req_sending...............: avg=67.92µs  min=6µs    med=44µs   max=9.83ms   p(90)=104µs   p(95)=154µs  
         http_req_tls_handshaking.......: avg=505.54µs min=0s     med=0s     max=226.38ms p(90)=0s      p(95)=0s     
         http_req_waiting...............: avg=11.28ms  min=409µs  med=8.53ms max=210.75ms p(90)=15ms    p(95)=22.03ms
         http_reqs......................: 50807   557.884567/s
         iteration_duration.............: avg=1.01s    min=1s     med=1s     max=1.36s    p(90)=1.01s   p(95)=1.03s  
         iterations.....................: 50807   557.884567/s
         vus............................: 5       min=5    max=1000
         vus_max........................: 1000    min=1000 max=1000
    ```
  - 내정보 가져오기
    ```javascript
                 /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
        /          \   |  |\  \ |  (‾)  |
        / __________ \  |__| \__\ \_____/ .io
        
        execution: local
        script: load/sign_in.js
        output: -
        
        scenarios: (100.00%) 1 scenario, 1000 max VUs, 2m0s max duration (incl. graceful stop):
        * default: Up to 1000 looping VUs for 1m30s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)

    
        running (1m32.2s), 0000/1000 VUs, 50745 complete and 0 interrupted iterations
        default ✗ [======================================] 0000/1000 VUs  1m30s
    
         █ setup
    
           ✓ logged in successfully
    
         █ 내 정보 가져오기
    
           ✓ retrieved member

        checks.....................: 100.00% ✓ 50746  ✗ 0     
        data_received..............: 18 MB   200 kB/s
        data_sent..................: 4.9 MB  54 kB/s
        group_duration.............: avg=1.01s    min=1s     med=1.01s   max=1.24s    p(90)=1.02s   p(95)=1.03s  
        http_req_blocked...........: avg=520.71µs min=0s     med=1µs     max=178.79ms p(90)=1µs     p(95)=1µs    
        http_req_connecting........: avg=154.89µs min=0s     med=0s      max=157.21ms p(90)=0s      p(95)=0s     
        ✓ http_req_duration..........: avg=13.94ms  min=5.42ms med=10.43ms max=246.21ms p(90)=21.09ms p(95)=35.81ms
        http_req_failed............: 100.00% ✓ 50746  ✗ 0     
        http_req_receiving.........: avg=61.04µs  min=8µs    med=50µs    max=11.62ms  p(90)=92µs    p(95)=115µs  
        http_req_sending...........: avg=52.25µs  min=6µs    med=42µs    max=18.98ms  p(90)=77µs    p(95)=101µs  
        http_req_tls_handshaking...: avg=360.72µs min=0s     med=0s      max=163.11ms p(90)=0s      p(95)=0s     
        http_req_waiting...........: avg=13.82ms  min=5.34ms med=10.32ms max=246.11ms p(90)=20.97ms p(95)=35.7ms
        http_reqs..................: 50746   550.16249/s
        iteration_duration.........: avg=1.01s    min=1s     med=1.01s   max=1.24s    p(90)=1.02s   p(95)=1.03s  
        iterations.................: 50745   550.151649/s
        vus........................: 255     min=0    max=998
        vus_max....................: 1000    min=1000 max=1000
    ```
  - 노선 정보 가져오기
    ```javascript
                /\      |‾‾| /‾‾/   /‾‾/   
         /\  /  \     |  |/  /   /  /    
        /  \/    \    |     (   /   ‾‾\  
      /          \   |  |\  \ |  (‾)  |
      / __________ \  |__| \__\ \_____/ .io
    
      execution: local
      script: load/get_lines.js
      output: -
    
      scenarios: (100.00%) 1 scenario, 1000 max VUs, 2m0s max duration (incl. graceful stop):
      * default: Up to 1000 looping VUs for 1m30s over 4 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
    running (1m31.1s), 0000/1000 VUs, 50891 complete and 0 interrupted iterations
    default ✗ [======================================] 0000/1000 VUs  1m30s

     █ 노선 정보 불러오기

       ✓ Status OK

      checks.........................: 100.00% ✓ 50891  ✗ 0     
      data_received..................: 383 MB  4.2 MB/s
      data_sent......................: 7.1 MB  78 kB/s
      group_duration.................: avg=1.01s    min=1s     med=1s     max=2.28s    p(90)=1.01s   p(95)=1.01s  
      http_req_blocked...............: avg=676.73µs min=0s     med=1µs    max=1.27s    p(90)=1µs     p(95)=1µs    
      http_req_connecting............: avg=218µs    min=0s     med=0s     max=1.24s    p(90)=0s      p(95)=0s     
      ✓ http_req_duration..............: avg=11.4ms   min=4.59ms med=9.22ms max=461.13ms p(90)=13.21ms p(95)=15.14ms
      { expected_response:true }...: avg=11.4ms   min=4.59ms med=9.22ms max=461.13ms p(90)=13.21ms p(95)=15.14ms
      http_req_failed................: 0.00%   ✓ 0      ✗ 50891
      http_req_receiving.............: avg=192.47µs min=14µs   med=82µs   max=76.77ms  p(90)=167µs   p(95)=231µs  
      http_req_sending...............: avg=52.08µs  min=7µs    med=41µs   max=15.85ms  p(90)=77µs    p(95)=103µs  
      http_req_tls_handshaking.......: avg=453.05µs min=0s     med=0s     max=176.28ms p(90)=0s      p(95)=0s     
      http_req_waiting...............: avg=11.16ms  min=324µs  med=9.05ms max=412.79ms p(90)=12.97ms p(95)=14.86ms
      http_reqs......................: 50891   558.900252/s
      iteration_duration.............: avg=1.01s    min=1s     med=1s     max=2.28s    p(90)=1.01s   p(95)=1.01s  
      iterations.....................: 50891   558.900252/s
      vus............................: 18      min=14   max=1000
      vus_max........................: 1000    min=1000 max=1000
    ```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- redis cache 적용
  - redis docker container 구동
  - 캐시 설정

- resource cache 설정
  - css 파일에 대한 1년 max-age로 설정
  - js 파일에 대한 no-cache, private 설정
  
- nginx compression level 6 to 9
  - compress level to 9
  
- nginx micro caching
  - proxy_cache_path로 캐시 설정
  
- nginx upstream 2개로 설정
  ```js
    upstream app {
      least_conn; ## 현재 connections이 가장 적은 server로 reqeust를 분배
      server 172.17.0.1:8080 max_fails=3 fail_timeout=3s;
      server 172.17.0.1:8081 max_fails=3 fail_timeout=3s;
    }
    ...
  ```
  
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- Coding as a Hobby 와 같은 결과를 반환하세요.
  ```sql
    SELECT 
      n.count,
      m.count
    FROM(
      SELECT count(1) as count FROM subway.programmer WHERE hobby='Yes'
    ) n CROSS JOIN
    (
      SELECT count(1) as count FROM subway.programmer WHERE hobby='No'
    ) m;

  ```
  - hobby index 생성
  - 개별 카운드 후 cross join으로 생성
  
- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
  ```sql
    SELECT 
      C.id, hospital.name 
    FROM 
      subway.covid AS C
    JOIN 
      subway.hospital
    ON 
      hospital.id = C.hospital_id
    WHERE 
      C.id >= 1000; 
  ```
  - covid 테이블에 hospital_id로 인덱스 생성
  - hospital 테이블에 id PK 설정

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
  ```sql
    SELECT 
	  P.id as member_id,
	  H.name
    FROM
      subway.covid as C
    INNER JOIN
      subway.programmer as P
    ON 
      C.member_id = P.member_id
    INNER JOIN
      subway.hospital as H
    ON 
      H.id = C.hospital_id
    WHERE
      P.hobby = 'Yes'
      AND (student LIKE 'Yes%' OR years_coding = '0-2 years')
    LIMIT 0, 10;
  ```
  - join 후 where에서 필러링


- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  ```sql
    SELECT
      covid.stay as stay,
      count(member.id) as count
    FROM
      subway.covid
    INNER JOIN subway.member    
      ON covid.member_id = member.id
    INNER JOIN subway.programmer
      ON covid.member_id = programmer.member_id
    WHERE
      member.age BETWEEN 20 and 29
      AND programmer.country = 'India'
      AND covid.hospital_id = (
        SELECT id FROM subway.hospital WHERE name='서울대병원' LIMIT 1
      )
    GROUP BY stay;
  ```
  - SubQuery로 id 추출하여 WHERE 조건절로 사용

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
  ```sql
    SELECT
	  exercise,
      count(exercise) as count
    FROM
      subway.covid as C
      INNER JOIN subway.programmer AS P
      ON C.member_id = P.member_id
      INNER JOIN subway.member as M
      ON M.id = C.member_id
    WHERE
      M.age BETWEEN 30 AND 39
      AND C.hospital_id = (
        SELECT id FROM subway.hospital WHERE name='서울대병원' LIMIT 1
      )
    GROUP BY exercise
    ORDER BY null;
  ```
  - ORDER BY null로 order by 취소
  - 병원 id를 통한 id 검색

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
  - https://app.realizeme.o-r.kr/favorites
