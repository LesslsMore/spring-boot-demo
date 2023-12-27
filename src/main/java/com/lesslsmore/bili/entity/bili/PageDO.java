package com.lesslsmore.bili.entity.bili;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString(callSuper = true)
@Document("page")
@TableName("page")
public class PageDO extends Page {
    private String name;
    private String mid;
    private String bvid;
    private String url;
    private int view;
}
