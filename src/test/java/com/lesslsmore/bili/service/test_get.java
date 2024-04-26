package com.lesslsmore.bili.service;

import com.lesslsmore.bili.service.user.SpaceService;
import com.lesslsmore.bili.service.video.InfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class test_get {
    @Autowired
    public SpaceService spaceService;
    @Autowired
    public InfoService infoService;

//    @Autowired
//    public ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    public ExecutorService executorService;

    @Test
    public void test_saveUserSpace() throws IOException, ExecutionException, InterruptedException {
        String mid = "302417610";
        Integer pn = 14;

        long startTime = System.currentTimeMillis();

        CompletableFuture<List<String>> futureBvids = CompletableFuture.supplyAsync(() -> {
            List<String> bvids = spaceService.saveUserSpace(mid, pn);
            return bvids;
        }, executorService);

        List<String> bvids = futureBvids.get();
        List<CompletableFuture<Integer>> allSize = new ArrayList<>();
        for (String bvid: bvids) {
            CompletableFuture<Integer> futureVideoInfoSize = CompletableFuture.supplyAsync(() -> {
                int videoInfoSize = infoService.saveVideoInfo(bvid);
                return videoInfoSize;
            }, executorService);
            allSize.add(futureVideoInfoSize);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(allSize.toArray(new CompletableFuture[allSize.size()]));
        allOf.get();
        int size = 0;
        for (CompletableFuture<Integer> futureVideoInfoSize: allSize) {
            Integer videoInfoSize = futureVideoInfoSize.get();
            size += videoInfoSize;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("接口调用共耗时:{}毫秒" + (endTime - startTime));
        System.out.println(size);
    }
}
