package com.pd.producer.utils;

import java.time.LocalDateTime;

/** @author dpeng */
public class DateUtils {
  public static LocalDateTime addMinutes(LocalDateTime orderTime, int orderTimeOut) {
    return orderTime.plusMinutes(orderTimeOut);
  }

  private DateUtils() {}
}
