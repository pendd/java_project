package com.pd.algorithms.grokking._01_introduction_to_algorithms;

/**
 * @author: YCWB0382
 * @date: 2021-08-16 14:28
 */
public class BinarySearch {

  private BinarySearch() {}

  public static int binarySearch(int[] list, int value) {

    int low = 0;
    int high = list.length - 1;

    while (low <= high) {
      int mid = (low + high) >> 1;

      if (list[mid] == value) {
        return mid;
      }

      if (list[mid] < value) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return -1;
  }

  /**
   * 递归方式实现二分法查找
   *
   * @param arr 需要从中查找元素的数组
   * @param min 查找区间数组中起点下标
   * @param max 查找区间数组中终点下标
   * @param num 需要查找的元素
   * @return 如果查找元素存在，返回元素对应数组中的下标，否则返回-1
   */
  private static int recursion(int[] arr, int min, int max, int num) {
    // 获得查询区间中间数值
    int mid = (max + min) >> 1;
    // 如果查询区间中间数值大于查询元素数值，
    if (arr[mid] > num) {
      // 那么要查询元素位于中间数值的左边，更新查找区间数组中起点下标
      max = mid - 1;
      // 如果查询区间中间数值小于查询元素数值
    } else if (arr[mid] < num) {
      // 那么要查询元素位于中间数值的右边，更新查找区间数组中终点下标
      min = mid + 1;
    } else {
      // 如果查询区间中间数值等于查询元素数值，那么返回当前数值下标（因为已经找到了）
      return mid;
    }
    // 如果查找区间数组中起点下标大于查找区间数组中终点下标，证明查找元素不在查找的区间内，否则递归调用继续查找
    return min > max ? -1 : recursion(arr, min, max, num);
  }

  public static void main(String[] args) {
    int result1 = binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 5);
    System.out.println(result1);

    int result2 = binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 5);
    System.out.println(result2);

    int result3 = binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 4);
    System.out.println(result3);

    int result4 = binarySearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 10);
    System.out.println(result4);

    int result11 = recursion(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, 8, 5);
    System.out.println(result11);

    int result12 = recursion(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 0, 7, 5);
    System.out.println(result12);

    int result13 = recursion(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 0, 7, 4);
    System.out.println(result13);

    int result14 = recursion(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 0, 7, 10);
    System.out.println(result14);
  }
}
