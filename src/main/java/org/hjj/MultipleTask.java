package org.hjj;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

class MultipleTask extends BenchmarkTask {
    public MultipleTask(CountDownLatch latch, AtomicLong totalTaskTime, AtomicLong maxTaskTime) {
        super(latch, totalTaskTime, maxTaskTime);
    }

    @Override
    protected void executeTask() {
        int sum = 0;
        // Integer calculations
        // Simulate some computational workload
        for (int j = 0; j < 100000000; j++) {
            sum += j * j;
        }
    }

    @Override
    protected String getTaskName() {
        return "Multiple";
    }
}
