package com.pd.concurrency.example.concurrent;

import com.pd.concurrency.annotations.ThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: pd
 * @date: 2021-03-07 下午2:56
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteOrConcurrentExample {
  private static Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
  private static Map<Integer, Integer> concurrentSkipListMap = new ConcurrentSkipListMap<>();
  private static Set<Integer> concurrentSkipListSet = new ConcurrentSkipListSet<>();
  private static List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
  private static Set<Integer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

  public static void main(String[] args) throws InterruptedException {
    ThreadTestUtil.testThread(CopyOnWriteOrConcurrentExample::concurrentHashMapUpdate);
    ThreadTestUtil.testThread(CopyOnWriteOrConcurrentExample::concurrentSkipListMapUpdate);
    ThreadTestUtil.testThread(CopyOnWriteOrConcurrentExample::concurrentSkipListSetUpdate);
    ThreadTestUtil.testThread(CopyOnWriteOrConcurrentExample::copyOnWriteArrayListUpdate);
    ThreadTestUtil.testThread(CopyOnWriteOrConcurrentExample::copyOnWriteArraySetUpdate);
    log.info("concurrentHashMap.size:{}", concurrentHashMap.size());
    log.info("concurrentSkipListMap.size:{}", concurrentSkipListMap.size());
    log.info("concurrentSkipListSet.size:{}", concurrentSkipListSet.size());
    log.info("copyOnWriteArrayList.size:{}", copyOnWriteArrayList.size());
    log.info("copyOnWriteArraySet.size:{}", copyOnWriteArraySet.size());
  }

  private static void concurrentHashMapUpdate(int i) {
    concurrentHashMap.put(i, i);
  }

  private static void concurrentSkipListMapUpdate(int i) {
    concurrentSkipListMap.put(i, i);
  }

  private static void copyOnWriteArrayListUpdate(int i) {
    copyOnWriteArrayList.add(i);
  }

  private static void concurrentSkipListSetUpdate(int i) {
    concurrentSkipListSet.add(i);
  }

  private static void copyOnWriteArraySetUpdate(int i) {
    copyOnWriteArraySet.add(i);
  }
}
