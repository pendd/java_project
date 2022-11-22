package com.pd.producer.mapper.postgresql;

import com.pd.model.TOrder;
import org.apache.ibatis.annotations.Insert;

/** @author dpeng */
public interface OrderMapperPost {
  @Insert("insert into t_order values (#{id} ,#{name} ,#{messageId} )")
  void insert(TOrder order);
}
