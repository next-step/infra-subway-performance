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

**개선전**
![before-summary.png](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/before-summary.png?raw=true)
![before-metrics.png](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/before-metrics.png?raw=true)
![before-view.png](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/before-view.png?raw=true)

**개선후**
![after-summary.png](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/after-summary.png?raw=true)
![after-metrics.png](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/after-metrics.png?raw=true)
![after-view.png](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/after-view.png?raw=true)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

**nginx 설정 개선**
- gzip 적용(CPU사용량과 압축률을 고려 level 설정)
- http2 적용
- ssl 적용
- 보안관련 설정(XSS 필터 설정, 스니핑 차단, 클릭재킹 방지)
- 캐시 적용
```bash
events {}

http {
  upstream app {
    server 192.168.5.23:8080;
  }

  # Redirect all traffic to HTTPS
  server {
    listen 80;
    return 301 https://$host$request_uri;
  }

	
  # gzip compression
  gzip on;
  gzip_comp_level 6;
  gzip_vary on;
  gzip_min_length 500;
  gzip_disable "MSIE [1-6].";
  gzip_proxied expired no-cache no-store private auth;
  gzip_types text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;

  ## Proxy 캐시 파일 경로, 메모리상 점유할 크기, 캐시 유지기간, 전체 캐시의 최대 크기 등 설정
  proxy_cache_path /tmp/nginx levels=1:2 keys_zone=mycache:10m inactive=10m max_size=200M;

  ## 캐시를 구분하기 위한 Key 규칙
  proxy_cache_key "$scheme$request_method$host$request_uri $cookie_user";

  server {
    listen 443 ssl http2;
    ssl_certificate /etc/letsencrypt/live/waterfog-subway.store/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/waterfog-subway.store/privkey.pem;

    # Disable SSL
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;

    # 통신과정에서 사용할 암호화 알고리즘
    ssl_prefer_server_ciphers on;
    ssl_ciphers ECDH+AESGCM:ECDH+AES256:ECDH+AES128:DH+3DES:!ADH:!AECDH:!MD5;

    # Enable HSTS
    # client의 browser에게 http로 어떠한 것도 load 하지 말라고 규제합니다.
    # 이를 통해 http에서 https로 redirect 되는 request를 minimize 할 수 있습니다.
    add_header Strict-Transport-Security "max-age=31536000" always;

    # XSS 필터 설정
    add_header X-XSS-Protection "1; mode=block";

    # Content-Type 스니핑 차단
    add_header X-Content-Type-Options   nosniff;

    # 클릭재킹 방지
    add_header X-Frame-Options SAMEORIGIN;

    # SSL sessions
    ssl_session_cache shared:SSL:10m;
    ssl_session_timeout 10m;

    location / {
      proxy_pass http://app;
    }

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

**index.html 개선**


```html
...
  <script src="/js/vendors.js" defer></script>
  <script src="/js/main.js" defer></script>
...
``` 
async 스크립트의 경우 DOM Interative 이전에 다운로드를 마치고, 페이지 렌더링을 방해할수 있기 때문에 defer 스크립트를 사용하였다.

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-06fb24a3831482faf

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

![autoscale.JPG](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/autoscale.JPG?raw=true)

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)

**load 테스트 결과**

![autoscale-loadtest.png](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/autoscale-loadtest.png?raw=true)

**stress 테스트 결과**

![autoscale-stresstest.png](https://github.com/waterfogSW/infra-subway-performance/blob/main/page-test-img/autoscale-stresstest.png?raw=true)

---

### 3단계 - WAS 개선하기

1. springboot에 HTTP Cache, gzip 설정하기

2. Data Cache 설정하기

3. Scale out 후 성능 개선 결과를 공유해주세요 (Load, Stress 테스트 결과)
