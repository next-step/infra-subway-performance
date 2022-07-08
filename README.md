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

Before
- [Smoke](https://github.com/tasklet1579/infra-subway-performance/blob/step1/file/k6/before/smoke) [Load](https://github.com/tasklet1579/infra-subway-performance/blob/step1/file/k6/before/load) [Stress](https://github.com/tasklet1579/infra-subway-performance/blob/step1/file/k6/before/stress)

Nginx
- [Smoke](https://github.com/tasklet1579/infra-subway-performance/blob/step1/file/k6/nginx/smoke) [Load](https://github.com/tasklet1579/infra-subway-performance/blob/step1/file/k6/nginx/load) [Stress](https://github.com/tasklet1579/infra-subway-performance/blob/step1/file/k6/nginx/stress)

Redis
- [Load](https://github.com/tasklet1579/infra-subway-performance/blob/step1/file/k6/cache/load) [Stress](https://github.com/tasklet1579/infra-subway-performance/blob/step1/file/k6/cache/stress)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

Nginx
- gzip 압축, cache, TLS, HTTP/2 설정

Redis
- 지하철역 조회

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

[링크](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-00ebdcb2aca0fdbe3)

3. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)


4. 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?

CacheControl에서 noCache와 noStore 설정은 모두 할 수 없도록 되어 있습니다.

no-cache : 클라이언트가 서버에게 매번 캐시의 유효성 확인을 요청함

no-store : 캐시를 비활성하고 이것은 비공개 그리고 공유 캐시에 적용됨
- 비휘발성 스토리지에 저장되는 것은 아니지만 완벽하게 privacy를 보장하는 것은 아님

인터넷 상에서는 모두 설정해주는 것이 좋다고 나와있는데 실제 코드에서는 왜 양립할 수 없는지 의문이 들었습니다.

하나. HTTP 스펙을 구현하는 시점에 설계 오류가 있었다.

둘. 기타 옵션을 적절히 사용해서 확실한 캐시 무효화 효과를 만들어낸다.

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

[쿼리](https://github.com/tasklet1579/infra-subway-performance/blob/step2/file/sql/Manager.sql)

[실행계획](https://github.com/tasklet1579/infra-subway-performance/blob/step2/file/sql/image/Manager_Plan.PNG)

실행시간

- 첫번째 : 1.51s
- 두번째 : 324ms
- 세번째 : 264ms


---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
