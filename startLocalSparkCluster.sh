#!/bin/bash

which docker

if [ $? -eq 0 ]
then
    docker run --rm -it -p 4040:4040 gettyimages/spark
else
    echo "You need to install Docker before. Take a look here: http://docker.com/"
fi