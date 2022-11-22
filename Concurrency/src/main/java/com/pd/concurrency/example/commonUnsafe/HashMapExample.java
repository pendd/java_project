package com.pd.concurrency.example.commonUnsafe;

import com.pd.concurrency.annotations.NotThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class HashMapExample {

  private static Map<Integer, Integer> map = new HashMap<>();

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(HashMapExample::update);

    log.info("size:{}", map.size());
  }

  private static void update(int i) {
    map.put(i, i);
  }
}
