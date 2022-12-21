#!/bin/bash

GIT_URL="https://github.com/crongcm/infra-subway-performance.git"
PROJECT_PATH="/home/ubuntu/nextstep"
PROJECT_NAME="infra-subway-performance"
JAR_FILE="subway-0.0.1-SNAPSHOT.jar"
BRANCH_NAME=$1
CURRENT_PID=""

# 입력 브랜치의 최신 내용 받기
clone_or_pull() {
  cd ${PROJECT_PATH}

  if [ -e ${JAR_FILE} ]; then
    echo "> Git Pull"
    cd ${PROJECT_PATH}/${PROJECT_NAME} && git pull origin ${BRANCH_NAME}
  else
    echo "> Git Clone"
    cd ${PROJECT_PATH} && git clone -b ${BRANCH_NAME} --single-branch ${GIT_URL}
  fi
}

# build 수행
build() {
  cd ${PROJECT_PATH}/${PROJECT_NAME}

  echo "> project build start"
  ./gradlew build
}

# build의 결과물 (jar 파일) 특정 위치로 복사
copy_jar() {
  echo "> directory로 이동"
  cd ${PROJECT_PATH}

  echo "> build 파일 복사"
  cp ${PROJECT_PATH}/${PROJECT_NAME}/build/libs/*.jar ${PROJECT_PATH}/
}

check_pid() {
  echo "> 현재 구동중인 애플리케이션 pid 확인"
  CURRENT_PID=$(pgrep -f ${JAR_NAME}.*.jar)
}

stop_process() {
  echo "> 현재 구동중인 애플리케이션 pid: ${CURRENT_PID}"
  if [ -z "${CURRENT_PID}" ]; then
          echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
  else
        echo "> kill -9 ${CURRENT_PID}"
          kill -9 ${CURRENT_PID}
          sleep 5
  fi
}

deploy() {
  echo "> 새 애플리케이션 배포"
  JAR_NAME=$(ls -tr ${PROJECT_PATH}/ | grep jar | tail -n 1)

  echo "> Jar Name: ${JAR_NAME}"
  nohup java -jar -Dspring.profiles.active=prod ${PROJECT_PATH}/${JAR_NAME} 2>&1 &
}

clone_or_pull
build
copy_jar
check_pid
stop_process
deploy
