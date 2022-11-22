package com.pd.time.localDate;

import org.junit.Test;
import sun.security.action.GetPropertyAction;

import java.security.AccessController;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author: YCWB0382
 * @date: 2021-08-26 14:21
 */
public class LocalDateDemo {

  @Test
  public void test01() {
    System.out.println(LocalDate.MAX);
    System.out.println(LocalDate.MIN);
  }

  @Test
  public void test02() {
    String zoneID = AccessController.doPrivileged(new GetPropertyAction("user.timezone"));
    System.out.println("zoneId:" + zoneID);

    String javaHome = AccessController.doPrivileged(new GetPropertyAction("java.home"));
    System.out.println("javaHome:" + javaHome);
  }

  @Test
  public void test03() {
    LocalDate date = LocalDate.of(2021, 9, 1);
    int year = date.getYear();
    Month month = date.getMonth();
    int dayOfMonth = date.getDayOfMonth();
    int monthValue = date.getMonthValue();
    DayOfWeek dayOfWeek = date.getDayOfWeek();
    int lengthOfMonth = date.lengthOfMonth();
    boolean leapYear = date.isLeapYear();
    System.out.println("year:" + year);
    System.out.println("month:" + month);
    System.out.println("dayOfMonth:" + dayOfMonth);
    System.out.println("monthValue:" + monthValue);
    System.out.println("dayOfWeek:" + dayOfWeek);
    System.out.println("lengthOfMonth:" + lengthOfMonth);
    System.out.println("leapYear:" + leapYear);
  }
}
