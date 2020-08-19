package com.pd.service;

import com.pd.config.Constent;
import com.pd.pojo.Msg;
import com.pd.utils.RedisUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-06 8:12 下午
 */
@Service
public class RedisService {

  @Autowired JdbcTemplate template;

  @Autowired private RedisUtil util;
  @Autowired CacheService cacheService;

  @Transactional
  public void set(Integer id, String message) {
    // websocket 传来的数据添加到 key为 data的 缓存中
    util.lSet(Constent.DATA_KEY, new Msg(id, message));
    // 获取key为data的大小，如果超过规定数量，就写入到数据库中
    long size = util.lGetListSize(Constent.DATA_KEY);
    if (size >= Constent.NUM) {
      // 从下标为0开始获取指定长度的缓存数据插入到数据库中
      List<Object> list = util.lGet(Constent.DATA_KEY, 0, Constent.NUM - 1);
      if (CollectionUtils.isEmpty(list)) return;
      list.forEach(
          e -> {
            if (e instanceof Msg) {
              Msg msg = (Msg) e;
              template.update(
                  String.format(
                      "insert into msg(id,message) values('%s','%s')",
                      msg.getId(), msg.getMessage()));
            }
          });
      // 截取剩下的部分继续留在key为data的缓存中，这个就是删除已经插入到数据库中的那些数据
      util.lTrim(Constent.DATA_KEY, Constent.NUM, -1);
      // 数据库已更新，则让CacheService 里的缓存过期，Redis的string 类型是没法直接删除的，
      // 只能设置过期时间，这个1秒后，缓存就没了，再次调用查询方法时，就会重新从数据库中查数据而不是从缓存中，因为已经没有了缓存
      util.expire(Constent.SQL_KEY, 1);
    }
  }

  public List<Msg> getAll() {
    // 获取数据库中数据
    List<Msg> returnList = cacheService.getForMysql();
    // 获取key为data中的数据
    List<Object> list = util.lGet(Constent.DATA_KEY, 0, -1);
    if (CollectionUtils.isEmpty(list)) return returnList;
    list.forEach(
        e -> {
          if (e instanceof Msg) {
            returnList.add((Msg) e);
          }
        });
    // 返回总得数据为 数据库 + data缓存中的数据
    return returnList;
  }
}
