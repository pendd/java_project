package com.pd.concurrency.example.immutable;

import com.pd.concurrency.annotations.NotThreadSafe;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

  private static final Integer a = 1;
  private static final String b = "2";
  private static final Map<Integer, Integer> map = new HashMap<>();

  static {
    map.put(1, 2);
    map.put(3, 4);
    map.put(5, 6);
  }

  public static void main(String[] args) {
    //        a = 2;
    //        b = "3";
    //        map = Maps.newHashMap();
    map.put(1, 3);
    log.info("{}", map.get(1));
  }

  private void test(final int a) {
    //        a = 1;
  }
}
