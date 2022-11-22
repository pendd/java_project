package com.pd.ioc.example.model.service;

import com.pd.ioc.annotation.MyBean;
import com.pd.ioc.example.model.User;

@MyBean("userService")
public class UserServiceImpl implements UserService {

  @Override
  public User getUserById(Long id) {
    User user = new User();
    if (id == 1) {
      user.setId(id);
      user.setName("张三");
    } else if (id == 2) {
      user.setId(id);
      user.setName("李四");
    }
    return user;
  }
}
