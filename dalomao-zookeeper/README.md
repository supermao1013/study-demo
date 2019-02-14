## dalomao-zookeeper
zookeeper客户端的使用，分为多种客户端

## 包路径说明
* client：zkClient客户端，在原生API基础上封装，是一个更易于使用的zookeeper客户端
* curator：curator客户端，在原生API基础上封装，apache顶级项目，目前基本都是用这个客户端，可以适用于分布式锁、Master选举等。Curator采用Fluent风格API
* election：利用zookeeper实现master选举
* lock：利用zookeeper实现分布式锁
* zk：zookeeper原生客户端API
