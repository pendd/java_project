package com.pd.producer.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pd.producer.constant.Constants;
import com.pd.producer.mapper.mysql.BrokerMessageLogMapper;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** @author dpeng */
@Configuration
@Slf4j
public class RabbitmqConfig {

  @Autowired private RabbitTemplate rabbitTemplate;
  @Autowired private BrokerMessageLogMapper brokerMessageLogMapper;

  @Bean
  public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }

  /**
   * 定制化amqp模版 可根据需要定制多个
   *
   * <p>
   *
   * <p>此处为模版类定义 Jackson消息转换器 ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调 即消息发送到exchange ack
   * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调 即消息发送不到任何一个队列中 ack
   *
   * @return the amqp template
   */
  @Bean
  public AmqpTemplate amqpTemplate() {

    // 使用jackson 消息转换器，如果不使用这个，默认会使用jdk的序列号，性能比较差
    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

    // 设置编码
    rabbitTemplate.setEncoding("UTF-8");
    // returnCallback需要设置true
    rabbitTemplate.setMandatory(true);

    // 一：confirmCallback 确认模式
    // 消息确认：yml需要配置 publisher-confirms: true
    // CachingConnectionFactory 的publisherConfirms 属性也需要设置为true
    // 确认的消息会根据它注册的RabbitTemplate.ConfirmCallback setConfirmCallback回调发送到给客户端
    // 一个RabbitTemplate也仅能支持一个ConfirmCallback
    // confirm 模式只能保证消息到达 broker，不能保证消息准确投递到目标 queue 里，所以要用到 returnCallback
    rabbitTemplate.setConfirmCallback(
        ((correlationData, ack, cause) -> {
          // 这里我们可以看到有correlationData这个实体参数
          // 消息信息和correlationData.id属性进行了绑定，我们若是可以根据id拿到消息，那么就可以进行“重试”或者“预警”等操作了。
          // 所以我们可以自定义基础correlationData来实现重试和预警等操作。建议是把这个内容给缓存起来，再用定时任务重发
          assert correlationData != null;
          String messageId = correlationData.getId();
          if (ack) {
            // 如果confirm返回成功 则进行更新
            brokerMessageLogMapper.changeBrokerMessageLogStatus(
                messageId, Constants.ORDER_SEND_SUCCESS, LocalDateTime.now());
            log.debug("消息发送到exchange成功,id: {}", messageId);
          } else {
            log.debug("消息发送到exchange失败,原因: {}", cause);
          }
        }));

    // 二：returnCallback 未投递到queue退回模式
    // 消息返回：发送到MQ失败之后返回到客户端的消息
    // 需要设置mandatory 属性为true，并且CachingConnectionFactory 的publisherReturns属性也需要设置为true（yml需要配置
    // publisher-returns: true）
    // 返回的消息会根据它注册的RabbitTemplate.ReturnCallback setReturnCallback 回调发送到给客户端
    // 一个RabbitTemplate仅能支持一个ReturnCallback

    rabbitTemplate.setReturnCallback(
        (message, replyCode, replyText, exchange, routingKey) -> {
          String correlationId = message.getMessageProperties().getCorrelationId();
          log.debug(
              "消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}",
              correlationId,
              replyCode,
              replyText,
              exchange,
              routingKey);
        });

    return rabbitTemplate;
  }

  // 本类绑定队列和交换机采用的是代码绑定，你也可以采用注解绑定
  // 为了配置方便，可以把交换机，队列，路由键都写到yml文件中，通过@Value来获取，例如
  //	@Value("${direct.exchange}")

  // topic 主题交换机的名字
  public static final String TOPIC_EXCHANGE = "TOPIC_EXCHANGE";

  // topic 队列名字
  public static final String TOPIC_QUEUE1 = "TOPIC_QUEUE1";
  public static final String TOPIC_QUEUE2 = "TOPIC_QUEUE2";

  // topic路由键
  public static final String TOPIC_ROUTING_KEY = "mes";

  /** 声明一个Topic交换机 */
  @Bean("topicExchange")
  public Exchange topicExchange() {
    return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE).durable(true).build();
  }

  /** 声明一个队列 */
  @Bean("topicQueue1")
  public Queue topicQueue1() {
    return QueueBuilder.durable(TOPIC_QUEUE1).build();
  }

  @Bean("topicQueue2")
  public Queue topicQueue2() {
    return QueueBuilder.durable(TOPIC_QUEUE2).build();
  }

  /**
   * 通过绑定路由键 将指定队列绑定到一个指定的交换机 .(完全匹配规则)
   *
   * @param queue the queue
   * @param exchange the exchange
   * @return the binding
   */
  @Bean
  public Binding topicBinding1(
      @Qualifier("topicQueue1") Queue queue, @Qualifier("topicExchange") Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(TOPIC_ROUTING_KEY).noargs();
  }

  @Bean
  public Binding topicBinding2(
      @Qualifier("topicQueue2") Queue queue, @Qualifier("topicExchange") Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(TOPIC_ROUTING_KEY).noargs();
  }
}
