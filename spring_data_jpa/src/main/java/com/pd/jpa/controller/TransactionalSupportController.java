package com.pd.jpa.controller;

import com.pd.jpa.service.TransactionalSupportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: pd
 * @date: 2021-03-14 下午1:19
 */
@RestController
public class TransactionalSupportController {

  @Resource private TransactionalSupportService service;

  @GetMapping("/insert")
  public void test() {
    service.insertStudent();
  }

  @GetMapping("/find")
  public void find() {
    service.getPerson();
  }
}
