package com.coder.commom.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * 写在实体类上的数据，用来显示该列数据在表格中的列名
 * @author coder
 */
@Retention(RUNTIME)
@Target({METHOD,TYPE,FIELD})
public @interface TableField {

  /**
   * @return 列表
   */
  String column() default "未知";

  /**
   * 标注在方法上，代表是否开启渲染还是原表呈现
   */
  boolean enableRendering() default false;

}
