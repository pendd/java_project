package com.pd.concurrency.example;

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
public class CountExample_volatile {

  // 请求总数
  public static int clientTotal = 5000;

  // 同时并发执行的线程数
  public static int threadTotal = 200;

  // volatile不具有原子性
  public static volatile int count = 0;

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
    // 1. 获取count值
    // 2. count + 1
    // 3. 设置count值

    // 当两个线程通过在第一步拿到最新的count值，在第二步进行加1操作
    // 然后进行第三步写会主存中，这时，可能会丢失一次加1操作
  }
}
