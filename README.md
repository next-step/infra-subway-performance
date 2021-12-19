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
- 리소스 부하가 큰 최적 경로 페이지 캐싱 적용해보기
  - caching 미적용
    - [smoke 결과](/k6/path/path_smoke.PNG)
    - [load 결과](/k6/path/path_load.PNG)
    - [stress 결과](/k6/path/path_stress.PNG)
  - caching 적용
    - [smoke 결과](/k6/path/redis_path_smoke.PNG)
    - [load 결과](/k6/path/redis_path_load.PNG)
    - [stress 결과](/k6/path/redis_path_stress.PNG)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
기존에 캐시를 미적용 하였을땐 stress 테스트에서 99프로 이상을 통과하지 못했던 상황이 생겼습니다.
그래서 이 부분에 캐싱을 적용하면 눈에 띄는 성능이 나타날 것이라고 예상하여
캐싱을 적용하였더니 100프로 통과하는 모습을 보여줬으며 지표 또한 눈에 띄는 결과를 나타내었습니다. 

- 지표적 성능 향상 수치
  - 총 요청수 : 2.5 배 가까이 정상적으로 올라옴
  - 요청 시작전 tcp 연결 대기 소요 시간 : 8배 향상
  - tcp 연결 소요 시간 : 10배 이상 향상
  - **원격 호스트 응답 대기 시간 : 3000배 가까운 성능향상**
  - **총 요청 시간 : 2.5배**

지표적으로 분석 했을 경우 캐싱을 적용하여 가장 눈에 띄는 지표는 원격 호스트의 응답 대기시간과
총 요청 시간인 것 같습니다.

- 평균값 지표

|http_지표|reqs|blocked|connecting|tls_handshaking|sending|waiting|receiving|duration|
|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|
|캐시 미적용|6170|413ps|345ps|0ps|76ps|1.5s|90ps|2.5s|
|캐시 적용|15108|49ps|26ps|0ps|71ps|4.3ms|135ps|1s|

- 지표 설명
  - http_reqs — 총 요청 수
  - http_req_blocked — 요청을 시작하기 전에 사용 가능한 TCP 연결을 기다리는 데 소요 된 시간
  - http_req_connecting — TCP 연결 설정에 소요 된 시간
  - http_req_tls_handshaking — TLS 핸드 쉐이킹에 소요 된 시간
  - http_req_sending — 데이터 전송에 소요 된 시간
  - http_req_waiting — 원격 호스트의 응답을 기다리는 데 소요 된 시간
  - http_req_receiving — 데이터 수신에 소요 된 시간
  - http_req_duration— 요청에 대한 총 시간. http_req_sending + http_req_waiting 기준

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

