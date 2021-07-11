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

## 요구사항
*[ ] 부하테스트 각 시나리오의 요청시간을 50ms 이하로 개선
    * 개선 전 / 후를 직접 계측하여 확인
*[ ] Redis를 이용해 부하가 많은 곳에 Caching 처리
*[ ] 비동기 작업을 위한 Thread pool 설정 추가 ( 비동기로 처리할 작업은... 없어보임.. )


## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.

### 1단계 - 화면 응답 개선하기

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

## 접속 빈도가 높은 페이지
### smoke

* 스크립트 위치
> /script/stress/connectionfrequency/smoke.js

* 결과
> /script/result/connectionfrequency/SMOKE_RESULT.md

<br>

### load
* 스크립트 위치
> /script/stress/connectionfrequency/load.js

* 결과
> /script/result/connectionfrequency/LOAD_RESULT.md

<br>

### stress
* 스크립트 위치
> /script/stress/connectionfrequency/stress.js

* 결과
> /script/result/connectionfrequency/STRESS_RESULT.md

<br><br>

## 데이터를 갱신하는 페이지
### smoke
* 스크립트 위치
> /script/stress/dataupdate/smoke.js

* 결과
> /script/result/dataupdate/SMOKE_RESULT.md

<br>

### load
* 스크립트 위치
> /script/stress/dataupdate/load.js

* 결과
> /script/result/dataupdate/LOAD_RESULT.md

<br>

### stress
* 스크립트 위치
> /script/stress/dataupdate/stress.js

* 결과
> /script/result/dataupdate/STRESS_RESULT.md

<br><br>

## 데이터를 조회하는데 여러 데이터를 참조하는 페이지

### smoke
* 스크립트 위치
> /script/stress/refertomultiplepage/smoke.js

* 결과
> /script/result/refertomultiplepage/SMOKE_RESULT.md

<br>

### load
* 스크립트 위치
> /script/stress/refertomultiplepage/load.js

* 결과
> /script/result/refertomultiplepage/LOAD_RESULT.md

<br>

### stress
* 스크립트 위치
> /script/stress/refertomultiplepage/stress.js

* 결과
> /script/result/refertomultiplepage/STRESS_RESULT.md

<br><br>

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    * Reverse proxy 역할을 하는 Nginx 개선
      * 적당한 worker 프로세스 Connection 갯수 적용 
      * Gzip 압축 적용
      * Proxy 캐시 적용
    * Application 캐시 적용
      * Redis cache를 이용하여 조회 빈도 수가 많은 서비스 캐싱


---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

