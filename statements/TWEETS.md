# Sets

Inside the resources folder we've placed a file named ``tweets.csv``. This file contains 400000 tweets classified with the following information separated by comas:
 
 * sentiment (0 as sentiment, marks a negative sentiment classification and 4 a positive sentiment classification)
 * tweetId
 * publicationDate
 * query
 * author
 * content
 
Using this file and Apache Spark answer the following questions:
 
 * Find the author with more tweets in the file.
 * Count the number of positive tweets.
 * Sum the number of words in the positive tweets.
 * Sum the number of words in the negative tweets.
 * Using the smaller file named ``tweets2.csv`` create a bigger RDD and count the number of words associated to positive or negative tweets.
 * For every partition in the tweets RDD execute a ``time`` command.
 * Filter just the positive tweets and adjust the number of partitions making them slower.
 * Repartition the tweets data set to just 2 partitions manually.
 * Repartition and sort tweets by author.


**As most of the questions should be answered using pair RDDs, don't use Spark SQL to answer these questions, use just regular pair RDDs please.**

***Think if you should ``cache`` or ``persist`` some of your RDDs! The file named ``tweets.csv`` contains 40000 tweets.***
 