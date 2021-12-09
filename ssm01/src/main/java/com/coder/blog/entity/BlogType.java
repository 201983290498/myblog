package com.coder.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *查看每个分类的博客数量
 * @author coder
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogType implements Serializable{

	private static final long serialVersionUID = 1L;

	/**  */
    private Integer id;

    /** 类别名称 */
    private String typename;

    /** 该类别下的数量 */
    private Integer num;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date addTime;

}
