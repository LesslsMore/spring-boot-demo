package com.lesslsmore.bili.entity.video;

import lombok.Data;

import java.util.List;

@Data
public class InfoData {

    private String bvid;
    private long aid;
    private int videos;
    private int tid;
    private String tname;
    private int copyright;
    private String pic;
    private String title;
    private long pubdate;
    private long ctime;
    private String desc;
    private int state;
    private int duration;
    private long mission_id;

    private InfoOwner owner;
    private InfoStat stat;

    private String dynamic;
    private long cid;
    private String premiere;
    private int teenage_mode;
    private boolean is_chargeable_season;
    private boolean is_story;
    private boolean is_upower_exclusive;
    private boolean is_upower_play;
    private boolean is_upower_preview;
    private int enable_vt;
    private String vt_display;
    private boolean no_cache;

//    private List<Pages> pages;
    private List<InfoPages> pages;

    private boolean is_season_display;
    private String like_icon;
    private boolean need_jump_bv;
    private boolean disable_show_up_info;
    private int is_story_play;
}
