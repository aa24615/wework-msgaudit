#!/bin/bash

mvn package &&

cd wework-docker &&

docker-compose restart wework

docker-compose up -d