package org.hjj;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

class PrimeTask extends BenchmarkTask {
    private int primeMax;

    public PrimeTask(int primeMax, CountDownLatch latch, AtomicLong totalTaskTime, AtomicLong maxTaskTime) {
        super(latch, totalTaskTime, maxTaskTime);
        this.primeMax = primeMax;
    }

    @Override
    protected void executeTask() {
        int sum = 0;
        // Prime number factorization
        // Simulate some computational workload
        for (int num = 2; num <= primeMax; num++) {
            boolean isPrime = true;
            for (int j = 2; j <= num / 2; j++) {
                if (num % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                // Process prime number
                sum += num;
            }
        }
    }

    @Override
    protected String getTaskName() {
        return "Prime";
    }
}
