#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=basic-webservice

echo ">build 파일복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo ">현재구동중인 애플리케이션 pid확인"
CURRENT_PID=$(pgrep -fl basic-webservice | grep jar | awk '{print $1}')

echo "현재구동중인 애플리케이션 pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> 현재구동중인 어플리케이션이 없으므로 종료하지않습니다."
else
        echo ">Kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo ">새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar |tail -n 1)

echo ">JAR Name : $JAR_NAME"

echo ">$JAR_NAME 에 권한추가"
chmod +x $JAR_NAME

echo ">$JAR_NAME 실행 "

nohup java -jar \
        -Dspring.config.location=classpath:\application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties,classpath:/application-real.properties \
        -Dspring.profiles.active=real \
        $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

