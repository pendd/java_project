package com.pd.producer.service;

import com.pd.model.Score;
import com.pd.model.TOrder;
import com.pd.producer.mapper.postgresql.ScoreMapperPost;
import java.util.List;
import java.util.UUID;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** @author dpeng */
@SpringBootTest
class MqServiceTest {

  @Autowired private MqService mqService;
  @Autowired private ScoreMapperPost scoreMapperPost;

  @Test
  void testInsert() {
    TOrder order = new TOrder();
    order.setId(20200612);
    order.setName("订单no.162");
    order.setMessageId(UUID.randomUUID().toString());
    List<Integer> ids = Lists.newArrayList();
    for (int i = 0; i < 1000000; i++) {
      ids.add(i);
    }
    order.setIds(ids);
    Score score = new Score();
    score.setId(192);
    score.setAmount(100);

    mqService.testInsert(order, score);
  }

  @Test
  void testConsume() {
    /*TOrder order = new TOrder();
    order.setId(2020061603);
    order.setName("订单no.18");
    order.setMessageId(UUID.randomUUID().toString());
    mqService.testConsume(order);*/
    Score score = new Score();
    score.setId(1111);
    score.setAmount(10);
    String s = "";
    for (int i = 0; i < 100000; i++) {
      s += i + ",";
    }
    score.setTestText(s);
    scoreMapperPost.insertTest(score);
  }
}
