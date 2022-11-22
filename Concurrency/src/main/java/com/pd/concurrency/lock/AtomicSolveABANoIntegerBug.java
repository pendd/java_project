package com.pd.concurrency.lock;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: YCWB0382
 * @date: 2021-07-20 18:04
 */
public class AtomicSolveABANoIntegerBug {
  public static void main(String[] args) {
    AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(200, 0);

    new Thread(
            () -> {
              try {
                Thread.sleep(50);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              Integer reference = stampedReference.getReference();
              boolean flag1 =
                  stampedReference.compareAndSet(
                      reference,
                      reference + 1,
                      stampedReference.getStamp(),
                      stampedReference.getStamp() + 1);
              System.out.println("flag1:" + flag1);
              boolean flag2 =
                  stampedReference.compareAndSet(
                      reference + 1,
                      reference,
                      stampedReference.getStamp(),
                      stampedReference.getStamp() + 1);
              System.out.println("flag2:" + flag2);
            })
        .start();

    new Thread(
            () -> {
              int stamp = stampedReference.getStamp();
              Integer reference = stampedReference.getReference();
              try {
                Thread.sleep(500);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println(reference);
              boolean result =
                  stampedReference.compareAndSet(reference, reference + 1, stamp, stamp + 1);
              System.out.printf("  >>> 修改 stampedReference :: %s %n", result);
            })
        .start();
  }
}
