Benchmarks to measure effects of memory access patterns


### Build 
'''
./gradlew -Dorg.gradle.java.home=<jdk_path>  bench_jar

'''

The above gradle task should build JMH benchmark jar.

'''
java -jar mem-bench/build/libs/mem-bench-all.jar -wi 3 -i 5 -f 2

'''
