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

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        String jobType = env.getProperty("CRON_JOB");
        if (jobType != null) {
            if (jobType.equalsIgnoreCase("cronJob1-name")) {
                /**
                 * code here for cronJob1
                 */
            } else if (jobType.equalsIgnoreCase("cronJob2-name")) {
                /**
                 * code here for cronJob2
                 */
            }
            System.exit(0);
        }
    }

}
