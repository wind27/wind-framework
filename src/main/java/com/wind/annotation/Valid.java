package com.wind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by qianchun on 17/12/11.
 */

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Valid {
    int maxLen() default 0;

    int minLen() default 0;

    boolean isNotEmpty() default  false;

    String[] contains() default {};

    int[] in() default {};

    String bs() default "";
}
