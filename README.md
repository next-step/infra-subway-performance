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
load 테스트  
```shell
[ubuntu@LOAD]:~$ k6 run --out influxdb=http://localhost:8086/myk6db cache_load.js

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: cache_load.js
     output: InfluxDBv1 (http://localhost:8086)

  scenarios: (100.00%) 1 scenario, 60 max VUs, 12m40s max duration (incl. graceful stop):
           * default: Up to 60 looping VUs for 12m10s over 5 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (12m10.2s), 00/60 VUs, 27591 complete and 0 interrupted iterations
default ↓ [======================================] 03/60 VUs  12m10s

     ✓ correct path1
     ✓ correct path2
     ✓ correct lines1

     checks.........................: 100.00% ✓ 82773      ✗ 0
     data_received..................: 3.5 GB  4.8 MB/s
     data_sent......................: 18 MB   24 kB/s
     http_req_blocked...............: avg=6.5µs   min=140ns   med=297ns   max=19.07ms  p(90)=436ns   p(95)=496ns
     http_req_connecting............: avg=1.03µs  min=0s      med=0s      max=8.69ms   p(90)=0s      p(95)=0s
   ✓ http_req_duration..............: avg=16.36ms min=3.31ms  med=7.61ms  max=268.69ms p(90)=45.55ms p(95)=67.47ms
       { expected_response:true }...: avg=16.36ms min=3.31ms  med=7.61ms  max=268.69ms p(90)=45.55ms p(95)=67.47ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 137955
     http_req_receiving.............: avg=1.57ms  min=12.56µs med=89.04µs max=157.65ms p(90)=2.71ms  p(95)=5.49ms
     http_req_sending...............: avg=43.17µs min=14.43µs med=29.51µs max=36.47ms  p(90)=55.4µs  p(95)=74.83µs
     http_req_tls_handshaking.......: avg=4.7µs   min=0s      med=0s      max=13.8ms   p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=14.74ms min=1.45ms  med=5.73ms  max=268.6ms  p(90)=42.48ms p(95)=62.66ms
     http_reqs......................: 137955  188.937438/s
     iteration_duration.............: avg=1.08s   min=1.02s   med=1.03s   max=1.48s    p(90)=1.2s    p(95)=1.22s
     iterations.....................: 27591   37.787488/s
     vus............................: 3       min=1        max=60
     vus_max........................: 60      min=60       max=60
```
![image](https://user-images.githubusercontent.com/87216027/167416366-8e6d77de-f40e-416b-97ce-096f2e6bf984.png)
RPS 수치 확인 (595 에서도 응답시간도 빠르고 안정적이었다.)  
![image](https://user-images.githubusercontent.com/87216027/167416414-dc6e47d8-a321-4659-941f-b29ee159be80.png)


stress 테스트  
```shell
WARN[0783] Request Failed                                error="Get \"https://dibtp1221.kro.kr//paths?source=24&target=12\": EOF"
ERRO[0783] the body is null so we can't transform it to JSON - this likely was because of a request error getting the response
running at reflect.methodValueCall (native)
default at file:///home/ubuntu/cache_stress.js:33:29(33)
        at native  executor=ramping-vus scenario=default source=stacktrace
^C
running (13m03.1s), 000/410 VUs, 58048 complete and 332 interrupted iterations
default ✗ [==========================>-----------] 320/410 VUs  13m03.1s/18m20.0s

     ✓ correct path1
     ✓ correct path2
     ✓ correct lines1

     checks.........................: 100.00% ✓ 175027     ✗ 0
     data_received..................: 7.5 GB  9.5 MB/s
     data_sent......................: 39 MB   50 kB/s
     http_req_blocked...............: avg=1.47ms   min=0s     med=306ns   max=560.65ms p(90)=435ns    p(95)=503ns
     http_req_connecting............: avg=228.4µs  min=0s     med=0s      max=235.18ms p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=96.16ms  min=0s     med=55.29ms max=1.67s    p(90)=241.11ms p(95)=300.29ms
       { expected_response:true }...: avg=96.19ms  min=3.31ms med=55.31ms max=1.67s    p(90)=241.14ms p(95)=300.33ms
     http_req_failed................: 0.03%   ✓ 106        ✗ 291719
     http_req_receiving.............: avg=4.41ms   min=0s     med=434.2µs max=335.88ms p(90)=9.73ms   p(95)=23.73ms
     http_req_sending...............: avg=920.69µs min=0s     med=29.19µs max=1.04s    p(90)=55.84µs  p(95)=89.92µs
     http_req_tls_handshaking.......: avg=694.05µs min=0s     med=0s      max=413.53ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=90.83ms  min=0s     med=52.79ms max=1.26s    p(90)=230.49ms p(95)=284.79ms
     http_reqs......................: 291825  372.636973/s
     iteration_duration.............: avg=1.48s    min=8.79ms med=1.48s   max=4.3s     p(90)=1.93s    p(95)=2.09s
     iterations.....................: 58048   74.122611/s
     vus............................: 332     min=1        max=332
     vus_max........................: 410     min=410      max=410

```
![image](https://user-images.githubusercontent.com/87216027/167416891-f2d93629-69e6-4e9b-8d68-b375245cb8ad.png)
RPS 수치 확인 (1300 까지도 괜찮아보인다..)  
![image](https://user-images.githubusercontent.com/87216027/167416914-1b3d1108-9977-4927-92a6-8e22a407b6fd.png)


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요  
   a. Nginx-gzip압축추가  
   https://dibtp1221.kro.kr PageSpeed (First View), webpage test 개선 전후 비교  
   사이트 3개 모두 TTI, TBT는 PageSpeed (데스크톱), 그 외 메트릭은  webpage test 수치 사용.  
   단위: 초(s)  
   ||아무개선 없었을 때|Nginx-gzip압축추가|
   |----------------|-------------------------------|-------------------------------|
   |FCP|4.853|1.7|
   |TTI|2.8|1.4|
   |Speed Index|4.803|1.719|
   |LCP|4.885|1.712|
   |TBT|0.05|0.07|

![image](https://user-images.githubusercontent.com/87216027/167415885-a705ec24-2cd8-4e18-adf2-2fb39613ec1c.png)

b. Nginx - cache 사용  
PageSpeed, smoke test 에서 수치개선 없어보였다.  

c. Nginx - TLS, HTTP/2 설정  
smoke test에서 오히려 처음에는 시간이 더 걸리는 것으로 나왔고, 이후에는 비슷했다.  
![image](https://user-images.githubusercontent.com/87216027/167415838-9780bba1-92f8-41fd-8cee-38bb545f168a.png)


d. WAS - caching 적용  
역관리, 노선관리, 구간관리, 경로 검색에 caching 적용하였다.  

캐싱 효과 확인을 위해 단일 요청 smoke test 를 진행해보았다.  
역관리 (역 list 나오는 화면)  
캐싱 적용 전  
![image](https://user-images.githubusercontent.com/87216027/167415999-fdb16ca1-ee66-4f8f-bb86-0e9e1d7cfc05.png)
캐싱 적용 후  
![image](https://user-images.githubusercontent.com/87216027/167416105-0862fa8d-77d7-4cc7-9798-09f3b5b3a816.png)

캐싱 적용 후에 첫 요청 응답시간이 전에 비해서 컸지만, redis에 값을 넣느라 그런게 아닐까 생각한다.  
후에는 확실히 유의미하게 시간이 줄어든 것을 확인할 수 있다.

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
