package com.lesslsmore.bili.service.video;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.video.InfoResp;
import com.lesslsmore.bili.service.InfoPagesExtService;
import com.lesslsmore.bili.service.user.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static com.lesslsmore.bili.common.API.getVideoInfo;
import static com.lesslsmore.bili.common.Utils.resp2infoPagesExts;

@Service
public class InfoService {
    @Autowired
    public InfoPagesExtService infoPagesExtService;
    @Autowired
    public SpaceService spaceService;
    @Autowired
    public ExecutorService executorService;


    public int saveVideoInfo(String bvid) {
        InfoResp resp = getVideoInfo(bvid);
        List<InfoPagesExt> infoPagesExts = resp2infoPagesExts(resp);
        boolean saved = infoPagesExtService.saveOrUpdateBatch(infoPagesExts);
        return infoPagesExts.size();
    }

    public int saveVideoInfos(List<String> bvids) throws ExecutionException, InterruptedException {
        List<CompletableFuture<Integer>> allSize = new ArrayList<>();
        for (String bvid: bvids) {
            CompletableFuture<Integer> futureVideoInfoSize = CompletableFuture.supplyAsync(() -> saveVideoInfo(bvid), executorService);
            allSize.add(futureVideoInfoSize);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(allSize.toArray(new CompletableFuture[allSize.size()]));
        allOf.get();
        int size = 0;
        for (CompletableFuture<Integer> futureVideoInfoSize: allSize) {
            Integer videoInfoSize = futureVideoInfoSize.get();
            size += videoInfoSize;
        }
        return size;
    }

    public List<InfoPagesExt> getVideoInfos(String param){
        QueryWrapper<InfoPagesExt> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", param);
        List<InfoPagesExt> list = infoPagesExtService.list(queryWrapper);
        return list;
    }

    public List<InfoPagesExt> getVideoInfos(){
        List<InfoPagesExt> list = infoPagesExtService.list();
        return list;
    }
}
