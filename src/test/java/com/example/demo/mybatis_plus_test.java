package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.PageDO;
import com.example.demo.mapper.PageMapper;
import com.example.demo.service.PageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.Utils.import_json_bvid;

@SpringBootTest
public class mybatis_plus_test {
    @Autowired
    private PageMapper pageMapper;
    @Autowired
    private PageService pageService;
    @Test
    public void testSelectPage() {
        QueryWrapper<PageDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", "npm");
        Page<PageDO> page = new Page(1,10);
        Page<PageDO> userPage = pageMapper.selectPage(page, queryWrapper);
        //返回对象得到分页所有数据
        long pages = userPage.getPages(); //总页数
        long current = userPage.getCurrent(); //当前页
        List<PageDO> records = userPage.getRecords(); //查询数据集合
        long total = userPage.getTotal(); //总记录数
        boolean hasNext = userPage.hasNext();  //下一页
        boolean hasPrevious = userPage.hasPrevious(); //上一页

        System.out.println(pages);
        System.out.println(current);
        System.out.println(records);
        System.out.println(total);
        System.out.println(hasNext);
        System.out.println(hasPrevious);
    }
    @Test
    public void test_query() {
        String query = pageService.query("npm", 1, 10);
        System.out.println(query);
    }
    @Test
    public void list() {
        QueryWrapper<PageDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", "npm");
        List<PageDO> pageDOList = pageMapper.selectList(queryWrapper);
        System.out.println(pageDOList);
    }
    @Test
    public void testAdd() {
        PageDO pageDO = new PageDO();
        pageDO.setUrl("test");
        int insert = pageMapper.insert(pageDO);
        System.out.println(insert);
    }

    @Test
    public void insertAll() throws IOException {
        String filePath = "D:\\T\\Documents\\VSCode\\js\\bili\\up\\37974444\\bvid\\BV1a34y167AZ.json";
//        new Path(filePath);
        Path path = Paths.get(filePath);
        List<PageDO> pages = import_json_bvid(path);
        System.out.println(pages);
        pages.forEach(pageDO -> {
            int insert = pageMapper.insert(pageDO);
            System.out.println(insert);
        });
    }

    @Test
    public void testSelect1() {
        List<PageDO> pages = pageMapper.selectBatchIds(Arrays.asList(1236960814, 1236960832));
        System.out.println(pages);
    }

    @Test
    public void testSelect2() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("cid",1236960814);
//        columnMap.put("age",20);
        List<PageDO> pages = pageMapper.selectByMap(columnMap);
        System.out.println(pages);
    }

    @Test
    public void testSelectMaps() {
        QueryWrapper<PageDO> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .select("url", "part")
//                .like("part", "npm");
        queryWrapper
                .like("part", "npm");
        List<Map<String, Object>> maps = pageMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }
}
