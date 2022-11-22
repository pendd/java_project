package com.pd.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author: pd
 * @date: 2021-02-18 上午10:52
 */
@Configuration
public class JwtTokenStoreConfig {

  @Bean
  public TokenStore jwtTokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    // 设置签名密钥
    jwtAccessTokenConverter.setSigningKey("sign_key");
    return jwtAccessTokenConverter;
  }

  @Bean
  public JwtTokenEnhancer jwtTokenEnhancer() {
    return new JwtTokenEnhancer();
  }
}
