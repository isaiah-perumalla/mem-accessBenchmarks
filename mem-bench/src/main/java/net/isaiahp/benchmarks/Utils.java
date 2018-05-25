package net.isaiahp.benchmarks;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Utils {

    public static final Unsafe UNSAFE;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe)field.get(null);
        } catch (Exception e) {
            throw new IllegalStateException("could not find unsafe");
        }
    }

    public static long getCacheLineAlignedAddress(long address, int cacheLineSize) {
        return address / cacheLineSize * cacheLineSize + cacheLineSize;
    }
}
