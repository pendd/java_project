package com.pd.concurrency.example.atomic;

import com.pd.concurrency.annotations.ThreadSafe;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: pd
 * @date: 2021-03-05 下午5:12
 */
@Slf4j
@ThreadSafe
public class AtomicInteger {

  // 请求总数
  public static int clientTotal = 5000;

  // 同时并发执行的线程数
  public static int threadTotal = 200;

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
  }
}
