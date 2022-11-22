package com.pd.algorithms.grokking._03_recursion;

/**
 * 递归实现找出列表中最大的数
 *
 * @author: YCWB0382
 * @date: 2021-08-17 11:33
 */
public class FindMaxValueInArray {

  private FindMaxValueInArray() {}

  public static int find_max(int[] array, int size) {
    if (size == 1) {
      return array[0];
    }

    // 避免第一次传参超过数组大小，造成NPE问题
    if (size > array.length || size < 1) {
      size = array.length;
    }

    return getMax(array[size - 1], find_max(array, size - 1));
  }

  public static int getMax(int a, int b) {
    return Math.max(a, b);
  }

  public static void main(String[] args) {
    int[] array = {1, 3, 4, 5, 2, 7, 6, 9, 8};
    int max = find_max(array, array.length);
    System.out.println(max);
  }
}
