#!/bin/bash

## set variable

txtrst='\033[1;37m' # White
txtylw='\033[1;33m' # Yellow
txtgrn='\033[1;32m' # Green

PROJECT_PATH='/nextstep/project/'
LOG_PATH='/nextstep/log/'
FUNC_NAME=$1
REPO_NAME=$2
BRANCH_NAME=$3
CURRENT_TIME=$(date "+%Y%m%d%H%M")
JAR_NAME=''
PID=''

FIX_LENGTH_FUNC_NAME=$(printf "%5.5s" "$FUNC_NAME")

begin() {
  echo "\n${txtylw}------------------------${txtrst}"
  echo "${txtgrn}| << ${FIX_LENGTH_FUNC_NAME} START 🧐 >> |${txtrst}"
  echo "${txtylw}------------------------${txtrst}"
}

info() {
  echo "\n${txtylw}---------------------------------------------------${txtrst}"
  echo " 1st param(function name) = ${PROJECT_PATH}"
  echo " 2nd param(repository name) = ${REPO_NAME}"
  echo " 3rd param(branch name) = ${BRANCH_NAME}"
  echo "${txtylw}---------------------------------------------------${txtrst}"
}

guide() {
  echo "${txtylw}============================================================================================================${txtrst}"
  echo " 1st param(function name) = guide, clone, pull, build, start, stop 중 하나"
  echo " 2st param(repository name) = ${PROJECT_PATH} 내에 존재하는 github repository명(예: infra-subway-deploy)"
  echo " 3rd param(branch name) = 2st param 내 존재하는 대상 branch명(예: earth-h)"
  echo "${txtylw}============================================================================================================${txtrst}"
}

clone() {
  info;

  echo "\n >> Clone 🏃  \n >> Path: https://github.com/earth-h/${REPO_NAME}.git"
  cd ${PROJECT_PATH} && git clone https://github.com/earth-h/${REPO_NAME}.git
  cd ${REPO_NAME} && git checkout origin/${BRANCH_NAME}
}

pull() {
  info;

  echo "\n >> Pull Request 🏃♂️ \n >> Path: ${PROJECT_PATH}${REPO_NAME}\n"
  cd ${PROJECT_PATH}${REPO_NAME} && git pull origin ${BRANCH_NAME}
}

build() {
  info;

  echo "\n >> Build Project 🏃♂️ \n >> Path: ${PROJECT_PATH}${REPO_NAME}\n >> Clean Build\n"
  cd ${PROJECT_PATH}${REPO_NAME} && ./gradlew clean build -x test
}

find_jar() {
  if [ ! -d ${PROJECT_PATH}${REPO_NAME}/build/libs ]
  then
    echo "${PROJECT_PATH}${REPO_NAME}/build/libs not exists!"
    exit 0
  fi
  echo "$(cd ${PROJECT_PATH}${REPO_NAME}/build/libs && find ./* -name "*jar" | awk -F './' '{print $2}')"
}

find_pid() {
  if [ "${JAR_NAME}" = "${PROJECT_PATH}${REPO_NAME}/build/libs not exists!" ]
  then
    echo "can't find PID because there is no jar file!"
  else
    echo "$(ps -ef | grep ${JAR_NAME} | grep -v grep | awk '{print $2}')"
  fi
}

stop_process() {
  info;

  echo "\n >> Stop Process 🏃♂️ \n"

  JAR_NAME=$(find_jar)
  PID=$(find_pid)

  if [ "${PID}" = "can't find PID because there is no jar file!" ]
  then
    echo "프로세스가 현재 실행중이지 않습니다."
  else
    echo "\n >> Find Java process(${REPO_NAME}) & Kill Java process\n >> JAR NAME: ${JAR_NAME}, PID: ${PID}"
    kill ${PID}
  fi
}

start_process() {
  info;

  echo "\n >> Start Process 🏃♂️ \n"

  JAR_NAME=$(find_jar)

  if [ "${JAR_NAME}" = "${PROJECT_PATH}${REPO_NAME}/build/libs not exists!" ]
  then
    echo "실행핧 수 있는 jar 파일이 존재하지 않습니다."
  elif [ -n "$(find_pid)" ]
  then
    echo "이미 실행 중인 프로세스가 존재합니다. 재시작을 원하시면 stop을 먼저 진행해야 합니다."
  else
    nohup java -Dspring.profiles.active=prod -jar ${PROJECT_PATH}${REPO_NAME}/build/libs/${JAR_NAME} 1> ${LOG_PATH}${REPO_NAME}_${CURRENT_TIME}.log 2>&1 &
    [ ! -e ${LOG_PATH}${REPO_NAME}.log ] || rm ${LOG_PATH}${REPO_NAME}.log
    ln -s ${LOG_PATH}${REPO_NAME}_${CURRENT_TIME}.log ${LOG_PATH}${REPO_NAME}.log
  fi
}

check() {
  if [ ! -d ${PROJECT_PATH}${REPO_NAME} ]
  then
    clone;
    cd ${PROJECT_PATH}${REPO_NAME} && git fetch
    pull;
    build;
    start_process;
  else
    cd ${PROJECT_PATH}${REPO_NAME} && git fetch
    master=$(cd ${PROJECT_PATH}${REPO_NAME} && git rev-parse HEAD)
    remote=$(cd ${PROJECT_PATH}${REPO_NAME} && git rev-parse --verify origin/${BRANCH_NAME})

    if [ "$master" = "$remote" ]
    then
      echo "[${CURRENT_TIME}] Nothing to do!!! 😫"
      exit 0
    else
      stop_process;
      pull;
      build;
      start_process;
    fi
  fi
}

end() {
  echo "\n${txtylw}------------------------${txtrst}"
  echo "${txtgrn}| <<  ${FIX_LENGTH_FUNC_NAME} END 🧐  >> |${txtrst}"
  echo "${txtylw}------------------------${txtrst}"
}

begin;

case ${FUNC_NAME} in
  guide)
    guide
    ;;
  pull)
    pull
    ;;
  build)
    build
    ;;
  stop)
    stop_process
    ;;
  start)
    start_process
    ;;
  check)
    check
    ;;
  clone)
    clone
    ;;
esac

end;
