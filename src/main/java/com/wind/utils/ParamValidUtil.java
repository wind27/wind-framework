package com.wind.utils;

import com.wind.annotation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ParamValidUtil
 *
 * @author qianchun
 * @date 17/12/12
 **/
public class ParamValidUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static boolean validate(Object object) throws Exception {
        boolean flag = true;

        if (object == null) {
            return false;
        }

        Field[] fields = object.getClass().getFields();
        if (fields == null || fields.length == 0) {
            return true;
        }
        for (Field field : fields) {

            if (field == null || StringUtils.isEmpty(field.getName()) || field.getAnnotation(Valid.class) == null) {
                continue;
            }

            Valid valid = field.getAnnotation(Valid.class);

            String fieldName = field.getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method = object.getClass().getMethod(methodName);

            if (method == null) {
                flag = false;
                logger.error(fieldName + " 没有设置 get 方法");
                continue;
            }
            Object value = method.invoke(object);
            if (valid.isNotEmpty() && StringUtils.isEmpty(value)) {
                flag = false;
                logger.error("[Valid Annotation 注解校验] isNotEmpty={}  :: {}={}", valid.isNotEmpty(), fieldName, value);
            }

            if(valid.minLen() > 0 && (StringUtils.isEmpty(value) || valid.minLen() > value.toString().length())) {
                flag = false;
                logger.error("[Valid Annotation 注解校验] minLen={}  :: {}={}", valid.minLen(), fieldName, value);
            }

            if(valid.maxLen() > 0 && (StringUtils.isEmpty(value) || valid.maxLen() > value.toString().length())) {
                flag = false;
                logger.error("[Valid Annotation 注解校验] maxLen={}  :: {}={}", valid.maxLen(), fieldName, value);
            }

            if(valid.in().length > 0 && !Arrays.asList(valid.in()).contains(value)) {
                flag = false;
                logger.error("[Valid Annotation 注解校验] in={}  :: {}={}", valid.in(), fieldName, value);
            }

            if(valid.contains().length > 0 && !Arrays.asList(valid.contains()).contains(value)) {
                flag = false;
                logger.error("[Valid Annotation 注解校验] contains={}  :: {}={}", valid.contains(), fieldName, value);
            }
        }

        return flag;
    }

}
