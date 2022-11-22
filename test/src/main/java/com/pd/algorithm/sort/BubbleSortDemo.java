package com.pd.algorithm.sort;

/**
 * @author YCWB0382
 * @date 2022-10-08 11:34
 */
public class BubbleSortDemo {

  public static void bubbleSort1(int[] a) {
    final int length = a.length;
    for (int i = 0; i < length; i++) {
      for (int j = 1; j < length; j++) {
        if (a[j - 1] > a[j]) {
          int temp = a[j - 1];
          a[j - 1] = a[j];
          a[j] = temp;
        }
      }
    }
  }

  public static void bubbleSort2(int[] a) {
    final int length = a.length;
    for (int i = length - 1; i > 0; i--) {
      for (int j = 0; j < i; j++) {
        if (a[j] > a[j + 1]) {
          int temp = a[j];
          a[j] = a[j + 1];
          a[j + 1] = temp;
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] a = {20, 40, 30, 10, 60, 50};
    bubbleSort2(a);
    for (int i : a) {
      System.out.print(i);
      System.out.print(" ");
    }
  }
}
