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
- 성능 개선 타겟 : 조회시_여러_데이터_참조_페이지 : 경로조회 요청
- threshold : p(99) < 1500
- VUs : 50

---
### smoke
- http_req_duration : 215.03ms -> 9.13ms

**_before_**

![smoke before](/step1/k6/before/step1_smoke_before.png)

**_after_**

![smoke after](/step1/k6/after/step1_smoke_after.png)



### load
- http_req_duration : 2.11s -> 48.38ms

**_before_**

![load_before](/step1/k6/before/step1_load_before.png)

**_after_**

![load_after](/step1/k6/after/step1_load_after.png)


### stress
- http_req_duration : 2.06s -> 39.83ms

**_before_**

![stress_before](/step1/k6/before/step1_stress_before.png)

**_after_**

![stress_after](/step1/k6/after/step1_stress_after.png)


**failed condition**
- 50VUs -> 250VUs

![stress_after_failed](/step1/k6/after/step1_stress_after_fail_250VUs.png)

---
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- [X] TLS, HTTP/2 설정하기
- [X] reverse proxy 개선 : gzip 압축
```text
# gzip Settings
http {
    gzip on; ## http 블록 수준에서 gzip 압축 활성화
    gzip_comp_level 9;
    gzip_vary on;
    gzip_types text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;
}
```
- [X] reverse proxy 개선 : cache
```text
http {
  ## Proxy 캐시 파일 경로, 메모리상 점유할 크기, 캐시 유지기간, 전체 캐시의 최대 크기 등 설정
  proxy_cache_path /tmp/nginx levels=1:2 keys_zone=mycache:10m inactive=10m max_size=200M;

  ## 캐시를 구분하기 위한 Key 규칙
  proxy_cache_key "$scheme$host$request_uri $cookie_user";

  server {
    location ~* \.(?:css|js|gif|png|jpg|jpeg)$ {
      proxy_pass http://app;
      
      ## 캐시 설정 적용 및 헤더에 추가
      # 캐시 존을 설정 (캐시 이름)
      proxy_cache mycache;
      # X-Proxy-Cache 헤더에 HIT, MISS, BYPASS와 같은 캐시 적중 상태정보가 설정
      add_header X-Proxy-Cache $upstream_cache_status;
      # 200 302 코드는 20분간 캐싱
      proxy_cache_valid 200 302 10m;    
      # 만료기간을 1 달로 설정
      expires 1M;
      # access log 를 찍지 않는다.
      access_log off;
    }
  }
}
```
![gzip, cache](/step1/step_1_gzip_cache.png)
![TLS, HTTP/2](/step1/step_1_http_2.png)


- [X] was 성능 개선 : Cache (redis 적용)

---

### 2단계 - 스케일 아웃
**실습**
- [X] 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
- [X] 확장자가 css인 경우 max-age를 1년, js인 경우는 no-cache, private를 설정합니다.
- [X] 모든 정적자원에 대해 no-cache, no-store 설정을 한다. 가능한가??
```text
  가능한지 불가능한지로 본다면 "가능하다" 라고 답할 수 있을 것 같습니다.
  우선 두가지 설정을 함께 쓰려고 하는 목적부터 알아봐야 할 것 같습니다.
  
  - no-store를 설정한다는 것은 Cache를 사용하지 않는다는 의미이고
  - no-cache로 설정하면 Cache는 허용하되 매번 리소스의 유효성을 판단해서 유효하다고 할 때만 Cache를 하는 것입니다. 
  
  즉, no-store를 사용한다는 것은 캐시를 무효화 하고자 하는 목저이라 생각됩니다.  
  no-store를 통해 캐시를 무효화 할 수 있겠지만 HTTP 스펙은 모든 상황을 완벽히 정의하지 못하고 디테일하게는 모호한 점들이 있다고 합니다.
  다양한 이슈로 no-store 만으로 해결하지 못하는 것들이 있을 수 있어 함께 사용하기도 합니다. 
  구글이나 네이버 등의 주요 사이트를 보면 no-cache, no-store, must-revalidate를 함께 가져가는 것을 볼 수 있습니다.
  
    Cache-Control: no-cache, no-store, must-revalidate
    Pragma: no-cache
    Expires: 0
   
  Cache-Control은 HTTP 1.1 사양을 따른다.
  Pragma는 HTTP 1.0 사양을 따른다.
  Expires 는 HTTP 1.0 및 1.1 사양을 따른다. 
  
  참고. 
    https://www.inflearn.com/questions/112647
    https://stackoverflow.com/questions/49547/how-do-we-control-web-page-caching-across-all-browsers
```
---
- [X] SpringBoot에 HTTP Cache, gzip 설정하기
- [X] Launch Template 작성하기
- [X] Auto Scaling Group 생성하기
- [X] 테스트 후 결과 공유하기

1. Launch Template 링크를 공유해주세요.
   - https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-059dc4c724258e3c0
   
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
```sh
$ stress -c 2
```
- cpu 부하 실행에 따른 cpu 사용량 증가 모니터 결과

![CPU 부하](/step2/cpu_stress.png)

- cpu 부하에 따라 추가된 인스턴스

![추가생성된 EC2](/step2/auto_scailing.png)

- Cloudwatch 

![오토스케일링](/step2/auto_scaling_monitor.png)
  
3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- 성능 개선 타겟 : 조회시_여러_데이터_참조_페이지 : 경로조회 요청

smoke
- threshold : p(99) < 1500, VUs : 50
- ![smoke](/step2/step2_smoke.png)

load
- threshold : p(99) < 1500, VUs : 50
- ![load](/step2/step2_load.png)

stress
- 성능개선 및 오토스케일 그룹 적용 전 실패 조건 : threshold : p(99) < 1500, VUs : 250
- stress 테스트 진행 조건 : threshold : p(99) < 1500, VUs : 1000
- ![stress](/step2/step2_hard_stress_1000VUs.png)

---

- [X] step2 단계에서 누락한 설정 처리
- ![종료정책](/step2/step2_terminate_policy.png)

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
