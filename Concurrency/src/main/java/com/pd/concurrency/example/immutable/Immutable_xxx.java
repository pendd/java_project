package com.pd.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.pd.concurrency.annotations.ThreadSafe;

@ThreadSafe
public class ImmutableExample3 {

  private static final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

  private static final ImmutableSet set = ImmutableSet.copyOf(list);

  private static final ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

  private static final ImmutableMap<Integer, Integer> map2 =
      ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).put(5, 6).build();

  public static void main(String[] args) {
    System.out.println(map2.get(3));
  }
}
