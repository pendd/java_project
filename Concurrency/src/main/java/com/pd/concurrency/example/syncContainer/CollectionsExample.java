package com.pd.concurrency.example.syncContainer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.pd.concurrency.annotations.ThreadSafe;
import com.pd.concurrency.example.util.ThreadTestUtil;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class CollectionsExample {

  private static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());
  private static Set<Integer> set = Collections.synchronizedSet(Sets.newHashSet());
  private static Map<Integer, Integer> map = Collections.synchronizedMap(Maps.newHashMap());

  public static void main(String[] args) throws Exception {
    ThreadTestUtil.testThread(CollectionsExample::update);
    log.info("list.size:{}", list.size());
    log.info("set.size:{}", set.size());
    log.info("map.size:{}", map.size());
  }

  private static void update(int i) {
    list.add(i);
    set.add(i);
    map.put(i, i);
  }
}
