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

- [x] 대상 페이지
    * 경로 조회
- [x] 목푯값 설정 (latency, throughput, 부하 유지기간)
    * DAU : 통계수치를 바탕으로 I사 하루 사용량 예상: 3만명 가량으로 예상
    * 사용자가 보통 5번씩 사용한다고 가정
    * 1일 총 접속수: 3만명 * 5 = 15만회
    * 150,000 / 86400 = 2rps
    * 1일 최대 rps: 2 * 100 / 10 = 20 rps
    * 사용자가 1분 내외로 사용한다고 가정.
- [x] 부하 테스트 시 저장될 데이터 건수 및 크기
    * 준비 된 운영 DB 데이터
- [x] 스크립트 작성
  * docs/testJs/smoke.js
  * docs/testJs/load.js
  * docs/testJs/stress.js  

- [x] 개선 전 테스트 결과
  * docs/testJs/beforeTest.txt

- [x] 개선 후 테스트 결과
  * docs/testJs/afterTest.txt  

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
* nginx
    - [x] gzip compress 적용
    - [x] css|js|gif|png|jpg|jpeg에 대해 nginx 캐시 적용
    - [x] http 2.0 적용

* 설정 전문
```shell    
## CPU Core에 맞는 적절한 Worker 프로세스를 할당
worker_processes auto;

events { worker_connections 10240; } ## Worker Process가 수용할 수 있는 Connection 수

http {
  gzip on; ## http 블록 수준에서 gzip 압축 활성화
  gzip_comp_level 9;
  gzip_vary on;
  gzip_types text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;

  ## Proxy 캐시 파일 경로, 메모리상 점유할 크기, 캐시 유지기간, 전체 캐시의 최대 크기 등 설정
  proxy_cache_path /tmp/nginx levels=1:2 keys_zone=mycache:10m inactive=10m max_size=200M;

  ## 캐시를 구분하기 위한 Key 규칙
  proxy_cache_key "$scheme$host$request_uri $cookie_user";


  upstream app {
    server 10.52.1.24:8080;
  }

    # Redirect all traffic to HTTPS
  server {
    listen 80;
    return 301 https://$host$request_uri;
  }

  server {
    listen 443 ssl http2;

    ssl_certificate /etc/letsencrypt/live/prodo-subway.r-e.kr/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/prodo-subway.r-e.kr/privkey.pem;

    # Disable SSL
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;

    # 통신과정에서 사용할 암호화 알고리즘
    ssl_prefer_server_ciphers on;
    ssl_ciphers ECDH+AESGCM:ECDH+AES256:ECDH+AES128:DH+3DES:!ADH:!AECDH:!MD5;

    # Enable HSTS
    # client의 browser에게 http로 어떠한 것도 load 하지 말라고 규제합니다.
    # 이를 통해 http에서 https로 redirect 되는 request를 minimize 할 수 있습니다.
    add_header Strict-Transport-Security "max-age=31536000" always;

    # SSL sessions
    ssl_session_cache shared:SSL:10m;
    ssl_session_timeout 10m;

    ## proxy_set_header :  뒷단 서버로 전송될 헤더 값을 다시 정의해주는 지시어
     proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection 'upgrade';
    proxy_set_header Host $host;

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

    location / {
      proxy_pass http://app;
    }
  }
}
```
* redis cache
    - [x] private 서버에 redis를 띄움
    - [x] prod profile에서는 private 서브넷의 redis에 접속
    - [x] 많은 엔티티를 조회 후 조합해서 응답을 만들어내는 findPath메서드에 캐시를 적용해서 redis에 저장
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

