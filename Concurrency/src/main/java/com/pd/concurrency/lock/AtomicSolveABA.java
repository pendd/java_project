package com.pd.concurrency.lock;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: YCWB0382
 * @date: 2021-07-20 18:04
 */
public class AtomicSolveABA {
  public static void main(String[] args) {
    AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(200, 0);

    new Thread(
            () -> {
              try {
                Thread.sleep(50);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              stampedReference.compareAndSet(
                  200, 201, stampedReference.getStamp(), stampedReference.getStamp() + 1);

              stampedReference.compareAndSet(
                  201, 200, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            })
        .start();

    new Thread(
            () -> {
              int stamp = stampedReference.getStamp();
              try {
                Thread.sleep(500);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              boolean result = stampedReference.compareAndSet(200, 201, stamp, stamp + 1);
              System.out.printf("  >>> 修改 stampedReference :: %s %n", result);
            })
        .start();
  }
}
