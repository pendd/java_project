package com.pd.concurrency.book;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: YCWB0382
 * @date: 2021-07-15 21:37
 */
public class LambdaArrays {
  public static void main(String[] args) {
    String[] arr1 = new String[] {"java", "fkava", "fkit", "ios", "android"};
    Arrays.parallelSort(arr1, Comparator.comparingInt(String::length));
    System.out.println(Arrays.toString(arr1));

    int[] arr2 = new int[] {3, -4, 25, 16, 30, 18};
    // left代表数组中前一个索引处的元素，计算第一个元素时left为1
    // right代表数组中当前索引处的元素
    Arrays.parallelPrefix(arr2, (left, right) -> left * right);
    System.out.println(Arrays.toString(arr2));
    long[] arr3 = new long[5];
    // operand代表正在计算的元素索引
    Arrays.parallelSetAll(arr3, operand -> operand * 5L);
    System.out.println(Arrays.toString(arr3));
  }
}
