package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString(callSuper = true)
@Document("Page")
@TableName("page")
public class PageDO extends Page{
    private String bvid;
    private String url;
    private int view;
}
