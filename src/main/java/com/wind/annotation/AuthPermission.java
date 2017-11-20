package com.wind.annotation;

import java.lang.annotation.*;

/**
 * @author qianchun  17/7/21.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPermission {
    String value() default "";
}
