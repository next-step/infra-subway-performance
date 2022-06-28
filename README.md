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

- [X] was 성능 개선 : Cache (redis 적용)

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
