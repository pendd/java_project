package com.pd.concurrency.structure;

import java.util.NoSuchElementException;

public class LinkedListTea {

  // 由于这个类仅由自己用，不暴露给外界 所以使用private 加内部类方式
  private class Node {
    private int value;
    private Node next;

    Node(int value) {
      this.value = value;
    }
  }

  private Node first;
  private Node last;
  private int size;

  public void addLast(int element) {
    Node node = new Node(element);
    if (isEmpty()) {
      first = last = node;
    } else {
      // 这里的last 指的是没有加入当前element元素之前的最后一个
      // 把它的next指向当前element
      last.next = node;
      // 设置当前的element元素为最后一个
      last = node;
    }
    size++;
  }

  public void addFirst(int element) {
    Node node = new Node(element);
    if (isEmpty()) {
      first = last = node;
    } else {
      // 把当前这个element元素指向之前的第一个元素
      // 之前的第一个元素就变为第二个元素了
      node.next = first;
      // 把当前元素设置为first
      first = node;
    }
    size++;
  }

  public int indexOf(int element) {
    Node node = first;
    int index = 0;
    while (node != null) {
      if (node.value == element) {
        return index;
      }
      node = node.next;
      index++;
    }
    return -1;
  }

  public void removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    if (first == last) {
      first = last = null;
    } else {
      Node toFirstNode = first.next;
      first.next = null;
      first = toFirstNode;
    }
    size--;
  }

  public void removeLast() {
    Node previous = getPrevious(last);
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    if (previous == null) {
      first = last = null;
    } else {
      last = previous;
      last.next = null;
    }
    size--;
  }

  public int[] toArray() {
    int index = 0;
    int[] array = new int[size];
    Node current = first;
    if (current != null) {
      array[index++] = current.value;
      current = current.next;
    }
    return array;
  }

  public boolean contains(int element) {
    return indexOf(element) != -1;
  }

  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    Node node = first;
    while (node != null) {
      builder.append(node.value);
      if (node != last) {
        builder.append(" ");
      }
      node = node.next;
    }
    builder.append("]");
    return builder.toString();
  }

  private boolean isEmpty() {
    return first == null;
  }

  private Node getPrevious(Node node) {
    Node loopNode = first;
    while (loopNode != null) {
      if (loopNode.next == node) {
        return loopNode;
      }
      loopNode = loopNode.next;
    }
    return null;
  }
}
