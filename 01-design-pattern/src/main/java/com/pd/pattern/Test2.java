package com.pd.pattern;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

/**
 * @author YCWB0382
 * @date 2022-06-14 17:36
 */
public class Test1 {
  public static void main(String[] args) {

    final Demo demo = new Demo();
    demo.setOpenId("111");
    System.out.println(demo);

    BigDecimal big1 = new BigDecimal(20);
    BigDecimal big2 = new BigDecimal(10);

    System.out.println(
        (big2.subtract(big1).divide(big1, 2, RoundingMode.HALF_UP).stripTrailingZeros()));

    LocalDateTime now = LocalDateTime.now();
    final LocalDateTime firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
    final LocalDateTime lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
    System.out.println(firstDayOfMonth);
    System.out.println(lastDayOfMonth);

    final LocalDateTime firstMinDayOfMonth =
        LocalDateTime.of(firstDayOfMonth.toLocalDate(), LocalTime.MIN);
    final LocalDateTime lastMaxDayOfMonth =
        LocalDateTime.of(lastDayOfMonth.toLocalDate(), LocalTime.MAX);
    System.out.println(firstMinDayOfMonth);
    System.out.println(lastMaxDayOfMonth);

    final LocalDateTime monday = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    final LocalDateTime sunday = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    System.out.println(monday);
    System.out.println(sunday);

    final LocalDateTime minMonday = LocalDateTime.of(monday.toLocalDate(), LocalTime.MIN);
    final LocalDateTime maxSunday = LocalDateTime.of(sunday.toLocalDate(), LocalTime.MAX);
    System.out.println(minMonday);
    System.out.println(maxSunday);
  }
}
