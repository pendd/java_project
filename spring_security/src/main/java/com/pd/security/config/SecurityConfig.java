package com.pd.security.config;

import com.pd.security.handler.MyAuthenticationFailureHandler;
import com.pd.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: pd
 * @date: 2021-02-16 下午8:13
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
        .loginProcessingUrl("/login")
        .loginPage("/login.html")
        // 登录成功后跳转页面  post 请求
        //        .successForwardUrl("/toMain")
        // 登录成功后处理器  不能和 successForwardUrl 共存
        .successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com"))
        // 登录失败后跳转页面 post 请求
        //        .failureForwardUrl("/toError")
        .failureHandler(new MyAuthenticationFailureHandler("/error.html"))
        .and()
        .authorizeRequests()
        .antMatchers("/error.html")
        .permitAll()
        .antMatchers("/login.html")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable();
  }

  // 容器内必须存在PasswordEncoder实例
  @Bean
  public PasswordEncoder getPw() {
    return new BCryptPasswordEncoder();
  }
}
