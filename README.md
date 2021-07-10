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
- 성능 개선 전 테스트 결과

#### Login Test 
* Smoke Test
    * Test Result : rps 1.94, fail 0 %
      ![smoke_login](./src/main/resources/static/images/k6/before/smoke_login.png)

* Load Test
    * Test Result : rps 241.64, fail 0 %
      ![load_login](./src/main/resources/static/images/k6/before/load_login.png)

* Stress Test
    * Test Result(Max 400) : rps 429.52, request fail 7.27 %, login fail 13 %
       ![stress_login_1](./src/main/resources/static/images/k6/before/stress_login.png)


#### Path Test 
* Smoke Test
    * Test Result : rps 0.86, fail 0 %
      ![smoke_path](./src/main/resources/static/images/k6/before/smoke_path.png)

* Load Test
    * Test Result : rps 11.79, fail 0 %
      ![load_path](./src/main/resources/static/images/k6/before/load_path.png)

* Stress Test
    1. Test Result : rps 11.86, request fail 0.23 %
       ![stress_path](./src/main/resources/static/images/k6/before/stress_path.png)


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

