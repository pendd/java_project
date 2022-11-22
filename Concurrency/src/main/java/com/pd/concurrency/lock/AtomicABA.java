package com.pd.concurrency.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: YCWB0382
 * @date: 2021-07-20 18:00
 */
public class AtomicABA {
  public static void main(String[] args) throws InterruptedException {
    AtomicInteger atomicInteger = new AtomicInteger(100);
    new Thread(
            () -> {
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              atomicInteger.compareAndSet(100, 101);
              atomicInteger.compareAndSet(101, 100);
            })
        .start();

    //    Thread.sleep(2000);

    new Thread(
            () -> {
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              boolean result = atomicInteger.compareAndSet(100, 101);
              System.out.printf("  >>> 修改 atomicInteger :: %s %n", result);
            })
        .start();
  }
}
