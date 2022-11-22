package com.pd.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** Bean的代理对象 使用jdk动态代理原理实现对java对象的代理，必须依赖接口 */
public class BeanProxy implements InvocationHandler {

  /** 被代理的bean对象 */
  private Object bean;

  public BeanProxy(Object bean) {
    this.bean = bean;
  }

  /**
   * 调用目标bean的相关方法
   *
   * @param proxy 代理对象
   * @param method 方法
   * @param args 参数
   * @return 方法返回值
   * @throws Throwable
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("before call method: " + method.getName());
    Object result = method.invoke(bean, args);
    System.out.println("after call method: " + method.getName());
    return result;
  }
}
