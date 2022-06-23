package com.fynd.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class SampleCronExecutor {

    @Autowired
    Environment env;

    @Autowired
    SampleTaskExecutorService sampleTaskExecutorService;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        //Will be available through FIK Consumers / Cron pods
        String jobType = env.getProperty("CRON_JOB");
        if (jobType != null) {
            //cronJob1 should exactly match with the name provided in FIK file
            if (jobType.equalsIgnoreCase("cronJob1")) {
                sampleTaskExecutorService.sampleTask();
            }
            System.exit(0);
        }
    }
}
