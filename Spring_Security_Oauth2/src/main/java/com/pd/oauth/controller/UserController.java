package com.pd.oauth.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author: pd
 * @date: 2021-02-17 下午10:10
 */
@RestController
@RequestMapping("/user")
public class UserController {
  /**
   * 获取当前用户
   *
   * @param authentication
   * @return user/getCurrentUser
   */
  @RequestMapping("/getCurrentUser")
  public Object getCurrentUser(Authentication authentication) {
    System.out.println("loading getCurrentUser");
    return authentication.getPrincipal();
  }

  /**
   * 获取当前用户
   *
   * @param authentication
   * @return user/getJwtToken
   */
  @RequestMapping("/getJwtToken")
  @ResponseBody
  public Object getJwtToken(HttpServletRequest httpServletRequest, Authentication authentication) {
    String authorization = httpServletRequest.getHeader("Authorization");
    String token = authorization.substring(authorization.indexOf("bearer") + 7);

    return Jwts.parser()
        .setSigningKey("sign_key".getBytes(StandardCharsets.UTF_8))
        .parseClaimsJws(token)
        .getBody();
  }
}
