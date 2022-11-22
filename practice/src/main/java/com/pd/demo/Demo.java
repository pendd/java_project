package com.pd.kafka;

import org.junit.Test;

import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author: YCWB0382
 * @date: 2021-06-16 09:38
 */
public class Demo {

  @Test
  public void test1() {
    String str = "we are happy!";
    StringBuilder sb = new StringBuilder();
    for (char c : str.toCharArray()) {
      if (c == ' ') sb.append("%20");
      else sb.append(c);
    }
    System.out.println(sb);
  }

  @Test
  public void test2() {
    LinkedList<Integer> list = new LinkedList<>();
    Integer[] integers = list.toArray(new Integer[0]);
    list.addFirst(1);
    int[] ints = list.stream().mapToInt(Integer::valueOf).toArray();
  }

  /**
   * 五十亿个int类型的正整数，要从中找出重复的数并返回
   *
   * @param arr 50亿数的数组 （实际案例下 文件流的方式获取参数）
   * @return 重复的数
   */
  public static Set<Integer> test(int[] arr) {
    int j = 0;
    // 用来把重复的数返回，存在Set里，这样避免返回重复的数。
    Set<Integer> output = new HashSet<>();
    BitSet bitSet = new BitSet(Integer.MAX_VALUE);
    int i = 0;
    while (i < arr.length) {
      int value = arr[i];
      // 判断该数是否存在bitSet里
      if (bitSet.get(value)) {
        output.add(value);
      } else {
        bitSet.set(value, true);
      }
      i++;
    }
    return output;
  }
  // 测试
  public static void main(String[] args) {
    int[] t = {1, 2, 3, 4, 5, 6, 7, 8, 3, 4};
    Set<Integer> t2 = test(t);
    System.out.println(t2);
  }
}
