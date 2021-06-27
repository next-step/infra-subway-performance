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


<details>
<summary>1 단계</summary>
<div markdown="1">

### 시나리오
* 구글 지도(코리아) MAU 를 기준 : 549만
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

**redis 접속**
* `127.0.0.1:6379> keys *`
* `1) "path::3,106"`

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
**애플리케이션만 개선**
```shell
running (3m00.0s), 00/30 VUs, 43834 complete and 0 interrupted iterations
default ✗ [======================================] 00/30 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 131502 ✗ 0
     data_received..................: 203 MB  1.1 MB/s
     data_sent......................: 15 MB   84 kB/s
     http_req_blocked...............: avg=59.75µs  min=968ns    med=2.57µs  max=350.48ms p(90)=4.03µs   p(95)=5.16µs
     http_req_connecting............: avg=4.38µs   min=0s       med=0s      max=46.63ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=30.52ms  min=690.48µs med=19.36ms max=703.81ms p(90)=71.04ms  p(95)=82.97ms
       { expected_response:true }...: avg=30.52ms  min=690.48µs med=19.36ms max=703.81ms p(90)=71.04ms  p(95)=82.97ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 131502
     http_req_receiving.............: avg=213.79µs min=10.07µs  med=37.47µs max=87.62ms  p(90)=223.3µs  p(95)=749.31µs
     http_req_sending...............: avg=70.09µs  min=5.37µs   med=12.59µs max=72.61ms  p(90)=27.78µs  p(95)=77.12µs
     http_req_tls_handshaking.......: avg=44.09µs  min=0s       med=0s      max=147.67ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=30.24ms  min=643.31µs med=19ms    max=703.71ms p(90)=70.91ms  p(95)=82.8ms
     http_reqs......................: 131502  730.547149/s
     iteration_duration.............: avg=92.9ms   min=4.22ms   med=58.04ms max=1.35s    p(90)=212.08ms p(95)=243.67ms
     iterations.....................: 43834   243.515716/s
     vus............................: 1       min=1    max=30
     vus_max........................: 30      min=30   max=30

ERRO[0181] some thresholds have failed
```

**워커 프로세스 설정**
```javascript
worker_processes auto;

events { worker_connections 10240; } ## Worker Process가 수용할 수 있는 Connection 수
```
요청을 다수의 프로세스로 나누는 것  
명시적으로 작성하면 코어 수보다도 높게 설정 가능하다 합니다.   
  
```shell
running (3m00.0s), 00/30 VUs, 49352 complete and 0 interrupted iterations
default ✗ [======================================] 00/30 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 148056 ✗ 0
     data_received..................: 229 MB  1.3 MB/s
     data_sent......................: 17 MB   95 kB/s
     http_req_blocked...............: avg=41.37µs  min=1.03µs   med=2.68µs  max=378.59ms p(90)=3.95µs   p(95)=5.2µs
     http_req_connecting............: avg=2.93µs   min=0s       med=0s      max=19.3ms   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=27.16ms  min=588.31µs med=19.63ms max=125.31ms p(90)=63.6ms   p(95)=70.73ms
       { expected_response:true }...: avg=27.16ms  min=588.31µs med=19.63ms max=125.31ms p(90)=63.6ms   p(95)=70.73ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 148056
     http_req_receiving.............: avg=164.19µs min=14.17µs  med=45.28µs max=45.23ms  p(90)=183.18µs p(95)=474.55µs
     http_req_sending...............: avg=51.28µs  min=5.2µs    med=13.05µs max=66.26ms  p(90)=29.87µs  p(95)=58.88µs
     http_req_tls_handshaking.......: avg=28.73µs  min=0s       med=0s      max=78.09ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=26.95ms  min=546.05µs med=19.34ms max=124.35ms p(90)=63.48ms  p(95)=70.61ms
     http_reqs......................: 148056  822.494154/s
     iteration_duration.............: avg=82.52ms  min=2.48ms   med=57.6ms  max=383.21ms p(90)=191.22ms p(95)=210.22ms
     iterations.....................: 49352   274.164718/s
     vus............................: 1       min=1    max=30
     vus_max........................: 30      min=30   max=30
```
이때부터 테스트가 통과되었습니다.   

**gzip 압축 설정**
```javascript
  gzip on; ## http 블록 수준에서 gzip 압축 활성화
  gzip_comp_level 9;
  gzip_vary on;
  gzip_types text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;
```
```shell
running (3m00.0s), 00/30 VUs, 47445 complete and 0 interrupted iterations
default ✗ [======================================] 00/30 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 142335 ✗ 0
     data_received..................: 223 MB  1.2 MB/s
     data_sent......................: 16 MB   91 kB/s
     http_req_blocked...............: avg=46.35µs  min=946ns    med=2.75µs  max=364.9ms  p(90)=4.16µs   p(95)=6.35µs
     http_req_connecting............: avg=4.04µs   min=0s       med=0s      max=30.37ms  p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=28.25ms  min=571.66µs med=18.75ms max=160.49ms p(90)=73.15ms  p(95)=88.68ms
       { expected_response:true }...: avg=28.25ms  min=571.66µs med=18.75ms max=160.49ms p(90)=73.15ms  p(95)=88.68ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 142335
     http_req_receiving.............: avg=185.37µs min=11.38µs  med=53.46µs max=70.63ms  p(90)=224.23µs p(95)=540.4µs
     http_req_sending...............: avg=55.07µs  min=5.1µs    med=13.46µs max=39.94ms  p(90)=35.46µs  p(95)=77.33µs
     http_req_tls_handshaking.......: avg=30.83µs  min=0s       med=0s      max=89.25ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=28.01ms  min=521.63µs med=18.45ms max=160.24ms p(90)=72.99ms  p(95)=88.55ms
     http_reqs......................: 142335  790.672458/s
     iteration_duration.............: avg=85.84ms  min=2.41ms   med=56.07ms max=439.21ms p(90)=219.38ms p(95)=267.63ms
     iterations.....................: 47445   263.557486/s
     vus............................: 1       min=1    max=30
     vus_max........................: 30      min=30   max=30

ERRO[0181] some thresholds have failed
```

**캐시 설정**
```javascript
## Proxy 캐시 파일 경로, 메모리상 점유할 크기, 캐시 유지기간, 전체 캐시의 최대 크기 등 설정
proxy_cache_path /tmp/nginx levels=1:2 keys_zone=mycache:10m inactive=10m max_size=200M;

## 캐시를 구분하기 위한 Key 규칙
proxy_cache_key "$scheme$host$request_uri $cookie_user";

## 생략 
server {
    ## 생략  
    
    ## proxy_set_header :  뒷단 서버로 전송될 헤더 값을 다시 정의해주는 지시어
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection 'upgrade';
    proxy_set_header Host $host;

    location ~* \.(?:css|js|gif|png|jpg|jpeg)$ {
      proxy_pass http://app;

      ## 캐시 설정 적용 및 헤더에 추가
      # 캐시 존을 설정 (캐시 이름)
      proxy_cache mycache;
      # X-Proxy-Cache 헤더에 HIT, MISS, BYPASS와 같은 캐시 적중 상태정보가 설정
      add_header X-Proxy-Cache $upstream_cache_status;
      # 200 302 코드는 20분간 캐싱
      proxy_cache_valid 200 302 10m;
      # 만료기간을 1 달로 설정
      expires 1M;
      # access log 를 찍지 않는다.
      access_log off;
    }

```
```shell
running (3m00.0s), 00/30 VUs, 46910 complete and 0 interrupted iterations
default ✗ [======================================] 00/30 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 140730 ✗ 0
     data_received..................: 221 MB  1.2 MB/s
     data_sent......................: 16 MB   90 kB/s
     http_req_blocked...............: avg=46.32µs  min=1.08µs   med=2.71µs  max=365.32ms p(90)=3.96µs   p(95)=5.24µs
     http_req_connecting............: avg=3.75µs   min=0s       med=0s      max=30ms     p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=28.57ms  min=594.41µs med=20.57ms max=158.88ms p(90)=67.1ms   p(95)=77.42ms
       { expected_response:true }...: avg=28.57ms  min=594.41µs med=20.57ms max=158.88ms p(90)=67.1ms   p(95)=77.42ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 140730
     http_req_receiving.............: avg=178.69µs min=15.73µs  med=46.95µs max=80.15ms  p(90)=214.96µs p(95)=561.6µs
     http_req_sending...............: avg=53.97µs  min=5.9µs    med=13.27µs max=86.68ms  p(90)=30.24µs  p(95)=60.35µs
     http_req_tls_handshaking.......: avg=32.7µs   min=0s       med=0s      max=101.36ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=28.34ms  min=532.54µs med=20.26ms max=158.82ms p(90)=67ms     p(95)=77.28ms
     http_reqs......................: 140730  781.786525/s
     iteration_duration.............: avg=86.82ms  min=2.45ms   med=60.35ms max=419.6ms  p(90)=201.76ms p(95)=231.92ms
     iterations.....................: 46910   260.595508/s
     vus............................: 1       min=1    max=30
     vus_max........................: 30      min=30   max=30
```
**로드 밸런싱 설정**
```javascript
 # Redirect all traffic to HTTPS
 # 서버 2개 띄웠습니다.   
  upstream app {
    least_conn; ## 현재 connections이 가장 적은 server로 reqeust를 분배
    server 172.17.0.1:8080 max_fails=3 fail_timeout=3s;
    server 172.17.0.1:8081 max_fails=3 fail_timeout=3s;
  }
```
```shell
running (3m00.0s), 00/30 VUs, 86378 complete and 0 interrupted iterations
default ✗ [======================================] 00/30 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 259134 ✗ 0
     data_received..................: 406 MB  2.3 MB/s
     data_sent......................: 30 MB   166 kB/s
     http_req_blocked...............: avg=61.81µs  min=1.07µs   med=2.84µs  max=386.53ms p(90)=4.2µs    p(95)=5.84µs
     http_req_connecting............: avg=11.87µs  min=0s       med=0s      max=51.29ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=14.99ms  min=549.41µs med=13.77ms max=619.19ms p(90)=28ms     p(95)=32.85ms
       { expected_response:true }...: avg=14.99ms  min=549.41µs med=13.77ms max=619.19ms p(90)=28ms     p(95)=32.85ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 259134
     http_req_receiving.............: avg=374.23µs min=12.43µs  med=37.87µs max=75.69ms  p(90)=762.23µs p(95)=1.64ms
     http_req_sending...............: avg=119.81µs min=5.25µs   med=13.7µs  max=86.33ms  p(90)=41.92µs  p(95)=346.97µs
     http_req_tls_handshaking.......: avg=33.09µs  min=0s       med=0s      max=126.3ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=14.5ms   min=503.09µs med=13.33ms max=619.06ms p(90)=27.3ms   p(95)=31.8ms
     http_reqs......................: 259134  1439.612694/s
     iteration_duration.............: avg=47.11ms  min=2.54ms   med=47.77ms max=730.28ms p(90)=77.74ms  p(95)=88.23ms
     iterations.....................: 86378   479.870898/s
     vus............................: 1       min=1    max=30
     vus_max........................: 30      min=30   max=30
```

**http2 개선 && 리버스 프록시 모든 성능 개선**
```javascript
## CPU Core에 맞는 적절한 Worker 프로세스를 할당
worker_processes auto;

events { worker_connections 10240; } ## Worker Process가 수용할 수 있는 Connection 수

http {
  gzip on; ## http 블록 수준에서 gzip 압축 활성화
  gzip_comp_level 9;
  gzip_vary on;
  gzip_types text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;

  ## Proxy 캐시 파일 경로, 메모리상 점유할 크기, 캐시 유지기간, 전체 캐시의 최대 크기 등 설정
  proxy_cache_path /tmp/nginx levels=1:2 keys_zone=mycache:10m inactive=10m max_size=200M;

  ## 캐시를 구분하기 위한 Key 규칙
  proxy_cache_key "$scheme$host$request_uri $cookie_user";


  upstream app {
    least_conn; ## 현재 connections이 가장 적은 server로 reqeust를 분배
    server 172.17.0.1:8080 max_fails=3 fail_timeout=3s;
    server 172.17.0.1:8081 max_fails=3 fail_timeout=3s;
  }

    # Redirect all traffic to HTTPS
  server {
    listen 80;
    return 301 https://$host$request_uri;
  }

  server {
    listen 443 ssl http2;

    ssl_certificate /etc/letsencrypt/live/kwj1270.ga/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/kwj1270.ga/privkey.pem;

    # Disable SSL
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;

    # 통신과정에서 사용할 암호화 알고리즘
    ssl_prefer_server_ciphers on;
    ssl_ciphers ECDH+AESGCM:ECDH+AES256:ECDH+AES128:DH+3DES:!ADH:!AECDH:!MD5;

    # Enable HSTS
    # client의 browser에게 http로 어떠한 것도 load 하지 말라고 규제합니다.
    # 이를 통해 http에서 https로 redirect 되는 request를 minimize 할 수 있습니다.
    add_header Strict-Transport-Security "max-age=31536000" always;

    # SSL sessions
    ssl_session_cache shared:SSL:10m;
    ssl_session_timeout 10m;

    ## proxy_set_header :  뒷단 서버로 전송될 헤더 값을 다시 정의해주는 지시어
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection 'upgrade';
    proxy_set_header Host $host;

    location ~* \.(?:css|js|gif|png|jpg|jpeg)$ {
      proxy_pass http://app;

      ## 캐시 설정 적용 및 헤더에 추가
      # 캐시 존을 설정 (캐시 이름)
      proxy_cache mycache;
      # X-Proxy-Cache 헤더에 HIT, MISS, BYPASS와 같은 캐시 적중 상태정보가 설정
      add_header X-Proxy-Cache $upstream_cache_status;
      # 200 302 코드는 20분간 캐싱
      proxy_cache_valid 200 302 10m;
      # 만료기간을 1 달로 설정
      expires 1M;
      # access log 를 찍지 않는다.
      access_log off;
    }

    location / {
      proxy_pass http://app;
    }
  }
}
```
```shell
running (3m00.0s), 00/30 VUs, 93718 complete and 0 interrupted iterations
default ✗ [======================================] 00/30 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 281154 ✗ 0
     data_received..................: 408 MB  2.3 MB/s
     data_sent......................: 24 MB   134 kB/s
     http_req_blocked...............: avg=35.68µs  min=217ns    med=410ns   max=369.16ms p(90)=501ns    p(95)=592ns
     http_req_connecting............: avg=8.18µs   min=0s       med=0s      max=40.75ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=13.94ms  min=645.03µs med=12.32ms max=152.03ms p(90)=26.78ms  p(95)=32.17ms
       { expected_response:true }...: avg=13.94ms  min=645.03µs med=12.32ms max=152.03ms p(90)=26.78ms  p(95)=32.17ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 281154
     http_req_receiving.............: avg=354.73µs min=18.25µs  med=60.5µs  max=139.66ms p(90)=666.18µs p(95)=1.31ms
     http_req_sending...............: avg=128.54µs min=10.31µs  med=26.66µs max=135.29ms p(90)=100.79µs p(95)=470.3µs
     http_req_tls_handshaking.......: avg=23.77µs  min=0s       med=0s      max=94.85ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=13.45ms  min=0s       med=11.86ms max=139.8ms  p(90)=26.15ms  p(95)=31.34ms
     http_reqs......................: 281154  1561.879527/s
     iteration_duration.............: avg=43.42ms  min=2.72ms   med=43.55ms max=374.08ms p(90)=71.96ms  p(95)=81.18ms
     iterations.....................: 93718   520.626509/s
     vus............................: 1       min=1    max=30
     vus_max........................: 30      min=30   max=30
```
     
**VUser 가 1명일때...**     
```shell
running (3m00.0s), 0/1 VUs, 58634 complete and 0 interrupted iterations
default ✗ [======================================] 0/1 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 175902 ✗ 0
     data_received..................: 255 MB  1.4 MB/s
     data_sent......................: 15 MB   84 kB/s
     http_req_blocked...............: avg=5.68µs   min=228ns    med=403ns    max=362.76ms p(90)=482ns   p(95)=512ns
     http_req_connecting............: avg=237ns    min=0s       med=0s       max=331.79µs p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=893.95µs min=609.45µs med=770.45µs max=12.86ms  p(90)=1.17ms  p(95)=1.2ms
       { expected_response:true }...: avg=893.95µs min=609.45µs med=770.45µs max=12.86ms  p(90)=1.17ms  p(95)=1.2ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 175902
     http_req_receiving.............: avg=60.8µs   min=28.49µs  med=59.11µs  max=8.19ms   p(90)=77.06µs p(95)=82.68µs
     http_req_sending...............: avg=23.67µs  min=15.07µs  med=21.98µs  max=5.9ms    p(90)=25.99µs p(95)=37.08µs
     http_req_tls_handshaking.......: avg=2.9µs    min=0s       med=0s       max=22.33ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=809.47µs min=513.52µs med=682.11µs max=12.75ms  p(90)=1.08ms  p(95)=1.12ms
     http_reqs......................: 175902  977.210388/s
     iteration_duration.............: avg=3.05ms   min=2.48ms   med=2.99ms   max=367.37ms p(90)=3.15ms  p(95)=3.28ms
     iterations.....................: 58634   325.736796/s
     vus............................: 1       min=1    max=1
     vus_max........................: 1       min=1    max=1
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
     http_req_duration..............: avg=535.5ms  min=0s       med=0s       max=28.03s p(90)=447.79ms p(95)=3.9s
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
```

**개선후**
```shell
running (3m00.0s), 0000/1000 VUs, 91286 complete and 0 interrupted iterations
default ✓ [======================================] 0000/1000 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 273858 ✗ 0
     data_received..................: 401 MB  2.2 MB/s
     data_sent......................: 24 MB   133 kB/s
     http_req_blocked...............: avg=3.43ms   min=189ns    med=401ns    max=2.54s    p(90)=499ns    p(95)=596ns
     http_req_connecting............: avg=1.14ms   min=0s       med=0s       max=1.11s    p(90)=0s       p(95)=0s
     http_req_duration..............: avg=235.52ms min=639.08µs med=182.07ms max=1.86s    p(90)=533.61ms p(95)=599.16ms
       { expected_response:true }...: avg=235.52ms min=639.08µs med=182.07ms max=1.86s    p(90)=533.61ms p(95)=599.16ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 273858
     http_req_receiving.............: avg=1.72ms   min=20.4µs   med=60.1µs   max=1.03s    p(90)=598.1µs  p(95)=1.41ms
     http_req_sending...............: avg=479.61µs min=9.55µs   med=27.24µs  max=848.84ms p(90)=88.12µs  p(95)=452.24µs
     http_req_tls_handshaking.......: avg=2.27ms   min=0s       med=0s       max=1.84s    p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=233.32ms min=0s       med=180.06ms max=1.86s    p(90)=530.53ms p(95)=595.08ms
     http_reqs......................: 273858  1521.402208/s
     iteration_duration.............: avg=751.99ms min=2.79ms   med=574.56ms max=4.46s    p(90)=1.68s    p(95)=1.87s
     iterations.....................: 91286   507.134069/s
     vus............................: 0       min=0    max=1000
     vus_max........................: 1000    min=1000 max=1000
```
   
Stress 테스트 같은 경우, `270 VU`부터 발생하는 `EOF 에러`가 해결되었습니다.    
`1080VU` 에서 에러가 발생하는걸 알았습니다.         
   
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

</div>
</details>

<details>
<summary>2 단계</summary>
<div markdown="1">
<h1> 2단계 - 조회 성능 개선하기 </h1>
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

> 우선 기본적으로 각각의 테이블에 (PK + UQ) 설정 했습니다.   
   
<h2> Coding as a Hobby 와 같은 결과를 반환하세요. </h2>       
             
![image](https://user-images.githubusercontent.com/50267433/123516704-6e7e8f00-d6d8-11eb-8fa6-44ee342fc3d4.png)   
  
<img width="571" alt="스크린샷 2021-06-26 오후 11 12 19" src="https://user-images.githubusercontent.com/50267433/123516681-5870ce80-d6d8-11eb-9094-ef66fae869f3.png">
   

          
```sql
SELECT (count(id) / (SELECT count(id) FROM programmer) * 100) as 'rate'
FROM programmer
GROUP BY hobby
```
1. 조회 대상인 hobby 칼럼에 인덱스 설정을 했습니다. 
2. 커버링 인덱스를 적용시켰습니다. 

<h2> 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name) </h2>     
             
<img width="1552" alt="스크린샷 2021-06-26 오후 11 27 19" src="https://user-images.githubusercontent.com/50267433/123516547-a46f4380-d6d7-11eb-9755-37be23895a05.png">     
          
<img width="557" alt="스크린샷 2021-06-26 오후 11 27 42" src="https://user-images.githubusercontent.com/50267433/123516555-a802ca80-d6d7-11eb-9b38-38a7ec844ad0.png">        
          
```sql
SELECT C.programmer_id as `프로그래머`, H.name as `병원이름`
FROM (SELECT id, hospital_id, programmer_id FROM covid) C
         JOIN (SELECT id FROM programmer) P
              ON P.id = C.programmer_id
         JOIN (SELECT id, name FROM hospital) AS H
              ON H.id = C.hospital_id;
# WHERE C.id >= 1000
# LIMIT 0, 10              
```
1. hospital name 에 대해서 UQ 설정 및 인덱스 설정   
2. 배운점 : 실습에서는 페이징 쿼리를 적용했었네요.   
   이점 참고해야겠습니다!!  
   
<h2> 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding) </h2>  

![image](https://user-images.githubusercontent.com/50267433/123516785-d2a15300-d6d8-11eb-80b2-00c21bcea4a8.png)   
             
![image](https://user-images.githubusercontent.com/50267433/123516792-dfbe4200-d6d8-11eb-8e84-f31a4f8460bd.png)
          

```sql 
SELECT P.id as `프로그래머`, CH.name as `병원이름`
FROM (SELECT id FROM programmer
      WHERE hobby LIKE 'Y%'
        AND (student LIKE 'Y%' OR years_coding = '0-2 years')) P
         JOIN (SELECT programmer_id, name
               FROM (SELECT programmer_id, hospital_id FROM covid) AS C
               JOIN (SELECT id, name FROM hospital) as H
               ON C.hospital_id = H.id) CH
         ON P.id = CH.programmer_id;
# WHERE P.id >= 1000
# LIMIT 0, 10         
```
1. hobby 칼럼에 인덱스 설정      
2. 배운점 : 여기서도 페이징 쿼리를 적용했었네요.   
   이점 참고해야겠습니다!!

<h2> 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay) </h2>
            
<img width="1552" alt="스크린샷 2021-06-26 오후 11 21 10" src="https://user-images.githubusercontent.com/50267433/123516830-1bf1a280-d6d9-11eb-92c8-37fc013ae594.png">
     
<img width="704" alt="스크린샷 2021-06-26 오후 11 21 33" src="https://user-images.githubusercontent.com/50267433/123516840-2b70eb80-d6d9-11eb-8ead-3fc7d46c3fe9.png">       
          
```sql
SELECT C.stay as `기간`, count(C.member_id) as `사람 수`
FROM (SELECT member_id, hospital_id, programmer_id, stay FROM covid) C
         JOIN (SELECT id FROM hospital WHERE name = '서울대병원') H
              ON H.id = C.hospital_id
         JOIN (SELECT id FROM member WHERE age BETWEEN 20 AND 29) M
              ON M.id = C.member_id
         JOIN (SELECT id FROM subway.programmer  WHERE country = 'India') P
              ON C.programmer_id = P.id
GROUP BY `기간`;
```

1. 실습과는 다르게 `country`에 대해서 `INDEX`를 설정하면 오히려 속도가 느려졌습니다.    
2. 각각에 테이블에 조건절을 넣어서 필요 데이터만 가져왔습니다.      
   
<h2> 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise) </h2>   
 
<img width="1552" alt="스크린샷 2021-06-26 오후 11 22 32" src="https://user-images.githubusercontent.com/50267433/123516890-62df9800-d6d9-11eb-911e-2e722a372745.png">
               
<img width="704" alt="스크린샷 2021-06-26 오후 11 22 45" src="https://user-images.githubusercontent.com/50267433/123516894-67a44c00-d6d9-11eb-88f5-c6b1d6dbb273.png">  
          
    
          
```sql
SELECT exercise, COUNT(P.id)
FROM (SELECT member_id, hospital_id, programmer_id FROM subway.covid) C
         JOIN (SELECT id FROM subway.hospital WHERE name = '서울대병원') H
              ON H.id = C.hospital_id
         JOIN (SELECT id FROM subway.member WHERE age BETWEEN 30 AND 39) M
              ON M.id = C.member_id
         JOIN (SELECT id, exercise FROM subway.programmer) P
              ON C.programmer_id = P.id
GROUP BY exercise;
```
1. `age` 에 대해서 인덱스 설정을 했습니다.  
2. `exercise`에 대한 인덱스 설정을 했습니다. 다만 성능 향상은 미비 했습니다.

___  

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요
- [https://kwj1270.ga/favorites](https://kwj1270.ga/favorites)   
- memberId : kwj1270@naver.com

<img width="662" alt="스크린샷 2021-06-26 오후 3 19 51" src="https://user-images.githubusercontent.com/50267433/123516915-886ca180-d6d9-11eb-80a4-793ee0a92b14.png">
  
___  
          
3. 이중화 작업 후 테스트(개인적으로 진행)  

**master db**          
```sql
mysql> SHOW MASTER STATUS\G
*************************** 1. row ***************************
             File: binlog.000002
         Position: 5931647
     Binlog_Do_DB:
 Binlog_Ignore_DB:
Executed_Gtid_Set:
1 row in set (0.00 sec)          
```          

**slave db**
```sql
mysql> SHOW SLAVE STATUS\G
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 172.17.0.1
                  Master_User: replication_user
                  Master_Port: 3306
                Connect_Retry: 60
              Master_Log_File: binlog.000002
          Read_Master_Log_Pos: 5931647
               Relay_Log_File: 21fce5b39c23-relay-bin.000002
                Relay_Log_Pos: 5931285
        Relay_Master_Log_File: binlog.000002
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB:
          Replicate_Ignore_DB:
           Replicate_Do_Table:
       Replicate_Ignore_Table:
      Replicate_Wild_Do_Table:
  Replicate_Wild_Ignore_Table:
                   Last_Errno: 0
                   Last_Error:
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 5931647
              Relay_Log_Space: 5931501
              Until_Condition: None
               Until_Log_File:
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File:
           Master_SSL_CA_Path:
              Master_SSL_Cert:
            Master_SSL_Cipher:
               Master_SSL_Key:
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error:
               Last_SQL_Errno: 0
               Last_SQL_Error:
  Replicate_Ignore_Server_Ids:
             Master_Server_Id: 1
                  Master_UUID: da3c8eef-d5c2-11eb-b615-0242ac110002
             Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
           Master_Retry_Count: 86400
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
            Network_Namespace:
1 row in set, 1 warning (0.01 sec)
```
![image](https://user-images.githubusercontent.com/50267433/123517360-73910d80-d6db-11eb-93fa-8a27a061923f.png)     
                  
**부하 테스트 진행해봤습니다.**             
```shell
running (3m00.0s), 000/300 VUs, 77928 complete and 0 interrupted iterations
default ✗ [======================================] 000/300 VUs  3m0s

     ✓ main page running
     ✓ path page running
     ✓ GangNam line searching success

     checks.........................: 100.00% ✓ 233784 ✗ 0
     data_received..................: 340 MB  1.9 MB/s
     data_sent......................: 20 MB   112 kB/s
     http_req_blocked...............: avg=607.57µs min=206ns    med=406ns    max=1.39s    p(90)=512ns    p(95)=599ns
     http_req_connecting............: avg=201.3µs  min=0s       med=0s       max=684.35ms p(90)=0s       p(95)=0s
   ✗ http_req_duration..............: avg=165.09ms min=662.81µs med=164.07ms max=1.01s    p(90)=282.18ms p(95)=320.77ms
       { expected_response:true }...: avg=165.09ms min=662.81µs med=164.07ms max=1.01s    p(90)=282.18ms p(95)=320.77ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 233784
     http_req_receiving.............: avg=1.94ms   min=19.93µs  med=61.83µs  max=598.31ms p(90)=1.17ms   p(95)=2.3ms
     http_req_sending...............: avg=346.13µs min=11.79µs  med=27.24µs  max=517.88ms p(90)=77.2µs   p(95)=577.43µs
     http_req_tls_handshaking.......: avg=396.6µs  min=0s       med=0s       max=1.1s     p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=162.8ms  min=0s       med=162.65ms max=794.97ms p(90)=278.14ms p(95)=313.87ms
     http_reqs......................: 233784  1298.7783/s
     iteration_duration.............: avg=520.46ms min=2.88ms   med=543.47ms max=1.97s    p(90)=833.16ms p(95)=926.47ms
     iterations.....................: 77928   432.9261/s
     vus............................: 1       min=1    max=300
     vus_max........................: 300     min=300  max=300
```
`http_req_duration`이 다소 증가하여 100ms 레이턴시는 실패하는 것을 알 수 있었습니다.   
원래 이중화를 하면 시간이 더 길어지는 건가요..?          
          
          
</div>
</details>




