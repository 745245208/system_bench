# CPU Benchmark Tool
这是一个用 Java 编写的多线程 CPU 性能测试工具，可以用于测试不同类型的 CPU 计算任务的性能。工具支持多线程执行，可以测试质数分解、整数计算、浮点数计算以及快速排序任务的性能。

## 用法
使用命令行参数来自定义测试配置。以下是可用的参数选项：

```
-threads <number>：指定线程数，测试将在指定数量的线程中执行（默认为可用 CPU 核心数）。
-prime_max <number>：指定质数分解任务中的最大质数（默认为 200000）。
-task <task_type>：指定测试任务类型，可选的类型包括 "prime"（质数分解）、"multiple"（整数计算）、"sqrt"（浮点数计算）和 "quicksort"（快速排序，默认为 "prime"）。
-iterations <number>：指定每种任务执行的次数（默认为 10）。
-array_size <size>：指定快速排序任务中的数组大小（默认为 2000000）。
```
## 示例
以下是一些使用示例：

测试质数分解任务，使用默认线程数和参数：
```bash
java -jar CpuBenchmark.jar -task prime
```

测试整数计算任务，指定线程数为 8，执行 20 次任务：
```bash
java -jar CpuBenchmark.jar -task multiple -threads 8 -iterations 20
```

测试浮点数计算任务，指定最大质数为 100000，使用默认线程数和参数：
```bash
java -jar CpuBenchmark.jar -task sqrt -prime_max 100000
```

测试快速排序任务，指定数组大小为 500000，执行 5 次任务：
```bash
java -jar CpuBenchmark.jar -task quicksort -array_size 500000 -iterations 5
```

## 注意事项
该工具用于模拟不同类型的 CPU 计算任务的性能，不考虑实际硬件差异。
快速排序任务将在指定数组大小的随机整数数组上执行快速排序算法。
在执行测试时，请根据系统配置和需求适当调整线程数、任务次数和参数。
