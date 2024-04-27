package com.lesslsmore.bili.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lesslsmore.bili.entity.user.SpaceReq;
import com.lesslsmore.bili.service.user.SpaceService;
import com.lesslsmore.bili.service.video.InfoService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaConsumer {
    @Autowired
    public InfoService infoService;
    @Autowired
    public SpaceService spaceService;

    @KafkaListener(topics = {"mid"})
    public void handleMid(ConsumerRecord record) throws ExecutionException, InterruptedException {
        SpaceReq req = JSON.parseObject((String) record.value(), SpaceReq.class);
        System.out.println(req);

        long startTime = System.currentTimeMillis();
        List<String> bvids = spaceService.saveUserSpaces(req.getMid(), req.getPn());
        System.out.println(bvids);
        long endTime = System.currentTimeMillis();
        System.out.println("接口调用共耗时:{}毫秒" + (endTime - startTime));
    }

    @KafkaListener(topics = {"bvids"})
    public void handleBvid(ConsumerRecord record) throws ExecutionException, InterruptedException {
        List<String> bvids = JSON.parseObject((String) record.value(), new TypeReference<List<String>>() {
        });
        int size = infoService.saveVideoInfos(bvids);
        System.out.printf("bvids parts num: %d\n", size);
    }

}
