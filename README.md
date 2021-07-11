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
    - 테스트 시나리오 
      - 메인 화면 접속 → 로그인 성공 → 경로 조회
      - 위 3가지 기능을 모두 수행하는 것을 하나의 시나리오로 보고 테스트하였습니다.  
    - 스크립트 : /k6test/script
    - 결과 : /k6test/result
        * Load/Smoke/Stress별로 파일을 나누었고, 아래와 같이 총 3가지 상황에서의 결과값을 작성하였습니다.
            * 개선 전
            * Reverse Proxy 개선 후
            * Redis를 이용한 캐싱 후
        * 📍 스트레스 테스트의 강도를 높여본 결과, **최대 사용자는 약 1000VU/최대 처리량은 약 18만개**임을 확인했습니다. 
    
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    - Reverse Proxy 서버 개선과 WAS 개선, 두 가지로 나눌 수 있을 것 같습니다.
    1) Reverse Proxy 개선
        * gzip 압축 적용
        * css, js 등에 대해 Nginx 캐시 적용
    2) WAS 
        * WAS 서버 내에 Redis 구동 
        * 가장 시간이 오래 걸리고, 여러 엔티티(`Line`, `Station`)를 조회하는 `MapService`의 
    `findPath()` 메소드에 캐시 적용
      
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

