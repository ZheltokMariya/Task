package com.example.task1.database;

import java.util.concurrent.CountDownLatch;

public class DatabaseWritingTask implements Runnable{
    private String[] array;
    private int start;
    private int batchSize;
    private CountDownLatch latch;

    public DatabaseWritingTask(String[] array, int start, int batchSize, CountDownLatch latch){
        this.array = array;
        this.start = start;
        this.batchSize = batchSize;
        this.latch = latch;
    }

    public void run(){
        WorkWithDatabase.addRowsToDatabase(array, start, batchSize);
        latch.countDown();
    }
}
