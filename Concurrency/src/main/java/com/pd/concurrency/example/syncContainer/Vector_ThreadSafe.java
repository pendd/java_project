package com.pd.concurrency.example.syncContainer;

import com.pd.concurrency.annotations.ThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.util.List;
import java.util.Vector;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class Vector_ThreadSafe {

  private static List<Integer> list = new Vector<>();

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(Vector_ThreadSafe::update);
    log.info("size:{}", list.size());
  }

  private static void update(int i) {
    list.add(i);
  }
}
