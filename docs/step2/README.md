## Launch Template 작성하기
### 배포 명령어
```shell
#!/bin/bash

sudo apt-get update

sudo apt -y install default-jre
sudo apt -y install openjdk-8-jre-headless

sudo apt install unzip 
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install

sudo apt-get update && \
sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common && \
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add - && \
sudo apt-key fingerprint 0EBFCD88 && \
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" && \
sudo apt-get update && \
sudo apt-get install -y docker-ce && \
sudo usermod -aG docker ubuntu && \
sudo curl -L https://github.com/docker/compose/releases/download/v2.9.0/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose && \
sudo chmod +x /usr/local/bin/docker-compose && \
sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

sudo mkdir -p /nextstep/log
sudo mkdir -p /nextstep/project/sh
#sudo mkdir -p /nextstep/sw/nginx
sudo chown ubuntu.ubuntu -R /nextstep
sudo -i -u ubuntu aws s3 cp s3://nextstep-camp-pro/earth-h-deploy.sh /nextstep/project/sh/deploy.sh
#sudo -i -u ubuntu aws s3 cp s3://nextstep-camp-pro/earth-h-nginx.tar.gz /nextstep/sw/nginx/earth-h-nginx.tar.gz
#sudo -i -u ubuntu tar zxvf /nextstep/sw/nginx/earth-h-nginx.tar.gz -C /nextstep/sw/nginx
#sudo -i -u ubuntu docker-compose -f /nextstep/sw/nginx/docker-compose.yml up -d
sudo -i -u ubuntu docker pull redis
sudo -i -u ubuntu docker run -d -p 6379:6379 redis
sudo -i -u ubuntu chmod 755 /nextstep/project/sh/deploy.sh
sudo -i -u ubuntu /bin/bash /nextstep/project/sh/deploy.sh check infra-subway-performance step2
```

## Auto Scaling Group 생성하기
- Auto Scaling Group명: earth-h-asg
- 로드밸런서명: earth-h-alb
- 접근 URL: http://www.earth-h.tk 또는 https://www.earth-h.tk
