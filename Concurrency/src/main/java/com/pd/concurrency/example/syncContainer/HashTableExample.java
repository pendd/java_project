package com.pd.concurrency.example.syncContainer;

import com.pd.concurrency.annotations.ThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.util.Hashtable;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class HashTableExample {

  private static Map<Integer, Integer> map = new Hashtable<>();

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(HashTableExample::update);
    log.info("size:{}", map.size());
  }

  private static void update(int i) {
    map.put(i, i);
  }
}
