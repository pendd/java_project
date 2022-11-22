package com.pd.concurrency.example.threadLocal;

/**
 * @author: pd
 * @date: 2021-03-07 下午12:50
 */
public class RequestHolder {
  private static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

  public static void add(Long id) {
    requestHolder.set(id);
  }

  public static Long getId() {
    return requestHolder.get();
  }

  public static void remove() {
    requestHolder.remove();
  }
}
