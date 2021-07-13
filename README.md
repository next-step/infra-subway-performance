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
#### TODO List
- [x] 개선 전 성능 파악하기
  - [x] 테스트, 로컬개발, 운영 환경 프로퍼티 세팅
  - [x] 부하 테스트 해보기
- [x] reverse-proxy 개선해보기
- [x] was 개선해보기

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

사용자가 가장 많이 사용할 거 같은 기능인 `지하철역 조회 -> 경로 탐색 시나리오`로 테스트 
* [Smoke 테스트 결과](./station-line-search/smoke/README.md)
* [Load 테스트 결과](./station-line-search/load/README.md)
* [Stress 테스트 결과](./station-line-search/stress/README.md)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
### 1. reverse-proxy 개선
#### worker_connection 수정 
* 스트레스 테스트 중 vus 240 정도로 올라가면 was 에 특별한 error log 가 없이, reverse-proxy 단에서 클라이언트 요청 처리가 실패 했음
* nginx 가 요청을 처리 못하는거 같았음, 코어가 1개 짜리 서버이기 때문에 worker_process 개수를 늘리는건 의미 없다고 생각해서, worker_connection 를 수정함 
* 512 (default) -> 1024 로 늘렸더니 시스템이 버틸수 있는 최대 vus 가 420~430 대 까지 늘었음
* 1024 -> 2048 로 늘려봤더니 max vus 가 930~940 까지 늘었음, 대신 평균 응답속도가 work_connection 1024 때 보다 더 느려짐
* worker_connection 이 1024 개던, 2048 개던 rps 는 200 초반 정도 였음 __worker_connection 이 늘어난다고 rps 가 늘지 않는다__ 는 걸 알게됨. 
* 부하 테스트의 최대 vus 가 300 이라 worker_connection 를 1024 로 세팅함

#### keepalive 지시어 추가
* reverse-proxy <-> was 의 통신에 대한 리소스를 최소한으로 사용해 보려고 nginx 설정에 keepalive 지시어를 추가했음
* 미세한 성능 효과를 얻음, 15ms 정도의 응답속도가 줄음.

#### gzip, http2 적용
gzip, http2 둘 다 응답속도의 영향을 주지 않을 만큼 (오히려 더 떨어졌을때도 있었음) 효과가 없었음. 

### 2. web-application 개선
#### N+1 문제 수정
* 최단경로를 찾기 위해 Line 을 가져오고 (1) -> Line 에서 Section 을 가져온다 (23 번) 총 24 번의 쿼리가 날아감
* `spring.jpa.properties.hibernate.default_batch_fetch_size` 설정을 추가해 Line 에 연관된 Section 을 in query 로 한번에 가져온다.
* 스트레스 테스트 기준 820~830ms 에서 620~630ms 정도로 응답속도가 개선됨.
#### 자주 조회 되지만 잘 안바뀌는 데이터에 캐시 적용
* 지하철노선도 어플리케이션에서 지하철역 리스트와 지하철역 사이의 최단 경로 결과를 redis 에 캐시함
* 스트레스 테스트 기준 620~630ms 에서 200ms 정도로 응답속도가 개선됨. 

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

