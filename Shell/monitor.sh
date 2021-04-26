#!/bin/bash
#
# 监控主机负载


######################
# 记录主机系统负载情况.
# - CPU利用率
# - 内存使用率
# - 运行进程数
######################
function sys_stat() {
    top -n 1 > /tmp/top.tmp
    cpu_idle=$(awk -F "," 'NR==3{print $4}' /tmp/top.tmp | awk -F "id" '{print $1}' | sed 's/ //g')
    memory=$(free -m | awk 'NR==2{printf "%.2f%%", $3*100/$2}')
    process_num=$(ps -ef | wc -l)

    echo "cpu_idle:${cpu_idle}%"
    echo "memory:${memory}"
    echo "process_num:${process_num}"
}

######################
# 记录主机网络情况
# 1. TCP连接数
# 2. 22端口连接数最多的ip
######################
function net_stat() {
    tcp_conn_sum=$(netstat -tpn | wc -l)
    most_conn_ip=$(netstat -tapn | grep 22 | grep -v \* | awk '{print $5}' | \
    awk -F ":" '{print $1}' | sort | uniq -c | sort -rn | awk 'NR==1{print $1}')

    echo "tcp connection sum:${tcp_conn_sum}"
    echo "most connection ip to 22 port:${most_conn_ip}"
}