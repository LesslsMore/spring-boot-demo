package com.lesslsmore.bili.config;

import com.alibaba.fastjson.JSON;
import com.lesslsmore.bili.entity.user.SpaceReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(String topic, List<String> data) {
        String jsonString = JSON.toJSONString(data);
        kafkaTemplate.send(topic, jsonString);
    }
    public void send(String topic, SpaceReq req) {
        String jsonString = JSON.toJSONString(req);
        kafkaTemplate.send(topic, jsonString);
    }
}