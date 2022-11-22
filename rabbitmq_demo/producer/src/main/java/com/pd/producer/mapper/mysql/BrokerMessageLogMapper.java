package com.pd.producer.mapper.mysql;

import com.pd.model.BrokerMessageLog;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/** @author dpeng */
public interface BrokerMessageLogMapper {

  @Insert(
      "insert into broker_message_log(message_id, message, status, next_retry, create_time, update_time) values "
          + "(#{messageId} , #{message}, #{status} ,#{nextRetry}, #{createTime}, #{updateTime} )")
  void insert(BrokerMessageLog brokerMessageLog);

  /** 更新最终消息发送结果 成功 or 失败 */
  @Update(
      "update broker_message_log set status = #{status}  , update_time = #{updateTime}  where message_id = #{messageId} ")
  void changeBrokerMessageLogStatus(
      @Param("messageId") String messageId,
      @Param("status") String status,
      @Param("updateTime") LocalDateTime updateTime);

  /** 查询消息状态为0(发送中) 且已经超时的消息集合 */
  @Select("select * from broker_message_log where status = 0 and next_retry <= sysdate()")
  List<BrokerMessageLog> query4StatusAndTimeoutMessage();

  /** 重新发送统计count发送次数 +1 */
  @Update(
      "update broker_message_log set try_count = try_count + 1, update_time = #{updateTime}, next_retry = #{nextRetry} where "
          + "message_id = #{messageId} ")
  void update4ReSend(
      @Param("messageId") String messageId,
      @Param("updateTime") LocalDateTime updateTime,
      @Param("nextRetry") LocalDateTime nextRetry);
}
