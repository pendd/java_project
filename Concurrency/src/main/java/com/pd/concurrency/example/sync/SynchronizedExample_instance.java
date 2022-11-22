package com.pd.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: pd
 * @date: 2021-02-25 下午3:11
 */
@Slf4j
public class SynchronizedExample_instance {

  // 修饰一个代码块
  public void test1(int j) {
    synchronized (this) {
      for (int i = 0; i < 10; i++) {
        log.info("test1 {} - {}", j, i);
      }
    }
  }

  // 修饰一个方法
  public synchronized void test2(int j) {
    for (int i = 0; i < 10; i++) {
      log.info("test2 {} - {}", j, i);
    }
  }

  // 上面两个修饰代码块和修饰非静态方法只会对调用对象有效
  public static void main(String[] args) {
    SynchronizedExample_instance instance1 = new SynchronizedExample_instance();
    SynchronizedExample_instance instance2 = new SynchronizedExample_instance();
    ExecutorService executorService = Executors.newCachedThreadPool();
    // 线程池中执行， 同一个对象instance1 分别执行test1() 和 test2() 方法 会按顺序执行，test1 走完 再执行test2
    //              当使用instance1.test1(1)   instance2.test2(2) 两个不同变量来执行的话，会发现结果是错序的
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
