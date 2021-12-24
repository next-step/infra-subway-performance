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
* [x] WAS 성능 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* 테스트 스크립트
  * [Smoke 테스트 스크립트](/k6/smoke.js)
 * [Load 테스트 스크립트](/k6/load.js)
 * [Stress 테스트 스크립트](/k6/stress.js)
* 개선 전
  * [Smoke 테스트 결과](/k6/before/smoke.log)
  * [Load 테스트 결과](/k6/before/load.log)
  * [Stress 테스트 결과](/k6/before/stress.log)
* 개선 후
  * [Smoke 테스트 결과](/k6/after/smoke.log)
  * [Load 테스트 결과](/k6/after/load.log)
  * [Stress 테스트 결과](/k6/after/stress.log)
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
 Redis 캐시 적용, Async Thread Pool 설정 했습니다. 
 전체적으로 40% 정도 성능차이가 났고 특히 stress테스트에서 fail이 났던게 사라져서 
 더 많은 request를 받을 수 있게 되었습니다.
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

