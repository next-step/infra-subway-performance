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
---
## 🚀 1단계 - 화면 응답 개선하기
### 요구사항
- 저장소를 활용하여 아래 요구사항을 해결합니다.
- README 에 있는 질문에 답을 추가한 후 PR을 보내고 리뷰요청을 합니다.
- [ ] 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선 
  - 개선 전 / 후를 직접 계측하여 확인

## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.


### 1단계 - 화면 응답 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- 성능 개선 전
  - smoke(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/1_before/smoke_K6.png)
  - load(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/1_before/load_K6.png)
  - stress(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/1_before/stress_K6.png)
- ngnix proxy 개선 후
  - smoke(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/2_proxy/smoke_K6.png)
  - load(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/2_proxy/load_K6.png)
  - stress(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/2_proxy/stress_K6.png)
- was 개선 후
  - smoke(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/3_was/smoke_K6.png)
  - load(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/3_was/load_K6.png)
  - stress(https://github.com/tyakamyz/infra-subway-performance/blob/step1/performance/3_was/stress_K6.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   - gzip 압축
   - cache 설정
   - TLS, HTTP/2 설정
   - Redis - Spring Data Cache

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
