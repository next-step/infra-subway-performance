#!/bin/bash

sudo apt-get update
sudo apt-get install -y openjdk-8-jdk gradle firewalld unzip
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install

sudo firewall-cmd --zone=public --add-port=80/tcp --permanent
sudo firewall-cmd --zone=public --add-port=8088/tcp --permanent
sudo firewall-cmd --zone=public --add-port=443/tcp --permanent
sudo firewall-cmd --reload

sudo -i -u ubuntu mkdir docker
sudo -i -u ubuntu mkdir docker/certs

dockerFile=docker/Dockerfile
nginxConfig=docker/nginx.conf
fullchainPem=docker/certs/fullchain.pem
privkeyPem=docker/certs/privkey.pem

sudo -i -u ubuntu tee $dockerFile > /dev/null << EOF
FROM nginx

COPY nginx.conf /etc/nginx/nginx.conf
COPY certs/fullchain.pem /etc/letsencrypt/live/tasklet1579.p-e.kr/fullchain.pem
COPY certs/privkey.pem /etc/letsencrypt/live/tasklet1579.p-e.kr/privkey.pem
EOF

sudo -i -u ubuntu tee $nginxConfig > /dev/null << EOF
events {}

http {
    # gzip 압축
    gzip on; ## http 블록 수준에서 gzip 압축 활성화
    gzip_comp_level 9;
    gzip_vary on;
    gzip_types text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;

    ## Proxy 캐시 파일 경로, 메모리상 점유할 크기, 캐시 유지기간, 전체 캐시의 최대 크기 등 설정
    proxy_cache_path /tmp/nginx levels=1:2 keys_zone=mycache:10m inactive=10m max_size=200M;

    ## 캐시를 구분하기 위한 Key 규칙
    proxy_cache_key "\$scheme\$host\$request_uri \$cookie_user";

    upstream app {
        server 172.17.0.1:8080;
    }

    server {
        listen 8088;
        location /health {
            return 200;
        }
    }

    # Redirect all traffic to HTTPS
    server {
        listen 80;
        return 301 https://\$host\$request_uri;
    }

    server {
        listen 443 ssl http2;
        ssl_certificate /etc/letsencrypt/live/tasklet1579.p-e.kr/fullchain.pem;
        ssl_certificate_key /etc/letsencrypt/live/tasklet1579.p-e.kr/privkey.pem;

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

        location / {
            proxy_set_header X-Real-IP \$remote_addr;
            proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
            proxy_set_header Host \$http_host;
            proxy_pass http://app;
        }

        location ~* \.(?:css|js|gif|png|jpg|jpeg)\$ {
            proxy_pass http://app;

            ## 캐시 설정 적용 및 헤더에 추가
            # 캐시 존을 설정 (캐시 이름)
            proxy_cache mycache;
            # X-Proxy-Cache 헤더에 HIT, MISS, BYPASS와 같은 캐시 적중 상태정보가 설정
            add_header X-Proxy-Cache \$upstream_cache_status;
            # 200 302 코드는 20분간 캐싱
            proxy_cache_valid 200 302 10m;
            # 만료기간을 1 달로 설정
            expires 1M;
            # access log 를 찍지 않는다.
            access_log off;
        }
    }
}
EOF

sudo -i -u ubuntu tee $fullchainPem > /dev/null << EOF
-----BEGIN CERTIFICATE-----
MIIFQTCCBCmgAwIBAgISBIRjIgr/lwt9epxbh37Tw8tEMA0GCSqGSIb3DQEBCwUA
MDIxCzAJBgNVBAYTAlVTMRYwFAYDVQQKEw1MZXQncyBFbmNyeXB0MQswCQYDVQQD
EwJSMzAeFw0yMjA2MDMxMTA2MjJaFw0yMjA5MDExMTA2MjFaMB8xHTAbBgNVBAMM
FCoudGFza2xldDE1NzkucC1lLmtyMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB
CgKCAQEAzcYSzs/oe57+Qxnzmw2Xee+r6XCorngosh8vYi3yAFqXHozK7ROmb1rA
pizfIr9LjPRBtjtl91oWPU9SY/WDUyVh3gzDf2Y37wyNha0zMtjNvt2bHOl5KRNV
COkKfg+ILG1ggMSLBqNDEHCV/cx+l8+sTlRbV7Kk9WBe5/hefdZhzIVmEzc2eCKv
mP+nX6ZzN2TzGiCciLW2YpspXID2jigrhLtJEeEjrXFzzXAtxN2QX897eY0BT+JS
kZdilo2X2ya7Z5bVw1CD5Ghv2FyZwzl9hpCY0z+dQr4Zsh0XtPysNS8B5v1io03T
HPy838zXVRb/iETGBhngO3X222JNdQIDAQABo4ICYjCCAl4wDgYDVR0PAQH/BAQD
AgWgMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjAMBgNVHRMBAf8EAjAA
MB0GA1UdDgQWBBSxiN0hmj6BwBeV59Suq7WiX83gijAfBgNVHSMEGDAWgBQULrMX
t1hWy65QCUDmH6+dixTCxjBVBggrBgEFBQcBAQRJMEcwIQYIKwYBBQUHMAGGFWh0
dHA6Ly9yMy5vLmxlbmNyLm9yZzAiBggrBgEFBQcwAoYWaHR0cDovL3IzLmkubGVu
Y3Iub3JnLzAzBgNVHREELDAqghQqLnRhc2tsZXQxNTc5LnAtZS5rcoISdGFza2xl
dDE1NzkucC1lLmtyMEwGA1UdIARFMEMwCAYGZ4EMAQIBMDcGCysGAQQBgt8TAQEB
MCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly9jcHMubGV0c2VuY3J5cHQub3JnMIIBAwYK
KwYBBAHWeQIEAgSB9ASB8QDvAHYAQcjKsd8iRkoQxqE6CUKHXk4xixsD6+tLx2jw
kGKWBvYAAAGBKXWcHAAABAMARzBFAiAu4tDx+kliZMLcLcSbj7ltKllH9wro0A53
OxaYLfzBSgIhAIJWF6/n+UA8Zc+9Qzihp5BUhABwW4DDNXnF0X/mSxzKAHUAKXm+
8J45OSHwVnOfY6V35b5XfZxgCvj5TV0mXCVdx4QAAAGBKXWcAgAABAMARjBEAiBJ
QUFmNrCmJA3D0ad0Aozw//c1xBI6t3vSj3UqhMsXfgIgRyzAxmvgpNkKNcGujoGm
041dzCFTVF63iS50nXgfjLQwDQYJKoZIhvcNAQELBQADggEBAIzrDPVTBOH5nHVf
4SxYa8diPqO9/qg6nhuro2HavDya09XggxdL5O2kmH49w10MrjnT9CI9fDfzpeF6
0KolJ0T/gqmgRfwKSlJ7yCmau7hSW1mgo9NRmt3zl93GVRbciVWJk8lW0Q8jiia1
fsDajxwWXC19U2o0NeK9KM6+5ywexhu91HOBIF6d8KCkFhbJuGm1A6sYH/tsqN07
3/tAABuJcE/T2t6kKFlaAS4/KVyJnCrToWTvUDiJO1F2bbnhg7E15/WGAzSSiGlN
TM29sGVEMbnwcFWEO17n88JrXq1MUinZAViBzA/tRahYiouyTzR9C5annFtgNao1
00wFRas=
-----END CERTIFICATE-----
-----BEGIN CERTIFICATE-----
MIIFFjCCAv6gAwIBAgIRAJErCErPDBinU/bWLiWnX1owDQYJKoZIhvcNAQELBQAw
TzELMAkGA1UEBhMCVVMxKTAnBgNVBAoTIEludGVybmV0IFNlY3VyaXR5IFJlc2Vh
cmNoIEdyb3VwMRUwEwYDVQQDEwxJU1JHIFJvb3QgWDEwHhcNMjAwOTA0MDAwMDAw
WhcNMjUwOTE1MTYwMDAwWjAyMQswCQYDVQQGEwJVUzEWMBQGA1UEChMNTGV0J3Mg
RW5jcnlwdDELMAkGA1UEAxMCUjMwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK
AoIBAQC7AhUozPaglNMPEuyNVZLD+ILxmaZ6QoinXSaqtSu5xUyxr45r+XXIo9cP
R5QUVTVXjJ6oojkZ9YI8QqlObvU7wy7bjcCwXPNZOOftz2nwWgsbvsCUJCWH+jdx
sxPnHKzhm+/b5DtFUkWWqcFTzjTIUu61ru2P3mBw4qVUq7ZtDpelQDRrK9O8Zutm
NHz6a4uPVymZ+DAXXbpyb/uBxa3Shlg9F8fnCbvxK/eG3MHacV3URuPMrSXBiLxg
Z3Vms/EY96Jc5lP/Ooi2R6X/ExjqmAl3P51T+c8B5fWmcBcUr2Ok/5mzk53cU6cG
/kiFHaFpriV1uxPMUgP17VGhi9sVAgMBAAGjggEIMIIBBDAOBgNVHQ8BAf8EBAMC
AYYwHQYDVR0lBBYwFAYIKwYBBQUHAwIGCCsGAQUFBwMBMBIGA1UdEwEB/wQIMAYB
Af8CAQAwHQYDVR0OBBYEFBQusxe3WFbLrlAJQOYfr52LFMLGMB8GA1UdIwQYMBaA
FHm0WeZ7tuXkAXOACIjIGlj26ZtuMDIGCCsGAQUFBwEBBCYwJDAiBggrBgEFBQcw
AoYWaHR0cDovL3gxLmkubGVuY3Iub3JnLzAnBgNVHR8EIDAeMBygGqAYhhZodHRw
Oi8veDEuYy5sZW5jci5vcmcvMCIGA1UdIAQbMBkwCAYGZ4EMAQIBMA0GCysGAQQB
gt8TAQEBMA0GCSqGSIb3DQEBCwUAA4ICAQCFyk5HPqP3hUSFvNVneLKYY611TR6W
PTNlclQtgaDqw+34IL9fzLdwALduO/ZelN7kIJ+m74uyA+eitRY8kc607TkC53wl
ikfmZW4/RvTZ8M6UK+5UzhK8jCdLuMGYL6KvzXGRSgi3yLgjewQtCPkIVz6D2QQz
CkcheAmCJ8MqyJu5zlzyZMjAvnnAT45tRAxekrsu94sQ4egdRCnbWSDtY7kh+BIm
lJNXoB1lBMEKIq4QDUOXoRgffuDghje1WrG9ML+Hbisq/yFOGwXD9RiX8F6sw6W4
avAuvDszue5L3sz85K+EC4Y/wFVDNvZo4TYXao6Z0f+lQKc0t8DQYzk1OXVu8rp2
yJMC6alLbBfODALZvYH7n7do1AZls4I9d1P4jnkDrQoxB3UqQ9hVl3LEKQ73xF1O
yK5GhDDX8oVfGKF5u+decIsH4YaTw7mP3GFxJSqv3+0lUFJoi5Lc5da149p90Ids
hCExroL1+7mryIkXPeFM5TgO9r0rvZaBFOvV2z0gp35Z0+L4WPlbuEjN/lxPFin+
HlUjr8gRsI3qfJOQFy/9rKIJR0Y/8Omwt/8oTWgy1mdeHmmjk7j1nYsvC9JSQ6Zv
MldlTTKB3zhThV1+XWYp6rjd5JW1zbVWEkLNxE7GJThEUG3szgBVGP7pSWTUTsqX
nLRbwHOoq7hHwg==
-----END CERTIFICATE-----
-----BEGIN CERTIFICATE-----
MIIFYDCCBEigAwIBAgIQQAF3ITfU6UK47naqPGQKtzANBgkqhkiG9w0BAQsFADA/
MSQwIgYDVQQKExtEaWdpdGFsIFNpZ25hdHVyZSBUcnVzdCBDby4xFzAVBgNVBAMT
DkRTVCBSb290IENBIFgzMB4XDTIxMDEyMDE5MTQwM1oXDTI0MDkzMDE4MTQwM1ow
TzELMAkGA1UEBhMCVVMxKTAnBgNVBAoTIEludGVybmV0IFNlY3VyaXR5IFJlc2Vh
cmNoIEdyb3VwMRUwEwYDVQQDEwxJU1JHIFJvb3QgWDEwggIiMA0GCSqGSIb3DQEB
AQUAA4ICDwAwggIKAoICAQCt6CRz9BQ385ueK1coHIe+3LffOJCMbjzmV6B493XC
ov71am72AE8o295ohmxEk7axY/0UEmu/H9LqMZshftEzPLpI9d1537O4/xLxIZpL
wYqGcWlKZmZsj348cL+tKSIG8+TA5oCu4kuPt5l+lAOf00eXfJlII1PoOK5PCm+D
LtFJV4yAdLbaL9A4jXsDcCEbdfIwPPqPrt3aY6vrFk/CjhFLfs8L6P+1dy70sntK
4EwSJQxwjQMpoOFTJOwT2e4ZvxCzSow/iaNhUd6shweU9GNx7C7ib1uYgeGJXDR5
bHbvO5BieebbpJovJsXQEOEO3tkQjhb7t/eo98flAgeYjzYIlefiN5YNNnWe+w5y
sR2bvAP5SQXYgd0FtCrWQemsAXaVCg/Y39W9Eh81LygXbNKYwagJZHduRze6zqxZ
Xmidf3LWicUGQSk+WT7dJvUkyRGnWqNMQB9GoZm1pzpRboY7nn1ypxIFeFntPlF4
FQsDj43QLwWyPntKHEtzBRL8xurgUBN8Q5N0s8p0544fAQjQMNRbcTa0B7rBMDBc
SLeCO5imfWCKoqMpgsy6vYMEG6KDA0Gh1gXxG8K28Kh8hjtGqEgqiNx2mna/H2ql
PRmP6zjzZN7IKw0KKP/32+IVQtQi0Cdd4Xn+GOdwiK1O5tmLOsbdJ1Fu/7xk9TND
TwIDAQABo4IBRjCCAUIwDwYDVR0TAQH/BAUwAwEB/zAOBgNVHQ8BAf8EBAMCAQYw
SwYIKwYBBQUHAQEEPzA9MDsGCCsGAQUFBzAChi9odHRwOi8vYXBwcy5pZGVudHJ1
c3QuY29tL3Jvb3RzL2RzdHJvb3RjYXgzLnA3YzAfBgNVHSMEGDAWgBTEp7Gkeyxx
+tvhS5B1/8QVYIWJEDBUBgNVHSAETTBLMAgGBmeBDAECATA/BgsrBgEEAYLfEwEB
ATAwMC4GCCsGAQUFBwIBFiJodHRwOi8vY3BzLnJvb3QteDEubGV0c2VuY3J5cHQu
b3JnMDwGA1UdHwQ1MDMwMaAvoC2GK2h0dHA6Ly9jcmwuaWRlbnRydXN0LmNvbS9E
U1RST09UQ0FYM0NSTC5jcmwwHQYDVR0OBBYEFHm0WeZ7tuXkAXOACIjIGlj26Ztu
MA0GCSqGSIb3DQEBCwUAA4IBAQAKcwBslm7/DlLQrt2M51oGrS+o44+/yQoDFVDC
5WxCu2+b9LRPwkSICHXM6webFGJueN7sJ7o5XPWioW5WlHAQU7G75K/QosMrAdSW
9MUgNTP52GE24HGNtLi1qoJFlcDyqSMo59ahy2cI2qBDLKobkx/J3vWraV0T9VuG
WCLKTVXkcGdtwlfFRjlBz4pYg1htmf5X6DYO8A4jqv2Il9DjXA6USbW1FzXSLr9O
he8Y4IWS6wY7bCkjCWDcRQJMEhg76fsO3txE+FiYruq9RUWhiF1myv4Q6W+CyBFC
Dfvp7OOGAN6dEOM4+qR9sdjoSYKEBpsr6GtPAQw4dy753ec5
-----END CERTIFICATE-----
EOF

sudo -i -u ubuntu tee $privkeyPem > /dev/null << EOF
-----BEGIN PRIVATE KEY-----
MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDNxhLOz+h7nv5D
GfObDZd576vpcKiueCiyHy9iLfIAWpcejMrtE6ZvWsCmLN8iv0uM9EG2O2X3WhY9
T1Jj9YNTJWHeDMN/ZjfvDI2FrTMy2M2+3Zsc6XkpE1UI6Qp+D4gsbWCAxIsGo0MQ
cJX9zH6Xz6xOVFtXsqT1YF7n+F591mHMhWYTNzZ4Iq+Y/6dfpnM3ZPMaIJyItbZi
mylcgPaOKCuEu0kR4SOtcXPNcC3E3ZBfz3t5jQFP4lKRl2KWjZfbJrtnltXDUIPk
aG/YXJnDOX2GkJjTP51CvhmyHRe0/Kw1LwHm/WKjTdMc/LzfzNdVFv+IRMYGGeA7
dfbbYk11AgMBAAECggEAFlInFiH9YPOFNzgocahOxx5VcpQS6CIGoBO76xV6y8kH
3Xt+Q6s/AZNBlIUHi1he1KJmQmM9E1DFWs8w68DlBu7mJ9UQm9A1vwuIiniYNirI
XqzhN4in6Om87qOAzN3YcqFGt5EaV5T8IFtmGcrF7KfNVA3YfrHVLPzQLq+FaHbX
/y8UfQLEgr3/mxjI39Wb//vL9ASJj0HFUky+/NYZ9GexgH72w16TbK+9qe+T3YC+
aCnWWE+iN8AHIFPeT1vzF/pTmQQo4lCifYrmAKDQzREWebQlxs04pCPnY09K1uKT
z2IojZ6u7D1rgo/xI6gAt+rNqqgHSQ2J5nUu1YGWgQKBgQDuH/nd/u8jYlbSIO07
BQkKqjzQKcvS7s0QhcqgCP9yGPAgIEjL7QuOiOnPS7lAgdAuAvZYoipQCiQM4V5k
74ekTuemZ3doFzUvoGkC1BcxW5216hPc9zL22eHJsFTF0Y28W1B7dFc3oeX2krGl
72J9TQjryYDTX78kGfH4h8i8VQKBgQDdOGhlnRzqOwyQVKjOEI855W0inxLLjZ4D
Tsk3RdrLof4tvWB5YwM3Lm1FKXrLzhqICICaf+Fb6cySqprqvRak3fm9oE6bIv7Q
0flSBpUVgykjX8AzWElP5h9F/QEs5ie3//g9NchSlYFdthDoNjL0KPxpLpbvxBsI
niVGW/BsoQKBgBgfVyGdBL6x5JeuzDlxXiQdcMGpqkM4+78Tvp4Nq0r0qcanXpiB
7+zFg+IDCsY1IFC47hm2e9DV5icD4ZsaBMSpVaAu1RLPXwzvFBQ8Rgky/bwC9XYp
+Ji3I79ZxIyxmMke+v02oOs61F/xJlvn7zNATYoNRKalCWjv0+pYXwZtAoGAOxvC
UTxMqI1EiyADxqOIEXkq0vBcDkV1rf6UJw+kKXrMNp5+M5DwIANosO0nYWNdlXFo
I1/98BglUB8Dq6sAbObevvshZbRRD6y7CF634PqduFTyPwXc+NtNCd9TPBL3E+Py
CTDgqKhH26k1FUa+EJ0O7G/FZqUocAIJhaY1NGECgYALzbgfZsfUusp8z4G5/l5G
tLdpke7QgeH0dd9ZyDkR4jvAOold5PaKbZdnF2w1jERupXLiT43GJqa816CIjvku
IOelxVvVkYP84iiR55r7yQ014a8xgEa4iHfyaXo0Mnec8oppQAxxDdN04M2/PxyC
nFX2PKtm/uz0HxIG/fuz1A==
-----END PRIVATE KEY-----
EOF

sudo apt-get install -y apt-transport-https ca-certificates curl gnupg lsb-release
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --batch --yes --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install -y docker-ce docker-ce-cli containerd.io
sudo docker build -t nextstep/reverse-proxy:0.0.1 /home/ubuntu/docker
sudo docker run -d -p 80:80 -p 8088:8088 -p 443:443 --name proxy -v /var/log/nginx:/var/log/nginx nextstep/reverse-proxy:0.0.1

sudo -i -u ubuntu aws s3 cp s3://nextstep-camp-pro/tasklet1579-deploy.sh /home/ubuntu
sudo -i -u ubuntu chmod 755 /home/ubuntu/tasklet1579-deploy.sh
sudo -i -u ubuntu /bin/bash /home/ubuntu/tasklet1579-deploy.sh step2 prod
