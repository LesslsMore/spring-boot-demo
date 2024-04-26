package com.lesslsmore.bili.config;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ProcessorsDemo {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());


    }
}
