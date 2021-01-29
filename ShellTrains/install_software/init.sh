#!/usr/bin/expect
set timeout 30
spawn mysql_secure_installation
expect "*any other key for No*"
send "N\r"
expect "New password:"
send "Java@czm7566576\r"
expect "Re-enter new password:" 
send "Java@czm7566576\r"
expect "Remove anonymous users*" 
send "N\r"
expect "Disallow root login remotely*"
send "Y\r"
expect "Remove test database and access to it*" 
send "N\r"
expect "Reload privilege tables now*" 
send "Y\r"
interact