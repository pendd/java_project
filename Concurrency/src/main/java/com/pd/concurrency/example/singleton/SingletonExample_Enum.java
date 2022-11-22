package com.pd.concurrency.example.singleton;

import com.pd.concurrency.annotations.Recommend;
import com.pd.concurrency.annotations.ThreadSafe;

/**
 * 枚举模式：最安全 <br>
 * 安全发布对象
 *
 * @author: pd
 * @date: 2021-02-25 下午3:26
 */
@ThreadSafe
@Recommend
public class SingletonExample_Enum {

  private SingletonExample_Enum() {}

  public static SingletonExample_Enum instance = Singleton.INSTANCE.getInstance();

  private enum Singleton {
    INSTANCE;

    private SingletonExample_Enum singleton;

    // JVM保证这个方法绝对只调用一次
    Singleton() {
      singleton = new SingletonExample_Enum();
    }

    public SingletonExample_Enum getInstance() {
      return singleton;
    }
  }
}
