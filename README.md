# samza-approximation-store
Approximation algorithms as Store for Samza Tasks

One of the key aspects of Apache Samza is it's first-class support for "local" state for all the stream tasks. And, the nice thing about it is, it's managed by Samza - which means, users of the framework need to only specify what kind of store they want and recovery of the store in case of failures is handled by Samza. Read Samza documentation for how it works. As of latest version of Samza, there's support for an in-memory store and RocksDB store. 

But, there are lot of scenarios where we might need other kind of stores. For some of the stuff that I work, I've had a need for approximate stores - so that, I don't have a huge impact on the memory footprint of the tasks, but, at the same time, have a good way to perform some basic operations like - set membership or unique count and the likes.

This project aims to add support for such stores for Samza.

Algorithm catalog:
- [x] Set membership - Bloomfilter
- [ ] Unique count - HyperLogLog
