package com.pd.concurrency.example.singleton;

import com.pd.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式 <br>
 * 单例实例在第一次使用时进行创建
 *
 * @author: pd
 * @date: 2021-02-25 下午3:23
 */
@NotThreadSafe
public class SingletonExample_Lazy_DoubleCheck {

  private SingletonExample_Lazy_DoubleCheck() {}

  // 1、memory = allocate() 分配对象的内存空间
  // 2、ctorInstance() 初始化对象
  // 3、instance = memory 设置instance指向刚分配的内存

  // JVM和cpu优化，发生了指令重排

  // 1、memory = allocate() 分配对象的内存空间
  // 3、instance = memory 设置instance指向刚分配的内存
  // 2、ctorInstance() 初始化对象

  private static SingletonExample_Lazy_DoubleCheck instance = null;

  public static SingletonExample_Lazy_DoubleCheck getInstance() {
    if (instance == null) { // 双重检测机制
      synchronized (SingletonExample_Lazy_DoubleCheck.class) { // 同步锁
        if (instance == null) {
          instance = new SingletonExample_Lazy_DoubleCheck();
        }
      }
    }
    return instance;
  }
}
