# SparkPlayground [![Build Status](https://travis-ci.org/pedrovgs/SparkPlayground.svg?branch=master)](https://travis-ci.org/pedrovgs/SparkPlayground)

Playground used to learn and experiment with [Apache Spark](https://spark.apache.org/) using [Scala](https://www.scala-lang.org/). Do you want to learn Apache Spark? Try to resolve the exercised proposed below.

![SparkLogo](./art/sparkLogo.png)

This repository contains a bunch of exercises resolved using Apache Spark and written in Scala. The exercises resolved use public APIs or open datasets in order to experiment with the different Apache Spark APIs. The goal is to practice and learn. Inside this repository you will find RDDs, DataSets, and DataFrames usage, Spark SQL queries, Spark Streaming examples and Machine Learning stuff :smiley:.

## Exercises

This table contains all the exercises resolved in this repository sorted by goals with links for the solution and the specs.

| # | Goal | Statement | Code | Tests |
| - | ---- | --------- | ---- | ----- |
| 1 | Learn how to use ``SparkContext`` and some basic ``RDDs`` methods. | [El Quijote.](./statements/EL_QUIJOTE.md) | [ElQuijote.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise1/ElQuijote.scala) | [ElQuijoteSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise1/ElQuijoteSpec.scala) |
| 2 | Learn how to parallelize Scala collections and work with them as ``RDDs``. | [Numerical series.](./statements/NUMERICAL_SERIES.md) | [NumericalSeries.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise2/NumericalSeries.scala) | [NumericalSeriesSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise1/NumericalSeriesSpec.scala) |
| 3 | Learn how to use set transformations for ``RDDs``. | [Sets.](./statements/SETS.md) | [Sets.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise3/Sets.scala) | [SetsSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise3/SetsSpec.scala) |
| 4 | Learn how to use ``Pair  RDDs``. | [Build executions.](./statements/BUILD_EXECUTIONS.md) | [BuildExecutions.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise4/BuildExecutions.scala) | [BuildExecutionsSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise4/BuildExecutionsSpec.scala) |
| 5 | Learn how to read and save data using different formats. | [Read and write data.](./statements/READ_AND_WRITE_DATA.md) | [ReadAndWrite.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise5/ReadAndWrite.scala) | [ReadAndWriteSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise5/ReadAndWriteSpec.scala) |
| 6 | Learn how to use shared variables and numeric operations. | [Movies.](./statements/MOVIES.md) | [Movies.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise6/Movies.scala) | [MoviesSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise6/MoviesSpec.scala) |
| 7 | Learn how to submit and execute Spark applications on a cluster. | [RunningOnACluster.](./statements/RUNNING_ON_A_CLUSTER.md) | [RunningOnACluster.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise7/RunningOnACluster.scala) | - |
| 8 | Learn how to use Kryo serialization. | [Kryo.](./statements/KRYO.md) | [Kryo.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise8/Kryo.scala) | [KryoSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise8/KryoSpec.scala) |

## Build and test this project

To build and test this project you can execute ``sbt test``. You can also use ``sbt`` interactive mode (you just have to execute ``sbt`` in your terminal) and then use the triggered execution to execute your tests using the following commands inside the interactive mode:

```
~ test // Runs every test in your project
~ test-only *AnySpec // Runs specs matching with the filter passed as param.
``` 

## Running on a cluster

Spark applications are developed to run on a cluster. Before to run your app you need to generate a ``.jar`` file you can submit to Spark to be executed. You can generate the ``sparkPlayground.jar`` file executing ``sbt assembly``. This will generate a binary file you can submit using ``spark-submit`` command. Ensure your local Spark version is ``Spark 2.1.1``. 

You can submit this application to your local spark installation executing these commands:

``
sbt assembly
./submitToLocalSpark.sh
``

You can submit this application to a dockerized Spark cluster using these commands:

```
sbt assembly
cd docker
docker-compse up -d
cd ..
./submitToDockerizedSpark.sh
```


Developed By
------------

* Pedro Vicente Gómez Sánchez - <pedrovicente.gomez@gmail.com>

<a href="https://twitter.com/pedro_g_s">
  <img alt="Follow me on Twitter" src="https://image.freepik.com/iconos-gratis/twitter-logo_318-40209.jpg" height="60" width="60"/>
</a>
<a href="https://es.linkedin.com/in/pedrovgs">
  <img alt="Add me to Linkedin" src="https://image.freepik.com/iconos-gratis/boton-del-logotipo-linkedin_318-84979.png" height="60" width="60"/>
</a>

License
-------

    Copyright 2017 Pedro Vicente Gómez Sánchez

    Licensed under the GNU General Public License, Version 3 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.gnu.org/licenses/gpl-3.0.en.html

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

