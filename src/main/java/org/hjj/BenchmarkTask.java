package org.hjj;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

abstract class BenchmarkTask implements Runnable {
    protected CountDownLatch latch;
    protected AtomicLong totalTaskTime;
    protected AtomicLong maxTaskTime;

    public BenchmarkTask(CountDownLatch latch, AtomicLong totalTaskTime, AtomicLong maxTaskTime) {
        this.latch = latch;
        this.totalTaskTime = totalTaskTime;
        this.maxTaskTime = maxTaskTime;
    }

    @Override
    public void run() {
        long taskStartTime = System.currentTimeMillis();
        executeTask();
        long taskEndTime = System.currentTimeMillis();
        long taskTime = taskEndTime - taskStartTime;
        totalTaskTime.addAndGet(taskTime);
        maxTaskTime.updateAndGet(max -> Math.max(max, taskTime));
        System.out.println(getTaskName() + " task completed in " + taskTime + " ms");
        latch.countDown();
    }

    protected abstract void executeTask();

    protected abstract String getTaskName();

    // Helper method to generate a random integer array
    protected int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random(42); // Fixed seed
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

}
