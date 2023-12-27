package com.lesslsmore.bili.controller.bili;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lesslsmore.bili.common.result.Result;
import com.lesslsmore.bili.entity.ListReq;
import com.lesslsmore.bili.entity.bili.PageDO;
import com.lesslsmore.bili.service.bili.MybatisPlusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MybatisPlusController {
    @Autowired
    private MybatisPlusService mybatisPlusService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisPlusController.class);
    //    http://127.0.0.1:8088/q?name=fs&p=1&s=10
    @GetMapping("/list/{page}/{size}")
    public Result list(@PathVariable int page,
                       @PathVariable int size,
                       ListReq req) {
//        LOGGER.error("get name", name);
        Page<PageDO> pageSize = new Page(page ,size);
        Page<PageDO> userPage = mybatisPlusService.list(pageSize, req);
        return Result.ok(userPage);
    }

    @PostMapping("/bvid")
    public Result addBvid(@RequestParam String bvid) {
        LOGGER.error("add bvid", bvid);
        List<PageDO> pageDOList = mybatisPlusService.addBvid(bvid);
        return Result.ok(pageDOList);
    }

    @PostMapping("/mid")
    public Result addMid(@RequestParam String mid) {
        LOGGER.error("add mid", mid);
        List<PageDO> pageDOList = mybatisPlusService.addMid(mid);
        return Result.ok(pageDOList);
    }
}
