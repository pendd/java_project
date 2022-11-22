package com.pd.concurrency.example.commonUnsafe;

import com.pd.concurrency.annotations.NotThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class StringExample1 {

  public static StringBuilder stringBuilder = new StringBuilder();

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(StringExample1::update);
    log.info("size:{}", stringBuilder.length());
  }

  private static void update() {
    stringBuilder.append("1");
  }
}
