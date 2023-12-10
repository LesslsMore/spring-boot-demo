package com.example.demo.service.bili;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.bili.ItemDO;
import com.example.demo.mapper.bili.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MybatisPlusService {
    @Autowired
    private ItemMapper itemMapper;

    public String query(String param) {
        QueryWrapper<ItemDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", param);
        List<ItemDO> pageDOList = itemMapper.selectList(queryWrapper);
        System.out.println(pageDOList);
//        System.out.println(pageMap);
        return JSON.toJSONString(pageDOList);
    }
    public Page<ItemDO> query(String param, int cur, int size) {
        QueryWrapper<ItemDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", param);
        Page<ItemDO> page = new Page(cur,size);
        Page<ItemDO> userPage = itemMapper.selectPage(page, queryWrapper);
        //返回对象得到分页所有数据
        return userPage;
    }

}
