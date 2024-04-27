package com.lesslsmore.bili;

import com.alibaba.fastjson.JSON;
import com.lesslsmore.bili.config.KafkaProducer;
import com.lesslsmore.bili.entity.user.SpaceReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class KafkaTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaProducer kafkaProducer;

//    @Test
//    public void testKafka() {
//        kafkaTemplate.send("test", "302417610");
//    }
//
//    @Test
//    public void testKafka_mid() {
//        kafkaTemplate.send("mid", "302417610");
//    }

    @Test
    public void testKafka_bvid() {
        String jsonString = JSON.toJSONString(Arrays.asList("BV1a4411B7V9", "BV1ad4y1y7LU"));
        kafkaTemplate.send("bvid", jsonString);
    }

    @Test
    public void testKafka_prod() {
        kafkaProducer.send("bvid", Arrays.asList("BV1a4411B7V9", "BV1ad4y1y7LU"));
    }

    @Test
    public void testKafka_mid() {
        // let mid = '37974444'
        // let pn = 17
//        kafkaProducer.send("mid",new SpaceReq("302417610", 14));
        kafkaProducer.send("mid",new SpaceReq("37974444", 17));
    }

}