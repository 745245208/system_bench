package org.hjj;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

class SqrtTask extends BenchmarkTask {
    public SqrtTask(CountDownLatch latch, AtomicLong totalTaskTime, AtomicLong maxTaskTime) {
        super(latch, totalTaskTime, maxTaskTime);
    }

    @Override
    protected void executeTask() {
        double sum = 0;
        // Floating point calculations
        // Simulate some computational workload
        for (int j = 0; j < 100000000; j++) {
            sum += Math.sqrt(j);
        }
    }

    @Override
    protected String getTaskName() {
        return "Sqrt";
    }
}
