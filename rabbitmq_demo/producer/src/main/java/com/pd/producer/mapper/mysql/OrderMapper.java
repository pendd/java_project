package com.pd.producer.mapper.mysql;

import com.pd.model.TOrder;
import org.apache.ibatis.annotations.Insert;

/** @author dpeng */
public interface OrderMapper {

  @Insert("insert into t_order values (#{id} ,#{name} ,#{messageId} )")
  void insert(TOrder order);
}
