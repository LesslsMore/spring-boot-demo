package com.lesslsmore.bili;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.lesslsmore.bili.mapper")
public class BiliApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(BiliApplication.class, args);
    }
}