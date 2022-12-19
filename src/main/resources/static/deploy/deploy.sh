#!/bin/bash

## 변수 설정
DEFAULT_PATH='/home/ubuntu/nextstep'
PROJECT_NAME='infra-subway-performance'
PROJECT_PATH='/home/ubuntu/nextstep/infra-subway-performance'
GIT_URL='https://github.com/jisu1211/infra-subway-performance.git'
BRANCH=$1
JAR=''
PID=''

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 배포 스크립트 실행 🧐 >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtpur}PROJECT_PATH = ${PROJECT_PATH}"
echo -e "${txtpur}BRANCH = ${BRANCH}"
echo -e "${txtylw}=======================================${txtrst}"

## deploy
deploy() {
    pull_or_clone;
    build;
    check_jar;
    find_ps_pid;
    stop_ps;
    start_ps;
}

## pull 또는 clone 수행
pull_or_clone() {
    if [[ -d $PROJECT_PATH  ]]
       then
            check_deploy;
            pull;
       else
            clone;
    fi
}

## git clone
clone() {
    echo -e ""
    echo -e "${txtgra}>> Clone Request 🏃♂️ "
    cd ${DEFAULT_PATH} && git clone -b ${BRANCH} --single-branch ${GIT_URL}
}

## deploy 실행 조건 점검
check_deploy() {
    cd ${PROJECT_PATH} && git fetch
    master=$(cd ${PROJECT_PATH} && git rev-parse ${BRANCH})
    remote=$(cd ${PROJECT_PATH} && git rev-parse origin/${BRANCH})

    if [[ "$master" = "$remote" ]]
    then
        echo -e "${txtgra}[$(date)] Nothing to do!!! 😫"
        exit 0
    fi
}

## git pull
pull() {
    echo -e ""
    echo -e "${txtgra}>> Pull Request 🏃♂️ "
    cd ${PROJECT_PATH} && git pull origin ${BRANCH}
}

## gradle build
build() {
    echo -e ""
    echo -e "${txtgra}>> Build Project 🏃♂️ "
    cd ${PROJECT_PATH} && ./gradlew clean build -x test
}

## jar 파일 생성 확인
check_jar() {
   if [[ -d "${PROJECT_PATH}/build/libs"  ]]
   then
       JAR=$(echo "$(cd ${PROJECT_PATH}/build/libs && find ./* -name '*jar' | awk -F './' '{print $2}')")
       echo -e ""
       echo -e "${txtgra}>> Build Jar Complete 🏃♂️ "
       echo -e "${txtgra}Path >> ${JAR}"
   else
       echo -e ""
       echo -e "${txtgra}>> Build Jar Failure 😢"
       exit 0
   fi
}

## jar 프로세스 pid 찾기
find_ps_pid() {
    PID=$(echo "$(ps -eaf | grep ${JAR} | grep -v grep | awk '{print $2}')")
    echo -e ""
    echo -e "${txtgra}PID >> ${PID}"
}

## 프로세스를 종료
stop_ps() {
    if [[ -n "$PID"  ]]
    then
        echo -e ""
        echo -e "${txtgra}>> Stop Process 🏃♂️ "
        kill $PID
    fi
}

## 프로세스를 실행
start_ps() {
    echo -e ""
    echo -e "${txtgra}>> Start Process 🏃♂️ "

    nohup java -jar -Dspring.profiles.active=prod ${PROJECT_PATH}/build/libs/$JAR 1> ${PROJECT_PATH}/log/subway.log 2>&1 &
}

deploy;

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 배포 스크립트 종료 🧐 >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"
