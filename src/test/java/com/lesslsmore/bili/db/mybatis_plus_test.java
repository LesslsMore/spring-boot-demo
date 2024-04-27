package com.lesslsmore.bili.db;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.mapper.InfoPagesExtMapper;
import com.lesslsmore.bili.controller.MybatisPlusService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

@SpringBootTest
public class mybatis_plus_test {
    @Autowired
    private InfoPagesExtMapper infoPagesExtMapper;
    @Autowired
    private MybatisPlusService mybatisPlusService;


    @Test
    public void testSelectPage() {
        QueryWrapper<InfoPagesExt> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", "npm");
        Page<InfoPagesExt> page = new Page(1,10);
        Page<InfoPagesExt> userPage = infoPagesExtMapper.selectPage(page, queryWrapper);
        //返回对象得到分页所有数据
        long pages = userPage.getPages(); //总页数
        long current = userPage.getCurrent(); //当前页
        List<InfoPagesExt> records = userPage.getRecords(); //查询数据集合
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
//        Page<PageDO> query = mybatisPlusService.list("npm", 1, 10);
//        System.out.println(query);
    }
    @Test
    public void list() {
        QueryWrapper<InfoPagesExt> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("part", "npm");
        List<InfoPagesExt> infoPagesExtList = infoPagesExtMapper.selectList(queryWrapper);
        System.out.println(infoPagesExtList);
    }

//    读取 bvid json 存入 bili page 数据库 数据表中
    @Test
    public void test_insert_json() throws IOException {
//        List<String> ls = new ArrayList<>();
//        ls = Arrays.asList("302417610", "37974444");
//        String dir = "D:\\T\\Documents\\VSCode\\js\\bili\\up";
//
//        ls.forEach(mid -> {
//            Path path = Paths.get(dir, mid, "bvid");
//            try {
//                List<Path> paths = Utils.walk_dir(path);
//                paths.forEach(p -> {
//                    try {
//                        List<PageDO> items = Utils.import_json_bvid(p);
//                        items.forEach(item -> {
//                            int insert = pageMapper.insert(item);
//                        });
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        System.out.println("done");
    }

    @Test
    public void testSelect1() {
        List<InfoPagesExt> pages = infoPagesExtMapper.selectBatchIds(Arrays.asList(1236960814, 1236960832));
        System.out.println(pages);
    }

    @Test
    public void testSelect2() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("cid",1236960814);
//        columnMap.put("age",20);
        List<InfoPagesExt> pages = infoPagesExtMapper.selectByMap(columnMap);
        System.out.println(pages);
    }

    @Test
    public void testSelectMaps() {
        QueryWrapper<InfoPagesExt> queryWrapper = new QueryWrapper<>();
//        queryWrapper
//                .select("url", "part")
//                .like("part", "npm");
        queryWrapper
                .like("part", "npm");
        List<Map<String, Object>> maps = infoPagesExtMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }
}
