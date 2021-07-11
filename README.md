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

- K6 개선 전 테스트 결과 
    - Smoke 결과 
        ![img.png](src/main/resources/images/before_smoke.png)
    - Load 결과
        ![img.png](src/main/resources/images/before_load.png)
    - Stress 결과
        ![img.png](src/main/resources/images/before_stress.png)
      
- K6 개선 후 테스트 결과
    - Smoke 결과
    - Load 결과
    - Stress 결과


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- [X] 리버스 Proxy 개선하기
    - [X] CPU Core에 맞는 적절한 Worker 프로세스 할당
    - [X] http 수준에서 gzip 압축 활성화 
      ~~~
      text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;
      ~~~
    - [X] 캐시 유지 기간 10분 설정
    - [X] 전체 캐시 크기 200MB 설정
    - [X] 캐시 구분을 위한 Key 규칙 설정
    - [X] 로드 벨런서 설정 (8080, 8081 포트로 나눠져서 Request 분배)
    - [X] http 로드 금지 
    - [X] access log 찍지 않기 설정
    - [X] http2 계층 위에서 동작하기 설
    

- [ ] WAS 성능 개선하기
- [ ] 
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

