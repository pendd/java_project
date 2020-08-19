package com.pd.jwt.service;

import com.pd.jwt.entity.User;
import com.pd.jwt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dpeng
 */
@Service
public class UserService {
    @Autowired private UserMapper mapper;

    public User login(User user) {
        User returnUser = mapper.login(user);
        if (returnUser == null) throw new RuntimeException("登录失败");
        return returnUser;
    }
}
