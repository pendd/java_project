package com.pd.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注入注解 类似于@Autowried
 *
 * @author pd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface AutoInject {

  /**
   * 注入的bean名称，为空时根据类型注入
   *
   * @return Bean名称
   */
  String value() default "";
}
