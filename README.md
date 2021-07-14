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

#### SmokeTest
##### script
```js
import http from "k6/http";
import { check, group, sleep, fail } from "k6";

export let options = {
	  vus: 1, // 1 user looping for 1 minute
	  duration: "10s",

	  thresholds: {
		      http_req_duration: ["p(99)<5500"],
		    },
};

const BASE_URL = "https://insup.kro.kr";
const USERNAME = "a@hanmail.net";
const PASSWORD = "1";

export default function () {
	  var payload = JSON.stringify({
		      email: USERNAME,
		      password: PASSWORD,
		    });

	  var params = {
		      headers: {
			            "Content-Type": "application/json",
			          },
		    };

	  let loginRes = http.post(`${BASE_URL}/login/token`, payload, params);

	  check(loginRes, {
		      "logged in successfully": (resp) => resp.json("accessToken") !== "",
		    });

	  let authHeaders = {
		      headers: {
			            Authorization: `Bearer ${loginRes.json("accessToken")}`,
			          },
		    };
	  let myObjects = http.get(`${BASE_URL}/members/me`, authHeaders).json();
	  check(myObjects, { "retrieved member": (obj) => obj.id != 0 });
	  sleep(1);

	  var pathRes = http.get(`${BASE_URL}/paths?source=1&target=134`, authHeaders);

	  check(pathRes, {
		      "path found successfully": (resp) => resp.status === 200,
		    });
}
```
##### Before
```
running (10.1s), 0/1 VUs, 9 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ path found successfully

     checks.........................: 100.00% ✓ 27       ✗ 0  
     data_received..................: 40 kB   3.9 kB/s
     data_sent......................: 3.6 kB  359 B/s
     http_req_blocked...............: avg=1.31ms  min=2.78µs  med=2.98µs  max=35.38ms  p(90)=3.19µs   p(95)=3.33µs  
     http_req_connecting............: avg=18.73µs min=0s      med=0s      max=505.84µs p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=39.86ms min=5.06ms  med=9.52ms  max=194.49ms p(90)=88.94ms  p(95)=119.42ms
       { expected_response:true }...: avg=99.96ms min=73.21ms med=87.16ms max=194.49ms p(90)=144.4ms  p(95)=169.45ms
     http_req_failed................: 66.66%  ✓ 18       ✗ 9  
     http_req_receiving.............: avg=96.9µs  min=66.59µs med=90.22µs max=183.09µs p(90)=133.81µs p(95)=143.39µs
     http_req_sending...............: avg=88.68µs min=35.55µs med=78.91µs max=314.15µs p(90)=115.19µs p(95)=186.32µs
     http_req_tls_handshaking.......: avg=1.08ms  min=0s      med=0s      max=29.17ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=39.68ms min=4.95ms  med=9.16ms  max=194.27ms p(90)=88.76ms  p(95)=119.22ms
     http_reqs......................: 27      2.666369/s
     iteration_duration.............: avg=1.12s   min=1.08s   med=1.1s    max=1.28s    p(90)=1.17s    p(95)=1.23s   
     iterations.....................: 9       0.88879/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1

```
##### After
```
running (10.5s), 0/1 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ path found successfully

     checks.........................: 100.00% ✓ 30       ✗ 0  
     data_received..................: 43 kB   4.1 kB/s
     data_sent......................: 4.0 kB  379 B/s
     http_req_blocked...............: avg=1.24ms   min=2.8µs   med=2.95µs   max=37.27ms  p(90)=3.14µs   p(95)=3.17µs  
     http_req_connecting............: avg=19.38µs  min=0s      med=0s       max=581.49µs p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=14.51ms  min=5.7ms   med=8.91ms   max=134.28ms p(90)=12.62ms  p(95)=34.16ms 
       { expected_response:true }...: avg=21.78ms  min=7.73ms  med=8.91ms   max=134.28ms p(90)=25.21ms  p(95)=79.74ms 
     http_req_failed................: 66.66%  ✓ 20       ✗ 10 
     http_req_receiving.............: avg=112.17µs min=63.82µs med=100.67µs max=193.61µs p(90)=153.73µs p(95)=171.17µs
     http_req_sending...............: avg=91.47µs  min=34.33µs med=84.11µs  max=537.6µs  p(90)=109.76µs p(95)=115.29µs
     http_req_tls_handshaking.......: avg=963.02µs min=0s      med=0s       max=28.89ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=14.3ms   min=5.57ms  med=8.74ms   max=134.05ms p(90)=12.4ms   p(95)=33.67ms 
     http_reqs......................: 30      2.860113/s
     iteration_duration.............: avg=1.04s    min=1.02s   med=1.02s    max=1.23s    p(90)=1.05s    p(95)=1.14s   
     iterations.....................: 10      0.953371/s
     vus............................: 1       min=1      max=1
     vus_max........................: 1       min=1      max=1
```

#### LoadTest
##### script
```js
export let options = {
  stages: [
	      { duration: "5s", target: 100 },
	      { duration: "20s", target: 100 },
	      { duration: "5s", target: 20 },
	    ],
	          thresholds: {
			                        http_req_duration: ['p(99)<5500'],
			                      },
};
```
##### Before
```
running (0m31.3s), 000/100 VUs, 1333 complete and 0 interrupted iterations
default ✓ [======================================] 000/100 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ path found successfully

     checks.........................: 100.00% ✓ 3999       ✗ 0    
     data_received..................: 5.6 MB  180 kB/s
     data_sent......................: 510 kB  16 kB/s
     http_req_blocked...............: avg=227.15µs min=2.25µs  med=2.88µs   max=52.49ms  p(90)=3.21µs   p(95)=9.14µs  
     http_req_connecting............: avg=25.57µs  min=0s      med=0s       max=14.81ms  p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=322.73ms min=3.45ms  med=250.76ms max=1.45s    p(90)=723.87ms p(95)=830.96ms
       { expected_response:true }...: avg=462.12ms min=34.82ms med=411.47ms max=1.45s    p(90)=864.66ms p(95)=967.78ms
     http_req_failed................: 66.66%  ✓ 2666       ✗ 1333 
     http_req_receiving.............: avg=469.79µs min=32.13µs med=84.74µs  max=119.98ms p(90)=237.56µs p(95)=983.6µs 
     http_req_sending...............: avg=275.7µs  min=22.79µs med=60.06µs  max=98.15ms  p(90)=167.2µs  p(95)=436.48µs
     http_req_tls_handshaking.......: avg=178.56µs min=0s      med=0s       max=51.39ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=321.98ms min=137.2µs med=250.63ms max=1.45s    p(90)=723.61ms p(95)=829.16ms
     http_reqs......................: 3999    127.793894/s
     iteration_duration.............: avg=1.97s    min=1.04s   med=2s       max=3.28s    p(90)=2.36s    p(95)=2.5s    
     iterations.....................: 1333    42.597965/s
     vus............................: 3       min=3        max=100
     vus_max........................: 100     min=100      max=100
```
##### After
```
running (0m31.0s), 000/100 VUs, 2481 complete and 0 interrupted iterations
default ↓ [======================================] 030/100 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ path found successfully

     checks.........................: 100.00% ✓ 7443       ✗ 0    
     data_received..................: 10 MB   325 kB/s
     data_sent......................: 897 kB  29 kB/s
     http_req_blocked...............: avg=122.37µs min=2.32µs  med=2.85µs  max=68.35ms  p(90)=3.15µs   p(95)=6.71µs 
     http_req_connecting............: avg=13.46µs  min=0s      med=0s      max=19.12ms  p(90)=0s       p(95)=0s     
   ✓ http_req_duration..............: avg=15.7ms   min=2.09ms  med=8.84ms  max=147.63ms p(90)=38.61ms  p(95)=51.91ms
       { expected_response:true }...: avg=16.45ms  min=2.09ms  med=7.63ms  max=115.37ms p(90)=45.4ms   p(95)=58.71ms
     http_req_failed................: 66.66%  ✓ 4962       ✗ 2481 
     http_req_receiving.............: avg=383.76µs min=33.84µs med=72.93µs max=67.49ms  p(90)=282.77µs p(95)=1.07ms 
     http_req_sending...............: avg=427.25µs min=19.09µs med=57.87µs max=80.59ms  p(90)=230.63µs p(95)=1.01ms 
     http_req_tls_handshaking.......: avg=81.07µs  min=0s      med=0s      max=42.83ms  p(90)=0s       p(95)=0s     
     http_req_waiting...............: avg=14.89ms  min=0s      med=8.28ms  max=118.22ms p(90)=36.45ms  p(95)=49.89ms
     http_reqs......................: 7443    239.979467/s
     iteration_duration.............: avg=1.05s    min=1.01s   med=1.03s   max=1.24s    p(90)=1.1s     p(95)=1.12s  
     iterations.....................: 2481    79.993156/s
     vus............................: 3       min=3        max=100
     vus_max........................: 100     min=100      max=100
```

#### StressTest
##### script
```js
export let options = {
	  stages: [
		      { duration: "10s", target: 200 },
		      { duration: "10s", target: 100 },
		      { duration: "15s", target: 300 },
		      { duration: "10s", target: 150 },
		      { duration: "5s", target: 100 },
		      { duration: "10s", target: 200 },
		      { duration: "5s", target: 100 },
		    ],
	    thresholds: {
		            http_req_duration: ['p(99)<7500'],
		        },
};

```
##### Before
```
running (1m07.2s), 000/300 VUs, 4328 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  1m5s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ path found successfully

     checks.........................: 100.00% ✓ 12984      ✗ 0    
     data_received..................: 18 MB   270 kB/s
     data_sent......................: 1.6 MB  24 kB/s
     http_req_blocked...............: avg=663.11µs min=2.13µs   med=2.84µs   max=321.13ms p(90)=3.18µs   p(95)=9.8µs   
     http_req_connecting............: avg=151.81µs min=0s       med=0s       max=124.67ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=535.16ms min=2.94ms   med=382.77ms max=2.96s    p(90)=1.27s    p(95)=1.62s   
       { expected_response:true }...: avg=704.52ms min=31.21ms  med=589.85ms max=2.96s    p(90)=1.47s    p(95)=1.78s   
     http_req_failed................: 66.66%  ✓ 8656       ✗ 4328 
     http_req_receiving.............: avg=604.24µs min=34.28µs  med=79.18µs  max=103.57ms p(90)=342.98µs p(95)=1.21ms  
     http_req_sending...............: avg=387.75µs min=21.94µs  med=55.94µs  max=173.77ms p(90)=189.93µs p(95)=708.49µs
     http_req_tls_handshaking.......: avg=454.8µs  min=0s       med=0s       max=286.61ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=534.17ms min=156.56µs med=381.31ms max=2.96s    p(90)=1.27s    p(95)=1.62s   
     http_reqs......................: 12984   193.216446/s
     iteration_duration.............: avg=2.61s    min=1.04s    med=2.46s    max=6.43s    p(90)=3.78s    p(95)=4.21s   
     iterations.....................: 4328    64.405482/s
     vus............................: 27      min=20       max=300
     vus_max........................: 300     min=300      max=300

```
##### After
```
running (1m05.9s), 000/300 VUs, 10141 complete and 0 interrupted iterations
default ✗ [======================================] 000/300 VUs  1m5s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ path found successfully

     checks.........................: 100.00% ✓ 30423      ✗ 0    
     data_received..................: 41 MB   617 kB/s
     data_sent......................: 3.6 MB  55 kB/s
     http_req_blocked...............: avg=124.21µs min=2.07µs  med=2.83µs  max=94.62ms  p(90)=3.11µs   p(95)=7.23µs 
     http_req_connecting............: avg=19.6µs   min=0s      med=0s      max=29.53ms  p(90)=0s       p(95)=0s     
   ✓ http_req_duration..............: avg=22.45ms  min=1.76ms  med=9.71ms  max=342.82ms p(90)=58.4ms   p(95)=84.01ms
       { expected_response:true }...: avg=23.16ms  min=1.76ms  med=9.03ms  max=262.89ms p(90)=63.54ms  p(95)=89.16ms
     http_req_failed................: 66.66%  ✓ 20282      ✗ 10141
     http_req_receiving.............: avg=531.91µs min=33.3µs  med=68.29µs max=217.38ms p(90)=504.79µs p(95)=1.39ms 
     http_req_sending...............: avg=607.78µs min=17.74µs med=54.62µs max=149.08ms p(90)=415.82µs p(95)=1.32ms 
     http_req_tls_handshaking.......: avg=71.86µs  min=0s      med=0s      max=67.54ms  p(90)=0s       p(95)=0s     
     http_req_waiting...............: avg=21.31ms  min=0s      med=8.96ms  max=342.66ms p(90)=56.18ms  p(95)=80.86ms
     http_reqs......................: 30423   461.334121/s
     iteration_duration.............: avg=1.07s    min=1s      med=1.04s   max=1.51s    p(90)=1.17s    p(95)=1.23s  
     iterations.....................: 10141   153.77804/s
     vus............................: 110     min=20       max=300
     vus_max........................: 300     min=300      max=300

```


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

##### 1. Reverse Proxy 개선
##### 2. Redis를 이용한 캐싱 - List 불러오는 부분을 캐싱하고 싶었으나 @CachePut 부분에서 어려움을 겪었습니다.
##### 3. 비동기 처리

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

