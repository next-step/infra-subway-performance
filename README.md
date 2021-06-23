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

### 시나리오
* 구글 지도(코리아) DAU 를 기준 : 549만
* 1일 사용자 수 : 183_000 (5_490_000 / 30)
* 1명당 1일 평균 접속수 : 10회(가정)
* 1일 총 접속 수 : 1_830_000 (183_000 * 10)
* 1일 평균 rps : 약 20rps (28.2 <- 1_830_000 / 86400)
* 1일 최대 rps : 약 200rps
* throughput : 100 rps 정도

지하철 노선도를 이용하여 경로 검색 서비스를 제공해주고자 한다.             
대상은 주로, 등교하는 학생, 출근하는 직장인, 여행온 여행객이다.          
대부분의 사람들은 아침시간과 퇴근 시간에 맞추어 해당 서비스에 접속을 한다.             
이 과정에서 경로를 한번만 검색하는 경우가 없으므로 10번 정도 검색을 한다 가정한다.   
그리고 새벽시간에는 전철이 운행을 하지 않으므로 트래픽이 없다고 가정한다.

유저 시나리오로는,
1. 유저가 메인 페이지 접속
2. 메인페이지에서 경로 검색 페이지로 이동
3. 용산 -> 강남 출근길 회사 경로 탐색

[k6 rps 계산 공식 방법](https://k6.io/blog/ref-how-to-generate-a-constant-request-rate-in-k6/)

* throughput : 100rps 목표
* R : 스크립트내 요청 수
   * 필자는, 한 유저당 요청 3개 함 -> (메인, 검색 사이트 이동, 검색)
* T : `R * http_req_duration`
   * 필자는, 1 <- (3 * 0.02{smoke시, 21.93ms}) + 1;
* VU : `(throughput * T) / R`
   * 필자는, `33rps` <- `(100 * 1) / 3`
   * 편의를 위해서 `30rps`로 선정

### 1단계 - 화면 응답 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

#### Smoke
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
   vus: 1, // 1 user looping for 1 minute
   duration: '1m',

   thresholds: {
      http_req_duration: ['p(99)<100'],
   },

};

const BASE_URL = 'https://kwj1270.ga';

export default () => {
   // scenario
   // user into the service
   // user move to /path
   // user search office
   
   // main page
   let mainUrl = `${BASE_URL}`;
   let mainPageResponse = http.get(mainUrl);
   check(mainPageResponse, {
      'main page running': (response) => response.status === 200
   });

   // move search path page
   let pathUrl = `${BASE_URL}/path`;
   let pathPageResponse = http.get(pathUrl);
   check(pathPageResponse, {
      'path page running': (response) => response.status === 200
   });

   // GangNam search line
   let GangNamSearchLineUrl = `${BASE_URL}/paths/?source=3&target=106`;
   let GangNamSearchLineResponse = http.get(GangNamSearchLineUrl);
   check(GangNamSearchLineResponse, {
      'GangNam line searching success': (response) => response.status === 200
   });

};
```
**실행 결과**
```shell
running (1m00.0s), 0/1 VUs, 901 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 2703 ✗ 0
     data_received..................: 3.6 MB  59 kB/s
     data_sent......................: 312 kB  5.2 kB/s
     http_req_blocked...............: avg=139.04µs min=1.5µs    med=2.53µs   max=362.09ms p(90)=3.95µs   p(95)=4.43µs
     http_req_connecting............: avg=290ns    min=0s       med=0s       max=305.06µs p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=21.93ms  min=653.75µs med=923.11µs max=162.59ms p(90)=63.84ms  p(95)=65.31ms
       { expected_response:true }...: avg=21.93ms  min=653.75µs med=923.11µs max=162.59ms p(90)=63.84ms  p(95)=65.31ms
     http_req_failed................: 0.00%   ✓ 0    ✗ 2703
     http_req_receiving.............: avg=80.35µs  min=35.83µs  med=74.35µs  max=1.69ms   p(90)=110.69µs p(95)=120.98µs
     http_req_sending...............: avg=17.71µs  min=7.73µs   med=18.32µs  max=466.04µs p(90)=22.74µs  p(95)=23.95µs
     http_req_tls_handshaking.......: avg=10.16µs  min=0s       med=0s       max=21.99ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=21.83ms  min=574.71µs med=830.76µs max=162.45ms p(90)=63.73ms  p(95)=65.2ms
     http_reqs......................: 2703    45.036389/s
     iteration_duration.............: avg=66.59ms  min=62.78ms  med=64.97ms  max=433.51ms p(90)=68.58ms  p(95)=70.62ms
     iterations.....................: 901     15.01213/s
     vus............................: 1       min=1  max=1
     vus_max........................: 1       min=1  max=1
```
#### Load
```javascript
import http from 'k6/http';
import {check} from 'k6';

export let options = {
   stages: [
      { duration: '45s', target: 30 },
      { duration: '1m30s', target: 30 },
      { duration: '45s', target: 0 },
   ],
   thresholds: {
      http_req_duration: ['p(99)<100'],
   },

};

const BASE_URL = 'https://kwj1270.ga';

export default () => {
   // scenario
   // user into the service
   // user move to /path
   // user search office

   // main page
   let mainUrl = `${BASE_URL}`;
   let mainPageResponse = http.get(mainUrl);
   check(mainPageResponse, {
      'main page running': (response) => response.status === 200
   });

   // move search path page
   let pathUrl = `${BASE_URL}/path`;
   let pathPageResponse = http.get(pathUrl);
   check(pathPageResponse, {
      'path page running': (response) => response.status === 200
   });

   // GangNam search line
   let GangNamSearchLineUrl = `${BASE_URL}/paths/?source=3&target=106`;
   let GangNamSearchLineResponse = http.get(GangNamSearchLineUrl);
   check(GangNamSearchLineResponse, {
      'GangNam line searching success': (response) => response.status === 200
   });
};
```
**개선전**
```shell
running (3m00.0s), 00/30 VUs, 4433 complete and 0 interrupted iterations
default ✓ [======================================] 00/30 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 13299 ✗ 0
     data_received..................: 18 MB   98 kB/s
     data_sent......................: 1.5 MB  8.6 kB/s
     http_req_blocked...............: avg=41.37µs  min=1.38µs   med=3.27µs  max=353.17ms p(90)=4.75µs   p(95)=5.26µs
     http_req_connecting............: avg=862ns    min=0s       med=0s      max=2.14ms   p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=306.64ms min=662.25µs med=2.1ms   max=2.17s    p(90)=1.11s    p(95)=1.19s
       { expected_response:true }...: avg=306.64ms min=662.25µs med=2.1ms   max=2.17s    p(90)=1.11s    p(95)=1.19s
     http_req_failed................: 0.00%   ✓ 0     ✗ 13299
     http_req_receiving.............: avg=89.67µs  min=19.88µs  med=71.61µs max=12.7ms   p(90)=112.92µs p(95)=136.4µs
     http_req_sending...............: avg=18.52µs  min=6.65µs   med=15.83µs max=3.78ms   p(90)=21.78µs  p(95)=26.29µs
     http_req_tls_handshaking.......: avg=11.73µs  min=0s       med=0s      max=23.12ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=306.53ms min=607.48µs med=1.99ms  max=2.17s    p(90)=1.11s    p(95)=1.19s
     http_reqs......................: 13299   73.868435/s
     iteration_duration.............: avg=920.53ms min=63.54ms  med=1.03s   max=2.18s    p(90)=1.23s    p(95)=1.3s
     iterations.....................: 4433    24.622812/s
     vus............................: 1       min=1   max=30
     vus_max........................: 30      min=30  max=30

ERRO[0181] some thresholds have failed
```
**개선후**
```shell

```

#### Stress
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
   stages: [
      { duration: '20s', target: 10 },
      { duration: '20s', target: 100 },
      { duration: '20s', target: 200 },
      { duration: '20s', target: 300 },
      { duration: '20s', target: 500 },
      { duration: '20s', target: 800 },
      { duration: '20s', target: 1000 },
      { duration: '40s', target: 0 },
   ],
   thresholds: {
      http_req_duration: ['p(99)<100'],
   },

};

const BASE_URL = 'https://kwj1270.ga';

export default () => {
   // scenario
   // user into the service
   // user move to /path
   // user search office

   // main page
   let mainUrl = `${BASE_URL}`;
   let mainPageResponse = http.get(mainUrl);
   check(mainPageResponse, {
      'main page running': (response) => response.status === 200
   });

   // move search path page
   let pathUrl = `${BASE_URL}/path`;
   let pathPageResponse = http.get(pathUrl);
   check(pathPageResponse, {
      'path page running': (response) => response.status === 200
   });


   // GangNam search line
   let GangNamSearchLineUrl = `${BASE_URL}/paths/?source=3&target=106`;
   let GangNamSearchLineResponse = http.get(GangNamSearchLineUrl);
   check(GangNamSearchLineResponse, {
      'GangNam line searching success': (response) => response.status === 200
   });

};
```

**개선전**
```shell
running (3m03.9s), 0000/1000 VUs, 17850 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1000 VUs  3m0s

     ✗ main page running
      ↳  21% — ✓ 3820 / ✗ 14030
     ✗ path page running
      ↳  22% — ✓ 3934 / ✗ 13916
     ✗ GangNam line searching success
      ↳  21% — ✓ 3903 / ✗ 13947

     checks.........................: 21.76% ✓ 11657  ✗ 41893
     data_received..................: 83 MB  451 kB/s
     data_sent......................: 16 MB  87 kB/s
     http_req_blocked...............: avg=183.89ms min=0s       med=0s       max=2.78s  p(90)=758.32ms p(95)=1.05s
     http_req_connecting............: avg=144.41ms min=0s       med=117ms    max=1.8s   p(90)=332.09ms p(95)=394.3ms
   ✗ http_req_duration..............: avg=535.5ms  min=0s       med=0s       max=28.03s p(90)=447.79ms p(95)=3.9s
       { expected_response:true }...: avg=2.39s    min=681.17µs med=287.92ms max=28.03s p(90)=9.68s    p(95)=11.65s
     http_req_failed................: 78.23% ✓ 41893  ✗ 11657
     http_req_receiving.............: avg=4.61ms   min=0s       med=0s       max=1.22s  p(90)=90.07µs  p(95)=8.58ms
     http_req_sending...............: avg=11.41ms  min=0s       med=0s       max=1.49s  p(90)=29.3ms   p(95)=71.43ms
     http_req_tls_handshaking.......: avg=141.43ms min=0s       med=0s       max=2.3s   p(90)=594.68ms p(95)=793.34ms
     http_req_waiting...............: avg=519.47ms min=0s       med=0s       max=28.02s p(90)=372.18ms p(95)=3.9s
     http_reqs......................: 53550  291.222597/s
     iteration_duration.............: avg=4s       min=2.76ms   med=2.94s    max=32.83s p(90)=9.84s    p(95)=12.82s
     iterations.....................: 17850  97.074199/s
     vus............................: 30     min=1    max=1000
     vus_max........................: 1000   min=1000 max=1000

ERRO[0186] some thresholds have failed
```
여기서 알 수 있었던 점은, 270 정도부터 `EOF`가 발생하는 것을 알았습니다.

**개선후**
```shell

```


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   * EC2 코어 갯수에 맞게 ThreadPool 설정을 입력했습니다,
   * 캐시 컨트롤을 이용해서 css: max-age, js:no-cache, private 설정을 했습니다.
   * 리버스 프록시를 개선했습니다.
      * http2 를 사용하도록 했습니다 -> http2 를 개선만해도 꽤나 좋아지더라고요
      * 리버스 프록시에서 정적 리소스 압축
      * 리퀘스트 분배
   * 애플리케이션에서 redis르 이용한 캐시를 적용했습니다.
      * Line 과 Map 이 주요 접근 지점이라 판단하여 캐시를 적용했습니다.
      * Cache를 로컬로 적용시켜서 테스트하면 에러가 나서 `prod`에만 적용했습니다.
      * `@Async`는 적용하면 테스트코드가 에러가 터져서... 적용안했습니다.
   * index.html 에도 지연 로딩을 적용시켰습니다.

—

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요