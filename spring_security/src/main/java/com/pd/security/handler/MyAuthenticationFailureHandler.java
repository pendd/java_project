package com.pd.security.demo.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @author: pd
 * @date: 2021-02-13 下午5:08
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

  private String url;

  public MyAuthenticationFailureHandler(String url) {
    this.url = url;
  }

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    response.sendRedirect(url);
  }
}
