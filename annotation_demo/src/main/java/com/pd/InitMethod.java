import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解实现初始化加载方法
 *
 * @author: pd
 * @date: 2021-02-16 上午1:51
 */
@Target(ElementType.METHOD) // 该注解作用于方法级别
@Retention(RetentionPolicy.RUNTIME)
public @interface InitMethod {}
