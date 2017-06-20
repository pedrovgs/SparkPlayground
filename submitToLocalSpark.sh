#!/bin/bash

if [ ! -e target/scala-2.11/sparkPlayground.jar ]
then
    echo "You have to execute 'sbt assembly' before submitting your app"
    exit -1
fi

which spark-submit
if [ $? -eq 0 ]
then
    spark-submit \
      --class com.github.pedrovgs.sparkplayground.SparkPlayground \
      --master spark://localhost:7077 \
      --deploy-mode client \
      target/scala-2.11/sparkPlayground.jar
else
    echo "Review your Apache Spark installation. We can't find 'spark-submit' binary file."
fi
