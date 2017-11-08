package com.wind.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * @author qianchun  17/7/21.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Repository
public @interface DAO {
    String catalog() default "";
}
