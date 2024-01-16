Benchmarks to measure effects of memory access patterns


### Build 
```
./gradlew -Dorg.gradle.java.home=<jdk_path>  bench_jar

```

The above gradle task should build JMH benchmark jar.

```
java -jar mem-bench/build/libs/mem-bench-all.jar -wi 3 -i 5 -f 2

```

### CacheLine / word Alignment benchmarks

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


### Page Alignment benchmarks

#### ARM 8 64-bit 
ways_of_associativity: 32
coherency_line_size: 128
number of sets: 8
size: 32K

```
# Run complete. Total time: 00:02:04

Benchmark                             (isPageAligned)  (pages)  Mode  Cnt    Score   Error  Units
CacheConflictBenchmark.pageReadWrite            false        4  avgt    5   21.491 ± 0.024  us/op
CacheConflictBenchmark.pageReadWrite            false        8  avgt    5   21.492 ± 0.022  us/op
CacheConflictBenchmark.pageReadWrite            false       16  avgt    5   21.492 ± 0.018  us/op
CacheConflictBenchmark.pageReadWrite            false       32  avgt    5   21.494 ± 0.016  us/op
CacheConflictBenchmark.pageReadWrite            false       64  avgt    5   21.498 ± 0.019  us/op
CacheConflictBenchmark.pageReadWrite            false      128  avgt    5   21.502 ± 0.045  us/op
CacheConflictBenchmark.pageReadWrite            false      512  avgt    5   21.488 ± 0.019  us/op
CacheConflictBenchmark.pageReadWrite             true        4  avgt    5   21.502 ± 0.024  us/op
CacheConflictBenchmark.pageReadWrite             true        8  avgt    5   21.501 ± 0.018  us/op
CacheConflictBenchmark.pageReadWrite             true       16  avgt    5   29.710 ± 0.019  us/op
CacheConflictBenchmark.pageReadWrite             true       32  avgt    5   61.133 ± 0.144  us/op
CacheConflictBenchmark.pageReadWrite             true       64  avgt    5  106.485 ± 0.151  us/op
CacheConflictBenchmark.pageReadWrite             true      128  avgt    5  106.342 ± 0.251  us/op
CacheConflictBenchmark.pageReadWrite             true      512  avgt    5  357.565 ± 3.332  us/op


```






#### ARM 7 32 bit
```
sudo java -jar mem-bench/build/libs/mem-bench-all.jar -wi 3 -i 3 -f 1 CacheConflict* -prof perfnorm


```
Results
``` 
# Run complete. Total time: 00:02:03

Benchmark                             (isPageAligned)  (pages)  Mode  Cnt    Score    Error  Units
CacheConflictBenchmark.pageReadWrite            false        1  avgt    5  202.448 ±  0.327  us/op
CacheConflictBenchmark.pageReadWrite            false        2  avgt    5  202.768 ±  0.155  us/op
CacheConflictBenchmark.pageReadWrite            false        4  avgt    5  202.668 ±  0.200  us/op
CacheConflictBenchmark.pageReadWrite            false        8  avgt    5  203.059 ±  0.988  us/op
CacheConflictBenchmark.pageReadWrite            false       32  avgt    5  202.448 ±  0.196  us/op
CacheConflictBenchmark.pageReadWrite            false      128  avgt    5  202.798 ±  0.635  us/op
CacheConflictBenchmark.pageReadWrite             true        1  avgt    5  204.281 ± 15.559  us/op
CacheConflictBenchmark.pageReadWrite             true        2  avgt    5  202.626 ±  0.158  us/op
CacheConflictBenchmark.pageReadWrite             true        4  avgt    5  355.587 ±  0.377  us/op
CacheConflictBenchmark.pageReadWrite             true        8  avgt    5  357.414 ±  2.564  us/op
CacheConflictBenchmark.pageReadWrite             true       32  avgt    5  658.425 ±  1.948  us/op
CacheConflictBenchmark.pageReadWrite             true      128  avgt    5  591.343 ±  3.837  us/op

```

#### intel Xeon 64 bit , 32k L1 data cache


```
Benchmark                             (isPageAligned)  (pages)  Mode  Cnt   Score    Error  Units
CacheConflictBenchmark.pageReadWrite            false        4  avgt    3   5.285 ±  0.430  us/op
CacheConflictBenchmark.pageReadWrite            false        8  avgt    3   5.361 ±  1.621  us/op
CacheConflictBenchmark.pageReadWrite            false       32  avgt    3   5.154 ±  1.896  us/op
CacheConflictBenchmark.pageReadWrite            false      128  avgt    3   5.430 ±  0.048  us/op
CacheConflictBenchmark.pageReadWrite            false      512  avgt    3   5.289 ±  0.089  us/op
CacheConflictBenchmark.pageReadWrite            false     1024  avgt    3   5.388 ±  1.921  us/op
CacheConflictBenchmark.pageReadWrite             true        4  avgt    3   5.102 ±  0.152  us/op
CacheConflictBenchmark.pageReadWrite             true        8  avgt    3  11.891 ±  4.445  us/op
CacheConflictBenchmark.pageReadWrite             true       32  avgt    3  39.232 ± 80.007  us/op
CacheConflictBenchmark.pageReadWrite             true      128  avgt    3  46.604 ± 37.191  us/op
CacheConflictBenchmark.pageReadWrite             true      512  avgt    3  46.259 ± 34.606  us/op
CacheConflictBenchmark.pageReadWrite             true     1024  avgt    3  47.330 ± 27.088  us/op

```
#### Intel(R) Xeon(R) CPU E3-1270 v6 @ 3.80GHz
``` 
# Run complete. Total time: 00:01:37

Benchmark                             (isPageAligned)  (pages)  Mode  Cnt   Score   Error  Units
CacheConflictBenchmark.pageReadWrite            false        4  avgt    5   2.679 ? 0.032  us/op
CacheConflictBenchmark.pageReadWrite            false        8  avgt    5   2.684 ? 0.061  us/op
CacheConflictBenchmark.pageReadWrite            false       32  avgt    5   2.684 ? 0.043  us/op
CacheConflictBenchmark.pageReadWrite            false      128  avgt    5   2.759 ? 0.063  us/op
CacheConflictBenchmark.pageReadWrite            false      512  avgt    5   2.765 ? 0.060  us/op
CacheConflictBenchmark.pageReadWrite            false     1024  avgt    5   2.762 ? 0.012  us/op
CacheConflictBenchmark.pageReadWrite             true        4  avgt    5   2.697 ? 0.056  us/op
CacheConflictBenchmark.pageReadWrite             true        8  avgt    5   2.926 ? 0.031  us/op
CacheConflictBenchmark.pageReadWrite             true       32  avgt    5  18.928 ? 0.211  us/op
CacheConflictBenchmark.pageReadWrite             true      128  avgt    5  27.438 ? 1.149  us/op
CacheConflictBenchmark.pageReadWrite             true      512  avgt    5  27.692 ? 0.632  us/op
CacheConflictBenchmark.pageReadWrite             true     1024  avgt    5  27.101 ? 0.302  us/op

```
### False sharing
plain access
```
Benchmark                              Mode  Cnt  Score   Error  Units
FalseSharingBench.write_read           avgt    2  0.004          us/op
FalseSharingBench.write_read:reader    avgt    2  0.006          us/op
FalseSharingBench.write_read:writer    avgt    2  0.002          us/op
FalseSharingBench.write_write          avgt    2  0.003          us/op
FalseSharingBench.write_write:writer0  avgt    2  0.003          us/op
FalseSharingBench.write_write:writer1  avgt    2  0.003          us/op
```