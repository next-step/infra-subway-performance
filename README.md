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
* 1일 평균 rps : 약 30rps (28.2 <- 1_830_000 / 64800 (86,400 - 새벽시간 6시간 제외(21600)))   
* 1일 최대 rps : 약 300rps      
                  
지하철 노선도를 이용하여 경로 검색 서비스를 제공해주고자 한다.             
대상은 주로, 등교하는 학생, 출근하는 직장인, 여행온 여행객이다.          
대부분의 사람들은 아침시간과 퇴근 시간에 맞추어 해당 서비스에 접속을 한다.             
이 과정에서 경로를 한번만 검색하는 경우가 없으므로 10번 정도 검색을 한다 가정한다.   
그리고 새벽시간에는 전철이 운행을 하지 않으므로 트래픽이 없다고 가정한다.       
   
### 1단계 - 화면 응답 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
 
#### Smoke
```javascript

```
**실행 결과**
```shell

```
#### Load
```javascript
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';


export let options = {
    stages: [
        { duration: '1m', target: 100 }, // simulate ramp-up of traffic from 1 to 100 users over 1 minutes.
        { duration: '2m', target: 100 }, // stay at 100 users for 2 minutes
        { duration: '10s', target: 0 }, // ramp-down to 0 users
    ],
};

const BASE_URL = 'https://kwj1270.ga';

export default () => {

    // main page
    let mainUrl = `${BASE_URL}`;
    let mainPageResponse = http.get(mainUrl);
    check(mainPageResponse, {
        'main page running': (response) => response.status === 200
    });

    // GangNam search line
    let GangNamSearchLineUrl = `${BASE_URL}/paths/?source=3&target=106`;
    let GangNamSearchLineResponse = http.get(GangNamSearchLineUrl);
    check(GangNamSearchLineResponse, {
        'GangNam line searching success': (response) => response.status === 200
    });

    // Gasan search line
    let GasanSearchLineUrl = `${BASE_URL}/paths/?source=3&target=24`;
    let GasanSearchLineResponse = http.get(GasanSearchLineUrl);
    check(GasanSearchLineResponse, {
        'Gasan line searching success': (response) => response.status === 200
    });

    // YangJae search line
    let YangJaeSearchLineUrl = `${BASE_URL}/paths/?source=3&target=150`;
    let YangJaeSearchLineResponse = http.get(YangJaeSearchLineUrl);
    check(YangJaeSearchLineResponse, {
        'YangJae line searching success': (response) => response.status === 200
    });

};
```
**개선전**
```shell
running (3m13.5s), 000/100 VUs, 1326 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ main page running
     ✓ GangNam line searching success
     ✓ Gasan line searching success
     ✓ YangJae line searching success

     checks.........................: 100.00% ✓ 5304  ✗ 0
     data_received..................: 7.3 MB  38 kB/s
     data_sent......................: 510 kB  2.6 kB/s
     http_req_blocked...............: avg=110.8µs min=274ns    med=592ns   max=22.12ms p(90)=797ns    p(95)=933ns
     http_req_connecting............: avg=6.68µs  min=0s       med=0s      max=1.46ms  p(90)=0s       p(95)=0s
     http_req_duration..............: avg=3s      min=966.15µs med=4.28s   max=12.83s  p(90)=4.84s    p(95)=4.92s
       { expected_response:true }...: avg=3s      min=966.15µs med=4.28s   max=12.83s  p(90)=4.84s    p(95)=4.92s
     http_req_failed................: 0.00%   ✓ 0     ✗ 5304
     http_req_receiving.............: avg=87.35µs min=39.6µs   med=80.28µs max=7.72ms  p(90)=102.23µs p(95)=114.36µs
     http_req_sending...............: avg=45.2µs  min=18.88µs  med=35.19µs max=9.36ms  p(90)=45.5µs   p(95)=54.28µs
     http_req_tls_handshaking.......: avg=98.61µs min=0s       med=0s      max=21.13ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=3s      min=73.21µs  med=4.28s   max=12.83s  p(90)=4.84s    p(95)=4.92s
     http_reqs......................: 5304    27.41295/s
     iteration_duration.............: avg=12.02s  min=272.74ms med=13.83s  max=22.43s  p(90)=14.49s   p(95)=14.64s
     iterations.....................: 1326    6.853237/s
     vus............................: 14      min=2   max=100
     vus_max........................: 100     min=100 max=100
```
**개선후**
```shell
running (3m10.0s), 000/100 VUs, 57678 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  3m10s

     ✓ main page running
     ✓ GangNam line searching success
     ✓ Gasan line searching success
     ✓ YangJae line searching success

     checks.........................: 100.00% ✓ 230712 ✗ 0
     data_received..................: 298 MB  1.6 MB/s
     data_sent......................: 20 MB   104 kB/s
     http_req_blocked...............: avg=150.49µs min=194ns    med=372ns    max=367.63ms p(90)=459ns    p(95)=527ns
     http_req_connecting............: avg=38.32µs  min=0s       med=0s       max=140.61ms p(90)=0s       p(95)=0s
     http_req_duration..............: avg=66.42ms  min=630.96µs med=55.33ms  max=402.79ms p(90)=129.76ms p(95)=175.11ms
       { expected_response:true }...: avg=66.42ms  min=630.96µs med=55.33ms  max=402.79ms p(90)=129.76ms p(95)=175.11ms
     http_req_failed................: 0.00%   ✓ 0      ✗ 230712
     http_req_receiving.............: avg=741.9µs  min=20.33µs  med=57.95µs  max=225.55ms p(90)=717µs    p(95)=1.78ms
     http_req_sending...............: avg=164.11µs min=8.85µs   med=25.98µs  max=207.84ms p(90)=62.38µs  p(95)=227.43µs
     http_req_tls_handshaking.......: avg=106.51µs min=0s       med=0s       max=291.56ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=65.52ms  min=2.41µs   med=54.54ms  max=402.72ms p(90)=128.24ms p(95)=174.01ms
     http_reqs......................: 230712  1214.241103/s
     iteration_duration.............: avg=269.06ms min=4.02ms   med=239.56ms max=1.2s     p(90)=461.38ms p(95)=644.17ms
     iterations.....................: 57678   303.560276/s
     vus............................: 1       min=1    max=100
     vus_max........................: 100     min=100  max=100
```

#### Stress
```javascript
import http from 'k6/http';
import {check} from 'k6';

export let options = {
    stages: [
      { duration: '2s', target: 100 }, // below normal load
      { duration: '5s', target: 200 },
      { duration: '2s', target: 300 }, // normal load
      { duration: '5s', target: 400 },
      { duration: '1m', target: 1000 }, // sudden traffic
      { duration: '30s', target: 400 }, // around the breaking point
      { duration: '10s', target: 300 },
      { duration: '15s', target: 200 }, // beyond the breaking point
      { duration: '10s', target: 100 },
      { duration: '5s', target: 0 }, // scale down. Recovery stage.
    ],

};

const BASE_URL = 'https://kwj1270.ga';

export default () => {

    // main page
    let mainUrl = `${BASE_URL}`;
    let mainPageResponse = http.get(mainUrl);
    check(mainPageResponse, {
        'main page running': (response) => response.status === 200
    });

    // GangNam search line
    let GangNamSearchLineUrl = `${BASE_URL}/paths/?source=3&target=106`;
    let GangNamSearchLineResponse = http.get(GangNamSearchLineUrl);
    check(GangNamSearchLineResponse, {
        'GangNam line searching success': (response) => response.status === 200
    });

    // Gasan search line
    let GasanSearchLineUrl = `${BASE_URL}/paths/?source=3&target=24`;
    let GasanSearchLineResponse = http.get(GasanSearchLineUrl);
    check(GasanSearchLineResponse, {
        'Gasan line searching success': (response) => response.status === 200
    });

    // YangJae search line
    let YangJaeSearchLineUrl = `${BASE_URL}/paths/?source=3&target=150`;
    let YangJaeSearchLineResponse = http.get(YangJaeSearchLineUrl);
    check(YangJaeSearchLineResponse, {
        'YangJae line searching success': (response) => response.status === 200
    });

};
```
**개선전**
```shell
running (2m54.0s), 0000/1000 VUs, 672 complete and 829 interrupted iterations
default ↓ [======================================] 0294/1000 VUs  2m24s

     ✓ main page running
     ✓ GangNam line searching success
     ✓ Gasan line searching success
     ✓ YangJae line searching success

     checks.........................: 100.00% ✓ 4304   ✗ 0
     data_received..................: 10 MB   58 kB/s
     data_sent......................: 1.1 MB  6.0 kB/s
     http_req_blocked...............: avg=2.21ms   min=255ns    med=592ns   max=368.71ms p(90)=5.33ms   p(95)=5.82ms
     http_req_connecting............: avg=116.77µs min=0s       med=0s      max=19.15ms  p(90)=322.09µs p(95)=390.89µs
     http_req_duration..............: avg=20.39s   min=1.21ms   med=20.64s  max=54.2s    p(90)=34.1s    p(95)=37.92s
       { expected_response:true }...: avg=20.39s   min=1.21ms   med=20.64s  max=54.2s    p(90)=34.1s    p(95)=37.92s
     http_req_failed................: 0.00%   ✓ 0      ✗ 4304
     http_req_receiving.............: avg=104.57µs min=35.97µs  med=82.96µs max=7.61ms   p(90)=113.73µs p(95)=150.5µs
     http_req_sending...............: avg=104.89µs min=18.53µs  med=37.51µs max=4.43ms   p(90)=282.64µs p(95)=415.61µs
     http_req_tls_handshaking.......: avg=1.39ms   min=0s       med=0s      max=79.77ms  p(90)=4.75ms   p(95)=5.06ms
     http_req_waiting...............: avg=20.39s   min=203.13µs med=20.64s  max=54.2s    p(90)=34.1s    p(95)=37.92s
     http_reqs......................: 4304    24.741875/s
     iteration_duration.............: avg=1m8s     min=10.1s    med=1m8s    max=2m9s     p(90)=1m48s    p(95)=1m56s
     iterations.....................: 672     3.863044/s
     vus............................: 18      min=18   max=1000
     vus_max........................: 1000    min=1000 max=1000
```
**개선후**
```shell
running (2m24.0s), 0000/1000 VUs, 33954 complete and 0 interrupted iterations
default ↓ [======================================] 0001/1000 VUs  2m24s

     ✓ main page running
     ✓ GangNam line searching success
     ✓ Gasan line searching success
     ✓ YangJae line searching success

     checks.........................: 100.00% ✓ 135816 ✗ 0
     data_received..................: 179 MB  1.2 MB/s
     data_sent......................: 12 MB   84 kB/s
     http_req_blocked...............: avg=9.73ms   min=200ns    med=388ns    max=4.44s  p(90)=491ns    p(95)=578ns
     http_req_connecting............: avg=3.16ms   min=0s       med=0s       max=1.82s  p(90)=0s       p(95)=0s
     http_req_duration..............: avg=518.35ms min=690.78µs med=460.3ms  max=23.44s p(90)=863.77ms p(95)=1.07s
       { expected_response:true }...: avg=518.35ms min=690.78µs med=460.3ms  max=23.44s p(90)=863.77ms p(95)=1.07s
     http_req_failed................: 0.00%   ✓ 0      ✗ 135816
     http_req_receiving.............: avg=4.62ms   min=21.06µs  med=64.78µs  max=1.6s   p(90)=996.51µs p(95)=2.78ms
     http_req_sending...............: avg=1.33ms   min=11.1µs   med=28.44µs  max=1.16s  p(90)=72.44µs  p(95)=373.89µs
     http_req_tls_handshaking.......: avg=6.43ms   min=0s       med=0s       max=4.28s  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=512.39ms min=0s       med=455.96ms max=23.44s p(90)=851.72ms p(95)=1.05s
     http_reqs......................: 135816  943.100584/s
     iteration_duration.............: avg=2.24s    min=5.09ms   med=2.11s    max=27.98s p(90)=4s       p(95)=4.56s
     iterations.....................: 33954   235.775146/s
     vus............................: 1       min=1    max=998
     vus_max........................: 1000    min=1000 max=1000
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