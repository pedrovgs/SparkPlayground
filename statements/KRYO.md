# Kryo

Apache Spark’s default serialization relies on Java with the default readObject(…) and writeObject(…) methods for all Serializable classes. Remember that every task run from Driver to Worker gets serialized and every result from every task gets serialized at some point. This is a very fine default behavior as long as you don’t rely on it too much. Why ? Because Java’s serialization framework is notoriously inefficient, consuming too much CPU, RAM and size to be a suitable large scale serialization format.

As we've already used Google protocol buffers in a previous exercise this time we will try another serialization library named Kryo. Kryo is a fast and efficient object graph serialization framework for Java and Scala. The goals of the project are speed, efficiency, and an easy to use API. The project is useful any time objects need to be persisted, whether to a file, database, or over the network.

Using Spark and Kryo could you find the fastest of the strongests pokemons?
