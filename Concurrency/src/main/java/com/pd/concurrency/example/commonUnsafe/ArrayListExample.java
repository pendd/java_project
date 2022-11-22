package com.pd.concurrency.example.commonUnsafe;

import com.pd.concurrency.annotations.NotThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class ArrayListExample {

  private static List<Integer> list = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(ArrayListExample::update);
    log.info("size:{}", list.size());
  }

  private static void update(int i) {
    list.add(i);
  }
}
