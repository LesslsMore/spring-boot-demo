package com.lesslsmore.bili.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lesslsmore.bili.common.result.Result;
import com.lesslsmore.bili.entity.ListReq;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.mapper.SpaceVlistMapper;
import com.lesslsmore.bili.service.SpaceVlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MybatisPlusController {
    @Autowired
    private MybatisPlusService mybatisPlusService;
    @Autowired
    private SpaceVlistMapper spaceVlistMapper;
    @Autowired
    private SpaceVlistService spaceVlistService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisPlusController.class);
    // http://localhost:8088/list/1/10
    @PostMapping("/list/{page}/{size}")
    public Result list(@PathVariable int page,
                       @PathVariable int size,
                       @RequestBody ListReq req) {
//        LOGGER.error("get name", name);
        Page<InfoPagesExt> pageSize = new Page(page ,size);
        Page<InfoPagesExt> userPage = mybatisPlusService.list(pageSize, req);
        return Result.ok(userPage);
    }

//    @PostMapping("/bvid")
//    public Result addBvid(@RequestParam String bvid) {
//        LOGGER.error("add bvid", bvid);
//        List<PageDO> pageDOList = mybatisPlusService.addBvid(bvid);
//        return Result.ok(pageDOList);
//    }
}
