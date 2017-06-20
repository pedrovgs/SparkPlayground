#!/bin/bash

if [ ! -e target/scala-2.11/sparkPlayground.jar ]
then
    echo "You have to execute 'sbt assembly' before submitting your app"
    exit -1
fi

which spark-submit
if [ $? -eq 0 ]
then
    cp -R src/main/resources* /tmp/resources/
    spark-submit \
      --class com.github.pedrovgs.sparkplayground.SparkPlayground \
      --master spark://localhost:7077 \
      --deploy-mode client \
      --files `pwd`/src/main/resources/exercise1/el-quijote.txt,`pwd`/src/main/resources/exercise3/frankenstein.txt,`pwd`/src/main/resources/exercise3/the-picture-of-dorian-gray.txt,`pwd`/src/main/resources/exercise4/buildExecutionTimes.txt,`pwd`/src/main/resources/exercise5/textFile.txt,`pwd`/src/main/resources/exercise5/users.json,`pwd`/src/main/resources/exercise5/users.json.gz,`pwd`/src/main/resources/exercise5/videoGamesSales.csv,`pwd`/src/main/resources/exercise6/movies.csv \
      target/scala-2.11/sparkPlayground.jar
else
    echo "Review your Apache Spark installation. We can't find 'spark-submit' binary file."
fi
