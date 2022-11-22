package com.pd.security.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 实现SpringSecurity的UserDetailsService接口
 *
 * @author: pd
 * @date: 2021-02-16 下午8:15
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource private PasswordEncoder passwordEncoder;

  /** 实现自定义登录逻辑 */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    System.out.println("执行了loadUserByUsername方法");

    // 1、通过提供的用户名参数访问数据库，查询记录返回过来，如果记录不存在则抛出异常
    if (!"admin".equals(username)) throw new RuntimeException();

    // 2、查询出来的凭证是被加密了的，这里是模拟查询的密码
    String encode = passwordEncoder.encode("123456");

    // 权限不可以为空，所以需要这么一个工具方法简单实现
    return new User(
        username,
        encode,
        AuthorityUtils.commaSeparatedStringToAuthorityList(
            "admin,normal,ROLE_vip-01,ROLE_A,ROLE_B,ROLE_C,/create,/update,/delete"));
  }
}
