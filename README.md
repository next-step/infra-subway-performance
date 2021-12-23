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

- ### 접속 빈도가 높은 메인 페이지
    - #### 개선 전
        - [load 테스트 결과](./k6/main/load-result.txt)
        - [smoke 테스트 결과](./k6/main/smoke-result.txt)
        - [stress 테스트 결과](./k6/main/stress-result.txt)
    - #### 개선 후
        - [load 테스트 결과](./k6/main/load-result.txt)
        - [smoke 테스트 결과](./k6/main/smoke-result.txt)
        - [stress 테스트 결과](./k6/main/stress-result.txt)
- ### 데이터를 갱신하는 내정보 수정 페이지
    - #### 개선 전
        - [load 테스트 결과](./k6/myinfo-update/load-result.txt)
        - [smoke 테스트 결과](./k6/myinfo-update/smoke-result.txt)
        - [stress 테스트 결과](./k6/myinfo-update/stress-result.txt)
    - #### 개선 후
        - [load 테스트 결과](./k6/main/load-result.txt)
        - [smoke 테스트 결과](./k6/main/smoke-result.txt)
        - [stress 테스트 결과](./k6/main/stress-result.txt)
- ### 데이터를 조회하는데 여러 데이터를 참조하는 경로 탐색 페이지
    - #### 개선 전
        - [load 테스트 결과](./k6/path/load-result.txt)
        - [smoke 테스트 결과](./k6/path/smoke-result.txt)
        - [stress 테스트 결과](./k6/path/stress-result.txt)
    - #### 개선 후
        - [load 테스트 결과](./k6/main/load-result.txt)
        - [smoke 테스트 결과](./k6/main/smoke-result.txt)
        - [stress 테스트 결과](./k6/main/stress-result.txt)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- Reverse Proxy 정적 파일 경량화 및 http2 적용
    - 성능 향상을 기대했으나, 성능 향상이 이루어지지 않고 결과에 따라 성능이 낮아진 결과도 보임
    - Redis 캐시 적용
    - DB 커넥션이 발생시키지 않고 메모리에 저장된 캐시를 사용하여, 테스트의 횟수가 늘어날 수록 압도적인 성능 향상을 보여주 었음
    - Thread Pool 설정 적용
    - 많은 조회가 일어나는 경로 찾기에 비동기로 적용 하였으나, Redis 캐시에 비해 성능 향상면에서 큰 효과는 없었음

---

### 2단계 - 조회 성능 개선하기

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

