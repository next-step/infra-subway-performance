#!/bin/bash

## 변수 설정
txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray
PROJECT_DIR=~/subway
REPOSITORY=https://github.com/giraffelim/infra-subway-performance.git
BRANCH=$1
PROFILE=$2

## git clone
function clone() {
  echo -e ""
  echo -e ">> Git Clone 🏃️"
  git clone -b $BRANCH --single-branch $REPOSITORY .
}

## gradle build
function build() {
  echo -e ""
  echo -e ">> Gradle Build 🚴♂️"
  ./gradlew clean build -x test
}


## run subway application
function run_application() {
  echo -e ""
  echo -e ">> run subway application 🚀"
  nohup java -jar -Dspring.profiles.active=$PROFILE $PROJECT_DIR/build/libs/subway-0.0.1-SNAPSHOT.jar 1> $PROJECT_DIR/subway.log 2>&1 &
}

mkdir $PROJECT_DIR && cd $PROJECT_DIR
clone
build
run_application
