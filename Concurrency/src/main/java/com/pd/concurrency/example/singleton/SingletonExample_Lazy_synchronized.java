package com.pd.concurrency.example.singleton;

import com.pd.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式 单例实例在第一次使用时进行创建
 *
 * @author: pd
 * @date: 2021-02-25 下午3:23
 */
@NotThreadSafe
public class SingletonExample_Lazy {

  private SingletonExample_Lazy() {}

  private static SingletonExample_Lazy instance = null;

  public static SingletonExample_Lazy getInstance() {
    if (instance == null) {
      instance = new SingletonExample_Lazy();
    }
    return instance;
  }
}
