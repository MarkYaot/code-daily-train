#！/bin/bash

#uninstall Java
#uninstall Golang
#uninstall Python
#uninstall Python3
#uninstall MySQL
#uninstall Tomcat
#uninstall Maven
#uninstall Redis

UNINSTALL_LOG='/var/log/uninstall.log'

printResult(){
    [ $? -eq 0 ] && echo "$1 $2 suceed" || (echo "$1 $2 failed" && exit)
}

kill_program(){
    if [ 0 -ne `ps -ef | grep $1 | grep -v grep | wc -l` ]; then
	    ps -ef | grep $1 | grep -v grep | awk '{print $2}' | xargs kill -9
	fi
}

kill_program tomcat
kill_program maven
kill_program redis
kill_program mysql

echo "Start uninstall Oracle Java8"
echo "Y" | sudo apt-get remove openjdk-8-jdk >> $UNINSTALL_LOG
printResult 'Uninstall' 'Java'

echo "Start uninstall Golang"
echo "Y" | sudo apt-get remove golang >> $UNINSTALL_LOG
printResult 'Uninstall' 'Golang'

echo "Start uninstall Python" 
echo "Y" | sudo apt-get remove python >> $UNINSTALL_LOG
printResult 'Uninstall' 'Python'

echo "Start uninstall Golang" 
echo "Y" | sudo apt-get remove python3 >> $UNINSTALL_LOG
printResult 'Uninstall' 'Python3'

echo "Start uninstall Tomcat"
rm -rf /opt/apache-tomcat*
sed -e '/export TOMCAT_HOME=/d'  /etc/profile > /dev/null
sed -i 's/:$TOMCAT_HOME//' /etc/profile
printResult 'Uninstall' 'Tomcat'

echo "Start uninstall Maven"
echo "Y" | sudo apt-get remove maven >> $UNINSTALL_LOG
printResult 'Uninstall' 'Maven'

echo "Start uninstall Redis"
echo "Y" | sudo apt-get remove redis-server >> $UNINSTALL_LOG
printResult 'Uninstall' 'Redis'

echo "Start uninstall MySQL" 
echo "Y" | sudo apt-get remove mysql-common >> $UNINSTALL_LOG
echo "Y" | sudo apt-get remove mysql-server >> $UNINSTALL_LOG
echo "Y" | sudo apt-get autoremove >> $UNINSTALL_LOG 
find / -name mysql* -exec rm -rf {} \;
printResult 'Uninstall' 'MySQL'
