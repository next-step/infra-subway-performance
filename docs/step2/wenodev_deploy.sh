#!/bin/bash

## 변수 설정

txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green

BRANCH=$1

function deploy_message() {
  echo -e "${txtylw}==============================================="
  echo -e "${txtgrn}  << Deploy Start... >>${txtgrn}"
  echo -e "${txtylw}==============================================="
}

function clone() {
    echo -e "git clone..."
    git clone https://github.com/wenodev/infra-subway-performance
    cd infra-subway-performance
}

function pull() {
    echo -e ""
    echo -e "${BRANCH} Pull Request ..."
    git pull origin ${BRANCH}
}

function find_pid() {
  echo -e ""
  echo -e ">> find PID 🏃..."
  PID=`ps -ef | grep -v "grep" | jps | grep "subway" | awk '{print $1}'`
}

function kill_pid() {
  if [ "$PID" != "" ]
  then
    echo -e ""
    echo -e ">> Kill ${PID} 🏃..."
    kill ${PID}
  else
    echo -e "PID is Empty!"
  fi
}

function build() {
  echo -e ""
  echo -e "${txtpur}>> Build ..."
  ./gradlew clean build -x test
}

function run() {
    echo -e " Application Run"
    nohup java -jar -Dspring.profiles.active=prod build/libs/subway-0.0.1-SNAPSHOT.jar 1> /dev/null 2>&1  &
}

deploy_message
clone
pull
build
find_pid
kill_pid
run
echo -e "Deploy success!"

