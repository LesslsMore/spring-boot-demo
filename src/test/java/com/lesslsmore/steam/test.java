package com.lesslsmore.steam;

import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.lesslsmore.steam.SteamWorkshopUnpack.executeCommand;

public class test {
    @Test
    public void test() throws IOException {
        System.out.println("中文");
        executeCommand("ipconfig");
        executeCommand("echo 您好!我是Java.");
    }
    @Test
    public void test2() throws IOException {
        String s = "E:\\T\\Downloads\\steamcmd\\steamapps\\workshop\\content\\616720\\2186042593";
        boolean b = Files.list(Paths.get(s)).anyMatch(file -> file.toString().endsWith(".lpk"));
        System.out.println(b);
    }
}
