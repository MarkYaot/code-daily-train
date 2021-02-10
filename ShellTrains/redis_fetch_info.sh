# 收集redis的info信息

# fetch redis info
echo "please input interval in seconds:"
read interval
echo "please input times:"
read times
echo "interval is:${interval}, times is:${times}"

path="/tmp/redis-info"
[ ! -d $path ] && mkdir $path
path="${path}/$(date "+%Y-%m-%d-%H:%M:%S")"
mkdir "$path"

for i in $(seq "$times")
do
  time=$(date "+%Y-%m-%d-%H:%M:%S")
  echo "fetch info: ${time}"
  redis-cli -h 127.0.0.1 -p 6379 info all > "${path}/${time}"
  let i+=1
  sleep "$interval"
done