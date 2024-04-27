package com.lesslsmore.bili.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lesslsmore.bili.entity.user.SpaceResp;
import com.lesslsmore.bili.entity.video.InfoPagesExt;
import com.lesslsmore.bili.entity.video.InfoData;
import com.lesslsmore.bili.entity.video.InfoResp;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    public static List<String> getNames(Class<?> clazz) {
        List<String> names = new ArrayList<>();

        // 遍历类及其父类的所有字段
        while (clazz != null) {
//            System.out.println("Fields for class: " + clazz.getName());
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
//                System.out.println("Field Name: " + field.getName() + ", Type: " + field.getType());
                names.add(field.getName());
            }
            // 获取父类的Class对象，继续遍历父类的字段
            clazz = (Class<InfoPagesExt>) clazz.getSuperclass();
        }
        return names;
    }

//    public static String formatTime(int seconds) {
//        Duration duration = Duration.ofSeconds(seconds);
//        long hours = duration.toHours();
//        long minutes = duration.toMinutes() % 60;
//        long remainingSeconds = duration.getSeconds() % 60;
//
//        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
//    }

    public static String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    public static void saveExcel(List<InfoPagesExt> list, String pathname) throws IOException {
        //在内存中创建一个Excel文件对象
        XSSFWorkbook excel = new XSSFWorkbook();
        //创建Sheet页
        XSSFSheet sheet = excel.createSheet("InfoPagesExt");

        //在Sheet页中创建行，0表示第1行
        XSSFRow row0 = sheet.createRow(0);

        List<String> names = Arrays.asList("name", "mid", "bvid", "cid", "url", "page", "view", "duration", "part");
        List<Integer> widths = Arrays.asList(10, 10, 12, 10, 45, 4, 8, 8, 45);
        for (int i = 0; i < names.size(); i++) {
            row0.createCell(i).setCellValue(names.get(i));
            // 设置每列的宽度
            sheet.setColumnWidth(i, widths.get(i) * 300);
        }

        list.sort((a, b) -> {
            if (a.getView() != b.getView()) {
                return Math.toIntExact(b.getView() - a.getView());
            } else {
                return a.getPage() - b.getPage();
            }
        });

        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            InfoPagesExt info = list.get(i);
            List<?> infos = Arrays.asList(info.getName(), info.getMid(), info.getBvid(), info.getCid(), info.getUrl(), info.getPage(), info.getView(), info.getDuration(), info.getPart());
            for (int j = 0; j < infos.size(); j++) {
                XSSFCell cell = row.createCell(j);
                if (j == 8) {
                    cell.setCellValue(info.getPart());
                    // 创建一个超链接对象
                    XSSFHyperlink hyperlink = (XSSFHyperlink) excel.getCreationHelper().createHyperlink(HyperlinkType.URL);
                    hyperlink.setAddress(info.getUrl());

                    cell.setHyperlink(hyperlink);
                } else if (j == 7) {
                    cell.setCellValue(formatTime(info.getDuration()));
                } else if (infos.get(j) instanceof String) {
                    cell.setCellValue((String) infos.get(j));
                } else {
                    cell.setCellValue(String.valueOf(infos.get(j)));
                } // 如果有其他类型的数据需要处理，可以继续添加相应的判断和处理逻辑
            }
        }

        FileOutputStream out = new FileOutputStream(new File(pathname));
        //通过输出流将内存中的Excel文件写入到磁盘上
        excel.write(out);

        //关闭资源
        out.flush();
        out.close();
        excel.close();
    }

    public static InfoResp getVideoInfo(String bvid) throws IOException {
        String path = String.format("E:\\T\\Documents\\VSCode\\proj\\vue\\vue3-ts-web-proj\\nodejs-bili-part-video-search\\up\\302417610\\bvid\\%s.json", bvid);
        String s = readFile(path);
        // 使用 FastJSON 解析 JSON 字符串为 JSONObject
        JSONObject jsonObject = JSON.parseObject(s);
        // 将 JSONObject 转换为 Java 对象
        InfoResp resp = jsonObject.toJavaObject(InfoResp.class);
        return resp;
    }

    public static SpaceResp getUserSpace(String mid, Integer pn) throws IOException {
        String path = String.format("E:\\T\\Documents\\VSCode\\proj\\vue\\vue3-ts-web-proj\\nodejs-bili-part-video-search\\up\\%s\\page\\%d.json", mid, pn);
        String s = readFile(path);
        // 使用 FastJSON 解析 JSON 字符串为 JSONObject
        JSONObject jsonObject = JSON.parseObject(s);

        // 将 JSONObject 转换为 Java 对象
        SpaceResp resp = jsonObject.toJavaObject(SpaceResp.class);
        return resp;
    }


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
