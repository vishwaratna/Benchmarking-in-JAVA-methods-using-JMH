package com.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
public class Testing {

  @Param({"10"})
  private int number;

  @Benchmark
  @BenchmarkMode({Mode.All})
  //traditional method to generate N numbers and store it in List
  public List<Integer> traditionalMethod(Testing T) {
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= number; i++) {
      list.add(i);
    }
    return list;
  }

  @Benchmark
  @BenchmarkMode({Mode.All})
  //using #stream to generate N numbers and store it in List
  public List<Integer> streamMethod(Testing T) {
    return IntStream.rangeClosed(1, number).boxed().collect(Collectors.toList());
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(Testing.class.getSimpleName())
        .forks(1)
        .build();

    new Runner(opt).run();
  }
}
