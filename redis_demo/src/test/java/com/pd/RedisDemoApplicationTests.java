package com.pd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

  @Autowired JdbcTemplate template;

  @Test
  void contextLoads() {}

  @Test
  void test01() {
    template.update("insert into msg(message) values('msg1')");
  }
}
