package com.pd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author YCWB0382
 * @date 2022-01-09 17:03
 */
@SpringBootApplication
@EnableAsync
public class HbaseDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(HbaseDemoApplication.class, args);
  }
}
