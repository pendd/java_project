package com.pd.producer.sender;

import com.pd.model.TOrder;
import com.pd.producer.amqp.RabbitmqConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** @author dpeng */
@Component
public class MqSender {
  @Autowired private RabbitTemplate rabbitTemplate;

  public void testSend(TOrder order) {
    // 消息唯一ID
    CorrelationData correlationData = new CorrelationData(order.getMessageId());
    rabbitTemplate.convertAndSend(
        RabbitmqConfig.TOPIC_EXCHANGE, RabbitmqConfig.TOPIC_ROUTING_KEY, order, correlationData);
  }
}
