package com.pd.concurrency.example.immutable;

import com.pd.concurrency.annotations.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

  private static Map<Integer, Integer> map = new HashMap<>();

  static {
    map.put(1, 2);
    map.put(3, 4);
    map.put(5, 6);
    map = Collections.unmodifiableMap(map);
  }

  public static void main(String[] args) {
    map.put(1, 3);
    log.info("{}", map.get(1));
  }
}
