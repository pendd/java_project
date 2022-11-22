package com.pd.algorithms.grokking;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @author: YCWB0382
 * @date: 2021-08-23 15:52
 */
public class Demo {
  public static void main(String[] args) {
    DateTimeFormatter fmt =
        new DateTimeFormatterBuilder()
            .appendPattern("yyyyMM")
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter();

    LocalDate start;
    LocalDate end;
    try {
      start = LocalDate.parse("202010", fmt);
      end = LocalDate.parse("202104", fmt);
    } catch (DateTimeParseException e) {
      System.err.printf("时间转换失败，失败原因{%s}", e.getMessage());
      return;
    }
    System.out.println(start.getMonthValue());
    System.out.println(start.getYear());
    System.out.println(start);

    long between = ChronoUnit.MONTHS.between(start, end);
    System.out.println("between:" + between);

    long deleteBeforeTime = start.atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    System.err.println(deleteBeforeTime);
  }
}
