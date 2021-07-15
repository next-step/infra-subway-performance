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
* test script: /performance/script/
* 개선전 테스트 결과: /performance/result/before/
* reverse proxy 개선 후 테스트 결과: /performance/result/proxy-improve/
* redis cache 적용 후 테스트 결과: /performance/result/cache-improve/
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    * reverse proxy 개선
        * gzip 적용
        * css, js, 이미지 파일에 대한 캐시 적용
        * http2 적용
    * 경로 조회 기능에 redis cache 적용

#### 요구사항
* 부하 테스트 각 시나리오의 요청 시간을 50이하로 개선
    * 개선 전/후를 직접 계측하여 확인

#### 작업 진행 순서
* [x] 성능 개선전 somke, load, stress 테스트 진행
* [x] reverse proxy 개선 후 성능 측정
* [x] redis 적용 후 성능 측정

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

#### 요구사항
* 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* 즐겨찾기 페이지에 페이징 쿼리를 적용
    * 로그인한 사용자는 최근에 추가한 즐겨찾기만 관심이 있기에 한번에 5개의 즐겨찾기만 보고싶다.
* 데이터베이스 이중화

#### 작업 진행 순서
* [ ] 인덱스 적용해보기 실습
* [ ] 즐겨찾기 페이지 페이징 쿼리 적용
* [ ] 데이터베이스 이중화
