#!/bin/bash

## 변수 설정
BRANCH=$1
PROFILE=$2

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray


echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 스크립트 🧐 >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

function clone() {
  echo -e ">> clone  🏃♂️ "
  git clone https://github.com/june2/infra-subway-performance.git
  cd ./infra-subway-performance
}

function pull() {
  echo -e ">> Pull Request 🏃♂️ "
  git pull origin $BRANCH
}

function build() {
  echo -e ">> build 🏃..."
  ./gradlew clean build -x test
}

function start_app() {
  echo -e ">> StartUp server (Profile: $PROFILE) 🏃... "
  nohup java -jar \
        -Dspring.profiles.active=$PROFILE \
        $(find ./* -name "*.jar" | head -n 1) \
        1>/home/ubuntu/subway.log \
        2>&1 \
        &
}


mkdir nextstep && cd nextstep
## 저장소 clone
clone;
## 저장소 pull
pull;
## build
build;
## start
start_app;

