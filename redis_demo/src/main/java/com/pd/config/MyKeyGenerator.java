package com.pd.config;

import java.lang.reflect.Method;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component("myKeyGenerator")
public class MyKeyGenerator implements KeyGenerator {
  // key的生成方式，为了准确获取，直接设置为""
  // 这里返回的是key的拼接后缀
  @Override
  public Object generate(Object target, Method method, Object... params) {
    return "";
  }
}
