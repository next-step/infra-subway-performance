#!/bin/bash

## 변수 설정

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray


echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 스크립트 ?? >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

BRANCH=$1
PROFILE=$2
SUBWAY_HOME=/home/ubuntu/infra-subway-performance
BRANCH_DEFAULT=step2
PROFILE_DEFAULT=prod

# 저장소 clone
function clone() {
  echo -e ">> Git Clone"
  git clone https://github.com/lakey001/infra-subway-performance.git
  cd infra-subway-performance
}


## 저장소 pull
function pull() {
  echo -e ""
  echo -e ">> Pull Request ??♂? "
  git pull origin ${BRANCH:=${BRANCH_DEFAULT}}
}

## gradle build
function build() {
  echo -e ""
  echo -e ">> Gradle Build ??♂? "
  cd ${SUBWAY_HOME}
  ./gradlew clean build
}

## 프로세스 pid를 찾아저 종료
function killApp() {
  process=$(ps -ef | grep 'subway')
  pid=$(echo ${process} | cut -d " " -f2)
  if [ -n "${pid}" ]
  then
      kill -15 ${pid}
      echo -e ">> Process is killed. ${pid}"
  else
      echo -e ">> Running process not found."
  fi
}

## 애플리케이션 실행
function startApp() {
  app=$(find ./* -name "*jar" | grep "subway")
  if [ -n "${app}" ]
  then
      nohup java -jar -Dspring.profiles.active=${PROFILE:=${PROFILE_DEFAULT}} ${app} 1> /home/ubuntu/application.log 2>&1  &
      echo -e ">> Start Application"
  else
      echo -e ">> jar file is not found."
  fi
}

clone
pull
build
killApp
startApp
echo -e""
echo -e ">> Application Deploy Finished"
