package com.pd.producer.service;

import com.pd.model.BrokerMessageLog;
import com.pd.model.Score;
import com.pd.model.TOrder;
import com.pd.producer.constant.Constants;
import com.pd.producer.mapper.mysql.BrokerMessageLogMapper;
import com.pd.producer.mapper.mysql.MessageConsumeMapper;
import com.pd.producer.mapper.mysql.OrderMapper;
import com.pd.producer.mapper.mysql.ScoreMapper;
import com.pd.producer.mapper.postgresql.OrderMapperPost;
import com.pd.producer.mapper.postgresql.ScoreMapperPost;
import com.pd.producer.sender.MqSender;
import com.pd.producer.utils.DateUtils;
import com.pd.producer.utils.JacksonConvertUtil;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author dpeng */
@Service
public class MqService {

  @Autowired private OrderMapperPost orderMapperPost;
  @Autowired private ScoreMapperPost scoreMapperPost;
  @Autowired private BrokerMessageLogMapper brokerMessageLogMapper;
  @Autowired private MqSender mqSender;
  @Autowired private MessageConsumeMapper messageConsumeMapper;
  @Autowired private OrderMapper orderMapper;
  @Autowired private ScoreMapper scoreMapper;

  @Transactional(transactionManager = "postT")
  public void testInsert(TOrder order, Score score) {
    order.setScoreId(score.getId());
    scoreMapperPost.insert(score);
    orderMapperPost.insert(order);

    // 插入消息记录表数据
    BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
    // 消息唯一ID
    brokerMessageLog.setMessageId(order.getMessageId());
    // 保存消息整体 转为JSON 格式存储入库
    brokerMessageLog.setMessage(JacksonConvertUtil.convertObjectToJSON(order));
    // 设置消息状态为0 表示发送中
    brokerMessageLog.setStatus(Constants.ORDER_SENDING);
    // 设置消息未确认超时时间窗口为 一分钟
    brokerMessageLog.setNextRetry(
        DateUtils.addMinutes(LocalDateTime.now(), Constants.ORDER_TIMEOUT));
    brokerMessageLog.setCreateTime(LocalDateTime.now());
    brokerMessageLog.setUpdateTime(LocalDateTime.now());
    scoreMapperPost.inserts(brokerMessageLog);

    //    mqSender.testSend(order);
  }

  @Transactional
  public void testConsume(TOrder order) {
    messageConsumeMapper.insert(order.getMessageId());
    orderMapper.insert(order);
    //        int i = 1 / 0;
    Score score = new Score();
    score.setId(order.getScoreId());
    score.setAmount(100);
    scoreMapper.insert(score);
  }

  @Transactional(transactionManager = "postT")
  public void testConsumePostT(TOrder order) {
    orderMapperPost.insert(order);
    //        int i = 1 / 0;
    Score score = new Score();
    score.setId(order.getScoreId());
    score.setAmount(100);
    scoreMapperPost.insert(score);
  }
}
