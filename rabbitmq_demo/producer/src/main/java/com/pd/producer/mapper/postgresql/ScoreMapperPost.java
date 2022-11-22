package com.pd.producer.mapper.postgresql;

import com.pd.model.BrokerMessageLog;
import com.pd.model.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/** @author dpeng */
public interface ScoreMapperPost {

  @Insert("insert into t_score(id, amount) values (#{id} ,#{amount} )")
  void insert(Score score);

  @Select("select * from t_score where id = #{id} ")
  Score selectById(Integer id);

  @Insert("insert into t_score(id, amount,test_text) values (#{id} ,#{amount} ,#{testText} )")
  void insertTest(Score score);

  @Insert(
      "insert into broker_message_log(message_id, message, status, next_retry, create_time, update_time) values "
          + "(#{messageId} , #{message}, #{status} ,#{nextRetry}, #{createTime}, #{updateTime} )")
  void inserts(BrokerMessageLog brokerMessageLog);
}
