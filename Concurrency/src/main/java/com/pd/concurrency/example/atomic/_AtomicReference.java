package com.pd.concurrency.example.atomic;

import com.pd.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class _AtomicReference {

  private static AtomicReference<Integer> count = new AtomicReference<>(0);

  public static void main(String[] args) {
    count.compareAndSet(0, 2); // 2
    count.compareAndSet(0, 1); // no
    count.compareAndSet(1, 3); // no
    count.compareAndSet(2, 4); // 4
    count.compareAndSet(3, 5); // no
    log.info("count:{}", count.get());
  }
}
