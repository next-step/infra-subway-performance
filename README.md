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
- 개선전
![before_1.png](capture%2Fbefore_1.png)
![before_2.png](capture%2Fbefore_2.png)

- 개선후
![after_1.png](capture%2Fafter_1.png)
![after_2.png](capture%2Fafter_2.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- 응답에 gzip 적용
- 정적파일 캐시 적용
- HTTP/2 적용
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- [launch template](https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-054e16f3899dcf877)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```
![cpu.png](capture%2Fcpu.png)

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)

- load
  - 인스턴스 1대
    ![load_1_1.png](capture%2Fload_1_1.png)
    ![load_1_2.png](capture%2Fload_1_2.png)
  - 인스턴스 5대
    ![load_5_1.png](capture%2Fload_5_1.png)
    ![load_5_2.png](capture%2Fload_5_2.png)
- stress
  - 인스턴스 1대
    ![stress_1_1.png](capture%2Fstress_1_1.png)
    ![stress_1_2.png](capture%2Fstress_1_2.png)
  - 인스턴스 5대
    ![stress_5_1.png](capture%2Fstress_5_1.png)
    ![stress_5_2.png](capture%2Fstress_5_2.png)

### 3단계 - WAS 개선하기

1. springboot에 HTTP Cache, gzip 설정하기

2. Data Cache 설정하기

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)
- load
  ![load_cahce_1_1.png](capture%2Fload_cahce_1_1.png)
  ![load_cahce_1_2.png](capture%2Fload_cahce_1_2.png)
- stress
  ![stress_cache_1_1.png](capture%2Fstress_cache_1_1.png)
  ![stress_cache_1_2.png](capture%2Fstress_cache_1_2.png)