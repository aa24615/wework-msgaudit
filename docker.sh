#!/bin/sh

#  run.sh
#  wework
#
#  Created by 读心印 on 2020/6/29.
#  Copyright © 2020 读心印. All rights reserved.


mvn compile && mvn package

docker build -t java_wework .
docker run -it --rm --net=net --name wework java_wework
docker rmi java_wework