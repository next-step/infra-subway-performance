#!/bin/bash

## 변수 설정
txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

REPO_PATH='/home/ubuntu/app/'
PROJECT_PATH='/home/ubuntu/app/infra-subway-performance/'
JAR_PATH=${PROJECT_PATH}build/libs/
LOG_PATH='/home/ubuntu/data/log/performance.log'
EXECUTION_PATH=$(pwd)
SHELL_SCRIPT_PATH=$(dirname $0)
BRANCH=$1
PROFILE=$2
REPO_NAME='infra-subway-performance'

function findJar(){
    echo "$(find ${JAR_PATH} -name '*jar')"
}

function findPid(){
    echo "$(ps -ef | grep -v 'grep' | grep ${JAR_PATH}$1 | awk '{print $2}')"
}

function print() {
    echo -e "${txtgrn}>> $1 ${txtgrn}"
}

function clone() {
    print "${txtrst}Step0. clone 🥚  ${txtrst}"
    cd ${REPO_PATH}
    print "$(pwd)"
    git clone -b ${BRANCH} --single-branch https://github.com/sixthou/infra-subway-performance.git
}

function pull() {
    print "${txtrst}Step1. pull request 🥚  ${txtrst}"
    cd ${PROJECT_PATH}
    print "$(pwd)"
    git pull origin ${BRANCH}
}

function build() {
    print "${txtrst}Step2. gradle build 🐣  ${txtrst}"
    cd ${PROJECT_PATH}
    ./gradlew clean build -x test
}

function stop_process() {
    print "${txtrst}Step3. stop 🐥  ${txtrst}"
    PID=$(findPid);
    if [[ -n ${PID} ]]
    then
        print "${txtgrn}KILL SUCCESS : ${PID}${txtgrn}"
        kill ${PID}
    else
        print "${txtylw}실행중인 프로세스가 없습니다.${txtylw}"
    fi
}

function run() {
    print "${txtrst}Step4. run 🐓  ${txtrst}"
    JAR=$(findJar);
        sleep 5
    nohup java -jar -Dspring.profiles.active=${PROFILE} ${JAR} 1>> ${LOG_PATH} 2>&1 &
    PID=$(findPid);
    if [[ -n ${PID} ]]
    then
        print "${txtgrn}RUN SUCCESS PID : ${PID}${txtgrn}"
    else
        print "${txtred}RUN FAIL${txtred}"
    fi
}

function deploy(){
    clone;
    pull;
    build;
    stop_process;
    run;
    exit
}

function check(){
    cd ${PROJECT_PATH}
    git fetch
    master=$(git rev-parse $BRANCH)
    remote=$(git rev-parse origin/$BRANCH)

    PID=$(findPid);
    if [[ -z ${PID} ]]
    then
        deploy;
        exit
    fi

    if [[ $master == $remote ]]
    then
        echo -e "[$(date)] Nothing to do!!! 😫  "
        exit 0
    else
        echo -e "${txtylw}=======================================${txtrst}"
        echo -e "${txtgrn}           << 배포 스크립트 🧐  >>           ${txtrst}"
        echo -e ""
        echo -e "${txtgrn} 브랜치 : ${txtred}${BRANCH} ${txtgrn}, 프로파일 : ${txtred}${PROFILE}"
        echo -e "${txtylw}=======================================${txtrst}"
        deploy;
    fi
}


if [[ $# -eq 2 ]]
then
    check;
    exit
else
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtred}         브랜치와 프로파일을 설정하세요        ${txtred}"
    echo -e "${txtylw}=======================================${txtrst}"
    exit
fi


sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
echo "deb https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
sudo apt-get update
sudo apt-get install k6
