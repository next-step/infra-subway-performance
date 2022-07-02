#!/bin/bash

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

SCRIPT_PATH=$(dirname $0)
BRANCH=$1
PROFILE=$2
REPO=$SCRIPT_PATH/infra-subway-performance


PID="";
BRANCH=$1
PROFILE=$2
TODAY=`date "+%Y%m%d"`
LOGFILE="server_${TODAY}.log"
echo "today is ${TODAY}!"

echo -e "${txtred} $SHELL_SCRIPT_PATH"
cd $SHELL_SCRIPT_PATH

if [ $# -ne 2 ]
then
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}  << 스크립트 $0 🧐 >>${txtrst}"
    echo -e "${txtgrn} INSUFFICIENT PARAM!"
    echo -e "${txtgrn} $0 ${txtpur} 브랜치이름 ${txtred}{ prod | dev }"
    echo -e "${txtylw}=======================================${txtrst}"
    exit
else
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}  << 스크립트 $0 🧐 >>${txtrst}"
    echo -e "${txtylw}=======================================${txtrst}"
fi

function clone() {
  echo -e ""
  echo -e ">> Git Clone 🏃♂️"
  git clone "https://github.com/kwonyongil/infra-subway-performance.git"
  cd $REPO
}

function pull() {
  echo -e ""
  echo -e ">> Pull Request 🏃♂️  ${BRANCH}"
  git pull origin ${BRANCH}
}

function build() {
  echo -e ""
  echo -e ">> Build 🏃..."
  ./gradlew clean build
}

function find_pid() {
  echo -e ""
  echo -e ">> find PID 🏃..."
  PID=`ps -ef | grep -v "grep" | jps | grep "subway" | awk '{print $1}'`
}

function kill_pid() {
  if [ "$PID" != "" ]
  then
    echo -e ""
    echo -e ">> Kill ${PID} 🏃..."
    kill ${PID}
  else
    echo -e "PID is Empty!"
  fi
}

function start() {
  echo -e "application start..."
  nohup java -jar -Dspring.profiles.active=$PROFILE ./build/libs/subway-0.0.1-SNAPSHOT.jar 1> ${LOGFILE} 2>&1  &
}

clone;
pull
build
find_pid
kill_pid
start
echo -e "Deploy success!"
