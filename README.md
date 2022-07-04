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

* 목표값 설정
    * throughput
        * 1일 평균 접속자 100만명 (서울시 하루 평균 지하철 이용자 600만명)
        * 1명당 1일 평균 접속 수 : 2번
        * 1일 총 접속 수 : 200만 (100만 * 2)
        * 피크 시간대 집중률 : 오전 (7 ~ 9), 오후 (6 ~ 8) 평소 트래픽에 6배 예상
        * 1일 평균 rps : 23.1 (200만 / 86400)
        * 1일 최대 rps : 138.6 (23.1 * 6)
    * latency
        * 목표 응답 시간 = 0.5s
        * VUser = 69 (138.6 * 0.5 / 1)
    * 부하 유지 기간
        * smoke test : 1분
        * load test : 30분
        * stress test : 7분
* 스크립트 시나리오
  * 로그인 하기 
  * 나의 정보 수정하기 
  * 경로 검색하기 
    
### Smoke Test 결과 비교 (http_req_duration)
|http_req_duration  | avg  | min  | med   | max  | p(90)  |   p(95)  |  
   |------|------|------|------|------|-------|-------|
| 개선 전  | 96.37ms | 4.35ms | 5.14ms | 568.98ms | 282.37ms |  308.67ms  | 
| 개선 후  | 36.85ms | 6.92ms | 9.35ms | 176.21ms | 98.87ms |  104.3ms  | 

### Load Test 결과 비교 (http_req_duration)
|http_req_duration  | avg  | min  | med   | max  | p(90)  |   p(95)  |  
   |------|------|------|------|------|-------|-------|
| 개선 전  | 4.08s | 3.94s | 3.5s | 18.29s | 9.39s |  10.82s  | 
| 개선 후  | 1.14s | 4.01ms | 830.97ms | 24.72s | 1.89s |  4.5s  | 

### Stress Test 결과 비교 (http_req_duration)
|http_req_duration  | avg  | min  | med   | max  | p(90)  |   p(95)  |  
   |------|------|------|------|------|-------|-------|
| 개선 전  | 13.96s | 0s | 12.23s | 39.82s | 28.98s |  32.41s  | 
| 개선 후  | 2.8s | 0s | 2.21s | 30.83s | 5.18s |  6.33s  | 

### 개선 결과
* Smoke test만 목표 응답 시간인 0.5s 만족
* Load test 평균 응답 시간 4.08s -> 1.14s, 목표치와 근사하게 접근
* Stress test 성능 개선이 가장 많이 됨 13.96s -> 2.8s
* 평균 응답 시간 3 ~ 5배 개선


## 성능 비교 : PageSpeed에서 측정
### 내 사이트 (성능 개선 전) : minzzang-subway.kro.kr

| 디바이스 | FCP  | TTI  | SI   | TBT  | LCP  |   CLS  | Score |
   |------|------|------|------|------|-------|-------|  -----   |
| MOBILE  | 14.7s | 15.3s | 14.7s | 560ms | 15.3s |  0.042  | 32 |
| PC  | 2.6s | 2.7s | 2.6s | 50ms | 2.7s |  0.004  | 68 |

### 내 사이트 (성능 개선 후) : minzzang-subway.kro.kr

| 디바이스 | FCP  | TTI  | SI   | TBT  | LCP  |   CLS  | Score |
   |------|------|------|------|------|-------|-------|  -----   |
| MOBILE  | 5.1s | 5.6s | 5.1s | 400ms | 5.6s |  0.047  | 54 |
| PC  | 1.1s | 1.2s | 1.3s | 20ms | 1.2s |  0.004  | 94 |

### 경쟁사 사이트 : 서울 교통 공사, 네이버 지도, 카카오 맵

1. 서울 교통 공사

| 디바이스 | FCP  | TTI  | SI   | TBT  | LCP  |   CLS  | Score |
   |------|------|------|------|------|-------|-------|  -----   |
| MOBILE  | 7.2s | 8.6s | 8.3s | 180ms | 8.7s |  0  | 49 |
| PC  | 1.6s | 2.0s | 2.3s | 50ms | 3.5s |  0.014  | 71 |

2. 네이버 지도

| 디바이스 | FCP  | TTI  | SI   | TBT  | LCP  |   CLS  | Score |
   |------|------|------|------|------|-------|-------|  -----   |
| MOBILE  | 2.1s | 6.6s | 5.7s | 240ms | 7.9s |  0.03  | 60 |
| PC  | 0.5s | 0.5s | 2.3s | 0ms | 1.6s |  0.006  | 90 |

3. 카카오 맵

| 디바이스 | FCP  | TTI  | SI   | TBT  | LCP  |   CLS  | Score |
   |------|------|------|------|------|-------|-------|  -----   |
| MOBILE  | 1.7s | 4.5s | 6.7s | 80ms | 6.3s |  0.005  | 68 |
| PC  | 0.5s | 1.0s | 2.6s | 0ms | 1.3s |  0.003  | 91 |

### 개선 결과
#### mobile 약 168% 성능 개선
* mobile은 아직 경쟁사 대비 점수가 낮은 상태라 추가 개선할 점이 남아 있음
#### pc 약 138% 성능 개선
* 경쟁사 중 가장 높은 점수로 개선

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
* Reverse Proxy 개선
    * gzip 압축
      * nginx.conf 파일에서 gzip압축 설정을 추가했습니다.
    * 정적파일 캐시
      * css파일, js파일, image 파일(gif,png,jpg,jpeg)을 캐시 처리했습니다.
    * http/2 설정
      * nginx.conf 파일에서 http2 설정을 추가했습니다.
* was 성능 개선
    * redis를 이용한 캐시
        * 데이터의 변경보다 조회가 많은 노선 조회와 경로 탐색을 캐시 처리하여 db 요청 수를 최소화했습니다.
    
    
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
