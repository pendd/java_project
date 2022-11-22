package com.pd.producer.service;

import com.pd.model.TOrder;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** @author dpeng */
@SpringBootTest
class TOrderServiceTest {

  @Autowired private OrderService orderService;

  @Test
  void createOrder() {
    TOrder order = new TOrder();
    order.setId(2018092101);
    order.setName("测试订单1");
    order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
    orderService.createOrder(order);
  }

  @Test
  void createOrderAndBrokerMessage() {
    TOrder order = new TOrder();
    order.setId(2018092101);
    order.setName("测试订单1");
    order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
    orderService.createOrderAndBrokerMessage(order);
  }
}
