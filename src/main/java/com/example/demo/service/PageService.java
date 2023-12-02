package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.PageDO;
import com.example.demo.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class PageService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PageMapper pageMapper;

    public String query(String param) {
        QueryWrapper<PageDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", param);
        List<PageDO> pageDOList = pageMapper.selectList(queryWrapper);
        System.out.println(pageDOList);
//        System.out.println(pageMap);
        return JSON.toJSONString(pageDOList);
    }
    public String query(String param, int cur, int size) {
        QueryWrapper<PageDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", param);
        Page<PageDO> page = new Page(cur,size);
        Page<PageDO> userPage = pageMapper.selectPage(page, queryWrapper);
        //返回对象得到分页所有数据
        return JSON.toJSONString(userPage);
    }

}
