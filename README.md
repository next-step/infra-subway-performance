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
   - Load : [main](/k6/main/load_main_result.md), [line-update](/k6/line-update/load_update_result.md), [paths](/k6/path/load_path_result.md)
   - Smoke : [main](/k6/main/smoke_main_result.md), [line-update](/k6/line-update/smoke_update_result.md), [paths](/k6/path/smoke_path_result.md)
   - Stress : [main](/k6/main/stress_main_result.md), [line-update](/k6/line-update/stress_update_result.md), [paths](/k6/path/stress_path_result.md)
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   ![img.png](img.png)
   ![img_1.png](img_1.png)
- 페이지에 대한 성능은 별로 차이가 없음을 볼 수 있습니다..
- k6로 다시 테스트 했을 때 paths 조회에서 http_req_duration이 4~5배 가량 좋아진 것을 볼 수 있습니다.
- [paths stress test](/k6/path/stress_path_result.md), [paths load test](/k6/path/load_path_result.md)
---
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

