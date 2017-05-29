# SparkPlayground

Playground for experimenting with [Apache Spark](https://spark.apache.org/) using [Scala](https://www.scala-lang.org/).

![SparkLogo](./art/sparkLogo.png)

This repository contains a bunch of exercises resolved using Apache Spark and written in Scala. The exercises resolved use public APIs or open datasets in order to experiment with the different Apache Spark APIs. The goal is to practice and learn. Inside this repository you will find RDDs, DataSets, and DataFrames usage, Spark SQL queries, Spark Streaming examples and Machine Learning stuff :simley:.

## Exercises

This table contains all the information needed to understand every exercise resolved in this repository.

| Exercise # | Tests | Goals	| Statement |
|---	|---	|---	|---	|---	|
| [#1. El Quijote][1] |[El Quijote Spec][1spec] | Learn how to use Spark Context and some basic RDDs methods. | [El Quijote Statement](./statements/EL_QUIJOTE.md) |

## Build and test this project

To build and test this project you can execute ``sbt test``.

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

[1]: ./src/main/scala/com/github/pedrovgs/sparkplayground/exercise1/ElQuijote.scala
[1spec]: ./src/main/test/scala/com/github/pedrovgs/sparkplayground/exercise1/ElQuijoteSpec.scala 
