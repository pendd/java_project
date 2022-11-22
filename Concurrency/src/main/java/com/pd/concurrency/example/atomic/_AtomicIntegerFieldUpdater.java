package com.pd.concurrency.example.atomic;

import com.pd.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class _AtomicIntegerFieldUpdater {

  private static AtomicIntegerFieldUpdater<_AtomicIntegerFieldUpdater> updater =
      AtomicIntegerFieldUpdater.newUpdater(_AtomicIntegerFieldUpdater.class, "count");

  @Getter public volatile int count = 100;

  public static void main(String[] args) {

    _AtomicIntegerFieldUpdater example5 = new _AtomicIntegerFieldUpdater();

    if (updater.compareAndSet(example5, 100, 120)) {
      log.info("update success 1, {}", example5.getCount());
    }

    if (updater.compareAndSet(example5, 100, 120)) {
      log.info("update success 2, {}", example5.getCount());
    } else {
      log.info("update failed, {}", example5.getCount());
    }
  }
}
