Benchmarks to measure effects of memory access patterns


### Build 
```
./gradlew -Dorg.gradle.java.home=<jdk_path>  bench_jar

```

The above gradle task should build JMH benchmark jar.

```
java -jar mem-bench/build/libs/mem-bench-all.jar -wi 3 -i 5 -f 2

```

### Memory Alignment benchmarks

#### ARM 7 32 bit 
JMH benchmark results 
```
Benchmark                                (memBlockSize)  (misAlignOffset)  Mode  Cnt        Score       Error  Units
MemAlignmentBenchmark.linearWrite                  4096                 0  avgt    3       89.715 ±     0.655  us/op
MemAlignmentBenchmark.linearWrite                  4096                 1  avgt    3       89.664 ±     1.849  us/op
MemAlignmentBenchmark.linearWrite                  4096                -1  avgt    3       89.887 ±     4.335  us/op
MemAlignmentBenchmark.linearWrite                 65536                 0  avgt    3     1565.938 ±    24.265  us/op
MemAlignmentBenchmark.linearWrite                 65536                 1  avgt    3     1568.062 ±    59.888  us/op
MemAlignmentBenchmark.linearWrite                 65536                -1  avgt    3     1918.832 ±    83.640  us/op
MemAlignmentBenchmark.linearWrite               1048576                 0  avgt    3    25611.007 ±  1685.394  us/op
MemAlignmentBenchmark.linearWrite               1048576                 1  avgt    3    25542.187 ±  4695.032  us/op
MemAlignmentBenchmark.linearWrite               1048576                -1  avgt    3    33026.602 ±  8863.661  us/op
MemAlignmentBenchmark.linearWrite              16777216                 0  avgt    3   407155.688 ±  4809.299  us/op
MemAlignmentBenchmark.linearWrite              16777216                 1  avgt    3   407601.876 ± 13148.847  us/op
MemAlignmentBenchmark.linearWrite              16777216                -1  avgt    3   497677.225 ± 29272.188  us/op
MemAlignmentBenchmark.pseudoRandomWrite            4096                 0  avgt    3       98.944 ±     0.134  us/op
MemAlignmentBenchmark.pseudoRandomWrite            4096                 1  avgt    3       99.141 ±     4.990  us/op
MemAlignmentBenchmark.pseudoRandomWrite            4096                -1  avgt    3      101.862 ±     0.075  us/op
MemAlignmentBenchmark.pseudoRandomWrite           65536                 0  avgt    3     2427.826 ±   157.557  us/op
MemAlignmentBenchmark.pseudoRandomWrite           65536                 1  avgt    3     2548.897 ±   179.833  us/op
MemAlignmentBenchmark.pseudoRandomWrite           65536                -1  avgt    3     3425.561 ±   235.718  us/op
MemAlignmentBenchmark.pseudoRandomWrite         1048576                 0  avgt    3    79802.016 ±  3839.396  us/op
MemAlignmentBenchmark.pseudoRandomWrite         1048576                 1  avgt    3    80007.067 ±  9291.158  us/op
MemAlignmentBenchmark.pseudoRandomWrite         1048576                -1  avgt    3   113246.661 ± 11960.413  us/op
MemAlignmentBenchmark.pseudoRandomWrite        16777216                 0  avgt    3  1753395.208 ± 63151.447  us/op
MemAlignmentBenchmark.pseudoRandomWrite        16777216                 1  avgt    3  1756633.994 ± 29088.063  us/op
MemAlignmentBenchmark.pseudoRandomWrite        16777216                -1  avgt    3  2231768.095 ± 97109.094  us/op

```

