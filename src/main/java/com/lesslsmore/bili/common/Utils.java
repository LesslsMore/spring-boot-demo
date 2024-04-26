package com.lesslsmore.bili.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.video.InfoData;
import com.lesslsmore.bili.entity.video.InfoResp;
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
//    public static List<PageDO> import_json_bvid(Path filePath) throws IOException {
//        String jsonString = readFile(filePath);
//        Data data = str2data(jsonString);
//        List<PageDO> pageDOList = data2page(data);
//        return pageDOList;
//    }

    public static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String jsonString = new String(bytes);
        return jsonString;
    }

    private static String readFile(Path filePath) throws IOException {
        File file = new File(filePath.toString());
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String jsonString = new String(bytes);
        return jsonString;
    }

    public static InfoData str2data(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONObject data = jsonObject.getJSONObject("data");
        InfoData biliData = data.toJavaObject(InfoData.class);
        return biliData;
    }

    public static List<InfoPagesExt> resp2infoPagesExts(InfoResp resp) {
        InfoData data = resp.getData();

        List<InfoPagesExt> infoPagesExtList = data.getPages().stream().map(page -> {
            InfoPagesExt infoPagesExt = new InfoPagesExt();
            BeanUtils.copyProperties(page, infoPagesExt);

            infoPagesExt.setBvid(data.getBvid());
            infoPagesExt.setView(data.getStat().getView());
            infoPagesExt.setMid(data.getOwner().getMid());
            infoPagesExt.setName(data.getOwner().getName());
            infoPagesExt.setUrl(String.format("https://www.bilibili.com/video/%s?p=%d", data.getBvid(), infoPagesExt.getPage()));
            return infoPagesExt;
        }).collect(Collectors.toList());
        return infoPagesExtList;
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
