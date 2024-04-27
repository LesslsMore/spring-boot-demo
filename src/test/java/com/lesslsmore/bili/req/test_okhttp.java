package com.lesslsmore.bili.req;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.user.SpaceResp;
import com.lesslsmore.bili.entity.user.SpaceVlist;
import com.lesslsmore.bili.entity.video.InfoPages;
import com.lesslsmore.bili.entity.video.InfoResp;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.lesslsmore.bili.common.API.getUserSpace;
import static com.lesslsmore.bili.common.API.getVideoInfo;
import static com.lesslsmore.bili.common.Utils.*;

public class test_okhttp {
    @Test
    public void test_getUserSpace() throws IOException {
        //        302417610 11
//        SpaceResp resp = getUserSpace("302417610", 3);
        SpaceResp resp = getUserSpace("302417610", 12);
        System.out.println(resp);
    }

    @Test
    public void test_getVideoInfo() throws IOException {
        InfoResp infoResp = getVideoInfo("BV1jW411u7tF");
        System.out.println(infoResp);
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
        List<InfoPages> pages = resp.getData().getPages();
//        List<String> bvids = Arrays.stream().map(bvid -> bvid.getBvid()).collect(Collectors.toList());
//        System.out.println(pages);

        List<InfoPagesExt> infoPagesExts = resp2infoPagesExts(resp);
        System.out.println(infoPagesExts);
    }
}
