package com.example.demo.controller.bili;

import com.example.demo.service.bili.MongodbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MongodbController {
    @Autowired
    MongodbService mongodbService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MongodbController.class);
    //    http://127.0.0.1:8088/q?name=fs&p=1&s=10
    @GetMapping("/query")
    public String query(@RequestParam(value = "name") String name,
                        @RequestParam int p,
                        @RequestParam int s) {
        LOGGER.error("enterprise instance not found by the scope id: {}", name);
        String query = mongodbService.query(name, p, s);
        return query;
    }
}
