package com.pd.producer.sender;

import com.pd.model.TOrder;
import com.pd.producer.constant.Constants;
import com.pd.producer.mapper.mysql.BrokerMessageLogMapper;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** @author dpeng */
@Component
@Slf4j
public class RabbitOrderSender {

  @Autowired private RabbitTemplate rabbitTemplate;
  @Autowired private BrokerMessageLogMapper brokerMessageLogMapper;

  // 发送消息方法调用：构建自定义对象消息
  public void sendOrder(TOrder order) {
    // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
    rabbitTemplate.setConfirmCallback(
        (CorrelationData correlationData, boolean ack, String cause) -> {
          log.info(String.format("correlationData: %s", correlationData));
          assert correlationData != null;
          String messageId = correlationData.getId();
          if (ack) {
            // 如果confirm返回成功 则进行更新
            brokerMessageLogMapper.changeBrokerMessageLogStatus(
                messageId, Constants.ORDER_SEND_SUCCESS, LocalDateTime.now());
          } else {
            // 失败则进行具体的后续操作:重试 或者补偿等手段
            log.info("异常处理...");
          }
        });
    // 消息唯一ID
    CorrelationData correlationData = new CorrelationData(order.getMessageId());
    rabbitTemplate.convertAndSend("order-exchange", "order.ABC", order, correlationData);
  }
}
