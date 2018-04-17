pid=`lsof -i :8080 | awk 'NR==2{print $2}'`
echo "start kill project in pid $pid ......"
kill -9 $pid
sleep 2
echo "end kill done......"