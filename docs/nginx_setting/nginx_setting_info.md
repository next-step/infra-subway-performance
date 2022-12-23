## Dockerfile, nginx, TLS 인증서
- 디렉토리

  `/home/ubuntu/nextstep/nginx_docker`
    - Dockerfile
    - nginx.conf
    - fullchain.pem
    - privkey.pem


- Dockerfile

```dockerfile
FROM nginx

COPY nginx_original.conf /etc/nginx/nginx.conf 
COPY fullchain.pem /etc/letsencrypt/live/runningmap.kro.kr/fullchain.pem
COPY privkey.pem /etc/letsencrypt/live/runningmap.kro.kr/privkey.pem
```


- DNS TXT 레코드
    - TXT record `_acme-challenge`
    - Value `RX6F-pBeX02oTIapjD3au1cfxL4ALWt3mgvibdvVC6c`