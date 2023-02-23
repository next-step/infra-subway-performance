#!/bin/bash

## 변수 설정
txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

BRANCH=$1
PROFILE=$2

if [[ $# -ne 2 ]]; then
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}  << 잘못된 입력값 🧐 >>${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
  exit
fi

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  << 배포 🧐 >>${txtrst}"
echo -e ""
echo -e "${txtgrn} $SHELL_SCRIPT_PATH $BRANCH ${txtred}$PROFILE"
echo -e "${txtylw}=======================================${txtrst}"

function pull() {
  echo -e ""
  echo -e ">> Pull Request 🏃♂️ "
  git clone https://github.com/ifjso/infra-subway-performance.git
  cd infra-subway-performance
  git checkout ${BRANCH}
}

function build() {
  echo -e ""
  echo -e ">> Build 🏃♂️ "
  ./gradlew clean build -x test
}

function launch() {
  echo -e ""
  echo -e ">> Application is launching 🏃♂️ "

  nohup java -jar -Dspring.profiles.active=$PROFILE build/libs/subway-0.0.1-SNAPSHOT.jar 1> /dev/null 2>&1 &
}

function deploy() {
  pull;
  build;
  launch;
}

deploy;
