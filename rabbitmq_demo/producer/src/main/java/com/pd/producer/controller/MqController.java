package com.pd.producer.controller;

import com.pd.model.Score;
import com.pd.model.SendVo;
import com.pd.producer.mapper.postgresql.ScoreMapperPost;
import com.pd.producer.service.MqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** @author dpeng */
@RestController
@Slf4j
public class MqController {

  @Autowired private MqService mqService;
  @Autowired private ScoreMapperPost scoreMapperPost;

  @PostMapping("/testSend")
  public String testSend(@RequestBody SendVo vo) {
    mqService.testInsert(vo.getOrder(), vo.getScore());
    Score getScore = scoreMapperPost.selectById(vo.getOrder().getScoreId());
    if (getScore == null || !getScore.getFlag()) return "触发器触发数据有误";
    mqService.testConsume(vo.getOrder());
    return "true";
  }
}
