package com.pd;

import java.lang.reflect.Method;

/**
 * @author: pd
 * @date: 2021-02-16 上午1:56
 */
public class TestDemo {

  public static void main(String[] args) throws Exception {
    Class<?> clazz = Class.forName("com.pd.InitDemo");
    Method[] methods = clazz.getMethods();
    for (Method method : methods) {
      if (method.isAnnotationPresent(InitMethod.class)) {

        System.out.println("方法名:" + method.getName());
        method.invoke(clazz.getConstructor(null).newInstance(null));
      }
    }
  }
}
