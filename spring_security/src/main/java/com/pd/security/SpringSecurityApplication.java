package com.pd.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author: pd
 * @date: 2021-02-16 下午7:37
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityApplication.class, args);
  }
}
