#!/bin/bash

## 변수 설정
FIND_PID_SCRIPT="java -jar -Dspring.profiles.active=prod build/libs/*.jar"

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

function do_new_deploy() {
  echo -e ""
  echo -e ">> Clone 🏃🏃🏃🏃🏃"
  git clone https://github.com/next-step/infra-subway-performance.git subway
  cd subway
  git checkout step2

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
  ./gradlew clean build -x test
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
  mkdir logs
  nohup java -jar -Dspring.profiles.active=prod ./build/libs/*.jar 1> ./logs/prod_exec.log 2>&1  &
}

## 저장소 clone
do_new_deploy;