package com.fynd.sample.webhook;

import com.fynd.extension.middleware.EventHandler;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("sampleHandler")
public class SampleEventHandler implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(SampleEventHandler.class);

    @SneakyThrows
    @Override
    public void handle(String eventName, Object body, String companyId, String applicationId) {
        log.info("Event  Received : {} for companyId : {}", eventName, companyId);
        /**
         * code to handle webhook event here
         */
    }
}
