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
#### ARM 7 32 bit
```
sudo java -jar mem-bench/build/libs/mem-bench-all.jar -wi 3 -i 3 -f 1 CacheConflict* -prof perfnorm


```
Results
``` 
Benchmark                             (isPageAligned)  (pages)  Mode  Cnt    Score    Error  Units
CacheConflictBenchmark.pageReadWrite            false       32  avgt    3  202.550 ±  2.406  us/op
CacheConflictBenchmark.pageReadWrite            false      128  avgt    3  203.078 ±  5.340  us/op
CacheConflictBenchmark.pageReadWrite            false     1024  avgt    3  203.699 ± 16.421  us/op
CacheConflictBenchmark.pageReadWrite            false     4096  avgt    3  202.729 ±  4.182  us/op
CacheConflictBenchmark.pageReadWrite             true       32  avgt    3  492.408 ± 14.038  us/op
CacheConflictBenchmark.pageReadWrite             true      128  avgt    3  583.352 ± 39.226  us/op
CacheConflictBenchmark.pageReadWrite             true     1024  avgt    3  592.209 ± 78.987  us/op
CacheConflictBenchmark.pageReadWrite             true     4096  avgt    3  591.641 ± 35.948  us/op

```
Benchmark results with cpu counter stats

```
 Benchmark                                                     (isPageAligned)  (pages)  Mode  Cnt        Score    Error  Units
 CacheConflictBenchmark.pageReadWrite                                    false       32  avgt    3      203.208 ±  0.089  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                false       32  avgt            15.135            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses              false       32  avgt          1275.856            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                    false       32  avgt         97224.684            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses             false       32  avgt          1270.661            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                   false       32  avgt         99563.013            #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses              false       32  avgt            42.312            #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                      false       32  avgt            98.903            #/op
 CacheConflictBenchmark.pageReadWrite:branches                           false       32  avgt          9831.627            #/op
 CacheConflictBenchmark.pageReadWrite:cycles                             false       32  avgt        735953.030            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                   false       32  avgt            68.000            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                  false       32  avgt            60.076            #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                   false       32  avgt             0.742            #/op
 CacheConflictBenchmark.pageReadWrite:instructions                       false       32  avgt         48624.398            #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend            false       32  avgt            17.360            #/op
 CacheConflictBenchmark.pageReadWrite                                    false      128  avgt    3      204.106 ±  2.147  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                false      128  avgt             9.974            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses              false      128  avgt          1270.346            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                    false      128  avgt         99949.582            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses             false      128  avgt          1268.023            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                   false      128  avgt         99133.654            #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses              false      128  avgt            73.233            #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                      false      128  avgt            46.364            #/op
 CacheConflictBenchmark.pageReadWrite:branches                           false      128  avgt         10050.116            #/op
 CacheConflictBenchmark.pageReadWrite:cycles                             false      128  avgt        507644.035            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                   false      128  avgt           103.609            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                  false      128  avgt           107.964            #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                   false      128  avgt            12.798            #/op
 CacheConflictBenchmark.pageReadWrite:instructions                       false      128  avgt         50898.000            #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend            false      128  avgt            49.241            #/op
 CacheConflictBenchmark.pageReadWrite                                    false     1024  avgt    3      203.266 ±  4.102  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                false     1024  avgt             9.575            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses              false     1024  avgt          1286.122            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                    false     1024  avgt        100358.080            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses             false     1024  avgt          1279.275            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                   false     1024  avgt         99713.344            #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses              false     1024  avgt            56.528            #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                      false     1024  avgt            20.569            #/op
 CacheConflictBenchmark.pageReadWrite:branches                           false     1024  avgt         10045.480            #/op
 CacheConflictBenchmark.pageReadWrite:cycles                             false     1024  avgt        481991.003            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                   false     1024  avgt           134.417            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                  false     1024  avgt           141.086            #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                   false     1024  avgt            33.527            #/op
 CacheConflictBenchmark.pageReadWrite:instructions                       false     1024  avgt         50338.098            #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend            false     1024  avgt            42.642            #/op
 CacheConflictBenchmark.pageReadWrite                                    false     4096  avgt    3      203.280 ±  5.622  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                false     4096  avgt            10.842            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses              false     4096  avgt          1257.166            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                    false     4096  avgt         97961.529            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses             false     4096  avgt          1268.318            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                   false     4096  avgt         98640.983            #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses              false     4096  avgt            53.921            #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                      false     4096  avgt            59.587            #/op
 CacheConflictBenchmark.pageReadWrite:branches                           false     4096  avgt          9739.950            #/op
 CacheConflictBenchmark.pageReadWrite:cycles                             false     4096  avgt        539906.205            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                   false     4096  avgt           126.858            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                  false     4096  avgt            84.541            #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                   false     4096  avgt             5.505            #/op
 CacheConflictBenchmark.pageReadWrite:instructions                       false     4096  avgt         49797.155            #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend            false     4096  avgt            15.176            #/op
 CacheConflictBenchmark.pageReadWrite                                     true       32  avgt    3      684.898 ± 31.310  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                 true       32  avgt            17.581            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses               true       32  avgt         11243.268            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                     true       32  avgt        100482.194            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses              true       32  avgt         11162.340            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                    true       32  avgt        100825.220            #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses               true       32  avgt           220.231            #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                       true       32  avgt            98.110            #/op
 CacheConflictBenchmark.pageReadWrite:branches                            true       32  avgt         10010.644            #/op
 CacheConflictBenchmark.pageReadWrite:cycles                              true       32  avgt        869778.243            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                    true       32  avgt         30124.119            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                   true       32  avgt         29753.568            #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                    true       32  avgt            77.987            #/op
 CacheConflictBenchmark.pageReadWrite:instructions                        true       32  avgt         49472.555            #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend             true       32  avgt           140.843            #/op
 CacheConflictBenchmark.pageReadWrite                                     true      128  avgt    3      779.339 ± 14.972  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                 true      128  avgt            20.907            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses               true      128  avgt         11014.722            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                     true      128  avgt        102256.180            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses              true      128  avgt         10824.524            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                    true      128  avgt        100123.732            #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses               true      128  avgt           183.467            #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                       true      128  avgt           290.695            #/op
 CacheConflictBenchmark.pageReadWrite:branches                            true      128  avgt         10099.414            #/op
 CacheConflictBenchmark.pageReadWrite:cycles                              true      128  avgt       1047063.819            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                    true      128  avgt         29236.066            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                   true      128  avgt         29885.659            #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                    true      128  avgt            12.284            #/op
 CacheConflictBenchmark.pageReadWrite:instructions                        true      128  avgt         50081.412            #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend             true      128  avgt            78.940            #/op
 CacheConflictBenchmark.pageReadWrite                                     true     1024  avgt    3      654.941 ± 15.538  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                 true     1024  avgt            37.094            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses               true     1024  avgt         11374.183            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                     true     1024  avgt        120808.599            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses              true     1024  avgt         11259.959            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                    true     1024  avgt        120554.228            #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses               true     1024  avgt           235.859            #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                       true     1024  avgt            40.290            #/op
 CacheConflictBenchmark.pageReadWrite:branches                            true     1024  avgt          9977.511            #/op
 CacheConflictBenchmark.pageReadWrite:cycles                              true     1024  avgt       1852690.503            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                    true     1024  avgt         29082.385            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                   true     1024  avgt         29095.590            #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                    true     1024  avgt            50.581            #/op
 CacheConflictBenchmark.pageReadWrite:instructions                        true     1024  avgt         49946.229            #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend             true     1024  avgt           147.106            #/op
 CacheConflictBenchmark.pageReadWrite                                     true     4096  avgt    3      615.349 ± 85.604  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                 true     4096  avgt            32.162            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses               true     4096  avgt         12498.800            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                     true     4096  avgt        124690.694            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses              true     4096  avgt         12755.185            #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                    true     4096  avgt        126188.341            #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses               true     4096  avgt           106.919            #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                       true     4096  avgt           390.590            #/op
 CacheConflictBenchmark.pageReadWrite:branches                            true     4096  avgt         10605.174            #/op
 CacheConflictBenchmark.pageReadWrite:cycles                              true     4096  avgt       1644808.029            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                    true     4096  avgt         29344.493            #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                   true     4096  avgt         28841.005            #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                    true     4096  avgt             7.082            #/op
 CacheConflictBenchmark.pageReadWrite:instructions                        true     4096  avgt         51140.920            #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend             true     4096  avgt            55.873            #/op

 ```
#### intel Xeon 64 bit , 32k L1 data cache


```
# Run complete. Total time: 00:00:51

Benchmark                             (isPageAligned)  (pages)  Mode  Cnt    Score     Error  Units
CacheConflictBenchmark.pageReadWrite            false       32  avgt    3    5.782 ±   3.153  us/op
CacheConflictBenchmark.pageReadWrite            false      128  avgt    3    5.397 ±   1.772  us/op
CacheConflictBenchmark.pageReadWrite            false     1024  avgt    3    4.985 ±   0.047  us/op
CacheConflictBenchmark.pageReadWrite            false     4096  avgt    3    5.239 ±   0.013  us/op
CacheConflictBenchmark.pageReadWrite             true       32  avgt    3   39.144 ±  79.990  us/op
CacheConflictBenchmark.pageReadWrite             true      128  avgt    3   46.456 ±  33.661  us/op
CacheConflictBenchmark.pageReadWrite             true     1024  avgt    3   46.400 ±  35.230  us/op
CacheConflictBenchmark.pageReadWrite             true     4096  avgt    3  147.837 ± 780.941  us/op


```

Benchmark results with cpu counter stats

```
 Benchmark                                                     (isPageAligned)  (pages)  Mode  Cnt       Score     Error  Units
 CacheConflictBenchmark.pageReadWrite                                    false       32  avgt    3       5.586 ±   2.250  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                false       32  avgt            0.458             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses              false       32  avgt          615.152             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                    false       32  avgt        13920.119             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses             false       32  avgt            1.780             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                   false       32  avgt        12683.862             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses              false       32  avgt           11.299             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-loads                    false       32  avgt        12763.885             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-load-misses                    false       32  avgt            0.831             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-loads                          false       32  avgt            1.997             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-store-misses                   false       32  avgt            0.106             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-stores                         false       32  avgt            0.810             #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                      false       32  avgt            2.786             #/op
 CacheConflictBenchmark.pageReadWrite:branches                           false       32  avgt          670.106             #/op
 CacheConflictBenchmark.pageReadWrite:cycles                             false       32  avgt        16967.276             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                   false       32  avgt            0.933             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-loads                         false       32  avgt        16101.514             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                  false       32  avgt            0.695             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-stores                        false       32  avgt        12840.067             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                   false       32  avgt            0.330             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-loads                         false       32  avgt        37341.592             #/op
 CacheConflictBenchmark.pageReadWrite:instructions                       false       32  avgt        37064.905             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-backend             false       32  avgt          357.312             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend            false       32  avgt         3272.579             #/op
 CacheConflictBenchmark.pageReadWrite                                    false      128  avgt    3       5.315 ±   0.074  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                false      128  avgt            0.442             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses              false      128  avgt          625.656             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                    false      128  avgt        14158.376             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses             false      128  avgt            1.743             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                   false      128  avgt        12923.875             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses              false      128  avgt            5.275             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-loads                    false      128  avgt        13033.383             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-load-misses                    false      128  avgt            0.675             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-loads                          false      128  avgt            0.816             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-store-misses                   false      128  avgt            0.060             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-stores                         false      128  avgt            0.868             #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                      false      128  avgt            2.038             #/op
 CacheConflictBenchmark.pageReadWrite:branches                           false      128  avgt          654.370             #/op
 CacheConflictBenchmark.pageReadWrite:cycles                             false      128  avgt        16718.734             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                   false      128  avgt            0.455             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-loads                         false      128  avgt        15934.572             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                  false      128  avgt            0.520             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-stores                        false      128  avgt        13092.117             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                   false      128  avgt            0.113             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-loads                         false      128  avgt        38099.101             #/op
 CacheConflictBenchmark.pageReadWrite:instructions                       false      128  avgt        37834.260             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-backend             false      128  avgt           78.909             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend            false      128  avgt         2740.623             #/op
 CacheConflictBenchmark.pageReadWrite                                    false     1024  avgt    3       5.491 ±   3.159  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                false     1024  avgt            0.452             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses              false     1024  avgt          624.331             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                    false     1024  avgt        14177.755             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses             false     1024  avgt            1.535             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                   false     1024  avgt        12939.783             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses              false     1024  avgt            5.304             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-loads                    false     1024  avgt        13063.681             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-load-misses                    false     1024  avgt            0.301             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-loads                          false     1024  avgt            0.896             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-store-misses                   false     1024  avgt            0.100             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-stores                         false     1024  avgt            0.517             #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                      false     1024  avgt            2.404             #/op
 CacheConflictBenchmark.pageReadWrite:branches                           false     1024  avgt          665.466             #/op
 CacheConflictBenchmark.pageReadWrite:cycles                             false     1024  avgt        17051.897             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                   false     1024  avgt            0.805             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-loads                         false     1024  avgt        15806.888             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                  false     1024  avgt            0.806             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-stores                        false     1024  avgt        13084.473             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                   false     1024  avgt            0.257             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-loads                         false     1024  avgt        37897.933             #/op
 CacheConflictBenchmark.pageReadWrite:instructions                       false     1024  avgt        37724.874             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-backend             false     1024  avgt          451.459             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend            false     1024  avgt         3129.847             #/op
 CacheConflictBenchmark.pageReadWrite                                    false     4096  avgt    3       5.723 ±   0.142  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                false     4096  avgt            0.478             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses              false     4096  avgt          628.475             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                    false     4096  avgt        14229.408             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses             false     4096  avgt            1.486             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                   false     4096  avgt        12980.482             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses              false     4096  avgt            4.884             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-loads                    false     4096  avgt        13085.431             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-load-misses                    false     4096  avgt            0.359             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-loads                          false     4096  avgt            0.947             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-store-misses                   false     4096  avgt            0.117             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-stores                         false     4096  avgt            1.193             #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                      false     4096  avgt            2.484             #/op
 CacheConflictBenchmark.pageReadWrite:branches                           false     4096  avgt          665.116             #/op
 CacheConflictBenchmark.pageReadWrite:cycles                             false     4096  avgt        17963.543             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                   false     4096  avgt            0.684             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-loads                         false     4096  avgt        17063.708             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                  false     4096  avgt            0.869             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-stores                        false     4096  avgt        13159.000             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                   false     4096  avgt            0.347             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-loads                         false     4096  avgt        37861.839             #/op
 CacheConflictBenchmark.pageReadWrite:instructions                       false     4096  avgt        37565.145             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-backend             false     4096  avgt          714.686             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend            false     4096  avgt         4023.069             #/op
 CacheConflictBenchmark.pageReadWrite                                     true       32  avgt    3      40.755 ± 102.041  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                 true       32  avgt            3.317             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses               true       32  avgt        10331.407             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                     true       32  avgt        13284.226             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses              true       32  avgt         9697.731             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                    true       32  avgt        12079.479             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses               true       32  avgt           51.511             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-loads                     true       32  avgt        13168.980             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-load-misses                     true       32  avgt            2.068             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-loads                           true       32  avgt           11.176             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-store-misses                    true       32  avgt            1.304             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-stores                          true       32  avgt            7.007             #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                       true       32  avgt           27.486             #/op
 CacheConflictBenchmark.pageReadWrite:branches                            true       32  avgt         1081.098             #/op
 CacheConflictBenchmark.pageReadWrite:cycles                              true       32  avgt       124278.371             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                    true       32  avgt            4.794             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-loads                          true       32  avgt        15428.016             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                   true       32  avgt            6.350             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-stores                         true       32  avgt        78058.056             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                    true       32  avgt            2.129             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-loads                          true       32  avgt        36904.953             #/op
 CacheConflictBenchmark.pageReadWrite:instructions                        true       32  avgt        37471.307             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-backend              true       32  avgt        94957.606             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend             true       32  avgt       110564.840             #/op
 CacheConflictBenchmark.pageReadWrite                                     true      128  avgt    3      47.268 ±  40.130  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                 true      128  avgt            4.318             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses               true      128  avgt        10493.376             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                     true      128  avgt        10948.842             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses              true      128  avgt         9815.178             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                    true      128  avgt        10629.676             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses               true      128  avgt           61.523             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-loads                     true      128  avgt        12695.474             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-load-misses                     true      128  avgt            3.780             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-loads                           true      128  avgt           32.491             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-store-misses                    true      128  avgt            0.498             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-stores                          true      128  avgt        19954.272             #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                       true      128  avgt           23.747             #/op
 CacheConflictBenchmark.pageReadWrite:branches                            true      128  avgt          999.335             #/op
 CacheConflictBenchmark.pageReadWrite:cycles                              true      128  avgt       146585.033             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                    true      128  avgt            4.507             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-loads                          true      128  avgt        14485.705             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                   true      128  avgt           11.094             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-stores                         true      128  avgt        81215.982             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                    true      128  avgt            1.948             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-loads                          true      128  avgt        34030.868             #/op
 CacheConflictBenchmark.pageReadWrite:instructions                        true      128  avgt        33947.116             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-backend              true      128  avgt       104877.802             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend             true      128  avgt       134219.948             #/op
 CacheConflictBenchmark.pageReadWrite                                     true     1024  avgt    3      46.751 ±  36.074  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                 true     1024  avgt            4.247             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses               true     1024  avgt        10599.101             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                     true     1024  avgt        11059.040             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses              true     1024  avgt         9860.432             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                    true     1024  avgt        10611.552             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses               true     1024  avgt           78.038             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-loads                     true     1024  avgt        12434.871             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-load-misses                     true     1024  avgt            1.576             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-loads                           true     1024  avgt           26.040             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-store-misses                    true     1024  avgt            1.427             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-stores                          true     1024  avgt        19999.357             #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                       true     1024  avgt           19.104             #/op
 CacheConflictBenchmark.pageReadWrite:branches                            true     1024  avgt         1046.752             #/op
 CacheConflictBenchmark.pageReadWrite:cycles                              true     1024  avgt       147528.395             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                    true     1024  avgt            9.013             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-loads                          true     1024  avgt        13053.454             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                   true     1024  avgt           40.535             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-stores                         true     1024  avgt        80674.669             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                    true     1024  avgt            2.978             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-loads                          true     1024  avgt        34560.075             #/op
 CacheConflictBenchmark.pageReadWrite:instructions                        true     1024  avgt        34740.232             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-backend              true     1024  avgt       108885.153             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend             true     1024  avgt       133687.335             #/op
 CacheConflictBenchmark.pageReadWrite                                     true     4096  avgt    3     115.789 ±  49.779  us/op
 CacheConflictBenchmark.pageReadWrite:CPI                                 true     4096  avgt            9.788             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-load-misses               true     4096  avgt        10744.778             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-loads                     true     4096  avgt        11478.363             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-store-misses              true     4096  avgt         9953.173             #/op
 CacheConflictBenchmark.pageReadWrite:L1-dcache-stores                    true     4096  avgt        11031.859             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-load-misses               true     4096  avgt          163.110             #/op
 CacheConflictBenchmark.pageReadWrite:L1-icache-loads                     true     4096  avgt        13504.146             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-load-misses                     true     4096  avgt           23.074             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-loads                           true     4096  avgt           46.267             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-store-misses                    true     4096  avgt         8751.583             #/op
 CacheConflictBenchmark.pageReadWrite:LLC-stores                          true     4096  avgt        20106.659             #/op
 CacheConflictBenchmark.pageReadWrite:branch-misses                       true     4096  avgt           32.815             #/op
 CacheConflictBenchmark.pageReadWrite:branches                            true     4096  avgt         1419.409             #/op
 CacheConflictBenchmark.pageReadWrite:cycles                              true     4096  avgt       360871.882             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-load-misses                    true     4096  avgt           27.957             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-loads                          true     4096  avgt        12731.561             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-store-misses                   true     4096  avgt          123.341             #/op
 CacheConflictBenchmark.pageReadWrite:dTLB-stores                         true     4096  avgt       168519.230             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-load-misses                    true     4096  avgt           11.575             #/op
 CacheConflictBenchmark.pageReadWrite:iTLB-loads                          true     4096  avgt        35706.140             #/op
 CacheConflictBenchmark.pageReadWrite:instructions                        true     4096  avgt        36868.697             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-backend              true     4096  avgt       297908.052             #/op
 CacheConflictBenchmark.pageReadWrite:stalled-cycles-frontend             true     4096  avgt       345119.173             #/op

 ```
