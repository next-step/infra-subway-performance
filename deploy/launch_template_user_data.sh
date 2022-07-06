#!/bin/bash

sudo apt-get update -y
sudo apt install -y unzip default-jdk
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install

sudo -i -u ubuntu aws s3 cp s3://nextstep-camp-pro/handh0413_deploy.sh /home/ubuntu
sudo -i -u ubuntu chmod 755 /home/ubuntu/handh0413_deploy.sh
sudo -i -u ubuntu /bin/bash /home/ubuntu/handh0413_deploy.sh step2 prod
