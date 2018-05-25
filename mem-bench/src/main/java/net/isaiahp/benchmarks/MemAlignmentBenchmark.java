package net.isaiahp.benchmarks;

import org.openjdk.jmh.annotations.*;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

import static net.isaiahp.benchmarks.Utils.getCacheLineAlignedAddress;

@State(Scope.Thread) @BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class MemAlignmentBenchmark {

    public static final int PRNG_SEED = 1327;
    @Param({"4096", "65536", "1048576", "16777216"})
    private int memBlockSize;

    @Param({"0", "1", "-1"})
    private int misAlignOffset;

    private long baseAddress;
    private Unsafe memory;
    private static int CACHE_LINE_SIZE = Integer.getInteger("CacheLineSize", 64);


    @Setup
    public void setUp() {
        memory = Utils.UNSAFE;
        long address = memory.allocateMemory(memBlockSize + CACHE_LINE_SIZE);
        long cacheLineAlignedAddress = getCacheLineAlignedAddress(address, CACHE_LINE_SIZE);
        baseAddress = cacheLineAlignedAddress;
        //pre fault pages
        for(int i =0; i < memBlockSize; i+= CACHE_LINE_SIZE) {
            memory.putInt(baseAddress + i, 0);
        }

    }



    @Benchmark
    public long linearWrite() {
        long result = 0;
        for(int i = 0; i < memBlockSize; i++) {
            int offset = (i * CACHE_LINE_SIZE) & (memBlockSize -1);
            long address = baseAddress + offset + misAlignOffset;
            memory.putInt(address, 42);
            result += address;
        }
        return result;
    }


    @Benchmark
    public long pseudoRandomWrite() {
        long result = 0 ;
        int offset = 0;
        for(int i =0; i < memBlockSize; i++) {
            offset = (offset + (CACHE_LINE_SIZE * PRNG_SEED)) & (memBlockSize -1);
            long address = baseAddress + offset + misAlignOffset;
            memory.putInt(address, 42);
            result += address;
        }
        return result;
    }


}
