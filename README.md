# SparkPlayground

Playground for experimenting with [Apache Spark](https://spark.apache.org/) using [Scala](https://www.scala-lang.org/).

![SparkLogo](./art/sparkLogo.png)

This repository contains a bunch of exercises resolved using Apache Spark and written in Scala. The exercises resolved use public APIs or open datasets in order to experiment with the different Apache Spark APIs. The goal is to practice and learn. Inside this repository you will find RDDs, DataSets, and DataFrames usage, Spark SQL queries, Spark Streaming examples and Machine Learning stuff :smiley:.

## Exercises

This table contains all the exercises resolved in this repository sorted by goals with links for the solution and the specs.

| # | Goal | Statement | Code | Tests |
| - | ---- | --------- | ---- | ----- |
| 1 | Learn how to use ``SparkContext`` and some basic ``RDDs`` methods. | [El Quijote statement.](./statements/EL_QUIJOTE.md) | [ElQuijote.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise1/ElQuijote.scala) | [ElQuijoteSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise1/ElQuijoteSpec.scala) |
| 2 | Learn how to parallelize Scala collections and work with them as ``RDDs``. | [Numerical series statement.](./statements/NUMERICAL_SERIES.md) | [NumericalSeries.scala](./src/main/scala/com/github/pedrovgs/sparkplayground/exercise2/NumericalSeries.scala) | [NumericalSeriesSpec.scala](./src/test/scala/com/github/pedrovgs/sparkplayground/exercise1/NumericalSeriesSpec.scala) |

## Build and test this project

To build and test this project you can execute ``sbt test``. You can also use ``sbt`` interactive mode (you just have to execute ``sbt`` in your terminal) and then use the triggered execution to execute your tests using the following commands inside the interactive mode:

```
~ test // Runs every test in your project
~ test-only *AnySpec // Runs specs matching with the filter passed as param.
```

or if you want to j

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

