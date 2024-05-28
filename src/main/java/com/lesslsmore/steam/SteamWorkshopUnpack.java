package com.lesslsmore.steam;

import com.alibaba.fastjson.JSON;
import com.lesslsmore.steam.entity.Workshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lesslsmore.steam.SteamWorkshopDown.json2item;

public class SteamWorkshopUnpack {
    private static final String STEAM_JSON = "E:\\T\\Documents\\VSCode\\proj\\30-monkey-scripts\\steam";
    private static final String DIRECTORY = "E:\\T\\Downloads\\steamcmd\\steamapps\\workshop\\content\\616720";
    private static final String LPK_UNPACKER_PATH = "E:\\T\\Documents\\VSCode\\proj\\40-live2d\\exe\\LpkUnpacker\\LpkUnpacker.py";
    private static final String DST_DIRECTORY = "E:\\T\\Downloads\\steamcmd\\steamapps\\workshop\\content";

    public static void main(String[] args) throws IOException {
        List<Path> jsons = Files.list(Paths.get(STEAM_JSON)).filter(file -> file.toString().endsWith(".json")).collect(Collectors.toList());
//        System.out.println(jsons);
        jsons.forEach(json -> json_proc(json));
//        try {
//            List<String> cmds = genCmd();
//            for (String cmd : cmds) {
//                System.out.println(cmd);
////                executeCommand(cmd);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void json_proc(Path json) {
        try {
            List<Workshop> workshops = json2item(json.toString());
            Set<String> down_items = Files.list(Paths.get(DIRECTORY))
                    .filter(Files::isDirectory).map(file -> file.getFileName().toString()).collect(Collectors.toSet());
            List<String> cmds = new ArrayList<>();
            workshops.forEach(item -> {
                if (down_items.contains(item.getPid())) {
                    cmds.add(genLpkCmd(Paths.get(DIRECTORY, item.getPid()), json.getFileName().toString().split("\\.")[0]));
                }
            });
            cmds.forEach(cmd -> {
                System.out.println(cmd);
                try {
                    executeCommand(cmd);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

    }

    public static String genLpkCmd(Path dirPath, String dst_dir) {
        File lpkFile = Paths.get(dirPath.toString(), dirPath.getFileName().toString() + ".lpk").toFile();
        File configFile = Paths.get(dirPath.toString(), "config.json").toFile();

        String title = "";
        try {
            byte[] bytes = Files.readAllBytes(configFile.toPath());
            String configContent = new String(bytes);
            title = JSON.parseObject(configContent).getString("title");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title_norm = sanitizeDirectoryName(title);
        Path dstPath = Paths.get(DST_DIRECTORY, dst_dir, title_norm);
        dstPath.toFile().mkdirs();

        return String.format("python \"%s\" \"%s\" \"%s\" -c \"%s\"", LPK_UNPACKER_PATH, lpkFile.getAbsolutePath(), dstPath, configFile.getAbsolutePath());
    }

    public static void executeCommand(String command) throws IOException {
        try {
            // 创建 ProcessBuilder 实例
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("cmd.exe", "/c", command); // chcp 65001 设置 CMD 编码为 UTF-8

            // 启动进程
            Process process = builder.start();

            // 处理标准输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 处理标准错误输出
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.forName("GBK")));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            // 等待进程结束
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sanitizeDirectoryName(String name) {
        // 定义非法字符的正则表达式模式
        String illegalChars = "[<>:\"/\\\\|?*\\x00-\\x1F]"; // Windows 中的非法字符
        // 用下划线替换非法字符
        return name.replaceAll(illegalChars, "_").trim();
    }
}
