package com.pd.concurrency.example.singleton;

import com.pd.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式 <br>
 * 单例实例在第一次使用时进行创建
 *
 * @author: pd
 * @date: 2021-02-25 下午3:23
 */
@ThreadSafe
public class SingletonExample_Lazy_DoubleCheck_volatile {

  private SingletonExample_Lazy_DoubleCheck_volatile() {}

  // 1、memory = allocate() 分配对象的内存空间
  // 2、ctorInstance() 初始化对象
  // 3、instance = memory 设置instance指向刚分配的内存

  // JVM和cpu优化，发生了指令重排

  // 1、memory = allocate() 分配对象的内存空间
  // 3、instance = memory 设置instance指向刚分配的内存
  // 2、ctorInstance() 初始化对象

  // 单例对象 volatile + 双重检测机制 -> 禁止指令重排
  private static volatile SingletonExample_Lazy_DoubleCheck_volatile instance = null;

  public static SingletonExample_Lazy_DoubleCheck_volatile getInstance() {
    if (instance == null) { // 双重检测机制
      synchronized (SingletonExample_Lazy_DoubleCheck_volatile.class) { // 同步锁
        if (instance == null) {
          instance = new SingletonExample_Lazy_DoubleCheck_volatile();
        }
      }
    }
    return instance;
  }
}
