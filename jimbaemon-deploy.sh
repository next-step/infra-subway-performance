#!/bin/bash

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

WEB_ROOT_PATH='/home/ubuntu/infra-subway-performance'
GIT_URL=$1
BRANCH=$2

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 스크립트 🧐  >>${txtrst}"
echo -e "${txtgrn}  환경 :${txtrst}"
echo -e "${txtgrn}  - BRANCH: $BRANCH${txtrst}"
echo -e "${txtgrn}  - GIT_URL: $GIT_URL${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

## 저장소 clone
function clone(){
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}>> Git Clone 🏃♂️ ${txtrst}"
  git clone -b $BRANCH --single-branch $GIT_URL
  echo -e "${txtgrn}>> Git Clone END ✅️ ${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
}

## gradle build
function build(){
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}>> Start Build Gradle!! 🛠🛠${txtrst}"
  sh $WEB_ROOT_PATH/gradlew -p $WEB_ROOT_PATH --console=plain clean build
  echo -e "${txtgrn}>> Build Gradle!! END  ✅️ ${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
}

## 새로 빌드한 어플리케이션 시작하는 명령어
function start_app(){
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}>> Start New Application 🍀🍀🍀${txtrst}"
  nohup java -jar -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=local $(find $WEB_ROOT_PATH/build -name "*jar") 1> $HOME/application.log 2>&1  &
  echo -e "${txtgrn}>> Start New Application Successfully!!!  ✅️ ${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
}

#함수 실행
clone;
build;
start_app;
