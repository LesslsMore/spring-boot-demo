package com.lesslsmore.bili.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lesslsmore.bili.entity.bili.Data;
import com.lesslsmore.bili.entity.bili.PageDO;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static List<PageDO> import_json_bvid(Path filePath) throws IOException {
        File file = new File(filePath.toString());
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String jsonString = new String(bytes);
        Data data = str2data(jsonString);
        List<PageDO> pageDOList = data2page(data);
        return pageDOList;
    }

    public static Data str2data(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONObject data = jsonObject.getJSONObject("data");
        Data biliData = data.toJavaObject(Data.class);
        return biliData;
    }

    public static List<PageDO> data2page(Data data) {
        List<PageDO> pageDOList = data.getPages().stream().map(biliPage -> {
            PageDO pageDO = new PageDO();
            BeanUtils.copyProperties(biliPage, pageDO);

            pageDO.setBvid(data.getBvid());
            pageDO.setView(data.getStat().getView());
            pageDO.setMid(data.getOwner().getMid());
            pageDO.setName(data.getOwner().getName());
            pageDO.setUrl(String.format("https://www.bilibili.com/video/%s?p=%d", data.getBvid(), pageDO.getPage()));
            return pageDO;
        }).collect(Collectors.toList());
        return pageDOList;
    }

    public static List<Path> walk_dir(Path p) throws IOException {

        try (Stream<Path> paths = Files.walk(p)) {
            return paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .collect(Collectors.toList());
//                    .forEach(path -> {
//                        //                            String jsonString = Files.readString(path);
////                        System.out.println(path);
//                        System.out.println(path.getFileName());
//                    });
        }
    }
}
