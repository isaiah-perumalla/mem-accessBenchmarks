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

    @Param({"false", "true"})
    private boolean isPageAligned;

    private int[] offsets;

    @Setup
    public void setUp() {
        memory = Utils.UNSAFE;
        final int capacity = pages * PG_SIZE;
        long address = memory.allocateMemory(capacity + (CACHE_LINE_SIZE * 2));
        baseAddress = getCacheLineAlignedAddress(address, CACHE_LINE_SIZE);

        if(Long.bitCount(capacity) != 1) {
            throw new IllegalStateException("memory capacity is not power of 2");
        }
        int align = isPageAligned ? 0 : CACHE_LINE_SIZE;
        offsets = new int[MAX_ITERS];
        for(int i = 0; i < MAX_ITERS; i ++) {
            int offset = (i*PG_SIZE) & (capacity -1) + align;
            offsets[i] = offset;
        }
        offsets[MAX_ITERS -1] = 0;

    }

    @Benchmark
    public long pageReadWrite() {
        long result = 0;
        for (int i = 0; i < MAX_ITERS; i ++) {
            long offset = offsets[i];
            long address = baseAddress + offset;
            memory.putInt(address, 42);
            result += offset;
        }
        return result;
    }



}
