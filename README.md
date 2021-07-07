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
    1. /k6Test/smoke
    2. /k6Test/load
    3. /k6Test/stress

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    1. reverse-proxy를 개선하여 캐시 설정을 하고 
        하나의 연결로 여러 클라이언트에게 응답을 보내는 http2 설정을 했습니다.
       (was가 여러 서버에 있어 연결이 적은 곳으로 로드밸런싱역할을 수행하면 
       더 개선될 것이라 생각합니다.)
   2. cache 활용 , 같은 내용의 요청과 응답을 지속적인 connection과 disconnection을 
    줄이기 위해 redis를 이용해 cache 기능을 활용했습니다. value의 cache 에 자주 사용하는
      메서드와 key로 요청과 응답을 줄였습니다.

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

