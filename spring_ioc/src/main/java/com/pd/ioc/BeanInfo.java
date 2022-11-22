package com.pd.ioc;

/** Bean类信息 */
public class BeanInfo {

  /** Bean类的类型 */
  private Class clasz;

  /** 保存在ioc容器中的Bean名称 */
  private String beanName;

  /** 保存在ioc容器中的Bean类型 */
  private Class beanType;

  /** 保存在ioc容器中的bean对象实例 */
  private Object bean;

  /** 保存在ioc容器中的bean的代理对象实例 */
  private Object proxyBean;

  public Class getClasz() {
    return clasz;
  }

  public void setClasz(Class clasz) {
    this.clasz = clasz;
  }

  public String getBeanName() {
    return beanName;
  }

  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }

  public Class getBeanType() {
    return beanType;
  }

  public void setBeanType(Class beanType) {
    this.beanType = beanType;
  }

  public Object getBean() {
    return bean;
  }

  public void setBean(Object bean) {
    this.bean = bean;
  }

  public Object getProxyBean() {
    return proxyBean;
  }

  public void setProxyBean(Object proxyBean) {
    this.proxyBean = proxyBean;
  }
}
