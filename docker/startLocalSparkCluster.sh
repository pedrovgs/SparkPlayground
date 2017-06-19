#!/bin/bash

which docker

if [ $? -eq 0 ]
then
    docker-compose up
else
    echo "You need to install Docker before submitting any app. Take a look here: http://docker.com/"
fi