package com.lesslsmore.bili.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lesslsmore.bili.common.result.Result;
import com.lesslsmore.bili.controller.MybatisPlusService;
import com.lesslsmore.bili.entity.ListReq;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.user.SpaceVlist;
import com.lesslsmore.bili.entity.video.InfoResp;
import com.lesslsmore.bili.mapper.SpaceVlistMapper;
import com.lesslsmore.bili.service.InfoPagesExtService;
import com.lesslsmore.bili.service.SpaceVlistService;
import com.lesslsmore.bili.service.user.SpaceService;
import com.lesslsmore.bili.service.video.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.lesslsmore.bili.common.API.*;
import static com.lesslsmore.bili.common.Utils.resp2infoPagesExts;

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
        List<InfoPagesExt> videoInfos = infoService.getVideoInfos(param);
        return Result.ok(videoInfos.size());
    }
}
