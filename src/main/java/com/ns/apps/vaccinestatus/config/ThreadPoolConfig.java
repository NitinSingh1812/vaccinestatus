package com.ns.apps.vaccinestatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    @Bean("vaccineStatusSchedulars")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(2);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "VaccineStatus-Schedulars");
        return threadPoolTaskScheduler;
    }

    @Bean("threadExecutorPool")
    public ExecutorService threadExecutor() {
        return Executors.newFixedThreadPool(5);
    }

}
