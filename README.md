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
- 개선 전 결과
    - stress-test/before.md
    
- 개선 후 결 과
    - stress-test/after.md

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Nginx.conf 수정 후에 도커 이미지 다시 생성하여 재실행하였으며 아래 사항에 대해 Reverse Proxy 개선하였습니다.
    - CPU Core에 맞는 적절한 Worker 프로세스를 할당
    - gzip 압축 활성화
    - 정적 파일 캐시 적용
    - http 2.0 적용

- PageSpeed 데스크톱 기준 메인 페이지 테스트
  - 개선 전 : 68점 
  - 개선 후 : 92점 

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
    least_conn; ## 현재 connections이 가장 적은 server로 reqeust를 분배
    server 192.168.5.11:8080 max_fails=3 fail_timeout=3s;
    #server 172.17.0.1:8081 max_fails=3 fail_timeout=3s;
  }

    # Redirect all traffic to HTTPS
  server {
    listen 80;
    return 301 https://$host$request_uri;
  }

  server {
    listen 443 ssl http2;

    ssl_certificate /etc/letsencrypt/live/oper912-infra-subway.p-e.kr/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/oper912-infra-subway.p-e.kr/privkey.pem;

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

- redis를 이용해 cache를 적용하여 발생하는 쿼리를 줄이고 API 응답 속도를 개선하였습니다. 
  redis는 별도의 인스턴스는 생성하지 않았고 was가 있는 서버에 설치하였습니다. 

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

