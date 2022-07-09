#!/bin/bash

PROFILE=prod
BRANCH=step2

if [ "$(docker ps -q)" ]; then
    docker stop $(docker ps -a -q)
    docker rm $(docker ps -a -q)
fi

if [ "$(docker images 'subway-performance' -a -q)" ]; then
    docker rmi $(docker images 'subway-performance' -a -q)
fi

git clone https://github.com/Sung-jin/infra-subway-performance.git
cd infra-subway-performance
git pull origin "$BRANCH"

SPRING_PROFILE_ACTIVE=test ./gradlew clean build
cp build/libs/*.jar scale-out/application.jar
cd scale-out

docker build -t subway-performance --build-arg PROFILE="$PROFILE" .
docker-compose up -d
