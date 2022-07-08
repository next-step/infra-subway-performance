#!/bin/bash

EXECUTION_PATH=$(pwd)
HOME_PATH=$(dirname "$0")
LOG_FILE_NAME="infra-subway-deploy.log"
PROFILE_TYPE=("prod" "local")
REPOSITORY_TYPE=("origin" "upstream")
REPOSITORY=$1
BRANCH=$2
PROFILE=$3
ORIGIN_REPOSITORY="https://github.com/kmmin78/infra-subway-performance.git"
ORIGIN_REPOSITORY_DIR="infra-subway-performance"

## 조건 설정
if [[ -z "${BRANCH}" ]] || [[ -z "${PROFILE}" ]];
then
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}  << 스크립트 🧐 >>${txtrst}"
    echo -e ""
    echo -e "${txtgrn} 브랜치와 프로파일은 필수입니다."
    echo -e "${txtylw}=======================================${txtrst}"
    exit
fi

if [[ ! " ${PROFILE_TYPE[*]} " =~ ${PROFILE} ]];
then
  echo -e "${PROFILE}은 존재하지 않습니다."
  exit
fi

if [[ ! " ${REPOSITORY_TYPE[*]} " =~ ${REPOSITORY} ]];
then
  echo -e "${REPOSITORY} 저장소는 등록되지 않았습니다."
  exit
fi

function clone() {
  git clone -b "${BRANCH}" --single-branch "${ORIGIN_REPOSITORY}"
  cd "${ORIGIN_REPOSITORY_DIR}"
}

function check_df() {
  git fetch
  master=$(git rev-parse "${BRANCH}")
  remote=$(git rev-parse "${REPOSITORY}" "${BRANCH}")

  if [[ $master == $remote ]]; then
    echo -e "[$(date)] Nothing to do!!! 😫"
    exit 0
  fi
}

function pull() {
  echo -e ""
  echo -e ">> Pull Request Start 🏃♂️ "
  git checkout "${BRANCH}"
  git pull "${REPOSITORY}" "${BRANCH}"
  echo -e ">> Pull Request End 🏃♂️ "
}

function build() {
  echo -e ""
  echo -e ">> Build Start 🏃♂️ "
  ./gradlew clean build
  echo -e ">> Build End 🏃♂️ "
}

function shutDownBeforeProcess() {
  echo -e ""
  echo -e ">> shutDownBeforeProcess Start 🏃♂️ "
  echo -e "searching pid ..."
  PID=$(ps -ef | grep "java" | grep -v "grep" | awk '{print $2}')
  if [[ -z "${PID}" ]]
  then
    echo -e "pid was not found"
  else
    echo -e "pid : ${PID}"
    echo -e "killing ${PID}..."
    kill -15 "${PID}"
    echo -e "${PID} is killed"
  fi
  echo -e ">> shutDownBeforeProcess End 🏃♂️ "
}

function deploy() {
  echo -e ">> deploy Start 🏃♂️ "
  BUILD_FILE_PATH=$(find "${HOME_PATH}"/build/libs -name "*.jar")
  nohup java -jar -Dspring.profiles.active="${PROFILE}" "${BUILD_FILE_PATH}" 1> ${LOG_FILE_NAME} 2>&1 &
  echo -e ">> deploy End 🏃♂️ "
}

function deploy_facade() {
  clone;
  check_df;
  pull;
  build;
  shutDownBeforeProcess;
  deploy;
}

deploy_facade;
