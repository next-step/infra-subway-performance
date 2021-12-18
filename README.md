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


1) 개선 전 - 회원조회, 노선조회

* smoke test

  ✓ http_req_duration : avg=9.21ms p(95)=13.13ms
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 2m10s max duration (incl. graceful stop):
           * default: 1 looping VUs for 1m40s (gracefulStop: 30s)


running (1m40.9s), 0/1 VUs, 98 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find line successfully

     checks.........................: 100.00% ✓ 294      ✗ 0  
     data_received..................: 105 kB  1.0 kB/s
     data_sent......................: 33 kB   330 B/s
     http_req_blocked...............: avg=103.59µs min=2.75µs  med=2.93µs  max=29.56ms  p(90)=3.13µs  p(95)=3.28µs  
     http_req_connecting............: avg=1.66µs   min=0s      med=0s      max=488.77µs p(90)=0s      p(95)=0s      
   ✓ http_req_duration..............: avg=9.21ms   min=6.08ms  med=8.8ms   max=26.41ms  p(90)=11.96ms p(95)=13.13ms 
       { expected_response:true }...: avg=9.21ms   min=6.08ms  med=8.8ms   max=26.41ms  p(90)=11.96ms p(95)=13.13ms 
     http_req_failed................: 0.00%   ✓ 0        ✗ 294
     http_req_receiving.............: avg=67.73µs  min=35.23µs med=64.34µs max=125.4µs  p(90)=95.61µs p(95)=105.64µs
     http_req_sending...............: avg=89.48µs  min=54.55µs med=84.81µs max=270.7µs  p(90)=119.1µs p(95)=126.3µs 
     http_req_tls_handshaking.......: avg=55.99µs  min=0s      med=0s      max=16.46ms  p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=9.05ms   min=5.96ms  med=8.65ms  max=26.17ms  p(90)=11.74ms p(95)=12.93ms 
     http_reqs......................: 294     2.914762/s
     iteration_duration.............: avg=1.02s    min=1.02s   med=1.02s   max=1.08s    p(90)=1.03s   p(95)=1.03s   
     iterations.....................: 98      0.971587/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1


```

* load test

  ✓ http_req_duration : avg=7.23ms p(95)=16.28ms
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 30 max VUs, 2m10s max duration (incl. graceful stop):
           * default: 30 looping VUs for 1m40s (gracefulStop: 30s)


running (1m41.0s), 00/30 VUs, 2945 complete and 0 interrupted iterations
default ✓ [======================================] 30 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find line successfully

     checks.........................: 100.00% ✓ 8835      ✗ 0   
     data_received..................: 3.2 MB  31 kB/s
     data_sent......................: 1.0 MB  9.9 kB/s
     http_req_blocked...............: avg=248.37µs min=2.54µs  med=2.82µs  max=82.92ms  p(90)=3.01µs   p(95)=3.14µs  
     http_req_connecting............: avg=19.65µs  min=0s      med=0s      max=10.28ms  p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=7.23ms   min=3.35ms  med=5.03ms  max=187.66ms p(90)=9ms      p(95)=16.28ms 
       { expected_response:true }...: avg=7.23ms   min=3.35ms  med=5.03ms  max=187.66ms p(90)=9ms      p(95)=16.28ms 
     http_req_failed................: 0.00%   ✓ 0         ✗ 8835
     http_req_receiving.............: avg=69.75µs  min=22.22µs med=47.93µs max=2.64ms   p(90)=107.83µs p(95)=149.87µs
     http_req_sending...............: avg=80.08µs  min=34.24µs med=67.18µs max=1.67ms   p(90)=113.63µs p(95)=132.12µs
     http_req_tls_handshaking.......: avg=219.37µs min=0s      med=0s      max=78.28ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=7.08ms   min=3.19ms  med=4.88ms  max=187.54ms p(90)=8.84ms   p(95)=16.1ms  
     http_reqs......................: 8835    87.489407/s
     iteration_duration.............: avg=1.02s    min=1.01s   med=1.01s   max=1.36s    p(90)=1.02s    p(95)=1.04s   
     iterations.....................: 2945    29.163136/s
     vus............................: 30      min=30      max=30
     vus_max........................: 30      min=30      max=30


```
* stress test

  ✓ http_req_duration : avg=5.4ms p(95)=10.42ms
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m20s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 50s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m50.9s), 000/300 VUs, 8995 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  50s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find line successfully

     checks.........................: 100.00% ✓ 26985      ✗ 0    
     data_received..................: 11 MB   208 kB/s
     data_sent......................: 3.2 MB  62 kB/s
     http_req_blocked...............: avg=57.88µs  min=2.49µs  med=2.82µs  max=31.37ms p(90)=3.02µs   p(95)=6.47µs  
     http_req_connecting............: avg=6.46µs   min=0s      med=0s      max=14.64ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=5.4ms    min=3.17ms  med=4.47ms  max=64.07ms p(90)=7.98ms   p(95)=10.42ms 
       { expected_response:true }...: avg=5.4ms    min=3.17ms  med=4.47ms  max=64.07ms p(90)=7.98ms   p(95)=10.42ms 
     http_req_failed................: 0.00%   ✓ 0          ✗ 26985
     http_req_receiving.............: avg=127.61µs min=19.19µs med=48.61µs max=13.82ms p(90)=197.59µs p(95)=369.88µs
     http_req_sending...............: avg=108.06µs min=31.66µs med=65.85µs max=27.57ms p(90)=155.06µs p(95)=235.73µs
     http_req_tls_handshaking.......: avg=43.78µs  min=0s      med=0s      max=30.05ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=5.16ms   min=0s      med=4.29ms  max=61.3ms  p(90)=7.64ms   p(95)=9.99ms  
     http_reqs......................: 26985   529.838137/s
     iteration_duration.............: avg=1.01s    min=1.01s   med=1.01s   max=1.11s   p(90)=1.02s    p(95)=1.02s   
     iterations.....................: 8995    176.612712/s
     vus............................: 34      min=20       max=300
     vus_max........................: 300     min=300      max=300

```

2) 개선 후 - 회원 조회, 노선조회

* smoke test

  ✓ http_req_duration : avg=4.05ms p(95)=4.66ms

```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 2m10s max duration (incl. graceful stop):
           * default: 1 looping VUs for 1m40s (gracefulStop: 30s)


running (1m40.3s), 0/1 VUs, 99 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find line successfully

     checks.........................: 100.00% ✓ 297      ✗ 0  
     data_received..................: 117 kB  1.2 kB/s
     data_sent......................: 34 kB   335 B/s
     http_req_blocked...............: avg=86.92µs min=2.74µs  med=2.89µs  max=24.93ms  p(90)=3.05µs   p(95)=3.12µs  
     http_req_connecting............: avg=1.58µs  min=0s      med=0s      max=470.77µs p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=4.05ms  min=3.48ms  med=3.9ms   max=6.86ms   p(90)=4.54ms   p(95)=4.66ms  
       { expected_response:true }...: avg=4.05ms  min=3.48ms  med=3.9ms   max=6.86ms   p(90)=4.54ms   p(95)=4.66ms  
     http_req_failed................: 0.00%   ✓ 0        ✗ 297
     http_req_receiving.............: avg=64.13µs min=33.9µs  med=59.97µs max=132.82µs p(90)=96.94µs  p(95)=103.13µs
     http_req_sending...............: avg=85.86µs min=49.91µs med=77.68µs max=256.97µs p(90)=120.02µs p(95)=124.07µs
     http_req_tls_handshaking.......: avg=53.02µs min=0s      med=0s      max=15.74ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=3.9ms   min=3.35ms  med=3.77ms  max=6.72ms   p(90)=4.35ms   p(95)=4.44ms  
     http_reqs......................: 297     2.959674/s
     iteration_duration.............: avg=1.01s   min=1.01s   med=1.01s   max=1.03s    p(90)=1.01s    p(95)=1.01s   
     iterations.....................: 99      0.986558/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```

* load test 

  ✓ http_req_duration : avg=4.92ms p(95)=8.06ms
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 30 max VUs, 2m10s max duration (incl. graceful stop):
           * default: 30 looping VUs for 1m40s (gracefulStop: 30s)


running (1m40.7s), 00/30 VUs, 2970 complete and 0 interrupted iterations
default ✓ [======================================] 30 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find line successfully

     checks.........................: 100.00% ✓ 8910      ✗ 0   
     data_received..................: 3.5 MB  35 kB/s
     data_sent......................: 1.0 MB  10 kB/s
     http_req_blocked...............: avg=262.11µs min=2.49µs  med=2.82µs  max=95.95ms p(90)=3.03µs   p(95)=5.42µs  
     http_req_connecting............: avg=15.3µs   min=0s      med=0s      max=14.69ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=4.92ms   min=3.19ms  med=4.43ms  max=34.68ms p(90)=6.57ms   p(95)=8.06ms  
       { expected_response:true }...: avg=4.92ms   min=3.19ms  med=4.43ms  max=34.68ms p(90)=6.57ms   p(95)=8.06ms  
     http_req_failed................: 0.00%   ✓ 0         ✗ 8910
     http_req_receiving.............: avg=128.46µs min=21.55µs med=49.79µs max=12.2ms  p(90)=245.32µs p(95)=429.38µs
     http_req_sending...............: avg=101.28µs min=28.49µs med=64.89µs max=16.81ms p(90)=145.85µs p(95)=226.82µs
     http_req_tls_handshaking.......: avg=235.02µs min=0s      med=0s      max=88.89ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=4.69ms   min=0s      med=4.25ms  max=33.98ms p(90)=6.23ms   p(95)=7.52ms  
     http_reqs......................: 8910    88.47548/s
     iteration_duration.............: avg=1.01s    min=1.01s   med=1.01s   max=1.13s   p(90)=1.02s    p(95)=1.02s   
     iterations.....................: 2970    29.491827/s
     vus............................: 30      min=30      max=30
     vus_max........................: 30      min=30      max=30

```
* stress test

  ✓ http_req_duration : avg=4.81ms p(95)=7.95ms
```
 
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m20s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 50s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m51.0s), 000/300 VUs, 9006 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  50s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find line successfully

     checks.........................: 100.00% ✓ 27018      ✗ 0    
     data_received..................: 11 MB   210 kB/s
     data_sent......................: 3.2 MB  62 kB/s
     http_req_blocked...............: avg=60.63µs  min=2.51µs   med=2.83µs  max=25.16ms p(90)=3.06µs   p(95)=7.65µs  
     http_req_connecting............: avg=6.92µs   min=0s       med=0s      max=15.11ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=4.81ms   min=3.11ms   med=4.2ms   max=64.08ms p(90)=6.48ms   p(95)=7.95ms  
       { expected_response:true }...: avg=4.81ms   min=3.11ms   med=4.2ms   max=64.08ms p(90)=6.48ms   p(95)=7.95ms  
     http_req_failed................: 0.00%   ✓ 0          ✗ 27018
     http_req_receiving.............: avg=124.14µs min=20.21µs  med=50.98µs max=22.02ms p(90)=212.24µs p(95)=388.76µs
     http_req_sending...............: avg=108.44µs min=33.02µs  med=68.54µs max=6.95ms  p(90)=172.38µs p(95)=262.25µs
     http_req_tls_handshaking.......: avg=45.41µs  min=0s       med=0s      max=24.03ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=4.58ms   min=271.98µs med=4.01ms  max=56.64ms p(90)=6.15ms   p(95)=7.52ms  
     http_reqs......................: 27018   530.271261/s
     iteration_duration.............: avg=1.01s    min=1.01s    med=1.01s   max=1.1s    p(90)=1.02s    p(95)=1.02s   
     iterations.....................: 9006    176.757087/s
     vus............................: 29      min=20       max=300
     vus_max........................: 300     min=300      max=300

```

3) 개선 전 - 회원조회, 경로찾기

* smoke test
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 2m10s max duration (incl. graceful stop):
           * default: 1 looping VUs for 1m40s (gracefulStop: 30s)


running (1m40.5s), 0/1 VUs, 99 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 297      ✗ 0  
     data_received..................: 84 kB   835 B/s
     data_sent......................: 34 kB   336 B/s
     http_req_blocked...............: avg=89.49µs min=2.72µs  med=2.9µs   max=25.7ms   p(90)=3.07µs  p(95)=3.15µs  
     http_req_connecting............: avg=1.63µs  min=0s      med=0s      max=484.87µs p(90)=0s      p(95)=0s      
   ✓ http_req_duration..............: avg=4.52ms  min=3.43ms  med=4.16ms  max=15.56ms  p(90)=5.21ms  p(95)=5.4ms   
       { expected_response:true }...: avg=4.52ms  min=3.43ms  med=4.16ms  max=15.56ms  p(90)=5.21ms  p(95)=5.4ms   
     http_req_failed................: 0.00%   ✓ 0        ✗ 297
     http_req_receiving.............: avg=67.06µs min=37.47µs med=63.69µs max=145.31µs p(90)=94.88µs p(95)=102.08µs
     http_req_sending...............: avg=87.64µs min=52.36µs med=78.49µs max=433.9µs  p(90)=119.9µs p(95)=124.88µs
     http_req_tls_handshaking.......: avg=53.4µs  min=0s      med=0s      max=15.86ms  p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=4.37ms  min=3.29ms  med=4.05ms  max=15.4ms   p(90)=5.02ms  p(95)=5.2ms   
     http_reqs......................: 297     2.95559/s
     iteration_duration.............: avg=1.01s   min=1.01s   med=1.01s   max=1.04s    p(90)=1.01s   p(95)=1.01s   
     iterations.....................: 99      0.985197/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1


```

* load test
```

  scenarios: (100.00%) 1 scenario, 30 max VUs, 2m10s max duration (incl. graceful stop):
           * default: 30 looping VUs for 1m40s (gracefulStop: 30s)


running (1m41.0s), 00/30 VUs, 2947 complete and 0 interrupted iterations
default ✓ [======================================] 30 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 8841     ✗ 0   
     data_received..................: 2.5 MB  25 kB/s
     data_sent......................: 1.0 MB  9.9 kB/s
     http_req_blocked...............: avg=189.17µs min=2.52µs  med=2.82µs  max=67.97ms  p(90)=3.03µs   p(95)=3.32µs  
     http_req_connecting............: avg=12.47µs  min=0s      med=0s      max=6.66ms   p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=6.46ms   min=3.27ms  med=5.03ms  max=108.25ms p(90)=9.29ms   p(95)=13.34ms 
       { expected_response:true }...: avg=6.46ms   min=3.27ms  med=5.03ms  max=108.25ms p(90)=9.29ms   p(95)=13.34ms 
     http_req_failed................: 0.00%   ✓ 0        ✗ 8841
     http_req_receiving.............: avg=82.53µs  min=19.29µs med=47.44µs max=9.15ms   p(90)=141.95µs p(95)=219.51µs
     http_req_sending...............: avg=88.04µs  min=30.25µs med=65.62µs max=3.02ms   p(90)=125.72µs p(95)=173.95µs
     http_req_tls_handshaking.......: avg=150.14µs min=0s      med=0s      max=57.78ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=6.29ms   min=2.72ms  med=4.87ms  max=107.68ms p(90)=9.04ms   p(95)=13.15ms 
     http_reqs......................: 8841    87.55506/s
     iteration_duration.............: avg=1.02s    min=1.01s   med=1.01s   max=1.23s    p(90)=1.02s    p(95)=1.03s   
     iterations.....................: 2947    29.18502/s
     vus............................: 30      min=30     max=30
     vus_max........................: 30      min=30     max=30

```

* stress test

```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m20s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 50s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m51.0s), 000/300 VUs, 8964 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  50s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 26892      ✗ 0    
     data_received..................: 8.6 MB  168 kB/s
     data_sent......................: 3.2 MB  62 kB/s
     http_req_blocked...............: avg=57.62µs  min=2.5µs   med=2.82µs  max=22.46ms  p(90)=3.02µs   p(95)=5.51µs  
     http_req_connecting............: avg=6.68µs   min=0s      med=0s      max=15.12ms  p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=6.35ms   min=3.22ms  med=4.71ms  max=109.75ms p(90)=9.92ms   p(95)=13.91ms 
       { expected_response:true }...: avg=6.35ms   min=3.22ms  med=4.71ms  max=109.75ms p(90)=9.92ms   p(95)=13.91ms 
     http_req_failed................: 0.00%   ✓ 0          ✗ 26892
     http_req_receiving.............: avg=120.75µs min=20.82µs med=48.92µs max=23.24ms  p(90)=200.5µs  p(95)=360.66µs
     http_req_sending...............: avg=108.42µs min=32.12µs med=65.6µs  max=36.19ms  p(90)=149.31µs p(95)=228.12µs
     http_req_tls_handshaking.......: avg=43.42µs  min=0s      med=0s      max=21.21ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=6.12ms   min=0s      med=4.53ms  max=109.63ms p(90)=9.57ms   p(95)=13.42ms 
     http_reqs......................: 26892   527.43247/s
     iteration_duration.............: avg=1.02s    min=1.01s   med=1.01s   max=1.15s    p(90)=1.03s    p(95)=1.04s   
     iterations.....................: 8964    175.810823/s
     vus............................: 27      min=20       max=300
     vus_max........................: 300     min=300      max=300

```


4) 개선 후 - 회원조회, 경로찾기

* smoke test
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 1 max VUs, 2m10s max duration (incl. graceful stop):
           * default: 1 looping VUs for 1m40s (gracefulStop: 30s)


running (1m40.4s), 0/1 VUs, 99 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 297      ✗ 0  
     data_received..................: 68 kB   672 B/s
     data_sent......................: 34 kB   336 B/s
     http_req_blocked...............: avg=79.39µs min=2.71µs  med=2.91µs  max=22.68ms  p(90)=3.13µs   p(95)=3.24µs  
     http_req_connecting............: avg=1.55µs  min=0s      med=0s      max=461.52µs p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=4.08ms  min=3.6ms   med=4.01ms  max=8.04ms   p(90)=4.41ms   p(95)=4.63ms  
       { expected_response:true }...: avg=4.08ms  min=3.6ms   med=4.01ms  max=8.04ms   p(90)=4.41ms   p(95)=4.63ms  
     http_req_failed................: 0.00%   ✓ 0        ✗ 297
     http_req_receiving.............: avg=60.77µs min=32.81µs med=57.21µs max=109.14µs p(90)=81.84µs  p(95)=90.78µs 
     http_req_sending...............: avg=87.01µs min=53.42µs med=79.07µs max=332.28µs p(90)=120.38µs p(95)=125.85µs
     http_req_tls_handshaking.......: avg=52.91µs min=0s      med=0s      max=15.71ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=3.94ms  min=3.42ms  med=3.87ms  max=7.88ms   p(90)=4.27ms   p(95)=4.49ms  
     http_reqs......................: 297     2.959378/s
     iteration_duration.............: avg=1.01s   min=1.01s   med=1.01s   max=1.04s    p(90)=1.01s    p(95)=1.01s   
     iterations.....................: 99      0.986459/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```

* load test

```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 30 max VUs, 2m10s max duration (incl. graceful stop):
           * default: 30 looping VUs for 1m40s (gracefulStop: 30s)


running (1m40.9s), 00/30 VUs, 2970 complete and 0 interrupted iterations
default ✓ [======================================] 30 VUs  1m40s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 8910      ✗ 0   
     data_received..................: 2.0 MB  20 kB/s
     data_sent......................: 1.0 MB  10 kB/s
     http_req_blocked...............: avg=261.58µs min=2.52µs  med=2.83µs  max=97.89ms p(90)=3.04µs   p(95)=3.96µs  
     http_req_connecting............: avg=9.9µs    min=0s      med=0s      max=8.88ms  p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=5.53ms   min=2.8ms   med=4.53ms  max=93.74ms p(90)=8.12ms   p(95)=10.84ms 
       { expected_response:true }...: avg=5.53ms   min=2.8ms   med=4.53ms  max=93.74ms p(90)=8.12ms   p(95)=10.84ms 
     http_req_failed................: 0.00%   ✓ 0         ✗ 8910
     http_req_receiving.............: avg=110.78µs min=18.54µs med=48.27µs max=8.66ms  p(90)=192.86µs p(95)=329.88µs
     http_req_sending...............: avg=96.01µs  min=29.81µs med=65.45µs max=10.63ms p(90)=142µs    p(95)=205.96µs
     http_req_tls_handshaking.......: avg=214.8µs  min=0s      med=0s      max=86.54ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=5.32ms   min=2.26ms  med=4.35ms  max=93.58ms p(90)=7.88ms   p(95)=10.59ms 
     http_reqs......................: 8910    88.269226/s
     iteration_duration.............: avg=1.01s    min=1.01s   med=1.01s   max=1.13s   p(90)=1.02s    p(95)=1.03s   
     iterations.....................: 2970    29.423075/s
     vus............................: 30      min=30      max=30
     vus_max........................: 30      min=30      max=30

```

* stress test

```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m20s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 50s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m50.9s), 000/300 VUs, 9001 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  50s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path successfully

     checks.........................: 100.00% ✓ 27003      ✗ 0    
     data_received..................: 7.1 MB  139 kB/s
     data_sent......................: 3.2 MB  63 kB/s
     http_req_blocked...............: avg=58.61µs  min=2.49µs   med=2.8µs   max=23.86ms p(90)=3.02µs   p(95)=7.41µs 
     http_req_connecting............: avg=6.69µs   min=0s       med=0s      max=16.47ms p(90)=0s       p(95)=0s     
   ✓ http_req_duration..............: avg=5.09ms   min=2.71ms   med=4.28ms  max=86.94ms p(90)=7.4ms    p(95)=9.38ms 
       { expected_response:true }...: avg=5.09ms   min=2.71ms   med=4.28ms  max=86.94ms p(90)=7.4ms    p(95)=9.38ms 
     http_req_failed................: 0.00%   ✓ 0          ✗ 27003
     http_req_receiving.............: avg=129.11µs min=18.83µs  med=47.4µs  max=22.8ms  p(90)=225.66µs p(95)=398.7µs
     http_req_sending...............: avg=111.19µs min=31.2µs   med=65.25µs max=10.96ms p(90)=161.5µs  p(95)=256.8µs
     http_req_tls_handshaking.......: avg=43.7µs   min=0s       med=0s      max=17.38ms p(90)=0s       p(95)=0s     
     http_req_waiting...............: avg=4.85ms   min=240.78µs med=4.08ms  max=85.97ms p(90)=7.05ms   p(95)=8.88ms 
     http_reqs......................: 27003   530.786053/s
     iteration_duration.............: avg=1.01s    min=1.01s    med=1.01s   max=1.11s   p(90)=1.02s    p(95)=1.02s  
     iterations.....................: 9001    176.928684/s
     vus............................: 29      min=20       max=300
     vus_max........................: 300     min=300      max=300

```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

* nginx http2 적용
    * 성능향상이 이루어지지 않음
* redis 캐시 적용
    * 디스크에 저장하지 않고, 메모리에서 캐싱하기 때문에 평균 1.5배 ~ 2배 가량의 성능향상이 이루어짐
* 정적파일 경량화
    * nginx의 gzip 설정 및 정적 리소스 캐싱 
* Async Thread Pool 설정
    * 경로 찾기 시 비동기로 설정하였으나, 성능 면에서 크게 이점은 보지 못함
```
ubuntu@ip-192-168-201-20:~/infra-subway-performance$  cat /proc/cpuinfo | grep "model name" | uniq -c | awk '{print $5 $6, $7,$8, $9, $10 $11}'
Intel(R)Xeon(R) CPU E5-2686 v4 @2.30GHz
ubuntu@ip-192-168-201-20:~/infra-subway-performance$ cat /proc/cpuinfo | grep "cpu cores" | tail -1 | awk '{print $4}'
2
ubuntu@ip-192-168-201-20:~/infra-subway-performance$ cat /proc/cpuinfo | grep "physical id" | sort -u | wc -l
1
ubuntu@ip-192-168-201-20:~/infra-subway-performance$ grep -c processor /proc/cpuinfo
2

CPU당 물리 코어 수 : 2
물리 CPU 수 : 1
리눅스 전체 코어(프로세스)개수 : 2
적절한 스레드 수 : 4 (사용가능한 코어수의 두배)
```



---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

