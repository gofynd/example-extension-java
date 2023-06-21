package com.fynd.sample.controller;


import com.fynd.extension.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class WebhookController {

    @Autowired
    WebhookService webhookService;

    @PostMapping(path = "/event/webhook")
    public ResponseEntity<Object> receiveWebhookEvents(HttpServletRequest httpServletRequest) {
        webhookService.processWebhook(httpServletRequest);
        return new ResponseEntity<>(Collections.singletonMap("success", true), HttpStatus.OK);
    }
}
