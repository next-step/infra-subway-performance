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
    * 스크립트 : 
        * Smoke : /k6/script/path_find_smoke_test.js
        * Load : /k6/script/path_find_load_test.js
        * Stress : /k6/script/path_find_stress_test.js
    * 결과 :
        * 성능 개선 미적용
            * Smoke : /k6/result/path_find_smoke_test_result_original.md
            * Load : /k6/result/path_find_load_test_result_original.md
            * Stress: /k6/result/path_find_stress_test_result_original.md
        * nginx http2 적용
            * Smoke : /k6/result/path_find_smoke_test_result_after_nginx_config.md
            * Load : /k6/result/path_find_load_test_result_after_nginx_config.md
            * Stress: /k6/result/path_find_stress_test_result_after_nginx_config.md
        * redis
            * Smoke : /k6/result/path_find_smoke_test_result_after_redis.md 
            * Load : /k6/result/path_find_load_test_result_after_redis.md 
            * Stress: /k6/result/path_find_stress_test_result_after_redis.md         
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
    * 서버 구성 :
        * web server : 1대 (192.168.123.202)
        * was server : 1대 (192.168.123.22)
        * db / redis server : 1대 (192.168.123.137)
    * 개선 과정 : 하기 개선 과정의 수치는 Load 테스트 기준(/k6/script/path_find_load_test.js)으로 함
        * latency 목표값 : 99%의 요청이 50ms 안에 수행
        * 개선을 위한 전락 :
            * nginx http2 적용
            * redis 적용 : 캐시 적용하여 DB 조회를 줄임
            * aync 미적용 : 현재 프로젝트에서는 외부 api 를 사용하는 부분이 없고
            , 속도 저하의 대부분이 DB에서 발생하기 때문에 적용에 이득이 없다고 판단함
        * 개선 내용 적용 결과 :
            * 요약 : http2 적용함에 따라 평균 속도 기준 대략 2배 정도 느려지는 모습이었으나, 
            redis를 적용하며 http2 적용시 비교하여 770배, 개선 미적용 비교하여 280배 빨라지는 효과가 있었음.
            추가로, latency 목표값인 99%의 요청이 50ms 안에 수행되는 부분 또한 충족함
            * 개선 미적용 시 latency :
                * avg=2.54s / min=9.31ms / med=920.89ms / max=14.32s
            * nginx http2 적용 시 latency :
                * avg=7.16s / min=9.67ms / med=2.92s / max=29s
            * redis 적용 시 latency :
                * avg=9.44ms / min=3.43ms / med=7.33ms / max=238.13ms
    
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

---

## 요구사항
### Step1
* 부하테스트 각 시나리오의 요청시간을 50ms 이하로 개선
    * 개선 전 / 후를 직접 계측하여 확인 [O]
### Step2   
* 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
* 즐겨찾기 페이지에 페이징 쿼리를 적용
    * 로그인한 사용자는 최근에 추가한 즐겨찾기만 관심이 있기에 한번에 5개의 즐겨찾기만 보고 싶다.
* 데이터베이스 이중화
