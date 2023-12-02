package com.example.demo;

import com.example.demo.entity.PageDO;
import com.example.demo.entity.User;
import com.example.demo.service.QueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.example.demo.Utils.import_json_bvid;

@SpringBootTest
public class mongo_template_test {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private QueryService queryService;

    @Test
    public void create() {
        User user = new User();
        user.setAge(20);
        user.setName("test");
        user.setEmail("test@test.com");
        User user1 = mongoTemplate.insert(user);
        System.out.println(user1);
    }

    @Test
    public void insert() throws IOException {
        String filePath = "D:\\T\\Documents\\VSCode\\js\\bili\\up\\37974444\\bvid\\BV1a34y167AZ.json";
//        new Path(filePath);
        Path path = Paths.get(filePath);
        List<PageDO> pages = import_json_bvid(path);
        System.out.println(pages);
        pages.forEach(pageDO -> {
            PageDO pageDO1 = mongoTemplate.insert(pageDO);
            System.out.println(pageDO1);
        });
    }

    @Test
    public void query() throws IOException {
        String name = "node";
        int pageNo = 1;
        int pageSize = 10;
        String query = queryService.query(name, pageNo, pageSize);
        System.out.println(query.toString());
    }
//    db.User.find()
    @Test
    public void findAll() {
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }
}