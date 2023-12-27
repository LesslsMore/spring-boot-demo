package com.lesslsmore.bili.service.bili;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lesslsmore.bili.entity.ListReq;
import com.lesslsmore.bili.entity.bili.Data;
import com.lesslsmore.bili.entity.bili.PageDO;
import com.lesslsmore.bili.mapper.bili.PageMapper;
import com.lesslsmore.bili.common.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MybatisPlusService {
    @Autowired
    private PageMapper pageMapper;

    @Autowired
    RestTemplate restTemplate;

    public String query(String param) {
        QueryWrapper<PageDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", param);
        List<PageDO> pageDOList = pageMapper.selectList(queryWrapper);
        System.out.println(pageDOList);
//        System.out.println(pageMap);
        return JSON.toJSONString(pageDOList);
    }
    public Page<PageDO> list(Page page, ListReq req) {
        QueryWrapper<PageDO> queryWrapper = new QueryWrapper<>();
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
        Page<PageDO> userPage = pageMapper.selectPage(page, queryWrapper);
        //返回对象得到分页所有数据
        return userPage;
    }

    public List<PageDO> addBvid(String bvid) {
        String url = String.format("https://api.bilibili.com/x/web-interface/view?bvid=%s", bvid);
        String object = restTemplate.getForObject(url, String.class);
        Data data = Utils.str2data(object);
        List<PageDO> pageDOList = Utils.data2page(data);
        pageDOList.forEach(pageDO -> {
            int insert = pageMapper.insert(pageDO);
        });
        return pageDOList;
    }

    public List<PageDO> addMid(String mid) {
        String url = String.format("https://api.bilibili.com/x/web-interface/view?bvid=%s", mid);
        String object = restTemplate.getForObject(url, String.class);
        Data data = Utils.str2data(object);
        List<PageDO> pageDOList = Utils.data2page(data);
        pageDOList.forEach(pageDO -> {
            int insert = pageMapper.insert(pageDO);
        });
        return pageDOList;
    }
}
