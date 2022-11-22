package com.pd.producer.mapper.mysql;

import com.pd.model.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/** @author dpeng */
public interface ScoreMapper {

  @Insert("insert into t_score(id, amount) values (#{id} ,#{amount} )")
  void insert(Score score);

  @Select("select * from t_score where id = #{id} ")
  Score selectById(Integer id);

  @Insert("insert into t_score(id, amount,test_text) values (#{id} ,#{amount} ,#{testText} )")
  void insertTest(Score score);
}
