package com.example.quartztest;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

//this class is not very helpful in getting whether job is complete
public class HelloJobListener implements JobListener {
    @Override
    public String getName() {
        return "hello job";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        String done = (String) jobExecutionContext.getResult();
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        if(done != null && done.equalsIgnoreCase("done")){
            System.out.println( jobName +" "+ done);
        }

        if (e != null && !e.getMessage().equals("")) {
            System.out.println("Exception thrown by: " + jobName
                    + " Exception: " + e.getMessage());
        }
    }
}
