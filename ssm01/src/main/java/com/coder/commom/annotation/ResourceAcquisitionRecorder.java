package com.coder.commom.annotation;

import com.coder.commom.annotation.Enum.ApplicationType;
import com.coder.commom.annotation.Enum.ResourceType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author coder
 * @Date 2021/12/3 12:19
 * @Description
 */

@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface ResourceAcquisitionRecorder {

    /**
     * @return 记录访问资源的类型
     */
    ResourceType resourceType() default ResourceType.HTML;

    ApplicationType applicationType() default ApplicationType.BLOG;

    String name() default "文档";
}
