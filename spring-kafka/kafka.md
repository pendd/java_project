> 创建topic

``
kafka-topics.sh --create --topic topicname --replication-factor 1 --partitions 1 --zookeeper localhost:2181 或 kafka-topics.sh --create --topic topicname --replication-factor 1 --partitions 1 --bootstrap-server localhost:9092
``

> 查看所有topic

``
kafka-topics.sh --list --zookeeper localhost:2181
``

> 查看topic详情

``
kafka-topics.sh --zookeeper localhost:2181 --topic topicname --describe
``

> 查看消费者组

``
新（保存在broker中） kafka-consumer-groups.sh --new-consumer --bootstrap-server 127.0.0.1:9092 --list 旧（保存在zk中） kafka-consumer-groups.sh --zookeeper 127.0.0.1:2181 --list
``

> 查看消费者组详情

``
新 kafka-consumer-groups.sh --new-consumer --bootstrap-server 127.0.0.1:9092 --group lx_test --describe 旧 kafka-consumer-groups.sh --zookeeper 127.0.0.1:2181 --group console-consumer-11967 --describe
``











