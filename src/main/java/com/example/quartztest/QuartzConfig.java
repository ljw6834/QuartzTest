package com.example.quartztest;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class QuartzConfig {
    JobKey jobKey = new JobKey("helloJobName", "group1");
    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(HelloJob.class)
                .withIdentity(jobKey)
                .withDescription("This is a hello job")
                .build();
    }

    @Bean
    public Trigger trigger(){
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("My trigger")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();
    }

    @Bean
    public Scheduler scheduler() throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getListenerManager()
                .addJobListener(new HelloJobListener(),KeyMatcher.keyEquals(jobKey));
        scheduler.start();
        scheduler.scheduleJob(jobDetail(), trigger());
        return scheduler;
    }
}
