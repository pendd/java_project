package com.pd.concurrency;

public class MethodSync {
  /* * 测试 synchronized 修饰方法时锁定的是调用该方法的对象 */

  public void method(String name) {
    System.out.println(name + " Start a sync method");
    int count = 0;
    try {
      Thread.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    count++;
    System.out.println(name + " End the sync method," + count);
  }

  public static void main(String[] args) throws InterruptedException {
    MethodSync method = new MethodSync();
    new Thread(() -> method.method(Thread.currentThread().getName())).start();
    new Thread(() -> method.method(Thread.currentThread().getName())).start();
    new Thread(() -> method.method(Thread.currentThread().getName())).start();
    new Thread(() -> method.method(Thread.currentThread().getName())).start();
    Thread.sleep(500);
  }
}
