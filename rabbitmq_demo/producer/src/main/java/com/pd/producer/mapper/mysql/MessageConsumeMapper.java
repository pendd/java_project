package com.pd.producer.mapper.mysql;

import com.pd.model.MessageConsume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/** @author dpeng */
public interface MessageConsumeMapper {
  @Insert("insert into message_consume(message_id) values(#{messageId}) ")
  void insert(String messageId);

  @Select("select * from message_consume where message_id = #{messageId} ")
  MessageConsume selectByMessageId(String messageId);
}
