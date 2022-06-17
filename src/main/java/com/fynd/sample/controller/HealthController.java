package com.fynd.sample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping(value = "/_healthz", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> health() {
        return new ResponseEntity<>(Collections.singletonMap("status", "up"), HttpStatus.OK);
    }

    @GetMapping(value = "/_readyz", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> ready() {
        return new ResponseEntity<>(Collections.singletonMap("status", "ready"), HttpStatus.OK);
    }

}
