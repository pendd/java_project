package com.pd.oauth.config;

import com.pd.oauth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 授权服务器配置
 *
 * @author: pd
 * @date: 2021-02-17 下午9:54
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Resource private PasswordEncoder passwordEncoder;

  @Resource private AuthenticationManager authenticationManager;

  @Resource private UserDetailsServiceImpl userDetailsService;

  //  @Resource
  //  @Qualifier("redisTokenStore")
  //  private RedisTokenStore redisTokenStore;

  @Resource
  @Qualifier("jwtTokenStore")
  private TokenStore jwtTokenStore;

  @Resource
  @Qualifier("jwtAccessTokenConverter")
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Resource
  @Qualifier("jwtTokenEnhancer")
  private TokenEnhancer jwtTokenEnhancer;

  // 使用密码模式所需配置
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

    // 配置JWT内容增强器
    TokenEnhancerChain chain = new TokenEnhancerChain();
    List<TokenEnhancer> delegates = new ArrayList<>();
    delegates.add(jwtTokenEnhancer);
    delegates.add(jwtAccessTokenConverter);
    chain.setTokenEnhancers(delegates);

    endpoints
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService)
        //        .tokenStore(redisTokenStore)
        .tokenStore(jwtTokenStore)
        .accessTokenConverter(jwtAccessTokenConverter)
        .tokenEnhancer(chain);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients
        .inMemory()
        // 配置client-id
        .withClient("admin")
        // 配置client-secret
        .secret(passwordEncoder.encode("112233"))
        // 配置访问token的有效期
        .accessTokenValiditySeconds(3600)
        // 配置RefreshToken的有效期
        .refreshTokenValiditySeconds(864000)
        // 配置redirect_uri, 用于授权成功后跳转
        .redirectUris("http://localhost:8081/login")
        // 配置自动授权
        //        .autoApprove(true)
        // 配置申请的权限范围
        .scopes("all")
        // 配置grant_type, 表示授权类型  授权码模式
        //        .authorizedGrantTypes("authorization_code");
        .authorizedGrantTypes("password", "refresh_token", "authorization_code");
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    // 获取密钥需要身份认证，使用单点登录必须配置
    security.tokenKeyAccess("isAuthenticated()");
  }
}
