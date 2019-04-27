# 版本

Mongodb服务端：3.4.20

原生Java驱动：3.5.0

pring-data-mongodb：1.10.9.RELEASE

spring：4.3.13.RELEASE

# 快速入门

安装单机版本参考官网：https://docs.mongodb.com/v3.4/tutorial/install-mongodb-on-red-hat/

快速入门代码：src/test/com/dalomao/mongodb/quickstart

快速入门提供功能：

* 原生Java客户端针对Document操作

* 原生Java客户端针对Pojo操作

* spring客户端针对Pojo操作

* 针对评论的分页加载

* 若评论过多，导致单个bson文档最大不能超过16M，则使用DBRef分集合存储，进行联合查询

# 测试数据

* easy包测试请使用：data/1.测试数据users.txt

* comment包测试请使用：data/2.测试数据users2内嵌comments.txt

* dbref包测试请使用：data/3.测试数据user3 DBRef查询comments.txt（注意：需要先生成comments集合，然后取其中一个文档的ID赋值给user1）

