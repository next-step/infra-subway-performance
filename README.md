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

load 테스트 (개선 전)

```shell
     checks.........................: 100.00% ✓ 20453 ✗ 0
     data_received..................: 16 MB  81 kB/s
     data_sent......................: 4.4 MB 23 kB/s
     http_req_blocked...............: avg=2.27ms  min=800ns  med=2.7µs  max=1.02s   p(90)=9.65ms  p(95)=13.83ms
     http_req_connecting............: avg=2.24ms  min=0s     med=0s     max=1.02s   p(90)=9.52ms  p(95)=13.74ms
   ✗ http_req_duration..............: avg=29.91ms min=2.24ms med=9.65ms max=1.25s   p(90)=80.52ms p(95)=123.82ms
       { expected_response:true }...: avg=29.92ms min=3.47ms med=9.65ms max=1.25s   p(90)=80.61ms p(95)=123.83ms
     http_req_failed................: 0.00%  ✓ 0     ✗ 20453
     http_req_receiving.............: avg=75.78µs min=0s     med=71.2µs max=6.63ms  p(90)=116µs   p(95)=132.83µs
     http_req_sending...............: avg=55.36µs min=5.7µs  med=13.6µs max=39.41ms p(90)=73.2µs  p(95)=98.43µs
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s     max=0s      p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=29.78ms min=2.22ms med=9.54ms max=1.25s   p(90)=80.17ms p(95)=123.68ms
     http_reqs......................: 20453  107.266915/s
     iteration_duration.............: avg=1.06s   min=3.12ms med=1.01s  max=3.19s   p(90)=1.17s   p(95)=1.22s
     iterations.....................: 10229  53.646569/s
     vus............................: 7      min=2   max=70
     vus_max........................: 70     min=70  max=70
```

load 테스트 (개선 후)

```shell
     checks.........................: 100.00% ✓ 21440 ✗ 0
     data_received..................: 17 MB   88 kB/s
     data_sent......................: 4.6 MB  24 kB/s
     http_req_blocked...............: avg=18.5µs  min=1µs    med=2.6µs  max=6.69ms  p(90)=4.8µs  p(95)=5.6µs
     http_req_connecting............: avg=15.08µs min=0s     med=0s     max=6.61ms  p(90)=0s     p(95)=0s
   ✓ http_req_duration..............: avg=7.83ms  min=3.44ms med=7.46ms max=48.24ms p(90)=11.3ms p(95)=12.92ms
       { expected_response:true }...: avg=7.83ms  min=3.44ms med=7.46ms max=48.24ms p(90)=11.3ms p(95)=12.92ms
     http_req_failed................: 0.00%   ✓ 0     ✗ 21440
     http_req_receiving.............: avg=73.5µs  min=11µs   med=69µs   max=988.7µs p(90)=109µs  p(95)=123.8µs
     http_req_sending...............: avg=20.31µs min=5.3µs  med=12.9µs max=8.38ms  p(90)=42.4µs p(95)=49.6µs
     http_req_tls_handshaking.......: avg=0s      min=0s     med=0s     max=0s      p(90)=0s     p(95)=0s
     http_req_waiting...............: avg=7.73ms  min=3.34ms med=7.38ms max=48.02ms p(90)=11.2ms p(95)=12.81ms
     http_reqs......................: 21440   112.718502/s
     iteration_duration.............: avg=1.01s   min=1.01s  med=1.01s  max=1.05s   p(90)=1.01s  p(95)=1.02s
     iterations.....................: 10720   56.359251/s
     vus............................: 2       min=2   max=70
     vus_max........................: 70      min=70  max=70
```

stress test (개선 전)

```shell
     checks.........................: 100.00% ✓ 188684 ✗ 0
     data_received..................: 44 MB  184 kB/s
     data_sent......................: 48 MB  171 kB/s
     http_req_blocked...............: avg=40.78ms  min=0s     med=54.5ms   max=158.76ms p(90)=82.53ms  p(95)=91.28ms
     http_req_connecting............: avg=30.07ms  min=0s     med=0s       max=158.72ms p(90)=78.78ms  p(95)=88.19ms
   ✓ http_req_duration..............: avg=585.48ms min=0s     med=413.81ms max=21.2s    p(90)=1.26s    p(95)=1.38s
       { expected_response:true }...: avg=822.29ms min=3.61ms med=884.01ms max=2.98s    p(90)=1.33s    p(95)=1.46s
     http_req_failed................: 0.00% ✓ 0  ✗ 188684
     http_req_receiving.............: avg=48.9µs   min=0s     med=38.3µs   max=26.86ms  p(90)=105.7µs  p(95)=127.7µs
     http_req_sending...............: avg=20.81ms  min=0s     med=47.4µs   max=285.9ms  p(90)=120.37ms p(95)=140.86ms
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s       max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=564.62ms min=0s     med=413.73ms max=21.2s    p(90)=1.2s     p(95)=1.36s
     http_reqs......................: 188684 661.625642/s
     iteration_duration.............: avg=1.46s    min=7.08ms med=1.38s    max=22.63s   p(90)=3.38s    p(95)=3.59s
     iterations.....................: 92753  349.862271/s
     vus............................: 1      min=1    max=700
     vus_max........................: 700    min=700  max=700
```

stress test (개선 전)

```shell
     checks.........................: 100.00% ✓ 190006 ✗ 0
     data_received..................: 45 MB   179 kB/s
     data_sent......................: 50 MB   199 kB/s
     http_req_blocked...............: avg=21.69µs  min=700ns  med=2.4µs    max=52.68ms p(90)=3.8µs    p(95)=4.5µs
     http_req_connecting............: avg=18.6µs   min=0s     med=0s       max=52.6ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=294.34ms min=5.68ms med=176.14ms max=2.64s   p(90)=789.54ms p(95)=1.08s
       { expected_response:true }...: avg=294.34ms min=5.68ms med=176.14ms max=2.64s   p(90)=789.54ms p(95)=1.08s
     http_req_failed................: 0.00%   ✓ 0      ✗ 190006
     http_req_receiving.............: avg=55.3µs   min=9.6µs  med=50.2µs   max=4.28ms  p(90)=93.2µs   p(95)=108.4µs
     http_req_sending...............: avg=17.4µs   min=5.6µs  med=13.1µs   max=9.38ms  p(90)=35.94µs  p(95)=45.5µs
     http_req_tls_handshaking.......: avg=0s       min=0s     med=0s       max=0s      p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=294.26ms min=5.61ms med=176.06ms max=2.64s   p(90)=789.45ms p(95)=1.08s
     http_reqs......................: 190006  756.930734/s
     iteration_duration.............: avg=1.58s    min=1.01s  med=1.4s     max=4.33s   p(90)=2.5s     p(95)=2.89s
     iterations.....................: 95003   378.465367/s
     vus............................: 1       min=1    max=700
     vus_max........................: 700     min=700  max=700
```

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
* nginx
  * gzip 압축 적용
  * worker 수 조정
  * http 2.0 적용
* was 성능 개선
  * 비동기 처리
  * redis 에 데이터 캐싱
  * was 인스턴스 추가하여 로드밸런싱
  * 정적 파일 경량화

처음에는 gzip 압축만으로 효과가 있었지만 부하테스트나 스트레스 테스트에서는 효과가 덜했습니다.
nginx worker 수를 조정해보고, 비동기로 처리하도록 수정하고 redis 를 적용하는 과정을 거쳤습니다.
마지막에는 was 인스턴스를 추가해서 처리량을 올렸습니다.

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

