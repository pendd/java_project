package com.pd.concurrency.example.publish;

import com.pd.concurrency.annotations.NotRecommend;
import com.pd.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 逸出
 *
 * @author: pd
 * @date: 2021-02-25 下午3:42
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

  private int thisCanBeEscape = 0;

  public Escape() {
    new InnerClass();
  }

  private class InnerClass {

    public InnerClass() {
      // 这里的this引用会照成逸出，当Escape类还没有加载完成，该this引用就已经暴露了
      log.info("{}", Escape.this.thisCanBeEscape);
    }
  }

  public static void main(String[] args) {
    new Escape();
  }
}
