package com.pd.concurrency.example.count;

import com.pd.concurrency.annotations.NotThreadSafe;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: pd
 * @date: 2021-02-25 下午2:28
 */
@Slf4j
@NotThreadSafe
public class CountExample {

  // 请求总数
  public static int clientTotal = 5000;

  // 同时并发执行的线程数
  public static int threadTotal = 200;

  public static int count = 0;

  public static void main(String[] args) throws InterruptedException {
    // 创建线程池对象
    ExecutorService executorService = Executors.newCachedThreadPool();
    // 信号量
    final Semaphore semaphore = new Semaphore(threadTotal);
    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
    for (int i = 0; i < clientTotal; i++) {
      executorService.execute(
          () -> {
            try {
              semaphore.acquire();
              add();
              semaphore.release();
            } catch (InterruptedException e) {
              log.error("exception", e);
            }
            countDownLatch.countDown();
          });
    }
    countDownLatch.await();
    executorService.shutdown();
    log.info("count:{}", count);
  }

  private static void add() {
    count++;
  }
}
