package com.pd.concurrency.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: YCWB0382
 * @date: 2021-07-20 13:47
 */
public class SynchronizedDemo {
  private static int count = 0;

  private static synchronized void addCount() {
    count++;
    System.out.println(Thread.currentThread().getName() + "----" + count);
  }

  public static void main(String[] args) {

    int loopCount = 100;

    ExecutorService executorService =
        new ThreadPoolExecutor(
            10,
            1000,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10),
            new ThreadPoolExecutor.AbortPolicy());

    for (int i = 0; i < loopCount; i++) {
      Runnable r = SynchronizedDemo::addCount;
      executorService.execute(r);
    }
    executorService.shutdown();
    System.out.println(SynchronizedDemo.count);
  }
}
