#! /bin/bash
## 변수 설정
txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray
RET_TRUE=1
RET_FALSE=0

EXECUTION_PATH=$(pwd)
SHELL_SCRIPT_PATH=$(dirname $0)
BRANCH=$1
PROFILE=$2
GIT_SUB_TOKEN=$3
GIT_URL=https://github.com/SanghoonSon/infra-subway-performance.git
BASE_DIR="/home/ubuntu/nextstep"
REPOSITORY="${BASE_DIR}/infra-subway-performance"
BUILD_PATH="${REPOSITORY}/build/libs"

function usage() {
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}<< 스크립트 🧐 >>${txtrst}"
  echo -e ""
  echo -e "${txtgrn}$0 branch${txtred}{ sanghoonson | step2 } ${txtgrn}profile${txtred}{ prod | test | local }"
  echo -e "${txtylw}=======================================${txtrst}"
}

function deploy() {
  cloneIfNotExistApplication;
  check_df;
  pull;
  buildAndRestartApp;
}

function cloneIfNotExistApplication() {
  if [[ ! -e $REPOSITORY ]]; then
    mkdir -p "$REPOSITORY"
    cd ${REPOSITORY}
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}<< 저장소 복사중 🧐 >>${txtrst}"
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtylw} >> 브랜치 : ${txtred}$BRANCH${txtrst}"
    echo -e "${txtylw} >> GIT : ${txtred}$GIT_URL${txtrst}"
    echo -e "${txtylw} >> 경로 : ${txtred}$REPOSITORY${txtrst}"
    git clone -b $BRANCH $GIT_URL $REPOSITORY
    git submodule init
    pullSubModule
    buildAndRestartApp
    exit 0
  fi
  cd ${REPOSITORY}
}


function check_df() {
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}<< 저장소 업데이트 🧐 >>${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtylw} >> 브랜치 비교 대상 : ${txtred}$BRANCH${txtrst}"
  git fetch

  master_branch=$(git rev-parse $BRANCH)
  remote_branch=$(git rev-parse origin/$BRANCH)

  if [[ $master_branch == $remote_branch ]]; then
    echo -e "${txtylw} >> [$(date)] 변경 된 내용이 없습니다 😫${txtrst}"
    exit 0
  fi
}

function pull() {
  echo -e ""
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}<< Pull Request 🏃♂ >>${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
  git pull origin $BRANCH
  pullSubModule();
}

function pullSubModule() {
  if [[ -n "$GIT_SUB_TOKEN" ]]; then
    git submodule set-url src/main/resources/config https://${GIT_SUB_TOKEN}@github.com/SanghoonSon/infra-subway-deploy-config.git
    git submodule update
    git checkout HEAD -- .gitmodules
  fi
}

function makeJar() {
  echo -e ""
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}<< Application 빌드 🧐 >>${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
  ./gradlew clean build
}

function shutdownApplication() {
  echo -e ""
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}<< Application 종료 >>${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
  local appPid=$(pgrep -f subway)
  if [[ -n "$appPid" ]]
  then
    kill -TERM $appPid
    echo -e "${txtylw} >> 종료 완료${txtrst}"
  else
    echo -e "${txtylw} >> 구동중인 Application이 없으므로 종료하지 않습니다.${txtrst}"
  fi
}

function releaseApplication() {
  echo -e ""
  echo -e "${txtylw}=======================================${txtrst}"
  echo -e "${txtgrn}<< Application 시작 🧐 >>${txtrst}"
  echo -e "${txtylw}=======================================${txtrst}"
    # tail -n으로 최신 jar 파일 변수에 저장
  local jarName=$(ls $BUILD_PATH | grep 'subway' | tail -n 1)
  echo -e "${txtylw} >> Profile : $PROFILE${txtrst}"
  echo -e "${txtylw} >> JAR : $jarName${txtrst}"

  nohup java -jar \
    -Dspring.profiles.active=$PROFILE \
    $BUILD_PATH/$jarName 1> app.log 2>&1  &
  echo -e "${txtylw} >> [$(date)] Application 시작 완료${txtrst}"
}

function buildAndRestartApp() {
  makeJar;
  shutdownApplication;
  releaseApplication;
}

if [[ $# -ne 2 ]]
then
  usage
fi

deploy;
exit;
