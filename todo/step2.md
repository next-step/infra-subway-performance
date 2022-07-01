# 🚀 2단계 - 스케일 아웃 (with ASG)
## 요구사항
- springboot에 HTTP Cache, gzip 설정하기
- Launch Template 작성하기
- Auto Scaling Group 생성하기
- Smoke, Load, Stress 테스트 후 결과를 기록

### 📚 Todo List 📚
- [x] 리뷰사항 반영
- [x] SpringBoot gzip, Cache 설정
  - [x] gzip 설정
````shell
> curl -i http://localhost:8080

HTTP/1.1 200
vary: accept-encoding
Content-Type: text/html;charset=UTF-8
Content-Language: ko-KR
Content-Length: 1000
Date: Wed, 29 Jun 2022 12:13:07 GMT
````
  - [x] cache 설정
    - [x] 모든 정적 자원에 대해 no-store 와 no-cache가 같이 가능한가?
      - 헤더에 no-store, no-cache를 설정해주면 가능하다. ([참고링크](https://www.lesstif.com/software-architect/http-https-browser-caching-http-header-20775788.html))
```shell
HTTP/1.1 200 OK
Accept-Ranges: bytes
Cache-Control: max-age=31536000
Vary: Origin
Last-Modified: Thu, 30 Jun 2022 07:36:07 GMT
Content-Length: 26
Date: Thu, 30 Jun 2022 08:45:38 GMT
Content-Type: text/css
```
  - [x] LaunchTemplate 작성
```shell
#!/bin/bash

sudo apt-get update
sudo apt-get -y install unzip 
sudo apt-get -y install default-jre
sudo apt-get -y install default-jdk
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install

sudo -i -u ubuntu aws s3 cp s3://nextstep-camp-pro/mond-page_deploy.sh /home/ubuntu
sudo -i -u ubuntu chmod 755 /home/ubuntu/mond-page_deploy.sh
sudo -i -u ubuntu /bin/bash /home/ubuntu/mond-page_deploy.sh
```
  - [x] Auto Scaling Group 생성하기
    - 동적 크기 조건 : 평균 CPU 50% 유지
  - [x] smoke, stress, load 테스트 실행
  - [x] README 답 작성
