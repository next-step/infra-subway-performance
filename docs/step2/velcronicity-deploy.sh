#!/bin/bash

## 변수 설정

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

BRANCH=$1
PROFILE=$2
APP_NAME=velcronicity_subway
PID=$(pgrep -f ${APP_NAME})
JAR_PATH=/build/libs

function clone() {
  echo -e ""
  echo -e "${txtgrn}[clone] git clone https://github.com/velcronicity/infra-subway-performance.git${txtrst}"
  git clone https://github.com/velcronicity/infra-subway-performance.git
  cd infra-subway-performance
}

function pull() {
  echo -e ""
  echo -e "${txtgrn}[pull] git pull origin ${BRANCH}${txtrst}"
  git pull origin ${BRANCH}
}

function build() {
  echo -e ""
  echo -e "${txtgrn}[build] ./gradlew clean build${txtrst}"
  ./gradlew clean build
}

function stop() {
  echo -e ""
  if [ -z "${PID}" ]; then
    echo -e "${txtgrn}[stop] not running${txtrst}"
  else
    echo -e "${txtgrn}[stop] kill -15 ${PID}${txtrst}"
    while $(kill -15 ${PID} 2>/dev/null); do
      sleep 1
    done
  fi
}

function start() {
  EXECUTION_PATH=$(pwd)
  echo -e ""
  echo -e "${txtgrn}[start] nohup java -DAPP_NAME=${APP_NAME} -Dspring.profiles.active=${PROFILE} -jar ${EXECUTION_PATH}${JAR_PATH}/*.jar 1>subway.log 2>&1 &${txtrst}"
  nohup java -DAPP_NAME=${APP_NAME} -Dspring.profiles.active=${PROFILE} -Dserver.port=8081 -jar ${EXECUTION_PATH}${JAR_PATH}/*.jar 1>subway.log 2>&1 &
  PID=$(pgrep -f ${APP_NAME})
  ps -ef | grep ${PID} | grep -v grep
}

function deploy() {
  clone
  pull
  build
  stop
  start
}

if [[ $# -ne 2 ]]; then
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}  << 스크립트 🧐 >>${txtrst}"
  echo -e ""
  echo -e "${txtgrn} $0 브랜치이름 ${txtred}{ prod | dev }"
  echo -e "${txtylw}=======================================${txtrst}"
  exit
fi

echo -e ""
echo -e "${txtgrn} [branch]: $BRANCH ${txtsrt}"
echo -e "${txtgrn} [profile]: $PROFILE ${txtsrt}"

deploy
echo -e ""
