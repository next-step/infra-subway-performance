#!/bin/bash

## 컬러
txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

## 변수
GIT_CLONE_PATH="https://github.com/mond-page/infra-subway-performance.git"
EXECUTION_PATH="$(pwd)/infra-subway-performance"
EXECUTION_PORT="8080"
JAR_FILE_NAME="subway-0.0.1-SNAPSHOT.jar"
PHASE="prod"

## Git Clone 함수
function cloneGit() {
        echo -e "${txtylw}========== Git clone 시작 ==========${txtrst}"
        git clone ${GIT_CLONE_PATH}
        echo -e "${txtylw}========== Git clone 종료 ==========${txtrst}"
}

## Git Checkout 함수
function checkoutBranch() {
        echo -e "${txtylw}==========  Git Checkout 시작 ==========${txtrst}"
        cd ${EXECUTION_PATH}
        git checkout -t origin/step2
        git pull
        echo -e "${txtylw}==========  Git Checkout 종료 ==========${txtrst}"
}

## Gralde Clean 후 Build 함수
function buildGradle() {
        echo -e "${txtgra}========== Gradle Build 시작 ==========${txtrst}"
        sudo ./gradlew clean build
        echo -e "${txtgra}========== Gradle Build 종료 ==========${txtrst}"
}

## 해당 포트 KILL 함수
function pidKill() {
        echo -e "${txtpur}========== PID KILL 시작 ==========${txtrst}"
        local pid=`sudo lsof -i:${EXECUTION_PORT}`
        if [[ -n ${pid} ]]; then
                sudo kill $(sudo lsof -ti:${EXECUTION_PORT})
        fi
        echo -e "${txtpur}========== PID KILL 종료 ==========${txtrst}"
}

## 서버 시작 함수
function startServer() {
        echo -e "${txtgrn}========== Subway 서버 시작 ==========${txtrst}"
        local JAR_EXE="sudo java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${PHASE} -jar ${EXECUTION_PATH}/build/libs/${JAR_FILE_NAME}"
        nohup ${JAR_EXE} 1> sudo ${EXECUTION_PATH}/null 2>&1
}

cloneGit;
checkoutBranch;
buildGradle;
pidKill;
startServer;
