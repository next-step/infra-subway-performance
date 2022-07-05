#!/bin/bash

EXECUTION_PATH=$(pwd)
SHELL_SCRIPT_PATH=$(dirname $0)
BRANCH=step2
REPO_URL=https://github.com/yulmucha/infra-subway-performance.git
REPO_NAME=infra-subway-performance
PROFILE=prod
APP_NAME=subway

function clone() {
  git clone -b $BRANCH --single-branch $REPO_URL
}

function build() {
	./gradlew clean build
}

function run() {
	JAR_PATH=$(find build -name "$APP_NAME*.jar")
	nohup java -jar -Dspring.profiles.active=$PROFILE $JAR_PATH 1> /dev/null 2>&1  &
}

cd $SHELL_SCRIPT_PATH
clone;
cd $REPO_NAME
build;
run;
