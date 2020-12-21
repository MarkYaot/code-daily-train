#!/bin/bash

#install Java
#install Golang
#install Python
#install Python3
#install MySQL
#install Tomcat
#install Maven
#install Redis

INSTALL_LOG='/var/log/install.log'

printResult(){
    [ $? -eq 0 ] && echo "$1 $2 suceed" || { echo "$1 $2 failed";exit;}
}

echo "Start install Oracle Java8"
echo "Y" | sudo apt install openjdk-8-jdk >> $INSTALL_LOG
printResult 'Install' 'Java'

echo "Start install Golang"
echo "Y" | sudo apt-get install golang >> $INSTALL_LOG
printResult 'Install' 'Golang'


echo "Start install Python" 
echo "Y" | sudo apt-get install python >> $INSTALL_LOG
printResult 'Install' 'Python'

echo "Start install Golang" 
echo "Y" | sudo apt-get install python3 >> $INSTALL_LOG
printResult 'Install' 'Python3'

echo "Start install MySQL" 
echo "Y" | sudo apt-get install mysql-server >> $INSTALL_LOG
printResult 'Install' 'MySQL'

if [ "X`cat /etc/mysql/mysql.conf.d/mysqld.cnf | grep 0.0.0.0`" = X ]; then
	echo "Init MySQL"
	echo "Y" | apt-get install expect >> $INSTALL_LOG
	./init_mysql.sh >> $INSTALL_LOG
	sed -i 's/127.0.0.1/0.0.0.0/' /etc/mysql/mysql.conf.d/mysqld.cnf
	mysql -uroot -pJava@czm7566576 -e 'create user "root"@"%" identified by \
	"Java@czm7566576";GRANT ALL PRIVILEGES ON *.* TO "root"@"%";FLUSH PRIVILEGES;' >> $INSTALL_LOG
fi

echo "Start install Tomcat"
if [ "X$(ls /opt/ | grep tomcat)" = X ]; then
	wget -P /opt http://mirror.bit.edu.cn/apache/tomcat/tomcat-8/v8.5.57/bin/apache-tomcat-8.5.57.tar.gz >> $INSTALL_LOG
	tar -zxvf /opt/apache-tomcat-8.5.57.tar.gz -C /opt
	echo 'export TOMCAT_HOME=/opt/apache-tomcat-8.5.57/' >> /etc/profile
fi
[ "X$(grep \"export PATH=\" /etc/profile)" = X ] && echo 'export PATH=$TOMCAT_HOME/bin:$PATH' >> /etc/profile || sed -i 's/$PATH/$PATH:$TOMCAT_HOME/' /etc/profile
printResult 'Install' 'Tomcat'

echo "Start install Maven"
echo "Y" | sudo apt-get install maven >> $INSTALL_LOG
printResult 'Install' 'Maven'

echo "Start install Redis"
echo "Y" | sudo apt-get install redis-server >> $INSTALL_LOG
printResult 'Install' 'Redis'
