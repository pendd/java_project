package com.pd.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: pd
 * @date: 2021-02-17 下午3:23
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final String url;

  public MyAuthenticationSuccessHandler(String url) {
    this.url = url;
  }

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    User user = (User) authentication.getPrincipal();
    System.out.println(user.getAuthorities());
    System.out.println(user.getUsername());
    // 为了安全  所以这里只会打印null
    System.out.println(user.getPassword());
    response.sendRedirect(url);
  }
}
