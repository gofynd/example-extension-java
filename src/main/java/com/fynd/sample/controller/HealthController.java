package com.fynd.sample.controller;

import com.fynd.extension.model.Extension;
import com.fynd.extension.storage.RedisStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HealthController {

    @Autowired
    Extension extension;

    @GetMapping(value = "/_healthz", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> health() {
        return new ResponseEntity<>(Collections.singletonMap("status", "up"), HttpStatus.OK);
    }

    @GetMapping(value = "/_readyz", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map<String, String>> ready() {


        String set = extension.getStorage()
            .set("d0b5211b-58c3-4561-8685-da0a3f741518", "{\"id\":1}");
        System.out.println(set);

        String setex = extension.getStorage()
            .setex("d0b5211b-58c3-4561-8685-da0a3f741518", 600, "{\"id\":1}");
        System.out.println(setex);

        String s = extension.getStorage()
            .get("d0b5211b-58c3-4561-8685-da0a3f741518");
        System.out.println(s);

        Object del = extension.getStorage().del("d0b5211b-58c3-4561-8685-da0a3f741518");
        System.out.println(del);

        Object hset = extension.getStorage()
            .hset("d0b5211b-58c3-4561-8685-da0a3f741519", "hashKey1", "hashVal1");

        System.out.println(hset);

        String hashVal1 = extension.getStorage()
            .hget("d0b5211b-58c3-4561-8685-da0a3f741519", "hashKey1");

        System.out.println(hashVal1);

        Map<String, Object> hgetall = extension.getStorage()
            .hgetall("d0b5211b-58c3-4561-8685-da0a3f741519");

        return new ResponseEntity<>(Collections.singletonMap("status", "ready"), HttpStatus.OK);
    }

}
