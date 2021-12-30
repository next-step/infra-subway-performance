## 부하테스트

### 페이징 API 테스트
- End-Point : /members
- 1차 결과
```shell

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 1000 max VUs, 1m30s max duration (incl. graceful stop):
           * default: Up to 1000 looping VUs for 1m0s over 1 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m09.9s), 0000/1000 VUs, 6627 complete and 0 interrupted iterations
default ↓ [======================================] 0999/1000 VUs  1m0s

     ✓ lending page

     checks.........................: 100.00% ✓ 6627      ✗ 0
     data_received..................: 14 MB   193 kB/s
     data_sent......................: 1.0 MB  15 kB/s
     http_req_blocked...............: avg=171.48µs min=4.2µs   med=7.54µs  max=48.92ms p(90)=725.78µs p(95)=1.05ms
     http_req_connecting............: avg=138µs    min=0s      med=0s      max=21.49ms p(90)=613.2µs  p(95)=935.75µs
   ✗ http_req_duration..............: avg=4.27s    min=28.72ms med=4.04s   max=17.14s  p(90)=8.51s    p(95)=9.4s
       { expected_response:true }...: avg=4.27s    min=28.72ms med=4.04s   max=17.14s  p(90)=8.51s    p(95)=9.4s
     http_req_failed................: 0.00%   ✓ 0         ✗ 6627
     http_req_receiving.............: avg=204.83µs min=27.41µs med=93.6µs  max=11.66ms p(90)=373.39µs p(95)=746.9µs
     http_req_sending...............: avg=41µs     min=10.95µs med=22.98µs max=5.29ms  p(90)=83.87µs  p(95)=101.61µs
     http_req_tls_handshaking.......: avg=0s       min=0s      med=0s      max=0s      p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=4.27s    min=28.59ms med=4.04s   max=17.14s  p(90)=8.51s    p(95)=9.4s
     http_reqs......................: 6627    94.777235/s
     iteration_duration.............: avg=5.27s    min=1.02s   med=5.04s   max=18.15s  p(90)=9.51s    p(95)=10.4s
     iterations.....................: 6627    94.777235/s
     vus............................: 97      min=17      max=999
     vus_max........................: 1000    min=1000    max=1000
```

- 2차 쿼리 테스트
- NoOffset
  - Duration : `0.0023`
  - 사용 쿼리
    ```sql
    SELECT * FROM subway.member WHERE id >= 20000 LIMIT 10;
    ```
- offset
    - Duration : `0.0060`
    - 사용 쿼리
      ```sql
      SELECT * FROM subway.member LIMIT 10000, 10;
      ```
- 비교

    | |NoOffset|offset|비고|
    |------|---|---|---|
    |Duration|0.0023|0.0066|쿼리 실행시간 약 3배 차이