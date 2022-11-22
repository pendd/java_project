package com.pd.concurrency.example.publish;

import com.pd.concurrency.annotations.NotThreadSafe;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * 不安全发布
 *
 * @author: pd
 * @date: 2021-02-25 下午3:41
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {
  private static String[] states = {"a", "b", "c"};

  public String[] getStates() {
    return states;
  }

  public static void main(String[] args) {
    UnsafePublish unsafePublish = new UnsafePublish();
    UnsafePublish unsafePublish2 = new UnsafePublish();
    log.info("{}", Arrays.toString(unsafePublish.getStates()));

    // 由于getStates() 方法是public的 所以当外界来调用该方法串改states的值
    // 所有对象的states值都会被串改  因为是static的

    unsafePublish.getStates()[0] = "d";
    log.info("{}", Arrays.toString(unsafePublish.getStates()));
    log.info("{}", Arrays.toString(unsafePublish2.getStates()));
  }
}
