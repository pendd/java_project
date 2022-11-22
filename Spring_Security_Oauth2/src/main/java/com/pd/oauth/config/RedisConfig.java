package com.pd.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author: pd
 * @date: 2021-02-17 下午11:15
 */
// @Configuration
public class RedisConfig {

  @Resource private RedisConnectionFactory redisConnectionFactory;

  @Bean
  public TokenStore redisTokenStore() {
    return new RedisTokenStore(redisConnectionFactory);
  }
}
