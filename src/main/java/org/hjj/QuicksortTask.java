package org.hjj;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

class QuicksortTask extends BenchmarkTask {
    private int arraySize;

    public QuicksortTask(int arraySize, CountDownLatch latch, AtomicLong totalTaskTime, AtomicLong maxTaskTime) {
        super(latch, totalTaskTime, maxTaskTime);
        this.arraySize = arraySize;
    }

    @Override
    protected void executeTask() {
        int[] arr = generateRandomArray(arraySize);
        quickSort(arr, 0, arr.length - 1);
    }

    @Override
    protected String getTaskName() {
        return "Quicksort";
    }

    // Helper method for Quicksort algorithm
    protected void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    protected int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
