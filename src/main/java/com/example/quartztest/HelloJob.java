package com.example.quartztest;

import org.quartz.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@DisallowConcurrentExecution
public class HelloJob implements Job {
    private final static String DONE = "done";
    private final static String START = "start";
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        System.out.println(Thread.currentThread().getName() + " : "+ formatDateTime);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        jobExecutionContext.setResult(DONE);
    }
}
