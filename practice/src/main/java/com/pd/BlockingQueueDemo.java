package com.pd;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列 当队列满的时候 阻塞入队 当队列空的时候 阻塞出队
 *
 * @author: pd
 * @date: 2021-04-05 上午10:27
 */
public class BlockingQueueDemo {

  public static void main(String[] args) throws InterruptedException {
    // 创建一个长度为 5 的阻塞队列
    ArrayBlockingQueue<Integer> q1 = new ArrayBlockingQueue<>(5);

    // 新创建一个线程执行入列
    new Thread(
            () -> {
              // 循环 10 次
              for (int i = 0; i < 10; i++) {
                try {
                  q1.put(i);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                System.out.println(
                    new Date() + " | ArrayBlockingQueue Size:" + q1.size() + ":::入队");
              }
              System.out.println(new Date() + " | For End.");
            })
        .start();

    // 新创建一个线程执行出列
    new Thread(
            () -> {
              for (int i = 0; i < 5; i++) {
                try {
                  // 休眠 1S
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                if (!q1.isEmpty()) {
                  try {
                    q1.take(); // 出列
                    System.out.println(
                        new Date() + " | ArrayBlockingQueue Size:" + q1.size() + ":::出队");
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
              }
            })
        .start();
  }
}
