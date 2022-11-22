package com.pd.producer.service;

import com.pd.model.BrokerMessageLog;
import com.pd.model.TOrder;
import com.pd.producer.constant.Constants;
import com.pd.producer.mapper.mysql.BrokerMessageLogMapper;
import com.pd.producer.mapper.mysql.OrderMapper;
import com.pd.producer.sender.RabbitOrderSender;
import com.pd.producer.utils.DateUtils;
import com.pd.producer.utils.JacksonConvertUtil;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** @author dpeng */
@Service
public class OrderService {

  @Autowired private OrderMapper orderMapper;
  @Autowired private BrokerMessageLogMapper brokerMessageLogMapper;
  @Autowired private RabbitOrderSender rabbitOrderSender;

  public void createOrder(TOrder order) {
    createOrderAndBrokerMessage(order);
    // 发送消息
    rabbitOrderSender.sendOrder(order);
  }

  @Transactional(transactionManager = "mysqlTransactionManager")
  public void createOrderAndBrokerMessage(TOrder order) {
    // 使用当前时间当做订单创建时间（为了模拟一下简化）
    LocalDateTime orderTime = LocalDateTime.now();
    // 插入业务数据
    orderMapper.insert(order);
    // 插入消息记录表数据
    BrokerMessageLog BrokerMessageLog = new BrokerMessageLog();
    // 消息唯一ID
    BrokerMessageLog.setMessageId(order.getMessageId());
    // 保存消息整体 转为JSON 格式存储入库
    BrokerMessageLog.setMessage(JacksonConvertUtil.convertObjectToJSON(order));
    // 设置消息状态为0 表示发送中
    BrokerMessageLog.setStatus(Constants.ORDER_SENDING);
    // 设置消息未确认超时时间窗口为 一分钟
    BrokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
    BrokerMessageLog.setCreateTime(LocalDateTime.now());
    BrokerMessageLog.setUpdateTime(LocalDateTime.now());
    brokerMessageLogMapper.insert(BrokerMessageLog);
  }
}
