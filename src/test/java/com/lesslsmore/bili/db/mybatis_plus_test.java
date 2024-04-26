package com.lesslsmore.bili.db;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.user.SpaceResp;
import com.lesslsmore.bili.entity.user.SpaceVlist;
import com.lesslsmore.bili.entity.video.InfoPages;
import com.lesslsmore.bili.entity.video.InfoResp;
import com.lesslsmore.bili.mapper.InfoPagesExtMapper;
import com.lesslsmore.bili.service.InfoPagesExtService;
import com.lesslsmore.bili.service.InfoPagesService;
import com.lesslsmore.bili.controller.MybatisPlusService;
import com.lesslsmore.bili.service.SpaceVlistService;
import com.lesslsmore.bili.service.user.SpaceService;
import com.lesslsmore.bili.service.video.InfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.lesslsmore.bili.common.Utils.readFile;
import static com.lesslsmore.bili.common.Utils.resp2infoPagesExts;

@SpringBootTest
public class mybatis_plus_test {
    @Autowired
    private InfoPagesExtMapper infoPagesExtMapper;
    @Autowired
    private MybatisPlusService mybatisPlusService;
    @Autowired
    public SpaceVlistService spaceVlistService;
    @Autowired
    public InfoPagesService infoPagesService;
    @Autowired
    public InfoPagesExtService infoPagesExtService;
    @Autowired
    public SpaceService spaceService;
    @Autowired
    public InfoService infoService;

    @Test
    public void test_saveUserSpace() {
        List<String> bvids = spaceService.saveUserSpace("302417610", 3);
        System.out.println(bvids);
    }
    @Test
    public void test_saveUserSpaces() throws ExecutionException, InterruptedException {
        List<String> bvids = spaceService.saveUserSpaces("302417610", 14);
        System.out.println(bvids);

        // let mid = '37974444'
        // let pn = 17
    }
    @Test
    public void test_saveVideoInfo() {
        int size = infoService.saveVideoInfo("BV1a4411B7V9");
        System.out.println(size);
    }

    @Test
    public void test_resp2object() throws IOException {
        String path = "E:\\T\\Documents\\VSCode\\proj\\vue\\vue3-ts-web-proj\\nodejs-bili-part-video-search\\up\\302417610\\page\\1.json";
        String s = readFile(path);
        // 使用 FastJSON 解析 JSON 字符串为 JSONObject
        JSONObject jsonObject = JSON.parseObject(s);

        // 将 JSONObject 转换为 Java 对象
        SpaceResp resp = jsonObject.toJavaObject(SpaceResp.class);
        List<SpaceVlist> vlist = resp.getData().getList().getVlist();

        boolean b = spaceVlistService.saveBatch(vlist);
//        List<String> bvids = Arrays.stream().map(bvid -> bvid.getBvid()).collect(Collectors.toList());
        System.out.println(vlist);
    }

    @Test
    public void test_resp2object_videoinfo() throws IOException {
        String path = "E:\\T\\Documents\\VSCode\\proj\\vue\\vue3-ts-web-proj\\nodejs-bili-part-video-search\\up\\302417610\\bvid\\BV1a4411B7V9.json";
        String s = readFile(path);
        // 使用 FastJSON 解析 JSON 字符串为 JSONObject
        JSONObject jsonObject = JSON.parseObject(s);

        // 将 JSONObject 转换为 Java 对象
        InfoResp resp = jsonObject.toJavaObject(InfoResp.class);
//        List<InfoPages> pages = resp.getData().getPages();

        List<InfoPagesExt> infoPagesExts = resp2infoPagesExts(resp);
        boolean saved = infoPagesExtService.saveBatch(infoPagesExts);
        System.out.println(infoPagesExts);

//        boolean b = infoPagesService.saveBatch(pages);
////        List<String> bvids = Arrays.stream().map(bvid -> bvid.getBvid()).collect(Collectors.toList());
//        System.out.println(pages);
    }

    @Test
    public void test_get_vlist() throws IOException {
        List<SpaceVlist> vlist = spaceVlistService.list();
        System.out.println(vlist);
    }

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
