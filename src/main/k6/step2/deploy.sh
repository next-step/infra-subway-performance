#!/bin/bash

## 변수 설정

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

REPOSITORY=~/nextstep
PROJECT_NAME=infra-subway-performance
FIND_JAR_NAME=subway

LOGPATH=~/nextstep/log
LOGFILENAME=log_$(date '+%Y-%m-%d_%H:%M:%S').out

EXECUTION_PATH=$(pwd)
SHELL_SCRIPT_PATH=$(dirname $0)
BRANCH=$1
PROFILE=$2

## 함수작성

## 저장소 pull
function pull() {
  cd $REPOSITORY/$PROJECT_NAME/
  echo -e ""
  echo -e ">> Pull Request 🏃♂️ "
  git pull origin $BRANCH
}

## github branch 변경사항이 있는 경우에만 동작
function check_df() {
  cd $REPOSITORY/$PROJECT_NAME/
  git fetch
  master=$(git rev-parse $BRANCH)
  remote=$(git rev-parse origin/$BRANCH)

  if [[ $master == $remote ]]; then
    echo -e "[$(date)] Nothing to do!!! 😫"
    exit 0
  fi
}

## gradle build
function build() {
  cd $REPOSITORY/$PROJECT_NAME/
  echo -e ""
  echo -e ">> Gradle build 🏃♂️ "
  SPRING_PROFILES_ACTIVE=$PROFILE ./gradlew clean build --exclude-task test
}

## 프로세스 pid를 찾아서 있는 경우 종료
function processFindAndKill() {
  echo -e ""
  echo -e ">> 현재 구동중인 프로세스 확인 🏃♂️ "

  CURRENT_PID=$(pgrep -f $FIND_JAR_NAME)

  if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."

  else
    echo "> kill -2 $CURRENT_PID"
    kill -2 $CURRENT_PID
    sleep 5
         CURRENT_PID2=$(pgrep -f $FIND_JAR_NAME)
         if [ -z $CURRENT_PID2 ]; then
           echo ">정상종료되었습니다."
         else
           echo ">강제 종료합니다."
           kill -9 $CURRENT_PID2
           sleep 5
         fi
       fi
     }

     ##애플리케이션 구동
     function run() {
       echo -e ""
       echo -e ">> 새 애플리케이션 배포 🏃♂️ "
       cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/
       cd $REPOSITORY
       JAR_NAME=$(ls -tr $REPOSITORY/ | grep $FIND_JAR_NAME | tail -n 1)
       echo "> JAR Name : $JAR_NAME"
       nohup java -jar -Dspring.profiles.active=$PROFILE $JAR_NAME  1> $LOGPATH/$LOGFILENAME 2>&1  &
       }

##여기부터 시작

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 스크립트 🧐 >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

check_df;
pull;
build;
processFindAndKill;
