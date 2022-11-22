package com.pd.producer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** @author dpeng */
@SpringBootApplication
// 作用：指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
@MapperScan("com.pd.producer.mapper.**")
public class ProducerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class, args);
  }
}
