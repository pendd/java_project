package com.pd.producer.task;

import com.pd.model.BrokerMessageLog;
import com.pd.model.TOrder;
import com.pd.producer.constant.Constants;
import com.pd.producer.mapper.mysql.BrokerMessageLogMapper;
import com.pd.producer.sender.RabbitOrderSender;
import com.pd.producer.utils.DateUtils;
import com.pd.producer.utils.JacksonConvertUtil;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RetryMessageTasker {

  @Autowired private RabbitOrderSender rabbitOrderSender;
  @Autowired private BrokerMessageLogMapper brokerMessageLogMapper;

  @Scheduled(initialDelay = 5000, fixedDelay = 10000)
  public void reSend() {
    log.info("-----------定时任务开始-----------");
    // pull status = 0 and timeout message
    List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
    list.forEach(
        messageLog -> {
          if (messageLog.getTryCount() >= 3) {
            // update fail message
            brokerMessageLogMapper.changeBrokerMessageLogStatus(
                messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE, LocalDateTime.now());
          } else {
            // resend
            brokerMessageLogMapper.update4ReSend(
                messageLog.getMessageId(),
                LocalDateTime.now(),
                DateUtils.addMinutes(LocalDateTime.now(), Constants.ORDER_TIMEOUT));
            TOrder reSendOrder = JacksonConvertUtil.convertJSONToObject(messageLog.getMessage());
            try {
              rabbitOrderSender.sendOrder(reSendOrder);
            } catch (Exception e) {
              log.debug("-----------异常处理111-----------");
            }
          }
        });
  }
}
