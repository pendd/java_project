package com.pd;

/**
 * @author YCWB0382
 * @date 2022-05-08 15:40
 */
public class Test2 {
  public static void main(String[] args) {
    String str = " 张三    1             2     4    ";
    final String s = str.replaceAll("\\s", "");
    System.out.println(s);

    System.out.println("--------------");
    System.out.println("123456.jpeg".indexOf(",", 1));
  }
}
