#!/bin/bash

## 변수 설정
txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

BASE_DIR=/home/ubuntu/infra-subway-performance
BRANCH=step2
PROFILE=prod

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}       << deploy start >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

## git clone & checkout
function clone() {
  echo -e ""
  echo -e ">> Git Clone & Checkout"
  git clone https://github.com/mins99/infra-subway-performance.git
  cd infra-subway-performance
  git checkout ${BRANCH}
}

## gradle build
function build() {
  echo -e ""
  echo -e ">> Gradle Clean & Build"
  ./gradlew clean build
}

## 프로세스 pid를 찾는 명령어
function findPid() {
  echo -e ""
  echo -e ">> Find Process ID"
  CURRENT_PID=$(pgrep -f subway)
}


## 프로세스를 종료하는 명령어
function shutdownServer() {
  echo -e ""
  echo -e ">> Shutdown Server"
  if [[ -n $CURRENT_PID ]]; then
      kill $CURRENT_PID

    echo -ne '## (20%)\r'
    sleep 1
    echo -ne '#### (40%)\r'
    sleep 1
    echo -ne '###### (60%)\r'
    sleep 1
    echo -ne '######## (80%)\r'
    sleep 1
    echo -ne '########## (100%)\r'
    sleep 1

    echo -e "Graceful Shutdown END <<"
  else
    echo -e "NO Server EXIST <<"
  fi
}

## 프로세스를 시작하는 명령어
function startServer() {
  echo -e ""
  echo -e ">> Start Server"
  nohup java -jar -Dspring.profiles.active=$PROFILE $BASE_DIR/build/libs/subway-0.0.1-SNAPSHOT.jar 1> $BASE_DIR/build/libs/log.out 2>&1 &

  sleep 3

  DEPLOY_PID=$(pgrep -f subway)
  if [ DEPLOY_PID > 0 ]; then
    echo -e "DEPLOY SUCCESS! <<"
  fi
}

clone;
build;
findPid;
shutdownServer;
startServer;
