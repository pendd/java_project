package com.pd.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-21 10:11 下午
 */
@EnableJpaRepositories(basePackages = "com.pd.jpa.repository") //repositoryBaseClass = BaseRepository.class
// 指定自定义baseRepository类
@SpringBootApplication
public class SpringDataJpaApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringDataJpaApplication.class, args);
  }
}
