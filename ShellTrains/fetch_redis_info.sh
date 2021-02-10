#!/bin/bash
#
# �ռ�redis��info��Ϣ

# �ɼ����Ϊinterval������Ϊtimes
echo -n "please input interval in seconds:"
read -r interval
echo -n "please input times:"
read -r times
echo "fetch redis info every ${interval} seconds for ${times} times"

# ��������Ŀ¼
work_path="/tmp/redis-info" && [ ! -d "${work_path}" ] && mkdir "${work_path}"
data_path="${work_path}/$(date "+%Y-%m-%d-%H:%M:%S")" && mkdir "${data_path}"

# ѭ���ɼ�����ʱ����¼
for i in $(seq "$times")
do
  time="$(date "+%Y-%m-%d-%H:%M:%S")"
  echo "fetch info: ${time}"
  redis-cli -h 127.0.0.1 -p 6379 info all > "${data_path}/${time}"
  sleep "$interval"
done