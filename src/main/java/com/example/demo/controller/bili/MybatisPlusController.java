package com.example.demo.controller.bili;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.result.Result;
import com.example.demo.entity.bili.ItemDO;
import com.example.demo.service.bili.MybatisPlusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisPlusController {
    @Autowired
    private MybatisPlusService mybatisPlusService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisPlusController.class);
    //    http://127.0.0.1:8088/q?name=fs&p=1&s=10
    @GetMapping("/q")
    public Result query(@RequestParam(value = "name") String name,
                        @RequestParam int p,
                        @RequestParam int s) {
        LOGGER.error("enterprise instance not found by the scope id: {}", name);
        Page<ItemDO> userPage = mybatisPlusService.query(name, p, s);
        return Result.ok(userPage);
    }
}
