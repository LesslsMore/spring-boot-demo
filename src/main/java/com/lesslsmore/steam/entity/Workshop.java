package com.lesslsmore.steam.entity;

import lombok.Data;

@Data
public class Workshop {
    private String id;
    private String pid;
    private String href;
    private String rate;
    private String img;
    private String author;
    private String author_link;
    private String title;
}
