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
- test_result 디렉토리 하위에 성능 개선 전후의 k6, grafana 결과 첨부하였습니다.

3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- reverse proxy : gzip, cache 적용, TLS, HTTP/2 설정
- was : redis cache 적용
- URL : https://cylee9409-subway.o-r.kr/

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- 배포 스크립트 : https://s3.console.aws.amazon.com/s3/object/nextstep-camp-pro?region=ap-northeast-2&prefix=cylee9409_deploy.sh
- Launch Template : https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-021bb0785927bc81b

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
- test_result_auto-scaling 디렉토리에 CloudWatch 캡쳐 추가했습니다.

시나리오에 따른 vUser
- 평소 vUser : 65
- 최대 vUSer : 75
- auto-scaling 적용 전 에러 없이 수행되는 vUser 수 (stress.js 기준) : 75
- auto-scaling 적용 후 에러 없이 수행되는 vUser 수 (stress.js 기준) : 200

기존 stress.js 부하테스트에서는 기존 웹 성능 예상에서 산출한 최대 vUser 99명을 적용한 경우, 
request block 이 여러 차례 발생하여 에러가 발생하지 않는 최대 치인 75 vUser 기준으로 테스트를 수행하였습니다.
하지만 auto-scaling 적용 후 instance 를 평소 2개 최대 4개로 설정한 기준으로 수행하였을 때 vUser 200 까지 에러없이 수행 가능해졌습니다.

- auto-scaling 적용 전
```sh


export let options = {

        stages: [

                { duration: '1m' , target: 75 },
                { duration: '5m' , target: 75 },
                { duration: '10m', target: 75 },
                { duration: '1m' , target: 0  }
        ],

        thresholds: {
                      http_req_duration: ['p(99)<500'],
                    },
};


```

- auto-scaling 적용 후
```sh


export let options = {

        stages: [

                { duration: '1m' , target: 99 },
                { duration: '5m' , target: 99 },
                { duration: '10m', target: 99 },
                { duration: '3m' , target: 150},
                { duration: '3m' , target: 200},
                { duration: '1m' , target: 0  }
        ],

        thresholds: {
                      http_req_duration: ['p(99)<500'],
                    },
};


```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- test_result_auto-scaling 디렉토리에 결과 추가했습니다.
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
