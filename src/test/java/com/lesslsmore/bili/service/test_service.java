package com.lesslsmore.bili.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lesslsmore.bili.entity.user.SpaceResp;
import com.lesslsmore.bili.entity.user.SpaceVlist;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.video.InfoResp;
import com.lesslsmore.bili.service.user.SpaceService;
import com.lesslsmore.bili.service.video.InfoService;

import static com.lesslsmore.bili.common.Utils.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class test_service {
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
    public void test_getVideoInfos() throws IOException {
        List<InfoPagesExt> infos = infoService.getVideoInfos("restful");
        System.out.println(infos.size());
        String pathname = "InfoPagesExt.xlsx";
        saveExcel(infos, pathname);
    }

    @Test
    public void test_saveExcel() throws IOException {
        long startTime = System.currentTimeMillis();

        List<InfoPagesExt> infos = infoService.getVideoInfos();
        System.out.println(infos.size());
        String pathname = "InfoPagesExt.xlsx";
        saveExcel(infos, pathname);

        long endTime = System.currentTimeMillis();
        System.out.println("接口调用共耗时:{}毫秒" + (endTime - startTime));
    }

    @Test
    public void test_saveUserSpace() {
        List<String> bvids = spaceService.saveUserSpace("302417610", 3);
        System.out.println(bvids);
    }

    @Test
    public void test_saveUserSpaces() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        // let mid = '37974444'
        // let pn = 17
        List<String> bvids = spaceService.saveUserSpaces("302417610", 14);
        System.out.println(bvids);

        long endTime = System.currentTimeMillis();
        System.out.println("接口调用共耗时:{}毫秒" + (endTime - startTime));
    }

    @Test
    public void test_saveVideoInfo() {
        int size = infoService.saveVideoInfo("BV1a4411B7V9");
        System.out.println(size);
    }

    @Test
    public void test_spaceVlistService() throws IOException {
        SpaceResp resp = getUserSpace("302417610", 11);
        List<SpaceVlist> vlist = resp.getData().getList().getVlist();
        boolean b = spaceVlistService.saveBatch(vlist);
//        List<String> bvids = Arrays.stream().map(bvid -> bvid.getBvid()).collect(Collectors.toList());
        System.out.println(vlist);
    }

    @Test
    public void test_infoPagesExtService() throws IOException {
//        BV19W411G7Sa
//        BV1a4411B7V9

        InfoResp resp = getVideoInfo("BV19W411G7Sa");

        List<InfoPagesExt> infoPagesExts = resp2infoPagesExts(resp);
        boolean saved = infoPagesExtService.saveOrUpdateBatch(infoPagesExts);
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
}
