package com.pd.producer.receiver;

import com.pd.model.TOrder;
import com.pd.producer.amqp.RabbitmqConfig;
import com.pd.producer.mapper.mysql.MessageConsumeMapper;
import com.pd.producer.mapper.mysql.ScoreMapper;
import com.pd.producer.mapper.postgresql.ScoreMapperPost;
import com.pd.producer.service.MqService;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/** @author dpeng */
@Slf4j
@Component
public class MqReceiver {

  @Autowired private MessageConsumeMapper messageConsumeMapper;
  @Autowired private ScoreMapper scoreMapper;
  @Autowired private ScoreMapperPost scoreMapperPost;
  @Autowired private MqService mqService;

  @RabbitListener(
      bindings =
          @QueueBinding(
              value = @Queue(value = RabbitmqConfig.TOPIC_QUEUE1, durable = "true"),
              exchange =
                  @Exchange(value = RabbitmqConfig.TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
              key = RabbitmqConfig.TOPIC_ROUTING_KEY))
  public void testReceive(
      @Payload TOrder order, @Headers Map<String, Object> headers, Channel channel)
      throws IOException {
    log.info("---------收到消息，开始消费---------");
    log.info(String.format("订单ID：%d", order.getId()));

    Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
    channel.basicAck(deliveryTag, false);

    /*MessageConsume messageConsume = messageConsumeMapper.selectByMessageId(order.getMessageId());
    try {
      if (messageConsume != null) log.info(String.format("消息ID：%s 已被消费", order.getMessageId()));
      else {
        Score score = scoreMapperPost.selectById(order.getScoreId());
        if (score == null || !score.getFlag()) log.info("触发器触发数据有误");
        else {
          mqService.testConsume(order);
        }
      }
      */
    /*
     Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag， 以便 Consumer
     可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。 RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
    */
    /*
    Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

    // ACK,确认一条消息已经被消费
    */
    /* multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认 如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认 */
    /*
      channel.basicAck(deliveryTag, false);
      log.info("----消费成功-----");
    } catch (Exception e) {
      // todo 对消费失败的消息记录在DB中  做轮询三次和  发送消息失败效果类似
      log.info("----消费异常，消息重回队列-----");
      Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
      // ack返回false，并重新回到队列
      //      channel.basicNack(deliveryTag, false, true);
      channel.basicReject(deliveryTag, false);
    }*/
  }
}
