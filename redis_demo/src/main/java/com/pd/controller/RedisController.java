package com.pd.controller;

import com.pd.pojo.Msg;
import com.pd.service.RedisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-06 8:12 下午
 */
@RestController
public class RedisController {

  @Autowired private RedisService service;

  @GetMapping("/set")
  public void set(Integer id, String msg) {
    service.set(id, msg);
  }

  @GetMapping("get")
  public List<Msg> get() {
    return service.getAll();
  }
}
