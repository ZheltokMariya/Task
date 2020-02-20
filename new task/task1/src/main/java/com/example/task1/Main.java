package com.example.task1;
import com.example.task1.database.WorkWithDatabase;
import com.example.task1.executor.TaskExecutorConfig;
import com.example.task1.executor.TaskExecutorForWritingToDatabase;
import com.example.task1.file.WorkWithFile;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException{

        WorkWithDatabase.createTable();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество потоков: ");
        int numberOfThreads = scanner.nextInt();
        System.out.println("Введите размер batch: ");
        int batchSize = scanner.nextInt();
        scanner.close();

        int latchSize;
        int lengthOfArray = WorkWithFile.readFromFile().split(",").length;

        if (lengthOfArray % WorkWithDatabase.NUMBER_OF_COLUMNS == 0){
            latchSize = lengthOfArray / (batchSize*2);
        }
        else {
            latchSize = (lengthOfArray / (batchSize*2)) + 1;
        }

        CountDownLatch latch = new CountDownLatch(latchSize);
        long start = System.currentTimeMillis();

        TaskExecutorForWritingToDatabase taskExecutor = new TaskExecutorForWritingToDatabase(numberOfThreads);
        taskExecutor.writingToDatabase(WorkWithFile.readFromFile().split(","), batchSize, latch);

        latch.await();

        long stop = System.currentTimeMillis();

        System.out.println("Время выполнения: ");
        System.out.println( stop - start);
    }
}
