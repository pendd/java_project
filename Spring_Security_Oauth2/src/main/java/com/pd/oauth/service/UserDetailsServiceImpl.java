package com.pd.oauth.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: pd
 * @date: 2021-02-17 下午9:15
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String password = passwordEncoder.encode("123456");
    return new User("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }
}
