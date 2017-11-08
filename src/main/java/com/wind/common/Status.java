package com.wind.common;

/**
 * Created by qianchun on 17/11/6.
 */
public enum Status {
    ENABLED(1,  "启用"),
    DISABLED(0,  "禁用");



    private int value;
    private String name;

    Status(int value, String name) {
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
