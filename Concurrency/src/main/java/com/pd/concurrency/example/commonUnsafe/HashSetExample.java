package com.pd.concurrency.example.commonUnsafe;

import com.pd.concurrency.annotations.NotThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class HashSetExample {

  private static Set<Integer> set = new HashSet<>();

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(HashSetExample::update);
    log.info("size:{}", set.size());
  }

  private static void update(int i) {
    set.add(i);
  }
}
