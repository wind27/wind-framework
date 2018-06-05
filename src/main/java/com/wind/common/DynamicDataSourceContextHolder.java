package com.wind.common;

import java.util.ArrayList;
import java.util.List;

/**
 * DynamicDataSourceContextHolder
 *
 * @author qianchun
 * @date 2018/4/20
 **/
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static List<String> dataSourceKeys = new ArrayList<String>();


    public static void setDataSourceKey(String key) {
        if (!dataSourceKeys.contains(key)) {
            dataSourceKeys.add(key);
        }
        holder.set(key);
    }

    public static String getDataSourceKey() {
        return holder.get();
    }

    public static void clearDataSourceKey() {
        holder.remove();
    }
}
