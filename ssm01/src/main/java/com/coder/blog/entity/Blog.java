package com.coder.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 博客类，关联用户类和评价类，同时与博客的类型相关
 * @author coder
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable{

	private static final long serialVersionUID = 1L;

  /**
   * 博文的发布者
   */
  private String username;

    /** 自增 */
    private Integer id;

    /** 博客标题 */
    private String title;

    /** 博客简介/摘要 */
    private String introduction;

    /**  */
    private String keyword;

    /**
     * 封面缩略图等,images是博文对应的图片,通过博文——图片列表关联到一起
     * 添加的时候比较麻烦，先单独上传图片，然后在ids列表，不用设置关联的，因为mybatis不支持多表同时修改，
     * 统一改成通过id去数据库拿信息，而不用通过博文，可以加速页面的加载
     */
    private List<String> imageIds;

    /** 点击量 */
    private Integer clickNum;

    /** 评论量 */
    private Integer commentNum;

    /** 点赞量 */
    private Integer agreeNum;

    /** 1置顶，0普通 */
    private boolean isTop;

    /** 博主推荐,1为推荐，0为普通 */
    private boolean isRecommend;

    /**  */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    /**  */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date addTime;

    /** -1为草稿，1为正文 ，2为回收站*/
    private Integer status;

  /**
   * 获取参数的时候，获取到的是类别名，可能需要单独处理
   */
    private BlogType type;

    /** 博客内容 */
    private String content;

}
