#!/bin/bash

DOCKER_PATH=$(dirname "$0")
PROFILE=$1

if [ -z "$PROFILE" ]
  then echo "프로필 설정은 필수입니다."
  exit
fi

rm -rf "$DOCKER_PATH/application.jar"
docker stop subway-performance nginx-proxy letsencrypt-nginx-proxy
docker rm subway-performance nginx-proxy letsencrypt-nginx-proxy

if [ "$(docker images 'subway-performance' -a -q)" ]; then
    docker rmi $(docker images 'subway-performance' -a -q)
fi

cd "$DOCKER_PATH"/../
SPRING_PROFILE_ACTIVE=test ./gradlew clean build
cp build/libs/*.jar docker/application.jar

cd docker
docker build -t subway-performance --build-arg PROFILE="$PROFILE" .
docker-compose up -d

#cp nginx/sung-jin.o-r.kr_location /nginx/vhost.d
