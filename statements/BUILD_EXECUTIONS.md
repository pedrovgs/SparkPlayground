# Build Executions

Build tools are used around the world to easily build software. Most of this build tools split the build in different tasks with different responsibilities and names. [Kuronometer](https://github.com/pedrovgs/Kuronometer) is a Gradle plugin which times every build task execution and saves the result into a csv file using this format:

name,executionTime,timestamp
clean,47067826,1495494522205
clean,88495683,1495494522331
clean,368078545,1495494522700
androidJavadocs,2671770875,1495494525373
androidJavadocsJar,87776466,1495494525461
androidSourcesJar,33206924,1495494525495

The first column contains the name of the task, the second one the execution time in milliseconds and the third one the execution timestamp.

Using a csv file obtained from a Java project using Gradle and Kuronometer you can find in the project resources directory, could you answer the following questions using Spark?

* Sum the execution times associated to every task.
* Transform the execution time to minutes.
* Get a list of all the tasks executed.
* Get a list of the slowest tasks.
* Get a list of the fastest tasks.
* Get a list of the task names sorted by task name.
* Get a Scala Map[String, Long] keeping just the values with execution times greater or equal to 1 second.
* Get the total execution time.