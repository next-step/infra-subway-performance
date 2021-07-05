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
- [x] 대상 페이지
    * 메인, 로그인, 경로 검색
- [x] 부하 테스트 시 저장될 데이터 건수 및 크기  
    * 준비 된 운영 DB 데이터  
- [x] 스크립트 작성
    * loadTest/longin 
        * smoke.js
        * load.js
        * stress.js
        
    * loadTest/main
        * smoke.js
        * load.js
        * stress.js

    * loadTest/path
        * smoke.js
        * load.js
        * stress.js

- [x] 개선 전 테스트 결과
    * loadTest/longin/beforeResult.txt
    * loadTest/main/beforeResult.txt
    * loadTest/path/beforeResult.txt

- [x] 개선 후 테스트 결과
    * loadTest/longin/afterResult.txt
    * loadTest/main/afterResult.txt
    * loadTest/path/afterResult.txt
    
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    - [x] Reverse Proxy 개선
        * gzip 압축 활성화
        * HTTP2.0 설정
    - [x] WAS 성능 개선
        * redis 적용
            * application-prod.properties 에 redis 설정값 추가 및 build.gradle 에 redis 추가
            * DB 서버에 Docker로 Redis Server 기동
            * 조회가 빈번한 station service, map service, member service 관련 메소드에 캐시 적용
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

