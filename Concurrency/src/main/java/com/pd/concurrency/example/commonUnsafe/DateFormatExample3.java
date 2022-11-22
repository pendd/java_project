package com.pd.concurrency.example.commonUnsafe;

import com.pd.concurrency.annotations.ThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Slf4j
@ThreadSafe
public class DateFormatExample3 {

  private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(DateFormatExample3::update);
  }

  private static void update(int i) {
    log.info("{}, {}", i, DateTime.parse("20180208", dateTimeFormatter).toDate());
  }
}
