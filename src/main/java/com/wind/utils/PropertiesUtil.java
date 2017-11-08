package com.wind.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * PropertiesUtil
 *
 * @author qianchun 17/9/18
 **/
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties properties;

    /**
     * 读取配置文件数据流
     * @return 返回 Properties
     */
    private static Properties getInstance(String fileName) {
        try {
            if (properties == null) {
                properties = new Properties();
                InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
                properties.load(in);
                Enumeration enum1 = properties.propertyNames();
                while (enum1.hasMoreElements()) {
                    String key = (String) enum1.nextElement();
                    String value = properties.getProperty(key);
                }
            }
        } catch (IOException e) {
            logger.error("[PropertiesUtil] 读取配置文件server.properties异常, e={}", e);
            return null;
        }
        return properties;
    }

    /**
     * 获取 key 值对应的value
     * @param key key
     * @return 返回 key 对应 value
     */
    public static String getValue(String fileName, String key) {
        properties = getInstance(fileName);
        if (properties != null) {
            return properties.get(key) == null ? null : String.valueOf(properties.get(key));
        }
        return null;
    }

}
