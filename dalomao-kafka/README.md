## dalomao-kafka

kafka快速入门，提供生产者和消费者代码

# 单机版kafka搭建

1.下载kafka
	地址：http://kafka.apache.org/downloads

2.上传安装包到服务器上面，并且进行解压
	tar xzvf kafka_2.11-0.10.1.0.tgz

3.kafka需要安装zookee使用，但kafka集成zookeeper,在单机搭建时可直接使用。使用需配置kafka/config 下的"zookeeper.properties"
	zk快照存放地址(地址可以随便配置)：dataDir=/usr/local/kafka/tmp/zookeeper
	客户端访问端口：clientPort=2181

4.配置kafka/config下的"server.properties"，修改log.dirs和zookeeper.connect。前者是日志存放文件夹，后者是zookeeper连接地址（端口和clientPort保持一致）
	log.dirs=/usr/local/kafka/tmp/kafka-logs
	zookeeper.connect=localhost:2181

至此，创建完毕！

# kafka相关命令

启动kafka：

	(1)开启kafka自带zookeeper(如果zookeeper单独安装的，需要先启动zookeeper)
		cd /usr/local/kafka
		nohup bin/zookeeper-server-start.sh config/zookeeper.properties > zookeeper-run.log 2>&1 &

	(2)开启kafka
		cd /usr/local/kafka
		nohup bin/kafka-server-start.sh config/server.properties > kafka-run.log 2>&1 &
		
创建kafka主题：
	bin/kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic test-topic		
	说明：--replication-factor复制因子为1，--partitions分区为1
	
显示kafka所有主题：		
	bin/kafka-topics.sh -list --zookeeper 127.0.0.1:2181
		
创建kafka生产者：
	bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic test-topic
	说明：--broker-list表示代理服务器的列表，这里只有一个，默认端口为9092，可自行更改
	
创建kafka消费者：
	bin/kafka-console-consumer.sh --zookeeper 127.0.0.1:2181 --topic test-topic --from-beginning
	说明：--from-beginning表示从消息开始处读取
		
【注意】JAVA客户端测试时候，一定要设置config/server.properties文件中的属性 advertised.listeners=PLAINTEXT://IP:9092 ，其中IP为kafka安装的机器IP