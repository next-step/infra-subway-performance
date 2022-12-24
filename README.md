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

#### 개선 전 측정
* Smoke before
![Smoke](k6/smoke/before_smoke_k6.png)
![Smoke](k6/smoke/before_smoke_grafana.png)
* Smoke after
![Smoke](k6/smoke/after_smoke_k6.png)
![Smoke](k6/smoke/after_smoke_grafana.png)

* Load before
![Load](k6/load/before_load_k6.png)
![Load](k6/load/before_load_grafana.png)
* Load After
![Load](k6/load/after_load_k6.png)
![Load](k6/load/after_load_grafana.png)

- Stress before
![Stress](k6/stress/before_stress_k6.png)
![Stress](k6/stress/before_stress_grafana.png)
- Stress before
![Stress](k6/stress/after_stress_k6.png)
![Stress](k6/stress/after_stress_grafana.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

#### 요구사항
- [x] Reverse Proxy 개선
  - [x] 개선 전 측정 
  - [x] gzip 압축
  - [x] cache
  - [x] TLS, HTTP/2 설정
- [x] WAS 성능 개선
  - [x] Spring Data Cache
  - [x] 비동기 처리

---

### 2단계 - 스케일 아웃

#### 요구사항
- [x] 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증
- [x] 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정
- [x] 모든 정적 자원에 대해 no-cache, no-store 설정이 가능한가? - 가능합니다.
- [x] springboot에 HTTP Cache, gzip 설정
- [x] Launch Template 작성
- [x] Auto Scaling Group 생성
- [x] Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.  
* [LaunchTemplate](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-03c5b3a699b0a0c38)
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
* auto scaling
![부하테스트 auto scaling](step2/auto_scaling.png)
* k6 결과
![부하테스트 k6](step2/stress_peak_k6.png)
* 인스턴스
![부하테스트 인스턴스](step2/instance.png)

```sh
// 부하테스트 스크립트
export let options = {
    thresholds: {
        http_req_duration: ['p(99)<1000'], // 99% of requests must complete below 1s
    },
    stages: [
        { duration: '5s', target: 200},
        { duration: '30s', target: 200},
        { duration: '5s', target: 3000},
        { duration: '20s', target: 2000},
        { duration: '10s', target: 2000},
        { duration: '20s', target: 2000},
        { duration: '10s', target: 3000},
        { duration: '10s', target: 2000},
        { duration: '30s', target: 1500},
        { duration: '20s', target: 1500},
        { duration: '30s', target: 1500},
    ],
};
...

```

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* smoke test
![](step2/smoke.png)
* stress test
![](step2/stress.png)
* load test
![](step2/load.png)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- 실행 쿼리
```sql
SELECT i.employee_id   AS 사원번호
     , i.last_name     AS 이름
     , i.annual_income AS 연봉
     , i.position_name AS 직급명
     , r.time AS 입출입시간
     , r.region AS 지역
     , r.record_symbol AS 입출입구분
  FROM (SELECT m.employee_id
             , e.last_name
             , s.annual_income
             , p.position_name
          FROM manager m
        INNER JOIN employee e ON e.id = m.employee_id
        INNER JOIN salary s ON s.id = e.id
        INNER JOIN position p ON p.id = m.employee_id
        INNER JOIN department d ON m.department_id = d.id
         WHERE d.note = 'active'
           AND p.position_name = 'manager'
           AND NOW() BETWEEN m.start_date AND m.end_date
           AND NOW() BETWEEN s.start_date AND s.end_date
        ORDER BY s.annual_income DESC
        LIMIT 5
) AS i
INNER JOIN record r ON r.employee_id = i.employee_id
WHERE r.record_symbol = 'O'
```
- 실행결과 이미지
![](step3/result.png)

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
