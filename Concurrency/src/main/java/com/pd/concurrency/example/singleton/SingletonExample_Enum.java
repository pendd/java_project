package com.pd.concurrency.example.singleton;

import com.pd.concurrency.annotations.NotThreadSafe;

/**
 * 饿汉模式 单例实例在类装载时进行创建
 *
 * @author: pd
 * @date: 2021-02-25 下午3:26
 */
@NotThreadSafe
public class SingletonExample_Hungry {

  // 私有构造函数
  private SingletonExample_Hungry() {}

  // 单例对象
  private static SingletonExample_Hungry instance = new SingletonExample_Hungry();

  // 静态的工厂方法
  public static SingletonExample_Hungry getInstance() {
    return instance;
  }
}
