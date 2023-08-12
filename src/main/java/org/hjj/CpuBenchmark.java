package org.hjj;

import org.apache.commons.cli.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class CpuBenchmark {

    public static void main(String[] args) throws Exception {
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        Options options = new Options();
        options.addOption("threads", true, "Number of threads (default: " + availableProcessors + ")");
        options.addOption("prime_max", true, "Maximum prime number");
        options.addOption("task", true, "Task type: prime, multiple, sqrt, or quicksort (default: prime)");
        options.addOption("iterations", true, "Number of task iterations");
        options.addOption("array_size", true, "Array size for quicksort task (default: 10000)");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        int THREADS = cmd.hasOption("threads") ? Integer.parseInt(cmd.getOptionValue("threads")) : availableProcessors;
        int PRIME_MAX = cmd.hasOption("prime_max") ? Integer.parseInt(cmd.getOptionValue("prime_max")) : 200000;
        String TASK = cmd.hasOption("task") ? cmd.getOptionValue("task") : "prime";
        int ITERATIONS = cmd.hasOption("iterations") ? Integer.parseInt(cmd.getOptionValue("iterations")) : 10;
        int ARRAY_SIZE = cmd.hasOption("array_size") ? Integer.parseInt(cmd.getOptionValue("array_size")) : 2000000;

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        CountDownLatch latch = new CountDownLatch(THREADS * ITERATIONS);

        AtomicLong totalTaskTime = new AtomicLong(0);
        AtomicLong maxTaskTime = new AtomicLong(Long.MIN_VALUE);

        BenchmarkTask task = null;

        switch (TASK) {
            case "prime":
                task = new PrimeTask(PRIME_MAX, latch, totalTaskTime, maxTaskTime);
                break;
            case "multiple":
                task = new MultipleTask(latch, totalTaskTime, maxTaskTime);
                break;
            case "sqrt":
                task = new SqrtTask(latch, totalTaskTime, maxTaskTime);
                break;
            case "quicksort":
                task = new QuicksortTask(ARRAY_SIZE, latch, totalTaskTime, maxTaskTime);
                break;
            default:
                throw new Exception("unknown task type:" + TASK);
        }

        for (int i = 0; i < THREADS * ITERATIONS; i++) {
            executor.submit(task);
        }

        latch.await();
        executor.shutdown();

        long averageTaskTime = totalTaskTime.get() / ((long) THREADS * ITERATIONS);
        System.out.println("Average task time: " + averageTaskTime + " ms");
        System.out.println("Max task time: " + maxTaskTime.get() + " ms");
    }
}