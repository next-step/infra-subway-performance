#!/bin/bash

## 변수 설정
EXECUTION_PATH=$(pwd)
SHELL_SCRIPT_PATH=$(dirname $0)
BRANCH=$1
PROFILE=$2

GIT_CLONE_SCRIPT="git clone https://github.com/next-step/infra-subway-performance.git subway"
CD_PROJECT_DIRECTORY_SCRIPT="cd subway"
CHECKOUT_SCRIPT="git checkout step2"
BUILD_SCRIPT="./gradlew clean build -x test"
FIND_PID_SCRIPT="java -jar -Dspring.profiles.active=$PROFILE build/libs/*.jar"
RUN_SCRIPT_PATH=$EXECUTION_PATH"/run_prod.sh"

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

## 조건 설정
if [[ $# -ne 2 ]]
then
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}  << 배포 스크립트 🧐 >>${txtrst}"
  echo -e "${txtgrn} $0 브랜치이름 ${txtred}{ local | prod }"
  echo -e "${txtylw}=======================================${txtrst}"
  exit
fi

function do_new_deploy() {
  echo -e ""
  echo -e ">> Clone 🏃🏃🏃🏃🏃"
  $GIT_PULL_SCRIPT
  $CD_PROJECT_DIRECTORY_SCRIPT
  $CHECKOUT_SCRIPT
  EXECUTION_PATH=$(pwd)
  ## gradle build
  build;
  ## 프로세스 pid를 찾는 명령어
  findPid;
  ## 프로세스를 종료하는 명령어
  killProcess;
  ## 프로세스 pid를 찾는 명령어
  findPid;
  ## 실행
  run;
}

function build() {
  echo -e ""
  echo -e ">> Gradle Build 🛠🛠🛠🛠🛠"
  $BUILD_SCRIPT
}

function findPid() {
  echo -e ""
  echo -e ">> Find Running Java Process ID 🔎🔎🔎🔎🔎"
  JAVA_PROCESS_ID=$(pgrep -f "$FIND_PID_SCRIPT")
  if [ $JAVA_PROCESS_ID -a -n $JAVA_PROCESS_ID  ]; then
    echo -e "Found!!"
  else
    echo -e "Not Found!!"
  fi
}

function killProcess() {
  echo -e ""
  if [ $JAVA_PROCESS_ID -a -n $JAVA_PROCESS_ID  ]; then
    echo -e ">> Kill Running Java Process 🥺🥺🥺🥺🥺"
    kill -9 $JAVA_PROCESS_ID
  fi
}

function run() {
  echo -e ""
  if [ -z $JAVA_PROCESS_ID ]; then
    echo -e ">> New Run 🏃🏃🏃🏃🏃"
    "$RUN_SCRIPT_PATH"
    exit 0
  fi
}


## 저장소 pull
do_new_deploy;