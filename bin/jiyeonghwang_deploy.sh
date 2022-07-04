#!/bin/bash

## 변수 설정

txtrst='\033[1;37m' # White
txtylw='\033[1;33m' # Yellow
txtgrn='\033[1;32m' # Green

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 스크립트 🧐 >>${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

function clone() {

## 저장소 clone
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}<< Git Clone >>${txtrst}"
    echo -e "${txtylw}=======================================${txtrst}"

    git clone https://github.com/jiyeonghwang/infra-subway-performance
    cd infra-subway-performance
}

function pull() {

## 저장소 pull
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}<< Git Pull >>${txtrst}"
    echo -e "${txtylw}=======================================${txtrst}"

    git pull origin step2
}

function build() {

## gradle build
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}<< build >>${txtrst}"
    echo -e "${txtylw}=======================================${txtrst}"

    ./gradlew clean build

}

function process_start() {

## 프로세스를 실행하는 명령어
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "프로세스 실행"
    echo -e "${txtylw}=======================================${txtrst}"

    nohup java -jar -Dspring.profiles.active=prod subway-0.0.1-SNAPSHOT.jar 1> log.log 2>&1 &

    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "프로세스 실행 완료"
    echo -e "${txtylw}=======================================${txtrst}"

}

clone;
pull;
build;
process_start;
