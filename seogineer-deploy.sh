#!/bin/bash

## 변수 설정

txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

DEPLOY_PATH=~/app
REPOSITORY=$1
BRANCH=$2
PROFILE=$3

## 조건 설정
if [[ $# -ne 3 ]]
then
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}  << 스크립트 🧐 >>${txtrst}"
    echo -e ""
    echo -e "${txtgrn} $0 저장소 브랜치이름 ${txtred}{ prod | dev }"
    echo -e "${txtylw}=======================================${txtrst}"
    exit
fi

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 스크립트 🧐 >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

mkdir $DEPLOY_PATH
cd $DEPLOY_PATH
EXECUTION_PATH=$(pwd)

## 저장소 clone
git clone -b $BRANCH --single-branch $REPOSITORY .

## build
./gradlew clean build

## 실행
JARFILE=`find $EXECUTION_PATH/build/libs/* -name "*jar"`
nohup java -jar -Dspring.profiles.active=$PROFILE $JARFILE 1> ~/subway.log 2>&1 &
