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

- 표 우측으로 갈수록 기능 누적 추가 (*고정 경로*)

|req_duration| before  | + gzip  | + cache | + http2 | + redis | +고정 경로 캐시 
|------------|---------|---------|---------|---------|---------|--------------
| smoke      | 66.16ms | 39.83ms | 36.53ms | 38.52ms | 37.98ms | 6.2ms         
| load       | 49.42ms | 48.50ms | 49.39ms | 51.02ms | 45.87ms | 6.6ms         
| stress     | 490.78ms| 454.78ms| 451.65ms| 473.73ms| 374.18ms| 215.17ms      

- 표 우측으로 갈수록 기능 누적 추가 (*랜덤 경로*)

|req_duration| + 랜덤 경로 캐시| + 톰캣 스레드(200 > 400) |
|------------|---------------|---------------
| smoke      | 23.72ms       | 11.34ms
| load       | 19.65ms       | 16.90ms
| stress     | 454.78ms      | 203.44ms

- handh.kro.kr

| 사이트        | 구분     | FCP   | TTI   | SI    | TBT    | LCP   | CLS   |
|--------------|---------|-------|-------|-------|--------|-------|-------|
| 개선 이전     | Mobile  | 14.5s | 15.1s | 14.5s | 0.560s | 15.1s | 0.042 |
|              | Desktop | 2.7s  | 2.8s  | 2.7s  | 0.050s | 2.8s  | 0.004 |
| 개선 이후     | Mobile  | 5.2s |  5.6s |  5.2s |  0.400s | 5.6s  | 0.042 |
|              | Desktop | 1.1s  | 1.2s  | 1.4s  | 0.020s | 1.2s  | 0.004 |

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- [x] gzip 압축 수행
    - nginx 설정파일에서 gzip 옵션 추가
    - smoke 부하 테스트는 36ms 속도 개선
- [x] cache 적용
    - nginx 설정파일에서 cache 옵션 추가
    - gzip 압축때와 비교하여 상대적으로 속도 개선 없었음
- [x] http2 적용
    - nginx 설정파일에서 http2 옵션 추가
    - cache까지 적용했을 때보다 오히려 속도가 살짝 느려짐
- [x] Spring Boot Redis 적용
    - 도커를 통해 redis 실행 및 SpringBoot와 연결 수행
    - stress 테스트의 경우에는 다른 테스트와 비교하여 상대적으로 많이 개선됨
- [x] Spring Boot Tomcat 스레드 수 증가
    - 200개 → 400개
    - 전체적으로 속도 개선됨
    
#### 목표 rps 구하기 (참고 자료)

- 예상 1일 사용자 수(DAU): 450,000명 (2021년 8월 네이버지도 1,392만명 이용)
    - DAU 참고 ([링크](https://moneys.mt.co.kr/news/mwView.php?no=2021091810258035737))
    
- 피크 시간대의 집중률: 2.2
    - 2022년 5월 승/하차 인원 피크 18~19시 평균: 1,332,176명
    - 2022년 5월 승/하차 인원 시간당 평균: 623,866명
    - 집중률 참고 ([링크](https://insfiler.com/detail/rt_subway_time-0003))
    
- 1명당 1일 평균 접속 수: 6회
    - 출근 3회, 퇴근 3회
    - 대중교통 환승 시 추가 사용 고려하여 선정 ([링크](https://www.sedaily.com/NewsView/265XF8LQW8))
    
- 1일 총 접속 수: 1일 사용자 수(DAU) x 1명당 1일 평균 접속 수
    - 450,000 * 6 = 2,700,000
    
- 1일 평균 rps: 1일 총 접속 수 / 86,400
    - 2,700,000 / 86,400 = 32
    
- 1일 최대 rps: 1일 평균 rps x (최대 트래픽 / 평균 트래픽)
    - 32 * 2.2 = 71
    
- Latency: 일반적으로 50 ~ 100ms 이하로
    - 100ms
    
### VUser 구하기

- R(VUser가 1회 테스트 시 요청 보내는 수): 6 
    - 메인페이지 이동
    - 로그인 페이지 이동
    - 회원가입 페이지 이동
    - 로그인
    - 내 정보 조회
    - 최단 경로 조회
    
- T = (R * http_req_duration) (+ 1s)
    - (6 * 0.1) + 1 = 2s
    
- VUser = (목표 rps * T) / R
    - Min VUser = (32 * 2) / 6 = 10
    - Max VUser = (71 * 2) / 6 = 24  

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
