#!/usr/bin/env bash
echo "----------------------------------------------------------------------------------------------------------------------"
echo "Start pull new code from repository......"
cd /data/server/boot-dubbo/provider
/usr/local/maven/bin/mvn clean
git pull

echo "----------------------------------------------------------------------------------------------------------------------"


echo "-----------------------------------------------------------------------------------------------------------------------"
sh shutdown-prod.sh
echo "------------------------------------------------------------------------------------------------------------------------"


echo "------------------------------------------------------------------------------------------------------------------------"
echo "Start restart project......"
chown  -R tomcat.tomcat /data/server/boot-dubbo/provider/*
nohup /usr/local/maven/bin/mvn spring-boot:run -Pprod -Drun.profiles=prod >> nohup.out 2>&1 &
#tail -f nohup.out
echo "Start success......"