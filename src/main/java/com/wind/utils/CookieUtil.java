package com.wind.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieUtil
 *
 * @author qianchun 17/11/21
 **/
public class CookieUtil {

    /**
     * 添加 cookie
     * @param response 请求 response
     * @param key cookie 名称
     * @param value cookie 值
     */
    public static void addCookie(HttpServletResponse response, String key, String value) {
        response.addCookie(new Cookie(key, value));
    }

    /**
     * 获取 cookie
     * @param request 请求 request
     * @param cookieName cookie 名称
     * @return 返回 cookie 值
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        if(StringUtils.isEmpty(cookieName)) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0) {
            for(Cookie cookie : cookies) {
                if(cookie!=null && cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 移除 cookie
     * @param request 请求 request
     * @param response 请求 response
     * @param key cookie 名称
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        if(StringUtils.isEmpty(key)) {
            return ;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length>0) {
            for(Cookie cookie : cookies) {
                if(cookie!=null && key.equals(cookie.getName())) {
                    cookie.setValue(null);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
}
