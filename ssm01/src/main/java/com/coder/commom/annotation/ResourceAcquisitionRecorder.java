package com.coder.commom.annotation;

import com.coder.commom.annotation.Enum.ApplicationType;
import com.coder.commom.annotation.Enum.ResourceType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Map;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The interface Resource acquisition recorder.
 *
 * @Author coder
 * @Date 2021 /12/3 12:19
 * @Description
 */
@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface ResourceAcquisitionRecorder {

  /**
   * Resource type resource type.
   *
   * @return 记录访问资源的类型 resource type
   */
  ResourceType resourceType() default ResourceType.HTML;

  /**
   * Application type application type.
   *
   * @return the application type
   */
  ApplicationType applicationType() default ApplicationType.BLOG;

  /**
   * Name string.
   *
   * @return the string
   */
  String name() default "文档";

  String role() default "user";
}
