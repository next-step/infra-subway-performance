#!/bin/bash
# execute script ". deploy.sh cwjohnpark prod && deploy"
txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

PROJECT_HOME="$(pwd)/infra-subway-performance"
LOG_PATH="${PROJECT_HOME}/log"
REPOSITORY=https://github.com/cwJohnPark/infra-subway-performance.git
BRANCH=${1}
PROFILE=${2}

if [[ $# -ne 2 ]]
then
	echo -e ""
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}  << 💁🏻 Usage >>${txtrst}"
    echo -e ""
    echo -e "${txtgrn} $0 ${txtpur} {branch name} ${txtred}{ prod | dev }"
    echo -e "${txtylw}=======================================${txtrst}"
	echo -e ""
fi

function clone() {
	echo -e ""
	echo -e "${txtylw}=======================================${txtrst}"
	echo -e ">> 📥  Clone Repository"
	echo -e "BRANCH=${BRANCH}"
	echo -e "REPOSITORY=${REPOSITORY}"
	echo -e "PROJECT_HOME=${PROJECT_HOME}"
	echo -e "${txtylw}=======================================${txtrst}"
	echo -e ""
	git clone -b $BRANCH $REPOSITORY $PROJECT_HOME
}

function check_update() {
	git fetch -a
	main=$(git rev-parse $BRANCH)
	remote=$(git rev-parse origin/$BRANCH)
	echo "Check update from remote git"
	if [[ $main == $remote ]]; then
		echo -e ">> Latest, no need to update"
		exit 0
	else
		echo -e ">> Start update"
		pull
		build
		kill_process
		run_app
	fi
}

function pull() {
	  echo -e ""
	  echo -e ">> Pull Repository"
	  sudo git pull origin $BRANCH
}

function build() {
	  cd ${PROJECT_HOME}
	  echo -e ""
	  echo -e ">> Build️ Application"
	  ./gradlew clean build -x test
	  JAR_NAME=$(basename -- build/libs/*.jar)
	  echo -e ">> JAR NAME : ${JAR_NAME}"
}

function kill_process(){
    PID=$(pgrep -f ${JAR_NAME})
    if [[ -z "${PID}" ]]
    then
        echo ">> Not found process with ${JAR_NAME}"
    else
        echo "kill -15 ${PID}"
        kill -15 ${PID}
        echo ">> Terminate process"
    fi
}

function run_app(){
	  echo ">> Run Application"
	  mkdir ${LOG_PATH}
	  COMMAND="nohup java -jar -Dspring.profiles.active=${PROFILE} ${PROJECT_HOME}/build/libs/${JAR_NAME} >> ${LOG_PATH}/application.log 2>&1 &"
	  sh -c "$COMMAND"
}

function deploy() {
    echo -e "${txtylw}=======================================${txtrst}"
    echo -e "${txtgrn}  << 📦  Deploy Application ! >>${txtrst}"
    echo -e "${txtylw}=======================================${txtrst}"
	  clone
	  build
	  run_app
}

deploy
