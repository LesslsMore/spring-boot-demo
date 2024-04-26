package com.lesslsmore.bili.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("vlist")
public class SpaceVlist {
    private int comment;
    private int typeid;
    private int play;
    private String pic;
    private String subtitle;
    private String description;
    private String copyright;
    private String title;
    private int review;
    private String author;
    private long mid;
    private long created;
    private String length;
    private int video_review;
    private long aid;
    @TableId
    private String bvid;
    private boolean hide_click;
    private int is_pay;
    private int is_union_video;
    private int is_steins_gate;
    private int is_live_playback;
    private int is_lesson_video;
    private int is_lesson_finished;
    private String lesson_update_info;
    private String jump_url;
//    private String meta;
    private int is_avoided;
    private int season_id;
    private long attribute;
    private boolean is_charging_arc;
    private int vt;
    private int enable_vt;
    private String vt_display;
    private int playback_position;
}
