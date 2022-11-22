package com.pd.ioc;

import com.pd.ioc.annotation.AutoInject;
import com.pd.ioc.annotation.MyBean;
import com.pd.ioc.utils.ClassUtils;
import com.pd.ioc.utils.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** Bean工厂 */
public class BeanFactory {

  /** 基础包路径 */
  private String basePackage;

  /** 上下文对象 */
  private Context context = new Context();

  /**
   * 工厂构造器
   *
   * @param basePackage 基础包路径
   */
  public BeanFactory(String basePackage) {
    this.basePackage = basePackage;
    init();
  }

  /** 工厂初始化 */
  private void init() {
    // 扫描包和加载bean到ioc容器
    List<BeanInfo> myBeanList = scanPackageAndLoadBeans();

    // 给bean注入依赖对象
    injectBeans(myBeanList);
  }

  /**
   * 扫描包和加载bean到ioc容器
   *
   * @return 加载进ioc容器中的相关Bean信息
   */
  private List<BeanInfo> scanPackageAndLoadBeans() {
    List<BeanInfo> myBeanList = new ArrayList<>();

    // 找到包下所有类
    Set<String> classNames = ClassUtils.getClassName(basePackage, true);
    for (String className : classNames) {
      try {
        // 查找类
        Class clasz = Class.forName(className);

        // 判断类上是否存在MyBean注解
        if (clasz.isAnnotationPresent(MyBean.class)) {
          // 获取类上的MyBean注解
          MyBean myBeanAnnotation = (MyBean) clasz.getAnnotation(MyBean.class);
          // 获取注解值，即Bean名称
          String beanName = myBeanAnnotation.value();
          // 获取类继承的相关接口
          Class[] interfaces = clasz.getInterfaces();
          // 判断类是否可以采用jdk动态代理（有接口方可进jdk动态代理，创建代理对象）
          boolean canJdkProxyBean = interfaces != null && interfaces.length > 0;

          // 获取待注入ioc容器的Bean的类型
          Class beanType = getBeanType(clasz, canJdkProxyBean);

          // 实例化当前类，生成bean实例
          Object bean = clasz.newInstance();
          Object iocBean = bean;
          if (canJdkProxyBean) {
            // 可以使用jdk动态代理，则创建代理对象，代理此Bean
            Object proxyBean = this.createBeanProxy(bean);
            iocBean = proxyBean;
          }
          // 保存生成的bean到ioc容器
          if (StringUtils.isNotBlank(beanName)) {
            context.putBean(beanName, iocBean);
          }
          context.putBean(beanType, iocBean);

          // 暂存Bean信息
          BeanInfo beanInfo = new BeanInfo();
          beanInfo.setClasz(clasz);
          beanInfo.setBeanName(beanName);
          beanInfo.setBeanType(beanType);
          beanInfo.setBean(bean);
          beanInfo.setProxyBean(canJdkProxyBean ? iocBean : null);
          myBeanList.add(beanInfo);
        }
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        System.out.println("加载bean异常");
        e.printStackTrace();
      }
    }
    return myBeanList;
  }

  /**
   * 给相关Bean注入依赖的Bean
   *
   * @param myBeanList 注入到ioc容器中的所有的Bean
   */
  private void injectBeans(List<BeanInfo> myBeanList) {
    for (BeanInfo myBeanInfo : myBeanList) {
      Class beanClass = myBeanInfo.getClasz();
      Object bean = myBeanInfo.getBean();

      // 查找Bean的声明的所有字段
      Field[] fields = beanClass.getDeclaredFields();
      for (Field field : fields) {
        // 判断字段上是否有AutoInject注解
        if (field.isAnnotationPresent(AutoInject.class)) {
          // 查找待注入的bean
          AutoInject autoInjectAnnotation = field.getAnnotation(AutoInject.class);
          // 获取注解的值，即待注入的Bean名称
          String injectBeanName = autoInjectAnnotation.value();
          // 获取字段的类型，即待注入的Bean类型
          Class injectBeanType = field.getType();
          Object proxyBean = null;

          // 从查找ioc容器中查找待注入的Bean对象
          if (StringUtils.isNotBlank(injectBeanName)) {
            // Bean名称不为空，则根据名称查找Bean
            proxyBean = context.getBean(injectBeanName);
          } else {
            // Bean名称为空，则根据Bean类型查找Bean
            proxyBean = context.getBean(injectBeanType);
          }

          // 设置当前字段可访问
          field.setAccessible(true);
          try {
            // 将找到的Bean注入到当前字段上
            field.set(bean, proxyBean);
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  /**
   * 获取待注入到ioc容器中的Bean类型
   *
   * @param clasz Bean类型
   * @param canJdkProxyBean 是否可以使用jdk动态代理
   * @return 注入到ioc容器中的Bean类型
   */
  private Class getBeanType(Class clasz, boolean canJdkProxyBean) {
    Class beanType = null;
    if (canJdkProxyBean) {
      // 可以使用jdk动态代理，则bean类型取bean的接口类型
      beanType = clasz.getInterfaces()[0];
    } else {
      // 不可以使用jdk动态代理，bean类型就取当前类类型
      beanType = clasz;
    }
    return beanType;
  }

  /**
   * 根据Bean名称获取Bean对象
   *
   * @param beanName Bean名称
   * @param <T> Bean类型
   * @return ioc容器中的Bean, 找不到返回null
   */
  public <T> T getBean(String beanName) {
    return (T) context.getBean(beanName);
  }

  /**
   * 根据Bean类型获取Bean对象
   *
   * @param clasz 注入到ioc容器中的Bean类型
   * @param <T> Bean类型
   * @return ioc容器中的Bean, 找不到返回null
   */
  public <T> T getBean(Class clasz) {
    return (T) context.getBean(clasz);
  }

  /**
   * 创建代理bean
   *
   * @param bean 当前Bean对象
   * @return Bean的代理对象
   */
  private Object createBeanProxy(Object bean) {
    InvocationHandler invocationHandler = new BeanProxy(bean);
    Object proxyBean =
        Proxy.newProxyInstance(
            bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), invocationHandler);
    return proxyBean;
  }
}
