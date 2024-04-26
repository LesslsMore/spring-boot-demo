package com.lesslsmore.bili.entity.video;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
//import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString(callSuper = true)
//@Document("page")
@TableName("pages_ext")
public class InfoPagesExt extends InfoPages {
    private String name;
    private long mid;
    private String bvid;
    private String url;
    private long view;
}
