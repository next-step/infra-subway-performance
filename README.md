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
- asset 파일에 성능 테스트 전후 결과 추가하였습니다.
- 각 시나리오의 요청시간을 50ms 이하로 개선

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
#### Front
- 번들 크기 줄이기 : 웹팩 설정에서 용량이 작은 라이브러리로 교체하여 성능을 개선하였습니다.
- 프론트 라이브러리 비동기로 변경: LightHouse에서 제안하는 초반 지연 로딩 개선
#### Nginx
- 서버 이중화로 트래픽 분산 환경으로 개선.
- gzip 압축 활성
- css|js|gif|png|jpg|jpeg 의 캐싱  
- HTTP 2.0 지원

    
#### WAS
- Redis 적용
    - 자주 사용되는 조회 기능에 redis 캐시를 적용.
    - 내부망 레디스를 통해서 캐시된 내용 로드
- 서버에서 압축을 지원하도록 수정
- 정적 자원 캐싱

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

