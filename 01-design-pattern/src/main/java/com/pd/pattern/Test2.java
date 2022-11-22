package com.pd.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author YCWB0382
 * @date 2022-06-14 17:36
 */
public class Test2 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    final Map<String, List<String>> collect =
        list.stream().collect(Collectors.groupingBy(Function.identity()));
    System.out.println(collect == null);
    final List<String> strings = collect.get(1);
    System.out.println(strings == null);
  }
}
