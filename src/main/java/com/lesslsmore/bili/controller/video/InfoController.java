package com.lesslsmore.bili.controller.video;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lesslsmore.bili.common.result.Result;
import com.lesslsmore.bili.entity.ListReq;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.service.user.SpaceService;
import com.lesslsmore.bili.service.video.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

//@Slf4j
@RequestMapping("/video")
@RestController
public class InfoController {
    @Autowired
    public InfoService infoService;
    @Autowired
    public SpaceService spaceService;

    @PostMapping("/info")
    public Result info(@RequestParam String bvid) {
        int size = infoService.saveVideoInfo(bvid);
        return Result.ok(size);
    }

    @PostMapping("/infos")
    public Result infos() throws ExecutionException, InterruptedException {
        List<String> bvids = spaceService.getBvids();
        int size = infoService.saveVideoInfos(bvids);
        return Result.ok(size);
    }

    @GetMapping("/infos")
    public Result getInfos() {
        String param = "js";
        Integer pageNum = 1;
        Integer pageSize = 10;
        com.github.pagehelper.Page<InfoPagesExt> infos = infoService.getVideoInfos(param, pageNum, pageSize);
        return Result.ok(infos.getResult());
    }

    @PostMapping("/list/{page}/{size}")
    public Result list(@PathVariable int page,
                       @PathVariable int size,
                       @RequestBody ListReq req) {
//        LOGGER.error("get name", name);
        Page pageSize = new Page(page, size);
        Page userPage = infoService.list(pageSize, req);
//        itemCache.get(req, key ->  mybatisPlusService.list(pageSize, req));
        return Result.ok(userPage);
    }
}
