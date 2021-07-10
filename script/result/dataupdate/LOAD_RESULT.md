# LOAD 부하 테스트

--- 

## 개선 전 계측

```shell
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 35 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 35 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.6s), 00/35 VUs, 5375 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  3m10s

     ✓ logged in successfully
     ✓ updated in  successfully

     checks.........................: 100.00% ✓ 10750     ✗ 0
     data_received..................: 3.5 MB  19 kB/s
     data_sent......................: 2.5 MB  13 kB/s
     http_req_blocked...............: avg=23.5µs  min=3.35µs  med=5.12µs  max=29.3ms   p(90)=8.2µs   p(95)=8.85µs
     http_req_connecting............: avg=2.15µs  min=0s      med=0s      max=1.56ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=6.27ms  min=4.64ms  med=6.06ms  max=23.38ms  p(90)=7.52ms  p(95)=8.11ms
       { expected_response:true }...: avg=6.27ms  min=4.64ms  med=6.06ms  max=23.38ms  p(90)=7.52ms  p(95)=8.11ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 10750
     http_req_receiving.............: avg=50.88µs min=17.12µs med=44.66µs max=615.87µs p(90)=77.59µs p(95)=84.23µs
     http_req_sending...............: avg=26.05µs min=10.86µs med=20.1µs  max=1.44ms   p(90)=40.27µs p(95)=43.39µs
     http_req_tls_handshaking.......: avg=14.9µs  min=0s      med=0s      max=28ms     p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=6.19ms  min=4.58ms  med=5.98ms  max=23.31ms  p(90)=7.43ms  p(95)=8.04ms
     http_reqs......................: 10750   56.397387/s
     iteration_duration.............: avg=1.01s   min=1.01s   med=1.01s   max=1.04s    p(90)=1.01s   p(95)=1.01s
     iterations.....................: 5375    28.198693/s
     vus............................: 2       min=1       max=35
     vus_max........................: 35      min=35      max=35
```

---

## 개선 후 계측

```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 35 max VUs, 3m40s max duration (incl. graceful stop):
           * default: Up to 35 looping VUs for 3m10s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (3m10.7s), 00/35 VUs, 5355 complete and 0 interrupted iterations
default ✓ [======================================] 00/35 VUs  3m10s

     ✓ logged in successfully
     ✓ updated in  successfully

     checks.........................: 100.00% ✓ 10710     ✗ 0
     data_received..................: 2.9 MB  15 kB/s
     data_sent......................: 1.7 MB  9.1 kB/s
     http_req_blocked...............: avg=19.94µs min=1.97µs  med=2.77µs max=29.48ms  p(90)=3.05µs   p(95)=3.2µs
     http_req_connecting............: avg=1.98µs  min=0s      med=0s     max=714.19µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=8.37ms  min=4.65ms  med=7.49ms max=86.59ms  p(90)=11.33ms  p(95)=13.32ms
       { expected_response:true }...: avg=8.37ms  min=4.65ms  med=7.49ms max=86.59ms  p(90)=11.33ms  p(95)=13.32ms
     http_req_failed................: 0.00%   ✓ 0         ✗ 10710
     http_req_receiving.............: avg=47.66µs min=13.66µs med=41.6µs max=1.2ms    p(90)=74.14µs  p(95)=81.3µs
     http_req_sending...............: avg=72.1µs  min=34.42µs med=62.9µs max=881.32µs p(90)=105.35µs p(95)=117.27µs
     http_req_tls_handshaking.......: avg=14.39µs min=0s      med=0s     max=27.96ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=8.25ms  min=4.57ms  med=7.37ms max=86.41ms  p(90)=11.21ms  p(95)=13.16ms
     http_reqs......................: 10710   56.158332/s
     iteration_duration.............: avg=1.01s   min=1.01s   med=1.01s  max=1.16s    p(90)=1.02s    p(95)=1.02s
     iterations.....................: 5355    28.079166/s
     vus............................: 2       min=1       max=35
     vus_max........................: 35      min=35      max=35
```
