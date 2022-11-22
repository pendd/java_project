package com.pd.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author: YCWB0382
 * @date: 2021-06-12 21:26
 */
// @Component
public class KafkaConsumer {
  // 消费监听
  //  @KafkaListener(
  //      groupId = "group1",
  //      topicPartitions = {@TopicPartition(topic = "topic4", partitions = "0")})
  @KafkaListener(groupId = "group1", topics = "topic4")
  public void onMessage1(ConsumerRecord<?, ?> record) {
    System.out.println("partitions0开始消费---------------");
    // 消费的哪个topic、partition的消息,打印出消息内容
    System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    System.out.println("--------------------------");
    System.out.println("--------------------------");
  }

  //  @KafkaListener(
  //      groupId = "group1",
  //      topicPartitions = {@TopicPartition(topic = "topic4", partitions = "1")})
  @KafkaListener(groupId = "group1", topics = "topic4")
  public void onMessage2(ConsumerRecord<?, ?> record) {
    System.out.println("partitions1开始消费---------------");
    // 消费的哪个topic、partition的消息,打印出消息内容
    System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    System.out.println("--------------------------");
    System.out.println("--------------------------");
  }
}
