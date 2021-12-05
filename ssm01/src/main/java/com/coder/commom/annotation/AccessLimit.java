package com.coder.commom.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The interface Access limit.
 *
 * @author coder
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {

    /**
     * seconds 内连续点击，次数累计
     *
     * @return int
     */
    int seconds() default 5;

    /**
     * 最大累加次数
     *
     * @return int
     */
    int maxCount() default 2;

}
