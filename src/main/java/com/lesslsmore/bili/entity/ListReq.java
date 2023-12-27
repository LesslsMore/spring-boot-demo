package com.lesslsmore.bili.entity;

import lombok.Data;

@Data
public class ListReq {
    private String name; // up 主
    private String mid;  // up id
    private String bvid; // 视频合集 id
    private String part; // 视频分 p 名
}
