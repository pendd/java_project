package com.pd.ioc.example.controller;

import com.pd.ioc.annotation.AutoInject;
import com.pd.ioc.annotation.MyBean;
import com.pd.ioc.example.model.User;
import com.pd.ioc.example.service.UserService;

@MyBean("userController")
public class UserController {

  @AutoInject UserService userService;

  public User getUserById(Long id) {
    return userService.getUserById(id);
  }
}
