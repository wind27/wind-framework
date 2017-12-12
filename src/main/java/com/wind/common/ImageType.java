package com.wind.common;

/**
 * Created by qianchun on 17/11/30.
 */
public enum ImageType {

    AVATER(1, "头像"), ALBUMS(2, "相册"), BLOG(3, "博客");

    private int value;

    private String name;

    ImageType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
