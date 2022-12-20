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
* `/k6/origin` - 기존 어플리케이션
* `/k6/reverse-proxy` - 리버스 프록시 개선
* `/k6/redis-cache` - 레디스 캐시 설정
* `/k6/test-script` - k6 테스트 스크립트

- Reverse Proxy 개선을 통해 웹페이지 속도가 향상되었습니다.
- WAS 성능 개선을 통해 http_req_duration이 향상되었습니다.

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Reverse Proxy 개선
  - gzip 압축 (정적 컨텐츠 파일 압축)
  - cache 설정 (정적 컨텐츠 대상으로 유저의 쿠키를 통해 캐싱)
  - TLS, HTTP/2 설정
- WAS 성능 개선
  - Redis(In-Memory DB)를 사용하여 각 조회 결과 캐싱

---

### 2단계 - 스케일 아웃

* `cache-control` - 헤더에 설정하여 HTTP Caching 설정을 한다.
  * `no-store` - Cache를 사용하지 않는다는 의미
  * `no-cache` - `max-age=0`과 동일한 의미, Cache를 사용하며, 요청시 유효성 검사를 통해 올바른 경우에만 Cache를 사용
  * `max-age` - Cache의 유효기간을 설정


  * `public`은 모든 사용자와 중간 서버가 캐시를 저장할 수 있음을 나타내고, `private`은 엔드포인트의 사용자 브라우저만 캐시를 저장할 수 있음 나타낸다.
  * `Etag` - 리소스의 특정 버전에 대한 식별자로, HTTP 컨텐츠가 바뀌었는지를 검사할 수 있는 태그

- [x] 미션1: 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
- [x] 미션2: 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
- [x] 미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?
  - HTTP 스펙이 모든 상황을 완벽하게 제어하는 것이 아니기 때문에 가능합니다.
  - 오래된 브라우저, HTTP 1.0, 버그, 수 많은 프록시 캐시 업체들과 그에 대한 구현방법등이 다르기 때문에 대처하기 위해 함께 사용될 수도 있습니다.

- [x] springboot에 HTTP Cache, gzip 설정하기
  - [x] HTTP Cache 설정하기
  - [x] gzip 설정하기
- [x] Launch Template 작성하기
- [ ] Auto Scaling Group 생성하기
- [ ] Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0a86898e1489db9b9

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

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
