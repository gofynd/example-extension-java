package com.fynd.controller;

import com.fynd.representation.response.CreditSystemResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(value = "creditsystem")
public class HealthController {

    @GetMapping(value = "health", produces = "application/json")
    @ResponseBody
    public CreditSystemResponseEntity  health() {
        return CreditSystemResponseEntity.buildSuccessResponse(Collections.singletonMap("status", "up"));
    }
}
