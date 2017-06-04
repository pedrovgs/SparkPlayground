# Build Executions

Build tools are used around the world to easily build software. Most of this build tools split the build in different tasks with different responsibilities and names. [Kuronometer](https://github.com/pedrovgs/Kuronometer) is a Gradle plugin which times every build task execution and saves the result into a file using this format:

```
preBuild,1024249,1495529611460
preBetaDebugBuild,392334,1495529611482
checkBetaDebugManifest,488727,1495529611483
preBetaReleaseBuild,351065,1495529611483
preProductionDebugBuild,259023,1495529611484
```

The first column contains the name of the task,  the second one the execution time in milliseconds, and the third column the task execution timestamp.

Using a file obtained from a Java project using Gradle and Kuronometer you can find in the project resources directory, could you answer the following questions using Spark?

* Sum the execution times associated to every task sorted by the execution times using a descending sort.
* Transform the execution time to minutes.
* Get a list of all the tasks executed.
* Get a list of the slowest tasks.
* Get a list of the fastest tasks.
* Get a list of the task names sorted by task name.
* Get a Scala Map[String, Long] keeping just the values with execution times greater or equal to 1 second.
* Get the total execution time.