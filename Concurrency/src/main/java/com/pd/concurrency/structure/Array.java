package com.pd.concurrency.structure;

/**
 * @author: pd
 * @date: 2021-03-10 下午8:13
 */
public class Array {

  private int size;
  private final int capacity = 5;
  private int[] array;

  Array() {
    array = new int[capacity];
  }

  public void add(int element) {
    ensureCapacityInternal();
    array[size++] = element;
  }

  public void removeAtFirst(int element) {
    int index = -1;
    for (int i = 0; i < size; i++) {
      if (array[i] == element) {
        index = i;
      }
    }

    if (index == -1) return;
    for (int i = index; i < size - 1; i++) {
      array[i] = array[i + 1];
    }
    size--;
  }

  public void ensureCapacityInternal() {
    if (array.length == size) {
      int[] newArray = new int[size * 2];
      for (int i = 0; i < size; i++) {
        newArray[i] = array[i];
      }
      array = newArray;
    }
  }

  public int length() {
    return size;
  }

  public void print() {
    System.out.print("[");
    for (int i = 0; i < size; i++) {
      System.out.print(array[i]);
      if (i < size - 1) System.out.print(",");
    }
    System.out.println("]");
  }

  public static void main(String[] args) {
    int i = 0;
    int result = ++i + i++ + ++i + ++i;

    System.out.println("result:" + result);
  }
}
