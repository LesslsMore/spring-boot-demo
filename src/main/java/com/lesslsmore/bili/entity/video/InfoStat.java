package com.lesslsmore.bili.entity.video;

import lombok.Data;

@Data
public class InfoStat {
//    int view;
    private long aid;
    private long view;
    private int danmaku;
    private int reply;
    private int favorite;
    private int coin;
    private int share;
    private int now_rank;
    private int his_rank;
    private int like;
    private int dislike;
    private String evaluation;
    private int vt;
}
