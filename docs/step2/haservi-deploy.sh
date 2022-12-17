txtrst='\033[1;37m' # White
txtred='\033[1;31m' # Red
txtylw='\033[1;33m' # Yellow
txtpur='\033[1;35m' # Purple
txtgrn='\033[1;32m' # Green
txtgra='\033[1;30m' # Gray

PROJECT_PATH='/home/ubuntu/infra-subway-performance'

JAR_PATH=${PROJECT_PATH}/build/libs
JAR=$(cd ${JAR_PATH} && find ./* -name "*jar" | cut -c 3-)
JAR_PID=$(ps -ef | grep $JAR | grep -v grep | awk '{print $2}')
LOG_FILE='/home/ubuntu/infra-subway-performance/subway.log'

function git_clone() {
  echo "저장소의 정보를 복사합니다."
  git clone "https://github.com/haservi/infra-subway-performance.git"
  cd infra-subway-performance
  git checkout step2
  git pull
}

function build() {
  cd ${PROJECT_PATH} && ./gradlew clean build
}

function stop_process() {
  if [ -z "$JAR_PID" ]; then
    echo "프로세스가 실행중이지 않습니다."
  else
    echo "$JAR의 프로세스를 종료합니다. (PID = $JAR_PID)"
    kill $JAR_PID
  fi
}


function start_process() {
  nohup java -jar -Dspring.profiles.active=prod $JAR_PATH/$JAR 1> $LOG_FILE 2>&1
}

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  Deploy Start                         ${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"

git_clone;
build;
stop_process;
start_process;

echo -e "${txtylw}=======================================${txtrst}"
echo -e "${txtgrn}  🛠  정상적으로 배포가 됐습니다.              ${txtrst}"
echo -e "${txtylw}=======================================${txtrst}"
