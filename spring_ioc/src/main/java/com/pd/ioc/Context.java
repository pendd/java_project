package com.pd.ioc;

import java.util.HashMap;
import java.util.Map;

/** 上下文对象 用于保存应用运行中的信息 */
public class Context {

  /** 根据Bean名称存储Bean的Map对象 */
  private Map<String, Object> nameBeanMap = new HashMap<>();

  /** 根据Bean类型存储Bean的Map对象 */
  private Map<Class, Object> typeBeanMap = new HashMap<>();

  public Object getBean(String beanName) {
    return nameBeanMap.get(beanName);
  }

  public Object getBean(Class clasz) {
    return typeBeanMap.get(clasz);
  }

  public void putBean(String beanName, Object bean) {
    nameBeanMap.put(beanName, bean);
  }

  public void putBean(Class beanType, Object bean) {
    typeBeanMap.put(beanType, bean);
  }
}
