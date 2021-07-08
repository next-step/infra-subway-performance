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
    * 경로 검색
- [x] 목푯값 설정 (latency, throughput, 부하 유지기간)
    * DAU : 10만 상정 (카카오 지하철 2017 DAU 1/4 수준)
    * 1명당 1일 평균 접속 수 : 3
    * 피크 시간대 집중률(최대 트래픽 / 평소 트래픽) : 10
    * 1일 총 접속수 (DAU * 1명당 1일 평균 접속 수) : 300,000
    * 1일 평균 rps (1일 총 접속수 / 86,400) = 3.47
    * 1일 최대 rps (1일 평균 rps * 피크 시간대 집중률) = 34.7
- [x] 부하 테스트 시 저장될 데이터 건수 및 크기
    * 준비 된 운영 DB 데이터
- [x] 스크립트 작성
    * docs/test/path/path-smoke.js
    * docs/test/path/path-load.js
    * docs/test/path/path-stress.js
  
- [x] 개선 전 테스트 결과
    * docs/test/path/beforeimprove/smoke-test-result.txt
    * docs/test/path/beforeimprove/load-test-result.txt
    * docs/test/path/beforeimprove/stress-test-result.txt

- [x] 개선 후 테스트 결과
  * docs/test/path/afterimprove/smoke-test-result.txt
  * docs/test/path/afterimprove/load-test-result.txt
  * docs/test/path/afterimprove/stress-test-result.txt


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
  least_conn; ## 현재 connections이 가장 적은 server로 reqeust를 분배
  server 192.168.2.48:8080 max_fails=3 fail_timeout=3s;
  server 192.168.2.118:8080 max_fails=3 fail_timeout=3s;
  }
  
      # Redirect all traffic to HTTPS
  server {
  listen 80;
  return 301 https://$host$request_uri;
  }
  
  server {
  listen 443 ssl http2;
  
      ssl_certificate /etc/letsencrypt/live/public.enemfk777.kro.kr/fullchain.pem;
      ssl_certificate_key /etc/letsencrypt/live/public.enemfk777.kro.kr/privkey.pem;
  
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
  - [x] private 서브넷에 인스턴스를 하나 더 만든 후  redis를 띄움
  - [x] prod profile에서는 private 서브넷의 redis에 접속
  - [x] 많은 엔티티를 조회 후 조합해서 응답을 만들어내는 findPath메서드에 캐시를 적용해서 redis에 저장
  

#### 1단계 코드리뷰 
- [x] 불필요한 JsonSerializer, JsonDeserializer 설정 코드 제거
- [x] prod profile의 hibernate ddl-auto level을 validate로 설정

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- [ ] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
  - [x] [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 와 같은 결과를 반환하세요
    * docs/optimize/query/codingasahobby 안에 실행 sql과 실행계획 캡처를 저장해두었습니다.
  - [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
    * docs/optimize/query/programmerhospital 안에 실행 sql과 실행계획 캡처를 저장해두었습니다.
  - [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
    * docs/optimize/query/juniorhospital 안에 실행 sql과 실행계획 캡처를 저장해두었습니다.
  - [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
    * docs/optimize/query/seoulhospitalindiatwenty 안에 실행 sql과 실행계획 캡처를 저장해두었습니다.
    
   
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

