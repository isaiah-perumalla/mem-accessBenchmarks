package net.isaiahp.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class FalseSharingBench {

    @State(Scope.Group)
    public static  class Counter {
        long t0Counter;
        long t1Counter;
    }

    @Benchmark
    @Group("write_write")
    public long writer0(Counter s) {
        return s.t0Counter++;
    }

    @Benchmark
    @Group("write_write")
    public long writer1(Counter s) {
        return s.t1Counter++;
    }

    @Benchmark
    @Group("write_read")
    public long writer(Counter s) {
        return s.t0Counter++;
    }

    @Benchmark
    @Group("write_read")
    public long reader(Counter s) {
        return s.t1Counter;
    }
}
