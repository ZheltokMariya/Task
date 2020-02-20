package com.example.task1.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfig {

    private final static int CORE_POOL_SIZE = 10;
    private final static int MAX_POOL_SIZE = 100;

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
       ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
       pool.setCorePoolSize(CORE_POOL_SIZE);
       pool.setMaxPoolSize(MAX_POOL_SIZE);
       pool.setWaitForTasksToCompleteOnShutdown(true);
       return pool;
    }
}
