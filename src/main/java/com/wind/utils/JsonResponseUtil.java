package com.wind.utils;

import com.alibaba.fastjson.JSONObject;
import com.wind.common.ErrorCode;

/**
 * JsonResponseUtil
 *
 * @author qianchun 17/7/13
 **/
public class JsonResponseUtil {

    public static String ok() {
        JSONObject result = new JSONObject();
        result.put("code", ErrorCode.SUCCESS.getValue());
        result.put("msg", ErrorCode.SUCCESS.getName());
        return result.toString();
    }

    public static String ok(Object obj) {
        JSONObject result = new JSONObject();
        result.put("code", ErrorCode.SUCCESS.getValue());
        result.put("msg", ErrorCode.SUCCESS.getName());
        result.put("data", obj);
        return result.toString();
    }

    public static String fail() {
        JSONObject result = new JSONObject();
        result.put("code", ErrorCode.FAIL.getValue());
        result.put("msg", ErrorCode.FAIL.getName());
        return result.toString();
    }

    public static String fail(ErrorCode errorCode) {
        JSONObject result = new JSONObject();
        result.put("code", errorCode.getValue());
        result.put("msg", errorCode.getName());
        return result.toString();
    }

    public static String fail(int code, String msg) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("msg", msg);
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(fail(ErrorCode.ERROR.SYS_ERROR));
    }
}
