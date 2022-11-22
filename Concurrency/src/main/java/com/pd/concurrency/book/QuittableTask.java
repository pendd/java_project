package com.pd.concurrency.book;

import java.util.concurrent.atomic.AtomicBoolean;

public class QuittableTask implements Runnable {
  final int id;
  private final AtomicBoolean running = new AtomicBoolean(true);

  public QuittableTask(int id) {
    this.id = id;
  }

  public void quit() {
    running.set(false);
  }

  @Override
  public void run() {
    while (running.get()) // [1]
    new Nap(0.1);
    System.out.print(id + " "); // [2]
  }
}
