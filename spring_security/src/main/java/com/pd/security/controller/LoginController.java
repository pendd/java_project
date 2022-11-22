package com.pd.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: pd
 * @date: 2021-02-16 下午8:36
 */
@Controller
public class LoginController {

  @PostMapping("/toIndex")
  public String toIndex() {
    System.out.println("执行登录成功方法");
    return "redirect:index.html";
  }

  @PostMapping("/toError")
  public String toError() {
    System.out.println("执行登录失败方法");
    return "redirect:error.html";
  }
}
