# SETS

After some exercises, we've looked at a number of operations we can perform on our data ounce we have it distributed in Spark. So far our examples have loaded and saved all of their data from a native collection or regular files, so it's time to explore our options for loading and saving data using different formats.

Today we are going to use different formats for reading and writing data using Apache Spark. Could implement the following list of exercises using Spark?

* Read a simple text file from the resources folder, capitalize it and write it down into another text file.
* Read a JSON file containing some information about users randomly obtained from the resources folder, sort these users by their last name and save the result into a json file.
* Read a CSV file containing information related to video games sales obtained from the resources folder, extract the information related to just GameBoy games, and save this information as a CSV file.
* Using the user data structure already created for the previous exercise, serialize some random users into a Protocol Buffers file, read it back, and check if everything is working as it should.
* Using the video game data structure already created for a previous exercise, serialized some random video games into a object file, read it back, and check if everything is working as it should.
* Using any of the previous already implemented files, compress it and read it as a compressed file using gzip.