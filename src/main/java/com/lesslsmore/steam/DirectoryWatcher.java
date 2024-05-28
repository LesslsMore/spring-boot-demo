package com.lesslsmore.steam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

import static com.lesslsmore.steam.SteamWorkshopUnpack.executeCommand;
import static com.lesslsmore.steam.SteamWorkshopUnpack.genLpkCmd;
import static java.nio.file.StandardWatchEventKinds.*;

@Component
public class DirectoryWatcher {
    @Autowired
    public static ExecutorService executorService;

    public static void main(String[] args) {
        String dir = "E:\\T\\Downloads\\steamcmd\\steamapps\\workshop\\content\\616720";
        // 要监听的目录路径
        Path path = Paths.get(dir);

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            // 注册监听的事件类型
            path.register(watchService, ENTRY_CREATE);

            System.out.println("Watching directory: " + path);

            // 无限循环等待事件
            while (true) {
                WatchKey key;
                try {
                    // 等待下一个文件系统事件
                    key = watchService.take();
                } catch (InterruptedException e) {
                    System.out.println("WatchService interrupted");
                    return;
                }

                // 处理事件
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    // 获取事件发生的文件名
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();

                    // 输出事件和文件名
                    System.out.println(kind.name() + ": " + fileName);
                    Path resolve = path.resolve(fileName);
                    System.out.println(resolve);

                    if (kind.equals(ENTRY_CREATE)) {
//                        executorService.submit(() -> {
//                        });
//                        unpack(resolve);
                    }

                    // 可以在这里添加具体的处理逻辑，如备份文件、同步文件等
                }

                // 重置 key 以继续监听
                boolean valid = key.reset();
                if (!valid) {
                    System.out.println("WatchKey no longer valid");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

//    public static void unpack(Path resolve) {
//        try {
//            Stream<Path> list = Files.list(resolve);
//            boolean lpk = list.anyMatch(file -> file.toString().endsWith(".lpk"));
//            Stream<Path> list2 = Files.list(resolve);
//            boolean json = list2.anyMatch(file -> file.toString().endsWith(".json"));
//            System.out.println(json);
//            if (lpk && json) {
//                String cmd = genLpkCmd(resolve);
//                System.out.println(cmd);
//                try {
//                    executeCommand(cmd);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}