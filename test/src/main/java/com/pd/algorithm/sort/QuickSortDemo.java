package com.pd.algorithm.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author YCWB0382
 * @date 2022-10-08 12:45
 */
public class QuickSortDemo {

  public static void quickSort(int[] a, int left, int right) {
    int middle = 0;
    int innerLeft = left;
    int innerRight = right;
    for (int i = innerLeft; i < a.length; i++) {
      if (a[i] > middle) {
        int temp = a[i];
        a[i] = a[innerRight];
        a[innerRight] = temp;
        innerRight = innerRight - 1;
      }
      for (int j = innerRight; j > 0; j--) {
        if (i >= j) {
          break;
        }

        middle = a[i];
        if (a[j] > middle) {
          int temp = a[i];
          a[i] = a[j];
          a[j] = temp;

          innerLeft = 1;
        }
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3);
    list.forEach(
        li -> {
          if (li.equals(2)) {
            return;
          }
          System.out.println(li);
        });
  }
}
