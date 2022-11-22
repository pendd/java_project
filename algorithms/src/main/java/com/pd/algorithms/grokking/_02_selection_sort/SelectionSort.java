package com.pd.algorithms.grokking._02_selection_sort;

/**
 * @author: YCWB0382
 * @date: 2021-08-16 17:49
 */
public class SelectionSort {
  private SelectionSort() {}

  public static void selectSort(int[] list) {
    int size = list.length;
    for (int i = 0; i < size; i++) {
      int minIndex = i;
      for (int j = i + 1; j < size; j++) {
        if (list[minIndex] > list[j]) {
          // 存在更小的元素 交换
          swap(list, i, j);
        }
      }
    }
  }

  /**
   * 优化选择排序， 外层遍历一次， 内层遍历所有获取最小元素 index， 如果和外层当前下标一致，代表外层当前元素为此次内层遍历最小的，不用交换 不必像未优化前
   * 内层循环只要有比minIndex小的 就交换 ，频繁交换 浪费
   *
   * @param list 待排序数组
   */
  public static void selectSortPlus(int[] list) {
    int size = list.length;
    for (int i = 0; i < size; i++) {
      int minIndex = i;
      for (int j = i + 1; j < size; j++) {
        if (list[minIndex] > list[j]) {
          // 存在更小的元素 更新minIndex的值
          minIndex = j;
        }
      }
      // 值被更新了 需要交换
      if (minIndex != i) {
        swap(list, i, minIndex);
      }
    }
  }

  public static void swap(int[] list, int i, int j) {
    int temp = list[i];
    list[i] = list[j];
    list[j] = temp;
  }

  public static void main(String[] args) {
    int[] arr = new int[] {1, 4, 5, 2, 6, 9, 3, 8, 7};
    selectSort(arr);
    for (int item : arr) {
      System.out.print(item + "  ");
    }

    System.out.println();
    int[] arr2 = new int[] {1, 4, 5, 2, 6, 9, 3, 8, 7};
    selectSortPlus(arr2);
    for (int item : arr2) {
      System.out.print(item + "  ");
    }
  }
}
