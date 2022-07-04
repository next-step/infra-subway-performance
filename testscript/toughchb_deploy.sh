#!/bin/bash

## 변수 설정

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 배포 스크립트 $0 🧐 >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

BRANCH=step2
PROFILE=prod
BASE_DIR=/home/ubuntu/infra-subway-performance

# 저장소 clone
function init() {
         echo -e "git cloning"
        git clone https://github.com/toughchb/infra-subway-performance.git
}

## 저장소 pull
function pull() {
  echo -e ""
  echo -e ">> Pull Request 🏃♂️ "
  git pull origin ${BRANCH}
  git checkout ${BRANCH}
}

## gradle build
function gradle_build() {
  echo -e ""
  echo -e "cd ${BASE_DIR}"
  cd ${BASE_DIR}
  echo -e ">> Gradle Build 🏃♂️ "
  ./gradlew clean build -x test
}

## 프로세스 pid 검색
function get_pid() {
    echo "$(ps -ef | grep 'subway' | grep -v 'grep' | awk '{ printf $2 }')"
}

## 프로세스를 종료

function kill_app() {
  local pid="$1"
  echo -e ""
  if [[ -z ${pid} ]]; then
    echo ">> Not Exist PID "
  else
    echo "Kill Application PID: ${pid}"
    kill -15 ${pid}
  fi
}

## 어플리케이션 파일 이름 검색
function find_app_name() {
    echo "$(find ./* -name "*.jar" | grep "subway")"
}

## 어플리케이션을 실행
function start_app() {
  local appName="$1"
   echo -e ""
   echo "Start Application "
   nohup java -jar \
          -Dspring.profiles.active=${PROFILE} \
          ${appName} \
          1>application.log \
          2>&1 \
          &
}

init
pull
gradle_build
PID=$(get_pid)
kill_app "$PID"
APP_NAME=$(find_app_name)
start_app "$APP_NAME"

echo -e "Deploy Finished"
