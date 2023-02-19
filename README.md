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
- mission/step1 폴더에 정리했습니다.

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- gzip 적용
  - 메인 페이지 로딩속도가 1054ms 에서 1039ms 근소하게 개선
  - main.js, vendoer.js 가 각각 3008ms, 4360ms 에서 1500ms, 2172ms 로 약 2배 개선
  - optimization 점수가 F 에서 A 로 향상
- cache 적용
  - cache static content 점수가 D 에서 A 로 향상
  - 정적 컨텐츠 로딩 속도가 근소하게 향상
- http2 적용
  - 정적 컨텐츠 로딩 속도가 특별하게 향상되지는 않았습니다.
- 정적 파일 경량화
  - javascript async 속성을 추가했지만 특별한 향상은 없었습니다. 
- 결과적으로 보면 gzip 을 적용한 효과가 가장 컸던것 같습니다.

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0c662332a3f44c183

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```
- mission/step2 폴더에 정리했습니다.

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)

- mission/step2 폴더에 정리했습니다.
- smoke test 에서는 오히려 이전보다 속도가 조금 느렸지만 load, stress 테스트에서는 유의미한 속도 향상이 있었고,
이전 테스트에서는 stress 테스트에서는 실패하는 케이스가 있었는데 auto scale 설정 후 stress 테스트도 모두 통과했습니다.

### 3단계 - WAS 개선하기

1. springboot에 HTTP Cache, gzip 설정하기

2. Data Cache 설정하기
- asg 설정에 따라 애플리케이션 인스턴스가 여러대 뜰 수 있으므로 로컬 캐시보다는 redis cache 를 적용했습니다.

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)
- mission/step3 폴더에 정리했습니다.