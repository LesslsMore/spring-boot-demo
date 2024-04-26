package com.lesslsmore.bili.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lesslsmore.bili.entity.ListReq;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.mapper.InfoPagesExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MybatisPlusService {
    @Autowired
    private InfoPagesExtMapper infoPagesExtMapper;

    @Autowired
    RestTemplate restTemplate;

    public String query(String param) {
        QueryWrapper<InfoPagesExt> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", param);
        List<InfoPagesExt> infoPagesExtList = infoPagesExtMapper.selectList(queryWrapper);
        System.out.println(infoPagesExtList);
//        System.out.println(pageMap);
        return JSON.toJSONString(infoPagesExtList);
    }
    public Page<InfoPagesExt> list(Page page, ListReq req) {
        QueryWrapper<InfoPagesExt> queryWrapper = new QueryWrapper<>();
        if (!req.getName().equals("")) {
            queryWrapper.eq("name", req.getName());
        }
        if (!req.getMid().equals("")) {
            queryWrapper.eq("mid", req.getMid());
        }
        if (!req.getBvid().equals("")) {
            queryWrapper.eq("bvid", req.getBvid());
        }
        if (!req.getPart().equals("")) {
            queryWrapper.like("part", req.getPart());
        }
        Page<InfoPagesExt> userPage = infoPagesExtMapper.selectPage(page, queryWrapper);
        //返回对象得到分页所有数据
        return userPage;
    }
}
