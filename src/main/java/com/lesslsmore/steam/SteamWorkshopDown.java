package com.lesslsmore.steam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lesslsmore.steam.entity.Workshop;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lesslsmore.steam.SteamWorkshopUnpack.executeCommand;

public class SteamWorkshopDown {
    public static void main(String[] args) throws Exception {

        String down_path = "E:\\T\\Downloads\\steamcmd\\steamapps\\workshop\\content\\616720";
        Set<String> down_items = Files.list(Paths.get(down_path)).map(file -> file.getFileName().toString()).collect(Collectors.toSet());

        String path = "E:\\T\\Documents\\VSCode\\proj\\30-monkey-scripts\\steam\\steam-workshop.json";

        List<Workshop> workshops = json2item(path);
        List<String> cmds = new ArrayList<>();
        workshops.forEach(item -> {
            if(!down_items.contains(item.getPid())) {
                String workshop_download_item = String.format("workshop_download_item %s %s", item.getId(), item.getPid());
                cmds.add(workshop_download_item);
            }
        });

        Set<String> todo_items = workshops.stream().map(item -> item.getPid()).collect(Collectors.toSet());

        List<String> list = down_items.stream().filter(item -> !todo_items.contains(item)).collect(Collectors.toList());
        System.out.println(list);


        System.out.println(cmds.size());
//        exe_cmds(cmds);
    }

    public static List<Workshop> json2item(String path) throws IOException {

        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String configContent = new String(bytes);
//        System.out.println(configContent);
//        JSONArray jsonObject = JSON.parseObject(configContent, JSONArray.class);

        JSONArray jsonArray = JSON.parseArray(configContent);

        // 将 JSONArray 转换为 List<User>
        List<Workshop> items = jsonArray.toJavaList(Workshop.class);
        return items;
    }

    public static void exe_cmds(List<String> cmds) throws IOException {
        String path = "E:\\T\\Downloads\\steamcmd";
        String steam = "steamcmd.exe";
        String steam_path = String.join("\\", path, steam);
        System.out.println(steam_path);
//            String cmd = steam_path;
//            executeCommand("cd " + path);
        String login = "login anonymous";

        String delimiter = " +";

//        String id = "1624692097";
//        String cmd_item = String.format("workshop_download_item 616720 %s", id);

        String cmd_login = String.join(delimiter, steam_path, login);

        String cmd_all = String.join(delimiter,cmds);

        String cmd = String.join(delimiter, cmd_login, cmd_all, "quit");
        System.out.println(cmd);

//            cmd = "ipconfig";
        executeCommand(cmd);
    }
}
