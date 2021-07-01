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
- [x] 대상 페이지
    * 경로 검색
- [x] 목푯값 설정 (latency, throughput, 부하 유지기간)
    * DAU : 10만 상정 (카카오 지하철 2017 DAU 1/4 수준)
    * 1명당 1일 평균 접속 수 : 3
    * 피크 시간대 집중률(최대 트래픽 / 평소 트래픽) : 10
    * 1일 총 접속수 (DAU * 1명당 1일 평균 접속 수) : 300,000
    * 1일 평균 rps (1일 총 접속수 / 86,400) = 3.47
    * 1일 최대 rps (1일 평균 rps * 피크 시간대 집중률) = 34.7
- [x] 부하 테스트 시 저장될 데이터 건수 및 크기
    * 준비 된 운영 DB 데이터
- [x] 스크립트 작성
    * docs/test/path/path-smoke.js
    * docs/test/path/path-load.js
    * docs/test/path/path-stress.js


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

