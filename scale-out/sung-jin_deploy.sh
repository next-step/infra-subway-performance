#!/bin/bash

PROFILE=$1
BRANCH=$2

if [ -z "$PROFILE" ]
  then echo "프로필 설정은 필수입니다."
  exit
fi

if [ -z "$BRANCH" ]
  then echo "브랜치 설정은 필수입니다."
  exit
fi

git clone https://github.com/Sung-jin/infra-subway-performance.git
cd infra-subway-performance
git pull origin "$BRANCH"

SPRING_PROFILE_ACTIVE=test ./gradlew clean build
cp build/libs/*.jar scale-out/application.jar
cd scale-out

docker-compose up -d
