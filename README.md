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
1. 성능 개선 결과를 공유해주세요 (webpagetest 테스트 결과)
   - [성능 테스트 결과](https://cultured-fir-679.notion.site/1-de9bdccf2822450c82095d132de94ae7)
   - 이미지가 많아 외부 페이지에 따로 작성 하였습니다.
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   - gzip, cache, http2, 보안 관련 개선을 진행하였는데요, 실제로 웹 페이지 테스트시 가시적으로 보여주는 성능 지표가 바뀌니 신기하다고 생각 했습니다.
   - 그런데 nginx가 이런 개선을 어떻게 코드 한 줄로 개선하지에 대한 이해가 부족해 추후에 공부가 필요한 부분이네요. 😢
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)

---

### 3단계 - WAS 개선하기

1. springboot에 HTTP Cache, gzip 설정하기

2. Data Cache 설정하기

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)
