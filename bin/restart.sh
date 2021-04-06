#!/bin/bash

while true
do
    data=$(redis-cli << EOF
        AUTH redis_password
        select 9
        rpop work_msgaudit_restart
EOF
)
    strlen=$(echo $data | wc -c)
    if [ $strlen -gt 10 ];then
        starttime=`date +'%Y-%m-%d %H:%M:%S'`
        echo $data
        supervisorctl restart wework-msgaudit:wework-msgaudit_00
    fi
    sleep 5s
done
