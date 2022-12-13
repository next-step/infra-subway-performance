## reverse proxy
### nginx 설정을 통한 성능 개선
```nginx configuration
events {}

http {

	gzip 		on; # http 블록 수준에서 gzip 압축 활성화(전역적으로 활성화하기 위함)
	gzip_comp_level 9; # 1 ~ 9 사이로 설정할 수 있으며, 압축율은 가장 높지만 압축 속도는 느림
	gzip_vary 	on;
	gzip_types 	text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml
			text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;

	## Proxy 캐시 파일 경로, 메모리상 점유할 크기, 캐시 유지기간, 전체 캐시의 최대 크기 등 설정
	## levels=1:2 :: 첫 번째 단계의 디렉토리는 한글자, 두 번째 단계의 디렉토리는 두글자로 명명
	## inactive=10m :: 10분간 접근이 없는 캐시 파일 삭제
	proxy_cache_path /tmp/nginx levels=1:2 keys_zone=mycache:10m inactive=10m max_size=200M;

 	## 캐시를 구분하기 위한 Key 규칙
	## 하기와 같이 작성할 경우, 유저 쿠키 별로 캐시 구성
	proxy_cache_key "$scheme$host$request_uri $cookie_user";


	upstream subway {
		server 172.20.0.4:8080;
	}

	server {
		listen 80;
		server_name earth-h.tk;

		return 301 https://$host$request_uri;
	}

	server {
		listen 443 ssl http2;
		server_name earth-h.tk;

		ssl_certificate /etc/letsencrypt/live/earth-h.tk/fullchain.pem;
		ssl_certificate_key /etc/letsencrypt/live/earth-h.tk/privkey.pem;
		ssl_protocols TLSv1.1 TLSv1.2;
		ssl_prefer_server_ciphers on;
		ssl_ciphers ECDH+AESGCM:ECDH+AES256:ECDH+AES128:DH+3DES:!ADH:!AECDH:!MD5;
		ssl_session_cache shared:SSL:10m;
		ssl_session_timeout 10m;

		# Enable HSTS
		# client의 browser에게 http로 어떠한 것도 load 하지 말라고 규제
		# 이를 통해 http에서 https로 redirect되는 request를 minimize할 수 있음
		add_header Strict-Transport-Security "max-age=31536000" always;

	        location ~* \.(?:css|js|gif|png|jpg|jpeg)$ {
	            	proxy_pass http://subway;

	            	## 캐시 설정 적용 및 헤더에 추가
	            	# 캐시 존을 설정 (캐시 이름)
	            	proxy_cache mycache;

	            	# X-Proxy-Cache 헤더에 HIT, MISS, BYPASS와 같은 캐시 적중 상태정보가 설정
	            	add_header X-Proxy-Cache $upstream_cache_status;

	            	# 200 302 코드는 20분간 캐싱
	            	proxy_cache_valid 200 302 10m;

	            	# 만료기간을 한달로 설정
	            	expires 1M;

	            	# access log 를 찍지 않는다.
	            	access_log off;
	        }

		location / {
			proxy_pass http://subway;
		}

		access_log /var/log/nginx/access.log;
		error_log /var/log/nginx/error.log;
	}
}
```
- gzip 압축을 통해 정적 컨텐츠 크기 줄임
  - 텍스트 기반 파일(js, css, html..)의 인코딩 및 전송 크기 최적화 
  - 1 ~ 9단계 중 가장 높은 단계로 압축함 (높은 단계로 압축하면 조금 느릴 수 있다고 함)
- cache 설정
  - 유저 쿠키별로 캐시 구성하며, 10분간 접근 하지 않은 캐시는 제거됨
  - 정적 컨텐츠 대상으로 캐시하며, 해당 내용은 access log를 찍지 않음
  - X-Proxy-Cache 헤더를 추가함으로써 크롬에서 개발자도구로 https://earth-h.tk 접근 시, request header에서 캐시 적중여부 확인 가능
- HTTP/1.1 대신 HTTP/2 사용
  - 하나의 TCP 연결을 통해 다수의 클라이언트 요청과 서버 응답이 비동기 방식으로 이루어질 수 있음
    - 요청과 응답이 message 단위로 구성되고, 이는 frame 등으로 나뉘어 stream 구조를 취하기 때문

