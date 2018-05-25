package net.isaiahp.benchmarks;

import org.openjdk.jmh.annotations.*;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

import static net.isaiahp.benchmarks.Utils.getCacheLineAlignedAddress;

@State(Scope.Thread) @BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class CacheConflictBenchmark {

    private static final int CACHE_LINE_SIZE = Integer.getInteger("CacheLineSize", 64);
    private static final int PG_SIZE = 4096;
    private static final int MAX_ITERS = 100_00;
    private Unsafe memory;
    @Param({"32", "128", "1024", "4096"})
    private int pages;
    private long baseAddress;

    @Param({"0", "64"})
    private int align ;

    @Setup
    public void setUp() {
        memory = Utils.UNSAFE;
        long address = memory.allocateMemory((pages * PG_SIZE) + (CACHE_LINE_SIZE * 2));
        baseAddress = getCacheLineAlignedAddress(address, CACHE_LINE_SIZE);

        if(Long.bitCount(pages * PG_SIZE) != 1) {
            throw new IllegalStateException("memory capacity is not power of 2");
        }


    }

    @Benchmark
    public long linearPageWrite() {
        long result = 0;
        long maxCapacity = pages * PG_SIZE;
        for (int i = 0; i < MAX_ITERS; i ++) {
            long offset = (i*PG_SIZE) & (maxCapacity -1);
            long address = baseAddress + offset + align;
            memory.putInt(address, 42);
            result += offset;
        }

        return result;
    }



}
