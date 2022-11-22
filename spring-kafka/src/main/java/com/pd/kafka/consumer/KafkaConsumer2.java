package com.pd.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pd.kafka.entity.UploadData;
import com.pd.kafka.enums.DataTypeEnum;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author: YCWB0382
 * @date: 2021-06-12 23:15
 */
@Component
public class KafkaConsumer2 {

  @Autowired private RedisTemplate<String, Object> redisTemplate;
  @Autowired JdbcTemplate template;

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name("retryTopic_pdd").partitions(10).replicas(1).build();
  }

  @Bean
  public NewTopic topic2() {
    return TopicBuilder.name("topic_pdd").partitions(10).replicas(1).build();
  }

  //  @KafkaListener(groupId = "pd9", topics = "topic_pdd")
  @SendTo("retryTopic_pdd")
  @Transactional
  public String onMessage3(String stringData, Acknowledgment ack) {
    // 数据库操作成功，redis操作失败 数据库事务进行回滚 需要方法上加上@Transaction注解
    //    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    System.out.println("pd9开始消费---------------");
    // 消费的哪个topic、partition的消息,打印出消息内容
    System.out.println(stringData);
    UploadData data = null;
    try {
      data = new ObjectMapper().readValue(stringData, UploadData.class);
    } catch (JsonProcessingException e) {
      return null;
    }

    // 判断redis中是否存在
    redisTemplate.opsForList();

    template.update(
        String.format(
            "insert into student(id, age, name) values('%s','%s','%s')",
            data.getStudent().getId(), data.getStudent().getAge(), data.getStudent().getName()));
    UploadData uploadData =
        new UploadData(
            UUID.randomUUID().toString(),
            data.getValue(),
            System.currentTimeMillis() + 1000 * 60 * 1,
            1,
            DataTypeEnum.TOPIC_TYPE.getValue());
    uploadData.setStudent(data.getStudent());

    ack.acknowledge();
    try {
      return new ObjectMapper().writeValueAsString(uploadData);
    } catch (JsonProcessingException e) {
      System.err.println("json 格式转换异常");
    }
    return null;
  }

  @KafkaListener(groupId = "pd9", topics = "topic_pdd")
  @Transactional
  public String onMessage3(String stringData) {
    // 数据库操作成功，redis操作失败 数据库事务进行回滚 需要方法上加上@Transaction注解
    //    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    System.out.println("pd9开始消费---------------");
    // 消费的哪个topic、partition的消息,打印出消息内容
    System.out.println(stringData);
    UploadData data = null;
    try {
      data = new ObjectMapper().readValue(stringData, UploadData.class);
    } catch (JsonProcessingException e) {
      return null;
    }
    int i = 1 / 0;
    template.update(
        String.format(
            "insert into student(id, age, name) values('%s','%s','%s')",
            data.getStudent().getId(), data.getStudent().getAge(), data.getStudent().getName()));
    return null;
  }

  //  @KafkaListener(id = "zhangchi_test", topics = "retryTopic_pdd")
  public void retry(String stringData, Acknowledgment ack) {
    System.out.println("zhangchi_test开始执行---------" + stringData);
    UploadData data = null;
    try {
      data = new ObjectMapper().readValue(stringData, UploadData.class);
    } catch (JsonProcessingException e) {
      System.out.println("json格式化错误");
      return;
    }
    long currentTimeMillis = System.currentTimeMillis();
    if (data.getNextRetryTime() >= currentTimeMillis) {
      try {
        System.out.println("开始休眠----------------");
        Thread.sleep(data.getNextRetryTime() - currentTimeMillis);
      } catch (InterruptedException e) {
        System.err.println("线程睡眠异常");
      }
    }

    System.out.println("retryTopic_pdd begin consuming, uuid:" + data.getUuid());
    System.out.println(data);
    ack.acknowledge();
  }
}
