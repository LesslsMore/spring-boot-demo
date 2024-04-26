//package com.lesslsmore.bili.service.bili;
//
//import com.alibaba.fastjson.JSON;
//import com.lesslsmore.bili.entity.video.PageDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
//@Service
//public class MongodbService {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public String query(String name, int pageNo, int pageSize) {
//        Query query = new Query();
//        String regex = String.format("%s%s%s", "^.*", name, ".*$");
//        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        query.addCriteria(Criteria.where("part").regex(pattern));
//
//        int totalCount = (int) mongoTemplate.count(query, PageDO.class);
//
//        List<PageDO> pageDOList = mongoTemplate.find(query.skip((pageNo - 1) * pageSize).limit(pageSize), PageDO.class);
//
//        Map<String, Object> pageMap = new HashMap<>();
//        pageMap.put("list", pageDOList);
//        pageMap.put("totalCount",totalCount);
////        System.out.println(pageMap);
//        return JSON.toJSONString(pageMap);
//    }
//}
