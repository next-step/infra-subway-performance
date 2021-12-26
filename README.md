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
#### 개선 전
- lighthouse 테스트

![lighthouse_개선전](https://user-images.githubusercontent.com/36442984/147405759-db2df1d1-fd26-4528-9569-b8d9fd0c79d7.png)
- smoke 테스트

![smoke_개선전](https://user-images.githubusercontent.com/36442984/147405758-6731e09d-7db8-4748-9a94-b390c2eccce0.png)
- load 테스트

![load_개선전](https://user-images.githubusercontent.com/36442984/147405757-c1ab7ffe-d8eb-454d-9d74-88494e6ae564.png)
- stress 테스트

![stress_개선전](https://user-images.githubusercontent.com/36442984/147405755-0161c3fa-120d-4214-839f-cf137ea551fc.png)
--- 
#### 개선 후
- lighthouse 테스트

![lighthouse_개선후](https://user-images.githubusercontent.com/36442984/147405754-7fe5a40e-ccc6-4b25-bd4c-e26701fe1546.png)
- smoke 테스트

![smoke_개선후](https://user-images.githubusercontent.com/36442984/147405752-fd51d61e-9674-406b-90d7-b72117779447.png)
- load 테스트

![load_개선후](https://user-images.githubusercontent.com/36442984/147405751-27fb28e6-c33c-44e8-8269-157c5b74fe9d.png)
- stress 테스트

![stress_개선후](https://user-images.githubusercontent.com/36442984/147405748-c41d9877-04ec-4085-b1cf-878aabea4f57.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Nginx와 Redis를 통한 개선을 시도해보았으나 결과적으로는 Nginx를 통한 성능 개선만 적용하였습니다. Redis의 경우 AOP로 인한 성능 저하가 캐시로 얻는 이점보다 더 큰 것으로 보였습니다.
- Nginx 성능 개선 요소
  1. response에 대한 gzip 압축
  2. 정적 파일들에 대한 캐싱
  3. Http/2 적용
  4. Strict-Transport-Security 헤더를 통해 클라이언트에서 http로 요청을 보내지 않도록 하여 redirect되는 요청을 최소화
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

