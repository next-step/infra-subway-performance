#!/bin/bash

## 변수 설정
BRANCH=$1

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
  echo -e ">> git clone"
  git clone https://github.com/jaesungahn91/infra-subway-performance.git
  cd ./infra-subway-performance
}

function pull() {
  echo -e ">> git pull"
  git pull origin $BRANCH
}

function build() {
  echo -e ">> build"
  ./gradlew clean build -x test
}

function start_app() {
  echo -e ">> Start server"
  nohup java -jar \
        -Dspring.profiles.active=prod \
        $(find ./* -name "*.jar" | head -n 1) \
        1>/dev/null \
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
