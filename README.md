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
   - k6Test 디렉토리 하위에 결과 파일 첨부했습니다.
     - 기존 설정으로 테스트 결과 3종
     - Reverse-proxy 개선 후 테스트 결과 3종
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   - 이전 단계에서 Reverse-proxy 압축 설정으로 성능 개선을 확인했었고, 추가적으로 캐싱 설정을 추가했습니다. 그 결과 압축 설정으로 큰 성능 개선이 이루어짐을 확인했고 캐싱은 비교적 성능 개선점이 나타나지는 않았습니다.
   - 추가적으로 was에 redis 캐시를 적용하여 db 부하를 줄이는 작업을 진행했습니다.

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

