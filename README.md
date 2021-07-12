## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.

### 1단계 - 화면 응답 개선하기
#### 1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
##### SMOKE
```
   ✓ checks.........................: 100.00% ✓ 1120     ✗ 0
   data_received..................: 3.1 MB  103 kB/s
   data_sent......................: 148 kB  4.9 kB/s
   http_req_blocked...............: avg=466.85µs min=2.52µs  med=2.79µs  max=61.96ms  p(90)=2.99µs   p(95)=3.08µs
   http_req_connecting............: avg=21.78µs  min=0s      med=0s      max=2.68ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=20.33ms  min=3.6ms   med=7.79ms  max=630.01ms p(90)=21.72ms  p(95)=40.56ms
   { expected_response:true }...: avg=31.25ms  min=4.27ms  med=10.92ms max=630.01ms p(90)=35.59ms  p(95)=70.32ms
   http_req_failed................: 50.00%  ✓ 560      ✗ 560
   http_req_receiving.............: avg=352.92µs min=31.54µs med=66.95µs max=28.37ms  p(90)=482.18µs p(95)=1.41ms
   http_req_sending...............: avg=79.39µs  min=19.65µs med=34.84µs max=13.97ms  p(90)=100.29µs p(95)=120.92µs
   http_req_tls_handshaking.......: avg=323.94µs min=0s      med=0s      max=48.01ms  p(90)=0s       p(95)=0s
   http_req_waiting...............: avg=19.9ms   min=7.25µs  med=7.64ms  max=629.91ms p(90)=20.61ms  p(95)=39.24ms
   http_reqs......................: 1120    36.72731/s
   iteration_duration.............: avg=1.08s    min=1.02s   med=1.03s   max=2.15s    p(90)=1.08s    p(95)=1.13s
   iterations.....................: 280     9.181828/s
   vus............................: 10      min=10     max=10
   vus_max........................: 10      min=10     max=10
```
##### LOAD
```
   ✓ checks.........................: 100.00% ✓ 20600      ✗ 0
     data_received..................: 58 MB   1.9 MB/s
     data_sent......................: 2.8 MB  89 kB/s
     http_req_blocked...............: avg=5.12ms   min=1.58µs  med=2.7µs   max=540.89ms p(90)=2.89µs   p(95)=3.15µs
     http_req_connecting............: avg=320.04µs min=0s      med=0s      max=45.45ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=106.34ms min=3.37ms  med=84.18ms max=1.21s    p(90)=216.2ms  p(95)=277.11ms
       { expected_response:true }...: avg=108.89ms min=3.78ms  med=88.48ms max=1.21s    p(90)=213.81ms p(95)=275.81ms
     http_req_failed................: 50.00%  ✓ 10300      ✗ 10300
     http_req_receiving.............: avg=2.22ms   min=26.61µs med=50.69µs max=491.06ms p(90)=315.37µs p(95)=3.25ms
     http_req_sending...............: avg=1.29ms   min=16.14µs med=32.35µs max=376.43ms p(90)=100µs    p(95)=880.9µs
     http_req_tls_handshaking.......: avg=4.68ms   min=0s      med=0s      max=509.07ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=102.81ms min=0s      med=81.89ms max=1.21s    p(90)=207.62ms p(95)=267.84ms
     http_reqs......................: 20600   661.892551/s
     iteration_duration.............: avg=1.47s    min=1.02s   med=1.46s   max=2.95s    p(90)=1.86s    p(95)=2.15s
     iterations.....................: 5150    165.473138/s
     vus............................: 40      min=40       max=250
     vus_max........................: 250     min=250      max=250
```
##### STRESS
```
   ✓ checks.........................: 100.00% ✓ 99300      ✗ 0
     data_received..................: 276 MB  2.0 MB/s
     data_sent......................: 13 MB   91 kB/s
     http_req_blocked...............: avg=346.75µs min=1.84µs  med=2.71µs  max=771.8ms  p(90)=2.9µs    p(95)=3.11µs
     http_req_connecting............: avg=105.24µs min=0s      med=0s      max=304.6ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=50.58ms  min=2.93ms  med=23.4ms  max=1.06s    p(90)=134.23ms p(95)=168.5ms
       { expected_response:true }...: avg=53.95ms  min=3.28ms  med=26.87ms max=1.06s    p(90)=139.74ms p(95)=173.72ms
     http_req_failed................: 50.00%  ✓ 49650      ✗ 49650
     http_req_receiving.............: avg=953.21µs min=25.69µs med=52.27µs max=321.87ms p(90)=321.98µs p(95)=1.58ms
     http_req_sending...............: avg=613.12µs min=15.1µs  med=32.36µs max=322.31ms p(90)=99.65µs  p(95)=492.64µs
     http_req_tls_handshaking.......: avg=210.12µs min=0s      med=0s      max=445.21ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=49.01ms  min=0s      med=22.36ms max=1.06s    p(90)=130.79ms p(95)=164.51ms
     http_reqs......................: 99300   706.220687/s
     iteration_duration.............: avg=1.21s    min=1.01s   med=1.12s   max=2.41s    p(90)=1.53s    p(95)=1.63s
     iterations.....................: 24825   176.555172/s
     vus............................: 6       min=6        max=400
     vus_max........................: 400     min=400      max=400
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요 
```
이전 미션 때 학습한 gzip와 정적 자원 캐싱을 우선 적용 했습니다.

그런데 이것 만으로는 개선 효과가 크지 않은 것 같아 레디스로 WAS에 캐싱을 적용했습니다.

그 결과, 이전과 비교가 안될 정도로 성능이 급격이 개선됐습니다.

그리고 HTTP2를 웹서버에 적용을 했는데, 기대한 것과 달리 도리여 전반적으로 조금씩 성능이 저하된 것을 확인할 수 있었습니다.

이를 통해 최신 기술이 꼭 은총알이 될 수는 없구나 하는 것을 다시 한 번 느꼇고, 

HTTP2 도입은 각 시스템의 특성을 고려하여 적용 여부를 판단 해야 한다는 깨달음을 얻었습니다.
```

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

