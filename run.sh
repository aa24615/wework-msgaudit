#!/bin/bash

mvn package

cd wework-docker

docker-compose up -d