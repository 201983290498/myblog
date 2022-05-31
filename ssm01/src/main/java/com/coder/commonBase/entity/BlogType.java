package com.coder.commonBase.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *查看每个分类的博客数量
 * @author coder
 */
@Data
@AllArgsConstructor
public class BlogType implements Serializable{

	private static final long serialVersionUID = 1L;

  public static final Integer pageSize = 15;

	/**  */
    private Integer id;

    /**
     * 类别名称
     * 插入的值是字段本身,可以自动装配,一般输出的值是字段的字符窜
     */
    private com.coder.commom.annotation.Enum.BlogType typename;

    /** 该类别下的数量 */
    private Integer num;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date addTime;

  public BlogType() {
    num = 0;
  }
}
