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

대상 사이트 : june2-nextstep.kro.kr

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

- 성능 개선 미적용
  - [smoke](https://github.com/june2/infra-subway-performance/blob/step1/docs/smoke/1.png)
  - [load](https://github.com/june2/infra-subway-performance/blob/step1/docs/load/1.png)
  - [stress](https://github.com/june2/infra-subway-performance/blob/step1/docs/stress/1.png)
- web proxy 개선
  - [smoke](https://github.com/june2/infra-subway-performance/blob/step1/docs/smoke/2.web.png)
  - [load](https://github.com/june2/infra-subway-performance/blob/step1/docs/load/2.web.png)
  - [stress](https://github.com/june2/infra-subway-performance/blob/step1/docs/stress/2.web.png)
- web + was 개선
  - [smoke](https://github.com/june2/infra-subway-performance/blob/step1/docs/smoke/3.web.was.png)
  - [load](https://github.com/june2/infra-subway-performance/blob/step1/docs/load/3.web.was.png)
  - [stress](https://github.com/june2/infra-subway-performance/blob/step1/docs/stress/3.web.was.png)


3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

   a. Reverse Proxy 개선
     - gzip 압축, cache, TLS, HTTP/2 설정

   b. WAS 성능 개선
     - Redis Spring Data Cache

 - 결과
   - web(nginx) 성능 개선으로 웹페이지 로딩 속도 감축
   - was(redis-cache) 데이터 응답 실패율 감소
  |         | FCP  |  TTI |  SI  |  TBT  |  LCP   |   CLS   |   Score  |
  |---------|------|------|------|-------|--------| ------- |  :-----: |
  | 미적용    |2.7s  | 2.8  | 2.7s | 70ms  |  2.8s  |  0.004  |    67    |
  | web개선  |1.2s  | 1.3  | 1.6s | 50ms  |  1.3s  |  0.004  |    92    |
  | was개선  |1.2s  | 1.2  | 1.7s | 50ms  |  1.2s  |  0.004  |    92    |
     

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
