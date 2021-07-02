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
#### 개선전
##### smoke
```
    ✓ retrieved member
    ✓ lines success!!
    ✓ get shortest line success
    ✓ logged in successfully

  ✓ checks.....................: 100.00% ✓ 36  ✗ 0  
    data_received..............: 106 kB  9.8 kB/s
    data_sent..................: 10 kB   948 B/s
    http_req_blocked...........: avg=5.32ms   min=2µs     med=3µs     max=191.66ms p(90)=6µs     p(95)=7.5µs   
    http_req_connecting........: avg=139.41µs min=0s      med=0s      max=5.01ms   p(90)=0s      p(95)=0s      
  ✓ http_req_duration..........: avg=43.96ms  min=17.48ms med=22.39ms max=173.68ms p(90)=94.7ms  p(95)=99.75ms 
    http_req_receiving.........: avg=79.94µs  min=44µs    med=73.5µs  max=165µs    p(90)=111.5µs p(95)=124.25µs
    http_req_sending...........: avg=27.83µs  min=14µs    med=26.5µs  max=76µs     p(90)=39µs    p(95)=44.25µs 
    http_req_tls_handshaking...: avg=3.84ms   min=0s      med=0s      max=138.43ms p(90)=0s      p(95)=0s      
    http_req_waiting...........: avg=43.86ms  min=17.41ms med=22.3ms  max=173.56ms p(90)=94.59ms p(95)=99.6ms  
    http_reqs..................: 36      3.323194/s
    iteration_duration.........: avg=1.19s    min=1.14s   med=1.15s   max=1.58s    p(90)=1.25s   p(95)=1.41s   
    iterations.................: 9       0.830798/s
    vus........................: 1       min=1 max=1
    vus_max....................: 2       min=2 max=2
```
##### load
```

    ✓ logged in successfully
    ✓ retrieved member
    ✓ lines success!!
    ✓ get shortest line success

  ✓ checks.....................: 100.00% ✓ 11232 ✗ 0    
    data_received..............: 33 MB   1.0 MB/s
    data_sent..................: 3.2 MB  98 kB/s
    http_req_blocked...........: avg=1.03ms   min=1µs    med=3µs      max=498.27ms p(90)=7µs      p(95)=9µs     
    http_req_connecting........: avg=323.71µs min=0s     med=0s       max=319.04ms p(90)=0s       p(95)=0s      
  ✓ http_req_duration..........: avg=184.5ms  min=9.86ms med=157.69ms max=1.2s     p(90)=378.16ms p(95)=433.13ms
    http_req_receiving.........: avg=67.03µs  min=18µs   med=62µs     max=391µs    p(90)=105µs    p(95)=120µs   
    http_req_sending...........: avg=23.83µs  min=7µs    med=20µs     max=779µs    p(90)=37µs     p(95)=46µs    
    http_req_tls_handshaking...: avg=701.38µs min=0s     med=0s       max=239.11ms p(90)=0s       p(95)=0s      
    http_req_waiting...........: avg=184.41ms min=9.79ms med=157.6ms  max=1.2s     p(90)=378.03ms p(95)=433.06ms
    http_reqs..................: 11232   345.300566/s
    iteration_duration.........: avg=1.74s    min=1.09s  med=1.66s    max=3.66s    p(90)=2.54s    p(95)=2.65s   
    iterations.................: 2808    86.325141/s
    vus........................: 74      min=10  max=299
    vus_max....................: 300     min=300 max=300
```
##### stress
```
    ✓ lines success!!
    ✓ get shortest line success
    ✓ logged in successfully
    ✓ retrieved member

  ✓ checks.....................: 100.00% ✓ 54804 ✗ 0    
    data_received..............: 157 MB  1.1 MB/s
    data_sent..................: 15 MB   108 kB/s
    http_req_blocked...........: avg=314.99µs min=1µs    med=3µs      max=1.19s    p(90)=6µs      p(95)=7µs     
    http_req_connecting........: avg=157.88µs min=0s     med=0s       max=1.17s    p(90)=0s       p(95)=0s      
  ✓ http_req_duration..........: avg=305.07ms min=9.18ms med=232.49ms max=4.25s    p(90)=615.14ms p(95)=935.97ms
    http_req_receiving.........: avg=61.39µs  min=15µs   med=55µs     max=4.87ms   p(90)=96µs     p(95)=108µs   
    http_req_sending...........: avg=21.49µs  min=5µs    med=19µs     max=8.66ms   p(90)=34µs     p(95)=41µs    
    http_req_tls_handshaking...: avg=152.03µs min=0s     med=0s       max=175.72ms p(90)=0s       p(95)=0s      
    http_req_waiting...........: avg=304.98ms min=9.13ms med=232.41ms max=4.25s    p(90)=615.04ms p(95)=935.91ms
    http_reqs..................: 54804   388.863073/s
    iteration_duration.........: avg=2.22s    min=1.09s  med=2.09s    max=8.03s    p(90)=3.42s    p(95)=4s      
    iterations.................: 13701   97.215768/s
    vus........................: 5       min=5   max=400
    vus_max....................: 400     min=400 max=400
```
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

