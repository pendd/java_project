package com.pd.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: pd
 * @date: 2021-03-07 下午12:48
 */
@SpringBootApplication
public class ConcurrencyApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(ConcurrencyApplication.class, args);
  }

  @Bean
  public FilterRegistrationBean<HttpFilter> httpFilter() {
    FilterRegistrationBean<HttpFilter> registrationBean =
        new FilterRegistrationBean<>(new HttpFilter());
    registrationBean.addUrlPatterns("/threadLocal/*");
    return registrationBean;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
  }
}
