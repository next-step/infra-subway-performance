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
   
   ./perfomancetest/load_test.md
   
   ./perfomancetest/smoke_test.md
   
   ./perfomancetest/stress_test.md
   
각 파일에 개선 전후 테스트 결과 기록하였습니다.

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- 먼저 인프라 부분에서 할수있는 부분을 먼저 개선해보았습니다.

    1. Reverse Proxy개선
        1. CPU core에 맞는 Worker 프로세스 할당
        2. gzip 압축전송사용
        3. nginx 캐시 사용
        4. http2 적용
    2. WAS 개선
        1. Redis적용 
            - 사용자들의 공통적으로 조회요청을 하는 부분인 노선조회, 경로조회 부분에 redis를 사용하여 캐시를 적용하였습니다.
            - 데이터의 변경이 발생하는 부분에서는 케시를 제거하여 이후 요청시에 변경된 내용까지 케시에 적용되도록 변경하였습니다.
        2. 이전 과제와 차이에서 느낀점
            - 이전 서비스 진단하기 2단계 미션에서 성능개선에 static hashmap을 사용하여 케쉬 역할을 하는 기능을 구현하여 성능 개선을 하였었습니다.
            - 하지만 로컬캐시를 하게되면 인스턴스가 늘어날경우 데이터의 일관성이 꺠지게되어 부정확한 데이터가 조회도리수 있었는데 Redis를 사용하여 처리를 하게되니 인스턴스가 늘어나도 데이터가 부정합할 가능성이 사라졌습니다.
            
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

