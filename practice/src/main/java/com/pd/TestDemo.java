package com.pd;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: pd
 * @date: 2021-04-05 上午10:34
 */
public class TestDemo {
  public static void main(String[] args) {
    // 创建一个长度为 5 的阻塞队列
    ArrayBlockingQueue<Integer> q1 = new ArrayBlockingQueue<>(5);

    // 新创建一个线程执行出列
    new Thread(
            () -> {
              for (int i = 0; i < 10; i++) {

                if (!q1.isEmpty()) {
                  try {
                    q1.take(); // 出列
                    System.out.println(
                        new Date() + " | ArrayBlockingQueue Size:" + q1.size() + ":::出队");
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }

                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            })
        .start();

    // 新创建一个线程执行入列
    new Thread(
            () -> {
              // 循环 10 次
              for (int i = 0; i < 5; i++) {
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
  }
}
