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

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- 목푯값 설정 (latency, throughput, 부하 유지기간)
   - DAU : 통계수치를 바탕으로 I사 하루 사용량 예상: 3만명 가량으로 예상
   - 사용자가 보통 5번씩 사용한다고 가정
   - 1일 총 접속수: 3만명 * 5 = 15만회
   - 150,000 / 86400 = 2rps
   - 1일 최대 rps: 2 * 100 / 10 = 20 rps
   - 사용자가 1분 내외로 사용한다고 가정.
- 부하 테스트 시 저장될 데이터 건수 및 크기
   - 준비 된 운영 DB 데이터

- 개선 전 테스트 결과
   * docs/beforeTest.txt

- 개선 후 테스트 결과
   * docs/afterTest.txt

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
* nginx
   - gzip compress 적용
   - css|js|gif|png|jpg|jpeg에 대해 nginx 캐시 적용
   - http 2.0 적용
   
* redis cache
   - private 서버에 redis를 띄움
   - prod profile에서는 private 서브넷의 redis에 접속
   - 많은 엔티티를 조회 후 조합해서 응답을 만들어내는 findPath메서드에 캐시를 적용해서 redis에 저장

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요