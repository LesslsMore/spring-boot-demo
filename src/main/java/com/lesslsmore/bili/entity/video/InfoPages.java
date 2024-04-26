package com.lesslsmore.bili.entity.video;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pages")
public class InfoPages {
    @TableId
    private long cid;
    private int page;
//    @TableField(value = "`from`")
//    private String from; // 和 sql 关键字冲突
    private String part;
    private int duration;
//    private String vid;
//    private String weblink;
//    private String first_frame;
}
