package com.pd.concurrency.example.commonUnsafe;

import com.pd.concurrency.annotations.NotThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.text.SimpleDateFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class DateFormatExample1 {

  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(DateFormatExample1::update);
  }

  private static void update() {
    try {
      simpleDateFormat.parse("20180208");
    } catch (Exception e) {
      log.error("parse exception", e);
    }
  }
}
