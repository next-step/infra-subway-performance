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
    `performance` 디렉토리 안에 결과 올렸습니다. 
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    1. `WebPageTest`, `Page Speed Insights`를 통해 성능 지표를 검토
    2. `load`, `smoke`, `stress` 테스트를 통하여 지표 출력
    3. 정적 파일 경량화
        - 번들 크기 줄이기
        - Code Splitting
        - Dynamic import
    4. Reverse Proxy 개선하기
        - http 블록 수준에서 gzip 압축 활성화
        - 캐시 설정
        - http2 적용
    5. WAS 성능 개선하기
        - Redis 활용 : 사용자, 노선, 역에 적용
        - 비동기 설정 추가 및 스레드 수 변경
        
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

