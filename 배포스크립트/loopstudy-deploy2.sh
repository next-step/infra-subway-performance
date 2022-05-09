#!/bin/bash

## ...

## 변수 설정

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

REPOSITORY=/home/ubuntu/nextstep/infra-subway-performance
JAR_REPOSITORY=${REPOSITORY}/build/libs
BRANCH=loop-study
PROFILE=prod

## 조건 설정
if [ $# -ne 2 ]
then
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}  << 스크립트 🧐 >>${txtrst}"
    echo -e ""
    echo -e "${txtgrn} $0 브랜치이름 ${txtred}{ prod | dev }"
    echo -e "${txtylw}=======================================${txtrst}"
    exit
fi

## ...

## 프로젝트 폴더로 move
## 저장소 pull
## gradle build
## 프로세스 pid를 찾고 종료하는 명령어
## 애플리케이션 배포 함수

function clone() {
    echo -e "${txtylw}=======================================${txtrst}"
    git clone -b loop-step1-step2 --single-branch https://github.com/loop-study/infra-subway-performance.git
    echo -e "${txtylw}=======================================${txtrst}"
}

function move() {
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e ">> MOVE 🏃♂️ "
    cd $REPOSITORY
    echo -e "${txtylw}=======================================${txtrst}"
}

function pull() {
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e ">> Pull Request 🏃♂️ "
    git pull origin $BRANCH
    echo -e "${txtylw}=======================================${txtrst}"
}

function build() {
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e ">> Gradle Clean Build 🏃♂️ "
    ./gradlew clean build
    echo -e "${txtylw}=======================================${txtrst}"
}

function findPidAndKillPid() {
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e ">> Find Pid And Kill Pid 🏃♂️ "
    CURRENT_PID=$(pgrep -f java)
    echo -e "실행중인 프로세스 ${CURRENT_PID}"
    if [ -z $CURRENT_PID ]; then
        echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
    else
        kill -2 $CURRENT_PID
        sleep 3
    fi
    echo -e "${txtylw}=======================================${txtrst}"
}

function startServer() {
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e ">> Start Server 🏃♂️ "
    JAR_NAME=$(ls -tr $JAR_REPOSITORY/ | grep jar | tail -n 1)
    echo -e "-Dspring.profiles.active=${PROFILE}"
    echo -e "${JAR_REPOSITORY}${JAR_NAME}"
    nohup java -jar -Dspring.profiles.active=${PROFILE} ${JAR_REPOSITORY}/${JAR_NAME} 1> infra-prod 2>&1 &
    echo -e "${txtylw}=======================================${txtrst}"
}

clone;
move;
##pull;
build;
findPidAndKillPid;
startServer;