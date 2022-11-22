package com.pd.concurrency.example.singleton;

import com.pd.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式 单例实例在类装载时进行创建
 *
 * @author: pd
 * @date: 2021-02-25 下午3:26
 */
@ThreadSafe
public class SingletonExample_Hungry_staticArea {

  // 私有构造函数
  private SingletonExample_Hungry_staticArea() {}

  // 单例对象  第一步
  private static SingletonExample_Hungry_staticArea instance = null;

  // 第二步   这两步顺序不能颠倒  否则实例为null
  static {
    instance = new SingletonExample_Hungry_staticArea();
  }

  // 静态的工厂方法
  public static SingletonExample_Hungry_staticArea getInstance() {
    return instance;
  }

  public static void main(String[] args) {
    System.out.println(getInstance().hashCode());
    System.out.println(getInstance().hashCode());
  }
}
