#!/bin/bash


EXECUTION_PATH=$(pwd)
SHELL_SCRIPT_PATH=$(dirname $0)
BRANCH=$1
PROFILE=$2

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray


if [[ $# -ne 2 ]]
then
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}  << 스크립트 🧐  >>${txtrst}"
    echo -e ""
    echo -e "${txtgrn} $0 브랜치이름 ${txtred}{ prod | dev }"
    echo -e "${txtylw}=======================================${txtrst}"
    exit
fi

function git_clone(){
  echo -e ">> Pull Request 🏃♂️ "
  git clone https://github.com/yangseungin/infra-subway-performance
}

function git_pull(){
  echo -e ">> clone repo"
  git pull origin $BRANCH
}

function build(){
  echo -e ">> gradle build"
  ./gradlew clean build -x test
}

function app_start(){
  echo -e ">> app start"
  nohup java -jar -Dspring.profiles.active=$PROFILE $EXECUTION_PATH/build/libs/*.jar 1> subway.log 2>&1 &
}

echo -e ">> make directory"
mkdir nextstep && cd nextstep

git_clone;

cd infra-subway-performance

git_pull;

build;

app_start;
