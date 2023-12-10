package com.example.demo.entity.bili;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString(callSuper = true)
@Document("item")
@TableName("item")
public class ItemDO extends Item {
    private String bvid;
    private String url;
    private int view;
}
