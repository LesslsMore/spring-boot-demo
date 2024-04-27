package com.lesslsmore.bili.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lesslsmore.bili.config.KafkaProducer;
import com.lesslsmore.bili.entity.user.SpaceResp;
import com.lesslsmore.bili.entity.user.SpaceVlist;
import com.lesslsmore.bili.service.SpaceVlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static com.lesslsmore.bili.common.API.getUserSpace;

@Service
public class SpaceService {
    @Autowired
    public SpaceVlistService spaceVlistService;
    @Autowired
    public ExecutorService executorService;
    @Autowired
    private KafkaProducer kafkaProducer;

    public List<String> saveUserSpace(String mid, Integer pn) {
        //        log.info("setSpace", mid, pn);

        SpaceResp resp = getUserSpace(mid, pn);
        List<SpaceVlist> vlist = resp.getData().getList().getVlist();
        boolean saved = spaceVlistService.saveOrUpdateBatch(vlist);
        // 放数据库异步
        // 消息队列？ 生产者 消费者
        List<String> bvids = vlist.stream().map(spaceVlist -> spaceVlist.getBvid()).collect(Collectors.toList());
        kafkaProducer.send("bvids", bvids);
        return bvids;
    }

    public List<String> saveUserSpaces(String mid, Integer pn) throws ExecutionException, InterruptedException {
        List<String> bvidss = new ArrayList<>();

        List<CompletableFuture<List<String>>> futures = new ArrayList<>();
        for (int i = 1; i <= pn; i++) {
            int finalI = i;
            CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(()-> saveUserSpace(mid, finalI), executorService);
            futures.add(future);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        allOf.get();
        for (CompletableFuture<List<String>> future : futures) {
            List<String> bvids = future.get();
            bvidss.addAll(bvids);
        }
        return bvidss;
    }

    public List<String> getBvids(String mid) {
        QueryWrapper<SpaceVlist> wrapper = new QueryWrapper<>();
        wrapper.eq("mid", mid);
        List<SpaceVlist> list = spaceVlistService.list(wrapper);
        List<String> bvids = list.stream().map(spaceVlist -> spaceVlist.getBvid()).collect(Collectors.toList());
        return bvids;
    }

    public List<String> getBvids() {
        List<SpaceVlist> list = spaceVlistService.list();
        List<String> bvids = list.stream().map(spaceVlist -> spaceVlist.getBvid()).collect(Collectors.toList());
        return bvids;
    }
}
