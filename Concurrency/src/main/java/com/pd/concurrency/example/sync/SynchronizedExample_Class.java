package com.pd.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: pd
 * @date: 2021-02-25 下午3:18
 */
@Slf4j
public class SynchronizedExample_Class {

  // 修饰一个类
  public static void test1(int j) {
    synchronized (SynchronizedExample_Class.class) {
      for (int i = 0; i < 10; i++) {
        log.info("test1 {} - {}", j, i);
      }
    }
  }

  // 修饰一个静态方法
  public static synchronized void test2(int j) {
    for (int i = 0; i < 10; i++) {
      log.info("test2 {} - {}", j, i);
    }
  }

  // 上面两个synchronized修饰对所有对象有效
  public static void main(String[] args) {
    SynchronizedExample_Class instance1 = new SynchronizedExample_Class();
    SynchronizedExample_Class instance2 = new SynchronizedExample_Class();
    ExecutorService executorService = Executors.newCachedThreadPool();
    // 线程池中执行， 两个不同对象instance1和instance2 分别执行test1() 和 test2() 方法
    // 会按顺序执行，test1 走完 再执行test2   因为对所有对象都是同步操作
    executorService.execute(
        () -> {
          instance1.test1(1);
        });

    executorService.execute(
        () -> {
          instance2.test2(2);
        });
  }
}
