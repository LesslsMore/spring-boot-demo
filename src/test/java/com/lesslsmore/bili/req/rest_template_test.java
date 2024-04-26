package com.lesslsmore.bili.req;

import com.lesslsmore.bili.common.WBI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class rest_template_test {
    @Autowired
    RestTemplate restTemplate;
    @Test
    public void test_rest() {
        String bvid = "BV1dS4y1y7vd";
        String url = String.format("https://api.bilibili.com/x/web-interface/view?bvid=%s", bvid);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String object = restTemplate.getForObject(url, String.class);
        System.out.println(entity.getBody());
        System.out.println(object);
//        Data data = Utils.str2data(object);
    }
    @Test
    public void test_get_bvids() {
        Map<String, Object> params = new HashMap<>();
        params.put("mid", 302417610);
        params.put("pn", 3);
        String finalParam = WBI.get_wbi_sign(params);
        String url = "https://api.bilibili.com/x/space/wbi/arc/search?" + finalParam;
        System.out.println(url);
//        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String object = restTemplate.getForObject(url, String.class);
//        System.out.println(entity.getBody());
        System.out.println(object);
//        Data data = str2data(object);
//        List<PageDO> pageDOList = data2page(data);
//        System.out.println(pageDOList);
    }
}
