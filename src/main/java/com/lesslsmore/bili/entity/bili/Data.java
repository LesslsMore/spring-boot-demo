package com.lesslsmore.bili.entity.bili;

import java.util.List;

@lombok.Data
public class Data {
    private String bvid;
    private Owner owner;
    private Stat stat;
    private List<Page> pages;
}
