package com.pd.concurrency.example.singleton;

import com.pd.concurrency.annotations.NotRecommend;
import com.pd.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 单例实例在第一次使用时进行创建
 *
 * @author: pd
 * @date: 2021-02-25 下午3:23
 */
@ThreadSafe
@NotRecommend
public class SingletonExample_Lazy_synchronized {

  private SingletonExample_Lazy_synchronized() {}

  private static SingletonExample_Lazy_synchronized instance = null;

  public static synchronized SingletonExample_Lazy_synchronized getInstance() {
    if (instance == null) {
      instance = new SingletonExample_Lazy_synchronized();
    }
    return instance;
  }
}
