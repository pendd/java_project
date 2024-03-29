package com.pd.consumer.receiver;

import com.pd.model.TOrder;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderReceiver {
  // 配置监听的哪一个队列，同时在没有queue和exchange的情况下会去创建并建立绑定关系
  @RabbitListener(
      bindings =
          @QueueBinding(
              value = @Queue(value = "order-queue", durable = "true"),
              exchange = @Exchange(name = "order-exchange", durable = "true", type = "topic"),
              key = "order.*"))
  @RabbitHandler // 如果有消息过来，在消费的时候调用这个方法
  public void onOrderMessage(
      @Payload TOrder order, @Headers Map<String, Object> headers, Channel channel)
      throws IOException {
    // 消费者操作
    log.info("---------收到消息，开始消费---------");
    log.info(String.format("订单ID：%d", order.getId()));

    /*
     Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag， 以便 Consumer
     可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。 RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
    */
    Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

    // ACK,确认一条消息已经被消费
    /* multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认 如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认 */
    channel.basicAck(deliveryTag, false);
  }
}
