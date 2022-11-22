package com.pd;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.stream.IntStream;

/**
 * @author YCWB0382
 * @date 2022-03-07 00:24
 */
public class Test {
  public static void main(String[] args) {
    WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);
    final int weekBasesYear = LocalDateTime.now().get(weekFields.weekBasedYear());
    int weekNumber = LocalDateTime.now().get(weekFields.weekOfWeekBasedYear());

    System.out.println(weekBasesYear);
    System.out.println(weekNumber);

    final LocalDate first = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    final LocalDate last = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    final LocalDateTime monday =
        LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);
    final LocalDateTime sunday =
        LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1);
    System.out.println(monday);
    System.out.println(sunday);
    System.out.println(first);
    System.out.println(last);

    final LocalDate with = LocalDate.now().withYear(2022).with(weekFields.weekOfYear(), 1);
    System.out.println(with);

    System.out.println("-------------------");

    revRange(0, 10).forEach(System.out::println);

    System.out.println("---------------");
  }

  private static IntStream revRange(int from, int to) {
    return IntStream.range(from, to).map(index -> to - index + from - 1);
  }

  public static String longTimeToMonthYear(Long timeStr) {
    DateTimeFormatter fmt =
        new DateTimeFormatterBuilder()
            .appendPattern("yyyy年M月")
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter();
    return millToDate(timeStr).format(fmt);
  }

  public static LocalDateTime millToDate(Long mill) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(mill), ZoneOffset.ofHours(8));
  }
}
