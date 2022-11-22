package com.pd.concurrency.example.commonUnsafe;

import com.pd.concurrency.annotations.ThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class StringExample2 {

  public static StringBuffer stringBuffer = new StringBuffer();

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(StringExample2::update);
    log.info("size:{}", stringBuffer.length());
  }

  private static void update() {
    stringBuffer.append("1");
  }
}
