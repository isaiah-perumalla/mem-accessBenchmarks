package net.isaiahp.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class FalseSharingBench {

    @State(Scope.Group)
    public static  class Counter {
        private static final VarHandle T_0_COUNTER_VH;
        private static final VarHandle T_1_COUNTER_VH;

        static {
            try {
                MethodHandles.Lookup l = MethodHandles.lookup();
                T_0_COUNTER_VH = l.findVarHandle(Counter.class, "t0Counter", long.class);
                T_1_COUNTER_VH = l.findVarHandle(Counter.class, "t1Counter", long.class);
            } catch (ReflectiveOperationException e) {
                throw new Error(e);
            }
        }
        long t0Counter;
        long t1Counter;
    }

    @Benchmark
    @Group("write_2X")
    public long writer0(Counter s) {
        long c = s.t0Counter;
        Counter.T_0_COUNTER_VH.setRelease(s, c + 1);
        return c+1;
    }

    @Benchmark
    @Group("write_2X")
    public long writer1(Counter s) {
        long c = s.t1Counter;
        Counter.T_1_COUNTER_VH.setRelease(s, c + 1);
        return c+1;
    }

    @Benchmark
    @Group("write_volatile_2X")
    public long writerVolatile0(Counter s) {
        long c = s.t0Counter;
        Counter.T_0_COUNTER_VH.setVolatile(s, c + 1);
        return c+1;
    }

    @Benchmark
    @Group("write_volatile_2X")
    public long writerVolatile1(Counter s) {
        long c = s.t1Counter;
        Counter.T_1_COUNTER_VH.setVolatile(s, c + 1);
        return c+1;
    }

    @Benchmark
    @Group("write_Release_2X")
    public long writerRelease0(Counter s) {
        long c = s.t0Counter;
        Counter.T_0_COUNTER_VH.setRelease(s, c + 1);
        return c+1;
    }

    @Benchmark
    @Group("write_Release_2X")
    public long writerRelease1(Counter s) {
        long c = s.t1Counter;
        Counter.T_1_COUNTER_VH.setRelease(s, c + 1);
        return c+1;
    }

    @Benchmark
    @Group("atomicAdd_readVolatile")
    public long atomicAdd2(Counter s) {
        return (long) Counter.T_0_COUNTER_VH.getAndAdd(s, 1);
    }

    @Benchmark
    @Group("atomicAdd_readVolatile")
    public long readVolatile2(Counter s) {
        return (long) Counter.T_1_COUNTER_VH.getVolatile(s);

    }
    @Benchmark
    @Group("atomicAdd_2X")
    public long atomicAdd0(Counter s) {
        return (long) Counter.T_0_COUNTER_VH.getAndAdd(s, 1);
    }

    @Benchmark
    @Group("atomicAdd_2X")
    public long atomicAdd1(Counter s) {
        return (long) Counter.T_1_COUNTER_VH.getAndAdd(s, 1);

    }

    @Benchmark
    @Group("write_ReleaseReadVolatile")
    public long writeAndRelease(Counter s) {
        long c = s.t0Counter;
        Counter.T_0_COUNTER_VH.setRelease(s, c + 1);
        return c+1;
    }

    @Benchmark
    @Group("writeVolatileReadVolatile")
    public long readVolatile1(Counter s) {
        return (long) Counter.T_1_COUNTER_VH.getVolatile(s);
    }

    @Benchmark
    @Group("writeVolatileReadVolatile")
    public long writeVolatile(Counter s) {
        long c = s.t0Counter;
        Counter.T_0_COUNTER_VH.setVolatile(s, c + 1);
        return c+1;
    }

    @Benchmark
    @Group("write_ReleaseReadVolatile")
    public long readVolatile(Counter s) {
        return (long) Counter.T_1_COUNTER_VH.getVolatile(s);
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
