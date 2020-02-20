package com.example.task1.executor;

import com.example.task1.database.DatabaseWritingTask;
import com.example.task1.database.WorkWithDatabase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;

public class TaskExecutorForWritingToDatabase {

    private ThreadPoolTaskExecutor taskExecutor;

    public TaskExecutorForWritingToDatabase(int corePoolSize) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        this.taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
        taskExecutor.setCorePoolSize(corePoolSize);
    }

    public void writingToDatabase(String[] array, int batchSize, CountDownLatch latch){
        for (int i=0; i < array.length; i+=batchSize*WorkWithDatabase.NUMBER_OF_COLUMNS){
            taskExecutor.execute(new DatabaseWritingTask(array, i, batchSize, latch));
        }
        taskExecutor.shutdown();
    }
}
