#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/spring-1.0-SNAPSHOT.jar \
    person@192.168.1.251:/home/person/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa person@192.168.1.251 << EOF

pgrep java | xargs kill -9
nohup java -jar spring-1.0-SNAPSHOT.jar > log.txt &
exit

EOF

echo 'Bye'