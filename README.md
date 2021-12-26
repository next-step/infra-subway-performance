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

#### 0. 목표 rps 구하기
1) 1일 예상 사용자 수(DAU) : 500,000명 (50만명)
2) 피크 시간대의 집중률 (최대 트래픽/평소 트래픽) : 10
3) 1명당 1일 평균 요청수 : 4

**Throughput 계산 : 1일 평균 rps ~ 1일 최대 rps**
- 1일 총 접속 수 : DAU(1일 사용자 수) X 1명당 1일 평균 접속 수
    - 500,000 * 4 = 2,000,000
- 1일 평균 rps : 1일 총 접속 수 / 86,400(초/일)
    - 2,000,000 / 86,400 = 23.14 => 24
- 1일 최대 rps : 1일 평균 rps * (피크 시간대 집중률)
    - 23.14 * 10 = 231.4 => 232 

[VUser 구하기]
```
VU: 가상 사용자 수
R: 하나의 VU iteration(시나리오)에 포함된 요청 수
T: VU iteration을 완료하는데 소요되는 시간보다 큰 시간
```
- http_req_duration : 0.8s
- Latency : 100ms 이하
- 
- R: 1 (메인페이지, 경로검색 모두 1)
- T = 1*0.8s +0.1s = 0.9s
- 평균 VUser = 24 * 0.9s / 1 = 21.6 => 22
- 최대 VUser = 232 * 0.9s / 1 = 208.8 => 209

#### 1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

[메인페이지] - 정적 리소스 캐싱과 etag 적용 영향 테스트

**smoke test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- |
| 개선전 | 19.64ms           | 276.09µs           | 18.74ms          | 26.53ms          |
| 개선후 | 57.36ms           | 436.79µs           | 56.43ms          | 27.47ms          |



**load test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- |
| 개선전 | 14.23ms           | 131.78µs           | 13.98ms          | 128.47µs         |
| 개선후 | 14.65ms           | 140.24µs           | 14.37ms          | 137.33µs         |



**stress test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked | http_req_failed |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- | --------------- |
| 개선전 | 9.7ms             | 85.08µs            | 9.55ms           | 43.2µs           | 31.52%          |
| 개선후 | 9.79ms            | 84.46µs            | 9.63ms           | 42.35µs          | 31.52%          |



[경로검색 - 복잡한 연산이 포함된 페이지] - WAS 캐싱 영향 테스트

**smoke test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- |
| 개선전 | 154.51ms          | 909.88µs           | 153.13ms         | 30.47ms          |
| 개선후 | 162.87ms          | 679.66µs           | 161.66ms         | 27.94ms          |



**load test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- |
| 개선전 | 601.18ms          | 462.54µs           | 600.64ms         | 810.24µs         |
| 개선후 | 15.01ms           | 164µs              | 14.69ms          | 150.46µs         |



**stress test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked | http_req_failed |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- | --------------- |
| 개선전 | 615.78ms          | 319.46µs           | 615.44ms         | 298.25µs         | 46.96%          |
| 개선후 | 11.94ms           | 153.98µs           | 11.71ms          | 54.68µs          | 29.27%          |


#### 결론
- 정적 리소스 캐싱을 적용한 것은 큰 차이를 보이지 못함
- WAS 캐싱은 많은 개선 효과가 있는 것으로 보임

** 스크립트와 결과물은 ```k6-test``` 폴더 참조

#### 2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
1) ETag와 정적 캐싱을 이용한 성능 개선
- 정적 리소스(js와 css)에 대해 캐시 만료 기한을 1년으로 설정
- 변경된 파일 배포시 클라이언트가 업데이트 받을 수 있도록 요청 url에 버전정보를 사용
- html에서 리소스 버전 정보를 하드코딩하여 url에 사용하지 않고 ```HandlebarsHelper```를 이용하여 버전 정보를 서버에서 받아올 수 있도록 설정
- 버전 정보는 설정파일로 관리하여, 버전 변경시 재빌드 하지 않아도 되도록 함

2) WAS 캐싱
- 경로 찾기 캐싱
  - ```노선 추가, 노선 삭제, 노선에 구간 추가, 노선에 구간 삭제```의 경우, 캐싱 삭제
- 노선 캐싱(전체조회, 특정ID로 조회)
  - 특정 노선에 대해 ```입력, 수정, 삭제``` 이벤트가 발생할 경우 ```전체 조회``` 캐싱 삭제
- 지하철역 캐싱(전체 조회)
  - 특정 지하철역에 대해 ```입력, 수정, 삭제``` 이벤트가 발생할 경우 ```전체 조회``` 캐싱 삭제


---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

