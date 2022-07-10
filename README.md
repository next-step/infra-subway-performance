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
- [개선 전](ground/camp-pro/infra-subway-performance/docs/step1/before)
- [개선 후](ground/camp-pro/infra-subway-performance/docs/step1/after)

2어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- nginx Reverse Proxy 개선 - gzip 압축, cache, TLS, HTTP/2 설정
- spring boot - response 압축 적용
- index.html - js, css 최적화 적용
- redis 캐시 - 노선 조회, 최단 경로 조회
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- [보러가기](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0e2d8b415f45e9a03)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
- [cloud watch](./docs/step2/cloud_watch_dashboard.png)
- [ec2](./docs/step2/ec2.png)
- [ec2](./docs/step2/auto_scaling.png)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- [smoke](./docs/step2/smoke_result.png)
- [load](./docs/step2/load_result.png)
- [stress](./docs/step2/stress_result.png)

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- [3단계 과제](./docs/step3/step3.md)

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- [4단계 과제](./docs/step4/step4.md)
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
