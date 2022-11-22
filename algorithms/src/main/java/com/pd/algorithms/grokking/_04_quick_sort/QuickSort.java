package com.pd.algorithms.grokking._04_quick_sort;

import java.util.stream.IntStream;

/**
 * @author: YCWB0382
 * @date: 2021-08-17 14:07
 */
public class QuickSort {

  private QuickSort() {}

  public static int[] quickSort(int[] array) {
    if (array.length < 2) {
      return array;
    }

    // 基准值
    int pivot = array[0];

    int[] greater =
        IntStream.range(1, array.length)
            .filter(index -> array[index] > pivot)
            .map(index -> array[index])
            .toArray();
    int[] less =
        IntStream.range(1, array.length)
            .filter(index -> array[index] <= pivot)
            .map(index -> array[index])
            .toArray();

    return concatArray(
        concatArray(quickSort(less), quickSort(new int[] {pivot})), quickSort(greater));
  }

  public static int[] concatArray(int[] array1, int[] array2) {
    return IntStream.concat(IntStream.of(array1), IntStream.of(array2)).toArray();
  }

  public static void main(String[] args) {
    int[] array = {2, 3, 1, 9, 5, 7, 6, 8, 4, 3, 7, 9, 6, 8};
    int[] result = quickSort(array);
    IntStream.of(result).forEach(System.out::print);
  }
}
