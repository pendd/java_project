package com.pd.concurrency.book;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamPuzzle2 {
  public static final Deque<String> trace = new ConcurrentLinkedDeque<>();

  static class IntGenerator implements Supplier<Integer> {
    private AtomicInteger current = new AtomicInteger();

    public Integer get() {
      trace.add(current.get() + ": " + Thread.currentThread().getName());
      return current.getAndIncrement();
    }
  }

  public static void main(String[] args) throws Exception {
    List<Integer> collect =
        IntStream.rangeClosed(0, 9)
            .peek(e -> System.out.println(e + ","))
            .parallel()
            .boxed()
            .collect(Collectors.toList());
    System.out.println(collect);

    //    List<Integer> y = Stream.generate(new
    // IntGenerator()).limit(10).collect(Collectors.toList());
    //    System.out.println(y);

    // 下面无限流即使使用 limit 截断  但是generate依旧会执行1024次，但是只取10个结果
    List<Integer> x =
        Stream.generate(new IntGenerator())
            //            .peek(e -> System.out.print(e + "，"))
            .limit(10)
            //            .peek(e -> System.out.println(e + ": " +
            // Thread.currentThread().getName()))
            .parallel()
            .collect(Collectors.toList());
    System.out.println(x);
    Files.write(Paths.get("PSP2.txt"), trace);
  }
}
