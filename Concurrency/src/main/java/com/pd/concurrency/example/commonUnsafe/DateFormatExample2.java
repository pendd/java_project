package com.pd.concurrency.example.commonUnsafe;

import com.pd.concurrency.annotations.ThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.text.SimpleDateFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class DateFormatExample2 {

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(DateFormatExample2::update);
  }

  private static void update() {
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
      simpleDateFormat.parse("20180208");
    } catch (Exception e) {
      log.error("parse exception", e);
    }
  }
}
