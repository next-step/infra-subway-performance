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

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요


---

1단계 - 화면 응답 개선하기

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
 - 기존 성능은 아래와 같습니다.



힌트에서 알려주는 할 수 있는 행위들

1. 정적 파일 경량화
   
2. Reverse Proxy 개선하기
   
3. WAS 성능 개선하기

4. 비동기 처리하기
   
5. HTTP 캐싱 적용하기

> cheatsheet
> 
> 공인 IP 확인 >  curl bot.whatismyipaddress.com
> 
> find ./* -name "*.jar"
>
> java -jar ./build/libs/subway-0.0.1-SNAPSHOT.jar
>
> k6 run smoke.js


**기존 요청 시간**

```
$ k6 run script/Connectionfrequency/load.js
[ 생략 ] 
     checks.........................: 100.00% ✓ 57198      ✗ 0
     data_received..................: 13 MB   128 kB/s
     data_sent......................: 13 MB   128 kB/s
     http_req_blocked...............: avg=230.47µs min=2.93µs   med=4.45µs  max=268.95ms p(90)=7.04µs   p(95)=11.93µs
     http_req_connecting............: avg=199.9µs  min=0s       med=0s      max=79.37ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=23.82ms  min=832.48µs med=2.33ms  max=1.95s    p(90)=21.52ms  p(95)=88.21ms
       { expected_response:true }...: avg=23.82ms  min=832.48µs med=2.33ms  max=1.95s    p(90)=21.52ms  p(95)=88.21ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 57198
     http_req_receiving.............: avg=691.83µs min=16.2µs   med=48.39µs max=443.43ms p(90)=226.68µs p(95)=971.68µs
     http_req_sending...............: avg=248.31µs min=8.22µs   med=15.38µs max=316.11ms p(90)=42.12µs  p(95)=157.31µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=22.88ms  min=751.39µs med=2.14ms  max=1.95s    p(90)=19.28ms  p(95)=83.7ms
     http_reqs......................: 57198   566.291892/s
     iteration_duration.............: avg=1.05s    min=1s       med=1s      max=3.53s    p(90)=1.05s    p(95)=1.22s
     iterations.....................: 28599   283.145946/s
     vus............................: 0       min=0        max=300
     vus_max........................: 300
```

```
k6 run script/Connectionfrequency/smoke.js
[생략]
     checks.........................: 100.00% ✓ 200      ✗ 0
     data_received..................: 45 kB   450 B/s
     data_sent......................: 45 kB   450 B/s
     http_req_blocked...............: avg=11.53µs min=3.95µs   med=6.57µs  max=575.63µs p(90)=7.88µs   p(95)=8.24µs
     http_req_connecting............: avg=4.78µs  min=0s       med=0s      max=506.72µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=1.31ms  min=981.6µs  med=1.37ms  max=4.78ms   p(90)=1.52ms   p(95)=1.57ms
       { expected_response:true }...: avg=1.31ms  min=981.6µs  med=1.37ms  max=4.78ms   p(90)=1.52ms   p(95)=1.57ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 200
     http_req_receiving.............: avg=75.97µs min=41.33µs  med=73.22µs max=144.22µs p(90)=100.96µs p(95)=118.94µs
     http_req_sending...............: avg=24.16µs min=11.8µs   med=26.28µs max=72.96µs  p(90)=37.48µs  p(95)=40.55µs
     http_req_tls_handshaking.......: avg=0s      min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=1.21ms  min=915.78µs med=1.26ms  max=4.64ms   p(90)=1.4ms    p(95)=1.44ms
     http_reqs......................: 200     1.992378/s
     iteration_duration.............: avg=1s      min=1s       med=1s      max=1s       p(90)=1s       p(95)=1s
     iterations.....................: 100     0.996189/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

```
k6 run script/Connectionfrequency/stress.js

     checks.........................: 82.51% ✓ 87250       ✗ 18492
     data_received..................: 20 MB  224 kB/s
     data_sent......................: 20 MB  224 kB/s
     http_req_blocked...............: avg=387.24µs min=0s       med=4.17µs  max=373.32ms p(90)=6.53µs   p(95)=17.97µs
     http_req_connecting............: avg=332.02µs min=0s       med=0s      max=373.25ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=51.4ms   min=0s       med=3.48ms  max=783.48ms p(90)=192.9ms  p(95)=265.9ms
       { expected_response:true }...: avg=62.3ms   min=784.71µs med=8.18ms  max=783.48ms p(90)=212.94ms p(95)=285.52ms
     http_req_failed................: 17.48% ✓ 18492       ✗ 87250
     http_req_receiving.............: avg=734.72µs min=0s       med=31.91µs max=272.07ms p(90)=117.32µs p(95)=376.46µs
     http_req_sending...............: avg=2ms      min=0s       med=13.76µs max=551.57ms p(90)=102.81µs p(95)=1.29ms
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s      max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=48.66ms  min=0s       med=3.2ms   max=783.39ms p(90)=184.37ms p(95)=254.47ms
     http_reqs......................: 105742 1202.085852/s
     iteration_duration.............: avg=819.01ms min=155.61µs med=1s      max=2.43s    p(90)=1.41s    p(95)=1.61s
     iterations.....................: 62117  706.152398/s
     vus............................: 57     min=50        max=1100
     vus_max........................: 1100   min=1100      max=1100
```



