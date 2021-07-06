
```
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
    server 10.51.1.8:8080 max_fails=3 fail_timeout=3s;
    server 10.51.1.19:8080 max_fails=3 fail_timeout=3s;
  }

    # Redirect all traffic to HTTPS
  server {
    listen 80;
    return 301 https://$host$request_uri;
  }

  server {
    listen 443 ssl http2;

    ssl_certificate /etc/letsencrypt/live/happy-subway.p-e.kr/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/happy-subway.p-e.kr/privkey.pem;

```
