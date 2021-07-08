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

### 적용 전

```
smoke
checks.........................: 100.00% ✓ 258      ✗ 0
     data_received..................: 95 kB   732 B/s
     data_sent......................: 70 kB   535 B/s
     http_req_blocked...............: avg=191.78µs min=4.11µs  med=7.92µs  max=47.72ms  p(90)=9.11µs  p(95)=9.41µs
     http_req_connecting............: avg=23.58µs  min=0s      med=0s      max=6.08ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=3.99ms   min=2.95ms  med=3.84ms  max=11.05ms  p(90)=4.75ms  p(95)=6.03ms
       { expected_response:true }...: avg=3.99ms   min=2.95ms  med=3.84ms  max=11.05ms  p(90)=4.75ms  p(95)=6.03ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 258
     http_req_receiving.............: avg=67.11µs  min=40.06µs med=67.69µs max=104.46µs p(90)=82.12µs p(95)=85.36µs
     http_req_sending...............: avg=27.21µs  min=12.25µs med=31.99µs max=103.21µs p(90)=42.17µs p(95)=46.34µs
     http_req_tls_handshaking.......: avg=159.09µs min=0s      med=0s      max=41.04ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=3.9ms    min=2.88ms  med=3.73ms  max=10.94ms  p(90)=4.64ms  p(95)=5.92ms
     http_reqs......................: 258     1.981076/s
     iteration_duration.............: avg=1s       min=1s      med=1s      max=1.05s    p(90)=1.01s   p(95)=1.01s
     iterations.....................: 129     0.990538/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

````
load
checks.........................: 100.00% ✓ 102548     ✗ 0
data_received..................: 38 MB   293 kB/s
data_sent......................: 28 MB   212 kB/s
http_req_blocked...............: avg=41.6µs  min=3.23µs  med=5.15µs  max=44.6ms  p(90)=6.84µs  p(95)=7.77µs
http_req_connecting............: avg=7.45µs  min=0s      med=0s      max=13.93ms p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=6.43ms  min=2.69ms  med=5.02ms  max=93.87ms p(90)=10.94ms p(95)=14.42ms
{ expected_response:true }...: avg=6.43ms  min=2.69ms  med=5.02ms  max=93.87ms p(90)=10.94ms p(95)=14.42ms
http_req_failed................: 0.00%   ✓ 0          ✗ 102548
http_req_receiving.............: avg=56.93µs min=21.45µs med=49.52µs max=31.35ms p(90)=67.61µs p(95)=81.34µs
http_req_sending...............: avg=28.24µs min=8.91µs  med=17.55µs max=16.51ms p(90)=30.04µs p(95)=41.42µs
http_req_tls_handshaking.......: avg=27.62µs min=0s      med=0s      max=31.33ms p(90)=0s      p(95)=0s
http_req_waiting...............: avg=6.35ms  min=2.6ms   med=4.94ms  max=93.81ms p(90)=10.85ms p(95)=14.28ms
http_reqs......................: 102548  783.692319/s
iteration_duration.............: avg=1.01s   min=1s      med=1.01s   max=1.14s   p(90)=1.02s   p(95)=1.02s
iterations.....................: 51274   391.846159/s
vus............................: 22      min=15       max=500
vus_max........................: 500     min=500      max=500
````

````
stress
checks.........................: 100.00% ✓ 104626     ✗ 0
data_received..................: 39 MB   299 kB/s
data_sent......................: 28 MB   216 kB/s
http_req_blocked...............: avg=38.78µs min=3.3µs   med=5.09µs  max=51.94ms  p(90)=6.75µs  p(95)=7.57µs
http_req_connecting............: avg=6.53µs  min=0s      med=0s      max=14.92ms  p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=5.82ms  min=2.6ms   med=4.58ms  max=123.77ms p(90)=9.63ms  p(95)=12.73ms
{ expected_response:true }...: avg=5.82ms  min=2.6ms   med=4.58ms  max=123.77ms p(90)=9.63ms  p(95)=12.73ms
http_req_failed................: 0.00%   ✓ 0          ✗ 104626
http_req_receiving.............: avg=55.96µs min=21.16µs med=49.54µs max=16.3ms   p(90)=67.71µs p(95)=81.7µs
http_req_sending...............: avg=25.27µs min=9.57µs  med=17.62µs max=15.33ms  p(90)=29.9µs  p(95)=41.34µs
http_req_tls_handshaking.......: avg=25.99µs min=0s      med=0s      max=31.04ms  p(90)=0s      p(95)=0s
http_req_waiting...............: avg=5.74ms  min=2.52ms  med=4.5ms   max=123.71ms p(90)=9.54ms  p(95)=12.64ms
http_reqs......................: 104626  799.576944/s
iteration_duration.............: avg=1.01s   min=1s      med=1.01s   max=1.13s    p(90)=1.02s   p(95)=1.02s
iterations.....................: 52313   399.788472/s
vus............................: 26      min=15       max=500
vus_max........................: 500     min=500      max=500
````

### 적용 후 

````
checks.........................: 100.00% ✓ 140      ✗ 0
data_received..................: 42 kB   599 B/s
data_sent......................: 28 kB   395 B/s
http_req_blocked...............: avg=387.67µs min=2.81µs  med=3.01µs  max=53.85ms  p(90)=3.19µs   p(95)=3.26µs
http_req_connecting............: avg=9.64µs   min=0s      med=0s      max=1.35ms   p(90)=0s       p(95)=0s
✓ http_req_duration..............: avg=2.87ms   min=2.2ms   med=2.91ms  max=8.3ms    p(90)=3.14ms   p(95)=3.21ms
{ expected_response:true }...: avg=2.87ms   min=2.2ms   med=2.91ms  max=8.3ms    p(90)=3.14ms   p(95)=3.21ms
http_req_failed................: 0.00%   ✓ 0        ✗ 140
http_req_receiving.............: avg=74.87µs  min=56.76µs med=73.78µs max=133.45µs p(90)=85.9µs   p(95)=90.51µs
http_req_sending...............: avg=90.31µs  min=35.51µs med=81.95µs max=258.26µs p(90)=142.18µs p(95)=148.04µs
http_req_tls_handshaking.......: avg=221.13µs min=0s      med=0s      max=30.95ms  p(90)=0s       p(95)=0s
http_req_waiting...............: avg=2.71ms   min=2.1ms   med=2.72ms  max=8.09ms   p(90)=2.93ms   p(95)=3.01ms
http_reqs......................: 140     1.984559/s
iteration_duration.............: avg=1s       min=1s      med=1s      max=1.06s    p(90)=1s       p(95)=1s
iterations.....................: 70      0.992279/s
vus............................: 1       min=1      max=1
vus_max........................: 1       min=1      max=1
````

```
load
checks.........................: 100.00% ✓ 180126     ✗ 0
data_received..................: 51 MB   137 kB/s
data_sent......................: 36 MB   96 kB/s
http_req_blocked...............: avg=22.94µs min=2.02µs  med=2.93µs  max=53.81ms p(90)=3.1µs   p(95)=3.2µs
http_req_connecting............: avg=3.98µs  min=0s      med=0s      max=16.82ms p(90)=0s      p(95)=0s
✓ http_req_duration..............: avg=3.63ms  min=1.63ms  med=3.02ms  max=54.67ms p(90)=5.48ms  p(95)=7.14ms
{ expected_response:true }...: avg=3.63ms  min=1.97ms  med=3.02ms  max=54.67ms p(90)=5.48ms  p(95)=7.14ms
http_req_failed................: 0.08%   ✓ 146        ✗ 179980
http_req_receiving.............: avg=65.43µs min=28.65µs med=58.35µs max=12.73ms p(90)=79.23µs p(95)=95.19µs
http_req_sending...............: avg=59.31µs min=21.17µs med=53.97µs max=7.22ms  p(90)=88.22µs p(95)=102.19µs
http_req_tls_handshaking.......: avg=15.17µs min=0s      med=0s      max=31.19ms p(90)=0s      p(95)=0s
http_req_waiting...............: avg=3.5ms   min=1.49ms  med=2.89ms  max=54.54ms p(90)=5.36ms  p(95)=7.02ms
http_reqs......................: 180126  485.59757/s
iteration_duration.............: avg=1s      min=1s      med=1s      max=1.07s   p(90)=1.01s   p(95)=1.01s
iterations.....................: 90063   242.798785/s
vus............................: 8       min=3        max=499
vus_max........................: 500     min=500      max=500
```



```
stress
     checks.........................: 100.00% ✓ 103006     ✗ 0
     data_received..................: 30 MB   230 kB/s
     data_sent......................: 20 MB   156 kB/s
     http_req_blocked...............: avg=37.42µs min=2.14µs  med=2.97µs  max=45.76ms p(90)=3.16µs  p(95)=3.28µs
     http_req_connecting............: avg=6.82µs  min=0s      med=0s      max=16.12ms p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=4.21ms  min=1.68ms  med=3.3ms   max=84.85ms p(90)=6.85ms  p(95)=9.15ms
       { expected_response:true }...: avg=4.21ms  min=2ms     med=3.3ms   max=84.85ms p(90)=6.85ms  p(95)=9.15ms
     http_req_failed................: 0.05%   ✓ 56         ✗ 102950
     http_req_receiving.............: avg=66.29µs min=28.49µs med=57.63µs max=10.96ms p(90)=80.11µs p(95)=101.11µs
     http_req_sending...............: avg=59.56µs min=21.25µs med=54.01µs max=6.46ms  p(90)=86.77µs p(95)=99.27µs
     http_req_tls_handshaking.......: avg=26.3µs  min=0s      med=0s      max=31.71ms p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=4.08ms  min=1.54ms  med=3.17ms  max=84.73ms p(90)=6.72ms  p(95)=9.03ms
     http_reqs......................: 103006  787.703812/s
     iteration_duration.............: avg=1s      min=1s      med=1s      max=1.09s   p(90)=1.01s   p(95)=1.01s
     iterations.....................: 51503   393.851906/s
     vus............................: 24      min=15       max=500
     vus_max........................: 500     min=500      max=500
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   크롬의 개발자 도구에서 Disable cache를 체크하고, Fast 3G 설정시 메인 페이지 로딩이 총 16.65s가 걸렸습니다.

따라서 리버스 프록시를 먼저 개선해보았습니다.
* 워크 프로세스의 설정을 auto로
* 워커 커넥션을 10240 으로
* gzip encoding 적용 (webpageTest 결과 Compress Transfer A등급 확인)
* HSTS max-age=31536000으로 설정
* 캐시 존 설정하고 200 302코드를 20분간 캐싱
* access_log를 off 로

적용 후 크롬의 같은 환경에서 메인 페이지 로딩을 6.10s로 단축 시켰습니다.

http2를 설정하였는데요. 크롬에서 프로토콜 h2 까지 확인하였습니다.

다음은 was의 부하를 줄이기 위한 작업을 했는데요.
레디스를 적용하였고, line의 생성과 업데이트, 제거 까지 @Cacheable이나 CacheEvict 등 애노테이션을 이용하여 가능하여 캐시가 계속해서 쌓이지 않을 것이라고 생각했습니다.

하지만 path의 경우에는 조회만 있어, 캐시의 생성만 가능한데요.

CacheConfig 클래스의 RedisCacheConfiguration에서 entryTtl 값을 추가하여 캐시 저장 기간을 총 7일로 설정하였습니다. 따라서 캐시는 7일의 ttl 값을 가지게 됩니다.

리버스 프록시와 was를 모두 개선 한 이후 웹 페이지 로딩 속도가 400ms로 개선되었는데요.  몇 가지 사항들만 수정했을 뿐인데 페이지 로딩 속도가 엄청나게 개선되었네요.

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

