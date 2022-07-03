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
#### before
- [smoke](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/before/smoke/before_smoke_result.png)
- [smoke_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/before/smoke/before_smoke_grafana.png)

- [load](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/before/load/before_load_result.png)
- [load_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/before/load/before_load_grafana.png)


- [stress](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/before/stress/before_stress_result.png)
- [stress_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/before/stress/before_stress_grafana.png)

#### after web
- [smoke](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/smoke/after_smoke_result.png)
- [smoke_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/smoke/after_smoke_grafana.png)

- [load](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/load/after_smoke_result.png)
- [load_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/load/after_smoke_grafana.png)

- [stress](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/stress/after_stress_result.png)
- [stress_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/stress/after_stress_grafana.png)

#### after web, was
- [smoke](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/smoke/after2_smoke_result.png)
- [smoke_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/smoke/after2_smoke_grafana.png)

- [load](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/load/after2_smoke_result.png)
- [load_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/load/after2_smoke_grafana.png)

- [stress](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/stress/after2_stress_result.png)
- [stress_grafana](https://github.com/kwonyongil/infra-subway-performance/blob/step1/docs/after/stress/after2_stress_grafana.png)


- [지난 부하테스트 목표설정](https://github.com/kwonyongil/infra-subway-monitoring/blob/step2/README.md)

#### stress 테스트 기준 개선전 
- http_req_duration : 1.7s, 
- 30.28%통과
#### stress 테스트 기준 web 개선후
- http_req_duration : 1.1s,
- 97,4%통과
#### stress 테스트 기준 web, was 개선후
- http_req_duration : 29.39ms,
- 99%통과

- 1.7s -> 29.29ms


#### load 테스트 기준 개선전
- http_req_duration : 145.2ms
#### load 테스트 기준 web 개선후
- http_req_duration : 23.03ms
#### load 테스트 기준 web, was 개선후
- http_req_duration : 4.29ms



2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
#### web 개선
gzip 압축,
cache, 
TLS 적용
HTTP/2 설정

#### was 개선
레디스 캐시 적용

---

### 2단계 - 스케일 아웃

- [x] 미션1: 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
- [x]  미션2: 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
- [x]  미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?
가능합니다. 
- [참고글] https://www.inflearn.com/questions/112647
두 자원을 모두 사용하는 것에 대한 의문이 있었고
위 링크에 있는 것처럼 모호한 부분이 있어 모두 사용하는 경우가 있다고 이해하였습니다.
- ResourceHandlerRegistry 에서는 둘 중 하나만 선택 가능하도록 되어있습니다.
- Cache-Control : no-cache : 데이터는 캐시해도 되지만, 항상 원 서버에 검증하고 사용
- Cache-Control : no-store : 캐시는 클라이언트 요청 혹은 서버 응답에 관해서 어떤 것도 저장해서는 안됩니다.
- public : 응답이 어떤 캐시에 의해서든 캐시된다는 것을 나타냅니다.
- private : 응답이 단일 사용자를 위한 것이며 공유 캐시에 의해 저장되지 않아야 한다는 것을 나타냅니다. 사설 캐시는 응답을 저장할 수도 있습니다.

1. Launch Template 링크를 공유해주세요.
- [LaunchTemplate] https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-09fef10a8a405a586
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

- [cpu](https://github.com/kwonyongil/infra-subway-performance/blob/step2/docs/step2/cloudwatch_cpu.png)
- [instance](https://github.com/kwonyongil/infra-subway-performance/blob/step2/docs/step2/cloudwatch_instance.png)


```sh
$ stress -c 4
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

- [smoke](https://github.com/kwonyongil/infra-subway-performance/blob/step2/docs/step2/step2_smoke.png)
- [load](https://github.com/kwonyongil/infra-subway-performance/blob/step2/docs/step2/step2_load.png)
  - http_req_duration : 4.29ms(step1개선) -> 3.44ms
- [stress](https://github.com/kwonyongil/infra-subway-performance/blob/step2/docs/step2/step2_stress.png)
  - http_req_duration : 29.29ms(step1개선) -> 4.41ms

- [stress 800](https://github.com/kwonyongil/infra-subway-performance/blob/step2/docs/step2/step2_stress_800.png)
  - http_req_duration : 45.51ms

- [stress 1400](https://github.com/kwonyongil/infra-subway-performance/blob/step2/docs/step2/step2_stress_1400.png)
    - http_req_duration : 


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
