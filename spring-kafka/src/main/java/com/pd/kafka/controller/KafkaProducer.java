package com.pd.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pd.kafka.entity.Student;
import com.pd.kafka.entity.UploadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author: YCWB0382
 * @date: 2021-06-12 21:24
 */
@RestController
@RequestMapping("producer")
public class KafkaProducer {

  @Autowired private KafkaTemplate<String, Object> kafkaTemplate;

  // 发送消息
  @GetMapping("/normal")
  public void sendMessage1(Student student) throws JsonProcessingException {
    kafkaTemplate.send(
        "topic_pdd",
        new ObjectMapper()
            .writeValueAsString(new UploadData(UUID.randomUUID().toString()).setStudent(student)));
  }
}
