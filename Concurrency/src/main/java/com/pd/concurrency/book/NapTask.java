package com.pd.concurrency.book;

import lombok.SneakyThrows;

public class NapTask implements Runnable {
  final int id;

  public NapTask(int id) {
    this.id = id;
  }

  @SneakyThrows
  @Override
  public void run() {
    Thread.sleep(100);
    System.out.println(this + " " + Thread.currentThread().getName());
  }

  @Override
  public String toString() {
    return "NapTask[" + id + "]";
  }
}
