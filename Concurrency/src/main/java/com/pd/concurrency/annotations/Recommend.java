package com.pd.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标记【推荐】的类或者写法
 *
 * @author: pd
 * @date: 2021-02-25 下午2:26
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Recommend {
  String value() default "";
}
