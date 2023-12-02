package com.example.demo.controller;

import com.example.demo.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MpController {
    @Autowired
    private PageService pageService;
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    //    http://127.0.0.1:8088/q?name=fs&p=1&s=10
    @GetMapping("/q")
    public String query(@RequestParam(value = "name") String name,
                        @RequestParam int p,
                        @RequestParam int s) {
        LOGGER.error("enterprise instance not found by the scope id: {}", name);
        String query = pageService.query(name, p, s);
        return query;
    }
}
