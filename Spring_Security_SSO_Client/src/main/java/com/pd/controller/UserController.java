package com.pd.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: pd
 * @date: 2021-02-18 下午12:04
 */
@Controller
@RequestMapping("user")
public class UserController {
  @RequestMapping("getCurrentUser")
  @ResponseBody
  public Object getCurrentUser(Authentication authentication) {
    // return authentication.getPrincipal();
    return authentication;
  }
}
