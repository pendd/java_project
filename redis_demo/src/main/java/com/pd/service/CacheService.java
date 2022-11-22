package com.pd.service;

import com.pd.config.Constent;
import com.pd.pojo.Msg;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-06 9:52 下午
 */
@Service
public class CacheService {
  @Autowired JdbcTemplate template;

  // 一个非缓存方法调用缓存方法必须不在同一个类中，要不然缓存失效
  @Cacheable(value = Constent.SQL_KEY, keyGenerator = "myKeyGenerator")
  public List<Msg> getForMysql() {
    return template.query("select * from msg", new BeanPropertyRowMapper<>(Msg.class));
  }


  @Cacheable(value = Constent.SQL_KEY, key = "#name")
  public List<Msg> getMsg(String name) {return null;}


}
